package com.accenture.oopapp.datalayer.jpadata;

import com.accenture.oopapp.datalayer.jpadata.interfaces.MovieOperationJPA;
import com.accenture.oopapp.datalayer.mysqldatabase.ConnectToDB;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


import java.util.List;

@Repository
public class MovieTableJPA implements MovieOperationJPA
{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ConnectToDB dbConnection;

    @Override
    public List<Movie> searchByGenre(Genre... genre)
    {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < genre.length ; i++)
        {
                s.append("g.genre = ?").append(i + 1);
                s.append(i + 1 < genre.length ? " and " : "");//с and ничего не выдает с or все норм
        }
        //Почему это не робит, а нижнее робит, я не понимаю, хотя, в обычном sql все норм, наверное, это скорее все изи связи MtoM в сущносте
        //String query = "SELECT m FROM Movie m INNER JOIN genres gs ON gs.film_id = m.movieid INNER JOIN genre g ON gs.genre = g.genre_id Where "+s;
        String query = "SELECT m FROM Movie m JOIN m.genres g Where "+s;
        TypedQuery<Movie> typedQuery = entityManager.createQuery(query,Movie.class);
        for (int i = 1; i <= genre.length; i++)
        {
            typedQuery.setParameter(i,genre[i-1]);
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
