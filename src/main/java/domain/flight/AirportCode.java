/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.flight;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.Validate;

/**
 * Airport code.
 */
@Embeddable
public class AirportCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    // Airport code is exactly two letters.
    @Pattern(regexp = "[A-Z]{3}")
    private String airportCode;
    private static final java.util.regex.Pattern VALID_PATTERN
            = java.util.regex.Pattern.compile("[A-Z]{3}");

    public AirportCode() {
        // Nothing to initialize.
    }

    /**
     * @param airportCode Location string.
     */
    public AirportCode(String airportCode) {
        Validate.notNull(airportCode,
                "Airport code may not be null");
        Validate.isTrue(VALID_PATTERN.matcher(airportCode).matches(),
                airportCode
                + " is not a valid airport code (does not match pattern)");

        this.airportCode = airportCode.toUpperCase();
    }

    /**
     * @return airport code, always upper case.
     */
    public String getCodeString() {
        return airportCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AirportCode other = (AirportCode) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return airportCode.hashCode();
    }

    boolean sameValueAs(AirportCode other) {
        return other != null && this.airportCode.equals(other.airportCode);
    }

    @Override
    public String toString() {
        return getCodeString();
    }
}
