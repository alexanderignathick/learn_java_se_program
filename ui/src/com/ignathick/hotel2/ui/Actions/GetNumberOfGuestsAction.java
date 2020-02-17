package com.ignathick.hotel2.ui.Actions;
import com.ignathick.hotel2.dependencyinjection.*;
import com.ignathick.hotel2.Facade.Facade;
import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.ui.Interfaces.IAction;

public class GetNumberOfGuestsAction implements IAction {
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IFacade facade = dependencyInjection.load(IFacade.class);
    @Override
    public void execute() {
        System.out.println(facade.getNumberOfGuests());
    }
}
