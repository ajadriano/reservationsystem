/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces.reservation.facade.dto;

import java.util.List;

/**
 *
 * @author ajadriano
 */
public class ItineraryDto {
    private int index;
    private final List<LegDto> legs;
    private final double cost;
    
    public ItineraryDto(int index,
            List<LegDto> legs,
            double cost) {
        this.index = index;
        this.legs = legs;
        this.cost = cost;
    }

    /**
     * @return the legs
     */
    public List<LegDto> getLegs() {
        return legs;
    }

    /**
     * @return the cost
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }
}
