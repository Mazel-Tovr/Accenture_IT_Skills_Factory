package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;

import java.sql.*;
import java.util.*;

public class MySqlDataBase implements DataBase
{


private static DataBase instance = null;

    private MySqlDataBase(){}

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

    private Connection dbConnection = null;
    @Override
    public boolean getDBConnection(String DB_DRIVER, String DB_CONNECTION,String DB_USER,String DB_PASSWORD)
    {
        try
        {
            Class.forName(DB_DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
        try
        {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public boolean disConnect()
    {
        try
        {
            dbConnection.close();
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
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
            return  movieList;

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
