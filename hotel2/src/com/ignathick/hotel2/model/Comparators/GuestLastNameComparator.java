package com.ignathick.hotel2.model.Comparators;

import com.ignathick.hotel2.model.Guest.Guest;

import java.util.Comparator;

public class GuestLastNameComparator implements Comparator<Guest> {
    @Override
    public int compare(Guest o1, Guest o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}
