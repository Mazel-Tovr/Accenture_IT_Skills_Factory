package com.accenture.oopapp.businesslayer.main;

import com.accenture.oopapp.businesslayer.exceptionhandler.InDataControl;
import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;
import com.accenture.oopapp.businesslayer.main.interfaces.ReviewBusinessLayer;
import com.accenture.oopapp.datalayer.jpadata.interfaces.ReviewOperationJPA;
import com.accenture.oopapp.model.films.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ReviewService implements ReviewBusinessLayer
{
    @Autowired
    private InDataControl inDataControl;
//    @Autowired
//    private ReviewOperation reviewOperation;

    //По тому что нужно было использвоать CrudRepository
    @Autowired
    private ReviewOperationJPA reviewOperationJPA;


    @Transactional
    public void addReview(String movieId,String userId,String txt,double rating) throws InputDataException
    {
        if(!inDataControl.ratingCheck(rating) && !inDataControl.notEmptyField(txt))
        {
            throw new InputDataException("Вы должны ввети текст отзыва,а также поставить оценку фильму (от 0 до 100) ");
        }
        //reviewOperation.addReview(movieId,userId,txt,rating);
        reviewOperationJPA.save(new Review(movieId,txt,new SimpleDateFormat("yyyy.MM.dd").format(new Date()),userId,rating));
        reviewOperationJPA.recalculateFilmRating(movieId);
    }

    @Transactional
    public void deleteReview(String movieId,Long reviewId) throws InputDataException
    {
        if(!inDataControl.notNullValue(reviewId))
        {
            throw new InputDataException("Введите id отызва");
        }
        if(!reviewOperationJPA.findById(reviewId).get().getMovieId().equals(movieId))
        {
            throw new InputDataException("Отзыв не относится к данному фильму");
        }
        // reviewOperation.removeReview(reviewId);
        reviewOperationJPA.deleteById(reviewId);
        reviewOperationJPA.recalculateFilmRating(movieId);
    }

    @Transactional
   public void editReview(String movieId,Long reviewId,String text) throws InputDataException
   {
       if(reviewOperationJPA.findById(reviewId).get().getMovieId().equals(movieId))
       {
           reviewOperationJPA.editingReview(reviewId,text);
       }
       else
       {
           throw new InputDataException("Отзыв не относится к данному фильму");
       }
   }

}
