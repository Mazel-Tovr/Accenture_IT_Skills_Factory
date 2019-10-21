package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.users.Person;

public class Session
{
    private Person lastEnteredUser;
    Person getLastEnteredUser() { return lastEnteredUser; }

    private Movie movie;
    Movie getMovie() { return movie; }
    void setMovie(Movie movie) { this.movie = movie; }

    public void setLastEnteredUser(Person lastEnteredUser) { this.lastEnteredUser = lastEnteredUser; }

    void logOut()
    {
        lastEnteredUser = null;
        movie = null;
    }
}
