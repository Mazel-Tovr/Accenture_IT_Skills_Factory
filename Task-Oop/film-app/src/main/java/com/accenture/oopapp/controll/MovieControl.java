package com.accenture.oopapp.controll;

import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.mysqldatabase.FilmTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
public class MovieControl
{

    @RequestMapping("/movie")
    public List<Movie> getAll()
    {
     return null;
    }
}