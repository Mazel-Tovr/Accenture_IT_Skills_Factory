package com.accenture.oopapp.datalayer.jpadata.interfaces;


import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.Movie;

import java.sql.SQLException;
import java.util.List;

public interface MovieOperationJPA
{
    List<Movie> searchByGenre(Genre ...genre);
    List<Movie> searchByRating(double from ,double to);
    List<Movie> getAllMovie();
    void addMovie(Movie movie);
}
