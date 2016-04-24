package domain.reservation;

import domain.reservation.FlightLeg;
import domain.reservation.ReservationStatus;
import domain.user.CreditCard;
import domain.user.Person;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-24T21:21:53")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile SingularAttribute<Reservation, Person> person;
    public static volatile SingularAttribute<Reservation, String> reservationNumber;
    public static volatile ListAttribute<Reservation, FlightLeg> outboundLegs;
    public static volatile ListAttribute<Reservation, FlightLeg> inboundLegs;
    public static volatile SingularAttribute<Reservation, Long> id;
    public static volatile SingularAttribute<Reservation, Date> reservationDate;
    public static volatile SingularAttribute<Reservation, CreditCard> creditCard;
    public static volatile SingularAttribute<Reservation, ReservationStatus> status;

}