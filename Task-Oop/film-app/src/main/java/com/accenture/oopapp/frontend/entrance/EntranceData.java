package com.accenture.oopapp.frontend.entrance;

import com.accenture.oopapp.users.Person;

public class EntranceData
{
 private String login;
 private String password;
 private Person person;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

    public void setPerson(String login,String password)
    {
     // this.person = FilmApp.dataBase
    }
}
