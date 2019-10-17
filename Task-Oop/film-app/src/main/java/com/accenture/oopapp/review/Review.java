package com.accenture.oopapp.review;


public class Review
{
   private String textOfReview;
   private String postDate;

    public Review(String textOfReview, String postDate)
    {
        this.textOfReview = textOfReview;
        this.postDate = postDate;

    }
    public String getTextOfReview() { return textOfReview; }

    public void setTextOfReview(String textOfReview) { this.textOfReview = textOfReview; }

    public String getPostDate() { return postDate; }

}
