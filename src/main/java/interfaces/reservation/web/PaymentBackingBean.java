/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.reservation.web;

import application.util.DateUtil;
import domain.reservation.ReservationStatus;
import interfaces.reservation.facade.ReservationServiceFacade;
import interfaces.reservation.facade.dto.ItineraryDto;
import interfaces.reservation.facade.dto.LegDto;
import interfaces.reservation.facade.dto.ReservationDto;
import interfaces.user.facade.UserServiceFacade;
import interfaces.user.facade.dto.UserDto;
import interfaces.user.web.SessionBean;
import interfaces.user.web.UserBackingBean;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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
@ViewScoped
public class PaymentBackingBean implements Serializable {

    @Inject
    private ReservationServiceFacade reservationServiceFacade;
    
    private int passengerCount;
    private String flightClass;
    
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
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        
        String ticketNumber = (String) session.getAttribute("reservation");  
        
        if (ticketNumber != null) {
            ReservationDto reservationDto = reservationServiceFacade.getReservation(ticketNumber);
            if (reservationDto != null) {
                selectedOutboundFlight = reservationDto.getOutboundFlight();
                selectedInboundFlight = reservationDto.getInboundFlight();
                passengerCount = reservationDto.getSeats();
                flightClass = reservationDto.getFlightClass();
                reservationNumber = reservationDto.getTicketNumber();

                price = 0;
                for (LegDto leg : selectedOutboundFlight.getLegs()) {
                    price += leg.getCost();
                }
                
                if (isHasReturnFlight()) {
                    for (LegDto leg : selectedInboundFlight.getLegs()) {
                        price += leg.getCost();
                    }
                }
                
                confirmed = reservationDto.getReservationStatus() == ReservationStatus.BOOKED;

                String username = SessionBean.getUsername();
                UserDto user = userServiceFacade.find(username);
                
                if (confirmed) {
                    this.creditCardNumber = reservationDto.getCreditCardNumber();
                    this.creditCardExpiration = DateUtil.toYearMonthString(reservationDto.getCreditCardExpirationDate());
                }
                else {
                    this.creditCardNumber = user.getCreditCardNumber();
                    this.creditCardExpiration = DateUtil.toYearMonthString(user.getCreditCardExpiration());
                }
            }
        }
        
    }
    
    public DashboardModel getModel() {
        return model;
    }
 
    public void setModel(DashboardModel model) {
        this.model = model;
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
                        return "/admin/payment.xhtml";
                    }
                } catch (ParseException ex) {
                    showError("Invalid expiration date.");
                    return "/admin/payment.xhtml";
                }
            }
            
            reservationServiceFacade.book(reservationNumber, creditCardNumber, creditCardExpirationDate);
            setConfirmed(true);
            return "/admin/payment.xhtml";
        }
        return "/admin/bookings.xhtml?faces-redirect=true";
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
    
    public boolean isHasReturnFlight() {
        return selectedInboundFlight != null && selectedInboundFlight.getLegs().isEmpty() == false;
    }
}
