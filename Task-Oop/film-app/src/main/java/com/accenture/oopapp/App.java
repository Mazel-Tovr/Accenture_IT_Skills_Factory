package com.accenture.oopapp;

import com.accenture.oopapp.mysqldatabase.DataBase;
import com.accenture.oopapp.mysqldatabase.MySqlDataBase;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //    String DB_DRIVER = "com.mysql.jdbc.Driver" ;
//    String DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/filmappdb?autoReconnect=true&useSSL=false";
//    String DB_USER = "root" ;
//    String DB_PASSWORD = "";
//        User user = new User("fg",10, Gender.FEMALE,"Sanya","Sanya");
//        User user2 = new User("fgfgfg",10, Gender.FEMALE,"Sanya2","Sanya2");
//        Administrator administrator = new Administrator("fg",10, Gender.FEMALE,"Sanya","Sanya");
//        Administrator administrator1 = new Administrator("fgfgfg",10, Gender.FEMALE,"Sanya2","Sanya2");
//
//        List<Person> personList = new ArrayList<>();
//        personList.add(administrator1);
//        personList.add(user);
//        DataBase dataBase = MySqlDataBase.getInstance();
//        dataBase.getDBConnection("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/filmappdb?autoReconnect=true&useSSL=false","root","");
//        dataBase.getMovieList().stream().forEach(x -> System.out.println(x.getMovieId()+"\t"+x.getMovieName()+"\t"+x.getMovieType()+"\t"+x.getGenres().toString()+"\t"+x.getDescription()));
       var dataBase = MySqlDataBase.getInstance();
        dataBase.addUserToDataBase(new User("Gaga",54, Gender.FEMALE,"Gaga","gaga"));
    }
}
