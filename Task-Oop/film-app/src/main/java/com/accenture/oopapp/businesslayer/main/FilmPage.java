package com.accenture.oopapp.businesslayer.main;

import com.accenture.oopapp.businesslayer.datacontrol.InDataControl;
import com.accenture.oopapp.businesslayer.datacontrol.InputDataException;
import com.accenture.oopapp.model.films.Movie;
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
      Movie movie = movieOperation.getMovie(movieId);
      objects[0]=movie;
      objects[1]=reviewOperation.getFilmsReview(movie);
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
    public boolean deleteReview(Integer reviewId) throws InputDataException {
        if(!inDataControl.notNullValue(reviewId))
        {
            throw new InputDataException("Введите id отызва");
        }
        return reviewOperation.removeReview(reviewId);
    }
}
