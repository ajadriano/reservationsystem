/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.reservation.facade;

import domain.flight.Airport;
import domain.reservation.FlightClass;
import interfaces.reservation.facade.dto.ItineraryDto;
import interfaces.reservation.facade.dto.ReservationDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface ReservationServiceFacade {

    ReservationDto getReservation(String ticketNumber);
    
    List<ReservationDto> getReservedBookings();
    List<ReservationDto> getConfirmedBookings();
    List<ReservationDto> getCancelledBookings();
    
    List<ItineraryDto> getItineraryOptions(Airport departureLocation,
            Airport arrivalLocation,
            Date departureDate,
            FlightClass flightClass,
            int passengerCount,
            int stops);
    String reserve(String username, 
            ItineraryDto outboundFlight,
            ItineraryDto inboundFlight,
            FlightClass flightClass,
            int passengerCount);
    void book(String reservationNumber, String creditCardNumber, Date creditCardExpirationDate);
    void cancel(String reservationNumber);
}
