package com.accenture.oopapp.businesslayer.jpa;

import com.accenture.oopapp.model.users.User;

import java.util.List;

public interface UserJPA
{
    List<User> getAll();
}
