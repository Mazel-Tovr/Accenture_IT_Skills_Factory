package com.accenture.oopapp.controll;

import com.accenture.oopapp.businesslayer.main.ReviewService;
import com.accenture.oopapp.businesslayer.main.MovieService;
import com.accenture.oopapp.model.films.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController("/movie")
public class MovieControl
{
    @Autowired
    private MovieService movieService;
    @Autowired
    private ReviewService reviewService;//short info about fill and all reviews to it

    @RequestMapping(value = "/movie",method = RequestMethod.GET)
    public List<Movie> getAll()
    {
         return movieService.getAll();
    }

    @RequestMapping(value = "/movie/{id}",method = RequestMethod.GET)
    public Object[]  getMovieById(@PathVariable(value = "id") String id)
    {
            return movieService.getAllMovieDetails(id);
    }



}
