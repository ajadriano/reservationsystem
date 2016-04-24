/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.flight.facade.dto;

import domain.flight.AirlineCode;
import domain.flight.AirportCode;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

/**
 * DTO for registering and routing a cargo.
 */
public class FlightDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("HH:mm");
    
    private AirlineCode airlineCode;
    private String flightNumber;
    private AirportCode departureAirportCode;
    private DayOfWeek departureDay;
    private Date departureTime;
    private AirportCode arrivalAirportCode;
    private DayOfWeek arrivalDay;
    private Date arrivalTime;
    private double businessClassCost;
    private double economyClassCost;
    
    public FlightDto() {
        departureDay = DayOfWeek.MONDAY;
        departureTime = new Date();
        arrivalDay = DayOfWeek.MONDAY;
        arrivalTime = new Date();
    }

    public FlightDto(
            AirlineCode airlineCode,
            String flightNumber,
            AirportCode departureAirportCode,
            DayOfWeek departureDay,
            Date departureTime,
            AirportCode arrivalAirportCode,
            DayOfWeek arrivalDay,
            Date arrivalTime,
            double businessClassCost,
            double economyClassCost) {
        this.airlineCode = airlineCode;
        this.flightNumber = flightNumber;
        this.departureAirportCode = departureAirportCode;
        this.departureDay = departureDay;
        this.departureTime = departureTime;
        this.arrivalAirportCode = arrivalAirportCode;
        this.arrivalDay = arrivalDay;
        this.arrivalTime = arrivalTime;
        this.businessClassCost = businessClassCost;
        this.economyClassCost = economyClassCost;
    }
    
    public String getAirlineCodeString() {
        if (airlineCode != null)
        {
            return airlineCode.getCodeString();
        }
        
        return null;
    }

    public AirlineCode getAirlineCode() {
        return airlineCode;
    }
    
    public String getCompleteFlightNumber() {
        return getAirlineCodeString() + flightNumber;
    }
    
    public String getFlightNumber() {
        return flightNumber;
    }
    
    public String getDepartureAirportCodeString() {
        
        if (departureAirportCode != null)
        {
            return departureAirportCode.getCodeString();
        }
        
        return null;
    }
    
    public AirportCode getDepartureAirportCode() {
        return departureAirportCode;
    }
    
    public Date getDepartureTime() {
        return departureTime;
    }
    
    public String getDepartureDayFormatted() {
        return getDepartureDay().getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }
    
    public String getDepartureFullTime() {
        return String.format("%s %s", 
                getDepartureDayFormatted(), 
                DATE_FORMAT.format(departureTime));
    }
    
    public String getArrivalAirportCodeString() {
        if (arrivalAirportCode != null)
        {
            return arrivalAirportCode.getCodeString();
        }
        
        return null;
    }
    
    public AirportCode getArrivalAirportCode() {
        return arrivalAirportCode;
    }
    
    public Date getArrivalTime() {
        return arrivalTime;
    }
    
    public String getArrivalDayFormatted() {
        return getArrivalDay().getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }
    
    public String getArrivalFullTime() {
        return String.format("%s %s", 
                getArrivalDayFormatted(), 
                DATE_FORMAT.format(arrivalTime));
    }
    
    public double getBusinessClassCost() {
        return businessClassCost;
    }
    
    public double getEconomyClassCost() {
        return economyClassCost;
    }
    
    public String getBusinessClassCostString() {
        return String.valueOf(businessClassCost);
    }
    
    public String getEconomyClassCostString() {
        return String.valueOf(economyClassCost);
    }

    /**
     * @param airlineCode the airlineCode to set
     */
    public void setAirlineCode(AirlineCode airlineCode) {
        this.airlineCode = airlineCode;
    }
    
    /**
     * @param airlineCode the airlineCode to set
     */
    public void setAirlineCode(String airlineCode) {
        this.airlineCode = new AirlineCode(airlineCode);
    }

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @param departureAirportCode the departureLocation to set
     */
    public void setDepartureAirportCode(AirportCode departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }
    
    /**
     * @param departureAirportCode the departureLocation to set
     */
    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = new AirportCode(departureAirportCode);
    }

    /**
     * @param departureDay the departureDay to set
     */
    public void setDepartureDay(DayOfWeek departureDay) {
        this.departureDay = departureDay;
    }
    
    /**
     * @param departureDay the departureDay to set
     */
    public void setDepartureDay(String departureDay) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getDisplayName(TextStyle.SHORT, Locale.getDefault()).equalsIgnoreCase(departureDay)) {
                this.departureDay = day;
                break;
            }
        }
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }
    
    /**
     * @param arrivalAirportCode the arrivalLocation to set
     */
    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = new AirportCode(arrivalAirportCode);
    }

    /**
     * @param arrivalAirportCode the arrivalLocation to set
     */
    public void setArrivalAirportCode(AirportCode arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    /**
     * @param arrivalDay the arrivalDay to set
     */
    public void setArrivalDay(DayOfWeek arrivalDay) {
        this.arrivalDay = arrivalDay;
    }
    
    /**
     * @param arrivalDay the departureDay to set
     */
    public void setArrivalDay(String arrivalDay) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getDisplayName(TextStyle.SHORT, Locale.getDefault()).equalsIgnoreCase(arrivalDay)) {
                this.arrivalDay = day;
                break;
            }
        }
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @param businessClassCost the businessClassCost to set
     */
    public void setBusinessClassCost(double businessClassCost) {
        this.businessClassCost = businessClassCost;
    }

    /**
     * @param economyClassCost the economyClassCost to set
     */
    public void setEconomyClassCost(double economyClassCost) {
        this.economyClassCost = economyClassCost;
    }

    /**
     * @return the departureDay
     */
    public DayOfWeek getDepartureDay() {
        return departureDay;
    }

    /**
     * @return the arrivalDay
     */
    public DayOfWeek getArrivalDay() {
        return arrivalDay;
    }
}
