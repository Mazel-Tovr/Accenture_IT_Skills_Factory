package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.mysqldatabase.interfaces.UserOperation;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTable implements UserOperation
{
    private static UserTable instance = null;
    private static ConnectToDB connectToDB = ConnectToDB.getInstance();
    private Connection dbConnection;
    private UserTable(){dbConnection = connectToDB.getDbConnection();}

    public static UserOperation getInstance()
    {
        if(instance == null)
        {
            synchronized (UserTable.class)
            {
             if (instance == null)
             {
                instance = new UserTable();
             }
            }
        }
        return instance;
    }

    @Override
    public boolean isUserExist(String nickName)
    {
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM user WHERE nickName =?");
            stmt.setString(1,nickName);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isConnect(String nickName,String password)
    {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM user WHERE nickName =? AND passWord=?");
            stmt.setString(1, nickName);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next())
            {
                FilmApp.session.setCurrentUser(new User(resultSet.getString("name"), resultSet.getInt("age"), Gender.valueOf(resultSet.getString("gender")),
                        resultSet.getString("nickName"), resultSet.getString("passWord"), resultSet.getString("isAdmin").equals("true")));
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void addUserToDataBase(User user)
    {
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, 'false')");
            stmt.setString(1,user.getName());stmt.setInt(2,user.getAge());
            stmt.setString(3,user.getGender().toString());
            stmt.setString(4,user.getNickName());stmt.setString(5,user.getPassWord());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
