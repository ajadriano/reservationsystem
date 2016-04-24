/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.flight;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.Validate;

/**
 *
 * @author ajadriano
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Airport.findAll", query = "Select a from Airport a"),
    @NamedQuery(name = "Airport.findByAirportCode",
            query = "Select a from Airport a where a.airportCode = :airportCode")})
public class Airport implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private AirportCode airportCode;
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String country;
    
    public Airport() {
        // Nothing to do.
    }
 
    
    /**
     * @param airportCode airport code
     * @param name airport name
     * @throws IllegalArgumentException if the airline code or name is null
     */
    public Airport(AirportCode airportCode, String name, String city, String country) {
        Validate.notNull(airportCode);
        Validate.notNull(name);
        Validate.notNull(city);
        Validate.notNull(country);

        this.airportCode = airportCode;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    /**
     * @return airline code for this airline.
     */
    public AirportCode getAirportCode() {
        return airportCode;
    }

    /**
     * @return Airport name.
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return Location.
     */
    public String getLocation() {
        return city + ", " + country;
    }

    /**
     * @param object to compare
     * @return Since this is an entity this will be true if codes are
     * equal.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!(object instanceof Airport)) {
            return false;
        }
        Airport other = (Airport) object;
        return sameIdentityAs(other);
    }

    public boolean sameIdentityAs(Airport other) {
        return this.airportCode.sameValueAs(other.airportCode);
    }

    /**
     * @return Hash code of airline code.
     */
    @Override
    public int hashCode() {
        return airportCode.hashCode();
    }

    @Override
    public String toString() {
        return name + " [" + airportCode + "]";
    }
}
