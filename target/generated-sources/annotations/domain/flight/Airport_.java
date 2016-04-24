package domain.flight;

import domain.flight.AirportCode;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-24T21:21:53")
@StaticMetamodel(Airport.class)
public class Airport_ { 

    public static volatile SingularAttribute<Airport, String> country;
    public static volatile SingularAttribute<Airport, String> city;
    public static volatile SingularAttribute<Airport, String> name;
    public static volatile SingularAttribute<Airport, Long> id;
    public static volatile SingularAttribute<Airport, AirportCode> airportCode;

}