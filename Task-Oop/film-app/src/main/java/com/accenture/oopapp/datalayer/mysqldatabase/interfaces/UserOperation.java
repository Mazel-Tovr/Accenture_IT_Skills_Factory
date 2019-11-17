package com.accenture.oopapp.datalayer.mysqldatabase.interfaces;

import com.accenture.oopapp.model.users.User;

public interface UserOperation
{
     boolean isUserExist(String nickName);
     User getUser(String nickName, String password);
     void addUserToDataBase(User user);
}
