package com.ignathick.hotel2.database;

import com.ignathick.hotel2.configannotations.ConfigProperty;
import com.ignathick.hotel2.configannotations.ConfigUtil;
import com.ignathick.hotel2.configannotations.MyConfig;
import com.ignathick.hotel2.model.Guest.Guest;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database implements IDatabase {


    private String pathToFile;

    private String DBNAME;
    private String JDBC_CONNECTION;
    private String DBLOGIN;
    private String DBPASSWORD;

    private Connection connection;
    private static Logger logger = Logger.getLogger(Database.class);
    private MyConfig myConfig;

    public Database() {
        init();
    }

    private void init() {

        this.myConfig = new MyConfig();
        ConfigUtil configUtil = new ConfigUtil();
        configUtil.configure(this.myConfig);
        DBNAME = myConfig.getDBNAME();
        JDBC_CONNECTION = myConfig.getJDBC_CONNECTION();
        DBLOGIN = myConfig.getDBLOGIN();
        DBPASSWORD = myConfig.getDBPASSWORD();

        try{
            connection = DriverManager.getConnection(JDBC_CONNECTION + DBNAME, DBLOGIN, DBPASSWORD);
            if (connection != null){
                logger.info("Connected to mysql database");
            } else {
                logger.error("failed to make connection do database");
            }
        } catch(SQLException ex) {
            logger.error("SQL error. Database " + DBNAME + " error:" + ex);
        } catch (Exception ex){
            logger.error("Connection error: " + ex);
        }

    }

    @Override
    public Connection getConnection(){
        return this.connection;
    }

    @Override
    public void closeConnection(){
        try{
            connection.close();
            if (connection != null){
                logger.info("Close connection to mysql database");
            } else {
                logger.error("failed to close connection do database");
            }
        } catch(SQLException ex) {
            logger.error("SQL error. Database " + DBNAME + " error:" + ex);
        } catch (Exception ex){
            logger.error("Connection error: " + ex);
        }
    }

    @Override
    public String getDBNAME(){
        return this.DBNAME;
    }


    @Override
    public void testConnectionToDatabase(){

        try(Connection connection = DriverManager.getConnection(JDBC_CONNECTION + DBNAME, DBLOGIN, DBPASSWORD)){
            if (connection != null){
                logger.info("Connected to mysql database");
            } else {
                logger.error("failed to make connection do database");
            }
        } catch(SQLException ex) {
            logger.error("SQL error. Database " + DBNAME + " error:" + ex);
        } catch (Exception ex){
            logger.error("Connection error: " + ex);
        }
    }

}
