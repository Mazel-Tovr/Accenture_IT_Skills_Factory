package com.accenture.oopapp.datalayer.jpadata;

import com.accenture.oopapp.datalayer.jpadata.interfaces.GenreOperationJPA;
import com.accenture.oopapp.datalayer.jpadata.interfaces.MovieOperationJPA;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.GenreModel;
import com.accenture.oopapp.model.films.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


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
    private UseFullTools useFullTools;
    @Autowired
    private GenreOperationJPA genreOperationJPA;

    @Override
    public List<Movie> searchMovieByGenre(Genre... genre)
    {
        //Почему это не робит, а нижнее робит, я не понимаю, хотя, в обычном sql все норм, наверное, это скорее все изи связи MtoM в сущносте.PS ya vse ponyal ya dyrak
        //String query = "SELECT m FROM Movie m INNER JOIN genres gs ON gs.film_id = m.movieid INNER JOIN genre g ON gs.genre = g.genre_id Where "+s;

        String query = "SELECT m FROM Movie m JOIN m.genres g Where g IN "+useFullTools.createGenreWhereInQuery(genre);
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
    public List<Movie> searchByRating(double from, double to)
    {
        TypedQuery<Movie> typedQuery = entityManager.createQuery("Select m from Movie m WHERE m.rating BETWEEN ?1 AND ?2",Movie.class);
        typedQuery.setParameter(1,from);
        typedQuery.setParameter(2,to);
        return  typedQuery.getResultList();
    }

    @Override
    public List<Movie> getAllMovie()
    {
        TypedQuery<Movie> q = entityManager.createQuery(
                "Select c from Movie c", Movie.class);
        return  q.getResultList();
    }

    @Override
    public void addMovie(Movie movie)
    {
        entityManager.persist(movie);
    }
}
