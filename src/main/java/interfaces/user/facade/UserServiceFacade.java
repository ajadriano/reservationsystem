/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.user.facade;

import domain.user.SearchRequest;
import interfaces.user.facade.dto.UserDto;
import java.util.Date;

/**
 *
 * @author ajadriano
 */
public interface UserServiceFacade {
    boolean usernameExists(String username);
    
    void registerUser(String username,
            String password,
            String name,
            String address,
            String emailAddress,
            String creditCardNumber,
            Date creditCardExpiration);
    
    UserDto validate(String username, String password);
    UserDto find(String username);
    void saveRequest(String username, SearchRequest request);
    SearchRequest getRequest(String username);
}
