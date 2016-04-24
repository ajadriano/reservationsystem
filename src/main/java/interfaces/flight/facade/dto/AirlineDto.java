/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.flight.facade.dto;

public class AirlineDto {
    private final String airlineCode;
    private final String name;
    
    public AirlineDto (String airlineCode, String name) {
        this.airlineCode = airlineCode;
        this.name = name;
    }

    /**
     * @return the airlineCode
     */
    public String getAirlineCode() {
        return airlineCode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return airlineCode + " - " + name;
    }
}
