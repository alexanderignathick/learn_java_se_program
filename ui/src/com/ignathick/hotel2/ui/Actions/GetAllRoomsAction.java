package com.ignathick.hotel2.ui.Actions;

import com.ignathick.hotel2.Facade.Facade;
import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.ui.Interfaces.IAction;
import com.ignathick.hotel2.ui.Util.PrintUtil;

public class GetAllRoomsAction implements IAction {
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IFacade facade = dependencyInjection.load(IFacade.class);
    @Override
    public void execute() {
        PrintUtil.printRommsList(facade.getAllRooms());
    }
}
