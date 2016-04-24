/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.flight.FlightNumber;
import domain.reservation.FlightLeg;
import domain.reservation.Reservation;
import domain.reservation.ReservationStatus;
import domain.user.CreditCard;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface ReservationService {
    Reservation get(String reservationNumber);
    List<Reservation> getReservations(String username, ReservationStatus status);
    String generateTicketNumber(String username, FlightNumber flightNumber);
    void reserve(String username, 
            List<FlightLeg> outboundFlights, 
            List<FlightLeg> inboundFlights);
    void book(String reservationNumber, CreditCard creditCard);
    void cancel(FlightNumber flightNumber);
    void cancel(String reservationNumber);
}
