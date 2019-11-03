package com.accenture.oopapp.businesslayer.entrence;

import com.accenture.oopapp.businesslayer.datacontrol.InDataControl;
import com.accenture.oopapp.businesslayer.datacontrol.InputDataException;
import com.accenture.oopapp.model.users.Gender;
import com.accenture.oopapp.model.users.User;
import com.accenture.oopapp.mysqldatabase.interfaces.UserOperation;
import org.springframework.beans.factory.annotation.Autowired;

public class SingUp
{
    @Autowired
    private InDataControl inDataControl;
    @Autowired
    private UserOperation userOperation;

    public void toRegister(String name, String nickName, String passWord, int age, Gender gender) throws InputDataException
    {
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
