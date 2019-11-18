package com.accenture.oopapp.datalayer.jpadata;

import com.accenture.oopapp.model.films.Genre;
import org.springframework.stereotype.Service;

@Service
class UseFullTools
{
    String createGenreWhereInQuery(Genre... genres)
    {
        StringBuilder s = new StringBuilder();
        s.append("(");
        for (int i = 0; i < genres.length ; i++)
        {
            s.append("?").append(i + 1);
            s.append(i + 1 < genres.length ? "," : ")");
        }
        return s.toString();
    }
}
