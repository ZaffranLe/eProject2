/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Controller.DuanJpaController;
import Backend.Enum.TRANGTHAIDUAN;
import Backend.Model.Duan;
import Backend.Model.Nguoidung;
import Backend.Sevices.Impl.DuanServicesImpl;
import Backend.Sevices.Impl.NguoidungServicesImpl;
import Foundation.Transdata;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
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
            DuanJpaController daJ = new DuanJpaController();
            Duan findDuan = daJ.findDuan(idProject.getText());
            if (findDuan.getTrangThai().equals(TRANGTHAIDUAN.DANGLAM.toString())) {
                Transdata.Instance().setProjectID(idProject.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/DetailProject.fxml"));
                Scene scene = new Scene(loader.load());
                Label lbID = (Label) scene.lookup("#lbID");
                System.out.println(idProject.getText());
                lbID.setText(idProject.getText());
                Label lbName = (Label) scene.lookup("#lbNameProject");
                lbName.setText(ProjectName.getText());
                Label lbEndDate = (Label) scene.lookup("#lbEndDay");
                lbEndDate.setText("");
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
            if (findDuan.getTrangThai().equals(TRANGTHAIDUAN.HOANTHANH.toString())) {
                Transdata.Instance().setProjectID(idProject.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/DetailProject.fxml"));
                Scene scene = new Scene(loader.load());
                Label lbID = (Label) scene.lookup("#lbID");
                System.out.println(idProject.getText());
                lbID.setText(idProject.getText());
                Label lbName = (Label) scene.lookup("#lbNameProject");
                lbName.setText(ProjectName.getText());
                Label lbEndDate = (Label) scene.lookup("#lbEndDay");
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String format = df.format(findDuan.getNgayKetThuc());
                lbEndDate.setText("EndDate: "+format);
                JFXButton add = (JFXButton) scene.lookup("#btnAdd");
                add.setVisible(false);
                JFXButton edit = (JFXButton) scene.lookup("#btnEditPJ");
                edit.setVisible(false);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
     

}
