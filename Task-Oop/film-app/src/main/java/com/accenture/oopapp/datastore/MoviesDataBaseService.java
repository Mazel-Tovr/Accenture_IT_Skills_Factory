package com.accenture.oopapp.datastore;

import com.accenture.oopapp.films.Movie;

import java.util.List;
import java.util.Set;

public interface MoviesDataBaseService
{
    boolean addMovieToMovieSet(Movie movie);
    List<Movie> idSearch(String text);
    List<Movie> nameSearch(String text);
    List<Movie> dataSearch(String text);
    Set<Movie> getMovieList();

}
