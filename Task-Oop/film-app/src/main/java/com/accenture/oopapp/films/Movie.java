package com.accenture.oopapp.films;

import com.accenture.oopapp.films.review.Review;

import java.util.*;

public class Movie
{
    private String movieId;
    private String movieName;
    private MoveType moveType;
    private EnumSet<Genre> genres;
    private String releaseDate;
    private List<Review> filmsReview = new ArrayList<>();
    private List<Review> reviewsOnModerations = new ArrayList<>();
    private double rating = 0;
    private String description;

    public Movie(String movieId, String movieName, MoveType moveType, EnumSet<Genre> genres, String releaseDate)
    {
        this.movieId = movieId;
        this.movieName = movieName;
        this.moveType = moveType;
        this.genres = genres;
        this.releaseDate = releaseDate;
    }

    public List<Review> getFilmsReview() { return filmsReview; }

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

    public void recalculateFilmRating()
    {
        int usersRating = 0;
        for (var item :filmsReview)
        {
           usersRating += item.howYouLikedFilm();
        }
        rating = (double) usersRating / filmsReview.size();
    }

}
