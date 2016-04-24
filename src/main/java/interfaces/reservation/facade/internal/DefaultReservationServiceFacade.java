/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.reservation.facade.internal;

import application.FlightService;
import application.ReservationService;
import domain.flight.Airport;
import domain.flight.FlightInstance;
import domain.flight.Itinerary;
import domain.reservation.FlightClass;
import domain.reservation.FlightLeg;
import domain.reservation.Reservation;
import domain.reservation.ReservationStatus;
import domain.user.CreditCard;
import interfaces.reservation.facade.ReservationServiceFacade;
import interfaces.reservation.facade.dto.ItineraryDto;
import interfaces.reservation.facade.dto.LegDto;
import interfaces.reservation.facade.dto.ReservationDto;
import interfaces.user.web.SessionBean;
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
public class DefaultReservationServiceFacade implements ReservationServiceFacade {
    
    @Inject
    private ReservationService reservationService;
    
    @Inject
    private FlightService flightService;
    
    @Override
    public ReservationDto getReservation(String ticketNumber) {
        Reservation reservation = reservationService.get(ticketNumber);
        if (reservation != null) {
            return convert(reservation);
        }
        return null;
    }
    
    @Override
    public List<ReservationDto> getReservedBookings() {                
        return getBookings(ReservationStatus.RESERVED);
    }
    
    @Override
    public List<ReservationDto> getConfirmedBookings() {
        return getBookings(ReservationStatus.BOOKED);
    }
    
    @Override
    public List<ReservationDto> getCancelledBookings() {
        return getBookings(ReservationStatus.CANCELLED);
    }
    
    private List<ReservationDto> getBookings(ReservationStatus status) {                
        List<ReservationDto> list;
        list = new ArrayList<>();
        
        String username = SessionBean.getUsername();
        if (username == null || username.isEmpty())
        {
            return new ArrayList<>();
        }
        
        List<Reservation> reservations = reservationService.getReservations(username, status);
        
        for (Reservation reservation : reservations) {
            list.add(convert(reservation));
        }
        
        return list;
    }
    
    public List<ItineraryDto> getItineraryOptions(Airport departureLocation,
            Airport arrivalLocation,
            Date departureDate,
            FlightClass flightClass,
            int passengerCount,
            int stops) {
        List<ItineraryDto> list = new ArrayList<>();
        
        List<Itinerary> options = flightService.getItineraryOptions(departureLocation, arrivalLocation, 
                departureDate, flightClass, passengerCount, stops);
        
        int index = 0;
        
        for (Itinerary option : options) {
            int cost = 0;
            List<LegDto> legs = new ArrayList<>();
            for (FlightInstance flight : option.getFlights()) {
                LegDto leg = new LegDto(flight, flightClass);
                legs.add(leg);
                
                cost += leg.getCost();
            }
            
            list.add(new ItineraryDto(index++, legs, cost * passengerCount));
        }
        return list;
    }
    
    @Override
    public String reserve(String username, 
            ItineraryDto outboundFlight,
            ItineraryDto inboundFlight,
            FlightClass flightClass,
            int passengerCount) {
        
        String ticketNumber = reservationService.generateTicketNumber(username,
                outboundFlight.getLegs().get(0).getFlight().getFlightNumber());
        
        List<FlightLeg> outboundLegs = new ArrayList<>();
        for (LegDto leg : outboundFlight.getLegs()) {
            FlightInstance flight = leg.getFlight();
            outboundLegs.add(new FlightLeg(ticketNumber,
                flight.getFlightNumber(),
                flight.getDepartureLocation(),
                flight.getDepartureDate(),
                flight.getArrivalLocation(),
                flight.getArrivalDate(),
                flightClass,
                passengerCount,
                ReservationStatus.RESERVED,
                leg.getCost(),
                username));
        }
        
        List<FlightLeg> inboundLegs = null;
        
        if (inboundFlight != null) {
            inboundLegs = new ArrayList<>();
            for (LegDto leg : inboundFlight.getLegs()) {
                FlightInstance flight = leg.getFlight();
                inboundLegs.add(new FlightLeg(ticketNumber,
                    flight.getFlightNumber(),
                    flight.getDepartureLocation(),
                    flight.getDepartureDate(),
                    flight.getArrivalLocation(),
                    flight.getArrivalDate(),
                    flightClass,
                    passengerCount,
                    ReservationStatus.RESERVED,
                    leg.getCost(),
                    username));
            }
        }
        
        reservationService.reserve(username, outboundLegs, inboundLegs);
        return ticketNumber;
    }
    
    @Override
    public void book(String reservationNumber, String creditCardNumber, Date creditCardExpirationDate) {
        reservationService.book(reservationNumber,
                new CreditCard(creditCardNumber, creditCardExpirationDate));
    }
    
    @Override
    public void cancel(String reservationNumber) {
        reservationService.cancel(reservationNumber);
    }
    
    private ReservationDto convert(Reservation reservation) {
        List<LegDto> outboundLegs = new ArrayList<>();
            for (FlightLeg leg : reservation.getOutboundLegs()) {   
                
                outboundLegs.add(new LegDto(leg.getFlightNumber().getAirline().getName(),
                    leg.getFlightNumber().getAirlineCodeAndFlightNumber(),
                    leg.getDepartureLocation().getLocation(),
                    leg.getDepartureLocation().getName(),
                    leg.getDepartureDate(),
                    leg.getArrivalLocation().getLocation(),
                    leg.getArrivalLocation().getName(),
                    leg.getArrivalDate(),
                    leg.getCost()));
            }
            
            List<LegDto> inboundLegs = new ArrayList<>();
            for (FlightLeg leg : reservation.getInboundLegs()) {
                inboundLegs.add(new LegDto(leg.getFlightNumber().getAirline().getName(),
                    leg.getFlightNumber().getAirlineCodeAndFlightNumber(),
                    leg.getDepartureLocation().getLocation(),
                    leg.getDepartureLocation().getName(),
                    leg.getDepartureDate(),
                    leg.getArrivalLocation().getLocation(),
                    leg.getArrivalLocation().getName(),
                    leg.getArrivalDate(),
                    leg.getCost()));
            }
            
            String creditCardNumber = null;
            Date creditCardExpirationDate = null;
            
            if (reservation.getCreditCard() != null) {
                creditCardNumber = reservation.getCreditCard().getNumber();
                creditCardExpirationDate = reservation.getCreditCard().getExpiration();
            }
            
            return new ReservationDto(reservation.getReservationNumber(),
                    new ItineraryDto(0, outboundLegs, 0),
                    new ItineraryDto(0, inboundLegs, 0),
                    reservation.getOutboundLegs().get(0).getSeatCount(), 
                    reservation.getOutboundLegs().get(0).getFlightClass().toString(),
                    reservation.getStatus(), creditCardNumber, creditCardExpirationDate);
    }
}
