package Views.Controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import Backend.Sevices.UserSevices;
import Backend.Sevices.Impl.UserSevicesImpl;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

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
        if (CheckValid(Email.getText(), Password.getText())) {
            if (userS.Login(Email.getText(), Password.getText())) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Views/SideBar.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Task Management");
                stage.setScene(scene);
                
                this.stage = stage;
                stage.show();
            }
        }
    }

    public boolean CheckValid(String Email, String Password) {
        if (Email.isEmpty() || Password.isEmpty()) {
            AlertMess alertMess = new AlertMess("Email or Password is empty!");
            alertMess.ShowMessError();
            return false;
        }
        return true;
    }

}
