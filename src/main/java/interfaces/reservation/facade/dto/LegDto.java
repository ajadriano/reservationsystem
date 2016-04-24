/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.reservation.facade.dto;

import domain.flight.FlightInstance;
import domain.reservation.FlightClass;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ajadriano
 */
public class LegDto {
    private static final SimpleDateFormat DATE_FORMAT
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    private final String airline;
    private final String flightNumber;
    private final String departureLocation;
    private final String departureAirport;
    private final Date departureDate;
    private final String arrivalLocation;
    private final String arrivalAirport;
    private final Date arrivalDate;
    private final FlightInstance flight;
    private double cost;
    
    public LegDto(FlightInstance flight, FlightClass flightClass) {
        this.flight = flight;
        this.airline = flight.getFlightNumber().getAirline().getName();
        this.flightNumber = flight.getFlightNumber().getAirlineCodeAndFlightNumber();
        this.departureLocation = flight.getDepartureLocation().getLocation();
        this.departureAirport = flight.getDepartureLocation().getName();
        this.departureDate = flight.getDepartureDate();
        this.arrivalLocation = flight.getArrivalLocation().getLocation();
        this.arrivalAirport = flight.getArrivalLocation().getName();
        this.arrivalDate = flight.getArrivalDate();
        if (flightClass == FlightClass.BUSINESS) {
            this.cost = flight.getBusinessClassCost();
        }
        else if (flightClass == FlightClass.ECONOMY) {
            this.cost = flight.getEconomyClassCost();
        }
    }
    
    public LegDto(String airline,
            String flightNumber,
            String departureLocation,
            String departureAirport,
            Date departureDate,
            String arrivalLocation,
            String arrivalAirport,
            Date arrivalDate,
            double cost) {
        this.flight = null;
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.departureLocation = departureLocation;
        this.departureAirport = departureAirport;
        this.departureDate = departureDate;
        this.arrivalLocation = arrivalLocation;
        this.arrivalAirport = arrivalAirport;
        this.arrivalDate = arrivalDate;
        this.cost = cost;
    }
    
    /**
     * @return the airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @return the departureLocation
     */
    public String getDepartureLocation() {
        return departureLocation;
    }

    /**
     * @return the departureDate
     */
    public String getDepartureDate() {
        return DATE_FORMAT.format(departureDate);
    }

    /**
     * @return the arrivalLocation
     */
    public String getArrivalLocation() {
        return arrivalLocation;
    }

    /**
     * @return the arrivalDate
     */
    public String getArrivalDate() {
        return DATE_FORMAT.format(arrivalDate);
    }

    /**
     * @return the departureAirport
     */
    public String getDepartureAirport() {
        return departureAirport;
    }

    /**
     * @return the arrivalAirport
     */
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     * @return the flight
     */
    public FlightInstance getFlight() {
        return flight;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(double cost) {
        this.cost = cost;
    }
}
