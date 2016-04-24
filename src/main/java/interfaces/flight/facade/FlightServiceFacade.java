/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.flight.facade;

import interfaces.flight.facade.dto.AirlineDto;
import interfaces.flight.facade.dto.AirportDto;
import interfaces.flight.facade.dto.FlightDto;
import interfaces.flight.facade.dto.InventoryDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface FlightServiceFacade {
    void AddNewFlight(FlightDto flight);
    void cancel(FlightDto flight);
    List<InventoryDto> getUnsoldSeats(Date fromDate, Date toDate);
    List<FlightDto> getFlights();
    List<AirportDto> getAirports();
    List<AirlineDto> getAirlines();
}
