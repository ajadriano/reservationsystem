/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.flight.FlightNumber;
import domain.reservation.FlightLeg;
import domain.reservation.Reservation;
import domain.reservation.ReservationRepository;
import domain.reservation.ReservationStatus;
import domain.user.CreditCard;
import domain.user.Person;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

@ApplicationScoped
public class JpaReservationRepository implements ReservationRepository, Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public Reservation get(String reservationNumber) {
        try {
           return entityManager.createNamedQuery("Reservation.findByReservationNumber",
                Reservation.class).setParameter("reservationNumber", reservationNumber)
                .getSingleResult(); 
        }
        catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Reservation> findAll(Person user, ReservationStatus status) {
        return entityManager.createNamedQuery("Reservation.findByUser",
                Reservation.class).setParameter("person", user)
                .setParameter("status", status)
                .getResultList();
    }
    
    @Override
    public void commit(Reservation reservation) {
        entityManager.persist(reservation);
    }
    
    @Override
    public int getSequenceNumber(FlightNumber flightNumber, String username) {
        return entityManager.createNamedQuery("FlightLeg.findByFlightNumberAndUser",
                FlightLeg.class).setParameter("flightNumber", flightNumber)
                .setParameter("username", username)
                .getResultList().size() + 1;
    }
    
    @Override
    public List<FlightLeg> getAllFlightInstances(FlightNumber flightNumber) {
        return entityManager.createNamedQuery("FlightLeg.findByFlightNumber",
                FlightLeg.class).setParameter("flightNumber", flightNumber)
                .getResultList();
    }
    
    @Override
    public void updateStatus(String reservationNumber, ReservationStatus status) {
        Reservation reservation = entityManager.createNamedQuery("Reservation.findByReservationNumber",
                Reservation.class).setParameter("reservationNumber", reservationNumber)
                .getSingleResult();
        reservation.setStatus(status);
    }
    
    @Override
    public List<FlightLeg> getAllFlightInstances(FlightNumber flightNumber, 
            Date departureDate) {
        return entityManager.createNamedQuery("FlightLeg.findByFlightNumberAndDate",
                FlightLeg.class).setParameter("flightNumber", flightNumber)
                .setParameter("departureDate", departureDate, TemporalType.TIMESTAMP)
                .getResultList();
    }
    
    @Override
    public void setCreditCard(String reservationNumber, CreditCard creditCard) {
        Reservation reservation = entityManager.createNamedQuery("Reservation.findByReservationNumber",
                Reservation.class).setParameter("reservationNumber", reservationNumber)
                .getSingleResult();
        reservation.setCreditCard(creditCard);
    }
}
