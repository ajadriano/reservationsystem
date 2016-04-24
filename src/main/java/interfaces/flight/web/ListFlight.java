/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.flight.web;

import interfaces.flight.facade.FlightServiceFacade;
import interfaces.flight.facade.dto.FlightDto;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 * Handles listing cargo. Operates against a dedicated service facade, and could
 * easily be rewritten as a thick Swing client. Completely separated from the
 * domain layer, unlike the tracking user interface.
 * <p/>
 * In order to successfully keep the domain model shielded from user interface
 * considerations, this approach is generally preferred to the one taken in the
 * tracking controller. However, there is never any one perfect solution for all
 * situations, so we've chosen to demonstrate two polarized ways to build user
 * interfaces.
 */
@Named
@ViewScoped
public class ListFlight implements Serializable {
    private List<FlightDto> flights;
    private FlightDto selectedFlight;
    private DashboardModel model;
    
    @Inject
    private FlightServiceFacade flightServiceFacade;
    
    public ListFlight() {
        this.model = new DefaultDashboardModel();
        DashboardColumn mainColumn = new DefaultDashboardColumn();
 
        mainColumn.addWidget("Flights");
 
        this.model.addColumn(mainColumn);
    }
    
    @PostConstruct
    public void init() {
        flights = flightServiceFacade.getFlights();
    }

    public List<FlightDto> getFlights() {
        return flights;
    }

    /**
     * @return the selectedFlight
     */
    public FlightDto getSelectedFlight() {
        return selectedFlight;
    }

    /**
     * @param selectedFlight the selectedFlight to set
     */
    public void setSelectedFlight(FlightDto selectedFlight) {
        this.selectedFlight = selectedFlight;
    }
    
    public String cancelFlight() {
        if (selectedFlight != null) {
            flightServiceFacade.cancel(selectedFlight);
        }
        
        return "/admin/flights.xhtml?faces-redirect=true";
    }

    /**
     * @return the model
     */
    public DashboardModel getModel() {
        return model;
    }
}
