package com.accenture.control;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class CorrectnessOfTheEnteredData
{

    public int getNumberLength() throws IOException
    {
        System.out.println("Для начала игры введите длину числа 3 или 4 или 5");
        int numberLength;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do
        {
            String usersInput = reader.readLine();
            if(TryParse.tryParseInt(usersInput))
            {
                numberLength = Integer.parseInt(usersInput);
                if(numberLength>=3 && 5>=numberLength)
                {
                    return numberLength;
                }
                else
                {
                    System.out.println("Ошибка вы должны ввести длину для числа равную 3 или 4 или 5");
                }
            }
            else
            {
                System.out.println("Ошибка ввода даных: введите число");
            }
        }while (true);
    }

    public boolean isCorrectNumber(String usersInPut,final int numberLength)
    {
        Set<Character> checkDigitsUnique = new HashSet<>();
        if(usersInPut.length() != numberLength)
        {
            System.out.println( "Число должно состоять из "+numberLength +" цифр" );
            return false;
        }
        for (int i = 0; i < numberLength ; i++)
        {
            checkDigitsUnique.add(usersInPut.charAt(i));
        }
        if (checkDigitsUnique.size() != numberLength) System.out.println("Число должно состоять из разных цифр");
        return checkDigitsUnique.size() == numberLength ? true : false;

    }


}
