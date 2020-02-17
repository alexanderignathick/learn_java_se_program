package com.ignathick.hotel2.Facade;

import com.ignathick.hotel2.Service.GuestService.GuestService;
import com.ignathick.hotel2.Service.GuestService.IGuestService;
import com.ignathick.hotel2.Service.OrderService.IOrderService;
import com.ignathick.hotel2.Service.OrderService.OrderService;
import com.ignathick.hotel2.Service.RoomService.IRoomService;
import com.ignathick.hotel2.Service.RoomService.RoomService;
import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Order.Order;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.model.Room.Status;

import java.util.Date;
import java.util.List;

public class Facade implements IFacade {

//    private static volatile Facade instance;
//
//    private Facade() {
//    }
//
//    public static Facade getInstance() {
//        if (instance == null)
//            synchronized (Facade.class) {
//                if (instance == null)
//                    instance = new Facade();
//            }
//        return instance;
//    }

    private IRoomService roomService = new RoomService();
    private IGuestService guestService = new GuestService();
    private IOrderService orderService = new OrderService();

    @Override
    public void addRoom(Room room) {
        roomService.addRoom(room);
    }

    @Override
    public void writeRoomsToData() {
        roomService.saveToFile();
    }

    @Override
    public void readRoomsFromData() {
        roomService.readFromFile();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @Override
    public List<Room> getFreeRooms() {
        return this.roomService.getFreeRooms();
    }

    @Override
    public Integer getNumberOfFreeRooms() {
        return this.roomService.getNumberOfFreeRooms();
    }

    @Override
    public List<Room> getFreeRoomsListFromDate(Date date) {
        return this.roomService.getFreeRoomsListFromDate(date);
    }

    @Override
    public void updateRoomStatus(Long id, Status status) {
        this.roomService.updateRoomStatus(id, status);
    }

    @Override
    public Room getRoomByID(Long id) {
        return roomService.getRoom(id);
    }

    @Override
    public void addGuest(Guest guest) {
        this.guestService.addGuest(guest);
    }

    @Override
    public void writeGuestsToData() {
        this.guestService.saveToFile();
    }

    @Override
    public void readGuestsFromData() {
        this.guestService.readFromFile();
    }

    @Override
    public List<Guest> getAllGuests() {
        return this.guestService.getGuestsList();
    }

    @Override
    public Integer getNumberOfGuests() {
        return this.guestService.getGuestsNumber();
    }

    @Override
    public Guest getGuestByID(Long id) {
        return guestService.getGuest(id);
    }

    @Override
    public void updateRoom(Room room){
        roomService.updateRoom(room);
    }


    @Override
    public void updateGuest(Guest guest){
        guestService.updateGuest(guest);
    }


    @Override
    public void updateOrder(Order order){
        orderService.updateOrder(order);
    }

    @Override
    public List<Guest> sortByGuestName() {
        return this.guestService.sortByGuestName();
    }

    @Override
    public List<Room> sortByPrice(List<Room> rooms) {
        return this.roomService.sortByPrice(rooms);
    }

    @Override
    public List<Room> sortByCapasity(List<Room> rooms) {
        return this.roomService.sortByCapasity(rooms);
    }

    @Override
    public List<Room> sortByStart(List<Room> rooms) {
        return this.roomService.sortByStart(rooms);
    }

    @Override
    public void addOrder(Order order) {
        this.orderService.addOrder(order);
    }

    @Override
    public void deleteOrder(Order order) {
        this.orderService.deleteOrder(order);
    }

    @Override
    public void deleteGuest(Guest guest) {
        this.guestService.deleteGuest(guest);
    }

    @Override
    public void deleteRoom(Room room) {
        this.roomService.deleteRoom(room);
    }

    @Override
    public void writeOrdersToData() {
        this.orderService.saveToFile();
    }

    @Override
    public List<Order> getAllOrders() {
        return orderService.getOrdersList();
    }

    @Override
    public Order getOrderByID(Long id) {
        return orderService.getOrder(id);
    }

    @Override
    public void setRoomServicePath(String path) {
        this.roomService.setPath(path);
    }

    @Override
    public void setOrderServicePath(String path) {
        this.orderService.setPath(path);
    }

    @Override
    public void setGuestServicePath(String path) { this.guestService.setPath(path);
    }

}
