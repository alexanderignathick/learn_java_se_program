package com.ignathick.hotel2.ui.Actions;

import com.ignathick.hotel2.Facade.Facade;
import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.ui.Interfaces.IAction;
import com.ignathick.hotel2.ui.Util.InputUtil;
import com.ignathick.hotel2.ui.Util.PrintUtil;

import java.util.Date;

public class GetFreeRoomsFromDateAction implements IAction {
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IFacade facade = dependencyInjection.load(IFacade.class);
    @Override
    public void execute() {
        System.out.println("Input date (patern is: \"yyyy-MM-dd\")");
        Date inputDate = InputUtil.readDate();
        PrintUtil.printRommsList(facade.getFreeRoomsListFromDate(inputDate));
    }
}
