/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.reservation;

/**
 *
 * @author ajadriano
 */
public enum ReservationStatus {
    
    CREATED, RESERVED, BOOKED, CANCELLED;
    
    public boolean sameValueAs(ReservationStatus other) {
        return this.equals(other);
    }
}
