package com.accenture.oopapp.users;

import java.util.Objects;

public class Administrator extends Person
{
    private String nickName;
    private String passWord;
    private String shortDescription;

    public Administrator(String name, Integer age, Gender gender, String nickName, String pussWord)
    {
        super(name, age, gender);
        this.nickName = nickName;
        this.passWord = pussWord;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return nickName.equals(that.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName);
    }
}
