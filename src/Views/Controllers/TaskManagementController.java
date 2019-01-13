/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Enum.TRANGTHAIDUAN;
import Backend.Model.Duan;
import Backend.Sevices.Impl.DuanServicesImpl;
import Foundation.Transdata;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class TaskManagementController implements Initializable {

    @FXML
    private GridPane BoardProject;
    public DuanServicesImpl duAn;
    public String idUser;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = Transdata.Instance().getUserLoginID();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            duAn = new DuanServicesImpl();
            List<Duan> lis = duAn.getAllByStatus(2, TRANGTHAIDUAN.DANGLAM.toString());
            for (Duan li : lis) {
                System.out.println(li.getTenDuAn());
                BoardProject.setPadding(new Insets(30, 30, 30, 30));
                Node[] nodes = new Node[1];
                Node node = (Node) FXMLLoader.load(getClass().getResource("/Views/panelProject.fxml"));
                Label lbName = (Label) node.lookup("#ProjectName");
                lbName.setText("hihi");
                Label lbStatus = (Label) node.lookup("#ProjectStatus");
                lbStatus.setText("hahah");
                BoardProject.add(node, 0, 0);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void openCreateProject(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/AddProject.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openProject(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/DetailProject.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public List<Duan> getListProjectDoing() {
        return duAn.getAllByStatus(Integer.parseInt(idUser), TRANGTHAIDUAN.DANGLAM.toString());
    }

    public List<Duan> getListProjectDone() {
        return duAn.getAllByStatus(Integer.parseInt(idUser), TRANGTHAIDUAN.HOANTHANH.toString());
    }

}
