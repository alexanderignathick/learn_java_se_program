package com.ignathick.hotel2.ui;

import java.util.List;

public class Menu {
    private String name;
    private List<MenuItem> menuItems;
    private boolean isRootMenu;

    public Menu() {
    }

    public Menu(String name) {
        this.name = name;
    }

    public Menu(String name, List<MenuItem> menuItems) {
        this.name = name;
        this.menuItems = menuItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public boolean isRootMenu() {
        return isRootMenu;
    }

    public void setRootMenu(boolean rootMenu) {
        isRootMenu = rootMenu;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", menuItems=" + menuItems +
                '}';
    }
}
