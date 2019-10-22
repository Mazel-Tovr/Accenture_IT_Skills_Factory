package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.review.Review;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.users.Administrator;
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
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Optional;

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

    @FXML
    public Label labelReviewDescription;

    @FXML
    private Label rateLabel;

    @FXML
    private Button buttonReview;

    @FXML
    private Button delButton;

    private ObservableList<Review> reviewObservableList = FXCollections.observableArrayList();;

    private Movie movie;
    private Person person;
    private Review review = null;
    private boolean isUser;
    public void initialize()
    {
        movie = FilmApp.session.getMovie();
        descriptionField.setText(movie.getDescription());
        reviewObservableList.addAll(movie.getFilmsReview());
        person = FilmApp.session.getLastEnteredUser();


        if(person instanceof User)
        {
            descriptionField.setDisable(true);
            isUser = true;
        }
        else
        {
            rateLabel.setVisible(false);
            ratingField.setVisible(false);
            buttonReview.setText("Сохранить");
            labelReviewDescription.setText("Редактировать отзывы к фильму");
            delButton.setVisible(true);
            isUser=false;
        }

        userId.setCellValueFactory(new PropertyValueFactory<Review, String>("personWhoWroteIt"));
        dataId.setCellValueFactory(new PropertyValueFactory<Review, String>("postDate"));
        reviewId.setCellValueFactory(new PropertyValueFactory<Review, String>("textOfReview"));
        ratingId.setCellValueFactory(new PropertyValueFactory<Review,Double>("youLikedFilm"));
        tableReview.setItems(reviewObservableList);

    }

    @FXML
    void goBack(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(MainFormController.class.getResource("MainForm.fxml"));
        FilmApp.primaryStage.close();
        FilmApp.primaryStage.setScene(new Scene(root));
        FilmApp.primaryStage.setTitle("Приложение Фильм");
        FilmApp.primaryStage.show();
    }

    @FXML
    void postReview(ActionEvent event)
    {
       Alert alert = new Alert(AlertType.ERROR);
       if(isUser)
       {
           if (tryParseDouble(ratingField.getText()) &&
                   (Double.parseDouble(ratingField.getText()) >= 0 && Double.parseDouble(ratingField.getText()) < 100) &&
                   !reviewField.getText().equals("")) {
               alert = new Alert(AlertType.INFORMATION);
               alert.setContentText("Отзыв успешно добавлен");
               alert.setTitle("Info");
               alert.showAndWait();
               User user = (User) person;
               user.addReview(movie, reviewField.getText(), Double.parseDouble(ratingField.getText()));

               upDateTables();
           }
           else
           {
               alert.setTitle("Error");
               alert.setContentText("Вы должны ввети текст отзыва,а также поставить оценку фильму (от 0 до 100)");
               alert.showAndWait();
           }
       }
       else
       {
            if(review != null && !reviewField.getText().equals(""))
            {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setHeaderText("Подтверждение");
                alert.setHeaderText("Вы уверены, что хотите изменить обзор к фильму: "+movie.getMovieName());
                alert.setContentText("Пользователя "+ review.getPersonWhoWroteIt());
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == ButtonType.OK)
                {
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setContentText("Отзыв успешно отредактирован");
                    alert.setTitle("Info");
                    alert.showAndWait();
                    Administrator admin = (Administrator)person;
                    review.setTextOfReview(reviewField.getText() + "\nEdited by "+ admin.getNickName());
                    review = null;
                    upDateTables();
                }
            }
            else
            {
                alert.setTitle("Error");
                alert.setContentText( review != null ? "Поле не может быть пустым":"Выбирете отзыв");
                alert.showAndWait();
            }
       }
    }


    void upDateTables()
    {
        reviewObservableList.clear();
        reviewObservableList.addAll(movie.getFilmsReview());
        tableReview.setItems(reviewObservableList);
    }

    private static boolean tryParseDouble(String value)
    {
        try
        {
            Double.parseDouble(value);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }


    public void chooseItem(MouseEvent mouseEvent)
    {
        if(!isUser)
        {
            reviewField.clear();
            review = tableReview.getSelectionModel().getSelectedItem();
            reviewField.setText(review.getTextOfReview());
        }
    }

    public void deleteReview(ActionEvent actionEvent)
    {

        if(review != null)
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Подтверждение");
            alert.setHeaderText("Вы уверены, что хотите удалить обзор к фильму: " + movie.getMovieName());
            alert.setContentText("Пользователя " + review.getPersonWhoWroteIt());
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get() == ButtonType.OK)
            {
                alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Отзыв успешно удален");
                alert.setTitle("Info");
                alert.showAndWait();
                movie.removeReview(review);
                movie.recalculateFilmRating();
                upDateTables();
                review = null;
            }
        }
        else
        {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Выбирете отзыв");
            alert.showAndWait();
        }

    }
}

