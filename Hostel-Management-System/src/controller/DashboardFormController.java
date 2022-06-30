package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import bo.custom.MakeReservationBO;
import bo.custom.ReservationDetailsBO;
import bo.custom.impl.ReservationDetailsBOImpl;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import entity.Room;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class DashboardFormController {
    public AnchorPane DashboardFormContext;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox cmbRoomID;
    public JFXTextField txtQty;

    //MakeReservationBO makeReservationBO = (MakeReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize() throws Exception {
        loadDateAndTime();
    }



    public void btnRoomOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ManageRoomForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnStudentOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ManageStudentForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UserManagementForm.fxml"))));
        stage.centerOnScreen();

    }

    public void btnGoBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LogInForm.fxml"))));
        stage.centerOnScreen();
    }



    public void btnReservationDetail(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/ReservationDetailForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnBookRoomOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MakeReservationForm.fxml"))));
        stage.centerOnScreen();
    }
    private void loadDateAndTime() {
        lblDate.setText(new SimpleDateFormat("yyy-MM-dd").format(new Date()));
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour()+":"+
                    currentTime.getMinute()+":"+
                    currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
            clock.setCycleCount(Animation.INDEFINITE);
            clock.play();


    }

    public void btnKeyMoneyOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) DashboardFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/KeyMoneyForm.fxml"))));
        stage.centerOnScreen();
    }
}
