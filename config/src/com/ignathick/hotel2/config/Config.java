package com.ignathick.hotel2.config;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class Config implements IConfig {

    private static volatile Config instance;
    private static Logger logger = Logger.getLogger(Config.class);
    private String pathToFile;
    private String serializationFilePath;
    private Boolean canChangeRoomStatus;
    private Properties properties;

    private Config() {

    }

    public static Config getInstance(){
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }
    @Override
    public String getSerializationFilePath() {
        return serializationFilePath;
    }

    public void setSerializationFilePath(String serializationFilePath) {
        this.serializationFilePath = serializationFilePath;
    }

    @Override
    public Boolean getCanChangeRoomStatus() {
        return canChangeRoomStatus;
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    public void setCanChangeRoomStatus(Boolean canChangeRoomStatus) {
        this.canChangeRoomStatus = canChangeRoomStatus;
    }

    @Override
    public void saveConfigFile() {
        try(OutputStream output = new FileOutputStream(pathToFile)){
            Properties properties = new Properties();
            properties.setProperty("pathToFile", this.pathToFile);
            properties.setProperty("serializationFilePath", this.serializationFilePath);
            properties.setProperty("canChangeRoomStatus", String.valueOf(this.getCanChangeRoomStatus()));
            properties.store(output, null);
            System.out.println(properties);
        } catch (IOException ex) {
           logger.error("Error in config save " + ex.getMessage());
        }
    }

    @Override
    public void loadConfigFile() {
        try(InputStream input = new FileInputStream(pathToFile)) {
            Properties properties = new Properties();
            properties.load(input);
            this.properties = properties;
            this.pathToFile = properties.getProperty("pathToFile");
            this.serializationFilePath = properties.getProperty("serializationFilePath");
            this.canChangeRoomStatus = Boolean.valueOf(properties.getProperty("canChangeRoomStatus"));
        } catch (IOException ex) {
            logger.error("Error in config load " + ex.getMessage());
        }
    }

    @Override
    public void setConfigPath(String path) {
        this.pathToFile = path;
    }


}
