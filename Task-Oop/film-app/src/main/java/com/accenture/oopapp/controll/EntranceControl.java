package com.accenture.oopapp.controll;

import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;
import com.accenture.oopapp.businesslayer.main.interfaces.EntranceBusinessLayer;
import com.accenture.oopapp.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController("/logging")
public class EntranceControl
{
    @Autowired
    private EntranceBusinessLayer entrance;

    @RequestMapping(value = "/logging",method = RequestMethod.GET)
    public String loggingPage()
    {
        return"just main page";
     }
    /*
     Example 4 test (use Postman) !!bd should bee on!!
     http://localhost:8080/logging?nickname=User&password=User
    */
    @RequestMapping(value = "/logging",method = RequestMethod.POST)
    public User singIn(@RequestParam(value = "nickname") String nickName, @RequestParam(value = "password") String passWord) throws InputDataException
    {
        return entrance.login(nickName, passWord);
    }
    @RequestMapping(value = "/logging/registration",method = RequestMethod.GET)
    public String registrationPage()
    {
        return "this is a registration page";
    }
    /*
    Example 4 test (use Postman) !!bd should bee on!!
    http://localhost:8080/logging/registration?name=Alex&nickname=Alex228&password=228&age=44&gender=male
     */

    //
    @RequestMapping(value = "/logging/registration",method = RequestMethod.POST)
    public String singUp(@RequestParam(value = "name")String name,@RequestParam(value = "nickname") String nickName,@RequestParam(value = "password") String passWord,
                         @RequestParam(value = "age")int age,@RequestParam(value = "gender") String gender) {
        //ну или просто бросать эксепшен выше
        try
        {
            entrance.toRegister(name, nickName, passWord, age, gender);
            return "Регистрация успешно завершена";
        }
        catch (InputDataException e)
        {
            return e.getMessage();
        }
    }

}
