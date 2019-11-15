package com.accenture.oopapp.model.films;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "movie")
public class Movie
{
    @Id
    @Column(name = "movieid")
    private String movieId;
    @Column(name = "moviename")
    private String movieName;
    @Column(name="movietype")
    @Enumerated(EnumType.STRING )
    private MovieType movieType;
    @Transient//How to fix it ?
    //@ManyToMany()

    private EnumSet<Genre> genres;
    @Column(name = "releasedate")
    private String releaseDate;
    @Column(name = "rating")
    private double rating = 0;
    @Column(name = "description")
    private String description;

    public Movie(){}

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
