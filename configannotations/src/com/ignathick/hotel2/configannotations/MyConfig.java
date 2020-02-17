package com.ignathick.hotel2.configannotations;
public class MyConfig {

    @ConfigProperty(configName = "config.properties", propertyName = "pathToFile")
    private String pathToFile;
    @ConfigProperty(configName = "config.properties", propertyName = "serializationFilePath")
    private String serializationFilePath;
    @ConfigProperty(configName = "config.properties", propertyName = "canChangeRoomStatus")
    private Boolean canChangeRoomStatus;
    @ConfigProperty(configName =  "database.properties", propertyName = "DBNAME")
    private String DBNAME;
    @ConfigProperty(configName =  "database.properties", propertyName = "JDBC_CONNECTION")
    private String JDBC_CONNECTION;
    @ConfigProperty(configName =  "database.properties", propertyName = "DBLOGIN")
    private String DBLOGIN;
    @ConfigProperty(configName =  "database.properties", propertyName = "DBPASSWORD")
    private String DBPASSWORD;

    public void setDBNAME(String DBNAME) {
        this.DBNAME = DBNAME;
    }

    public void setJDBC_CONNECTION(String JDBC_CONNECTION) {
        this.JDBC_CONNECTION = JDBC_CONNECTION;
    }

    public void setDBLOGIN(String DBLOGIN) {
        this.DBLOGIN = DBLOGIN;
    }

    public void setDBPASSWORD(String DBPASSWORD) {
        this.DBPASSWORD = DBPASSWORD;
    }

    public String getDBNAME() {
        return DBNAME;
    }

    public String getJDBC_CONNECTION() {
        return JDBC_CONNECTION;
    }

    public String getDBLOGIN() {
        return DBLOGIN;
    }

    public String getDBPASSWORD() {
        return DBPASSWORD;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getSerializationFilePath() {
        return serializationFilePath;
    }

    public void setSerializationFilePath(String serializationFilePath) {
        this.serializationFilePath = serializationFilePath;
    }

    public Boolean getCanChangeRoomStatus() {
        return canChangeRoomStatus;
    }

    public void setCanChangeRoomStatus(Boolean canChangeRoomStatus) {
        this.canChangeRoomStatus = canChangeRoomStatus;
    }

    @Override
    public String toString() {
        return "MyConfig{" +
                "pathToFile='" + pathToFile + '\'' +
                ", serializationFilePath='" + serializationFilePath + '\'' +
                ", canChangeRoomStatus=" + canChangeRoomStatus +
                '}';
    }
}
