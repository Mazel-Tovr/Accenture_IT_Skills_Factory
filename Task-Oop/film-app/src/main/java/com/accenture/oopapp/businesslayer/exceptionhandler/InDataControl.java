package com.accenture.oopapp.businesslayer.exceptionhandler;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InDataControl
{

    public boolean notEmptyField(String value)
    {
        return value != null && !value.isEmpty();
    }


    public boolean notNullValue(Object value)
    {
        return value != null;
    }
    /*
    public boolean notNullValue(Integer ...value)
    {
        return value != null;
    }
    public boolean notNullValue(String ...value)
    {
        return value != null;
    }
    public boolean notNullValue(Double ...value)
    {
        return value != null;
    }
     */
    public boolean ageCheck(String ageId)
    {
        if(tryParseInt(ageId))
        {
            int age = Integer.parseInt(ageId);
            return age >= 3 && age <= 100;
        }
        return false;
    }
    public boolean ageCheck(int age)
    {
            return age >= 3 && age <= 100;
    }


    public boolean filterValidation(String filter)
    {
        List<String> filtersEnum = Arrays.asList("movieId", "movieName", "releaseDate");//TODO continue enum
        return filtersEnum.contains(filter);

    }

    public boolean ratingCheck(Double rating)
    {
        return rating >= 0 && rating < 100;
    }
    public boolean ratingCheck(String rating)
    {
       if(tryParseDouble(rating))
       {
           double value = Double.parseDouble(rating);
           return value >= 0 && value < 100;
       }
       return false;
    }

    private boolean tryParseDouble(String value)
    {
        try
        {
            Double.parseDouble(value);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
    private boolean tryParseInt(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
