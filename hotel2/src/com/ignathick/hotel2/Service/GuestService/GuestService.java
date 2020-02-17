package com.ignathick.hotel2.Service.GuestService;

import com.ignathick.hotel2.IFileUtil;
import com.ignathick.hotel2.Store.GuestStore.GuestStoreDatabaseImpl;
import com.ignathick.hotel2.Store.GuestStore.IGuestStore;
import com.ignathick.hotel2.database.IDatabase;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Comparators.GuestLastNameComparator;
import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Guest.GuestIFileUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class GuestService implements IGuestService {

    private IGuestStore GuestStore;
    private final String GUEST_DATA_FILE = "ignathick_data_guest_class.txt";
    private IFileUtil<Guest> utilGuest;
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IDatabase database = dependencyInjection.load(IDatabase.class);
    private static Logger logger = Logger.getLogger(GuestService.class);

    public GuestService() {
        init();
    }

    private void init(){
        this.GuestStore = new GuestStoreDatabaseImpl();
        this.utilGuest = new GuestIFileUtil(GUEST_DATA_FILE);
    }


    @Override
    public void addGuest(Guest guest) {
        this.GuestStore.save(guest);
    }

    @Override
    public Guest getGuest(Long id) {
        return this.GuestStore.get(id);
    }

    @Override
    public void deleteGuest(Guest guest) {
        this.GuestStore.delete(guest);
    }

    @Override
    public void updateGuest(Guest guest) {
        Connection connection = database.getConnection();
        try {
            connection.setAutoCommit(false);
            this.GuestStore.update(guest);
        } catch (Exception ex) {
            logger.error("Error in SQL guest update transaction: " + ex);

        }

    }

    @Override
    public List<Guest> getGuestsList() {
        return this.GuestStore.getAll();
    }

    @Override
    public Integer getGuestsNumber() {
        return this.GuestStore.getAll().size();
    }

    @Override
    public void removeAllGuests() {

        this.GuestStore.deleteAll();

    }

    @Override
    public void saveToFile() {
        utilGuest.writeToFile(this.GuestStore.getAll());
    }

    @Override
    public void readFromFile() {

        removeAllGuests();
        for (Guest guest :
                this.utilGuest.readFromFile()) {
            this.GuestStore.save(guest);
        }

    }

    @Override
    public List<Guest> sortByGuestName() {

        GuestLastNameComparator guestLastNameComparator = new GuestLastNameComparator();
        List<Guest> guests = getGuestsList();
        Collections.sort(guests, guestLastNameComparator);
        return guests;

    }

    @Override
    public void setPath(String path) {
        this.utilGuest = new GuestIFileUtil(path);
    }
}
