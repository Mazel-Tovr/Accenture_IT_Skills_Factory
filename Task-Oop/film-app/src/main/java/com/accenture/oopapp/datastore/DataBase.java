package com.accenture.oopapp.datastore;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.users.Administrator;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.User;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class DataBase
{
    private Set<User> userSet  = new HashSet<>();;
    private Set<Administrator> administratorSet = new HashSet<>();;
    private Set<Movie> movieSet = new HashSet<>();

    {
        userSet.add(new User("User",54, Gender.FEMALE,"User","User"));
        movieSet.add(new Movie("Movie1","Фильм", MovieType.FILM,EnumSet.of(Genre.ADVENTURE,Genre.COMEDY),"20.01.2019"));
    }
//    public Set<User> getUserSet() { return userSet; }
//
//    public Set<Administrator> getAdministratorSet() { return administratorSet; }
//
//    public Set<Movie> getMovieSet() { return movieSet; }

    public boolean addUserToUserSet(User user) { return userSet.add(user); }

    public boolean addAdministratorToAdministratorSet(Administrator administrator) { return administratorSet.add(administrator); }

    public boolean addMovieTOMovieSet(Movie movie) { return movieSet.add(movie); }

    public Set<Movie> getMovieSet() { return movieSet; }

    public void setMovieSet(Set<Movie> movieSet) {
        this.movieSet = movieSet;
    }

    /*
        Тут все очень плохо по поиску 19.10
        Шас будет еше хуже 20.10
         */
    private User lastEnteredUser = null;

    public User getLastEnteredUser() { return lastEnteredUser; }

    public boolean isUserExist(String nickName)
     {
         for (var item:userSet)
         {
           if(item.getNickName().equals(nickName))
           {
               return true;
           }
         }
         return false;
     }
     public boolean isConnect(String nickName,String password)
     {
         for (var item:userSet)
         {
             if(item.getNickName().equals(nickName) && item.getPassWord().equals(password))
             {
                 lastEnteredUser = item;
                 return true;
             }
         }
         return false;
     }

//     public User getPerson(String login,String password)
//     {
//         for (var item:userSet)
//         {
//             if(item.getNickName().equals(login) && item.getPassWord().equals(password))
//             {
//                 return item;
//             }
//         }
//         return null;
//     }

}
