package com.ignathick.hotel2.Service.RoomService;

import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.model.Room.Status;

import java.util.Date;
import java.util.List;

public interface IRoomService {
    //CRUD
    public void addRoom(Room room);
    public Room getRoom(Long id);
    public void deleteRoom(Room room);
    public void updateRoom(Room room);
    public void updateRoomStatus(long id, Status status);

    public List<Room> getFreeRooms();
    public Integer getNumberOfFreeRooms();
    public List<Room> getFreeRoomsListFromDate(Date date);
    public void removeAllRooms();
    public void saveToFile();
    public void readFromFile();
    public List<Room> getAllRooms();

    //Soft
    public List<Room> sortByPrice(List<Room> rooms);
    public List<Room> sortByCapasity(List<Room> rooms);
    public List<Room> sortByStart(List<Room> rooms);

    public void setPath(String path);

}
