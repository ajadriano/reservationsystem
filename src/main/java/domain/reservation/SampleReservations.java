/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.reservation;

import application.util.DateUtil;
import domain.flight.FlightNumber;
import domain.flight.SampleAirlines;
import domain.flight.SampleAirports;
import domain.user.SamplePersons;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author ajadriano
 */
public class SampleReservations {
    public static final FlightLeg FlightLeg1User1 = new FlightLeg("PR-123-AJADRI-001",
        new FlightNumber(SampleAirlines.Philippines, "123"),
        SampleAirports.Philippines,
        DateUtil.toDate("2016-04-30","09:00"),
        SampleAirports.Singapore,
        DateUtil.toDate("2016-04-30","12:30"),
        FlightClass.ECONOMY,
        1,
        ReservationStatus.RESERVED,
        100,
        SamplePersons.User1.getUsername());
    
    public static final FlightLeg FlightLeg2User1 = new FlightLeg("PR-123-AJADRI-001",
        new FlightNumber(SampleAirlines.Philippines, "231"),
        SampleAirports.Singapore,
        DateUtil.toDate("2016-05-05","18:00"),
        SampleAirports.Philippines,
        DateUtil.toDate("2016-05-05","21:30"),
        FlightClass.ECONOMY,
        1,
        ReservationStatus.RESERVED,
        100,
        SamplePersons.User1.getUsername());
    
    public static final FlightLeg FlightLeg1Administrator = new FlightLeg("PR-123-AJADRI-001",
        new FlightNumber(SampleAirlines.Philippines, "123"),
        SampleAirports.Philippines,
        DateUtil.toDate("2016-04-30","09:00"),
        SampleAirports.Singapore,
        DateUtil.toDate("2016-04-30","12:30"),
        FlightClass.ECONOMY,
        1,
        ReservationStatus.RESERVED,
        100,
        SamplePersons.Administrator.getUsername());
    
    public static final FlightLeg FlightLeg2Administrator = new FlightLeg("PR-123-AJADRI-001",
        new FlightNumber(SampleAirlines.Philippines, "231"),
        SampleAirports.Singapore,
        DateUtil.toDate("2016-05-05","18:00"),
        SampleAirports.Philippines,
        DateUtil.toDate("2016-05-05","21:30"),
        FlightClass.ECONOMY,
        1,
        ReservationStatus.RESERVED,
        100,
        SamplePersons.Administrator.getUsername());
    
    public static final Reservation Reservation1 = 
            new Reservation("PR-123-AJADRI-001",
                    SamplePersons.User1,
                    new ArrayList<>(Arrays.asList(FlightLeg1User1)),
                    new ArrayList<>(Arrays.asList(FlightLeg2User1)),
                    new Date(),
                    ReservationStatus.RESERVED);
    public static final Reservation Reservation2 = 
            new Reservation("PR-123-AJADRI-002",
                    SamplePersons.Administrator,
                    new ArrayList<>(Arrays.asList(FlightLeg1Administrator)),
                    new ArrayList<>(Arrays.asList(FlightLeg2Administrator)),
                    new Date(),
                    ReservationStatus.RESERVED);
    
    public static final FlightLeg FlightLeg3User1 = new FlightLeg("BA-111-AJADRI-001",
        new FlightNumber(SampleAirlines.England, "111"),
        SampleAirports.England,
        DateUtil.toDate("2016-06-01","12:00"),
        SampleAirports.UnitedStates,
        DateUtil.toDate("2016-06-01","19:30"),
        FlightClass.BUSINESS,
        1,
        ReservationStatus.BOOKED,
        100,
        SamplePersons.User1.getUsername());
    
    public static final FlightLeg FlightLeg4User1 = new FlightLeg("BA-111-AJADRI-001",
        new FlightNumber(SampleAirlines.UnitedStates, "002"),
        SampleAirports.UnitedStates,
        DateUtil.toDate("2016-06-01","21:15"),
        SampleAirports.Colombia,
        DateUtil.toDate("2016-06-02","03:45"),
        FlightClass.BUSINESS,
        1,
        ReservationStatus.BOOKED,
        100,
        SamplePersons.User1.getUsername());
    
