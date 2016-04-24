/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.user;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ajadriano
 */
@Embeddable
public class CreditCard implements Serializable {

    @Column(name = "number")
    private String number;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration")
    private Date expiration;
    
    public CreditCard() {
        
    }
    
    public CreditCard(String number, Date expiration) {
        this.number = number;
        this.expiration = expiration;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @return the expiration
     */
    public Date getExpiration() {
        return expiration;
    }
}
