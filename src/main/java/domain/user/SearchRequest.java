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
public class SearchRequest implements Serializable {
    @Column(name = "from_airport")
    private String fromAirport;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "from_date")
    private Date fromDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "from_time")
    private Date fromTime;
    
    @Column(name = "to_airport")
    private String toAirport;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "to_date")
    private Date toDate; 
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "to_time")
    private Date toTime;
    
    @Column(name = "flight_type")
    private String flightType;
    
    @Column(name = "passenger_count")
    private int passengerCount;
    
    @Column(name = "stops")
    private int stops;
    
    @Column(name = "flight_class")
    private String flightClass;
    
    public SearchRequest() {
        
    }
    
    public SearchRequest(String fromAirport,
                Date fromDate,
                Date fromTime,
                String toAirport,
                Date toDate,
                Date toTime,
                String flightType,
                int passengerCount,
                int stops,
                String flightClass) {
        this.fromAirport = fromAirport;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toAirport = toAirport;
        this.toDate = toDate;
        this.toTime = toTime;
        this.flightType = flightType;
        this.passengerCount = passengerCount;
        this.stops = stops;
        this.flightClass = flightClass;
    }

    /**
     * @return the fromAirport
     */
    public String getFromAirport() {
        return fromAirport;
    }

    /**
     * @param fromAirport the fromAirport to set
     */
    public void setFromAirport(String fromAirport) {
        this.fromAirport = fromAirport;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the fromTime
     */
    public Date getFromTime() {
        return fromTime;
    }

    /**
     * @param fromTime the fromTime to set
     */
    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    /**
     * @return the toAirport
     */
    public String getToAirport() {
        return toAirport;
    }

    /**
     * @param toAirport the toAirport to set
     */
    public void setToAirport(String toAirport) {
        this.toAirport = toAirport;
    }

    /**
     * @return the toDate
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * @param toDate the toDate to set
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * @return the toTime
     */
    public Date getToTime() {
        return toTime;
    }

    /**
     * @param toTime the toTime to set
     */
    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    /**
     * @return the flightType
     */
    public String getFlightType() {
        return flightType;
    }

    /**
     * @param flightType the flightType to set
     */
    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    /**
     * @return the passengerCount
     */
    public int getPassengerCount() {
        return passengerCount;
    }

    /**
     * @param passengerCount the passengerCount to set
     */
    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    /**
     * @return the stops
     */
    public int getStops() {
        return stops;
    }

    /**
     * @param stops the stops to set
     */
    public void setStops(int stops) {
        this.stops = stops;
    }

    /**
     * @return the flightClass
     */
    public String getFlightClass() {
        return flightClass;
    }

    /**
     * @param flightClass the flightClass to set
     */
    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
}
