package domain.flight;

import domain.flight.AirlineCode;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-24T21:21:53")
@StaticMetamodel(Airline.class)
public class Airline_ { 

    public static volatile SingularAttribute<Airline, String> name;
    public static volatile SingularAttribute<Airline, AirlineCode> airlineCode;
    public static volatile SingularAttribute<Airline, Long> id;

}