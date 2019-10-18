package com.accenture.oopapp.frontend;

import com.accenture.oopapp.datastore.DataBase;
import com.accenture.oopapp.frontend.entrance.EntranceData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FilmApp extends Application {

    public static EntranceData entranceData = new EntranceData();
    public static DataBase dataBase = new DataBase();
    public static Stage primaryStage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("\\entrance\\singin\\SingIn.fxml"));
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
