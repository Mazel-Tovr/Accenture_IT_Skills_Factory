package com.accenture.oopapp.controll;

import com.accenture.oopapp.datalayer.jpadata.interfaces.MovieOperationJPA;
import com.accenture.oopapp.datalayer.jpadata.interfaces.ReviewOperationJPA;
import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.Review;
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
    private MovieOperationJPA movieOperationJPA;

    @Autowired
    private ReviewOperationJPA reviewOperation;

//    @RequestMapping(value = "/test",method = RequestMethod.GET)
//    public List<User> getAll()
//    {
//        return userJPA.getAll();
//    }
//    @RequestMapping(value = "/test1",method = RequestMethod.GET)
//    public List<Review> getAllRev()
//    {
//        return userJPA.getAllRev();
//    }
    @RequestMapping(value = "/test2",method = RequestMethod.GET)
    public List<Movie> getAllMovie()  {
        return movieOperationJPA.searchMovieByGenre(Genre.HORROR);
    }
    @RequestMapping(value = "/test3",method = RequestMethod.GET)
    public List<Movie> getAllMovie3(){
        return movieOperationJPA.searchByRating(1,50);
    }
    @RequestMapping(value = "/test4",method = RequestMethod.GET)
    public void getAllMovie4()
    {
        reviewOperation.findById(1l);
    }
    @RequestMapping(value = "/test5",method = RequestMethod.GET)
    public Review getAllMovie5(){
        return null;
    }

}
