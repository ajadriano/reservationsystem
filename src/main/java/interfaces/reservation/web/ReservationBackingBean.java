/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.reservation.web;

import application.util.DateUtil;
import domain.flight.Airport;
import domain.flight.AirportCode;
import domain.flight.AirportRepository;
import domain.reservation.FlightClass;
import domain.user.SearchRequest;
import interfaces.reservation.facade.ReservationServiceFacade;
import interfaces.reservation.facade.dto.ItineraryDto;
import interfaces.user.facade.UserServiceFacade;
import interfaces.user.facade.dto.UserDto;
import interfaces.user.web.SessionBean;
import interfaces.user.web.UserBackingBean;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author ajadriano
 */
@Named
@FlowScoped("reservation")
public class ReservationBackingBean implements Serializable {
    
    private static final String RETURN = "Return";
    private static final String ONEWAY = "One-way";
    private static final String BUSINESS = "Business";
    private static final String ECONOMY = "Economy";
    
    @Inject
    private ReservationServiceFacade reservationServiceFacade;
    
    @Inject
    private AirportRepository airportRepository;
    
    private String flightType;
    private int passengerCount;
    private String flightClass;
    
    private List<ItineraryDto> outboundFlights;
    private List<ItineraryDto> inboundFlights;
    
    private ItineraryDto selectedOutboundFlight;
    private ItineraryDto selectedInboundFlight;
    
    private String creditCardNumber;
    private String creditCardExpiration;
    
    private double price;
    private String reservationNumber;
    
    private boolean confirmed;
    
    @Inject
    private UserServiceFacade userServiceFacade;
    
    private UserBackingBean userBackingBean = new UserBackingBean();
    
    private DashboardModel model;
    
    @PostConstruct
    public void init() {
        this.model = new DefaultDashboardModel();
        DashboardColumn mainColumn = new DefaultDashboardColumn();
 
        mainColumn.addWidget("OutboundFlights");
        mainColumn.addWidget("InboundFlights");
        mainColumn.addWidget("BookingDetails");  
 
        this.model.addColumn(mainColumn);
        
        outboundFlights = new ArrayList<>();
        inboundFlights = new ArrayList<>();
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        SearchRequest request = (SearchRequest) session.getAttribute("searchrequest");  
        if (request != null) {
            
            setFlightType(request.getFlightType());
            setFlightClass(request.getFlightClass());
            setPassengerCount(request.getPassengerCount());
            
            searchFlights(request);
            session.setAttribute("searchBackingBean", null);
        }
    }
    
    public DashboardModel getModel() {
        return model;
    }
 
    public void setModel(DashboardModel model) {
        this.model = model;
    }
    
    public String getReturnValue() {
        return "/admin/bookings.xhtml";
    }
    
    public void searchFlights(SearchRequest request) {
        Airport origin = airportRepository.find(new AirportCode(request.getFromAirport()));
        Airport destination = airportRepository.find(new AirportCode(request.getToAirport()));
        
        FlightClass selectedFlightClass = null;
        if (getFlightClass().equalsIgnoreCase(BUSINESS)) {
            selectedFlightClass = FlightClass.BUSINESS;
        }
        else if (getFlightClass().equalsIgnoreCase(ECONOMY)) {
            selectedFlightClass = FlightClass.ECONOMY;
        }
        
        getOutboundFlights().addAll(reservationServiceFacade.getItineraryOptions(origin, destination, 
                DateUtil.combine(request.getFromDate(), request.getFromTime()), 
                selectedFlightClass, getPassengerCount(), request.getStops()));
        if (getFlightType().equalsIgnoreCase(RETURN)) {
            getInboundFlights().addAll(reservationServiceFacade.getItineraryOptions(destination, origin, 
                    DateUtil.combine(request.getToDate(), request.getToTime()), 
                    selectedFlightClass, getPassengerCount(), request.getStops()));
        }
        
        if (outboundFlights.isEmpty()) {
            showError("No outbound flights available.");
        }
        if (getFlightType().equalsIgnoreCase(RETURN) && 
            inboundFlights.isEmpty()) {
            showError("No inbound flights available.");
        }
    }
    
