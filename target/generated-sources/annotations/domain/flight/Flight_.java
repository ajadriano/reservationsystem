package domain.flight;

import domain.flight.Airport;
import domain.flight.FlightNumber;
import java.time.DayOfWeek;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-24T21:21:53")
@StaticMetamodel(Flight.class)
public class Flight_ { 

    public static volatile SingularAttribute<Flight, Date> departureTime;
    public static volatile SingularAttribute<Flight, Airport> departureLocation;
    public static volatile SingularAttribute<Flight, DayOfWeek> arrivalDay;
    public static volatile SingularAttribute<Flight, Date> arrivalTime;
    public static volatile SingularAttribute<Flight, Double> economyClassCost;
    public static volatile SingularAttribute<Flight, Double> businessClassCost;
    public static volatile SingularAttribute<Flight, Long> id;
    public static volatile SingularAttribute<Flight, DayOfWeek> departureDay;
    public static volatile SingularAttribute<Flight, Airport> arrivalLocation;
    public static volatile SingularAttribute<Flight, FlightNumber> flightNumber;

}