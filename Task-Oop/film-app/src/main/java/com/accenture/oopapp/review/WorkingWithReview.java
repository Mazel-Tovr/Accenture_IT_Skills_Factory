package com.accenture.oopapp.review;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.users.Person;

import java.util.List;

public class WorkingWithReview
{
   public List<Review> getUnModedReviewsFromFilm(Movie movie)
   {
     return movie.getReviewsOnModerations();
   }




}
