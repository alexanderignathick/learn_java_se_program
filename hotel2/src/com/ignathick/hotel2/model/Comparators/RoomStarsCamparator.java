package com.ignathick.hotel2.model.Comparators;

import com.ignathick.hotel2.model.Room.Room;

import java.util.Comparator;

public class RoomStarsCamparator implements Comparator<Room> {

    @Override
    public int compare(Room room1, Room room2) {
        return room1.getStar().getStarValue() - room2.getStar().getStarValue();
    }
}
