package com.accenture.gamelogic;

import com.accenture.control.CorrectnessOfTheEnteredData;
import com.accenture.control.TryParse;

import java.util.*;

public class Game
{
    private int wordLength;
    private String secretNumber;
    private int tryCount=0;

    public Game(int wordLength)
    {
        this.wordLength = wordLength;
        generateNumber();
    }

    public void generateNumber()
    {
        Set<Integer> numbersSet = new HashSet<>();
        Random random = new Random();
        while (numbersSet.size() != wordLength)
        {
           numbersSet.add(random.nextInt(9));
        }
       secretNumber = numbersSet.toString().replaceAll("\\D","");//Удаляем нецифровые символы
    }
    public void tryToGuess()
    {
        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Введите угадываемое число или сдайтесь");
            String userInput = scanner.nextLine().toLowerCase();
            CorrectnessOfTheEnteredData correctness = new CorrectnessOfTheEnteredData();
            if(TryParse.tryParseInt(userInput) && correctness.isCorrectNumber(userInput,wordLength)||userInput.equals("сдаюсь"))
            {
                tryCount++;
               if(isGameEnded(userInput)) break;
            }
            else
            {
                    System.out.println("Ошибка: введите число");
            }
        }
    }

    private void userGiveUp()
    {
        System.out.println("Слабак!");
        System.out.println(tryCount == 0 ? "Сдался даже не пытаясь": tryCount == 1 ? "Сдался с первой же попытки":"Тебе потребовалось "+tryCount +"попытки что бы сдаться");
        System.out.println("Число, загаданное компьютером: "+secretNumber);
    }

    private void userWon()
    {
        System.out.println("Победа!");
        System.out.println(tryCount == 1 ? "Вы угадали число с первой же попытки, да вам стоит сходить в казино" : "Вам потребовалось "+tryCount+ " попытки чтобы угадать число");
    }

    private boolean isGameEnded(String userInput)
    {
        if(userInput.equals("сдаюсь"))
        {
            userGiveUp();
            return true;
        }
        if(secretNumber.equals(userInput))
        {
            userWon();
            return true;
        }
        else
        {
            System.out.println("Число быков: "+getBullsCount(userInput) +", число коров: "+getCowsCount(userInput));
            return false;
        }
    }

    private int getBullsCount(String userInput)
    {
        int counter = 0;
        for (int i = 0; i < wordLength ; i++)
        {
            if(userInput.charAt(i) == secretNumber.charAt(i))
            {
                counter++;
            }
        }
        return counter;
    }
    private int getCowsCount(String userInput)
    {
        int counter = 0;
        for (int i = 0; i <wordLength ; i++)
        {
            for (int j = 0; j < wordLength ; j++)
            {
                if(i!=j && secretNumber.charAt(i) == userInput.charAt(j))
                {
                    counter++;
                }
            }
        }
        return counter;
    }

}
