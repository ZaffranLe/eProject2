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
        UserName.setText("");
        PhoneNumber.setText("");
        Address.setText("");
    }

    @FXML
    private void backToLogin(MouseEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
            Controllers.MainLaunch.stage.getScene().setRoot(root);
    }
    

    @FXML
    private void Register_click(MouseEvent event) {
        try {
            UserSevicesImpl UserS = new UserSevicesImpl();
            System.out.println(Address.getText());
            boolean checkRegister = UserS.Register(Email.getText(), Password.getText(), UserName.getText(), PhoneNumber.getText(), Address.getText());
            clearText();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
