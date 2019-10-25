package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.users.User;

public class Session implements SessionService
{
    private static Session instance = null;
    private Session(){}
    public static Session getInstance()
    {
     if(instance == null)
     {
         synchronized (Session.class)
         {
             if(instance == null)
             {
                 instance = new Session();
             }
         }
     }
     return instance;
    }

    private User currentUser;
    private Movie movie;

    public User getCurrentUser() { return currentUser; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }

    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }

    public void logOut()
    {
        currentUser = null;
        movie = null;
    }

}
