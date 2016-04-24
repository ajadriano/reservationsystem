/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.flight.web;

import interfaces.flight.facade.FlightServiceFacade;
import interfaces.flight.facade.dto.AirlineDto;
import interfaces.flight.facade.dto.AirportDto;
import interfaces.flight.facade.dto.FlightDto;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ajadriano
 */
@Named
@ViewScoped
public class FlightBackingBean implements Serializable {
    
    private FlightDto flightDto;
    
    private List<AirlineDto> airlines;
    private List<AirportDto> airports;
    
    public static Comparator<AirportDto> AirportComparator = new Comparator<AirportDto>() {
        @Override
	public int compare(AirportDto s1, AirportDto s2) {
	   return s1.getLabel().toUpperCase().compareTo(s2.getLabel().toUpperCase());
    }};
    
    public static Comparator<AirlineDto> AirlineComparator = new Comparator<AirlineDto>() {
        @Override
	public int compare(AirlineDto s1, AirlineDto s2) {
	   return s1.getName().toUpperCase().compareTo(s2.getName().toUpperCase());
    }};
    
    private boolean valid = true;
    
    @Inject
    private FlightServiceFacade flightServiceFacade;
    
    @PostConstruct
    public void init() {
        flightDto = new FlightDto();
        airlines = flightServiceFacade.getAirlines();
        airlines.sort(AirlineComparator);
        airports = flightServiceFacade.getAirports();
        airports.sort(AirportComparator);
    }

    /**
     * @return the airlineCode
     */
    public String getAirlineCode() {
        return flightDto.getAirlineCodeString();
    }

    /**
     * @param airlineCode the airlineCode to set
     */
    public void setAirlineCode(String airlineCode) {
        flightDto.setAirlineCode(airlineCode);
    }

    /**
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightDto.getFlightNumber();
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(String flightNumber) {
        flightDto.setFlightNumber(flightNumber);
    }

    /**
     * @return the departureAirportCode
     */
    public String getDepartureAirportCode() {
        return flightDto.getDepartureAirportCodeString();
    }

    /**
     * @param departureAirportCode the departureAirportCode to set
     */
    public void setDepartureAirportCode(String departureAirportCode) {
        flightDto.setDepartureAirportCode(departureAirportCode);
    }

    /**
     * @return the departureTime
     */
    public Date getDepartureTime() {
        return flightDto.getDepartureTime();
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(Date departureTime) {
        flightDto.setDepartureTime(departureTime);
    }

    /**
     * @return the departureDay
     */
    public String getDepartureDay() {
        return flightDto.getDepartureDayFormatted();
    }

    /**
     * @param departureDay the departureDay to set
     */
    public void setDepartureDay(String departureDay) {
        flightDto.setDepartureDay(departureDay);
    }

    /**
     * @return the arrivalAirportCode
     */
    public String getArrivalAirportCode() {
        return flightDto.getArrivalAirportCodeString();
    }

    /**
     * @param arrivalAirportCode the arrivalAirportCode to set
     */
    public void setArrivalAirportCode(String arrivalAirportCode) {
        flightDto.setArrivalAirportCode(arrivalAirportCode);
    }

    /**
     * @return the arrivalTime
     */
    public Date getArrivalTime() {
        return flightDto.getArrivalTime();
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(Date arrivalTime) {
        flightDto.setArrivalTime(arrivalTime);
    }

    /**
     * @return the arrivalDay
     */
    public String getArrivalDay() {
        return flightDto.getArrivalDayFormatted();
    }

    /**
     * @param arrivalDay the arrivalDay to set
     */
    public void setArrivalDay(String arrivalDay) {
        flightDto.setArrivalDay(arrivalDay);
    }

    /**
     * @return the businessClassCost
     */
    public double getBusinessClassCost() {
        return flightDto.getBusinessClassCost();
    }

    /**
     * @param businessClassCost the businessClassCost to set
     */
    public void setBusinessClassCost(double businessClassCost) {
        flightDto.setBusinessClassCost(businessClassCost);
    }

    /**
     * @return the economyClassCost
     */
    public double getEconomyClassCost() {
        return flightDto.getEconomyClassCost();
    }

    /**
     * @param economyClassCost the economyClassCost to set
     */
    public void setEconomyClassCost(double economyClassCost) {
        flightDto.setEconomyClassCost(economyClassCost);
    }

    /**
     * @return the airlines
     */
    public List<AirlineDto> getAirlines() {
        return airlines;
    }

    /**
     * @return the airports
     */
    public List<AirportDto> getAirports() {
        return airports;
    }
    
    public List<String> getDaysOfWeek() {
        List<String> days = new ArrayList<>(DayOfWeek.values().length);
        
        for (DayOfWeek day : DayOfWeek.values()) {
            days.add(day.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        }
        
        return days;
    }
    
    public boolean isValid() {
        return valid;
    }
    
    public String addFlight() {
        
        if (!flightDto.getDepartureAirportCode().equals(flightDto.getArrivalAirportCode())) {
            flightServiceFacade.AddNewFlight(flightDto);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage("Origin and destination cannot be the same.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, message);
            return "/admin/addflight.xhtml";
        }
        
        return "/admin/flights.xhtml?faces-redirect=true";
        
    }
}
