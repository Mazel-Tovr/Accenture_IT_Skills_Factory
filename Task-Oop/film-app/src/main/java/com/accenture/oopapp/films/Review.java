package com.accenture.oopapp.films;


import com.accenture.oopapp.users.User;

public class Review {
    private String text;
    private String postDate;
    private String personWhoWroteIt;
    private double userRating;

    public Review(String text, String postDate, User user, double userRating)
    {
        this.text = text;
        this.postDate = postDate;
        personWhoWroteIt = user.getNickName();
        this.userRating = userRating;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

}
