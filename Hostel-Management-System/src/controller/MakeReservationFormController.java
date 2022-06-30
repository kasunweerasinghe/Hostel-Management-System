package controller;

import bo.BOFactory;
import bo.custom.MakeReservationBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Room;
import entity.Student;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.ReservationTM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class MakeReservationFormController {
    public AnchorPane StudentRegisterFormContext;
    public Label lblDate;
    public Label lblTime;
    public JFXComboBox<StudentDTO> cmbStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDOB;
    public JFXComboBox cmbGender;
    public JFXComboBox<RoomDTO> cmbRoomID;
    public JFXTextField txtType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtQty;
    public JFXTextField txtPaidAmount;
    public TableView<ReservationTM> tblReservationDetail;
    public Label lblReservationID;
    public JFXButton btnReservation;


    MakeReservationBO makeReservationBO = (MakeReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize() throws Exception {
        loadDateAndTime();
        tblReservationDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("resID"));
        tblReservationDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("date"));
        tblReservationDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("roomId"));
        tblReservationDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblReservationDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("status"));

        lblReservationID.setText(generateNewReservationID());

        lblDate.setText(LocalDate.now().toString());


        clearFields();

        List<StudentDTO> allStudents = makeReservationBO.getAllStudents();
        for (StudentDTO studentDTO : allStudents) {
            cmbStudentID.getItems().add(studentDTO);
        }

        List<RoomDTO> allRooms = makeReservationBO.getAllRooms();
        for (RoomDTO roomDTO : allRooms) {
            cmbRoomID.getItems().add(roomDTO);
        }

        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("FeMale");

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtContact.setDisable(false);
                txtDOB.setDisable(false);
                cmbGender.setDisable(false);

                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContactNo());
                txtDOB.setText(String.valueOf(newValue.getDob()));
                cmbGender.getSelectionModel().select(newValue.getGender());
            }
        });

        cmbRoomID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                txtType.setDisable(false);
                txtKeyMoney.setDisable(false);
                txtQty.setDisable(false);
                txtType.setText(newValue.getType());
                txtKeyMoney.setText(newValue.getKeyMoney());
                txtQty.setText(String.valueOf(newValue.getQty()));
                btnReservation.setDisable(false);
            }
        });

        loadAllReservationDetails();
    }

    private void loadAllReservationDetails() throws Exception {
        List<ReservationDTO> allReservations = makeReservationBO.getAllReservations();
        for (ReservationDTO reservationDTO : allReservations) {
            tblReservationDetail.getItems().add(new ReservationTM(reservationDTO.getResId(),reservationDTO.getDate(),reservationDTO.getRoom().getRoomId(),reservationDTO.getStudent().getStudentId(),reservationDTO.getStatus()));
        }
    }

    public String generateNewReservationID() {
        return makeReservationBO.generateNewReservationID();
    }

    private void clearFields() {
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtDOB.setDisable(true);
        cmbGender.setDisable(true);

        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtContact.setEditable(false);
        txtDOB.setEditable(false);
        cmbGender.setEditable(false);

        txtType.setDisable(true);
        txtType.setEditable(false);
        txtKeyMoney.setDisable(true);
        txtKeyMoney.setEditable(false);
        txtQty.setDisable(true);
        txtQty.setEditable(false);
        btnReservation.setDisable(true);
    }

    public void btnGoBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) StudentRegisterFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
        stage.centerOnScreen();
    }


    public void btnReservationOnAction(ActionEvent actionEvent) throws Exception {
        double keyMoney=Double.parseDouble(txtKeyMoney.getText());
        double paidKeyMoney=Double.parseDouble(txtPaidAmount.getText());
        String status="";
        if (keyMoney==paidKeyMoney){
            status="Paid";
        }else{
            double balanceToPay=keyMoney-paidKeyMoney;
            status=String.valueOf(balanceToPay);
        }
        
        StudentDTO studentDTO = cmbStudentID.getValue();
        Student student=new Student(studentDTO.getStudentId(),studentDTO.getName(),studentDTO.getAddress(),studentDTO.getContactNo(),studentDTO.getDob(),studentDTO.getGender());

        RoomDTO roomDTO = cmbRoomID.getValue();
        Room room=new Room(roomDTO.getRoomId(),roomDTO.getType(),roomDTO.getKeyMoney(),roomDTO.getQty()-1);

        if (makeReservationBO.saveReservation(new ReservationDTO(lblReservationID.getText(), LocalDate.parse(lblDate.getText()),status,student,room))) {
            tblReservationDetail.getItems().add(new ReservationTM(lblReservationID.getText(),LocalDate.parse(lblDate.getText()),room.getRoomId(),student.getStudentId(),status));
            new Alert(Alert.AlertType.CONFIRMATION,"Reserved").show();
            makeReservationBO.updateRoomDetails(new RoomDTO(room.getRoomId(),room.getType(),room.getKeyMoney(),room.getQty()));
            txtPaidAmount.clear();
            cmbStudentID.getSelectionModel().clearSelection();
            cmbRoomID.getSelectionModel().clearSelection();
            btnReservation.setDisable(true);
            txtName.clear();
            txtAddress.clear();
            txtContact.clear();
            txtDOB.clear();
            cmbGender.getSelectionModel().clearSelection();
            txtType.clear();
            txtKeyMoney.clear();
            txtQty.clear();
            lblReservationID.setText(generateNewReservationID());
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

}
