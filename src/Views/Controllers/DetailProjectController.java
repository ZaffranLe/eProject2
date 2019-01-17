/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import Backend.Model.Duan;
import Backend.Sevices.Impl.NoidungServiceImpl;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class DetailProjectController implements Initializable {

    @FXML
    private VBox vbToDo;
    @FXML
    private Label txtMoTa;
    @FXML
    private VBox vbInProgress;
    @FXML
    private VBox vbSolved;
    @FXML
    private VBox vbComplete;
    @FXML
    private Label lbID;
    @FXML
    private Label lbNameProject;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NoidungServiceImpl ndS = new NoidungServiceImpl();
        
        
    }    
    public void initData(Duan DA){
//        setDa(DA);
    }

    @FXML
    private void addTask(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/AddMissionForProject.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private void detailTask(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/DetailMission.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void btnLocNhiemVu(MouseEvent event) {
    }

    @FXML
    private void addMember(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/AddMemberForProject.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
}
