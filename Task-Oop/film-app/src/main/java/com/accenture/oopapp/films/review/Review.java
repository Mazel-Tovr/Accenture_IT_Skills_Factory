package com.accenture.oopapp.films.review;


import com.accenture.oopapp.users.User;

public class Review {
    private String text;
    private String postDate;
    private String personWhoWroteIt;
    private double youLikedFilm;

    public Review(String text, String postDate, User user, double youLikedFilm)
    {
        this.text = text;
        this.postDate = postDate;
        personWhoWroteIt = user.getNickName();
        this.youLikedFilm = youLikedFilm;

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

    public double getYouLikedFilm() {
        return youLikedFilm;
    }

}
