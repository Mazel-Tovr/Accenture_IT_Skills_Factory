package com.accenture.oopapp.users;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.review.Review;
import com.accenture.oopapp.films.review.WorkingWithReview;

import java.util.List;
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
