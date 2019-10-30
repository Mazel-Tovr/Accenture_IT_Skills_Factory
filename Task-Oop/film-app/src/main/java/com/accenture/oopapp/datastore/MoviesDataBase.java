package com.accenture.oopapp.datastore;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.films.Review;
import com.accenture.oopapp.frontend.FilmApp;

import java.util.*;


public class MoviesDataBase implements MoviesDataBaseService
{

    private static MoviesDataBaseService instance = null;

    private MoviesDataBase(){}

    public static MoviesDataBaseService getInstance()
    {
        if(instance == null)
        {
            synchronized (MoviesDataBase.class)
            {
                if(instance == null)
                {
                    instance = new MoviesDataBase();
                }
            }
        }
        return instance;
    }

    private Set<Movie> movieSet = new HashSet<>();

    @Override
    public boolean addMovieToMovieSet(Movie movie) { return movieSet.add(movie); }

    public List<Movie> idSearch(String text)
        {
        List<Movie> tempo = new ArrayList<>(10);
        for (Movie item :movieSet)
        {
            if(item.getMovieId().contains(text))
            {
                tempo.add(item);
            }
        }
        return tempo;
    }
    @Override
    public List<Movie> nameSearch(String text)
    {
        List<Movie> tempo = new ArrayList<>(10);
        for (Movie item : movieSet) {
            if (item.getMovieName().contains(text)) {
               // item.getMovieName().matches(".*" + text + ".*");
                tempo.add(item);
            }
        }
        return tempo;
    }
    @Override
    public List<Movie> dataSearch(String text)
    {
        List<Movie> tempo = new ArrayList<>(10);
        for (Movie item : movieSet)
        {
            if (item.getReleaseDate().contains(text)) {
                tempo.add(item);
            }
        }
        return tempo;
    }

    @Override
    public Set<Movie> getMovieList() { return movieSet; }

}
