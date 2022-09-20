package project.dao.entity;

import project.dao.interfaces.Identifiable;

import java.io.Serializable;
import java.util.List;

public class Booking implements Serializable, Identifiable {
    private static final long serialVersionUID = 1L;
    static long counter = 0;

    private long id;
    private long flightId;
    private long userId;
    private List<Passenger> passengers;

    public Booking(long flightId, long userId, List<Passenger> passengers) {
        this(++counter, flightId, userId, passengers);
    }

    public Booking(long id, long user_id, long flight_id, List<Passenger> passengers) {
        this.id = id;
        this.userId = user_id;
        this.flightId = flight_id;
        this.passengers = passengers;
    }

    @Override
    public long getId() {
        return id;
    }

    public long getFlightId() {
        return flightId;
    }

    public long getUserId() {
        return userId;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public String display() {
        StringBuilder sb = new StringBuilder();
        sb.append("Passengers:\n");
        for (Passenger p : passengers) {
            sb.append(p.getFirstName()).append(" ").append(p.getLastName()).append("\n");
        }
        return sb.toString();
    }
}
