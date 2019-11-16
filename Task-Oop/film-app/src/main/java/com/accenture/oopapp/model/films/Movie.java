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
    @ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name = "genres",joinColumns = {@JoinColumn(name = "film_id")},inverseJoinColumns = {@JoinColumn(name="genre")})
    private Set<GenreModel> genres;
    @Column(name = "releasedate")
    private String releaseDate;
    @Column(name = "rating")
    private double rating = 0;
    @Column(name = "description")
    private String description;

    public Movie(){}

    public Movie(String movieId, String movieName, MovieType movieType, Set<GenreModel> genres, String releaseDate, String description,double rating)
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public Set<GenreModel> getGenres() { return genres; }

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
