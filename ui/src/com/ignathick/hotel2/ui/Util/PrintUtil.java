package com.ignathick.hotel2.ui.Util;

import com.ignathick.hotel2.DateUtil;
import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Order.Order;
import com.ignathick.hotel2.model.Room.Room;

import java.util.List;

public class PrintUtil {

    public static String getRoomString(Room room){
        String roomInfo = "Room id: "+room.getId() + ";\t"
                +"number: " + room.getNumber() + ";\t"
                + "capasity: " + room.getCapacity() + ";\t"
                + "status: " + room.getStatus() + ";\t"
                + "stat: " + room.getStar() + ";\t"
                + "from: " + DateUtil.getDateAsString(room.getBusyFrom()) + ";\t"
                + "to: " + DateUtil.getDateAsString(room.getBusyTo()) + ";\t"
                + "price:" + room.getPrice() + "";
        return roomInfo;
    }

    public static void printRommsList(List<Room> roomList) {
        for (Room room:
                roomList) {
            System.out.println(getRoomString(room));
        }
    }

    public static void printRoom(Room room){
        System.out.println(getRoomString(room));
    }

    public static String getGuestString (Guest guest) {
        String guestInfo = "Guest id: "+guest.getId() + ";\t"
                +"first name: " + guest.getFirstName() + ";\t"
                + "last name: " + guest.getLastName() + ";\t"
                + "date of birth: " + DateUtil.getDateAsString(guest.getDateOfBirth()) + ";\t"
                + "passport: " + guest.getPassport() + ";\t"
                + "room : " + guest.getRoom() + ";\t"
                ;
        return guestInfo;
    }

    public static void printGuestsList(List<Guest> guestsList){
        for (Guest guest:
             guestsList) {
            System.out.println(getGuestString(guest));
        }
    }

    public static String getOrderString(Order order) {

        String orderInfo = "Order id: "+order.getId() + ";\t"
                +"guest: " + order.getGuest().getFirstName()+ " " + order.getGuest().getLastName() + ";\t"
                + "room #: " + order.getRoom().getNumber() + ";\t"
                + "date from: " + DateUtil.getDateAsString(order.getDateFrom()) + ";\t"
                + "date to: " + DateUtil.getDateAsString(order.getDateTo()) + ";\t"
                + "price : " + order.getOrderPrice() + ";\t"
                ;
        return orderInfo;

    }

    public static void printOrdersList(List<Order> allOrders) {
        for (Order order:
             allOrders) {
            System.out.println(getOrderString(order));
        }
    }
}
