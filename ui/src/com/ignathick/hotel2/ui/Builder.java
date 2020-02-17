package com.ignathick.hotel2.ui;

import com.ignathick.hotel2.ui.Actions.*;

import java.util.ArrayList;
import java.util.List;

public class Builder implements IBuilder {

    private Menu rootMenu;
    private Menu roomsMenu;
    private Menu ordersMenu;
    private Menu sortRoomsMenu;
    private Menu guestsMenu;

    private static volatile Builder instance;

    public static Builder getInstance() {
        if (instance == null)
            synchronized (Builder.class) {
                if (instance == null)
                    instance = new Builder();
            }
        return instance;
    }

    public Builder() {
        buildRootMenu();
        buildAllMenus();
    }

    private void buildAllMenus(){
        buildRoomsMenu();
        buildRoomsSortMenu();
        buildGuestsMenu();
        buildOrdersMenu();
    }

    @Override
    public Menu getRootMenu() {
        return this.rootMenu;
    }

    private void buildRootMenu() {

        this.rootMenu = new Menu("Main menu");
        this.rootMenu.setRootMenu(true);
        this.roomsMenu = new Menu("Rooms menu");
        this.ordersMenu = new Menu("Orders menu");
        this.sortRoomsMenu = new Menu("All rooms sort menu");
        this.guestsMenu = new Menu("Guests menu");

        MenuItem itemRooms = new MenuItem("Rooms", null, this.roomsMenu, null);
        MenuItem itemGuests = new MenuItem("Guests",null, this.guestsMenu, null);
        MenuItem itemOrders = new MenuItem("Orders", null, this.ordersMenu, null);

        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(itemRooms);
        menuItems.add(itemGuests);
        menuItems.add(itemOrders);

        this.rootMenu.setMenuItems(menuItems);

    }

    private void buildRoomsMenu() {

        MenuItem itemAddRoom = new MenuItem("Add room",new AddRoomAction(), null, this.rootMenu);
        MenuItem itemShowAllRooms = new MenuItem("Show all rooms", new GetAllRoomsAction(), null, this.rootMenu);
        MenuItem itemShowFreeRooms = new MenuItem("Show free rooms", new GetFreeRoomsAction(), null, this.rootMenu);
        MenuItem itemShowNumberFreeRooms = new MenuItem("Show number of free rooms", new GetNumberOfFreeRoomsAction(), null, this.rootMenu);
        MenuItem itemShowFreeRoomsFromDate = new MenuItem("Show free rooms from date", new GetFreeRoomsFromDateAction(), null, this.rootMenu);
        MenuItem itemUpdateRoomStatus = new MenuItem("Update room status", new UpdateRoomStatusAction(), null, this.rootMenu);
        MenuItem itemSortRoomsMenu = new MenuItem("Sort rooms menu", null, this.sortRoomsMenu, this.rootMenu);
        MenuItem itemCloneRoom = new MenuItem("Clone room", new CloneRoomAction(), null, this.rootMenu);
        List<MenuItem> itemsRoomsMenu = new ArrayList<>();
        itemsRoomsMenu.add(itemAddRoom);
        itemsRoomsMenu.add(itemShowAllRooms);
        itemsRoomsMenu.add(itemShowFreeRooms);
        itemsRoomsMenu.add(itemShowNumberFreeRooms);
        itemsRoomsMenu.add(itemShowFreeRoomsFromDate);
        itemsRoomsMenu.add(itemUpdateRoomStatus);
        itemsRoomsMenu.add(itemSortRoomsMenu);
        itemsRoomsMenu.add(itemCloneRoom);
        this.roomsMenu.setMenuItems(itemsRoomsMenu);

    }

    private void buildRoomsSortMenu(){

        MenuItem itemSortByName = new MenuItem("All rooms (sorted by star)", new GetAllRoomsSortedByStarAction(), null, this.roomsMenu);
        MenuItem itemSortByCapasity = new MenuItem("All rooms (orted by capasity)", new GetAllRoomsSortedByCapasityAction(), null, this.roomsMenu);
        MenuItem itemSortByPrice = new MenuItem("All rooms (sorted by price)", new GetAllRoomsSortedByPriceAction(), null, this.roomsMenu);
        List<MenuItem> itemsSortRoomsMenu = new ArrayList<>();
        itemsSortRoomsMenu.add(itemSortByName);
        itemsSortRoomsMenu.add(itemSortByCapasity);
        itemsSortRoomsMenu.add(itemSortByPrice);
        this.sortRoomsMenu.setMenuItems(itemsSortRoomsMenu);

    }

    private void buildGuestsMenu(){

        MenuItem itemAddGuest = new MenuItem("Add guest", new AddGuestAction(), null, this.rootMenu);
        MenuItem itemGetAllGuests = new MenuItem("Get all guests", new GetAllGuestsAction(), null, this.rootMenu);
        MenuItem itemGetNumberOfGuests = new MenuItem("Get number of guests", new GetNumberOfGuestsAction(), null, this.rootMenu);
        List<MenuItem> itemsGuestsMenu = new ArrayList<>();
        itemsGuestsMenu.add(itemAddGuest);
        itemsGuestsMenu.add(itemGetAllGuests);
        itemsGuestsMenu.add(itemGetNumberOfGuests);
        this.guestsMenu.setMenuItems(itemsGuestsMenu);

    }

    private  void buildOrdersMenu(){
        MenuItem itemAddOrder = new MenuItem("Add order", new AddOrderAction(), null, this.rootMenu);
        List<MenuItem> itemsOrdersMenu = new ArrayList<>();
        itemsOrdersMenu.add(itemAddOrder);
        this.ordersMenu.setMenuItems(itemsOrdersMenu);
    }

}
