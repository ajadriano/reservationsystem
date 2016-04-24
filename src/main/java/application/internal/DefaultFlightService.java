/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.internal;

import application.FlightService;
import application.util.DateUtil;
import domain.flight.Airline;
import domain.flight.AirlineCode;
import domain.flight.AirlineRepository;
import domain.flight.Airport;
import domain.flight.AirportCode;
import domain.flight.AirportRepository;
import domain.flight.Flight;
import domain.flight.FlightInstance;
import domain.flight.FlightInventory;
import domain.flight.FlightNumber;
import domain.flight.FlightRepository;
import domain.flight.Itinerary;
import domain.reservation.FlightClass;
import domain.reservation.FlightLeg;
import domain.reservation.ReservationRepository;
import domain.reservation.ReservationStatus;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ajadriano
 */
@Stateless
public class DefaultFlightService implements FlightService {
    
    @Inject
    private AirlineRepository airlineRepository;
    
    @Inject
    private AirportRepository airportRepository;
    
    @Inject
    private FlightRepository flightRepository;
    
    @Inject
    private ReservationRepository reservationRepository;
    
    // TODO See if the logger can be injected.
    private static final Logger logger = Logger.getLogger(
            DefaultFlightService.class.getName());

    @Override
    public void AddNewFlight(AirlineCode airlineCode, 
            String flightNumber, 
            AirportCode departureAirportCode, 
            Date departureTime, 
            DayOfWeek departureDay, 
            AirportCode arrivalAirportCode, 
            Date arrivalTime, 
            DayOfWeek arrivalDay, 
            double businessClassCost, 
            double economyClassCost) {
        
        Airport departureAirport = airportRepository.find(departureAirportCode);
        Airport arrivalAirport = airportRepository.find(arrivalAirportCode);
        Airline airline = airlineRepository.find(airlineCode);
        
        Flight flight = new Flight(
            new FlightNumber(airline, flightNumber),
            departureAirport,
            departureTime,
            departureDay,
            arrivalAirport,
            arrivalTime,
            arrivalDay,     
            businessClassCost, 
            economyClassCost);
        
        flightRepository.store(flight);
        logger.log(Level.INFO, "Added new flight with flight number {0}",
                flight.getFlightNumber().toString());
    }
    
    @Override
    public void delete(FlightNumber flightNumber) {
        flightRepository.delete(flightNumber);
    }
    
    @Override
    public List<Itinerary> getItineraryOptions(Airport departureLocation,
            Airport arrivalLocation,
            Date departureDate,
            FlightClass flightClass,
            int seatCount,
            int stops) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(departureDate);
        
