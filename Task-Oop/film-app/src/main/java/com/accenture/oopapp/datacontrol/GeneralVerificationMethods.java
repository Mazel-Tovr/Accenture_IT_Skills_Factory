package com.accenture.oopapp.datacontrol;

public class GeneralVerificationMethods
{

    public boolean notEmptyField(String value)
    {
        return !value.equals("");
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
            return Integer.parseInt(ageId) >= 3 && Integer.parseInt(ageId) <= 100;
        }
        return false;
    }

    public boolean ratingCheck(String ratingId)
    {
       if(tryParseDouble(ratingId))
       {
           double value = Double.parseDouble(ratingId);
           if(value >= 0 && value < 100)
           {
               return true;
           }
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
