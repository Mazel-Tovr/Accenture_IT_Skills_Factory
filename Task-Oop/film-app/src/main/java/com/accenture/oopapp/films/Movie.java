package com.accenture.oopapp.films;

import com.accenture.oopapp.users.User;

import java.text.SimpleDateFormat;
import java.util.*;

public class Movie
{
    private String movieId;
    private String movieName;
    private MovieType movieType;
    private EnumSet<Genre> genres;
    private String releaseDate;
    private double rating = 0;
    private String description;

    public Movie(String movieId, String movieName, MovieType movieType, EnumSet<Genre> genres, String releaseDate, String description,double rating)
    {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieType = movieType;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.description = description;
        this.rating = rating;
    }


    public String getMovieName() { return movieName; }

    public String getMovieId() {
        return movieId;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public EnumSet<Genre> getGenres() {
        return genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }




    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return movieId.equals(movie.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId);
    }


}
