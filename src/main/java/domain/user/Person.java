/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ajadriano
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "Select p from Person p"),
    @NamedQuery(name = "Person.findByUsername",
            query = "Select p from Person p where p.username = :username")})
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "username", unique = true, updatable = false)
    @NotNull
    private String username;
    
    @Column(name = "password")
    @NotNull
    private String password;
    
    @Column(name = "name")
    @NotNull
    private String name;
    
    @Column(name = "address")
    private String address; 
    
    @Column(name = "email_address")
    private String emailAddress;
    
    @Embedded
    private CreditCard creditCard;
    
    @Embedded
    private SearchRequest searchRequest;
    
    public Person() {
        
    }
        
    public Person(String username,
            String password,
            String name,
            String address,
            String emailAddress,
            CreditCard creditCard) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.emailAddress = emailAddress;
        this.creditCard = creditCard;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Person other = (Person) o;

        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    boolean sameValueAs(Person other) {
        return other != null && this.getUsername().equals(other.getUsername());
    }

    @Override
    public String toString() {
        return getUsername();
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the creditCard
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @return the searchRequest
     */
    public SearchRequest getSearchRequest() {
        return searchRequest;
    }

    /**
     * @param searchRequest the searchRequest to set
     */
    public void setSearchRequest(SearchRequest searchRequest) {
        this.searchRequest = searchRequest;
    }
}
