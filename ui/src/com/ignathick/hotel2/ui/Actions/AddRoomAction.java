package com.ignathick.hotel2.ui.Actions;

import com.ignathick.hotel2.Facade.Facade;
import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.model.Room.Star;
import com.ignathick.hotel2.model.Room.Status;
import com.ignathick.hotel2.ui.Interfaces.IAction;
import com.ignathick.hotel2.ui.Util.InputUtil;
import com.ignathick.hotel2.ui.Util.PrintUtil;
import jdk.nashorn.internal.ir.CatchNode;
import org.apache.log4j.Logger;

public class AddRoomAction implements IAction {
    private static Logger logger = Logger.getLogger(AddRoomAction.class);
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IFacade facade = dependencyInjection.load(IFacade.class);
    @Override
    public void execute() {

        //Room room1 = new Room(1L,1, 1, 100d, Star.FIVE, Status.FREE);
        //public Room(Long id, Integer number, Integer capacity, Double price, Star star, Status status) {
        try {
            System.out.println("1 of 6: Insert room id (example 100L)");
            Long id = InputUtil.readLong();
            System.out.println("2 of 6: Insert room number (example 100)");
            Integer number = InputUtil.readInteger();
            System.out.println("3 of 6: Insert room capasity (example 2)");
            Integer capasity = InputUtil.readInteger();
            System.out.println("4 of 6: Insert room price (example 150d)");
            Double price = InputUtil.readDouble();
            System.out.println("5 of 6: Insert room star (ONE, TWO, THREE, FOUR, FIVE)");
            Star star = Star.valueOf(InputUtil.getScanStringFromKeyboard());
            System.out.println("5 of 6: Insert room status (FREE, BUSY, REPAIRED, MAINTAINED)");
            //Status status = Status.valueOf(InputUtil.getScanStringFromKeyboard());
            Status status = InputUtil.readStatus();

            Room room = new Room(id, number, capasity, price, star, status);
            facade.addRoom(room);
            System.out.println("Done: " + PrintUtil.getRoomString(room));
            logger.info("Room add success " + PrintUtil.getRoomString(room));
        } catch (Exception ex) {
            logger.error("Error. Cant add new room. Reason: " + ex.getMessage());
        }

    }
}
