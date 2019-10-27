package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.User;

import java.sql.*;
import java.util.*;

public class MySqlDataBase implements DataBase
{


private static DataBase instance = null;

    private MySqlDataBase()
    {
    DB_DRIVER = "com.mysql.jdbc.Driver";
    DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/filmappdb?autoReconnect=true&useSSL=false";
    DB_USER = "root";
    DB_PASSWORD = "";
    getDBConnection();
    }

    public static DataBase getInstance()
    {
        if(instance == null)
        {
            synchronized (MySqlDataBase.class)
            {
                if(instance == null)
                {
                    instance = new MySqlDataBase();
                }
            }
        }
        return instance;
    }

   private final String DB_DRIVER;
   private final String DB_CONNECTION;
   private final String DB_USER;
   private final String DB_PASSWORD;

    private Connection dbConnection = null;
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

    @Override
    public List<Movie> getMovieList()
    {
        List<Movie> movieList = new ArrayList<>(10);
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM movie");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                String[] genresString  = rs.getString("genres").split(",");
                Set<Genre> genres = new HashSet<>();
                for (String s : genresString)
                {
                    genres.add(Genre.valueOf(s));
                }
                movieList.add(new Movie(rs.getString("movieId"),rs.getString("movieName"), MovieType.valueOf(rs.getString("movieType")),EnumSet.copyOf(genres),rs.getString("releaseDate"),rs.getString("description")));
            }
            dbConnection.close();
            return  movieList;
        }
        catch (SQLException e)
        {
          //  dbConnection.close();
            e.printStackTrace();
        }
        return null;
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
