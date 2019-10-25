package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.users.User;

public interface SessionService
{
    User getCurrentUser();
    Movie getMovie();
    void setMovie(Movie movie);
    void setCurrentUser(User currentUser);
    void logOut();
}
