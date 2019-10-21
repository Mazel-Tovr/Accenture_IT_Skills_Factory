package com.accenture.oopapp.films.review;


import com.accenture.oopapp.users.Person;
import com.accenture.oopapp.users.User;

public class Review {
    private String textOfReview;
    private String postDate;
    private String personWhoWroteIt;
    private double youLikedFilm;

    public Review(String textOfReview, String postDate, User person, double youLikedFilm) {
        this.textOfReview = textOfReview;
        this.postDate = postDate;
        personWhoWroteIt = person.getNickName();
        this.youLikedFilm = youLikedFilm;

    }

    public String getTextOfReview() {
        return textOfReview;
    }

    public void setTextOfReview(String textOfReview) {
        this.textOfReview = textOfReview;
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
