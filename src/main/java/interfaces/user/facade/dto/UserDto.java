/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.user.facade.dto;

import java.util.Date;

/**
 *
 * @author ajadriano
 */
public class UserDto {
    private String username;
    private String creditCardNumber;
    private Date creditCardExpiration;
    
    public UserDto(String username,
            String creditCardNumber,
            Date creditCardExpiration) {
        this.username = username;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiration = creditCardExpiration;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the creditCardNumber
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * @return the creditCardExpiration
     */
    public Date getCreditCardExpiration() {
        return creditCardExpiration;
    }
}
