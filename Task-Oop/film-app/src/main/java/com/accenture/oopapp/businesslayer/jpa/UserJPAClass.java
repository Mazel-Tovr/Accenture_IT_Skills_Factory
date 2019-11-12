package com.accenture.oopapp.businesslayer.jpa;

import com.accenture.oopapp.model.users.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserJPAClass implements UserJPA
{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAll()
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = builder.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }
}
