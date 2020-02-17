package com.ignathick.hotel2.configannotations;


import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ConfigUtil {
    public static final String PATH_HOME = "/home/alex/senlaGitLab/task4_gitlab/Ignathick_Alexandr/task4/Modules/hotel2/resources/";
    private Map<String, Properties> configMap;
    private static Logger logger = Logger.getLogger(ConfigUtil.class);

    public ConfigUtil() {
        init();
    }
    private void init(){
        configMap = new HashMap();
    }

    public void configure(Object object){
        try {
            Class myConfigClass = object.getClass();
            for (Field field : myConfigClass.getDeclaredFields()) {
                if (!field.isAnnotationPresent(ConfigProperty.class)) {
                    continue;
                }
                ConfigProperty configProperty = (ConfigProperty) field.getAnnotation(ConfigProperty.class);
                String configName = configProperty.configName();
                putConfigIntoConfigMap(configName);
                Properties currentProperties = (Properties) this.configMap.get(configName);
                String propertyName = configProperty.propertyName();
                String methodName = getMethodName(propertyName);

                Class type = field.getType();
                Object param = null;
                if (type.equals(Boolean.class)) {
                    param = Boolean.valueOf(currentProperties.getProperty(propertyName));
                } else if (type.equals(String.class)){
                    param = currentProperties.getProperty(propertyName);
                }
                Method method = myConfigClass.getMethod(methodName, type);
                method.invoke(object, param);
            }
        } catch (Exception ex) {
            logger.error("Error in configure method: " + ex);
            System.out.println(ex.getMessage());
        }

    }

    private void putConfigIntoConfigMap(String configName){
        if (!configMap.containsValue(configName)) {
            configMap.put(configName, PropertiesReader.getProperties(PATH_HOME+configName));
        }
    }

    private String getMethodName(String propertyName){
        return "set" + propertyName.substring(0,1).toUpperCase() + propertyName.substring(1);
    }

}
