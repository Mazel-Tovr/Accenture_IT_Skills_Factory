package com.accenture.oopapp.users;

import java.util.Objects;

public class User extends Person
{
    private String nickName;
    private String passWord;
    private String shortDescription;
    private int rating = 0;
    public User(String name, Integer age, Gender gender, String nickName, String passWord)
    {
        super(name, age, gender);
        this.nickName = nickName;
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return nickName.equals(user.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName);
    }
}
