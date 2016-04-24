/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.flight;

import application.util.DateUtil;
import java.util.Date;

/**
 *
 * @author ajadriano
 */
public class FlightInstance {
    private final FlightNumber flightNumber;

    private final Airport departureLocation;
    private final Date departureDate;
    
    private final Airport arrivalLocation;
    private final Date arrivalDate;
    
    private final double businessClassCost;
    private final double economyClassCost;
    
    public FlightInstance(Flight flight, Date departureDate) {
        this.flightNumber = flight.getFlightNumber();
        this.departureLocation = flight.getDepartureLocation();
        this.departureDate = DateUtil.combine(departureDate, flight.getDepartureTime());
        this.arrivalLocation = flight.getArrivalLocation();
        this.arrivalDate = DateUtil.combine(
                DateUtil.getNewDate(departureDate, flight.getArrivalDay()), 
                flight.getArrivalTime());
        this.businessClassCost = flight.getBusinessClassCost();
        this.economyClassCost = flight.getEconomyClassCost();
    }

    /**
     * @return the flightNumber
     */
    public FlightNumber getFlightNumber() {
        return flightNumber;
    }

    /**
     * @return the departureLocation
     */
    public Airport getDepartureLocation() {
        return departureLocation;
    }

    /**
     * @return the departureDate
     */
    public Date getDepartureDate() {
        return departureDate;
    }

    /**
     * @return the arrivalLocation
     */
    public Airport getArrivalLocation() {
        return arrivalLocation;
    }

    /**
     * @return the arrivalDate
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * @return the businessClassCost
     */
    public double getBusinessClassCost() {
        return businessClassCost;
    }

    /**
     * @return the economyClassCost
     */
    public double getEconomyClassCost() {
        return economyClassCost;
    }
}
