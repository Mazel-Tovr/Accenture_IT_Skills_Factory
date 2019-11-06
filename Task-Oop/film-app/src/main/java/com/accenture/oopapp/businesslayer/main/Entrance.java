package com.accenture.oopapp.businesslayer.main;

import com.accenture.oopapp.businesslayer.datacontrol.InDataControl;
import com.accenture.oopapp.businesslayer.datacontrol.InputDataException;
import com.accenture.oopapp.model.users.Gender;
import com.accenture.oopapp.model.users.User;
import com.accenture.oopapp.mysqldatabase.interfaces.UserOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Entrance
{
    @Autowired
    private InDataControl inDataControl;
    @Autowired
    private UserOperation userOperation;

    public User login(String nickName, String passWord) throws InputDataException
    {
        if(inDataControl.notEmptyField(nickName)&&inDataControl.notEmptyField(nickName))
        {
            User user = userOperation.getUser(nickName,passWord);
            if(inDataControl.notNullValue(user))
            {
                return user;
            }
            else
            {
                throw new InputDataException("Проверьте правильность ввода данных");
            }
        }
        else
        {
            throw new InputDataException("Вы должны заполнить все поля");
        }
    }

    public void toRegister(String name, String nickName, String passWord, int age, String gender1) throws InputDataException
    {
        Gender gender = Gender.valueOf(gender1.toUpperCase());
        if(dataCheck(name,nickName,passWord,age,gender))
        {
            userOperation.addUserToDataBase(new User(name,age,gender,nickName,passWord));
        }
    }

    private boolean dataCheck(String name, String nickName, String passWord, int age, Gender gender)throws InputDataException
    {
        if(!inDataControl.notEmptyField(name))
        {
            throw new InputDataException("Заполните поле с именем ");
        }
        if(!inDataControl.ageCheck(age))
        {
            throw new InputDataException("Укажите возраст в диапазон от 3 до 100 лет ");
        }
        if(!inDataControl.notNullValue(gender))
        {
            throw new InputDataException("Выберите гендер");
        }
        if(inDataControl.notEmptyField(nickName))
        {
            if(userOperation.isUserExist(nickName))
            {
                throw new InputDataException("Пользователь с таким именем уже существует");
            }
        }
        else
        {
            throw new InputDataException("Пользователь с таким именем уже существует");
        }
        if(!inDataControl.notEmptyField(passWord))
        {
            throw new InputDataException("Заполните поле пароля");
        }

        return true;
    }
}
