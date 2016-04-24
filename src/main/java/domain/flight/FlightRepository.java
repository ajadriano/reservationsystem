/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.flight;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface FlightRepository {

    /**
     *
     * @return
     */
    List<Flight> findAll();
    
    void store(Flight flight);
    
    void delete(FlightNumber flightNumber);
    
    List<Flight> getFlights(Airport departureLocation,
            Date departureTime,
            DayOfWeek departureDay);
    
    List<Flight> getFlights(Airport departureLocation,
            Airport arrivalLocation,
            Date departureTime,
            DayOfWeek departureDay);
    
    List<Flight> getFlights(DayOfWeek departureDay);
}
