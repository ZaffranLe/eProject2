/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuSide;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class SideBarController implements Initializable {

    @FXML
    private AnchorPane content;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void taskManagement(MouseEvent event) throws IOException {
        Parent fxml =  FXMLLoader.load(getClass().getResource("/TaskManagement/TaskManagement.fxml"));
        content.getChildren().removeAll();

        content.getChildren().setAll(fxml);
    }

    @FXML
    private void userManagement(MouseEvent event) throws IOException {
        Parent fxml =  FXMLLoader.load(getClass().getResource("/UserManagement/UserManagement.fxml"));
        content.getChildren().removeAll();

        content.getChildren().setAll(fxml);
    }
    
}
