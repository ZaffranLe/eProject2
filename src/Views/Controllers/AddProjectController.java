/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Sevices.Impl.DuanServicesImpl;
import Foundation.AlertMess;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class AddProjectController implements Initializable {

    @FXML
    private Label IDuser;
    
    @FXML
    private JFXTextField txtProjectID;
    
    @FXML
    private JFXTextField txtProjectName;
    
    @FXML
    private DatePicker dtStartDay;
    
    @FXML
    private DatePicker dtEndDay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnThemProject(MouseEvent event) {
        try {
            DuanServicesImpl DA = new  DuanServicesImpl();
            AlertMess.Instance().ShowMessSuccess("Success!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
