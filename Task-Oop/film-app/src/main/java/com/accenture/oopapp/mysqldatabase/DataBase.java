package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.Review;
import com.accenture.oopapp.users.User;

import java.sql.SQLException;
import java.util.List;

public interface DataBase
{
    List<Movie> getMovieList();
    boolean isUserExist(String nickName);
    boolean isConnect(String nickName,String password);
    void addUserToDataBase(User user);
    List<Movie> idSearch(String text);
    List<Movie> nameSearch(String text);
    List<Movie> dataSearch(String text);
    List<Review> getFilmsReview(Movie movie);
    boolean removeReview(Review review);
    void addReview(Movie movie,User user, String text, double rating);
    void editingReview(Review review,String text);
    void recalculateFilmRating(Movie movie);
    void addMoveToDataBase(Movie movie);
}
