package com.accenture.oopapp.datastore;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.films.review.Review;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.users.Administrator;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.Person;
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
        administratorSet.add(new Administrator("Admin ce god",54,Gender.MALE,"Admin","Admin"));
        movieSet.add(new Movie("Movie1","На парах", MovieType.SERIAL,EnumSet.of(Genre.HORROR,Genre.COMEDY),"2018.01.21","Описание"));
        movieSet.add(new Movie("Movie2","Самый", MovieType.FILM,EnumSet.of(Genre.ADVENTURE,Genre.COMEDY),"2019.01.20",""));
        movieSet.add(new Movie("Movie3","Худший", MovieType.FILM,EnumSet.of(Genre.ADVENTURE),"2018.12.01",""));
        movieSet.add(new Movie("Movie4","Фильм", MovieType.FILM,EnumSet.of(Genre.ADVENTURE,Genre.COMEDY,Genre.HORROR),"2001.12.19",""));

        movieSet.iterator().next().getFilmsReview().add(new Review("Норм","2007.07.07",userSet.iterator().next(),60));
        movieSet.iterator().next().recalculateFilmRating();

    }

    public boolean addUserToUserSet(User user) { return userSet.add(user); }

    public boolean addAdministratorToAdministratorSet(Administrator administrator) { return administratorSet.add(administrator); }

    public boolean addMovieToMovieSet(Movie movie) { return movieSet.add(movie); }

    public Set<Movie> getMovieSet() { return movieSet; }


    /*
        Тут все очень плохо по поиску 19.10
        Шас будет еше хуже 20.10
        Тут все плохо
    */
    public boolean isUserExist(String nickName)
     {
         for (Administrator item : administratorSet)
         {
             if(item.getNickName().equals(nickName))
             {
                 return true;
             }
         }

         for (User item :userSet)
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
         for (Administrator item : administratorSet)
         {
             if(item.getNickName().equals(nickName) && item.getPassWord().equals(password))
             {
                 FilmApp.session.setLastEnteredUser(item);
                 return true;
             }
         }
         for (User item:userSet)
         {
             if(item.getNickName().equals(nickName) && item.getPassWord().equals(password))
             {
                 FilmApp.session.setLastEnteredUser(item);
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
