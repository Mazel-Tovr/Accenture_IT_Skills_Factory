package com.accenture.oopapp.mysqldatabase;


import com.accenture.oopapp.mysqldatabase.interfaces.UserOperation;
import com.accenture.oopapp.model.users.Gender;
import com.accenture.oopapp.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserTable implements UserOperation
{
    @Autowired
    private ConnectToDB dbConnection;
    @Override
    public boolean isUserExist(String nickName)
    {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("SELECT * FROM user WHERE nickName =?");
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
    public User getUser(String nickName, String password)
    {
        try {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("SELECT * FROM user WHERE nickName =? AND passWord=?");
            stmt.setString(1, nickName);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next())
            {
                return new User(resultSet.getString("name"), resultSet.getInt("age"), Gender.valueOf(resultSet.getString("gender")),
                        resultSet.getString("nickName"), resultSet.getString("passWord"), resultSet.getString("isAdmin").equals("true"));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void addUserToDataBase(User user)
    {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, 'false')");
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
