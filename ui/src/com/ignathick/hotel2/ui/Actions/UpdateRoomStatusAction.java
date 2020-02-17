package com.ignathick.hotel2.ui.Actions;

import com.ignathick.hotel2.Facade.Facade;
import com.ignathick.hotel2.Facade.IFacade;
import com.ignathick.hotel2.config.Config;
import com.ignathick.hotel2.config.IConfig;
import com.ignathick.hotel2.configannotations.ConfigUtil;
import com.ignathick.hotel2.configannotations.MyConfig;
import com.ignathick.hotel2.dependencyinjection.DependencyInjection;
import com.ignathick.hotel2.model.Room.Status;
import com.ignathick.hotel2.ui.Interfaces.IAction;
import com.ignathick.hotel2.ui.Util.InputUtil;
import org.apache.log4j.Logger;

public class UpdateRoomStatusAction implements IAction {
    private DependencyInjection dependencyInjection = DependencyInjection.getInstance();
    private IFacade facade = dependencyInjection.load(IFacade.class);
    private IConfig config = Config.getInstance();
    private MyConfig myConfig;
    private boolean canChangeStatus;
    private static Logger logger = Logger.getLogger(UpdateRoomStatusAction.class);

    public UpdateRoomStatusAction() {
        //Annotation config
        this.myConfig = new MyConfig();
        ConfigUtil configUtil = new ConfigUtil();
        configUtil.configure(this.myConfig);
    }

    @Override
    public void execute() {
        canChangeStatus = true;
        try{
            canChangeStatus = myConfig.getCanChangeRoomStatus();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        if (!canChangeStatus) {
            System.out.println("Cant change room status (config option)");
        } else {
            GetAllRoomsAction getAllRoomsAction = new GetAllRoomsAction();
            getAllRoomsAction.execute();
            System.out.println("Input room Long id");
            Long id = InputUtil.readLong();
            System.out.println("Input room Status status (FREE, BUSY, REPAIRED, MAINTAINED)");
            Status status = InputUtil.readStatus();
            facade.updateRoomStatus(id,status);
        }
    }
}
