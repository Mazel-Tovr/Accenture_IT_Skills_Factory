package com.accenture.oopapp.businesslayer.main.interfaces;

import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;
import com.accenture.oopapp.model.users.User;

public interface EntranceBusinessLayer
{
    User login(String nickName, String passWord) throws InputDataException;
    void toRegister(String name, String nickName, String passWord, int age, String gender1) throws InputDataException;
}
