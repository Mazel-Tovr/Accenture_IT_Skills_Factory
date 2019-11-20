package com.accenture.oopapp.datalayer.jpadata;

import com.accenture.oopapp.datalayer.jpadata.interfaces.GenreOperationJPA;
import com.accenture.oopapp.datalayer.jpadata.interfaces.MovieOperationJPA;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.GenreModel;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.MovieType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class MovieTableJPA implements MovieOperationJPA
{
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private QueryBuilder queryBuilder;
    @Autowired
    private GenreOperationJPA genreOperationJPA;

    @Override
    public List<Movie> searchMovieByGenre(Genre... genre)
    {

        //String query = "SELECT m FROM Movie m INNER JOIN genres gs ON gs.film_id = m.movieid INNER JOIN genre g ON gs.genre = g.genre_id Where "+s;

        String query = "SELECT m FROM Movie m JOIN m.genres g Where g IN "+ queryBuilder.createInQuery(genre.length);
        TypedQuery<Movie> typedQuery = entityManager.createQuery(query,Movie.class);

        int i = 1;
        Set<GenreModel> genreModels = genreOperationJPA.getGenreByName(genre);
        for (GenreModel item :genreModels)
        {
            typedQuery.setParameter(i,item);
            i++;
        }

        Set<Movie> movies = new HashSet<>();
        for (Movie item: typedQuery.getResultList())
        {
            if(item.getGenres().containsAll(genreModels))
            {
                movies.add(item);
            }
        }
        return new ArrayList<>(movies);
    }

    @Override
    public List<Movie> searchMovieByType(MovieType... type)
    {
        TypedQuery<Movie> typedQuery = entityManager.createQuery("Select m from Movie m WHERE m.movieType IN "+queryBuilder.createInQuery(type.length),Movie.class);
        for (int i = 0; i <type.length ; i++)
        {
            typedQuery.setParameter((i+1),type[i]);
        }

        return typedQuery.getResultList();
    }

    
    @Override
    public List<Movie> searchByRating(double from, double to)
    {
        TypedQuery<Movie> typedQuery = entityManager.createQuery("Select m from Movie m WHERE m.rating BETWEEN ?1 AND ?2",Movie.class);
        typedQuery.setParameter(1,from);
        typedQuery.setParameter(2,to);
        return  typedQuery.getResultList();
    }

    @Transactional
    @Override
    public void removeMovie(Movie movie)
    {
        entityManager.remove(movie);
    }


    @Override
    public List<Movie> getMovieList()
    {
        TypedQuery<Movie> q = entityManager.createQuery(
                "Select c from Movie c", Movie.class);
        return  q.getResultList();
    }

    @Override
    public Movie getMovie(String movieId)
    {
        TypedQuery<Movie> q = entityManager.createQuery(
                "Select c from Movie c Where c.movieId =: movieId", Movie.class);
        q.setParameter("movieId",movieId);
        return q.getSingleResult();
    }

    @Transactional
    @Override
    public void addMoveToDataBase(Movie movie) { entityManager.persist(movie); }

    @Override
    public List<Movie> search(String filter, String text)
    {
        return null;
    }
}
