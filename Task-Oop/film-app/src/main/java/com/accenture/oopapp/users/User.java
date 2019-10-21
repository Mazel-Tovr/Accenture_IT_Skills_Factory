package com.accenture.oopapp.users;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.review.Review;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class User extends Person implements Comparable<User>
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

    public void addReview(Movie movie,String text,double rating)
    {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        movie.getFilmsReview().add(new Review(text,formatForDateNow.format(date),this,rating));
        movie.recalculateFilmRating();
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
