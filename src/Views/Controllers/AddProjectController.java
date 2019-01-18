/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.VITRI;
import Backend.Sevices.Impl.DuanServicesImpl;
import Foundation.Transdata;
import Foundation.AlertMess;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private JFXTextField txtMaDuAn;
    
    @FXML
    private JFXTextField txtTenProject;
    
    @FXML
    private DatePicker dtNgayBatDau;
    


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
            LocalDate date = dtNgayBatDau.getValue();
            Date dt = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            DA.create(Integer.parseInt(IDuser.getText()), txtMaDuAn.getText(), txtTenProject.getText(),dt , TRANGTHAIDUAN.DANGLAM.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
