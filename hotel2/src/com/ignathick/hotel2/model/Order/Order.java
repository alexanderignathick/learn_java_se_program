package com.ignathick.hotel2.model.Order;

import com.ignathick.hotel2.DateUtil;
import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Room.Room;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements IOrder, Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Guest guest;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private Double orderPrice;

    public Order() {
    }

    public Order(Long id, Guest guest, Room room, Date dateFrom, Date dateTo, Double orderPrice) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.orderPrice = orderPrice;
    }

    public Order(Long id, Guest guest, Room room, String dateFrom, String dateTo, Double orderPrice) {
        this(id, guest, room, DateUtil.getDateFromString(dateFrom), DateUtil.getDateFromString(dateTo), orderPrice);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Room getRoom() {
        return room;
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", guest=" + guest +
                ", room=" + room +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", orderPrice=" + orderPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) &&
                Objects.equals(guest, order.guest) &&
                Objects.equals(room, order.room) &&
                Objects.equals(dateFrom, order.dateFrom) &&
                Objects.equals(dateTo, order.dateTo) &&
                Objects.equals(orderPrice, order.orderPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guest, room, dateFrom, dateTo, orderPrice);
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

}
