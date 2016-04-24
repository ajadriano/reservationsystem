/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.flight.Airline;
import domain.flight.AirlineCode;
import domain.flight.AirlineRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class JpaAirlineRepository implements AirlineRepository, Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Airline find(AirlineCode airlineCode) {
        return entityManager.createNamedQuery("Airline.findByAirlineCode",
                Airline.class).setParameter("airlineCode", airlineCode)
                .getSingleResult();
    }

    @Override
    public List<Airline> findAll() {
        return entityManager.createNamedQuery("Airline.findAll", Airline.class)
                .getResultList();
    }
}
