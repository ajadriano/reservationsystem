/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.flight.web;

import application.util.DateUtil;
import interfaces.flight.facade.FlightServiceFacade;
import interfaces.flight.facade.dto.InventoryDto;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author ajadriano
 */
@Named
@ViewScoped
public class ListInventory implements Serializable {
    private List<InventoryDto> inventory;
    private Date fromDate;
    private Date toDate;
    private DashboardModel model;
    
    @Inject
    private FlightServiceFacade flightServiceFacade;
    
    public ListInventory() {
        fromDate = DateUtil.trim(new Date());
        toDate = fromDate;
        
        this.model = new DefaultDashboardModel();
        DashboardColumn mainColumn = new DefaultDashboardColumn();
 
        mainColumn.addWidget("Inventory");
 
        this.model.addColumn(mainColumn);
    }
    
    @PostConstruct
    public void init() {
        inventory = flightServiceFacade.getUnsoldSeats(fromDate, toDate);
    }

    /**
     * @return the inventory
     */
    public List<InventoryDto> getInventory() {
        return inventory;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the model
     */
    public DashboardModel getModel() {
        return model;
    }
    
    public String search() {
        inventory = flightServiceFacade.getUnsoldSeats(fromDate, toDate);
        return "/admin/inventory.xhtml?faces-redirect=true";
    }
}
