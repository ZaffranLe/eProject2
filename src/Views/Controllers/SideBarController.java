/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Controller.NguoidungJpaController;
import Backend.Model.Nguoidung;
import Foundation.Transdata;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import javafx.util.Duration;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class SideBarController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private ImageView imgBanhRang1;
    @FXML
    private ImageView imgBanhRang2;
    @FXML
    private ImageView imgBanhRang3;
    @FXML
    private ImageView imgBanhRang4;
    @FXML
    private JFXButton openDetail;

    /**
     * Initializes the controller class.
     */
    public static Stage stage = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        roltate();

    }

    @FXML
    private void taskManagement(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Views/TaskManagement.fxml"));
        content.getChildren().removeAll();

        content.getChildren().setAll(fxml);
    }

    @FXML
    private void userManagement(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/Views/UserManagement.fxml"));
        content.getChildren().removeAll();

        content.getChildren().setAll(fxml);
    }

    public void roltate() {
        RotateTransition rotation1 = new RotateTransition(Duration.seconds(2), imgBanhRang1);
        RotateTransition rotation2 = new RotateTransition(Duration.seconds(2), imgBanhRang2);
        RotateTransition rotation3 = new RotateTransition(Duration.seconds(2), imgBanhRang3);
        RotateTransition rotation4 = new RotateTransition(Duration.seconds(2), imgBanhRang4);

        rotation1.setByAngle(-360);
        rotation1.setCycleCount(100);
        rotation1.play();

        rotation2.setByAngle(360);
        rotation2.setCycleCount(100);
        rotation2.play();

        rotation3.setByAngle(360);
        rotation3.setCycleCount(100);
        rotation3.play();

        rotation4.setByAngle(360);
        rotation4.setCycleCount(100);
        rotation4.play();
    }

    @FXML
    private void btnLogout(MouseEvent event) {
        try {
            System.out.println("Stage is closing");
            NguoidungJpaController userJPA = new NguoidungJpaController();
            Nguoidung user = Transdata.Instance().getUserLogin();
            user.setTrangThaiDangNhap(false);
            userJPA.edit(user);
            FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Views/Main.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);

                
                this.stage = stage;
                stage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
              
             

                

          
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
