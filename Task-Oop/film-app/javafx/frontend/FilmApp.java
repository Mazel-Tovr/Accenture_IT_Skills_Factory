package com.accenture.oopapp.frontend;

import com.accenture.oopapp.model.films.Review;
import com.accenture.oopapp.frontend.mainform.Session;
import com.accenture.oopapp.frontend.mainform.SessionService;
import com.accenture.oopapp.datalayer.mysqldatabase.ReviewTable;
import com.accenture.oopapp.datalayer.mysqldatabase.UserTable;
import com.accenture.oopapp.datalayer.mysqldatabase.interfaces.MovieOperation;
import com.accenture.oopapp.datalayer.mysqldatabase.FilmTable;
import com.accenture.oopapp.datalayer.mysqldatabase.interfaces.ReviewOperation;
import com.accenture.oopapp.datalayer.mysqldatabase.interfaces.UserOperation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FilmApp extends Application {

    public static SessionService session = Session.getInstance();
    public static UserOperation userOperation = UserTable.getInstance();
    public static ReviewOperation reviewOperation = ReviewTable.getInstance();
    public static MovieOperation movieOperation = FilmTable.getInstance();
    public static Stage primaryStage;
    public static void main(String[] args) { launch(args); }

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
