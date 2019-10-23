package com.accenture.oopapp.frontend.entrance;

import com.accenture.oopapp.datacheck.GeneralVerificationMethods;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.frontend.mainform.MainFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SingInController
{
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button singInButton;
    @FXML
    private Button singUpButton;

    @FXML
    void signingIn(ActionEvent event) throws IOException
    {
        GeneralVerificationMethods generalVerificationMethods = new GeneralVerificationMethods();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String userText = loginField.getText();
        String passwordText = passwordField.getText();
        if(generalVerificationMethods.notEmptyField(userText) && generalVerificationMethods.notEmptyField(passwordText))
        {
            if(FilmApp.usersDataBase.isConnect(userText,passwordText))
            {
                Parent root = FXMLLoader.load(MainFormController.class.getResource("MainForm.fxml"));
                FilmApp.primaryStage.close();
                FilmApp.primaryStage.setScene(new Scene(root));
                FilmApp.primaryStage.setTitle("Приложение Фильм");
                FilmApp.primaryStage.show();

            }
            else
            {
                alert.setTitle("Ошибка подключения");
                alert.setContentText("Проверьте правильность ввода данных");
                alert.showAndWait();
            }
        }
        else
        {
            alert.setTitle("Ошибка");
            alert.setContentText("Вы должны заполнить все поля");
            alert.showAndWait();
        }

    }

    @FXML
    void signingUp(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(SingInController.class.getResource("SingUp.fxml"));
        FilmApp.primaryStage.close();
        FilmApp.primaryStage.setScene(new Scene(root));
        FilmApp.primaryStage.setTitle("Регистрация");
        FilmApp.primaryStage.show();
    }

}
