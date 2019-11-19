package com.accenture.oopapp.businesslayer.main;

import com.accenture.oopapp.businesslayer.exceptionhandler.InDataControl;
import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;
import com.accenture.oopapp.businesslayer.main.interfaces.MovieBusinessLayer;
import com.accenture.oopapp.datalayer.jpadata.MovieTableJPA;
import com.accenture.oopapp.datalayer.jpadata.interfaces.GenreOperationJPA;
import com.accenture.oopapp.datalayer.jpadata.interfaces.MovieOperationJPA;
import com.accenture.oopapp.datalayer.mysqldatabase.interfaces.ReviewOperation;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.GenreModel;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import javax.inject.Qualifier;
import java.util.List;
import java.util.Set;

@Service
public class MovieService implements MovieBusinessLayer
{
    @Autowired
    private InDataControl inDataControl;
    @Autowired
    private MovieOperationJPA movieOperationJPA;
    @Autowired
    private ReviewOperation reviewOperation;
    @Autowired
    private GenreOperationJPA genreOperationJPA;

    public List<Movie> getAll()
    {
       return movieOperationJPA.getMovieList();
    }

    public Object[] getAllMovieDetails(String movieId)
    {
        Object[] objects = new Object[2];
        objects[0] = movieOperationJPA.getMovie(movieId);
        objects[1] = reviewOperation.getFilmsReview((Movie)objects[0]);
        return objects;
    }

    @Override
    public void addMovieToBase(String movieId, String movieName, String movieType, String genres, String releaseDate, String description, double rating)
    {
        Movie movie = new Movie(movieId,movieName,MovieType.valueOf(movieType.toUpperCase()),
                genreOperationJPA.getGenreByName(getGenreArray(genres)),releaseDate,description,rating);
        movieOperationJPA.addMoveToDataBase(movie);
    }

    public List<Movie> searchBy(String filter,String txt) throws InputDataException
    {
        if(!inDataControl.filterValidation(filter))
        {
            throw new InputDataException("Неподходящий фильтер поиска");
        }
        return movieOperationJPA.search(filter,txt);
    }
    public List<Movie> getMovieByMovieType(String movieType)
    {
        return movieOperationJPA.searchMovieByType(MovieType.valueOf(movieType.toUpperCase()));
    }

    public List<Movie> getMovieByGenre(String genre)
    {
        return movieOperationJPA.searchMovieByGenre(getGenreArray(genre));
    }

    @Override
    public List<Movie> getMovieByRating(double from, double to)throws InputDataException
    {
        if(!inDataControl.ratingCheck(from)&&!inDataControl.ratingCheck(to))
        {
            throw new InputDataException("Допустимые занчения рейтинга от 0 до 100");
        }
        return movieOperationJPA.searchByRating(from,to);
    }

    private Genre[] getGenreArray(String genre)
    {
        String[] genreArray= genre.split("\\s*(\\s|,|!|\\.)\\s*");
        Genre[] genres = new Genre[genreArray.length];
        for (int i = 0; i <genreArray.length ; i++)
        {
            genres[i] = Genre.valueOf(genreArray[i].toUpperCase());
        }
        return genres;
    }
}
