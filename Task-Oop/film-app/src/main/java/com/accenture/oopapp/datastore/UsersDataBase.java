package com.accenture.oopapp.datastore;

import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.User;

import java.util.*;

public class UsersDataBase
{
    private Map<String,User> userMap = new HashMap<>();
    {
        userMap.put("User",new User("User",54, Gender.FEMALE,"User","User"));
        userMap.put("Admin",new User("Admin ce god",54,Gender.MALE,"Admin","Admin",true));
    }


    public void addUserToUserMap(User user) {  userMap.put(user.getNickName(),user); }

    public Collection<User> getUserMap() { return userMap.values(); }

    public boolean isUserExist(String nickName)
     {
         return userMap.containsKey(nickName);
     }
     public boolean isConnect(String nickName,String password)
     {
         if(!userMap.containsKey(nickName))
         {
             return false;
         }
         User user =  userMap.get(nickName);
         if(user.getNickName().equals(nickName) && user.getPassWord().equals(password))
         {
                 FilmApp.session.setCurrentUser(user);
                 return true;
         }
         return false;
     }

}