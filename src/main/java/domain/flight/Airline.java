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
    @NamedQuery(name = "Airline.findAll", query = "Select a from Airline a"),
    @NamedQuery(name = "Airline.findByAirlineCode",
            query = "Select a from Airline a where a.airlineCode = :airlineCode")})
public class Airline implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private AirlineCode airlineCode;
    @NotNull
    private String name;
    
    public Airline() {
    }
 
    
    /**
     * @param airlineCode airline code
     * @param name airline name
     * @throws IllegalArgumentException if the airline code or name is null
     */
    public Airline(AirlineCode airlineCode, String name) {
        Validate.notNull(airlineCode);
        Validate.notNull(name);

        this.airlineCode = airlineCode;
        this.name = name;
    }

    /**
     * @return airline code for this airline.
     */
    public AirlineCode getAirlineCode() {
        return airlineCode;
    }

    /**
     * @return Actual name of this location, e.g. "Stockholm".
     */
    public String getName() {
        return name;
    }

    /**
     * @param object to compare
     * @return Since this is an entiy this will be true iff UN locodes are
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
        if (!(object instanceof Airline)) {
            return false;
        }
        Airline other = (Airline) object;
        return sameIdentityAs(other);
    }

    public boolean sameIdentityAs(Airline other) {
        return this.airlineCode.sameValueAs(other.airlineCode);
    }

    /**
     * @return Hash code of airline code.
     */
    @Override
    public int hashCode() {
        return airlineCode.hashCode();
    }

    @Override
    public String toString() {
        return name + " [" + airlineCode + "]";
    }
}
