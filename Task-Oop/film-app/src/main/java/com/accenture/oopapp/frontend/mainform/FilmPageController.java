package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.films.review.Review;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.frontend.entrance.SingInController;
import com.accenture.oopapp.users.Person;
import com.accenture.oopapp.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class FilmPageController
{

    @FXML
    private TextField ratingField;

    @FXML
    private TextArea reviewField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TableView<Review> tableReview;

    @FXML
    private TableColumn<Review, String> userId;

    @FXML
    private TableColumn<Review, String> dataId;

    @FXML
    private TableColumn<Review, String> reviewId;

    @FXML
    private TableColumn<Review, Double> ratingId;

    private ObservableList<Review> reviews = FXCollections.observableArrayList();;

    public void initialize()
    {
        Movie movie = FilmApp.dataBase.getMovie();
        descriptionField.setText(movie.getDescription());
        reviews.addAll(movie.getFilmsReview());
        Person person = FilmApp.dataBase.getLastEnteredUser();

        if(person instanceof User)
        {
            //TODO Сделать инициализацию под юзера
            descriptionField.setDisable(true);
        }
        else
        {
            //TODO Сделать инициализацию под админа
        }

        userId.setCellValueFactory(new PropertyValueFactory<Review, String>("personWhoWroteIt"));
        dataId.setCellValueFactory(new PropertyValueFactory<Review, String>("postDate"));
        reviewId.setCellValueFactory(new PropertyValueFactory<Review, String>("textOfReview"));
        ratingId.setCellValueFactory(new PropertyValueFactory<Review,Double>("youLikedFilm"));
        tableReview.setItems(reviews);

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(MainFormController.class.getResource("MainForm.fxml"));
        FilmApp.primaryStage.close();
        FilmApp.primaryStage.setScene(new Scene(root));
        FilmApp.primaryStage.setTitle("Приложение Фильм");
        FilmApp.primaryStage.show();
    }

    @FXML
    void postReview(ActionEvent event)
    {

    }

}

