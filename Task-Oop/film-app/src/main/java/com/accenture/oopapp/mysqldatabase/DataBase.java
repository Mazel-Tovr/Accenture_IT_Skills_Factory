package com.accenture.oopapp.mysqldatabase;

import java.sql.*;

public interface DataBase
{
    String DB_DRIVER = "com.mysql.jdbc.Driver" ;
    String DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/filmappdb?autoReconnect=true&useSSL=false";
    String DB_USER = "root" ;
    String DB_PASSWORD = "";

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
            System.out.println("Все ок");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return dbConnection;

    }

    public static void main(String[] args)
    {
        getDBConnection();

        try {
            Connection dbConnection = getDBConnection();
           Statement statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery("SELECT * FROM user");

            // И если что то было получено то цикл while сработает
            while (rs.next()) {
                String userid = rs.getString("userId");
                String username = rs.getString("name");

                System.out.println("userid : " + userid);
                System.out.println("username : " + username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
