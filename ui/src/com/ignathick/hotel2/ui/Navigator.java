package com.ignathick.hotel2.ui;

import com.ignathick.hotel2.ui.Util.InputUtil;
import org.apache.log4j.Logger;
import com.ignathick.hotel2.config.*;

public class Navigator {

    private static Logger logger = Logger.getLogger(Navigator.class);


    static class MyInputException extends Exception {

        public MyInputException(String msg) {
            super(msg);
        }
    }

    private Menu currentMenu;
    private Menu previousMenu;

    public Navigator() {
    }

    public Navigator(Menu menu) {
        setCurrentMenu(menu);
    }

    public void run() {


        Integer readValue = 0;
        while (true) {
            printMenu();
            readValue = InputUtil.readInteger();
            if (checkForExit(readValue)) {
               logger.info("Exit from programm");
                break;
            }

            try {
                navigate(readValue);
            } catch (IndexOutOfBoundsException | MyInputException ex) {
                logger.error("Error in navigate menu " + ex.getMessage());
            }

        }
    }

    private boolean checkForExit(Integer readValue) {
        if (getCurrentMenu().isRootMenu() && (readValue == -1)) {
           return true;
        } else {
            return false;
        }
    }

    private void navigate(Integer index) throws IndexOutOfBoundsException, MyInputException{

//        if (this.currentMenu.getMenuItems().size() < (index)) {
//            throw new MyInputException("You enter invalid value to navigate menu");
//        }
        if (index == 0) {
            this.currentMenu = Builder.getInstance().getRootMenu();
            this.previousMenu = null;
        } else {
            if (index == 9 && !this.currentMenu.isRootMenu()) {
                this.currentMenu = this.previousMenu;
            } else {
                MenuItem menuItem = this.currentMenu.getMenuItems().get(index - 1);
                menuItem.setPreviousMenu(this.currentMenu);
                if (menuItem.getNextMenu() == null) {
                    this.currentMenu = menuItem.getPreviousMenu();
                } else {
                    this.previousMenu = currentMenu;
                    this.currentMenu = menuItem.getNextMenu();
                }
                menuItem.doAction();
            }
        }
    }

    private void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    private Menu getCurrentMenu() {
        return currentMenu;
    }

    private void printMenu() {
        InputUtil.clearScreen();
        System.out.println(currentMenu.getName());
        int index = 0;
        for (MenuItem menuItem :
                currentMenu.getMenuItems()) {
            index++;
            System.out.println("   " + index + ": " + menuItem.getTitle());
        }
        if (currentMenu.isRootMenu()) {
            System.out.println("   -1: Exit");
        } else {
            System.out.println("   9: Previous menu");
            System.out.println("   0: Main menu");
        }

    }

}
