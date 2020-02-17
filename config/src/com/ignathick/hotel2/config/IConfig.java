package com.ignathick.hotel2.config;

import java.util.Properties;

public interface IConfig {
    public void saveConfigFile();
    public void loadConfigFile();
    public void setConfigPath(String path);
    public String getSerializationFilePath();
    public Boolean getCanChangeRoomStatus();
    public Properties getProperties();

}
