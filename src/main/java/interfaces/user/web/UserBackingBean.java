/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.user.web;

import application.util.DateUtil;
import domain.user.SearchRequest;
import interfaces.reservation.facade.ReservationServiceFacade;
import interfaces.reservation.facade.dto.ReservationDto;
import interfaces.user.facade.UserServiceFacade;
import interfaces.user.facade.dto.UserDto;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ajadriano
 */
@Named
@ViewScoped
public class UserBackingBean implements Serializable {
    private String loginUsername;
    private String loginPassword;
    
    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String address; 
    private String emailAddress;
    private String creditCardNumber;
    private String creditCardExpiration;
    
    @Inject
    private UserServiceFacade userServiceFacade;
    
    @Inject ReservationServiceFacade reservationServiceFacade;

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return the username
     */
    public String getLoginUsername() {
        return loginUsername;
    }

    /**
     * @param loginUsername the username to set
     */
    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the password
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * @param loginPassword the password to set
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    /**
     * @return the password
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the password to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
     * @return the expiration
     */
    public String getCreditCardExpiration() {
        return creditCardExpiration;
    }

    /**
     * @param expiration the expiration to set
     */
    public void setCreditCardExpiration(String expiration) {
        this.creditCardExpiration = expiration;
    }
    
    public UserDto register(UserServiceFacade facade) {
        
        boolean hasError = false;
        
        if (username == null || username.isEmpty()) {
            showError("Username required.");
            hasError = true;
        }
        
        if (password == null || password.isEmpty()) {
            showError("Password required.");
            hasError = true;
        }
        
        if (name == null || name.isEmpty()) {
            showError("Name required.");
            hasError = true;
        }
        
        if (password != null && !password.isEmpty() &&
            !password.equals(confirmPassword)) {
            showError("Password confirmation failed.");
            hasError = true;
        }
        
        if (username != null && !username.isEmpty() &&
            facade.usernameExists(username)) {
            showError("Username exists.");
            hasError = true;
        }  
        
        Date creditCardExpirationDate = null;
        if (creditCardNumber != null && !creditCardNumber.isEmpty()) {
            if (creditCardExpiration != null && !creditCardExpiration.isEmpty()) {
                try {
                    creditCardExpirationDate = DateUtil.toYearMonthDate(creditCardExpiration);
                    
                    Date todayDate = new Date();
                    long diff = creditCardExpirationDate.getTime() - todayDate.getTime();
                    if (diff < 0) {
                        showError("Invalid expiration date.");
                        hasError = true;
                    }
                    
                } catch (ParseException ex) {
                    showError("Invalid expiration date.");
                    hasError = true;
                }
            }
            else {
                showError("Expiration date required.");
                hasError = true;
            }
        }
        
        if (hasError) {
            return null;
        }
        
        
        facade.registerUser(username, 
                password, 
                name, 
                address, 
                emailAddress, 
                creditCardNumber, 
                creditCardExpirationDate);
        
        SessionBean.setUsername(username);
        
        return new UserDto(username, creditCardNumber, creditCardExpirationDate);
    }
    
    public String registerUser() {
        UserDto user = register(userServiceFacade);
        if (user == null) {
            return null;
        }
        
        return "/admin/index.xhtml?faces-redirect=true";
    }
    
    public UserDto validate(UserServiceFacade facade) {
        if (loginUsername == null || loginUsername.isEmpty()) {
            showError("Username required.");
            return null;
        }
        
        if (loginPassword == null || loginPassword.isEmpty()) {
            showError("Password required.");
            return null;
        }
        
        UserDto user = facade.validate(loginUsername, loginPassword);
        if (user != null) {
            SessionBean.setUsername(user.getUsername());
            return user;
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return null;
        }
    }
    
    public String validateUsernamePassword() {
        UserDto user = validate(userServiceFacade);
        if (user != null) {
            
            if (user.getUsername().equals("administrator")) {
                return "/admin/flights.xhtml?faces-redirect=true";
            }
            
            SearchRequest request = userServiceFacade.getRequest(user.getUsername());
            if (request != null){
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
                session.setAttribute("searchrequest", request);         
                return "reservation";
            }
            
            List<ReservationDto> reservations = reservationServiceFacade.getReservedBookings();
            if (reservations.isEmpty() == false) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
                session.setAttribute("reservation", reservations.get(0).getTicketNumber());         
                return "/admin/payment.xhtml?faces-redirect=true";
            }
            
            return "/admin/bookings.xhtml?faces-redirect=true";
        } else {
            return "/admin/user.xhtml";
        }
    }
    
    private void showError(String errorMessage) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(errorMessage);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, message);
    }
}
