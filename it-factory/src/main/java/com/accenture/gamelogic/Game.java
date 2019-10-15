package com.accenture.gamelogic;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class Game
{
    private int wordLength;
    private String secretNumber;
    private int tryCount=0;

    public String getSecretNumber() { return secretNumber; }

    public int getTryCount() { return tryCount; }

    public int getWordLength() { return wordLength; }

    public Game(int wordLength)
    {
        this.wordLength = wordLength;
    }

    public void generateNumber()
    {
        Set<Integer> numbersSet = new HashSet<>();
        Random random = new Random();
        while (numbersSet.size() != wordLength)//можно было fori
        {
           numbersSet.add(random.nextInt(9));
        }
       secretNumber = numbersSet.toString().replaceAll("\\D","");//Удаляем нецифровой символ ы
    }
    public void tryToGuess()
    {

        tryCount++;
    }
}
