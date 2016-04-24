/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.user.web;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author davidd
 */
@Named
@ViewScoped
public class UserView implements Serializable{

    private DashboardModel model;

    private String name = "TestDD";

    public String getName() {
        return name;
    }
       
    public UserView(){
        // Initialize the dashboard model
        this.model = new DefaultDashboardModel();
        DashboardColumn mainColumn = new DefaultDashboardColumn();
        
        mainColumn.addWidget("ConfirmedBookings");
        mainColumn.addWidget("ReservedBookings");
        mainColumn.addWidget("CancelledBookings");
 
        this.model.addColumn(mainColumn);
 
    }
 
    public DashboardModel getModel() {
        return model;
    }
 
    public void setModel(DashboardModel model) {
        this.model = model;
    }
    
}
