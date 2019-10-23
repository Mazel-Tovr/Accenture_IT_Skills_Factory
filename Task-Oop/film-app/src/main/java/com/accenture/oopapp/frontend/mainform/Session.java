package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.users.User;

public class Session
{
    private User currentUser;
    User getCurrentUser() { return currentUser; }

    private Movie movie;
    Movie getMovie() { return movie; }
    void setMovie(Movie movie) { this.movie = movie; }

    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }

    void logOut()
    {
        currentUser = null;
        movie = null;
    }

}
