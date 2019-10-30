package com.accenture.oopapp.mysqldatabase.interfaces;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.Review;
import com.accenture.oopapp.users.User;

import java.sql.SQLException;
import java.util.List;

public interface MovieOperation
{
    List<Movie> getMovieList();
    List<Movie> search(String filter,String text);
    void recalculateFilmRating(Movie movie);
    void addMoveToDataBase(Movie movie);
}
