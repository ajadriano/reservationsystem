package domain.reservation;

import domain.flight.Airport;
import domain.flight.FlightNumber;
import domain.reservation.FlightClass;
import domain.reservation.ReservationStatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-24T21:21:53")
@StaticMetamodel(FlightLeg.class)
public class FlightLeg_ { 

    public static volatile SingularAttribute<FlightLeg, Airport> departureLocation;
    public static volatile SingularAttribute<FlightLeg, Double> cost;
    public static volatile SingularAttribute<FlightLeg, String> reservationNumber;
    public static volatile SingularAttribute<FlightLeg, FlightClass> flightClass;
    public static volatile SingularAttribute<FlightLeg, Long> id;
    public static volatile SingularAttribute<FlightLeg, Date> departureDate;
    public static volatile SingularAttribute<FlightLeg, Airport> arrivalLocation;
    public static volatile SingularAttribute<FlightLeg, Integer> seatCount;
    public static volatile SingularAttribute<FlightLeg, FlightNumber> flightNumber;
    public static volatile SingularAttribute<FlightLeg, Date> arrivalDate;
    public static volatile SingularAttribute<FlightLeg, ReservationStatus> status;
    public static volatile SingularAttribute<FlightLeg, String> username;

}