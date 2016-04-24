/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.user.facade.internal;

import application.UserService;
import domain.user.CreditCard;
import domain.user.Person;
import domain.user.SearchRequest;
import interfaces.user.facade.UserServiceFacade;
import interfaces.user.facade.dto.UserDto;
import java.util.Date;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author ajadriano
 */
@ApplicationScoped
public class DefaultUserServiceFacade implements UserServiceFacade {

    @Inject
    private UserService userService;
    
    @Override
    public boolean usernameExists(String username) {
        return userService.usernameExists(username);
    }
    
    @Override
    public void registerUser(String username, 
                    String password, 
                    String name, 
                    String address, 
                    String emailAddress, 
                    String creditCardNumber, 
                    Date creditCardExpiration) {
        userService.registerUser(username, password, name, address, emailAddress, creditCardNumber, creditCardExpiration);
    }
    
    @Override
    public UserDto validate(String username, String password) {
        if (userService.validate(username, password)) {
            return find(username);
        }
        
        return null;
    }
    
    @Override
    public UserDto find(String username) {
        Person user = userService.getUser(username);
            
            String creditCardNumber = null;
            Date creditCardExpiration = null;
            
            CreditCard creditCard = user.getCreditCard();
            if (creditCard != null) {
                creditCardNumber = creditCard.getNumber();
                creditCardExpiration = creditCard.getExpiration();
            }
            
            return new UserDto(user.getUsername(),
                creditCardNumber, creditCardExpiration);
    }
    
    @Override
    public void saveRequest(String username, SearchRequest request) {
        userService.saveRequest(username, request);
    }
    
    @Override
    public SearchRequest getRequest(String username) {
        return userService.getRequest(username);
    }
}
