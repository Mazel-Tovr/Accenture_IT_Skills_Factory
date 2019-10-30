package com.accenture.oopapp.mysqldatabase.interfaces;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.Review;
import com.accenture.oopapp.users.User;

import java.util.List;

public interface ReviewOperation
{
    List<Review> getFilmsReview(Movie movie);
    boolean removeReview(Review review);
    void addReview(Movie movie, User user, String text, double rating);
    void editingReview(Review review,String text);
}
