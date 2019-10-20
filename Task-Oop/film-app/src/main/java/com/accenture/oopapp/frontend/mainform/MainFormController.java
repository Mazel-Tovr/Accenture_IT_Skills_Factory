package com.accenture.oopapp.frontend.mainform;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.users.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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

    private User user;
    private ObservableList<Movie> listOfItems;
    private ObservableList<String> namesOfItems;
    public void initialize()
    {
        listOfItems = FXCollections.observableArrayList();
        user = FilmApp.dataBase.getLastEnteredUser();
        nameLable.setText(user.getName());
        ageLable.setText(user.getAge().toString());
        genderLable.setText(user.getGender().name());
        nickNameLable.setText(user.getNickName());
        statusLable.setText("Simple user");
        //listOfItems.addAll();
        List<String> list = new ArrayList<>();
        for (var item:FilmApp.dataBase.getMovieSet())
        {
            list.add(item.getMovieName());
        }
        namesOfItems = FXCollections.observableArrayList(list);
        filmsView.setItems(namesOfItems);
    }

    @FXML
    void search(ActionEvent event) {

    }
}
