package com.ignathick.hotel2.serializer;

import org.apache.log4j.Logger;

import java.io.*;

public class SerializerUtil implements ISerializerUtil {

    private String filePath;
    private static Logger logger = Logger.getLogger(SerializerUtil.class);

    public SerializerUtil(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void serializeObject(Object object) {
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File(filePath)))) {
            objectOutputStream.writeObject(object);
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
            logger.error("Error in serizlization^ " + ex.getStackTrace());
        }
    }

    @Override
    public Object deserializeObject() {
        Object object = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File(filePath)))){
            object = objectInputStream.readObject();
        } catch (Exception ex) {
            //System.out.println(ex.getMessage());
            logger.error("Error in deserizlization^ " + ex.getStackTrace());
        }
        return object;
    }
}
