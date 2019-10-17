package com.accenture;

import com.accenture.control.*;
import com.accenture.gamelogic.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Добро пожаловать в игру Быки и коровы:\n"+"Что бы начать игру введите 1 , что бы узнать правила игры 2");
      int usersСhoice = -1;
      do {
          String usersInput = reader.readLine();
          if(TryParse.tryParseInt(usersInput))
          {
                usersСhoice = Integer.parseInt(usersInput);
                switch (usersСhoice)
                {
                    case 1:
                        startTheGame();
                        break;
                    case 2:
                        rules();
                        break;
                    default:
                        System.out.println("Введите 1 либо 2");
                }
          }
          else
          {
              System.out.println("Ошибка ввода даных: введите число");
          }

      }while (usersСhoice != 1);

    }

   static void  startTheGame() throws IOException
    {
        CorrectnessOfTheEnteredData correctness = new CorrectnessOfTheEnteredData();
        Scanner scanner = new Scanner(System.in);
        String userDecision;
        do
        {
            Game game = new Game(correctness.getNumberLength());
            game.startGame();

            do
            {
                System.out.println("Хотите сыграть еше ?\n"+ "Y/N");
                userDecision = scanner.nextLine().toLowerCase();
            }while (!(userDecision.equals("n")||userDecision.equals("y")));

        }while (!userDecision.equals("n"));
        System.out.println("Спасибо за игру");

    }
   static void rules()
    {
        System.out.println("Правила игры\n" +
                "Компьютер задумывает 3,4 или 5 различных цифор из 0,1,2,...9. " +
                "Игрок делает ходы, чтобы узнать эти цифры и их порядок.\n" +
                "Каждый ход состоит из четырёх цифр, 0 может стоять на первом месте.\n" +
                "В ответ компьютер показывает число отгаданных цифр, стоящих на своих местах (число быков) и число отгаданных цифр,стоящих не на своих местах (число коров).\n" +
                "Если игрок отгадывает число программа сообщает об этом, игрок может сдаться если напишет \"сдаюсь\"\n"+
                "Пример\n" +
                "Компьютер задумал 0834. Игрок сделал ход 8134. Компьютер ответил: 2 быка (цифры 3 и 4) и 1 корова (цифра 8).");
        System.out.println("Удачной игры!\n"+"Для начала игры введите 1");
    }
}
