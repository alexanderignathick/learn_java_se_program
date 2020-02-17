package com.ignathick.hotel2.Store.RoomStore;

import com.ignathick.hotel2.Store.Dao;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.model.Room.Status;

public interface IRoomStore extends Dao<Room> {
    public void updateRoomStatus(Long id, Status status);
}
