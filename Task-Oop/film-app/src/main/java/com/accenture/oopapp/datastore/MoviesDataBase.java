package com.accenture.oopapp.datastore;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.films.Review;
import com.accenture.oopapp.frontend.FilmApp;

import java.util.*;


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

    public List<Movie> idSearch(String text)
    {
        List<Movie> tempo = new ArrayList<>(10);
        for (var item :movieSet)
        {
            if(item.getMovieId().contains(text))
            {
                tempo.add(item);
            }
        }
        return tempo;
    }
    public List<Movie> nameSearch(String text)
    {
        List<Movie> tempo = new ArrayList<>(10);
        for (var item : movieSet) {
            if (item.getMovieName().contains(text)) {
                tempo.add(item);
            }
        }
        return tempo;
    }
    public List<Movie> dataSearch(String text)
    {
        List<Movie> tempo = new ArrayList<>(10);
        for (var item : movieSet)
        {
            if (item.getReleaseDate().contains(text)) {
                tempo.add(item);
            }
        }
        return tempo;
    }


    public Set<Movie> getMovieSet() { return movieSet; }

}
