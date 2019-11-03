package com.accenture.oopapp.businesslayer.main;

import com.accenture.oopapp.businesslayer.datacontrol.InDataControl;
import com.accenture.oopapp.businesslayer.datacontrol.InputDataException;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.mysqldatabase.interfaces.MovieOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{
    @Autowired
    private InDataControl inDataControl;
    @Autowired
    private MovieOperation movieOperation;

    public List<Movie> getAll()
    {
       return movieOperation.getMovieList();
    }

    public List<Movie> searchBy(String filter,String txt) throws InputDataException
    {
        if(!inDataControl.filterValidation(filter))
        {
            throw new InputDataException("Неподходящий фильтер поиска");
        }
        return movieOperation.search(filter,txt);
    }
    public void addMovieToBase()//Some params
    {
        //In progress
    }
    public void upLoad()
    {
        //In progress (no ofc)
    }
}
