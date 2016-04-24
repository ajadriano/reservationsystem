/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.flight;

import java.util.List;

/**
 *
 * @author ajadriano
 */
public interface AirportRepository {
    
    Airport find(AirportCode airportCode);

    List<Airport> findAll();
    
}
