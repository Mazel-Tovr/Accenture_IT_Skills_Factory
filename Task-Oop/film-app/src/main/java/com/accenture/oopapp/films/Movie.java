package com.accenture.oopapp.films;

import com.accenture.oopapp.films.review.Review;
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
    private List<Review> filmsReview = new ArrayList<>();
    private double rating = 0;
    private String description;

    public Movie(String movieId, String movieName, MovieType movieType, EnumSet<Genre> genres, String releaseDate, String description)
    {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieType = movieType;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.description = description;
    }

    public List<Review> getFilmsReview() { return filmsReview; }

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

    public boolean removeReview(Review review){ return filmsReview.remove(review);}

    public void addReview(User user, String text, double rating)
    {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        filmsReview.add(new Review(text,formatForDateNow.format(date),user,rating));
        recalculateFilmRating();
    }

    public void recalculateFilmRating()
    {
        int usersRating = 0;
        for (Review item :filmsReview)
        {
            usersRating += item.getYouLikedFilm();
        }
        rating = (double) usersRating / filmsReview.size();
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
