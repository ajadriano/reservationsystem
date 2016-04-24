/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.internal;

import application.UserService;
import domain.user.CreditCard;
import domain.user.Person;
import domain.user.SearchRequest;
import domain.user.UserRepository;
import java.util.Date;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ajadriano
 */
@Stateless
public class DefaultUserService implements UserService {
    @Inject
    private UserRepository userRepository;
    
    @Override
    public boolean usernameExists(String username) {
        return userRepository.find(username) != null;
    }
    
    @Override
    public void registerUser(String username, 
            String password, 
            String name, 
            String address, 
            String emailAddress, 
            String creditCardNumber, 
            Date creditCardExpiration) {
        CreditCard card = null;
        if (creditCardNumber != null &&
            !creditCardNumber.isEmpty() &&
            creditCardExpiration != null) {
            card = new CreditCard(creditCardNumber, creditCardExpiration);
        }
        
        Person user = new Person(username, 
                    password,
                    name,
                    address,
                    emailAddress,
                    card);
        userRepository.store(user);
    }
    
    @Override
    public boolean validate(String username, String password) {
        Person user = userRepository.find(username);
        if (user != null) {
            return password.equals(user.getPassword());
        }
        
        return false;
    }
    
    @Override
    public Person getUser(String username) {
        return userRepository.find(username);
    }
    
    @Override
    public void saveRequest(String username, SearchRequest request) {
        userRepository.saveRequest(username, request);
    }
    
    @Override
    public SearchRequest getRequest(String username) {
        return userRepository.getRequest(username);
    }
}
