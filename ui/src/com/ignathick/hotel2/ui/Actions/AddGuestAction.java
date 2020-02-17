package com.ignathick.hotel2.ui.Actions;

import com.ignathick.hotel2.Facade.Facade;
import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.ui.Interfaces.IAction;
import com.ignathick.hotel2.ui.Util.InputUtil;
import com.ignathick.hotel2.ui.Util.PrintUtil;
import org.apache.log4j.Logger;

import java.util.Date;

public class AddGuestAction implements IAction {
    private static Logger logger = Logger.getLogger(AddGuestAction.class);
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IFacade facade = dependencyInjection.load(IFacade.class);
    @Override
    public void execute() {
        //Guest guest1 = new Guest(1L,"Alexandr", "Ignathick", "1987-05-10", "AB123456");
        //public Guest(Long id, String firstName, String lastName, String dateOfBirth, String passport) {
        try {
            System.out.println("1 of 5: Insert guest id (example 25L)");
            Long id = InputUtil.readLong();
            System.out.println("2 of 5: Insert guest first name (example Alexander)");
            String firstName = InputUtil.getScanStringFromKeyboard();
            System.out.println("3 of 5: Insert guest last name (example Ignathick)");
            String lasttName = InputUtil.getScanStringFromKeyboard();
            System.out.println("4 of 5: Insert guest date of birth (example 1987-05-10)");
            String DateIString = InputUtil.getScanStringFromKeyboard();
            Date dateOfBirth = InputUtil.getDateFromString(DateIString);
            System.out.println("5 of 5: Insert guest passport number (example AB123456)");
            String passportNumber = InputUtil.getScanStringFromKeyboard();
            Guest guest = new Guest(id, firstName, lasttName, DateIString, passportNumber);
            facade.addGuest(guest);
            logger.info("Guest add success: " + PrintUtil.getGuestString(guest));
        } catch (Exception ex) {
            logger.error("Error. Cant add new guest. Reason: " + ex.getMessage());
        }

    }
}
