package com.accenture.oopapp.users;

import java.util.Objects;

public class User implements Comparable<User>
{
    private String name;
    private Integer age;
    private Gender gender;
    private String nickName;
    private String passWord;
    private boolean isAdmin;
    private int rating = 0;
    public User(String name, Integer age, Gender gender, String nickName, String passWord)
    {
        this.name=name;
        this.age=age;
        this.gender= gender;
        this.nickName = nickName;
        this.passWord = passWord;
    }
    public User(String name, Integer age, Gender gender, String nickName, String passWord, boolean isAdmin)
    {
        this.name=name;
        this.age=age;
        this.gender= gender;
        this.nickName = nickName;
        this.passWord = passWord;
        this.isAdmin = isAdmin;
    }

    public String getName() { return name; }

    public Integer getAge() { return age; }

    public Gender getGender() { return gender; }

    public String getNickName() {
        return nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public boolean isAdmin() { return isAdmin; }


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

    @Override
    public int compareTo(User o)
    {
        return this.getNickName().compareTo(o.getNickName());
    }
}
