package com.accenture.oopapp.fileinput;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.frontend.FilmApp;

import java.io.*;
import java.util.*;

public class CSVParser
{

    //TODO  Сделать проверку на входные данные для фильма
    public void addToDataBase(String path)
    {
        List<String[]> movieDataListLines = ParsingInLines(path);
        for (String[] filmDataIn : movieDataListLines)
        {
            String[] genresString = filmDataIn[3].split(",");
            Set<Genre> genres = new HashSet<>();
            for (String s : genresString)
            {
                genres.add(Genre.valueOf(s.toUpperCase()));
            }
            FilmApp.movieOperation.addMoveToDataBase(new Movie(filmDataIn[0], filmDataIn[1], MovieType.valueOf(filmDataIn[2].toUpperCase()), EnumSet.copyOf(genres), filmDataIn[4], filmDataIn[5],0));
        }
    }

    public void addToDataBase(File file)
    {
        List<String[]> movieDataListLines = ParsingInLines(file);
        for (String[] filmDataIn : movieDataListLines)
        {
            String[] genresString = filmDataIn[3].split(",");
            Set<Genre> genres = new HashSet<>();
            for (String s : genresString)
            {
                genres.add(Genre.valueOf(s.toUpperCase()));
            }
            FilmApp.movieOperation.addMoveToDataBase(new Movie(filmDataIn[0], filmDataIn[1], MovieType.valueOf(filmDataIn[2].toUpperCase()), EnumSet.copyOf(genres), filmDataIn[4], filmDataIn[5],0));
        }
    }

    private  List<String[]> ParsingInLines(File file)
    {
        List<String[]> movieDataListLines = new ArrayList<>();
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.ready())
            {
                movieDataListLines.add(bufferedReader.readLine().split(";"));
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return movieDataListLines;
    }
    private  List<String[]> ParsingInLines(String path)
    {
        List<String[]> movieDataListLines = new ArrayList<>();
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
            while (bufferedReader.ready())
            {
                movieDataListLines.add(bufferedReader.readLine().split(";"));
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return movieDataListLines;
    }



}
