package com.accenture.oopapp.users;

public abstract class Person {

    private String name;
    private Integer age;
    private Gender gender;

    public String getName() { return name; }

    public Gender getGender() { return gender; }

    public Integer getAge() { return age; }

    protected void setName(String name) { this.name = name; }

    protected void setAge(Integer age) { this.age = age; }

    Person(String name, Integer age,Gender gender)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}