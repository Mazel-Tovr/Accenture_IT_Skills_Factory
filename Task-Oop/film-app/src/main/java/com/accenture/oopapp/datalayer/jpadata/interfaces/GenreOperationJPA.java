package com.accenture.oopapp.datalayer.jpadata.interfaces;

import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.GenreModel;

import java.util.Set;

public interface GenreOperationJPA
{
    GenreModel getGenreById(Long id);
    GenreModel getGenreByName(Genre genre);
    Set<GenreModel> getGenreByName(Genre ...genres);

}
