package com.ignathick.hotel2.model.Room;

import com.ignathick.hotel2.DateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Room implements IRoom, Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Integer number;
    private Integer capacity;
    private Double price;
    private Star star;
    private Status status;
    private Date busyFrom;
    private Date busyTo;

    public Room() {
    }

    public Room(Long id, Integer number, Integer capacity, Double price, Star star, Status status) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.price = price;
        this.star = star;
        this.status = status;
    }

    public Room(Long id, Integer number, Integer capacity, Double price, Star star, Status status, Date busyFrom, Date busyTo) {
        this(id, number, capacity, price, star, status);
        this.busyFrom = busyFrom;
        this.busyTo = busyTo;
    }

    public Room(Long id, Integer number, Integer capacity, Double price, Star star, Status status, String busyFrom, String busyTo) {
        this(id, number, capacity, price, star, status);
        this.busyFrom = DateUtil.getDateFromString(busyFrom);
        this.busyTo = DateUtil.getDateFromString(busyTo);
    }

    public Room(Long id, Integer number, Integer capacity, Double price, String star, String status, Date busyfrom, Date busyto) {

        Star star1 = Star.valueOf(star.toUpperCase());
        Status status1 = Status.valueOf(status.toUpperCase());
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.price = price;
        this.star = star1;
        this.status = status1;
        this.busyFrom = busyfrom;
        this.busyTo = busyto;

    }

    @Override
    public Room clone(){
        try {
            Room room = (Room) super.clone();
            return  room;
        } catch (CloneNotSupportedException  ex) {
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capasity) {
        this.capacity = capasity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Star getStar() {
        return star;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getBusyFrom() {
        return busyFrom;
    }

    public void setBusyFrom(Date busyFrom) {
        this.busyFrom = busyFrom;
    }

    public Date getBusyTo() {
        return busyTo;
    }

    public void setBusyTo(Date busyTo) {
        this.busyTo = busyTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id) &&
                Objects.equals(number, room.number) &&
                Objects.equals(capacity, room.capacity) &&
                Objects.equals(price, room.price) &&
                star == room.star &&
                status == room.status &&
                Objects.equals(busyFrom, room.busyFrom) &&
                Objects.equals(busyTo, room.busyTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, capacity, price, star, status, busyFrom, busyTo);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", capasity=" + capacity +
                ", price=" + price +
                ", star=" + star +
                ", status=" + status +
                ", busyFrom=" + busyFrom +
                ", busyTo=" + busyTo +
                '}';
    }

}
