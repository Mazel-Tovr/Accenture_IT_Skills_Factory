package com.accenture.oopapp.businesslayer.entrence;

import com.accenture.oopapp.businesslayer.datacontrol.InDataControl;
import com.accenture.oopapp.businesslayer.datacontrol.InputDataException;
import com.accenture.oopapp.model.users.User;
import com.accenture.oopapp.mysqldatabase.interfaces.UserOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingIn
{
    @Autowired
    private InDataControl inDataControl;
    @Autowired
    private UserOperation userOperation;

    public User login(String nickName,String passWord) throws InputDataException
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
}
