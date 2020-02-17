package com.ignathick.hotel2.ui.Actions;

import com.ignathick.hotel2.Facade.Facade;
import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.ui.Interfaces.IAction;
import com.ignathick.hotel2.ui.Util.InputUtil;
import com.ignathick.hotel2.ui.Util.PrintUtil;
import org.apache.log4j.Logger;

public class CloneRoomAction implements IAction {
    private static Logger logger = Logger.getLogger(CloneRoomAction.class);
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IFacade facade = dependencyInjection.load(IFacade.class);
    private Room room;
    private Room roomClone;
    @Override
    public void execute() {
        GetAllRoomsAction getAllRoomsAction = new GetAllRoomsAction();
        getAllRoomsAction.execute();
        System.out.println("Input room id to clone");
        Long id = InputUtil.readLong();
        room = facade.getRoomByID(id);
        roomClone = room.clone();
        PrintUtil.printRoom(roomClone);
        facade.addRoom(roomClone);
    }
}
