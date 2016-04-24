/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import domain.user.Person;
import domain.user.SearchRequest;
import java.util.Date;

/**
 *
 * @author ajadriano
 */
public interface UserService {
    boolean usernameExists(String username);
    
    void registerUser(String username, 
                    String password, 
                    String name, 
                    String address, 
                    String emailAddress, 
                    String creditCardNumber, 
                    Date creditCardExpiration);
    
    boolean validate(String username, String password);
    
    Person getUser(String username);
    
    void saveRequest(String username, SearchRequest request);
    
    SearchRequest getRequest(String username);
}
