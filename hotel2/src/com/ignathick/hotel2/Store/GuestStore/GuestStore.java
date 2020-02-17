package com.ignathick.hotel2.Store.GuestStore;

import com.ignathick.hotel2.Store.Dao;
import com.ignathick.hotel2.model.Guest.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestStore implements Dao<Guest>, IGuestStore {
    private List<Guest> guests = new ArrayList<Guest>();

    @Override
    public Guest get(Long id) {
        for (Guest guest:
             guests) {
            if(guest.getId() == id) {
                return guest;
            }
        }
        return null;
    }

    @Override
    public List<Guest> getAll() {
        return this.guests;
    }

    @Override
    public void save(Guest guest) {
        this.guests.add(guest);
    }

    @Override
    public void update(Guest guest) {

        this.guests.set(this.guests.indexOf(guest), guest);

    }

    @Override
    public void delete(Guest guest) {
        this.guests.remove(guest);
    }

    @Override
    public void deleteAll() {
        this.guests.clear();
    }
}