    public static final FlightLeg FlightLeg5User1 = new FlightLeg("BA-111-AJADRI-001",
        new FlightNumber(SampleAirlines.Colombia, "521"),
        SampleAirports.Colombia,
        DateUtil.toDate("2016-06-15","12:20"),
        SampleAirports.UnitedStates,
        DateUtil.toDate("2016-06-15","18:30"),
        FlightClass.BUSINESS,
        1,
        ReservationStatus.BOOKED,
        100,
        SamplePersons.User1.getUsername());
    
    public static final FlightLeg FlightLeg6User1 = new FlightLeg("BA-111-AJADRI-001",
        new FlightNumber(SampleAirlines.UnitedStates, "321"),
        SampleAirports.UnitedStates,
        DateUtil.toDate("2016-06-15","20:00"),
        SampleAirports.England,
        DateUtil.toDate("2016-06-16","02:10"),
        FlightClass.BUSINESS,
        1,
        ReservationStatus.BOOKED,
        100,
        SamplePersons.User1.getUsername());
    
    public static final Reservation Reservation3 = 
            new Reservation("BA-111-AJADRI-001",
                    SamplePersons.User1,
                    new ArrayList<>(Arrays.asList(FlightLeg3User1, FlightLeg4User1)),
                    new ArrayList<>(Arrays.asList(FlightLeg5User1, FlightLeg6User1)),
                    new Date(),
                    ReservationStatus.BOOKED);
    
    public static final FlightLeg FlightLeg3Administrator = new FlightLeg("BA-111-AJADRI-001",
        new FlightNumber(SampleAirlines.England, "111"),
        SampleAirports.England,
        DateUtil.toDate("2016-06-01","12:00"),
        SampleAirports.UnitedStates,
        DateUtil.toDate("2016-06-01","19:30"),
        FlightClass.BUSINESS,
        1,
        ReservationStatus.BOOKED,
        100,
        SamplePersons.Administrator.getUsername());
    
    public static final FlightLeg FlightLeg4Administrator = new FlightLeg("BA-111-AJADRI-001",
        new FlightNumber(SampleAirlines.UnitedStates, "002"),
        SampleAirports.UnitedStates,
        DateUtil.toDate("2016-06-01","21:15"),
        SampleAirports.Colombia,
        DateUtil.toDate("2016-06-02","03:45"),
        FlightClass.BUSINESS,
        1,
        ReservationStatus.BOOKED,
        100,
        SamplePersons.Administrator.getUsername());
    
    public static final FlightLeg FlightLeg5Administrator = new FlightLeg("BA-111-AJADRI-001",
        new FlightNumber(SampleAirlines.Colombia, "521"),
        SampleAirports.Colombia,
        DateUtil.toDate("2016-06-15","12:20"),
        SampleAirports.UnitedStates,
        DateUtil.toDate("2016-06-15","18:30"),
        FlightClass.BUSINESS,
        1,
        ReservationStatus.BOOKED,
        100,
        SamplePersons.Administrator.getUsername());
    
    public static final FlightLeg FlightLeg6Administrator = new FlightLeg("BA-111-AJADRI-001",
        new FlightNumber(SampleAirlines.UnitedStates, "321"),
        SampleAirports.UnitedStates,
        DateUtil.toDate("2016-06-15","20:00"),
        SampleAirports.England,
        DateUtil.toDate("2016-06-16","02:10"),
        FlightClass.BUSINESS,
        1,
        ReservationStatus.BOOKED,
        100,
        SamplePersons.Administrator.getUsername());
    
    public static final Reservation Reservation4 = 
            new Reservation("BA-111-AJADRI-002",
                    SamplePersons.Administrator,
                    new ArrayList<>(Arrays.asList(FlightLeg3Administrator, FlightLeg4Administrator)),
                    new ArrayList<>(Arrays.asList(FlightLeg5Administrator, FlightLeg6Administrator)),
                    new Date(),
                    ReservationStatus.BOOKED);
}
