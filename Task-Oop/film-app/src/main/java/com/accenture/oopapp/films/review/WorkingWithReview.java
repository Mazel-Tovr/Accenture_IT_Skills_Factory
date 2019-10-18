package com.accenture.oopapp.films.review;

import com.accenture.oopapp.films.Movie;

import java.util.List;

public interface WorkingWithReview
{
   Movie getMovie(List<Movie>movies);
   void peekReview(List<Review> reviews ,int index);

   boolean postReview(Review review);

   void editReview(Review review);
}
