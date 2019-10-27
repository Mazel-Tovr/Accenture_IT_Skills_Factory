package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.users.User;

import java.sql.SQLException;
import java.util.List;

public interface DataBase
{
    List<Movie> getMovieList();
    boolean isUserExist(String nickName);
    boolean isConnect(String nickName,String password);
    void addUserToDataBase(User user);
}