        List<Itinerary> list = new ArrayList<>();
        if (stops == 0) {
            List<Flight> flights = flightRepository.getFlights(departureLocation, 
                    arrivalLocation, 
                    DateUtil.getTimeOnly(departureDate), 
                    DateUtil.toDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
            for (Flight flight : flights) {
                if (flight.getArrivalLocation().sameIdentityAs(arrivalLocation)) {
                    FlightInstance instance = new FlightInstance(flight, departureDate);
                    if (hasSeats(instance, seatCount, flightClass)) {
                        list.add(new Itinerary(Arrays.asList(instance)));
                    }
                }
            }
        }
        else {
            List<Flight> flights = flightRepository.getFlights(departureLocation, 
                    DateUtil.getTimeOnly(departureDate), 
                    DateUtil.toDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
            for (Flight flight : flights) {
                FlightInstance instance = new FlightInstance(flight, departureDate);
                if (hasSeats(instance, seatCount, flightClass)) {
                    if (flight.getArrivalLocation().sameIdentityAs(arrivalLocation)) {
                        list.add(new Itinerary(Arrays.asList(instance)));
                    }
                    else {
                        List<FlightInstance> instanceList = new ArrayList<>();
                        instanceList.add(instance);
                        list.addAll(getItineraryOptions(new Itinerary(
                                instanceList),
                                instance, arrivalLocation, flightClass, seatCount, stops - 1));
                    }
                }
            }
        }
        
        return list;
    }
    
    private List<Itinerary> getItineraryOptions(
            Itinerary itinerary,
            FlightInstance connectingFlight,
            Airport arrivalLocation,
            FlightClass flightClass,
            int seatCount,
            int stops) { 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(connectingFlight.getArrivalDate());
        
        List<Itinerary> list = new ArrayList<>();
        if (stops == 0) {
            List<Flight> flights = flightRepository.getFlights(
                    connectingFlight.getArrivalLocation(), 
                    arrivalLocation, 
                    DateUtil.getTimeOnly(connectingFlight.getArrivalDate()), 
                    DateUtil.toDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
            for (Flight flight : flights) {
                FlightInstance instance = new FlightInstance(flight, connectingFlight.getArrivalDate());
                if (hasSeats(instance, seatCount, flightClass)) {
                    Itinerary newItinerary = new Itinerary(itinerary.getFlights());
                    newItinerary.getFlights().add(instance);
                    list.add(newItinerary);
                }
            }
        }
        else {
            List<Flight> flights = flightRepository.getFlights(
                    connectingFlight.getArrivalLocation(), 
                    DateUtil.getTimeOnly(connectingFlight.getArrivalDate()), 
                    DateUtil.toDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
            for (Flight flight : flights) {
                FlightInstance instance = new FlightInstance(flight, connectingFlight.getArrivalDate());
                if (hasSeats(instance, seatCount, flightClass)) {
                    Itinerary newItinerary = new Itinerary(itinerary.getFlights());
                    newItinerary.getFlights().add(instance);

                    if (flight.getArrivalLocation().sameIdentityAs(arrivalLocation)) {
                        list.add(newItinerary);
                    }
                    else {
                        list.addAll(getItineraryOptions(newItinerary,
                                instance, arrivalLocation, flightClass, seatCount, stops - 1));
                    } 
                }
            }
        }
        
        return list;
    }
    
    
    private boolean hasSeats(FlightInstance flightInstance, int seatCount, FlightClass flightClass) {
        int maxSeatCount = 0;
        if (flightClass == FlightClass.BUSINESS) {
            maxSeatCount = 10;
        }
        else if (flightClass == FlightClass.ECONOMY) {
            maxSeatCount = 90;
        }
        
        return getSeatCount(flightInstance, flightClass) + seatCount <= maxSeatCount;
    }
    
    @Override
    public List<FlightInventory> getInventory(Date fromDate, Date toDate) {
        List<FlightInventory> inventory = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromDate);
        
        do {
            List<Flight> flights = flightRepository.getFlights(
                    DateUtil.toDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
            for (Flight flight : flights) {
                FlightInstance instance = new FlightInstance(flight, calendar.getTime());
                
                int unsoldBusinessSeatCount = 10 - getSeatCount(instance, FlightClass.BUSINESS);
                int unsoldEconomySeatCount = 90 - getSeatCount(instance, FlightClass.ECONOMY);
                
                if (unsoldBusinessSeatCount > 0 || unsoldEconomySeatCount > 0) {
                    inventory.add(new FlightInventory(
                            calendar.getTime(),
                            instance.getFlightNumber().getAirlineCodeAndFlightNumber(),
                            unsoldBusinessSeatCount,
                            unsoldEconomySeatCount));
                }
            }
            calendar.add(Calendar.DATE, 1);
        } while (calendar.getTime().compareTo(toDate) <= 0);
        
        return inventory;
    }
    
    private int getSeatCount(FlightInstance flightInstance, FlightClass flightClass) {
        int reservedSeats = 0;
        
        List<FlightLeg> flights = reservationRepository.getAllFlightInstances(
                flightInstance.getFlightNumber(), 
                flightInstance.getDepartureDate());
        for (FlightLeg flight : flights) {
            if (flight.getStatus() != ReservationStatus.CANCELLED) {
                if (flight.getFlightClass() == flightClass) {
                    reservedSeats += flight.getSeatCount();
                }
            }
        }
        
        return reservedSeats;
    }
}
