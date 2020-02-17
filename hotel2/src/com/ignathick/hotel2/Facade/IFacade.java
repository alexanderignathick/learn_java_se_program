package com.ignathick.hotel2.Facade;

import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Order.Order;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.model.Room.Status;

import java.util.Date;
import java.util.List;

public interface IFacade {

    public void addRoom(Room room);
    public void writeRoomsToData();
    public void readRoomsFromData();
    public List<Room> getAllRooms();
    public List<Room> getFreeRooms();
    public Integer getNumberOfFreeRooms();
    public List<Room> getFreeRoomsListFromDate(Date date);
    public void updateRoomStatus(Long id, Status status);
    public Room getRoomByID(Long id);


    public void addGuest(Guest guest);
    public void writeGuestsToData();
    public void readGuestsFromData();
    public List<Guest> getAllGuests();
    public Integer getNumberOfGuests();
    public Guest getGuestByID(Long id);

    void updateRoom(Room room);

    void updateGuest(Guest guest);

    void updateOrder(Order order);

    public List<Guest> sortByGuestName();
    public List<Room> sortByPrice(List<Room> rooms);
    public List<Room> sortByCapasity(List<Room> rooms);
    public List<Room> sortByStart(List<Room> rooms);

    public void addOrder(Order order);
    public void deleteOrder(Order order);

    void deleteGuest(Guest guest);

    void deleteRoom(Room room);

    public void writeOrdersToData();
    public List<Order> getAllOrders();
    public Order getOrderByID(Long id);

    public void setRoomServicePath(String path);
    public void setOrderServicePath(String path);
    public void setGuestServicePath(String path);

}
