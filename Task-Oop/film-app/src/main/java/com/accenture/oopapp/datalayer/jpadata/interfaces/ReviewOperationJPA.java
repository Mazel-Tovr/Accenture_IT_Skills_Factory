package com.accenture.oopapp.datalayer.jpadata.interfaces;

import com.accenture.oopapp.model.films.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewOperationJPA extends CrudRepository<Review,Long>
{

}
