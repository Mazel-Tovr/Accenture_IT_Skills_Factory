package com.accenture.oopapp.model.films;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review
{
    @Id
    @Column(name = "reviewid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @Column(name = "movieid")
    private String movieId;
    @Column(name = "text")
    private String text;
    @Column(name = "postdata")
    private String postDate;
    @Column(name = "userid")
    private String personWhoWroteIt;
    @Column(name = "userrating")
    private double userRating;

    public Review(){}

    public Review(Long reviewId, String movieId, String text, String postDate, String personWhoWroteIt, double userRating)
    {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.text = text;
        this.postDate = postDate;
        this.personWhoWroteIt = personWhoWroteIt;
        this.userRating = userRating;
    }

    //В бд айди авто инкрисемент стоит
    public Review(String movieId, String text, String postDate, String personWhoWroteIt, double userRating)
    {
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
