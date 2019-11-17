package com.accenture.oopapp.datalayer.mysqldatabase.interfaces;

import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.Review;
import com.accenture.oopapp.model.users.User;

import java.util.List;

public interface ReviewOperation
{
    List<Review> getFilmsReview(Movie movie);
    Review getReview(Long reviewId);
    boolean removeReview(Review review);
    boolean removeReview(Long reviewId);
    void addReview(Movie movie, User user, String text, double rating);
    void addReview(String movie, String user, String text, double rating);
    void editingReview(Review review,String text);
    void editingReview(Long reviewId,String text);
}
