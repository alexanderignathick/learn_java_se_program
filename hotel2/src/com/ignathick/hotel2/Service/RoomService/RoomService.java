package com.ignathick.hotel2.Service.RoomService;

import com.ignathick.hotel2.IFileUtil;
import com.ignathick.hotel2.Service.GuestService.GuestService;
import com.ignathick.hotel2.Store.RoomStore.IRoomStore;
import com.ignathick.hotel2.Store.RoomStore.RoomStore;
import com.ignathick.hotel2.Store.RoomStore.RoomStoreDatabaseImpl;
import com.ignathick.hotel2.database.IDatabase;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Comparators.RoomCapasityCamparator;
import com.ignathick.hotel2.model.Comparators.RoomPriceCamparator;
import com.ignathick.hotel2.model.Comparators.RoomStarsCamparator;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.model.Room.RoomIFileUtil;
import com.ignathick.hotel2.model.Room.Status;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class RoomService implements IRoomService {

    private IRoomStore RoomStore;
    private final String ROOM_DATA_FILE = "ignathick_data_room_class.txt";
    private IFileUtil<Room> utilRoom;
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IDatabase database = dependencyInjection.load(IDatabase.class);
    private static Logger logger = Logger.getLogger(RoomService.class);

    public RoomService() {
        init();
    }

    private void init() {
        this.RoomStore = new RoomStoreDatabaseImpl();
        this.utilRoom = new RoomIFileUtil(ROOM_DATA_FILE);
    }

    @Override
    public void addRoom(Room room) {
        RoomStore.save(room);
    }

    @Override
    public Room getRoom(Long id) {
        return this.RoomStore.get(id);
    }

    @Override
    public void deleteRoom(Room room) {
        this.RoomStore.delete(room);
    }

    @Override
    public void updateRoom(Room room) {
        Connection connection = database.getConnection();
        try {
            connection.setAutoCommit(false);
            this.RoomStore.update(room);
        } catch (Exception ex) {
            logger.error("Error in SQL guest update transaction: " + ex);

        }
    }

    @Override
    public void updateRoomStatus(long id, Status status) {
        this.RoomStore.updateRoomStatus(id, status);
    }

    @Override
    public List<Room> getFreeRooms() {
        List<Room> rooms = new ArrayList<>();
        for (Room room:
                this.RoomStore.getAll()) {
            if (room.getStatus().equals(Status.FREE)) {
                rooms.add(room);
            }
        }
        return  rooms;
    }

    @Override
    public Integer getNumberOfFreeRooms() {
        List<Room> rooms = getFreeRooms();
        return rooms.size();
    }

    @Override
    public List<Room> getFreeRoomsListFromDate(Date date) {

        List<Room> allRooms = new ArrayList<>();
        allRooms.addAll(this.RoomStore.getAll());

        List<Room> roomsNullDate = new ArrayList<>();

        for (Room room:
                allRooms) {
            if(room.getBusyTo() == null) {
                roomsNullDate.add(room);
            }
        }

        allRooms.removeAll(roomsNullDate);
        List<Room> rooms = new ArrayList<>();
        for (Room room:
                allRooms) {
            if (room.getBusyTo().before(date)) {
                rooms.add(room);
            }
        }
        return  rooms;
    }

    @Override
    public void removeAllRooms() {
        this.RoomStore.deleteAll();
    }

    @Override
    public void saveToFile() {
        utilRoom.writeToFile(this.RoomStore.getAll());
    }

    @Override
    public void readFromFile() {
        removeAllRooms();
        for (Room room :
                this.utilRoom.readFromFile()) {
            this.RoomStore.save(room);
        }
    }

    @Override
    public List<Room> getAllRooms() {
        return this.RoomStore.getAll();
    }

    @Override
    public List<Room> sortByPrice(List<Room> rooms) {
        //List<Room> rooms = getAllRooms();
        RoomPriceCamparator roomPriceCamparator = new RoomPriceCamparator();
        Collections.sort(rooms, roomPriceCamparator);
        return rooms;
    }

    @Override
    public List<Room> sortByCapasity(List<Room> rooms) {
        //List<Room> rooms = getAllRooms();
        RoomCapasityCamparator roomCapasityCamparator = new RoomCapasityCamparator();
        Collections.sort(rooms, roomCapasityCamparator);
        return rooms;

    }

    @Override
    public List<Room> sortByStart(List<Room> rooms) {
    //    List<Room> rooms = getAllRooms();
        RoomStarsCamparator roomStarsCamparator = new RoomStarsCamparator();
        Collections.sort(rooms, roomStarsCamparator);
        return rooms;
    }

    @Override
    public void setPath(String path) {
        this.utilRoom = new RoomIFileUtil(path);
    }

}
