/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.reservation.web;

import application.util.DateUtil;
import domain.user.SearchRequest;
import interfaces.flight.facade.FlightServiceFacade;
import interfaces.flight.facade.dto.AirportDto;
import interfaces.user.facade.UserServiceFacade;
import interfaces.user.web.SessionBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ajadriano
 */
@Named
@ViewScoped
public class SearchBackingBean implements Serializable {
    
    private static final String RETURN = "Return";
    private static final String ONEWAY = "One-way";
    private static final String BUSINESS = "Business";
    private static final String ECONOMY = "Economy";
    
    @Inject
    private UserServiceFacade userServiceFacade;
    
    @Inject
    private FlightServiceFacade flightServiceFacade;
    
    private String fromAirport;
    private Date fromDate;
    private Date fromTime;
    private String toAirport;
    private Date toDate; 
    private Date toTime;
    private String flightType;
    private int passengerCount;
    private int stops;
    private String flightClass;
    
    private List<AirportDto> airports;
    
    public static Comparator<AirportDto> AirportComparator = new Comparator<AirportDto>() {
        @Override
	public int compare(AirportDto s1, AirportDto s2) {
	   return s1.getLabel().toUpperCase().compareTo(s2.getLabel().toUpperCase());
    }};
    
    @PostConstruct
    public void init() {
        airports = flightServiceFacade.getAirports();
        airports.sort(AirportComparator);
        setFlightType(RETURN);
        setFlightClass(ECONOMY);
        
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        setFromDate(c.getTime());
        c.add(Calendar.DATE, 1);
        setToDate(c.getTime());
        
        setFromTime(DateUtil.TimeToDate("00:00"));
        setToTime(DateUtil.TimeToDate("00:00"));
        
        stops = 0;
        passengerCount = 1;
    }
    
    public List<AirportDto> getAirports() {
        return airports;
    }
    
    public List<String> getFlightTypes() {
        List<String> flightTypes = new ArrayList<>(2);
        
        flightTypes.add(RETURN);
        flightTypes.add(ONEWAY);
        
        return flightTypes;
    }
    
    public List<String> getFlightClasses() {
        List<String> flightClasses = new ArrayList<>(2);
        
        flightClasses.add(BUSINESS);
        flightClasses.add(ECONOMY);
        
        return flightClasses;
    }
    /**
     * @return the fromAirport
     */
    public String getFromAirport() {
        return fromAirport;
    }

    /**
     * @param fromAirport the fromAirport to set
     */
    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }
    
    public String getFromDateString() {
        return DateUtil.combineFormat(fromDate, fromTime);
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the fromTime
     */
    public Date getFromTime() {
        return fromTime;
    }

    /**
     * @param fromTime the fromTime to set
     */
    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    /**
     * @return the toAirport
     */
    public String getToAirport() {
        return toAirport;
    }

    /**
     * @param toAirport the toAirport to set
     */
    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    /**
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }
    
    public String getToDateString() {
        return DateUtil.combineFormat(toDate, toTime);
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the toTime
     */
    public Date getToTime() {
        return toTime;
    }

    /**
     * @param toTime the toTime to set
     */
    public void setToTime(Date toTime) {
        this.toTime = toTime;
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
     * @return the stops
     */
    public int getStops() {
        return stops;
    }

    /**
     * @param stops the stops to set
     */
    public void setStops(int stops) {
        this.stops = stops;
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
    
    public String searchFlights() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        
        SearchRequest request = new SearchRequest(fromAirport,
                fromDate,
                fromTime,
                toAirport,
                toDate,
                toTime,
                flightType,
                passengerCount,
                stops,
                flightClass);
        
        String username = SessionBean.getUsername();
        if (username != null && username.isEmpty() == false)
        {
            userServiceFacade.saveRequest(username, request);
        }
        
        session.setAttribute("searchrequest", request);
        return "reservation";
    }
    
    public Date getDateToday() {
        return new Date();
    }
}
