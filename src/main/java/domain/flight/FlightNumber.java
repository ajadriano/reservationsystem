/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.flight;

import java.io.Serializable;
import javax.persistence.Column;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.Validate;

/**
 * Flight number.
 */
@Embeddable
public class FlightNumber implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name = "airline_id", updatable = false)
    @NotNull
    private Airline airline;
    
    @NotNull
    // Flight number is exactly three digits.
    @Pattern(regexp = "[0-9]{3}")
    @Column(name = "flight_number")
    private String flightNumber;
    private static final java.util.regex.Pattern VALID_PATTERN
            = java.util.regex.Pattern.compile("[0-9]{3}");

    public FlightNumber() {
        // Nothing to initialize.
    }

    /**
     * @param flightNumber flight number.
     */
    public FlightNumber(Airline airline, String flightNumber) {
        Validate.notNull(airline,
                "airline may not be null");
        Validate.notNull(flightNumber,
                "flight number may not be null");
        Validate.isTrue(VALID_PATTERN.matcher(flightNumber).matches(),
                flightNumber
                + " is not a valid flight number (does not match pattern)");

        this.airline = airline;
        this.flightNumber = flightNumber.toUpperCase();
    }

    /**
     * @return flight number.
     */
    public String getAirlineCodeAndFlightNumber() {
        return getAirline().getAirlineCode().getCodeString().concat(getFlightNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FlightNumber other = (FlightNumber) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return flightNumber.hashCode();
    }

    public boolean sameValueAs(FlightNumber other) {
        return other != null && 
                this.getAirline().equals(other.getAirline()) &&
                this.getFlightNumber().equals(other.getFlightNumber());
    }

    @Override
    public String toString() {
        return getAirlineCodeAndFlightNumber();
    }

    /**
     * @return the airline
     */
    public Airline getAirline() {
        return airline;
    }

    /**
     * @return the flightNumber
     */
    public String getFlightNumber() {
        return flightNumber;
    }
}
