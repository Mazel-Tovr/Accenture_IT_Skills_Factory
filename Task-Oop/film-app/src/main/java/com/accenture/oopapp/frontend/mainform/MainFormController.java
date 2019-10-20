package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private User user;
    private ObservableList<Movie> listOfItems;
    public void initialize()
    {
        listOfItems = FXCollections.observableArrayList();
        user = FilmApp.dataBase.getLastEnteredUser();
        nameLable.setText(user.getName());
        ageLable.setText(user.getAge().toString());
        genderLable.setText(user.getGender().name());
        nickNameLable.setText(user.getNickName());
        statusLable.setText("Simple user");

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
    void search(ActionEvent event) {

    }

    public void openFilmPage(ActionEvent actionEvent) {
    }
}
