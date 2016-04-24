/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.flight;

import application.util.DateUtil;
import java.time.DayOfWeek;
import java.util.Date;

/**
 *
 * @author ajadriano
 */
public class SampleFlights {
    public static final Flight MNLSIN01 = new Flight(
            new FlightNumber(SampleAirlines.Philippines, "123"),
            SampleAirports.Philippines,
            DateUtil.toDate("1900-01-01", "09:00"),
            DayOfWeek.SATURDAY,
            SampleAirports.Singapore,
            DateUtil.toDate("1900-01-01", "12:30"),
            DayOfWeek.SATURDAY,     
            50.0, 10.0);
    public static final Flight SINMNL01 = new Flight(
            new FlightNumber(SampleAirlines.Philippines, "231"),
            SampleAirports.Singapore,
            DateUtil.toDate("1900-01-01", "18:00"),
            DayOfWeek.THURSDAY,
            SampleAirports.Philippines,
            DateUtil.toDate("1900-01-01", "21:30"),
            DayOfWeek.THURSDAY,     
            50.0, 10.0);
    public static final Flight LHRJFK01 = new Flight(
            new FlightNumber(SampleAirlines.England, "111"),
            SampleAirports.England,
            DateUtil.toDate("1900-01-01", "12:00"),
            DayOfWeek.WEDNESDAY,
            SampleAirports.UnitedStates,
            DateUtil.toDate("1900-01-01", "19:30"),
            DayOfWeek.WEDNESDAY,     
            50.0, 10.0);
    public static final Flight JFKBOG01 = new Flight(
            new FlightNumber(SampleAirlines.UnitedStates, "002"),
            SampleAirports.UnitedStates,
            DateUtil.toDate("1900-01-01", "21:45"),
            DayOfWeek.WEDNESDAY,
            SampleAirports.Colombia,
            DateUtil.toDate("1900-01-01", "03:45"),
            DayOfWeek.THURSDAY,     
            50.0, 10.0);
    public static final Flight BOGJFK01 = new Flight(
            new FlightNumber(SampleAirlines.Colombia, "521"),
            SampleAirports.Colombia,
            DateUtil.toDate("1900-01-01", "12:20"),
            DayOfWeek.WEDNESDAY,
            SampleAirports.UnitedStates,
            DateUtil.toDate("1900-01-01", "18:30"),
            DayOfWeek.WEDNESDAY,     
            50.0, 10.0);
    public static final Flight JFKLHR01 = new Flight(
            new FlightNumber(SampleAirlines.UnitedStates, "321"),
            SampleAirports.UnitedStates,
            DateUtil.toDate("1900-01-01", "20:00"),
            DayOfWeek.WEDNESDAY,
            SampleAirports.England,
            DateUtil.toDate("1900-01-01", "02:10"),
            DayOfWeek.THURSDAY,     
            50.0, 10.0);
    public static final Flight LHRNRT01 = new Flight(
            new FlightNumber(SampleAirlines.Japan, "261"),
            SampleAirports.England,
            DateUtil.toDate("1900-01-01", "07:00"),
            DayOfWeek.SUNDAY,
            SampleAirports.Japan,
            DateUtil.toDate("1900-01-01", "20:10"),
            DayOfWeek.SUNDAY,     
            1000.0, 500.0);
    public static final Flight NRTLHR01 = new Flight(
            new FlightNumber(SampleAirlines.Japan, "631"),
            SampleAirports.Japan,
            DateUtil.toDate("1900-01-01", "21:00"),
            DayOfWeek.SUNDAY,
            SampleAirports.England,
            DateUtil.toDate("1900-01-01", "15:10"),
            DayOfWeek.MONDAY,     
            1000.0, 500.0);
    public static final Flight LHRNRT02 = new Flight(
            new FlightNumber(SampleAirlines.England, "529"),
            SampleAirports.England,
            DateUtil.toDate("1900-01-01", "07:00"),
            DayOfWeek.SUNDAY,
            SampleAirports.Japan,
            DateUtil.toDate("1900-01-01", "20:10"),
            DayOfWeek.SUNDAY,     
            1200.0, 600.0);
    public static final Flight NRTLHR02 = new Flight(
            new FlightNumber(SampleAirlines.England, "421"),
            SampleAirports.Japan,
            DateUtil.toDate("1900-01-01", "21:00"),
            DayOfWeek.SUNDAY,
            SampleAirports.England,
            DateUtil.toDate("1900-01-01", "15:10"),
            DayOfWeek.MONDAY,     
            1200.0, 600.0);
}
