package com.ignathick.hotel2.model.Guest;

import com.ignathick.hotel2.DateUtil;
import com.ignathick.hotel2.model.Room.Room;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Guest implements IGuest, Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String passport;
    private Room room;

    public Guest(Long id, String firstName, String lastName, Date dateOfBirth, String passport) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.passport = passport;
    }

    public Guest(Long id, String firstName, String lastName, Date dateOfBirth, String passport, Room room) {
       this(id, firstName, lastName, dateOfBirth,passport);
       this.room = room;
    }

    public Guest() {
    }

    public Guest(Long id, String firstName, String lastName, String dateOfBirth, String passport) {
        this(id, firstName, lastName, DateUtil.getDateFromString(dateOfBirth), passport);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guest guest = (Guest) o;
        return id.equals(guest.id) &&
                Objects.equals(firstName, guest.firstName) &&
                Objects.equals(lastName, guest.lastName) &&
                Objects.equals(dateOfBirth, guest.dateOfBirth) &&
                Objects.equals(passport, guest.passport) &&
                Objects.equals(room, guest.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, dateOfBirth, passport, room);
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passport='" + passport + '\'' +
                ", room=" + room +
                '}';
    }

}
