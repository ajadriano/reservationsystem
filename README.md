# reservationsystem

How to run
1. Open in Netbeans IDE with a Glass fish server installation
2. Build project
3. Run

To create/delete flights
1. Login as administrator. Username and password is 'administrator'
2. administrator can create and delete flights, and view inventory

To book flights
1. User does not need login to search for flights.
2. User will be asked to login/register upon reserving a flight

System Design
The design of the system, and how the files are organized, is based on the Cargo Tracker System
- All entity classes are stored in src/main/java/domain
- Flight entities are in src/main/java/domain/flight
- Reservation entities are in src/main/java/domain/reservation
- User entities are in src/main/java/domain/user
- Interfaces for the entity repositories are located in each of these folders
- Implementation of the JPA repositories are at src/main/java/persistence
- Business tier interfaces and implementations are at src/main/java/application
- Managed beans, facades and DTOs are at src/main/java/interface
- web application is at src/main/java/webapp

Important Entities
Flight - represents a flight created by the reservation manager/administrator
FlightInstance - an instance of a flight. A Flight entity has day of week and time information, a FlightInstance
  has the actual date of departure and arrival. Flight Instance is not an entity but more of a DTO for showing
  itinerary options
FlightLeg - once a user selected a FlightInstance for its booking, it will be converted to an entity called
  FlightLeg, to be saved in the database
Reservation - An entity composed of an array of outbound FlightLeg entities and inbound FlightLeg entities.


Application services
FlightService
  - Responsible for creating and deleting flights
  - View inventory
  - Get itinerary options given the departure and arrival airport, date of departure, flight class, number of seats
  
ReservationService
  - Responsible for retrieving the user's reserved, confirmed and cancelled bookings
  - Creating a reservation, and confirming or cancelling a specific reservation
  - Cancel all reservations that has a flight specified by a flight number
  - Generate a ticket number

UserService
  - Create a user
  - Validation of username and password
  - saving of last search details
  
ManagedBeans
Session Scoped Beans
UserSession - for storing the status of the user, if the user is an administrator or not, or if
  there is a user logged in. These information need to be stored until the http session is alive,
  hence it is stored in a session scope bean
  
Flow Scoped Beans
ReservationBackingBean - for storing the state while the user is in the process of creating a reservation.
The process will be going through several pages (in src/main/java/webapp/reservation), state such as the
the selected inbound and outbound flight needs to be active during the duration of the process, so the bean
was marked as flow scoped

All other beans are viewscoped beans due to ajax use, to minimise creating a new instance due to an ajax 
request.

Website design is also based on the Cargo Tracker design, using the same CSS files and themes
https://java.net/projects/cargotracker/pages/Home




