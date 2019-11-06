package com.accenture.oopapp.controll;

import com.accenture.oopapp.businesslayer.datacontrol.InputDataException;
import com.accenture.oopapp.businesslayer.main.FilmPage;
import com.accenture.oopapp.businesslayer.main.MovieService;
import com.accenture.oopapp.model.films.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController("/movie")
public class MovieAndReviewControl
{
    @Autowired
    private MovieService movieService;
    @Autowired
    private FilmPage filmPage;//short info about fill and all reviews to it

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
    @RequestMapping(value = "/movie/{id}/review",method = RequestMethod.GET)
    public Object[] getFilmsReview(@PathVariable(value = "id") String id)
    {
        return filmPage.getAllMovieDetails(id);
    }

    //http://localhost:8080/movie/shit/review/delete/16
    @RequestMapping(value = "/movie/{id}/review/delete/{reviewId}",method=RequestMethod.DELETE)
    public void deleteReview(@PathVariable(value = "reviewId") Integer reviewId) throws InputDataException
    {
        filmPage.deleteReview(reviewId);
    }

    //http://localhost:8080/movie/Movie9/review/add?nickname=User&txt=some txt idk&rating=60
    @RequestMapping(value="/movie/{id}/review/add",method = RequestMethod.PUT)
    public void addReview(@PathVariable(value = "id") String id,@RequestParam(value = "nickname") String nickName,
                          @RequestParam(value = "txt") String txt,@RequestParam(value = "rating") Double rating) throws InputDataException
    {
        filmPage.addReview(id,nickName,txt,rating);
    }
}
