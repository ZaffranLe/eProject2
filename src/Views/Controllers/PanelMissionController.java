/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import Backend.Model.Noidung;
import Backend.Sevices.Impl.NoidungServiceImpl;
import Foundation.Transdata;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class PanelMissionController implements Initializable {

    @FXML
    private AnchorPane pnMission;
    @FXML
    private Label lbIDTask;
    @FXML
    private Label lbTitleTask;
    @FXML
    private Label lbUserName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void panelMissionClick(MouseEvent event) throws IOException {
        Transdata.Instance().setTaskID(lbIDTask.getText());
        Transdata.Instance().setIsEdit(true);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/AddMissionForProject.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Label lbTittle = (Label) scene.lookup("#tittleForm");
        lbTittle.setText("Detail Task");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
}
