package com.accenture.oopapp.controll;

import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;
import com.accenture.oopapp.businesslayer.main.ReviewService;
import com.accenture.oopapp.businesslayer.main.interfaces.MovieBusinessLayer;
import com.accenture.oopapp.model.films.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ReviewControl
{

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieBusinessLayer movieJPA;

    //http://localhost:8080/movie/shit/review/delete/16
    @RequestMapping(value = "/movie/{id}/review/delete/{reviewId}",method= RequestMethod.DELETE)
    public void deleteReview(@PathVariable(value = "id")String id , @PathVariable(value = "reviewId") Long reviewId) throws InputDataException
    {
        reviewService.deleteReview(id,reviewId);
    }

    //http://localhost:8080/movie/Movie9/review/add?nickname=User&txt=some txt idk&rating=60
    @RequestMapping(value="/movie/{id}/review/add",method = RequestMethod.PUT)
    public void addReview(@PathVariable(value = "id") String id,@RequestParam(value = "nickname") String nickName,
                          @RequestParam(value = "txt") String txt,@RequestParam(value = "rating") double rating) throws InputDataException
    {
        reviewService.addReview(id,nickName,txt,rating);
    }
    //http://localhost:8080/movie/Movie9/review/editing?reviewid=19&txt=some txt v3
    @RequestMapping(value="/movie/{id}/review/editing",method=RequestMethod.POST)
    public void editReview(@PathVariable(value = "id")String id ,@RequestParam(value = "reviewid") Long reviewId,@RequestParam(value = "txt") String txt) throws InputDataException
    {
        reviewService.editReview(id,reviewId,txt);
    }
    @RequestMapping(value = "/movie/genre",method=RequestMethod.GET)
    public List<Movie> getAllTitleType(@RequestParam(value = "type") String type)
    {
        return null;//movieJPA.getMovieByMovieType(type);
    }
    @RequestMapping(value = "/movie/titletype",method=RequestMethod.GET)
    public List<Movie> getAllGenres()
    {
        return null;
    }
}
