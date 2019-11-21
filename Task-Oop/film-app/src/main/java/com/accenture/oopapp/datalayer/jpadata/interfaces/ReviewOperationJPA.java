package com.accenture.oopapp.datalayer.jpadata.interfaces;

import com.accenture.oopapp.model.films.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ReviewOperationJPA extends CrudRepository<Review,Long>
{

    @Modifying
    @Query("UPDATE Review  r SET r.text =?2 WHERE r.reviewId =?1")
    void editingReview(Long reviewId,String text);

    @Modifying
    @Query("UPDATE Movie m SET m.rating = (SELECT AVG(userRating) FROM Review r WHERE r.movieId =?1) WHERE m.movieId =?1")
    void recalculateFilmRating(String  movieId);

}

