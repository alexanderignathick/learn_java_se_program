package com.ignathick.hotel2.configannotations;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static Logger logger = Logger.getLogger(PropertiesReader.class);
    public static Properties getProperties(String pathToFile) {
        try(InputStream input = new FileInputStream(pathToFile)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        } catch (Exception ex) {
            logger.error("Error in config load " + ex);
            return null;
        }
    }

}
