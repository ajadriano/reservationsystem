/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.reservation;

import domain.flight.Airport;
import domain.flight.FlightNumber;
import java.io.Serializable;
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
    @NamedQuery(name = "FlightLeg.findByFlightNumber",
            query = "Select f from FlightLeg f where f.flightNumber = :flightNumber"),
    @NamedQuery(name = "FlightLeg.findByFlightNumberAndUser",
            query = "Select f from FlightLeg f where f.flightNumber = :flightNumber"
                    + " and f.username = :username"),
    @NamedQuery(name = "FlightLeg.findByFlightNumberAndDate",
            query = "Select f from FlightLeg f where f.flightNumber = :flightNumber and "
                    + "f.departureDate = :departureDate")})
public class FlightLeg implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "reservation_number")
    @NotNull
    private String reservationNumber;
    
    @Embedded
    private FlightNumber flightNumber;
    
    @ManyToOne
    @JoinColumn(name = "departure_location_id")
    @NotNull
    private Airport departureLocation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "departure_date")
    @NotNull
    private Date departureDate;
    
    @ManyToOne
    @JoinColumn(name = "arrival_location_id")
    @NotNull
    private Airport arrivalLocation;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "arrival_date")
    @NotNull
    private Date arrivalDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "flight_class")
    @NotNull
    private FlightClass flightClass;
    
    @Column(name = "seat_count")
    private int seatCount;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private ReservationStatus status;
    
    @Column(name = "cost")
    private double cost;
    
    @Column(name = "username")
    @NotNull
    private String username;
    
    public FlightLeg() {
    }
    
    public FlightLeg(
            String reservationNumber,
            FlightNumber flightNumber,
            Airport departureLocation,
            Date departureDate,
            Airport arrivalLocation,
            Date arrivalDate,
            FlightClass flightClass,
            int seatCount,
            ReservationStatus status,
            double cost,
            String username)
    {
        Validate.notNull(reservationNumber, "Reservation number is required");
        Validate.notNull(flightNumber, "Flight number is required");
        Validate.notNull(departureLocation, "Departure location is required");
        Validate.notNull(departureDate, "Departure date is required");
        Validate.notNull(arrivalLocation, "Arrival location is required");
        Validate.notNull(arrivalDate, "Arrival date is required");
        Validate.notNull(flightClass, "Flight class is required");
        Validate.notNull(status, "Reservation status is required");
        
        this.reservationNumber = reservationNumber;
        this.flightNumber = flightNumber;
        this.departureLocation = departureLocation;
        this.departureDate = departureDate;
        this.arrivalLocation = arrivalLocation;
        this.arrivalDate = arrivalDate;
        this.flightClass = flightClass;
        this.seatCount = seatCount;
        this.status = status;
        this.username = username;
        this.cost = cost;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FlightLeg other = (FlightLeg) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {   
        return getReservationNumber().hashCode() ^ getFlightNumber().hashCode();
    }

    boolean sameValueAs(FlightLeg other) {
        return other != null && 
                this.getReservationNumber().equals(other.getReservationNumber()) &&
                this.getFlightNumber().sameValueAs(other.getFlightNumber());
    }

    @Override
    public String toString() {
        return getReservationNumber().toString();
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
     * @return the flightClass
     */
    public FlightClass getFlightClass() {
        return flightClass;
    }

    /**
     * @return the seatCount
     */
    public int getSeatCount() {
        return seatCount;
    }

    /**
     * @return the reservationNumber
     */
    public String getReservationNumber() {
        return reservationNumber;
    }

    /**
     * @return the status
     */
    public ReservationStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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
