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
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void openRegistration(MouseEvent event) throws Exception {
        Parent fxml =  FXMLLoader.load(getClass().getResource("/Views/Registration.fxml"));
        contentArea.getChildren().removeAll();

        contentArea.getChildren().setAll(fxml);

    }
    
    @FXML
    private void closeApp(MouseEvent event){
    System.exit(0);
    }

    @FXML
    private void login(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/SideBar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Task Management");
        stage.setScene(scene);
        this.stage = stage;
        stage.show();
       
    }

    @FXML
    private void openRegistration_Click(MouseEvent event) {
    }   
    
    
}
