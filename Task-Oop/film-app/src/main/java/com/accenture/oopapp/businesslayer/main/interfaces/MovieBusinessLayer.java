package com.accenture.oopapp.businesslayer.sql.interfaces;

import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.MovieType;

import java.util.List;
import java.util.Set;

public interface MovieBusinessLayer
{
    List<Movie> getAll();
    Object[] getAllMovieDetails(String movieId);
    void addMovieToBase(String movieId, String movieName, String movieType, String genres, String releaseDate, String description, double rating);
}
