package com.accenture.oopapp.films;

import com.accenture.oopapp.review.Review;
import com.accenture.oopapp.users.*;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

public class Movie
{
    private String movieId;
    private String movieName;
    private MoveType moveType;
    private EnumSet<Genre> genres;
    private String releaseDate;
    private List<Review> filmsReview = new ArrayList<>();
    private List<Review> reviewsOnModerations = new ArrayList<>();
    private String description;

    public Movie(String movieId, String movieName, MoveType moveType, EnumSet<Genre> genres, String releaseDate)
    {
        this.movieId = movieId;
        this.movieName = movieName;
        this.moveType = moveType;
        this.genres = genres;
        this.releaseDate = releaseDate;
    }

    public boolean addReview(Review review, Person person)
    {
       if(person instanceof User) return reviewsOnModerations.add(review);
       else if(person instanceof Administrator) return filmsReview.add(review);
       return false;
    }

    public List<Review> getReviewsOnModerations()
    {
        return reviewsOnModerations;
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
