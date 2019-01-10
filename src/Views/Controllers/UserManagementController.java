/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class UserManagementController implements Initializable {

    @FXML
    private Label txtUserName;
    @FXML
    private Label txtEmail;
    @FXML
    private Label txtPhoneNumber;
    @FXML
    private Label txtAddress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnEditClick(MouseEvent event) {
    }

    @FXML
    private void btnChangePassword(MouseEvent event) {
    }
    
}
