package controller;

import bo.BOFactory;
import bo.custom.UserBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.UserDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.ValidationUtil;
import view.tm.UserTM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserManagementFormController {
    public AnchorPane UserManagementFormContext;
    public Label lblDate;
    public Label lblTime;
    public JFXTextField txtNic;
    public JFXTextField txtName;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXTextField txtConfirmPassword;
    public JFXButton btnAddUser;
    public JFXButton btnCancel;
    public TableView<UserTM> tblUserData;
    public JFXTextField txtSearch;
    public TableColumn colNic;
    public TableColumn colName;
    public TableColumn colUserName;
    public TableColumn colPassword;
    public TableColumn colOption;
    private final UserBO uBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    private final LinkedHashMap<JFXTextField, Pattern> regexMap = new LinkedHashMap<>();

    public void initialize() {
        btnAddUser.setDisable(true);
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        try {
            loadAllUsers(uBO.getAllUsers());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
        regexMap.put(txtNic, Pattern.compile("^[0-9]{12,15}(V)?(v)?$"));
        regexMap.put(txtName, Pattern.compile("^[A-z ]+$"));
        regexMap.put(txtUserName, Pattern.compile("^[A-z_.0-9]{5,20}$"));
        regexMap.put(txtPassword, Pattern.compile("^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})$"));
        regexMap.put(txtConfirmPassword, Pattern.compile("^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})$"));

        tblUserData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setUpdateFields(newValue);
            }
        });
    }
    private void setUpdateFields(UserTM tm) {
        btnAddUser.setDisable(false);
        txtNic.setText(tm.getNic());
        txtName.setText(tm.getName());
        txtUserName.setText(tm.getUserName());
        txtPassword.setText(tm.getPassword());
        txtConfirmPassword.setText(tm.getPassword());
        btnAddUser.setText("Update");
    }
    private void loadAllUsers(ArrayList<UserDTO> users) {
        ObservableList<UserTM> obList = FXCollections.observableArrayList();
        obList.addAll(users.stream().map(dto -> {
            JFXButton btn = new JFXButton("Delete");
            btn.setStyle("-fx-border-color: Black");
            btn.setOnAction(event -> {
                deleteUser(dto.getNic());
            });
            return new UserTM(dto.getNic(), dto.getName(), dto.getUserName(), dto.getPassword(), btn);
        }).collect(Collectors.toList()));
        tblUserData.setItems(obList);
    }
    public void deleteUser(String nic) {
        try {
            if (new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure?", ButtonType.YES, ButtonType.NO).showAndWait().get().equals(ButtonType.YES)) {
                uBO.deleteUser(nic);
                new Alert(Alert.AlertType.CONFIRMATION,"User Deleted..!").show();
            }
            loadAllUsers(uBO.getAllUsers());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }


    public void btnAddUserOnAction(ActionEvent actionEvent) {
        if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
            try {
                if (btnAddUser.getText().equals("Add User")) {
                    if (uBO.saveUser(new UserDTO(txtNic.getText(), txtName.getText(), txtUserName.getText(), txtPassword.getText())))
                        new Alert(Alert.AlertType.CONFIRMATION,"User Saved..!").show();
                } else {
                    if (uBO.updateUser(new UserDTO(txtNic.getText(), txtName.getText(), txtUserName.getText(), txtPassword.getText())))
                        new Alert(Alert.AlertType.CONFIRMATION,"User Updated..!").show();
                }
                loadAllUsers(uBO.getAllUsers());
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "User Already Exists!", ButtonType.OK).show();
            }
            btnCancel.fire();
        } else {
            new Alert(Alert.AlertType.WARNING, "Passwords Doesn't Match!").show();
        }

    }

    public void resetFields(JFXTextField... fields) {
        for (JFXTextField field : fields) {
            field.getParent().setStyle("-fx-border-color :   #EDEDF0;" + "-fx-border-width:1.5;" + "-fx-border-radius:  5;" + "-fx-background-radius:  5;");
            field.clear();
        }
    }


    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        btnAddUser.setText("Add User");
        btnAddUser.setDisable(true);
        tblUserData.getSelectionModel().clearSelection();
        resetFields(txtNic, txtName, txtUserName, txtPassword, txtConfirmPassword);

    }

    public void btnGoBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) UserManagementFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
        stage.centerOnScreen();
    }

    public void validateFieldsOnKeyReleased(KeyEvent keyEvent) {
        Object obj = ValidationUtil.validate(regexMap, btnAddUser);
        if (keyEvent.getCode().equals(KeyCode.ENTER))
            if (obj instanceof JFXTextField) ((JFXTextField) obj).requestFocus();
            else btnAddUser.fire();

    }

    public void searchUserOnKeyReleased(KeyEvent keyEvent) {
        try {
            loadAllUsers(uBO.getMatchingUsers(txtSearch.getText()));
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
        }
    }
}

