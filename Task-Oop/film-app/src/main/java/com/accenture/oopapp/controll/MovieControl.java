package com.accenture.oopapp.controll;

import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;
import com.accenture.oopapp.businesslayer.main.interfaces.MovieBusinessLayer;
import com.accenture.oopapp.model.films.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController("/movie")
public class MovieControl
{
    @Autowired
    private MovieBusinessLayer movieBusinessLayer;
    //http://localhost:8080/movie
    @RequestMapping(value = "/movie",method = RequestMethod.GET)
    public List<Movie> getAll()
    {
         return movieBusinessLayer.getAll();
    }
    //http://localhost:8080/movie/Movie9
    @RequestMapping(value = "/movie/{id}",method = RequestMethod.GET)
    public Object[]  getMovieById(@PathVariable(value = "id") String id) { return movieBusinessLayer.getAllMovieDetails(id); }

    //http://localhost:8080/movie/genre?genre=action,adventure
    @RequestMapping(value = "/movie/genre",method=RequestMethod.GET)
    public List<Movie> getAllTitleType(@RequestParam(value = "genre") String genre)
    {
        return movieBusinessLayer.getMovieByGenre(genre);
    }
    //http://localhost:8080/movie/titletype?type=film,short
    @RequestMapping(value = "/movie/titletype",method=RequestMethod.GET)
    public List<Movie> getAllGenres(@RequestParam(value = "type") String type)
    {
        return movieBusinessLayer.getMovieByMovieType(type);
    }
    //http://localhost:8080/movie/rating?from=0.0&to=50.0
    @RequestMapping(value = "/movie/rating",method=RequestMethod.GET)
    public List<Movie> getMovieByRating(@RequestParam(value = "from") double from,@RequestParam(value = "to") double to) throws InputDataException
    {
        return movieBusinessLayer.getMovieByRating(from,to);
    }

    //http://localhost:8080/movie/add?movieid=Film288&moviename=why&movietype=film&genres=action,horror&data=2019.11.19&description= some txt&rating=50.0
    @RequestMapping(value="/movie/add",method = RequestMethod.PUT)
    public void addMovie(@RequestParam(value = "movieid") String movieId,@RequestParam(value = "moviename") String movieName,
                         @RequestParam(value = "movietype") String type,@RequestParam(value = "genres") String genres,@RequestParam(value = "data") String data,
                         @RequestParam(value = "description") String description,@RequestParam(value = "rating") double rating)
    {
        movieBusinessLayer.addMovieToBase(movieId,movieName,type,genres,data,description,rating);
    }

}
