/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.reservation;

import domain.user.CreditCard;
import domain.user.Person;
import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.PrivateOwned;

/**
 *
 * @author ajadriano
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Reservation.findByUser",
            query = "Select r from Reservation r where r.person = :person and r.status = :status"),
    @NamedQuery(name = "Reservation.findByReservationNumber",
            query = "Select r from Reservation r where r.reservationNumber = :reservationNumber")})
public class Reservation implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "reservation_number", unique = true, updatable = false)
    @NotNull
    private String reservationNumber;
    
    @ManyToOne
    @JoinColumn(name = "person")
    @NotNull
    private Person person;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "outgoing_legs")
    // TODO Index this is in leg_index
    @OrderBy("departureDate")
    @PrivateOwned
    @Size(min = 1)
    private List<FlightLeg> outboundLegs = Collections.emptyList();
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "incoming_legs")
    // TODO Index this is in leg_index
    @OrderBy("departureDate")
    @PrivateOwned
    private List<FlightLeg> inboundLegs = Collections.emptyList();
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reservation_date")
    @NotNull
    private Date reservationDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private ReservationStatus status;
    
    @Embedded
    private CreditCard creditCard;
    
    public Reservation() {
        reservationNumber = "AA-000-ADMINI-000";
    }
    
    public Reservation(String reservationNumber,
            Person person,
            List<FlightLeg> outboundLegs,
            List<FlightLeg> inboundLegs,
            Date reservationDate,
            ReservationStatus status) {
        this.reservationNumber = reservationNumber;
        this.person = person;
        this.outboundLegs = outboundLegs;
        this.inboundLegs = inboundLegs;
        this.reservationDate = reservationDate;
        this.status = status;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Reservation other = (Reservation) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return getReservationNumber().hashCode();
    }

    boolean sameValueAs(Reservation other) {
        return other != null && this.getReservationNumber().equals(other.getReservationNumber());
    }

    @Override
    public String toString() {
        return getReservationNumber().toString();
    }

    /**
     * @return the outboundLegs
     */
    public List<FlightLeg> getOutboundLegs() {
        return outboundLegs;
    }

    /**
     * @return the inboundLegs
     */
    public List<FlightLeg> getInboundLegs() {
        return inboundLegs;
    }

    /**
     * @return the reservationNumber
     */
    public String getReservationNumber() {
        return reservationNumber;
    }

    /**
     * @return the status
     */
    public ReservationStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ReservationStatus status) {
        this.status = status;
        for (FlightLeg flightLeg : outboundLegs) {
            flightLeg.setStatus(status);
        }
        
        if (inboundLegs != null) {
            for (FlightLeg flightLeg : inboundLegs) {
                flightLeg.setStatus(status);
            }
        }
    }
        

    /**
     * @return the reservationDate
     */
    public Date getReservationDate() {
        return reservationDate;
    }

    /**
     * @param reservationDate the reservationDate to set
     */
    public void setReservationDate(Date reservationDate) {
        this.reservationDate = reservationDate;
    }

    /**
     * @return the creditCard
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @param creditCard the creditCard to set
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
