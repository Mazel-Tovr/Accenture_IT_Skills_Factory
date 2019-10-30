package com.accenture.oopapp.mysqldatabase.interfaces;

import com.accenture.oopapp.users.User;

public interface UserOperation
{
     boolean isUserExist(String nickName);
    boolean isConnect(String nickName,String password);
     void addUserToDataBase(User user);
}
