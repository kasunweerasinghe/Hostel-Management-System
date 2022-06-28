package controller;

import bo.BOFactory;
import bo.custom.KeyMoneyRemainStudentsBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tm.RemainKeyMoneyDetailsTM;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class KeyMoneyFormController {
    public Label lblDate;
    public Label lblTime;
    public TableView<RemainKeyMoneyDetailsTM> tblKeyMoney;
    public AnchorPane keyMoneyFormContext;


    private final KeyMoneyRemainStudentsBO keyMoneyRemainStudentsBO = (KeyMoneyRemainStudentsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.KEYMONEYREMAINSTUDENTS);


    public void initialize() {
        tblKeyMoney.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblKeyMoney.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("name"));
        tblKeyMoney.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("address"));
        tblKeyMoney.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("contactNo"));
        tblKeyMoney.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("resId"));
        tblKeyMoney.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("date"));
        tblKeyMoney.getColumns().get(6).setCellValueFactory(new PropertyValueFactory("roomId"));
        tblKeyMoney.getColumns().get(7).setCellValueFactory(new PropertyValueFactory("status"));

        loadAllData();
    }

    private void loadAllData() {
        List<Object[]> list = keyMoneyRemainStudentsBO.getKeyMoneyAndStudentDetails();
        for (Object[] objects : list) {
            String stu_Id= (String) objects[0];
            String name= (String) objects[1];
            String address= (String) objects[2];
            String con_No= (String) objects[3];
            String res_Id= (String) objects[4];
            LocalDate date= (LocalDate) objects[5];
            String roomId= (String) objects[6];
            String status= (String) objects[7];
            tblKeyMoney.getItems().add(new RemainKeyMoneyDetailsTM(stu_Id,name,address,con_No,res_Id,date,roomId,status));
        }
    }


    public void btnGoBackOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage) keyMoneyFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DashboardForm.fxml"))));
        stage.centerOnScreen();
    }
}
