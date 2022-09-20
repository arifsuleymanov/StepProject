package project.dao.entity;

import java.io.Serializable;

public class Passenger implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String firstName;
    private final String lastName;

    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
