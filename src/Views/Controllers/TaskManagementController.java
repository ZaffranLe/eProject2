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
    private int col;
    private int row;

    public GridPane getBoardProject() {
        return BoardProject;
    }

    public void setBoardProject(GridPane BoardProject) {
        this.BoardProject = BoardProject;
    }

    public DuanServicesImpl getDuAn() {
        return duAn;
    }

    public void setDuAn(DuanServicesImpl duAn) {
        this.duAn = duAn;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setIdUser(Transdata.Instance().getUserLoginID());
            duAn = new DuanServicesImpl();
            List<Duan> lstToDo = duAn.getAllByStatus(Integer.parseInt(getIdUser()), TRANGTHAIDUAN.DANGLAM.toString());
            List<Duan> lstDone = duAn.getAllByStatus(Integer.parseInt(getIdUser()), TRANGTHAIDUAN.HOANTHANH.toString());
            BoardProject.setPadding(new Insets(30, 30, 30, 30));
            for (Duan DA : lstToDo) {
                Node node = (Node) FXMLLoader.load(getClass().getResource("/Views/panelProjects.fxml"));
                Label lbName = (Label) node.lookup("#ProjectName");
                lbName.setText(DA.getTenDuAn());
                Label lbStatus = (Label) node.lookup("#ProjectStatus");
                lbStatus.setText("Đang Làm");
                Label lbID = (Label) node.lookup("#idProject");
                lbID.setText(DA.getIDDuAn());
                BoardProject.add(node, col, row);
                col = col++;
                if (col == 2) {
                    col = 0;
                    row = row++;
                }
            }
            for (Duan DA : lstDone) {
                Node node = (Node) FXMLLoader.load(getClass().getResource("/Views/panelProjects.fxml"));
                Label lbName = (Label) node.lookup("#ProjectName");
                lbName.setText(DA.getTenDuAn());
                Label lbStatus = (Label) node.lookup("#ProjectStatus");
                lbStatus.setText("Hoàn Thành");
                Label lbID = (Label) node.lookup("#idProject");
                lbID.setText(DA.getIDDuAn());
                BoardProject.add(node, col, row);
                col = col++;
                if (col == 2) {
                    col = 0;
                    row = row++;
                }
            }
//            for (int i = 0; i < 6; i++) {
//                System.out.println(getCol()+"va"+getRow());
//                 Node node = (Node) FXMLLoader.load(getClass().getResource("/Views/panelProject.fxml"));
//                Label lbName = (Label) node.lookup("#ProjectName");
//                lbName.setText("hihihi");
//                Label lbStatus = (Label) node.lookup("#ProjectStatus");
//                lbStatus.setText("hahahaha");
//                BoardProject.add(node, getCol(), getRow());
//                setCol(getCol()+1);
//                if (getCol() == 2) {
//                    setCol(0);
//                    setRow(getRow()+1);
//                }
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void openCreateProject(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/Views/AddProject.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Label lookup = (Label)scene.lookup("#IDuser");
        lookup.setText(getIdUser());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


   

}
