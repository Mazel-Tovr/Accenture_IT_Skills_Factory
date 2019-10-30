package com.accenture.oopapp.mysqldatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectToDB
{
    private static ConnectToDB instance = null;
    private Connection dbConnection = null;

    private final String DB_DRIVER;
    private final String DB_CONNECTION;
    private final String DB_USER;
    private final String DB_PASSWORD;

    private ConnectToDB()
    {
        DB_DRIVER = "com.mysql.jdbc.Driver";
        DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/filmappdb?autoReconnect=true&useSSL=false";
        DB_USER = "root";
        DB_PASSWORD = "";
        getDBConnection();
    }

    public static ConnectToDB getInstance()
    {
        if(instance == null)
        {
            synchronized (ConnectToDB.class)
            {
                if(instance == null)
                {
                    instance = new ConnectToDB();
                }
            }
        }
        return instance;
    }

    public Connection getDbConnection()
    {
        if(dbConnection == null) getDBConnection();

        return dbConnection;
    }

    private boolean getDBConnection()
    {
        try
        {
            Class.forName(DB_DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
        try
        {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
            return true;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
