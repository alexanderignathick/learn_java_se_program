package com.ignathick.hotel2.dependencyinjection;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DependencyInjection {
    private static Map<Class, Object> map = new HashMap<>();
    private static DependencyInjection instance;
    private static Properties properties = new Properties();
    private static Logger logger = Logger.getLogger(DependencyInjection.class);
    public static final String PATH_HOME = "/home/alex/senlaGitLab/task4_gitlab/Ignathick_Alexandr/task4/Modules/hotel2/resources/configDI.properties";

    public static DependencyInjection getInstance() {
        if (instance == null) {
            return new DependencyInjection();
        }
        return instance;
    }

    private DependencyInjection() {
        try {
            InputStream input = new FileInputStream(PATH_HOME);
            this.properties.load(input);

        } catch (Exception ex) {
            logger.error("DI module error: " + ex);
        }
    }

    public <T> T load(Class<T> cInterface) {
        try {
            if (map.containsKey(cInterface)) {
                return (T) map.get(cInterface);
            } else {
                String realisationString = properties.getProperty(cInterface.getName());
                Class realisationClass = Class.forName(realisationString);
                T newInstanceOfClass = (T) realisationClass.newInstance();
                map.put(cInterface, newInstanceOfClass);
                return newInstanceOfClass;
            }
        } catch (Exception ex) {
            logger.error(ex);
        }

        return null;
    }

}
