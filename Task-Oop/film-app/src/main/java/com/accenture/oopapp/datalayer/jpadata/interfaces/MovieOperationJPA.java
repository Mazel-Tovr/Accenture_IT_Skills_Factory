package com.accenture.oopapp.datalayer.jpadata.interfaces;


import com.accenture.oopapp.datalayer.mysqldatabase.interfaces.MovieOperation;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.MovieType;

import java.util.List;

public interface MovieOperationJPA extends MovieOperation
{
    List<Movie> searchMovieByGenre(Genre ...genre);
    List<Movie> searchMovieByType(MovieType ...type);
    List<Movie> searchByRating(double from ,double to);
    void removeMovie(Movie movie);
}
