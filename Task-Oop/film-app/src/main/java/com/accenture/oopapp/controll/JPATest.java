package com.accenture.oopapp.controll;

import com.accenture.oopapp.businesslayer.jpa.MovieJPA;
import com.accenture.oopapp.businesslayer.jpa.UserJPA;
import com.accenture.oopapp.datalayer.jpadata.interfaces.MovieOperationJPA;
import com.accenture.oopapp.datalayer.mysqldatabase.interfaces.MovieOperation;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.Review;
import com.accenture.oopapp.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.SQLException;
import java.util.List;

@RestController( "/test")
public class JPATest
{
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UserJPA userJPA;
    //Бд
    @Autowired
    private MovieOperationJPA movieOperationJPA;
    @Autowired
    private MovieJPA movieJPA;

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
    public List<Movie> getAllMovie()  {
        return movieOperationJPA.searchByGenre(Genre.HORROR,Genre.ANIMATION);
    }
    @RequestMapping(value = "/test3",method = RequestMethod.GET)
    public List<Movie> getAllMovie3(){
        return movieOperationJPA.searchByRating(1,50);
    }
    @RequestMapping(value = "/test4",method = RequestMethod.GET)
    public void getAllMovie4(){
        movieJPA.addMovie();
    }

}
