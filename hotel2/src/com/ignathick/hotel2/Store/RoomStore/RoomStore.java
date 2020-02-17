package com.ignathick.hotel2.Store.RoomStore;

import com.ignathick.hotel2.Store.Dao;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.model.Room.Status;

import java.util.ArrayList;
import java.util.List;

public class RoomStore implements Dao<Room>, IRoomStore {

    private List<Room> rooms = new ArrayList<>();

    @Override
    public Room get(Long id) {
        for (Room room:
             this.rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        return null;
    }

    @Override
    public List<Room> getAll() {
        return this.rooms;
    }

    @Override
    public void save(Room room) {
        room.setId(Long.valueOf(this.rooms.size()));
        this.rooms.add(room);
    }

    @Override
    public void update(Room room) {
        this.rooms.set( rooms.indexOf(room) , room);
    }

    @Override
    public void delete(Room room) {
        this.rooms.remove(room);
    }

    @Override
    public void deleteAll() {
        this.rooms.clear();
    }

    @Override
    public void updateRoomStatus(Long id, Status status) {

        Room room = get(id);
        room.setStatus(status);

    }
}
