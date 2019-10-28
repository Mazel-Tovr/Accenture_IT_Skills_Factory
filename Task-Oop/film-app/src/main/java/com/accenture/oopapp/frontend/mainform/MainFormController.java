package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.datacontrol.GeneralVerificationMethods;
import com.accenture.oopapp.fileinput.CSVParser;
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
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Optional;

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
    private ObservableList<Movie> movieObservableList;

    @FXML
    private Button upLoadButton;

    private GeneralVerificationMethods generalVerificationMethods;

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
            upLoadButton.setVisible(true);
            nameLabel.setText(user.getName());
            ageLabel.setText(user.getAge().toString());
            genderLabel.setText(user.getGender().name());
            nickNameLabel.setText(user.getNickName());
            statusLabel.setText("You are Admin");
        }

        movieObservableList = FXCollections.observableArrayList();
        movieObservableList.addAll(FilmApp.dataBase.getMovieList());
        movieId.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieId"));
        nameId.setCellValueFactory(new PropertyValueFactory<Movie,String>("movieName"));
        typeId.setCellValueFactory(new PropertyValueFactory<Movie,MovieType>("movieType"));
        genreId.setCellValueFactory(new PropertyValueFactory<Movie,Genre>("genres"));
        dateId.setCellValueFactory(new PropertyValueFactory<Movie,String>("releaseDate"));
        ratingId.setCellValueFactory(new PropertyValueFactory<Movie,Double>("rating"));

        tableView.setItems(movieObservableList);

    }


    @FXML
    private void search(ActionEvent event)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        try
        {
            switch (filterBox.getValue())
            {
                case "Показать все":
                    tableView.setItems(movieObservableList);
                    break;
                case "По индентификатору":
                    searchByIdToDisplay();
                    break;
                case "Названию":
                    searchByNameToDisplay();
                    break;
                case "Дате":
                    searchByDateToDisplay();
                    break;
            }
        }
        catch (NullPointerException e)
        {
            alert.setContentText("Выберите по чему проводить поиск");
            alert.showAndWait();
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
            alert.setContentText("Выберите фильм");
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

    private void searchByIdToDisplay()
    {
        if(generalVerificationMethods.notEmptyField(findField.getText()))
        {
            tableView.setItems(FXCollections.observableArrayList(FilmApp.dataBase.idSearch(findField.getText())).sorted((o1, o2)->-Double.compare(o1.getRating(), o2.getRating())));
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Введите данные в поле поиска");
            alert.showAndWait();
        }
    }

    private void searchByNameToDisplay()
    {
        if(generalVerificationMethods.notEmptyField(findField.getText()))
        {
            tableView.setItems(FXCollections.observableArrayList(FilmApp.dataBase.nameSearch(findField.getText())).sorted(new Comparator<Movie>() {
                @Override
                public int compare(Movie movie, Movie t1)
                {
                    return -Double.compare(movie.getRating(), t1.getRating());
                }
            }));
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Введите данные в поле поиска");
            alert.showAndWait();
        }
    }

    private void searchByDateToDisplay()
    {
        if(generalVerificationMethods.notEmptyField(findField.getText()))
        {
            tableView.setItems(FXCollections.observableArrayList(FilmApp.dataBase.dataSearch(findField.getText())).sorted(new Comparator<Movie>() {
                @Override
                public int compare(Movie movie, Movie t1) {
                    return -Double.compare(movie.getRating(), t1.getRating());
                }
            }));
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Введите данные в поле поиска");
            alert.showAndWait();
        }
    }

   public void upLoad(ActionEvent actionEvent)
    {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV File", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(FilmApp.primaryStage);
        if(file != null)
        {
            CSVParser csvParser = new CSVParser();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Select");
            alert.setHeaderText("Выберите опции:");

            ButtonType addTo = new ButtonType("Добавить к существующим");
           // ButtonType replaceAdd = new ButtonType("Заменить новыми");
            ButtonType cancel = new ButtonType("Отмена");

            alert.getButtonTypes().clear();

            alert.getButtonTypes().addAll(addTo,cancel);

            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == addTo)
            {
               csvParser.addToDataBase(file);
               upDateDisplayInfo();
            }
        }
    }

    void upDateDisplayInfo()
    {
        movieObservableList.clear();
        movieObservableList.addAll(FilmApp.dataBase.getMovieList());
        tableView.setItems(movieObservableList);
    }

}
