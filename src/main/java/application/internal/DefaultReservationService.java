/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.internal;

import application.ReservationService;
import domain.flight.FlightNumber;
import domain.reservation.FlightLeg;
import domain.reservation.Reservation;
import domain.reservation.ReservationRepository;
import domain.reservation.ReservationStatus;
import domain.user.CreditCard;
import domain.user.Person;
import domain.user.UserRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ajadriano
 */
@Stateless
public class DefaultReservationService implements ReservationService {
    @Inject
    private UserRepository userRepository;
    
    @Inject
    private ReservationRepository reservationRepository;
    
    @Override
    public Reservation get(String reservationNumber) {
       return reservationRepository.get(reservationNumber);
    }
    
    @Override
    public List<Reservation> getReservations(String username, ReservationStatus status) {
        Person user = userRepository.find(username);
        if (user != null)
        {
            List<Reservation> reservations = reservationRepository.findAll(user, status);
            
            if (status == ReservationStatus.RESERVED) {
                
                List<Reservation> reservationsToRemove = new ArrayList<>();
                
                for (Reservation reservation : reservations) {
                    long difference = getDateDiff(reservation.getReservationDate(), new Date(), TimeUnit.SECONDS);
                    if (difference > 120) {
                        reservationRepository.updateStatus(reservation.getReservationNumber(), ReservationStatus.CANCELLED);
                        reservationsToRemove.add(reservation);
                    }
                }
                
                for (Reservation reservation : reservationsToRemove) {
                    reservations.remove(reservation);
                }
            }
            else if (status == ReservationStatus.CANCELLED) {
                
                List<Reservation> reservedList = reservationRepository.findAll(user, ReservationStatus.RESERVED);
                
                for (Reservation reservation : reservedList) {
                    long difference = getDateDiff(reservation.getReservationDate(), new Date(), TimeUnit.SECONDS);
                    if (difference > 120) {
                        reservationRepository.updateStatus(reservation.getReservationNumber(), ReservationStatus.CANCELLED);
                        reservations.add(reservation);
                    }
                }
            }
            
            
            return reservations;
        }
            
        return new ArrayList<>();
    }
    
    @Override
    public String generateTicketNumber(String username, FlightNumber flightNumber) {
        return flightNumber.getAirline().getAirlineCode().getCodeString() + "-" +
               flightNumber.getFlightNumber() + "-" +
               username.substring(0, 6).toUpperCase() + "-" +
               String.format("%03d", reservationRepository.getSequenceNumber(flightNumber, username));
    }
    
    @Override
    public void reserve(String username, 
            List<FlightLeg> outboundFlights, 
            List<FlightLeg> inboundFlights) {
        Person user = userRepository.find(username);
        
        String ticketNumber = outboundFlights.get(0).getReservationNumber();
        Reservation reservation = new Reservation(
                ticketNumber,
                user,
                outboundFlights,
                inboundFlights,
                new Date(),
                ReservationStatus.RESERVED);
        
        reservationRepository.commit(reservation);
    }
    
    @Override
    public void book(String reservationNumber, CreditCard creditCard) {
        reservationRepository.updateStatus(reservationNumber, ReservationStatus.BOOKED);
        reservationRepository.setCreditCard(reservationNumber, creditCard);
    }
    
    @Override
    public void cancel(FlightNumber flightNumber) {
        
        List<String> reservationNumbers = new ArrayList<>();
        List<FlightLeg> legs = reservationRepository.getAllFlightInstances(flightNumber);
        
        for (FlightLeg leg : legs) {
            if (!reservationNumbers.contains(leg.getReservationNumber())) {
                reservationNumbers.add(leg.getReservationNumber());
            }
            
            leg.setStatus(ReservationStatus.CANCELLED);
        }
        
        for (String number : reservationNumbers) {
            reservationRepository.updateStatus(number, ReservationStatus.CANCELLED);
        }
    }
    
    @Override
    public void cancel(String reservationNumber) {
        reservationRepository.updateStatus(reservationNumber, ReservationStatus.CANCELLED);
    }
    
    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
}
}
