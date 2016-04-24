/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.flight.Airport;
import domain.flight.AirportCode;
import domain.flight.AirportRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class JpaAirportRepository implements AirportRepository, Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Airport find(AirportCode airportCode) {
        return entityManager.createNamedQuery("Airport.findByAirportCode",
                Airport.class).setParameter("airportCode", airportCode)
                .getSingleResult();
    }

    @Override
    public List<Airport> findAll() {
        return entityManager.createNamedQuery("Airport.findAll", Airport.class)
                .getResultList();
    }
}
