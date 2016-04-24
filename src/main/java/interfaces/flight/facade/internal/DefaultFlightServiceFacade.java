/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.flight.facade.internal;

import application.FlightService;
import application.ReservationService;
import application.util.DateUtil;
import domain.flight.Airline;
import domain.flight.AirlineRepository;
import domain.flight.Airport;
import domain.flight.AirportRepository;
import domain.flight.Flight;
import domain.flight.FlightInventory;
import domain.flight.FlightNumber;
import domain.flight.FlightRepository;
import interfaces.flight.facade.FlightServiceFacade;
import interfaces.flight.facade.dto.AirlineDto;
import interfaces.flight.facade.dto.AirportDto;
import interfaces.flight.facade.dto.FlightDto;
import interfaces.flight.facade.dto.InventoryDto;
import interfaces.flight.facade.internal.assembler.FlightRouteDtoAssembler;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author ajadriano
 */
@ApplicationScoped
public class DefaultFlightServiceFacade implements FlightServiceFacade {

    @Inject
    private FlightService flightService;
    
    @Inject
    private FlightRepository flightRepository;
    
    @Inject
    private AirportRepository airportRepository;
    
    @Inject
    private AirlineRepository airlineRepository;
    
    @Inject
    private ReservationService reservationService;
    
    @Override
    public List<FlightDto> getFlights() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightDto> flightDtos = new ArrayList<>(flights.size());

        FlightRouteDtoAssembler assembler = new FlightRouteDtoAssembler();

        for (Flight flight : flights) {
            flightDtos.add(assembler.toDto(flight));
        }

        return flightDtos;
        
        
        /*List<FlightRoute> list;
        list = new ArrayList<>(2);
        list.add(new FlightRoute("PR", "222", "LHR", "M", new Date(), "MNL", "T", new Date(), 30.0, 30.0));
        list.add(new FlightRoute("PR", "333", "MNL", "T", new Date(), "LHR", "T", new Date(), 60.0, 60.0));
        return list;*/
    }

    @Override
    public List<AirportDto> getAirports() {
        List<Airport> airports = airportRepository.findAll();
        
        List<AirportDto> airportDtos = new ArrayList<>(airports.size());

        for (Airport airport : airports) {
            airportDtos.add(new AirportDto(
                    airport.getAirportCode().getCodeString(),
                    airport.getName(),
                    airport.getLocation()
                ));
        }

        return airportDtos;
    }

    @Override
    public List<AirlineDto> getAirlines() {
        List<Airline> airlines = airlineRepository.findAll();
        
        List<AirlineDto> airlineDtos = new ArrayList<>(airlines.size());

        for (Airline airline : airlines) {
            airlineDtos.add(new AirlineDto(
                    airline.getAirlineCode().getCodeString(),
                    airline.getName()
                ));
        }

        return airlineDtos;
    }
    
    @Override
    public void AddNewFlight(FlightDto flight) {
        flightService.AddNewFlight(flight.getAirlineCode(), 
                flight.getFlightNumber(), 
                flight.getDepartureAirportCode(), 
                DateUtil.getTimeOnly(flight.getDepartureTime()), 
                flight.getDepartureDay(), 
                flight.getArrivalAirportCode(), 
                DateUtil.getTimeOnly(flight.getArrivalTime()), 
                flight.getArrivalDay(), 
                flight.getBusinessClassCost(), 
                flight.getEconomyClassCost());
    }
    
    @Override
    public List<InventoryDto> getUnsoldSeats(Date fromDate, Date toDate) {
        List<InventoryDto> list = new ArrayList<>();
        
        List<FlightInventory> inventoryList = flightService.getInventory(fromDate, toDate);             
        
        for (FlightInventory inventory : inventoryList) {
            list.add(new InventoryDto(inventory.getDate(), 
                    inventory.getFlightNumber(), 
                    inventory.getBusinessSeatCount(),
                    inventory.getEconomySeatCount()));
        }
        
        return list;
    }
    
    public void cancel(FlightDto flight) {
        Airline airline = airlineRepository.find(flight.getAirlineCode());
        FlightNumber flightNumber = new FlightNumber(airline, flight.getFlightNumber());
        reservationService.cancel(flightNumber);
        flightService.delete(flightNumber);
    }
}
