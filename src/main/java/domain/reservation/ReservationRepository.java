/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.reservation;

import domain.flight.FlightNumber;
import domain.user.CreditCard;
import domain.user.Person;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface ReservationRepository {
    Reservation get(String reservationNumber);
    List<Reservation> findAll(Person user, ReservationStatus status);
    void updateStatus(String reservationNumber, ReservationStatus status);
    void setCreditCard(String reservationNumber, CreditCard creditCard);
    void commit(Reservation reservation);
    int getSequenceNumber(FlightNumber flightNumber, String username);
    List<FlightLeg> getAllFlightInstances(FlightNumber flightNumber);
    List<FlightLeg> getAllFlightInstances(FlightNumber flightNumber, Date departureDate);
}
