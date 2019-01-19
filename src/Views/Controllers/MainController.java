package Views.Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Backend.Controller.NguoidungJpaController;
import Backend.Model.Nguoidung;
import Foundation.AlertMess;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.stage.WindowEvent;
import Backend.Sevices.UserSevices;
import Backend.Sevices.Impl.UserSevicesImpl;
import Foundation.Transdata;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author OS
 */
public class MainController implements Initializable {

    public static Stage stage = null;

    private Label label;
    @FXML
    private Pane contentArea;
    @FXML
    private Label btn_signup;

    @FXML
    private JFXTextField Email;

    @FXML
    private JFXPasswordField Password;

    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void openRegistration(MouseEvent event) throws Exception {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Views/Registration.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);

    }

    @FXML
    private void closeApp(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void login(MouseEvent event) throws IOException {
        UserSevicesImpl userS = new UserSevicesImpl();
        if (Validate.Instance().validateEmail(Email.getText()) && Validate.Instance().validatePassword(Password.getText())) {
            if (userS.Login(Email.getText(), Password.getText())) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Views/SideBar.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Task Management");
                stage.setScene(scene);
                
                this.stage = stage;
                stage.show();
//                Controllers.MainLaunch.stage.hide();
              ((Node) (event.getSource())).getScene().getWindow().hide();
                
                //Goi su kien khi nhan nut tat (dung de chuyen trang thai dang nhap)
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        try {
                            System.out.println("Stage is closing");
                            NguoidungJpaController userJPA = new NguoidungJpaController();
                            Nguoidung user = Transdata.Instance().getUserLogin();
                            user.setTrangThaiDangNhap(false);
                            userJPA.edit(user);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });

            }
        }
    }

}
