package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.users.User;

public interface SessionService
{
    User getCurrentUser();
    Movie getMovie();
    void setMovie(Movie movie);
    void setCurrentUser(User currentUser);
    void logOut();
}