    public String reserveFlight() {
        if (this.selectedOutboundFlight == null) {
            showError("Select an outbound flight.");
            return "reservation";
        }
        
        if (getFlightType().equalsIgnoreCase(RETURN) &&
            this.selectedInboundFlight == null) {
            showError("Select an inbound flight.");
            return "reservation";
        }
        
        price = selectedOutboundFlight.getCost();
        if (getFlightType().equalsIgnoreCase(RETURN)) {
            price += selectedInboundFlight.getCost();
        }
        
        String username = SessionBean.getUsername();
        if (username == null || username.isEmpty())
        {
            return "reservation-user";
        }
        
        UserDto user = userServiceFacade.find(username);
        
        this.creditCardNumber = user.getCreditCardNumber();
        this.creditCardExpiration = DateUtil.toYearMonthString(user.getCreditCardExpiration());

        FlightClass selectedFlightClass = null;
        if (getFlightClass().equalsIgnoreCase(BUSINESS)) {
            selectedFlightClass = FlightClass.BUSINESS;
        }
        else if (getFlightClass().equalsIgnoreCase(ECONOMY)) {
            selectedFlightClass = FlightClass.ECONOMY;
        }

        reservationNumber = reservationServiceFacade.reserve(username, 
                selectedOutboundFlight, 
                selectedInboundFlight, 
                selectedFlightClass, getPassengerCount());
        
        userServiceFacade.saveRequest(username, null);

        return "reservation-payment";
    }
    
    public String login() {
        UserDto user = userBackingBean.validate(userServiceFacade);
        if (user != null) {
            SessionBean.setUsername(user.getUsername());
            
            this.creditCardNumber = user.getCreditCardNumber();
            this.creditCardExpiration = DateUtil.toYearMonthString(user.getCreditCardExpiration());
            
            FlightClass selectedFlightClass = null;
            if (getFlightClass().equalsIgnoreCase(BUSINESS)) {
                selectedFlightClass = FlightClass.BUSINESS;
            }
            else if (getFlightClass().equalsIgnoreCase(ECONOMY)) {
                selectedFlightClass = FlightClass.ECONOMY;
            }
            
            reservationNumber = reservationServiceFacade.reserve(user.getUsername(), 
                    selectedOutboundFlight, 
                    selectedInboundFlight, 
                    selectedFlightClass, getPassengerCount());
            
            return "reservation-payment";
        }
        
        return "reservation-user";
    }
    
    public String register() {
        UserDto user = userBackingBean.register(userServiceFacade);
        if (user != null) {
            SessionBean.setUsername(user.getUsername());
            
            this.creditCardNumber = user.getCreditCardNumber();
            this.creditCardExpiration = DateUtil.toYearMonthString(user.getCreditCardExpiration());
            
            FlightClass selectedFlightClass = null;
            if (getFlightClass().equalsIgnoreCase(BUSINESS)) {
                selectedFlightClass = FlightClass.BUSINESS;
            }
            else if (getFlightClass().equalsIgnoreCase(ECONOMY)) {
                selectedFlightClass = FlightClass.ECONOMY;
            }
            
            reservationNumber = reservationServiceFacade.reserve(user.getUsername(), 
                    selectedOutboundFlight, 
                    selectedInboundFlight, 
                    selectedFlightClass, getPassengerCount());
            
            return "reservation-payment";
        }
        
        return "reservation-user";
    }
    
