/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.VITRI;
import Backend.Sevices.Impl.DuanServicesImpl;
<<<<<<< HEAD
import Foundation.Transdata;
=======
import Foundation.AlertMess;
>>>>>>> 3cbc7fe65af8b44ae502d29f27963dbafcf0d83d
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
    private JFXTextField txtProjectID;
    
    @FXML
    private JFXTextField txtProjectName;
    
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
<<<<<<< HEAD
           LocalDate date = dtNgayBatDau.getValue();
           Date start = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            System.out.println(start);
            System.out.println("aa");
            System.out.println(new Date());
            System.out.println(txtProjectID.getText());
            System.out.println(txtProjectName.getText());
//            System.out.println("aaaa");
//            System.out.println(dtStartDay.getValue());
          System.out.println(date);
          DA.create(4, "xxx", "aa", start, TRANGTHAIDUAN.DANGLAM.toString());
//          DA.create(Integer.parseInt(IDuser.getText()), txtProjectID.getText(), txtProjectName.getText(),start , TRANGTHAIDUAN.DANGLAM.toString());

=======
            AlertMess.Instance().ShowMessSuccess("Success!");
>>>>>>> 3cbc7fe65af8b44ae502d29f27963dbafcf0d83d
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
