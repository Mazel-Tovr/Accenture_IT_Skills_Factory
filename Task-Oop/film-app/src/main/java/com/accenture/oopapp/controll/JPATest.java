package com.accenture.oopapp.controll;

import com.accenture.oopapp.businesslayer.jpa.UserJPA;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.Review;
import com.accenture.oopapp.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController( "/test")
public class JPATest
{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserJPA userJPA;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public List<User> getAll()
    {
        return userJPA.getAll();
    }
    @RequestMapping(value = "/test1",method = RequestMethod.GET)
    public List<Review> getAllRev()
    {
        return userJPA.getAllRev();
    }
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public List<Movie> getAllMovie()
    {
        return userJPA.getAllMovie();
    }
}
