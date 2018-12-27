package Controllers;

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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class RegistrationController implements Initializable {

    @FXML 
     private AnchorPane pane;
    @FXML
    private Label backToLogin;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    private void closeApp(MouseEvent event){
    System.exit(0);
    }
    
    @FXML
    private void backToLogin(MouseEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("/Views/Main.fxml"));
         MainLaunch.stage.getScene().setRoot(root);
//         pane.getChildren().removeAll();
//         pane.getChildren().setAll(root);
           

    }
}
