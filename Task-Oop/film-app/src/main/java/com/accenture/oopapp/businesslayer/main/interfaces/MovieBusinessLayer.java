package com.accenture.oopapp.businesslayer.main.interfaces;

import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;
import com.accenture.oopapp.model.films.Movie;

import java.util.List;

public interface MovieBusinessLayer
{
    List<Movie> getAll();
    Object[] getAllMovieDetails(String movieId);
    void addMovieToBase(String movieId, String movieName, String movieType, String genres, String releaseDate, String description, double rating);
    List<Movie> getMovieByMovieType(String movieType);
    List<Movie> getMovieByGenre(String genre);
    List<Movie> getMovieByRating(double from,double to)throws InputDataException;
}
