package com.accenture.oopapp.datastore;


import com.accenture.oopapp.users.User;

import java.util.Collection;
import java.util.List;

public interface UsersDataBaseService
{
    void addUserToDataBase(User user);
    Collection<User> getUserMap();
    boolean isUserExist(String nickName);
    boolean isConnect(String nickName,String password);
}
