package com.ignathick.hotel2.propertiesreader;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static Logger logger = Logger.getLogger(PropertiesReader.class);
    private Properties properties = new Properties();
    private String pathToFile;

    public PropertiesReader(String pathToConfig) {
        this.properties = new Properties();
        this.pathToFile = pathToConfig;

    }

    public void loadConfigFile() {
        try(InputStream input = new FileInputStream(pathToFile)) {
            this.properties.load(input);
        } catch (Exception ex) {
            logger.error("Error in config load " + ex);
        }
    }

}
