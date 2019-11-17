package com.accenture.oopapp.businesslayer.jpa;

import com.accenture.oopapp.datalayer.jpadata.interfaces.MovieOperationJPA;
import com.accenture.oopapp.datalayer.mysqldatabase.interfaces.MovieOperation;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.GenreModel;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieJPA
{

    @Autowired
    private MovieOperationJPA movieOperationJPA;
    @Autowired
    private EntityManager entityManager;

    public List<Movie> getMovieByMovieType(String genre)
    {
//        Query query = entityManager.createQuery("Select c from Movie c Where c.movieType =: movietype",Movie.class);
//        query.setParameter("movietype", MovieType.valueOf(genre.toUpperCase()));
        return null;//query.getResultList();
    }

    @Transactional
    public void addMovie()
    {
        Set<GenreModel> genreModelSet = new HashSet<>();
        genreModelSet.add(entityManager.createQuery("Select g from GenreModel g Where g.genreId = 1 ",GenreModel.class).getSingleResult());
        Movie movie = new Movie("forTest", "AddTest", MovieType.FILM, genreModelSet , "2018.10.14", "some",0 );
        movieOperationJPA.addMovie(movie);

    }
}
