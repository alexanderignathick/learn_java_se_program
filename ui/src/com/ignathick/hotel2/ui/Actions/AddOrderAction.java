package com.ignathick.hotel2.ui.Actions;

import com.ignathick.hotel2.Facade.Facade;
import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Guest.IGuest;
import com.ignathick.hotel2.model.Order.IOrder;
import com.ignathick.hotel2.model.Order.Order;
import com.ignathick.hotel2.model.Room.IRoom;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.ui.Interfaces.IAction;
import com.ignathick.hotel2.ui.Util.InputUtil;
import com.ignathick.hotel2.ui.Util.PrintUtil;
import org.apache.log4j.Logger;

import java.util.Date;

public class AddOrderAction implements IAction {
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IFacade facade = dependencyInjection.load(IFacade.class);
    private static Logger logger = Logger.getLogger(AddOrderAction.class);
    @Override
    public void execute() {

        try {

            PrintUtil.printOrdersList(facade.getAllOrders());

            System.out.println("1 of 6: Insert order id (example 1L)");
            Long id = InputUtil.readLong();

            //show all guestlist
            PrintUtil.printGuestsList(facade.getAllGuests());
            System.out.println("2 of 6: Select guest by id (example 2)");
            Long guestId = InputUtil.readLong();
            Guest guest = facade.getGuestByID(guestId);

            //show all rooms
            PrintUtil.printRommsList(facade.getAllRooms());
            System.out.println("3 of 6: Select room by id (example 4)");
            Long roomID = InputUtil.readLong();
            Room room = facade.getRoomByID(roomID);

            System.out.println("4 of 6: Insert date from (example 2020-02-11)");
            String DateString = InputUtil.getScanStringFromKeyboard();
            Date dateFrom = InputUtil.getDateFromString(DateString);

            System.out.println("5 of 6: Insert date to (example 2020-02-16)");
            String DateStr = InputUtil.getScanStringFromKeyboard();
            Date dateTo = InputUtil.getDateFromString(DateStr);

            System.out.println("6 of 6: Insert room price (example 150d)");
            Double price = InputUtil.readDouble();

            Order order = new Order(id, guest, room, dateFrom, dateTo, price);
            facade.addOrder(order);
            logger.info("Order add success: " + PrintUtil.getOrderString(order));

        } catch (Exception ex) {
            logger.error("Error. Cant add new guest. Reason: " + ex.getMessage());
        }

    }
}
