/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.flight;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.Validate;

/**
 *
 * @author ajadriano
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Flight.findAll",
            query = "Select f from Flight f"),
    @NamedQuery(name = "Flight.findByFlightNumber",
            query = "Select f from Flight f where f.flightNumber = :flightNumber"),
    @NamedQuery(name = "Flight.getAllFlightNumber",
            query = "Select f.flightNumber from Flight f"),
    @NamedQuery(name = "Flight.getAvailableFlights",
            query = "Select f from Flight f where"
                    + " f.departureLocation = :departureLocation and"
                    + " f.departureTime >= :departureTime and"
                    + " f.departureDay = :departureDay"),
    @NamedQuery(name = "Flight.getAvailableFlightsWithArrival",
            query = "Select f from Flight f where"
                    + " f.departureLocation = :departureLocation and"
                    + " f.arrivalLocation = :arrivalLocation and"
                    + " f.departureTime >= :departureTime and"
                    + " f.departureDay = :departureDay"),
    @NamedQuery(name = "Flight.getFlightsByDay",
            query = "Select f from Flight f where"
                    + " f.departureDay = :departureDay")})
public class Flight implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private FlightNumber flightNumber;
    
    @ManyToOne
    @JoinColumn(name = "departure_location_id")
    @NotNull
    private Airport departureLocation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "departure_time")
    @NotNull
    private Date departureTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "departure_day")
    @NotNull
    private DayOfWeek departureDay;
    
    @ManyToOne
    @JoinColumn(name = "arrival_location_id")
    @NotNull
    private Airport arrivalLocation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arrival_time")
    @NotNull
    private Date arrivalTime;
    @Enumerated(EnumType.STRING)
    @Column(name = "arrival_day")
    @NotNull
    private DayOfWeek arrivalDay;
    
    @Column(name = "business_class_cost")
    private double businessClassCost;
    @Column(name = "economy_class_cost")
    private double economyClassCost;
    
    public Flight() {
        
    }
    
    public Flight(FlightNumber flightNumber,
            Airport departureLocation,
            Date departureTime,
            DayOfWeek departureDay,
            Airport arrivalLocation,
            Date arrivalTime,
            DayOfWeek arrivalDay,
            double businessClassCost,
            double economyClassCost)
    {
        Validate.notNull(flightNumber, "Flight number is required");
        Validate.notNull(departureLocation, "Departure location is required");
        Validate.notNull(departureTime, "Departure time is required");
        Validate.notNull(departureDay, "Departure day is required");
        Validate.notNull(arrivalLocation, "Arrival location is required");
        Validate.notNull(arrivalTime, "Arrival time is required");
        Validate.notNull(arrivalDay, "Arrival day is required");
        
        this.flightNumber = flightNumber;
        this.departureLocation = departureLocation;
        this.departureTime = departureTime;
        this.departureDay = departureDay;
        this.arrivalLocation = arrivalLocation;
        this.arrivalTime = arrivalTime;
        this.arrivalDay = arrivalDay;
        this.businessClassCost = businessClassCost;
        this.economyClassCost = economyClassCost;
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
     * @return the departureTime
     */
    public Date getDepartureTime() {
        return departureTime;
    }

    /**
     * @return the departureDay
     */
    public DayOfWeek getDepartureDay() {
        return departureDay;
    }

    /**
     * @return the arrivalLocation
     */
    public Airport getArrivalLocation() {
        return arrivalLocation;
    }

    /**
     * @return the arrivalTime
     */
    public Date getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @return the arrivalDay
     */
    public DayOfWeek getArrivalDay() {
        return arrivalDay;
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

    /**
     * @param flightNumber the flightNumber to set
     */
    public void setFlightNumber(FlightNumber flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * @param departureLocation the departureLocation to set
     */
    public void setDepartureLocation(Airport departureLocation) {
        this.departureLocation = departureLocation;
    }

    /**
     * @param departureTime the departureTime to set
     */
    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * @param departureDay the departureDay to set
     */
    public void setDepartureDay(DayOfWeek departureDay) {
        this.departureDay = departureDay;
    }

    /**
     * @param arrivalLocation the arrivalLocation to set
     */
    public void setArrivalLocation(Airport arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @param arrivalDay the arrivalDay to set
     */
    /*public void setArrivalDay(DayOfWeek arrivalDay) {
        this.arrivalDay = arrivalDay;
    }*/

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
     * @param object to compare
     * @return True if they have the same identity
     * @see #sameIdentityAs(Cargo)
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Flight other = (Flight) object;
        return sameIdentityAs(other);
    }

    private boolean sameIdentityAs(Flight other) {
        return other != null && flightNumber.sameValueAs(other.flightNumber);
    }
    
    @Override
    public int hashCode() {
        return flightNumber.hashCode();
    }

    @Override
    public String toString() {
        return flightNumber.toString();
    }
}
