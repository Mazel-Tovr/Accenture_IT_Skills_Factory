package com.accenture.oopapp.frontend.entrance;

import com.accenture.oopapp.datacontrol.GeneralVerificationMethods;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SingUpController
{
    @FXML
    private TextField nameId;

    @FXML
    private ComboBox<Gender> genderId;

    @FXML
    private TextField ageId;

    @FXML
    private TextField nickNameId;

    @FXML
    private PasswordField passWordId;

    private String name;
    private String nickName;
    private String passWord;
    private int age;
    private Gender gender;
    @FXML
    public void initialize()
    {
        genderId.setItems(FXCollections.observableArrayList(Gender.MALE,Gender.FEMALE));
    }

    public void completeRegistration(ActionEvent actionEvent) throws IOException {
        if(dataCheck())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Инофрмация");
            alert.setContentText("Регистрация успешно завершена");
            alert.showAndWait();
            FilmApp.usersDataBase.addUserToDataBase(new User(name,age,gender,nickName,passWord));
            goBack(actionEvent);
        }

    }

    public void goBack(ActionEvent actionEvent) throws IOException
    {
        Parent root = FXMLLoader.load(SingInController.class.getResource("SingIn.fxml"));
        FilmApp.primaryStage.close();
        FilmApp.primaryStage.setScene(new Scene(root));
        FilmApp.primaryStage.setTitle("Вход в приложение");
        FilmApp.primaryStage.show();
    }


    private boolean dataCheck()
    {
        GeneralVerificationMethods generalVerificationMethods = new GeneralVerificationMethods();
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Ошибка ввода");

        if (generalVerificationMethods.notEmptyField(nameId.getText()))
        {
            name = nameId.getText();
        } else
        {
            errorAlert.setContentText("Заполните поле с именем ");
            errorAlert.showAndWait();
            return false;
        }
        if (generalVerificationMethods.ageCheck(ageId.getText()))
        {
            age = Integer.parseInt(ageId.getText());
        } else
        {
            errorAlert.setContentText("Укажите возраст в диапазон от 3 до 100 лет ");
            errorAlert.showAndWait();
            return false;
        }
        if (generalVerificationMethods.notNullValue(genderId.getValue()))
        {
            gender = genderId.getValue();
        }
        else
        {
            errorAlert.setContentText("Выберите гендер");
            errorAlert.showAndWait();
            return false;
        }
        if (generalVerificationMethods.notEmptyField(nickNameId.getText()))
        {
            if(!FilmApp.usersDataBase.isUserExist(nickNameId.getText()))
            {
                nickName = nickNameId.getText();
            }
            else
            {
                errorAlert.setContentText("Пользователь с таким именем уже существует");
                errorAlert.showAndWait();
                return false;
            }
        }
        else
        {
            errorAlert.setContentText("Заполните поле с никнеймом ");
            errorAlert.showAndWait();
            return false;
        }
        if (generalVerificationMethods.notEmptyField(passWordId.getText()))
        {
            passWord = passWordId.getText();
        }
        else
        {
            errorAlert.setContentText("Заполните поле пароля");
            errorAlert.showAndWait();
            return false;
        }

        return true;
    }
}
