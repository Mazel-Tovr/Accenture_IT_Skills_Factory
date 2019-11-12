package com.accenture.oopapp.controll;

import com.accenture.oopapp.businesslayer.jpa.UserJPA;
import com.accenture.oopapp.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController( "/test")
public class JPATest
{
    @Autowired
    private UserJPA userJPA;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public List<User> getAll()
    {
        return userJPA.getAll();
    }
}
