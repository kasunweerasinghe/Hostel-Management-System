package controller;

import bo.BOFactory;
import bo.custom.ReservationDetailsBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.ReservationTM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ReservationDetailFormController {
    public AnchorPane ReservationDetailsFormContext;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtReservationID;
    public JFXTextField txtDate;
    public JFXTextField txtRoomID;
    public JFXTextField txtStatus;
    public JFXButton btnUpdate;
    public JFXTextField txtStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDOB;
    public JFXTextField txtGender;
    public TableView<ReservationTM> tblReservationDetails;

    private final ReservationDetailsBO reservationDetailsBO = (ReservationDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATIONDETAILS);

    public void initialize() throws Exception {
        disableFields();
        loadDateAndTime();

        tblReservationDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("resID"));
        tblReservationDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("date"));
        tblReservationDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("roomId"));
        tblReservationDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblReservationDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("status"));
        TableColumn<ReservationTM, Button> lastCol = (TableColumn<ReservationTM, Button>) tblReservationDetails.getColumns().get(5);
        lastCol.setCellValueFactory(param -> {
            Button btnRemove = new Button("Remove");
            btnRemove.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Are You Sure ?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get().equals(ButtonType.YES)) {
                    try {
                        if (reservationDetailsBO.removeReservation(param.getValue().getResID())) {
                            tblReservationDetails.getItems().remove(param.getValue());
                            new Alert(Alert.AlertType.CONFIRMATION, "Removed..!!").show();
                            RoomDTO roomDTO = reservationDetailsBO.getRoom(param.getValue().getRoomId());
                            reservationDetailsBO.updateRoomQty(roomDTO.getRoomId(), roomDTO.getQty() + 1);

                            clearFields();

                            btnUpdate.setDisable(true);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            return new ReadOnlyObjectWrapper<>(btnRemove);
        });

        tblReservationDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtReservationID.setDisable(false);
                txtDate.setDisable(false);
                txtStatus.setDisable(false);
                txtRoomID.setDisable(false);
                txtStudentID.setDisable(false);

                txtReservationID.setEditable(false);
                txtDate.setEditable(false);
                txtStudentID.setEditable(false);
                txtRoomID.setEditable(false);

                txtName.setDisable(false);
                txtName.setEditable(false);
                txtAddress.setDisable(false);
                txtAddress.setEditable(false);
                txtContact.setDisable(false);
                txtContact.setEditable(false);
                txtDOB.setDisable(false);
                txtDOB.setEditable(false);
                txtGender.setDisable(false);
                txtGender.setEditable(false);

                txtReservationID.setText(newValue.getResID());
                txtDate.setText(String.valueOf(newValue.getDate()));
                txtRoomID.setText(newValue.getRoomId());
                txtStatus.setText(newValue.getStatus());
                btnUpdate.setDisable(false);



                StudentDTO student = reservationDetailsBO.getStudent(newValue.getStudentId());
                txtStudentID.setText(student.getStudentId());
                txtName.setText(student.getName());
                txtAddress.setText(student.getAddress());
                txtContact.setText(student.getContactNo());
                txtDOB.setText(String.valueOf(student.getDob()));
                txtGender.setText(student.getGender());

                txtStatus.requestFocus();
            }
        });

        loadAllReservationDetails();


    }
    private void disableFields() {
        btnUpdate.setDisable(true);
        txtReservationID.setDisable(true);
        txtDate.setDisable(true);
        txtStudentID.setDisable(true);
        txtRoomID.setDisable(true);
        txtStatus.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtDOB.setDisable(true);
        txtGender.setDisable(true);
    }

    private void clearFields() {
        tblReservationDetails.getSelectionModel().clearSelection();
        txtReservationID.clear();
        txtDate.clear();
        txtRoomID.clear();
        txtStatus.clear();
        txtStudentID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDOB.clear();
        txtGender.clear();
    }

    private void loadAllReservationDetails() throws Exception {
        List<ReservationDTO> allReservations = reservationDetailsBO.getAllReservations();
        for (ReservationDTO dto : allReservations) {
            tblReservationDetails.getItems().add(new ReservationTM(dto.getResId(), dto.getDate(), dto.getRoom().getRoomId(), dto.getStudent().getStudentId(), dto.getStatus()));
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        ReservationTM selectedItem = tblReservationDetails.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (reservationDetailsBO.updateReservationStatus(selectedItem.getResID(), txtStatus.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                selectedItem.setStatus(txtStatus.getText());
                tblReservationDetails.refresh();
                clearFields();
                disableFields();
                tblReservationDetails.requestFocus();
            }
        }
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



    public void btnGoBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) ReservationDetailsFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
        stage.centerOnScreen();
    }
}
