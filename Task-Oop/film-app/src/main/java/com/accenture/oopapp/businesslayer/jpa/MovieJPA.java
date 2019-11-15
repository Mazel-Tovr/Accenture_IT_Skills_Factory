package com.accenture.oopapp.businesslayer.jpa;

import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.MovieType;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
public class MovieJPA
{
    @PersistenceContext
    private EntityManager entityManager;


    public List<Movie> getMovieByMovieType(String genre)
    {
        Query query = entityManager.createQuery("Select c from Movie c Where c.movieType =: movietype",Movie.class);
        query.setParameter("movietype", MovieType.valueOf(genre.toUpperCase()));
        return query.getResultList();
    }
}
