package com.accenture.oopapp.frontend;

import com.accenture.oopapp.datastore.MoviesDataBase;
import com.accenture.oopapp.datastore.UsersDataBase;
import com.accenture.oopapp.frontend.mainform.Session;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FilmApp extends Application {

    public static Session session = new Session();
    public static UsersDataBase usersDataBase = new UsersDataBase();
    public static MoviesDataBase moviesDataBase = new MoviesDataBase();
    public static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("entrance/SingIn.fxml"));
        primaryStage.setTitle("Вход в приложение");
        primaryStage.setScene(new Scene(root));
        runStage(primaryStage);
    }

    public static void runStage(Stage stage) throws IOException
    {
        primaryStage = stage;
        primaryStage.show();
    }
}
