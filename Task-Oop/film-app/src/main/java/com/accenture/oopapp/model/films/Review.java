package com.accenture.oopapp.model.films;


public class Review
{
    private Long reviewId;
    private String movieId;
    private String text;
    private String postDate;
    private String personWhoWroteIt;
    private double userRating;

    public Review(Long reviewId, String movieId, String text, String postDate, String personWhoWroteIt, double userRating)
    {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.text = text;
        this.postDate = postDate;
        this.personWhoWroteIt = personWhoWroteIt;
        this.userRating = userRating;
    }

    public String getText() {
        return text;
    }

    public String getPostDate() {
        return postDate;
    }

    public String getPersonWhoWroteIt() {
        return personWhoWroteIt;
    }

    public double getUserRating() {
        return userRating;
    }

    public Long getReviewId() { return reviewId; }

    public String getMovieId() { return movieId; }
}
