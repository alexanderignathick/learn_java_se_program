package com.ignathick.hotel2.ui;

import com.ignathick.hotel2.Facade.Facade;
import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.config.Config;
import com.ignathick.hotel2.config.IConfig;
import com.ignathick.hotel2.configannotations.ConfigUtil;
import com.ignathick.hotel2.configannotations.MyConfig;
import com.ignathick.hotel2.database.IDatabase;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Guest.Guest;
import com.ignathick.hotel2.model.Order.Order;
import com.ignathick.hotel2.model.Room.Room;
import com.ignathick.hotel2.serializer.ISerializerUtil;
import com.ignathick.hotel2.serializer.SerializerUtil;
import com.ignathick.hotel2.ui.Wrapper.Wrapper;

public class MenuController {

    private static Builder builder;
    private Navigator navigator;
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IFacade facade = dependencyInjection.load(IFacade.class);
    private IDatabase database = dependencyInjection.load(IDatabase.class);
    private Wrapper wrapper;
    private ISerializerUtil serializerUtil;

    public MenuController() {
        run();
    }

    private void run(){

//        //load config file
//        config.setConfigPath("/home/alex/senlaGitLab/task4_gitlab/Ignathick_Alexandr/task4/Modules/hotel2/resources/config.properties");
//        config.loadConfigFile();

        //Annotation config
        MyConfig myConfig = new MyConfig();
        ConfigUtil configUtil = new ConfigUtil();
        configUtil.configure(myConfig);

//        //load data from file
//        serializerUtil = new SerializerUtil(myConfig.getSerializationFilePath());
//        wrapper = (Wrapper)serializerUtil.deserializeObject();
//        loadDataFromWrapperToFasade();

        builder = builder.getInstance();
        navigator = new Navigator(builder.getRootMenu());
        navigator.run();

        database.closeConnection();

//        //save data to file
//        wrapper = new Wrapper(facade.getAllRooms(), facade.getAllGuests(), facade.getAllOrders());
//        serializerUtil.serializeObject(wrapper);

    }

    public Builder getBuilder() {
        return builder;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    private void loadDataFromWrapperToFasade(){
        for (Room room:
             wrapper.getRoomlist()) {
            facade.addRoom(room);
        }

        for (Guest guest:
             wrapper.getGuestListList()) {
            facade.addGuest(guest);
        }

        for (Order order:
             wrapper.getOrderslist()) {
            facade.addOrder(order);
        }
    }

}
