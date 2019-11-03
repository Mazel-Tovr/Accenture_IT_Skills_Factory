package com.accenture.oopapp.mysqldatabase.interfaces;

import com.accenture.oopapp.model.films.Movie;

import java.util.List;

public interface MovieOperation
{
    List<Movie> getMovieList();
    List<Movie> search(String filter,String text);
    void recalculateFilmRating(Movie movie);
    void addMoveToDataBase(Movie movie);
}
