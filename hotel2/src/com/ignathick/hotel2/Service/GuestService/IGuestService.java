package com.ignathick.hotel2.Service.GuestService;

import com.ignathick.hotel2.model.Guest.Guest;

import java.util.List;

public interface IGuestService {
    //CRUD
    public void addGuest(Guest guest);
    public Guest getGuest(Long id);
    public void deleteGuest(Guest guest);
    public void updateGuest(Guest guest);

    public List<Guest> getGuestsList();
    public Integer getGuestsNumber();
    public void removeAllGuests();

    public void saveToFile();
    public void readFromFile();

    //Sort
    public List<Guest> sortByGuestName();

    public void setPath(String path);


}
