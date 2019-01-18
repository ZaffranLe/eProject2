/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Foundation.Transdata;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vukho
 */
public class PanelProjectController implements Initializable {
    /**
     * Initializes the controller class.
     */
    @FXML
    private Label idProject;
    @FXML
    private Label ProjectName;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void panelProjectClick(MouseEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/DetailProject.fxml"));
            Scene scene = new Scene(loader.load());
            Label lbID = (Label) scene.lookup("#lbID");
            lbID.setText(idProject.getText());
            Label lbName = (Label) scene.lookup("#lbNameProject");
            lbName.setText(ProjectName.getText());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Transdata.Instance().setProjectID(idProject.getText());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}