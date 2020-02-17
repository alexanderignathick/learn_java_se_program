package com.ignathick.hotel2.ui.Wrapper;

import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Order.Order;
import com.ignathick.hotel2.model.Room.Room;

import java.io.Serializable;
import java.util.List;

public class Wrapper implements Serializable {
    private static long serialVersionUID = 1L;
    private List<Room> roomlist;
    private List<Guest> guestListList;
    private List<Order> orderslist;

    public Wrapper(List<Room> roomlist, List<Guest> guestListList, List<Order> orderslist) {
        this.roomlist = roomlist;
        this.guestListList = guestListList;
        this.orderslist = orderslist;
    }

    public List<Room> getRoomlist() {
        return roomlist;
    }

    public void setRoomlist(List<Room> roomlist) {
        this.roomlist = roomlist;
    }

    public List<Guest> getGuestListList() {
        return guestListList;
    }

    public void setGuestListList(List<Guest> guestListList) {
        this.guestListList = guestListList;
    }

    public List<Order> getOrderslist() {
        return orderslist;
    }

    public void setOrderslist(List<Order> orderslist) {
        this.orderslist = orderslist;
    }

}
