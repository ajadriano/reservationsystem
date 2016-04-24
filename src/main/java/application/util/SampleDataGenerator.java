package application.util;

import domain.flight.SampleAirlines;
import domain.flight.SampleAirports;
import domain.flight.SampleFlights;
import domain.reservation.SampleReservations;
import domain.user.SamplePersons;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Loads sample data for demo.
 */
@Singleton
@Startup
public class SampleDataGenerator {

    // TODO See if the logger can be injected.
    private static final Logger logger = Logger.getLogger(
            SampleDataGenerator.class.getName());
    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void loadSampleData() {
        logger.info("Loading sample data.");
        unLoadAll(); //  Fail-safe in case of application restart that does not trigger a JPA schema drop.
        loadSampleAirports();
        loadSampleAirlines();
        loadSampleFlights();
        
        loadSampleUsers();
        loadSampleReservations();
    }

    private void unLoadAll() {
        logger.info("Unloading all existing data.");
        
        entityManager.createQuery("Delete from FlightLeg").executeUpdate();   
        entityManager.createQuery("Delete from Reservation").executeUpdate();             
        entityManager.createQuery("Delete from Person").executeUpdate(); 
        
        entityManager.createQuery("Delete from Flight").executeUpdate();
        entityManager.createQuery("Delete from Airport").executeUpdate();
        entityManager.createQuery("Delete from Airline").executeUpdate();
    }
    
    private void loadSampleAirports() {
        logger.info("Loading sample airports.");
        
        entityManager.persist(SampleAirports.Argentina);
        entityManager.persist(SampleAirports.Australia);
        entityManager.persist(SampleAirports.Austria);
        entityManager.persist(SampleAirports.Canada);
        entityManager.persist(SampleAirports.China);
        entityManager.persist(SampleAirports.Colombia);
        entityManager.persist(SampleAirports.England);
        entityManager.persist(SampleAirports.Finland);
        entityManager.persist(SampleAirports.France);
        entityManager.persist(SampleAirports.Germany);
        entityManager.persist(SampleAirports.India);
        entityManager.persist(SampleAirports.Indonesia);
        entityManager.persist(SampleAirports.Ireland);
        entityManager.persist(SampleAirports.Italy);
        entityManager.persist(SampleAirports.Japan);
        entityManager.persist(SampleAirports.Korea);
        entityManager.persist(SampleAirports.Mexico);
        entityManager.persist(SampleAirports.NewZealand);
        entityManager.persist(SampleAirports.Philippines);
        entityManager.persist(SampleAirports.Russia);
        entityManager.persist(SampleAirports.Singapore);
        entityManager.persist(SampleAirports.Spain);
        entityManager.persist(SampleAirports.Switzerland);
        entityManager.persist(SampleAirports.Thailand);
        entityManager.persist(SampleAirports.UnitedStates);
    }
    
    private void loadSampleAirlines() {
        logger.info("Loading sample airlines.");
        
        entityManager.persist(SampleAirlines.Argentina);
        entityManager.persist(SampleAirlines.Australia);
        entityManager.persist(SampleAirlines.Austria);
        entityManager.persist(SampleAirlines.Canada);
        entityManager.persist(SampleAirlines.China);
        entityManager.persist(SampleAirlines.Colombia);
        entityManager.persist(SampleAirlines.England);
        entityManager.persist(SampleAirlines.Finland);
        entityManager.persist(SampleAirlines.France);
        entityManager.persist(SampleAirlines.Germany);
        entityManager.persist(SampleAirlines.India);
        entityManager.persist(SampleAirlines.Indonesia);
        entityManager.persist(SampleAirlines.Ireland);
        entityManager.persist(SampleAirlines.Italy);
        entityManager.persist(SampleAirlines.Japan);
        entityManager.persist(SampleAirlines.Korea);
        entityManager.persist(SampleAirlines.Mexico);
        entityManager.persist(SampleAirlines.NewZealand);
        entityManager.persist(SampleAirlines.Philippines);
        entityManager.persist(SampleAirlines.Russia);
        entityManager.persist(SampleAirlines.Singapore);
        entityManager.persist(SampleAirlines.Spain);
        entityManager.persist(SampleAirlines.Switzerland);
        entityManager.persist(SampleAirlines.Thailand);
        entityManager.persist(SampleAirlines.UnitedStates);
    }
    
    private void loadSampleFlights() {
        logger.info("Loading sample flights.");
        
        entityManager.persist(SampleFlights.BOGJFK01);
        entityManager.persist(SampleFlights.JFKBOG01);
        entityManager.persist(SampleFlights.JFKLHR01);
        entityManager.persist(SampleFlights.LHRJFK01);
        entityManager.persist(SampleFlights.MNLSIN01);
        entityManager.persist(SampleFlights.SINMNL01);
        entityManager.persist(SampleFlights.LHRNRT01);
        entityManager.persist(SampleFlights.LHRNRT02);
        entityManager.persist(SampleFlights.NRTLHR01);
        entityManager.persist(SampleFlights.NRTLHR02);
    }
    
    private void loadSampleUsers() {
        logger.info("Loading sample users.");

        entityManager.persist(SamplePersons.Administrator);
        entityManager.persist(SamplePersons.User1);
        entityManager.persist(SamplePersons.User2);
    }
    
    private void loadSampleReservations() {
        logger.info("Loading sample reservations.");
        entityManager.persist(SampleReservations.Reservation1);
        //entityManager.persist(SampleReservations.Reservation2);
        entityManager.persist(SampleReservations.Reservation3);
        //entityManager.persist(SampleReservations.Reservation4);
    }
}
