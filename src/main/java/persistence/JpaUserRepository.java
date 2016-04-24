/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import domain.user.Person;
import domain.user.SearchRequest;
import domain.user.UserRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class JpaUserRepository implements UserRepository, Serializable {

    private static final long serialVersionUID = 1L;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Person find(String username) {
        try 
        {
            return entityManager.createNamedQuery("Person.findByUsername",
                    Person.class).setParameter("username", username)
                    .getSingleResult();
        }
        catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Person> findAll() {
        return entityManager.createNamedQuery("Person.findAll", Person.class)
                .getResultList();
    }
    
    @Override
    public void store(Person user) {
        entityManager.persist(user);
    }
    
    @Override
    public void saveRequest(String username, SearchRequest request) {
        Person person = find(username);
        if (person != null) {
            person.setSearchRequest(request);
        }
    }
    
    @Override
    public SearchRequest getRequest(String username) {
        Person person = find(username);
        if (person != null) {
            return person.getSearchRequest();
        }
        
        return null;
    }
}
