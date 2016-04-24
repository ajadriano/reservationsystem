/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.flight.facade.internal.assembler;

import domain.flight.Flight;
import interfaces.flight.facade.dto.FlightDto;

/**
 *
 * @author ajadriano
 */
public class FlightRouteDtoAssembler {
    public FlightDto toDto(Flight flight) {
        return new FlightDto(flight.getFlightNumber().getAirline().getAirlineCode(), 
                flight.getFlightNumber().getFlightNumber(), 
                flight.getDepartureLocation().getAirportCode(), 
                flight.getDepartureDay(), 
                flight.getDepartureTime(), 
                flight.getArrivalLocation().getAirportCode(), 
                flight.getArrivalDay(), 
                flight.getArrivalTime(), 
                flight.getBusinessClassCost(), 
                flight.getEconomyClassCost());
    }
}
