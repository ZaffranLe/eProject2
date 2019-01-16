package Views.Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import Backend.Sevices.Impl.UserSevicesImpl;
import Foundation.AlertMess;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class RegistrationController implements Initializable {

    @FXML
    private Label backToLogin;
    @FXML
    private JFXPasswordField Password;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXTextField PhoneNumber;
    @FXML
    private JFXTextField Address;
    @FXML
    private JFXPasswordField RepeatPassword;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeApp(MouseEvent event) {
        System.exit(0);
    }

    private void clearText() {
        Email.setText("");
        Password.setText("");
        RepeatPassword.setText("");
        UserName.setText("");
        PhoneNumber.setText("");
        Address.setText("");
    }

    public boolean valid() {
        if (!Validate.Instance().validateEmail(Email.getText())) {
            return false;
        }
        if (!Validate.Instance().validateUserName(UserName.getText())) {
            return false;
        }
        if (!Validate.Instance().validatePassword(Password.getText())) {
            return false;
        }
        if (!Validate.Instance().validatePhoneNumber(PhoneNumber.getText())) {
            return false;
        }
        if (!Validate.Instance().validateAddress(Address.getText())) {
            return false;
        }
        if (!Password.getText().equals(RepeatPassword.getText())) {
            AlertMess.Instance().ShowMessError("Confirm password does not match");
            return false;
        }
        return true;
    }

    @FXML
    private void backToLogin(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
        Controllers.MainLaunch.stage.getScene().setRoot(root);
    }

    @FXML
    private void Register_click(MouseEvent event) {
        try {
            if (valid()) {
                UserSevicesImpl UserS = new UserSevicesImpl();
                boolean checkRegister = UserS.Register(Email.getText(), Password.getText(), UserName.getText(), PhoneNumber.getText(), Address.getText());
                if (checkRegister) {
                    AlertMess.Instance().ShowMessSuccess("Create acount sucess!");
                    clearText();
                }
            }else{
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
