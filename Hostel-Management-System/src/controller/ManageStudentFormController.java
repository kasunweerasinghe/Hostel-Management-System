package controller;

import bo.BOFactory;
import bo.custom.ManageStudentsBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.StudentTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ManageStudentFormController {
    public AnchorPane ManageStudentFormContext;
    public Label lblDate;
    public Label lblTime;
    public JFXButton btnSave;
    public JFXTextField txtStudentID;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXDatePicker txtDate;
    public JFXComboBox<String> cmbGender;
    public JFXButton btnDelete;
    public TableView<StudentTM> tblStudent;
    public TableColumn colStudentID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDOB;
    public TableColumn colGender;


    ManageStudentsBO manageStudentsBO = (ManageStudentsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MANAGESTUDENTS);

    public void initialize(){
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("contactNo"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("gender"));


//        colStudentID.setCellFactory(new PropertyValueFactory("studentId"));
//        colName.setCellFactory(new PropertyValueFactory("name"));
//        colAddress.setCellFactory(new PropertyValueFactory("address"));
//        colContact.setCellFactory(new PropertyValueFactory("contactNo"));
//        colDOB.setCellFactory(new PropertyValueFactory("dob"));
//        colGender.setCellFactory(new PropertyValueFactory("gender"));

        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("FeMale");

        clearFields();
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue==null);
            btnSave.setDisable(newValue==null);
            btnSave.setText(newValue !=null ? "Update":"Save");

            if (newValue != null){
                txtStudentID.setText(newValue.getStudentId());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContact.setText(newValue.getContactNo());
                txtDate.setValue(newValue.getDob());
                cmbGender.getSelectionModel().select(newValue.getGender());

                txtName.setDisable(false);
                txtStudentID.setDisable(false);
                txtAddress.setDisable(false);
                txtDate.setDisable(false);
                txtContact.setDisable(false);
                cmbGender.setDisable(false);
            }
        });

        loadAllStudentDetails();

    }

    private void clearFields() {
        txtStudentID.clear();
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();
        txtDate.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
        txtName.setDisable(true);
        txtStudentID.setDisable(true);
        txtAddress.setDisable(true);
        txtDate.setDisable(true);
        txtContact.setDisable(true);
        cmbGender.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadAllStudentDetails() {
        tblStudent.getItems().clear();
        List<StudentDTO> allStudents = manageStudentsBO.getAllStudents();
        for (StudentDTO student : allStudents) {
            tblStudent.getItems().add(new StudentTM(student.getStudentId(),student.getName(),student.getAddress(),student.getContactNo(),student.getDob(),student.getGender()));
        }
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equalsIgnoreCase("Save")){
            if (existStudent(txtStudentID.getText())){
                new Alert(Alert.AlertType.WARNING,"Student Already Exists").show();
            }
            manageStudentsBO.saveStudent(new StudentDTO(txtStudentID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(), LocalDate.parse(txtDate.getValue().toString()),cmbGender.getValue()));
            tblStudent.getItems().add(new StudentTM(txtStudentID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),LocalDate.parse(txtDate.getValue().toString()),cmbGender.getValue()));
            new Alert(Alert.AlertType.CONFIRMATION,"Saved..!").show();
        }else{
            if(!existStudent(txtStudentID.getText())){
                new Alert(Alert.AlertType.ERROR,"Student Not Exists").show();
            }
            manageStudentsBO.updateStudent(new StudentDTO(txtStudentID.getText(),txtName.getText(),txtAddress.getText(),txtContact.getText(),LocalDate.parse(txtDate.getValue().toString()),cmbGender.getValue()));
            new Alert(Alert.AlertType.CONFIRMATION,"Updated..!").show();

            StudentTM selectedItem = tblStudent.getSelectionModel().getSelectedItem();
            selectedItem.setStudentId(txtStudentID.getText());
            selectedItem.setName(txtName.getText());
            selectedItem.setAddress(txtAddress.getText());
            selectedItem.setContactNo(txtContact.getText());
            selectedItem.setDob(LocalDate.parse(txtDate.getValue().toString()));
            selectedItem.setGender(cmbGender.getValue());
            tblStudent.refresh();

            clearFields();
            tblStudent.getSelectionModel().clearSelection();
        }
    }


    public void textFields_Key_Released(KeyEvent keyEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String studentId = tblStudent.getSelectionModel().getSelectedItem().getStudentId();
        if (!existStudent(studentId)){
            new Alert(Alert.AlertType.WARNING,"Student is not Exists !!").show();
        }
        Alert alert=new Alert(Alert.AlertType.WARNING,"Are You Sure ?", ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get().equals(ButtonType.YES)){
            if (manageStudentsBO.deleteStudent(studentId)) {
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted..!").show();
                tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
                tblStudent.getSelectionModel().clearSelection();
                clearFields();
            }
        }

    }
    private boolean existStudent(String studentId) {
        return manageStudentsBO.checkStudentIsExists(studentId);
    }




    public void btnGoBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) ManageStudentFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
        stage.centerOnScreen();
    }

    public void btnAddNewStudentOnAction(ActionEvent actionEvent) {
        txtStudentID.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContact.setDisable(false);
        txtDate.setDisable(false);
        cmbGender.setDisable(false);
        txtStudentID.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
        txtDate.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
        tblStudent.getSelectionModel().clearSelection();
        btnDelete.setDisable(true);
        btnSave.setDisable(false);
        btnSave.setText("Save");
        txtStudentID.requestFocus();
    }
}
