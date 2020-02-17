package com.ignathick.hotel2.database;

import java.sql.Connection;

public interface IDatabase {

    Connection getConnection();
    void closeConnection();
    String getDBNAME();
    void testConnectionToDatabase();

}
