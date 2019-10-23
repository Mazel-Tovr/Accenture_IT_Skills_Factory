package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.datacheck.GeneralVerificationMethods;
import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.frontend.entrance.SingInController;
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

public class MainFormController
{

    @FXML
    private TextField findField;

    @FXML
    private ComboBox<String> filterBox;

    @FXML
    private Label nameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label nickNameLabel;

    @FXML
    private Label statusLabel;

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

    @FXML
    private ObservableList<Movie> listOfItems;

    GeneralVerificationMethods generalVerificationMethods;

    public void initialize()
    {
        generalVerificationMethods = new GeneralVerificationMethods();
        User user = FilmApp.session.getCurrentUser();
        filterBox.setItems(FXCollections.observableArrayList("Показать все","По индентификатору","Названию","Дате"));
        if(!user.isAdmin())
        {
            nameLabel.setText(user.getName());
            ageLabel.setText(user.getAge().toString());
            genderLabel.setText(user.getGender().name());
            nickNameLabel.setText(user.getNickName());
            statusLabel.setText("Simple user");
        }
        else
        {
            nameLabel.setText(user.getName());
            ageLabel.setText(user.getAge().toString());
            genderLabel.setText(user.getGender().name());
            nickNameLabel.setText(user.getNickName());
            statusLabel.setText("You are Admin");
        }

        listOfItems = FXCollections.observableArrayList();
        listOfItems.addAll(FilmApp.moviesDataBase.getMovieSet());
        movieId.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieId"));
        nameId.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieName"));
        typeId.setCellValueFactory(new PropertyValueFactory<Movie,MovieType>("movieType"));
        genreId.setCellValueFactory(new PropertyValueFactory<Movie,Genre>("genres"));
        dateId.setCellValueFactory(new PropertyValueFactory<Movie,String>("releaseDate"));
        ratingId.setCellValueFactory(new PropertyValueFactory<Movie,Double>("rating"));

        tableView.setItems(listOfItems);

    }


    @FXML
    private void search(ActionEvent event)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        switch (filterBox.getValue())
        {
            case "Показать все":
                tableView.setItems(listOfItems);
                break;
            case "По индентификатору":
                searchById();
                break;
            case "Названию":
                searchByName();
                break;
            case "Дате":
                searchByDate();
                break;
            default:
                alert.setContentText("Выбирете значение");
                alert.showAndWait();
                break;
        }
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

    private void searchById()
    {
        if(generalVerificationMethods.notEmptyField(findField.getText()))
        {
            var a = FilmApp.moviesDataBase.idSearch(findField.getText());
            ObservableList<Movie> tempo = FXCollections.observableArrayList(a);
//            String str = findField.getText();
//            for (var item:listOfItems)
//            {
//                if(str.equals(item.getMovieId()))tempo.add(item);
//            }
            tableView.setItems(tempo);
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Введите данные в поле поиска");
            alert.showAndWait();
        }
    }

    private void searchByName()
    {
        ObservableList<Movie> tempo = FXCollections.observableArrayList();
        if(!findField.getText().equals(""))
        {
            String str = findField.getText();
            for (var item:listOfItems)
            {
                if(str.equals(item.getMovieName()))tempo.add(item);
            }
            tableView.setItems(tempo);
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Введите данные в поле поиска");
            alert.showAndWait();
        }
    }

    private void searchByDate()
    {
        ObservableList<Movie> tempo = FXCollections.observableArrayList();
        if(!findField.getText().equals(""))
        {
            String str = findField.getText();
            for (var item:listOfItems)
            {
                if(str.equals(item.getReleaseDate()))tempo.add(item);
            }
            tableView.setItems(tempo);
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Введите данные в поле поиска");
            alert.showAndWait();
        }
    }
}
