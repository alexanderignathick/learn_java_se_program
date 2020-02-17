package com.ignathick.hotel2.ui;

import com.ignathick.hotel2.ui.Interfaces.IAction;

public class MenuItem {

    private String title;
    private IAction action;
    private Menu nextMenu;
    private Menu previousMenu;

    public MenuItem(String title) {
        this.title = title;
    }
//
    public void doAction() {
        if(action!=null) {
            action.execute();
        }
    }

    public MenuItem(String title, IAction action, Menu nextMenu, Menu previousMenu) {
        this.title = title;
        this.action = action;
        this.nextMenu = nextMenu;
        this.previousMenu = previousMenu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public IAction getAction() {
        return action;
    }

    public void setAction(IAction action) {
        this.action = action;
    }

    public Menu getNextMenu() {
        return nextMenu;
    }

    public void setNextMenu(Menu nextMenu) {
        this.nextMenu = nextMenu;
    }

    public Menu getPreviousMenu() {
        return previousMenu;
    }

    public void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "title='" + title + '\'' +
                '}';
    }
}
