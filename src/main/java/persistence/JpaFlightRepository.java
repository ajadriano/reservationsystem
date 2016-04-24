/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.flight.Airport;
import domain.flight.Flight;
import domain.flight.FlightNumber;
import domain.flight.FlightRepository;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

@ApplicationScoped
public class JpaFlightRepository implements FlightRepository, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(
            JpaFlightRepository.class.getName());

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Flight> findAll() {
        return entityManager.createNamedQuery("Flight.findAll", Flight.class)
                .getResultList();
    }
    
    @Override
    public void store(Flight flight) {
        entityManager.persist(flight);
    }
    
    @Override
    public List<Flight> getFlights(Airport departureLocation,
            Date departureTime,
            DayOfWeek departureDay) {
        return entityManager.createNamedQuery("Flight.getAvailableFlights", Flight.class)
                .setParameter("departureLocation", departureLocation)
                .setParameter("departureTime", departureTime, TemporalType.TIMESTAMP)
                .setParameter("departureDay", departureDay)
                .getResultList();
    }
    
    @Override
    public List<Flight> getFlights(Airport departureLocation,
            Airport arrivalLocation,
            Date departureTime,
            DayOfWeek departureDay) {
        return entityManager.createNamedQuery("Flight.getAvailableFlightsWithArrival", Flight.class)
                .setParameter("departureLocation", departureLocation)
                .setParameter("arrivalLocation", arrivalLocation)
                .setParameter("departureTime", departureTime, TemporalType.TIMESTAMP)
                .setParameter("departureDay", departureDay)
                .getResultList();
    }
    
    @Override
    public List<Flight> getFlights(DayOfWeek departureDay) {
        return entityManager.createNamedQuery("Flight.getFlightsByDay", Flight.class)
                .setParameter("departureDay", departureDay)
                .getResultList();
    }
    
    @Override
    public void delete(FlightNumber flightNumber) {
        Flight flight = entityManager.createNamedQuery("Flight.findByFlightNumber", Flight.class)
                .setParameter("flightNumber", flightNumber)
                .getSingleResult();
        entityManager.remove(flight);
    }
}
