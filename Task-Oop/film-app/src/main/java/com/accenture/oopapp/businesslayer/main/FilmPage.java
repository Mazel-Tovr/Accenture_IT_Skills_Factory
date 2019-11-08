package com.accenture.oopapp.businesslayer.main;

import com.accenture.oopapp.businesslayer.exceptionhandler.InDataControl;
import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.Review;
import com.accenture.oopapp.mysqldatabase.interfaces.MovieOperation;
import com.accenture.oopapp.mysqldatabase.interfaces.ReviewOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmPage
{
    @Autowired
    private InDataControl inDataControl;
    @Autowired
    private MovieOperation movieOperation;
    @Autowired
    private ReviewOperation reviewOperation;

    public Object[] getAllMovieDetails(String movieId)
    {
        Object[] objects = new Object[2];
        objects[0] = movieOperation.getMovie(movieId);
        objects[1] = reviewOperation.getFilmsReview((Movie)objects[0]);
        return objects;
    }

    public void addReview(String movieId,String userId,String txt,Double rating) throws InputDataException
    {
        if(!inDataControl.ratingCheck(rating) && !inDataControl.notEmptyField(txt))
        {
            throw new InputDataException("Вы должны ввети текст отзыва,а также поставить оценку фильму (от 0 до 100) ");
        }
        reviewOperation.addReview(movieId,userId,txt,rating);
    }
    public boolean deleteReview(String movieId,Long reviewId) throws InputDataException
    {
        if(!inDataControl.notNullValue(reviewId))
        {
            throw new InputDataException("Введите id отызва");
        }
        if(!reviewOperation.getReview(reviewId).getMovieId().equals(movieId))
        {
            throw new InputDataException("Отзыв не относится к данному фильму");
        }
        return reviewOperation.removeReview(reviewId);
    }

   public void editReview(String movieId,Long reviewId,String text) throws InputDataException
   {
       if(reviewOperation.getReview(reviewId).getMovieId().equals(movieId))
       {
           reviewOperation.editingReview(reviewId, text);
       }
       else
       {
           throw new InputDataException("Отзыв не относится к данному фильму");
       }
   }

}
