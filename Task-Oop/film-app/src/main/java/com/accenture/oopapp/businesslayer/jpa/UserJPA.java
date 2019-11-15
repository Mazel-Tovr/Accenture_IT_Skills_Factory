package com.accenture.oopapp.businesslayer.jpa;

import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.Review;
import com.accenture.oopapp.model.users.User;

import java.util.List;

public interface UserJPA
{
    List<User> getAll();
    List<Review> getAllRev();
    List<Movie> getAllMovie();
}