    public String confirmFlight() {
        if (isConfirmed() == false) {
            Date creditCardExpirationDate = null;
            if (creditCardExpiration != null && !creditCardExpiration.isEmpty()) {
                try {
                    creditCardExpirationDate = DateUtil.toYearMonthDate(creditCardExpiration);
                    
                    Date todayDate = new Date();
                    long diff = creditCardExpirationDate.getTime() - todayDate.getTime();
                    if (diff < 0) {
                        showError("Invalid expiration date.");
                        return "reservation-payment";
                    }
                    
                } catch (ParseException ex) {
                    showError("Invalid expiration date.");
                    return "reservation-payment";
                }
            }
            
            reservationServiceFacade.book(reservationNumber, creditCardNumber, creditCardExpirationDate);
            setConfirmed(true);
            return "reservation-payment";
        }
        return "/admin/bookings.xhtml?faces-redirect=true";
    }

    /**
     * @return the outboundFlights
     */
    public List<ItineraryDto> getOutboundFlights() {
        return outboundFlights;
    }

    /**
     * @return the inboundFlights
     */
    public List<ItineraryDto> getInboundFlights() {
        return inboundFlights;
    }

    /**
     * @return the selectedOutboundFlight
     */
    public ItineraryDto getSelectedOutboundFlight() {
        return selectedOutboundFlight;
    }

    /**
     * @return the selectedInboundFlight
     */
    public ItineraryDto getSelectedInboundFlight() {
        return selectedInboundFlight;
    }

    /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @return the creditCardExpiration
     */
    public String getCreditCardExpiration() {
        return creditCardExpiration;
    }

    /**
     * @param creditCardNumber the creditCardNumber to set
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * @param creditCardExpiration the creditCardExpiration to set
     */
    public void setCreditCardExpiration(String creditCardExpiration) {
        this.creditCardExpiration = creditCardExpiration;
    }

    /**
     * @param selectedOutboundFlight the selectedOutboundFlight to set
     */
    public void setSelectedOutboundFlight(ItineraryDto selectedOutboundFlight) {
        this.selectedOutboundFlight = selectedOutboundFlight;
    }

    /**
     * @param selectedInboundFlight the selectedInboundFlight to set
     */
    public void setSelectedInboundFlight(ItineraryDto selectedInboundFlight) {
        this.selectedInboundFlight = selectedInboundFlight;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    private void showError(String errorMessage) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(errorMessage);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
    }

    /**
     * @return the reservationNumber
     */
    public String getReservationNumber() {
        return reservationNumber;
    }

    /**
     * @return the flightType
     */
    public String getFlightType() {
        return flightType;
    }

    /**
     * @param flightType the flightType to set
     */
    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    /**
     * @return the passengerCount
     */
    public int getPassengerCount() {
        return passengerCount;
    }

    /**
     * @param passengerCount the passengerCount to set
     */
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    /**
     * @return the flightClass
     */
    public String getFlightClass() {
        return flightClass;
    }

    /**
     * @param flightClass the flightClass to set
     */
    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    /**
     * @return the confirmed
     */
    public boolean isConfirmed() {
        return confirmed;
    }
    
    /**
     * @return the confirmed
     */
    public boolean isNotConfirmed() {
        return !confirmed;
    }
    
    public boolean isCanReserveFlights() {
        boolean canReserve = true;
        
        if (getOutboundFlights().isEmpty()) {
            return false;
        }
        if (getFlightType().equalsIgnoreCase(RETURN) && getInboundFlights().isEmpty()) {
            return false;
        }
        
        return canReserve;
    }
    
    public boolean isSearchAgainAvailable() {
        return !isCanReserveFlights();
    }
    
    public boolean isHasReturnFlight() {
        return getFlightType().equalsIgnoreCase(RETURN);
    }

    /**
     * @param confirmed the confirmed to set
     */
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    /**
     * @return the userBackingBean
     */
    public UserBackingBean getUserBackingBean() {
        return userBackingBean;
    }

    /**
     * @param userBackingBean the userBackingBean to set
     */
    public void setUserBackingBean(UserBackingBean userBackingBean) {
        this.userBackingBean = userBackingBean;
    }
}
