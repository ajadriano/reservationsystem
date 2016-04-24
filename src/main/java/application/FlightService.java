/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.flight.AirlineCode;
import domain.flight.Airport;
import domain.flight.AirportCode;
import domain.flight.FlightInventory;
import domain.flight.FlightNumber;
import domain.flight.Itinerary;
import domain.reservation.FlightClass;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface FlightService {
    
    void AddNewFlight(AirlineCode airlineCode,
            String flightNumber,
            AirportCode departureAirportCode,
            Date departureTime,
            DayOfWeek departureDay,
            AirportCode arrivalAirportCode,
            Date arrivalTime,
            DayOfWeek arrivalDay,
            double businessClassCost,
            double economyClassCost);
    
    void delete(FlightNumber flightNumber);
    
    List<Itinerary> getItineraryOptions(Airport departureLocation,
            Airport arrivalLocation,
            Date departureDate,
            FlightClass flightClass,
            int seatCount,
            int stops);
    
    List<FlightInventory> getInventory(Date fromDate, Date toDate);
    
}
