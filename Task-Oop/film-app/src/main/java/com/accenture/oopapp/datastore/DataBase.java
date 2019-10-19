package com.accenture.oopapp.datastore;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.users.*;

import java.util.HashSet;
import java.util.Set;

public class DataBase
{
    Set<User> userSet = new HashSet<>();
    Set<Administrator> administratorSet = new HashSet<>();
    Set<Movie> movieSet = new HashSet<>();

//    public Set<User> getUserSet() { return userSet; }
//
//    public Set<Administrator> getAdministratorSet() { return administratorSet; }
//
//    public Set<Movie> getMovieSet() { return movieSet; }

    public boolean addUserToUserSet(User user) { return userSet.add(user); }

    public boolean addAdministratorToAdministratorSet(Administrator administrator) { return administratorSet.add(administrator); }

    public boolean addMovieTOMovieSet(Movie movie) { return movieSet.add(movie); }

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
                 return true;
             }
         }
         return false;
     }
}
