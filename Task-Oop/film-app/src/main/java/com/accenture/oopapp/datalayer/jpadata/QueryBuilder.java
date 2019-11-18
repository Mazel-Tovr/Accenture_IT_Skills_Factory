package com.accenture.oopapp.datalayer.jpadata;

import com.accenture.oopapp.model.films.Genre;
import org.springframework.stereotype.Service;

@Service
class QueryBuilder
{
    String createInQuery(int countOfElements)
    {
        StringBuilder s = new StringBuilder();
        s.append("(");
        for (int i = 0; i < countOfElements ; i++)
        {
            s.append("?").append(i + 1);
            s.append(i + 1 < countOfElements ? "," : ")");
        }
        return s.toString();
    }
}
