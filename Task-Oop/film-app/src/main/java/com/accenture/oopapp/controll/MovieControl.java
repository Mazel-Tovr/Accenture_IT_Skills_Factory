package com.accenture.oopapp.controll;

import com.accenture.oopapp.businesslayer.datacontrol.InputDataException;
import com.accenture.oopapp.businesslayer.main.FilmPage;
import com.accenture.oopapp.businesslayer.main.MovieService;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.Review;
import com.accenture.oopapp.mysqldatabase.interfaces.ReviewOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController("/movie")
public class MovieControl
{
    @Autowired
    private MovieService movieService;
    @Autowired
    private FilmPage filmPage;

    @RequestMapping(value = "/movie",method = RequestMethod.GET)
    public List<Movie> getAll()
    {
     return movieService.getAll();
    }

    @RequestMapping(value = "/movie/{id}",method = RequestMethod.GET)
    public List<Movie> getMovieById(@PathVariable(value = "id") String id)
    {
        try
        {
            return movieService.searchBy("movieId",id);

        }
        catch (InputDataException e)
        {
            e.getMessage();
            return null;
        }
    }
    @RequestMapping(value = "/movie/{id}/review")
    public Object[] getFilmsReview(@PathVariable(value = "id") String id)
    {
        return filmPage.getAllMovieDetails(id);
    }
    
}
