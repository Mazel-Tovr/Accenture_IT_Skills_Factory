package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.frontend.entrance.SingInController;
import com.accenture.oopapp.users.Administrator;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.Person;
import com.accenture.oopapp.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFormController
{
    @FXML
    private ListView<String> filmsView;

    @FXML
    private TextField findField;

    @FXML
    private ComboBox<?> filterBox;

    @FXML
    private Label nameLable;

    @FXML
    private Label ageLable;

    @FXML
    private Label genderLable;

    @FXML
    private Label nickNameLable;

    @FXML
    private Label statusLable;

    @FXML
    private TableColumn<Movie,String> movieId;

    @FXML
    private TableColumn<Movie,String> nameId ;

    @FXML
    private TableColumn<Movie, MovieType> typeId;

    @FXML
    private TableColumn<Movie, Genre> genreId;

    @FXML
    private TableColumn<Movie,String> dateId;

    @FXML
    private TableColumn<Movie,Double> ratingId;

    @FXML
    private TableView<Movie> tableView;


    public void initialize()
    {
        Person user = FilmApp.session.getLastEnteredUser();
        if(user instanceof User)
        {
            User simpleUser = (User) user;
            nameLable.setText(simpleUser.getName());
            ageLable.setText(simpleUser.getAge().toString());
            genderLable.setText(simpleUser.getGender().name());
            nickNameLable.setText(simpleUser.getNickName());
            statusLable.setText("Simple user");
        }
        else
        {
            Administrator simpleUser = (Administrator) user;
            nameLable.setText(simpleUser.getName());
            ageLable.setText(simpleUser.getAge().toString());
            genderLable.setText(simpleUser.getGender().name());
            nickNameLable.setText(simpleUser.getNickName());
            statusLable.setText("You are Admin");
        }

        ObservableList<Movie> listOfItems = FXCollections.observableArrayList();
        listOfItems.addAll(FilmApp.dataBase.getMovieSet());
        movieId.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieId"));
        nameId.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieName"));
        typeId.setCellValueFactory(new PropertyValueFactory<Movie,MovieType>("movieType"));
        genreId.setCellValueFactory(new PropertyValueFactory<Movie,Genre>("genres"));
        dateId.setCellValueFactory(new PropertyValueFactory<Movie,String>("releaseDate"));
        ratingId.setCellValueFactory(new PropertyValueFactory<Movie,Double>("rating"));

        tableView.setItems(listOfItems);

    }

    @FXML
    void search(ActionEvent event)
    {

    }

    public void openFilmPage(ActionEvent actionEvent) throws IOException
    {
        Movie movie = tableView.getSelectionModel().getSelectedItem();
        if(movie != null )
        {
            FilmApp.session.setMovie(movie);
            Parent root = FXMLLoader.load(FilmPageController.class.getResource("FilmPageForm.fxml"));
            FilmApp.primaryStage.close();
            FilmApp.primaryStage.setScene(new Scene(root));
            FilmApp.primaryStage.setTitle("Страница фильма");
            FilmApp.primaryStage.show();
        }
        else
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Выбирете фильм");
            alert.showAndWait();
        }
    }

    public void logOut(ActionEvent actionEvent) throws IOException
    {
        FilmApp.session.logOut();
        Parent root = FXMLLoader.load(SingInController.class.getResource("SingIn.fxml"));
        FilmApp.primaryStage.close();
        FilmApp.primaryStage.setScene(new Scene(root));
        FilmApp.primaryStage.setTitle("Вход в приложение");
        FilmApp.primaryStage.show();
    }
}
