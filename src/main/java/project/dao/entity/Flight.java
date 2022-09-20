package project.dao.entity;

import project.dao.interfaces.Identifiable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight implements Serializable, Identifiable {
    private static final long serialVersionUID = 1L;
    static long counter = 0;

    private final long id;
    private final String cityFrom;
    private final String cityTo;
    private final LocalDateTime date;
    private int seats;

    public Flight(String cityFrom, String cityTo, LocalDateTime date, int seats) {
        this(++counter, cityFrom, cityTo, date, seats);
    }

    public Flight(long id, String cityFrom, String cityTo, LocalDateTime date, int seats) {
        this.id = id;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.date = date;
        this.seats = seats;
    }

    public static void setCounter(long counter) {
        Flight.counter = counter;
    }

    @Override
    public long getId() {
        return id;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String display() {
        return String.format("" +
                        "||=================================================================||\n" +
                        "||ID:%3d | %-10s -> %-10s : %10s : Seats: %3d||\n" +
                        "||_________________________________________________________________||\n",
                id,
                cityFrom,
                cityTo,
                date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm")),
                seats
        );
    }
}
