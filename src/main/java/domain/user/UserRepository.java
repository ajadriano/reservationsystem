/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.user;

import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface UserRepository {

    Person find(String username);

    List<Person> findAll();
    
    void store(Person user);
    
    void saveRequest(String username, SearchRequest request);
    
    SearchRequest getRequest(String username);
}
