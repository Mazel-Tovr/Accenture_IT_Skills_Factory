package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.films.Movie;

import java.util.List;

public interface DataBase
{
    boolean getDBConnection(String DB_DRIVER, String DB_CONNECTION,String DB_USER,String DB_PASSWORD);
    boolean disConnect();
    List<Movie> getMovieList();
}
