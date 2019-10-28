package com.accenture.oopapp.films;


import com.accenture.oopapp.users.User;

public class Review
{
    private int reviewId;
    private String text;
    private String postDate;
    private String personWhoWroteIt;
    private double userRating;

    public Review(int reviewId,String text, String postDate, String user, double userRating)
    {
        this.reviewId = reviewId;
        this.text = text;
        this.postDate = postDate;
        personWhoWroteIt = user;
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

    public int getReviewId() { return reviewId; }

}
