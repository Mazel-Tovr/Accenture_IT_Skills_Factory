package com.accenture.oopapp.businesslayer.main;

import com.accenture.oopapp.businesslayer.exceptionhandler.InDataControl;
import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;
import com.accenture.oopapp.businesslayer.main.interfaces.MovieBusinessLayer;
import com.accenture.oopapp.datalayer.jpadata.interfaces.MovieOperationJPA;
import com.accenture.oopapp.datalayer.mysqldatabase.interfaces.ReviewOperation;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MovieService implements MovieBusinessLayer
{
    @Autowired
    private InDataControl inDataControl;
    @Autowired
    private MovieOperationJPA movieOperation;
    @Autowired
    private ReviewOperation reviewOperation;

    public List<Movie> getAll()
    {
       return movieOperation.getMovieList();
    }

    public Object[] getAllMovieDetails(String movieId)
    {
        Object[] objects = new Object[2];
        objects[0] = movieOperation.getMovie(movieId);
        objects[1] = reviewOperation.getFilmsReview((Movie)objects[0]);
        return objects;
    }

    @Override
    public void addMovieToBase(String movieId, String movieName, String movieType, String genres, String releaseDate, String description, double rating)
    {
        //Ничего не происходит,потому что реализововать я буду через jpa
    }

    public List<Movie> searchBy(String filter,String txt) throws InputDataException
    {
        if(!inDataControl.filterValidation(filter))
        {
            throw new InputDataException("Неподходящий фильтер поиска");
        }
        return movieOperation.search(filter,txt);
    }
    public List<Movie> getMovieByMovieType(String genre)
    {
        String[] genreArray= genre.split("\\s*(\\s|,|!|\\.)\\s*");
        Genre[] genres = new Genre[genreArray.length];
        for (int i = 0; i <genreArray.length ; i++)
        {
            genres[i] = Genre.valueOf(genreArray[i]);
        }

//        Query query = entityManager.createQuery("Select c from Movie c Where c.movieType =: movietype",Movie.class);
//        query.setParameter("movietype", MovieType.valueOf(genre.toUpperCase()));
        return null;//query.getResultList();
    }

    public void addMovie()
    {
//        Set<GenreModel> genreModelSet = new HashSet<>();
//        genreModelSet.add(entityManager.createQuery("Select g from GenreModel g Where g.genreId = 1 ",GenreModel.class).getSingleResult());
//        Movie movie = new Movie("forTest", "AddTest", MovieType.FILM, genreModelSet , "2018.10.14", "some",0 );
//        movieOperationJPA.addMovie(movie);
//        genreOperationJPA.getGenreByName(Genre.valueOf("HORROR"),Genre.valueOf("ACTION")).forEach(a-> System.out.println(a.getGenre()));
    }
}
