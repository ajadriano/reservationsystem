/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.flight.facade.dto;

/**
 *
 * @author ajadriano
 */
public class AirportDto {
    private final String airportCode;
    private final String name;
    private final String location;
    
    public AirportDto (String airportCode, String name, String location) {
        this.airportCode = airportCode;
        this.name = name;
        this.location = location;
    }

    /**
     * @return the airportCode
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * @return the airportName
     */
    public String getAirportName() {
        return name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }
    
    public String getLabel() {
        return location + " - " + airportCode + " - " + name;
    }
}
