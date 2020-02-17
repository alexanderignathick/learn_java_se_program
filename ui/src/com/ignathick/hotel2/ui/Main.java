package com.ignathick.hotel2.ui;

import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.model.Room.Star;
import com.ignathick.hotel2.model.Room.Status;
import org.apache.log4j.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        //test
        DependencyInjection dependencyInjection = DependencyInjection.getInstance();
        IFacade facade = dependencyInjection.load(IFacade.class);
        if (args.length > 0) {
            facade.setRoomServicePath(args[0]);
            facade.setGuestServicePath(args[1]);
            facade.setOrderServicePath(args[2]);
        }

//        IDatabase database = new Database();
//        database.testConnectionToDatabase();
//        database.selectAllGuestsFromDB();


//        ////////////////////////////////////////////////////////////////////////
//        // ROOM
//        ////////////////////////////////////////////////////////////////////////
//        Room room1 = new Room(1L,1, 1, 100d, Star.FIVE, Status.BUSY, "2019-12-01", "2019-12-05");
//        Room room2 = new Room(2L,2, 2, 150d, Star.FOUR, Status.FREE);
//        Room room3 = new Room(3L,3, 3, 120d, Star.THREE, Status.BUSY, "2019-12-05", "2019-12-06");
//        Room room4 = new Room(4L,4, 2, 123d, Star.TWO, Status.FREE);
//        Room room5 = new Room(5L,5, 1, 50d, Star.ONE, Status.FREE);
//
//        fasade.addRoom(room1);
//        fasade.addRoom(room2);
//        fasade.updateRoomStatus(room2.getId(), Status.MAINTAINED);
//        fasade.addRoom(room3);
//        fasade.addRoom(room4);
//        fasade.addRoom(room5);
//
//        //List<Room> somelist = fasade.getFreeRoomsListFromDate(DateUtil.getDateFromString("2019-12-06"));
//
//        fasade.writeRoomsToData();
//        fasade.readRoomsFromData();
//
//        System.out.println("Print rooms thas where loaded from file system:");
//        for (Room room:
//                fasade.getAllRooms()) {
//            System.out.println(room);
//        }
//
//        System.out.println("List of free rooms: " + fasade.getNumberOfFreeRooms());
//        for (Room room:
//                fasade.getFreeRooms()) {
//            System.out.println(room);
//        }
//
//        ////////////////////////////////////////////////////////////////////////////
//        // GUESTS
//        ////////////////////////////////////////////////////////////////////////////
//        Guest guest1 = new Guest(1L,"Alexandr", "Ignathick", "1987-05-10", "AB123456");
//        Guest guest2 = new Guest(2L,"Michail", "Shoroch", "1988-04-12", "AB000001");
        Guest guest3 = new Guest(6L,"Bob", "Bobov", "1989-01-16", "AB1111111");
        facade.addGuest(guest3);
       guest3.setFirstName("UpdatedIvan");
//        guest3.setLastName("UpdatedIvanov");
        facade.updateGuest(guest3);
        facade.deleteGuest(guest3);
        Room room = new Room(11L,5, 1, 50d, Star.ONE, Status.FREE);
        facade.addRoom(room);
        room.setStatus(Status.BUSY);
        facade.updateRoom(room);
        facade.deleteRoom(room);

//        fasade.addGuest(guest1);
//        fasade.addGuest(guest2);
//        fasade.addGuest(guest3);
//        fasade.writeGuestsToData();
//        fasade.readRoomsFromData();
//        System.out.println("Print guests thas where loaded from file system:");
//        for (Guest guest:
//                fasade.getAllGuests()) {
//            System.out.println(guest);
//        }
//        System.out.println("Guest number is: " + fasade.getNumberOfGuests());
//
//        ////////////////////////////////////////////////////////////////////////////
//        // ORDERS
//        ////////////////////////////////////////////////////////////////////////////
//
//        Order order1 = new Order(1L, guest1, room1, "2020-01-01", "2020-01-10", 200D);
//        fasade.addOrder(order1);
//        fasade.writeOrdersToData();
//        System.out.println("ORDERS");
//        System.out.println(order1);
//        System.out.println(guest1);
//        System.out.println(room1);
//
//
//        ////////////////////////////////////////////////////////////////////////////
//        // SORT
//        ////////////////////////////////////////////////////////////////////////////
//        System.out.println("Guests sort by name: ");
//        for (Guest guest:
//                fasade.sortByGuestName()) {
//            System.out.println(guest);
//        }
//
//        System.out.println("Rooms sort by capasity: ");
//        for (Room room:
//                fasade.sortByCapasity(fasade.getAllRooms())) {
//            System.out.println(room);
//        }
//
//        System.out.println("Rooms sort by price : ");
//        for (Room room:
//                fasade.sortByPrice(fasade.getAllRooms())) {
//            System.out.println(room);
//        }
//
//        System.out.println("Room sort by stars: ");
//        for (Room room:
//                fasade.sortByStart(fasade.getAllRooms())) {
//            System.out.println(room);
//        }

        /////////////////////////////////////////////////////////////////////////////
        // UI
        /////////////////////////////////////////////////////////////////////////////
        //logger.info("Start ui");
        // logger.error("test error");
        MenuController menyController = new MenuController();

        // logger.info("Quit ui");

    }

}
