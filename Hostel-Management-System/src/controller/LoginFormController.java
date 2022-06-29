package controller;

import bo.BOFactory;
import bo.custom.LoginBO;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginFormController {
    public AnchorPane LogInFormContext;
    public JFXTextField txtUserName;
    public JFXTextField txtShowPassword;
    public JFXPasswordField txtPassword;

    private LoginBO lBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);


    public void btnLogInOnAction(ActionEvent actionEvent) {
        try {
            if(lBO.checkCredentials(txtUserName.getText(),txtPassword.getText())){
                LogInFormContext.getChildren().clear();
                LogInFormContext.getChildren().add(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml")));
            }else{
                new Alert(Alert.AlertType.WARNING,"Incorrect UserName Or Password", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void UnmaskPassword(MouseEvent mouseEvent) {
        txtShowPassword.setText(txtPassword.getText());
        txtShowPassword.setVisible(true);

    }

    public void MaskPassword(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(false);
        txtPassword.requestFocus();
    }

    public void checkCredentials(ActionEvent actionEvent) {
    }
}
