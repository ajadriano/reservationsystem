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
 * Airline code.
 */
@Embeddable
public class AirlineCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull
    // Airline code is exactly two letters.
    @Pattern(regexp = "[0-9A-Z]{2}")
    private String airlineCode;
    private static final java.util.regex.Pattern VALID_PATTERN
            = java.util.regex.Pattern.compile("[0-9A-Z]{2}");

    public AirlineCode() {
    }

    /**
     * @param airlineCode Location string.
     */
    public AirlineCode(String airlineCode) {
        Validate.notNull(airlineCode,
                "Airline code may not be null");
        Validate.isTrue(VALID_PATTERN.matcher(airlineCode).matches(),
                airlineCode
                + " is not a valid airline code (does not match pattern)");

        this.airlineCode = airlineCode.toUpperCase();
    }

    /**
     * @return airline code, always upper case.
     */
    public String getCodeString() {
        return airlineCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AirlineCode other = (AirlineCode) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return airlineCode.hashCode();
    }

    boolean sameValueAs(AirlineCode other) {
        return other != null && this.airlineCode.equals(other.airlineCode);
    }

    @Override
    public String toString() {
        return getCodeString();
    }
}