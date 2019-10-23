package com.accenture.oopapp.datastore;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.films.review.Review;
import com.accenture.oopapp.frontend.FilmApp;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoviesDataBase
{
    private Set<Movie> movieSet = new HashSet<>();

    {
        movieSet.add(new Movie("Movie1","На парах", MovieType.SERIAL, EnumSet.of(Genre.HORROR,Genre.COMEDY),"2018.01.21","Описание"));
        movieSet.add(new Movie("Movie2","Самый", MovieType.FILM,EnumSet.of(Genre.ADVENTURE,Genre.COMEDY),"2019.01.20",""));
        movieSet.add(new Movie("Movie3","Худший", MovieType.FILM,EnumSet.of(Genre.ADVENTURE),"2018.12.01",""));
        movieSet.add(new Movie("Movie4","Фильм", MovieType.FILM,EnumSet.of(Genre.ADVENTURE,Genre.COMEDY,Genre.HORROR),"2001.12.19",""));

        movieSet.iterator().next().getFilmsReview().add(new Review("Норм","2007.07.07", FilmApp.usersDataBase.getUserMap().iterator().next(),60));
        movieSet.iterator().next().recalculateFilmRating();
    }
    public boolean addMovieToMovieSet(Movie movie) { return movieSet.add(movie); }

    public Set<Movie> idSearch(String text)
    {
        Set<Movie> tempo = new HashSet<>();
        for (var item:movieSet)
        {
            if(item.getMovieId().contains(text))
            {
                tempo.add(item);
            }
        }
        return tempo;
    }



    public Set<Movie> getMovieSet() { return movieSet; }

}
