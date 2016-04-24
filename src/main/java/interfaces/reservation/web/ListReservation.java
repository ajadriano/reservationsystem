/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.reservation.web;

import interfaces.reservation.facade.ReservationServiceFacade;
import interfaces.reservation.facade.dto.ReservationDto;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ajadriano
 */
@Named
@ViewScoped
public class ListReservation implements Serializable {
    private List<ReservationDto> reservedBookings;
    private List<ReservationDto> confirmedBookings;
    private List<ReservationDto> cancelledBookings;
    
    private ReservationDto selectedReservedBooking;
    
    @Inject
    private ReservationServiceFacade reservationService;
    
    @PostConstruct
    public void init() {
        reservedBookings = reservationService.getReservedBookings();
        confirmedBookings = reservationService.getConfirmedBookings();
        cancelledBookings = reservationService.getCancelledBookings();
    }
    
    public List<ReservationDto> getReservedBookings() {
        return reservedBookings;
    }
    
    public List<ReservationDto> getConfirmedBookings() {
        return confirmedBookings;
    }
    
    public List<ReservationDto> getCancelledBookings() {
        return cancelledBookings;
    }

    /**
     * @return the selectedConfirmedBooking
     */
    public ReservationDto getSelectedReservedBooking() {
        return selectedReservedBooking;
    }

    /**
     * @param selectedConfirmedBooking the selectedConfirmedBooking to set
     */
    public void setSelectedReservedBooking(ReservationDto selectedConfirmedBooking) {
        this.selectedReservedBooking = selectedConfirmedBooking;
    }
    
    public String cancelBooking() {
        if (selectedReservedBooking != null) {
           reservationService.cancel(selectedReservedBooking.getTicketNumber()); 
        }
        return "/admin/bookings.xhtml?faces-redirect=true";
    }
}
