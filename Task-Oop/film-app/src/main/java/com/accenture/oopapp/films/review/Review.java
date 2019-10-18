package com.accenture.oopapp.films.review;


import com.accenture.oopapp.users.Person;

public class Review
{
   private String textOfReview;
   private String postDate;
   private Person personWhoWroteIt;
   private double youLikedFilm;

    public Review(String textOfReview, String postDate,Person person,double youLikedFilm)
    {
        this.textOfReview = textOfReview;
        this.postDate = postDate;
        personWhoWroteIt = person;
        this.youLikedFilm = youLikedFilm;
    }
    public String getTextOfReview() { return textOfReview; }

    public void setTextOfReview(String textOfReview) { this.textOfReview = textOfReview; }

    public String getPostDate() { return postDate; }

    public double howYouLikedFilm() { return youLikedFilm; }
}
