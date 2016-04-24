/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.reservation.facade.dto;

import domain.reservation.ReservationStatus;
import java.util.Date;

/**
 *
 * @author ajadriano
 */
public class ReservationDto {
    private String ticketNumber;
    private ItineraryDto outboundFlight;
    private ItineraryDto inboundFlight;    
    private int seats;
    private String flightClass;
    private ReservationStatus reservationStatus;
    private String creditCardNumber;
    private Date creditCardExpirationDate;
    
    public ReservationDto(String ticketNumber,
            ItineraryDto outboundFlight,
            ItineraryDto inboundFlight,
            int seats,
            String flightClass,
            ReservationStatus reservationStatus,
            String creditCardNumber,
            Date creditCardExpirationDate) {
        this.ticketNumber = ticketNumber;
        this.outboundFlight = outboundFlight;
        this.inboundFlight = inboundFlight;
        this.seats = seats;
        this.flightClass = flightClass;
        this.reservationStatus = reservationStatus;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpirationDate = creditCardExpirationDate;
    }

    /**
     * @return the ticketNumber
     */
    public String getTicketNumber() {
        return ticketNumber;
    }

    /**
     * @param ticketNumber the ticketNumber to set
     */
    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    /**
     * @return the outboundFlight
     */
    public ItineraryDto getOutboundFlight() {
        return outboundFlight;
    }

    /**
     * @param outboundFlight the outboundFlight to set
     */
    public void setOutboundFlight(ItineraryDto outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    /**
     * @return the inboundFlight
     */
    public ItineraryDto getInboundFlight() {
        return inboundFlight;
    }

    /**
     * @param inboundFlight the inboundFlight to set
     */
    public void setInboundFlight(ItineraryDto inboundFlight) {
        this.inboundFlight = inboundFlight;
    }

    /**
     * @return the seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * @return the flightClass
     */
    public String getFlightClass() {
        return flightClass;
    }

    /**
     * @param flightClass the flightClass to set
     */
    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
    
    public boolean isHasInboundFlight() {
        return inboundFlight.getLegs().isEmpty() == false;
    }

    /**
     * @return the reservationStatus
     */
    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    /**
     * @param reservationStatus the reservationStatus to set
     */
    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @param creditCardNumber the creditCardNumber to set
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    /**
     * @return the creditCardExpirationDate
     */
    public Date getCreditCardExpirationDate() {
        return creditCardExpirationDate;
    }

    /**
     * @param creditCardExpirationDate the creditCardExpirationDate to set
     */
    public void setCreditCardExpirationDate(Date creditCardExpirationDate) {
        this.creditCardExpirationDate = creditCardExpirationDate;
    }
    
}
