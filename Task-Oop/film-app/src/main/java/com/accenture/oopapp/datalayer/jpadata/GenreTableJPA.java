package com.accenture.oopapp.datalayer.jpadata;

import com.accenture.oopapp.datalayer.jpadata.interfaces.GenreOperationJPA;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.GenreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

@Repository
public class GenreTableJPA implements GenreOperationJPA
{

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private QueryBuilder queryBuilder;

    @Override
    public GenreModel getGenreById(Long id)
    {
        TypedQuery<GenreModel> typedQuery = entityManager.createQuery("Select g from GenreModel g Where g.genreId = ?1",GenreModel.class);
        typedQuery.setParameter(1,id);
        return typedQuery.getSingleResult();
    }

    @Override
    public GenreModel getGenreByName(Genre genre)
    {
        TypedQuery<GenreModel> typedQuery = entityManager.createQuery("Select g from GenreModel g Where g.genre = ?1",GenreModel.class);
        typedQuery.setParameter(1, genre);
        return typedQuery.getSingleResult();
    }

    @Override
    public Set<GenreModel> getGenreByName(Genre... genres)
    {
        String query = "Select g from GenreModel g Where g.genre IN "+ queryBuilder.createInQuery(genres.length);
        TypedQuery<GenreModel> typedQuery = entityManager.createQuery(query,GenreModel.class);
        for (int i = 1; i <= genres.length; i++)
        {
            typedQuery.setParameter(i,genres[i-1]);
        }
        return  new HashSet<GenreModel>(typedQuery.getResultList());
    }


}
