/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author vukho
 */
public class PanelProjectController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void panelProjectClick(MouseEvent event) throws IOException {
        try {
//            Node node = (Node) FXMLLoader.load(getClass().getResource("/Views/DetailProject.fxml"));
//            DetailProjectController controller = node<DetailProjectController>getController();
        } catch (Exception e) {
        }
    }

}
