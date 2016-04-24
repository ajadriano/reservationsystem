/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.flight;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajadriano
 */
public class Itinerary {
    private final List<FlightInstance> flights;
    
    public Itinerary () {
        flights = new ArrayList<>();
    }
    
    public Itinerary(List<FlightInstance> flights) {
        this.flights = flights;
    }

    /**
     * @return the flights
     */
    public List<FlightInstance> getFlights() {
        return flights;
    }
}
