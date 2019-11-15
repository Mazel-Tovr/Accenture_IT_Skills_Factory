package com.accenture.oopapp.businesslayer.jpa;

import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.Review;
import com.accenture.oopapp.model.users.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserJPAClass implements UserJPA
{

    @PersistenceContext
    private EntityManager entityManager;//problem here

    @Override
    public List<User> getAll()
    {
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> cq = builder.createQuery(User.class);
//        Root<User> root = cq.from(User.class);
//        cq.select(root);
//        return entityManager.createQuery(cq).getResultList();
        TypedQuery<User> q = entityManager.createQuery(
                "Select c from User c", User.class);
        return  q.getResultList();
    }
    public List<Review> getAllRev()
    {
        TypedQuery<Review> q = entityManager.createQuery(
                "Select c from Review c", Review.class);
        return  q.getResultList();
    }
    public List<Movie> getAllMovie()
    {
        TypedQuery<Movie> q = entityManager.createQuery(
                "Select c from Movie c", Movie.class);
        return  q.getResultList();
    }
}
