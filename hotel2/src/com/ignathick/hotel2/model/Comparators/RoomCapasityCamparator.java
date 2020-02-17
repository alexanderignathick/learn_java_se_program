package com.ignathick.hotel2.model.Comparators;

import com.ignathick.hotel2.model.Room.Room;

import java.util.Comparator;

public class RoomCapasityCamparator implements Comparator<Room> {

    @Override
    public int compare(Room room1, Room room2) {
        return (int) (room1.getCapacity() - room2.getCapacity());
    }
}
