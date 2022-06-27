package controller;

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

public class DashboardFormController {
    public AnchorPane DashboardFormContext;
    public Label lblDate;
    public Label lblTime;

    public void initialize(){
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

    }

    public void btnGoBackOnAction(ActionEvent actionEvent) {

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
}
