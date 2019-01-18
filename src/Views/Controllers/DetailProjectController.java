/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import Backend.Enum.TRANGTHAITASK;
import Backend.Model.Duan;
import Backend.Model.Nguoidung;
import Backend.Model.Noidung;
import Backend.Sevices.Impl.NguoidungServicesImpl;
import Backend.Sevices.Impl.NoidungServiceImpl;
import Backend.Sevices.Impl.UserSevicesImpl;
import Foundation.Transdata;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
        try {
            loadForm();
        } catch (IOException ex) {
            Logger.getLogger(DetailProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }    
    public void loadForm() throws IOException{
        NoidungServiceImpl ndS = new NoidungServiceImpl();
        List<Noidung> lstTask  = ndS.getAllByDuAn(Transdata.Instance().getProjectID());
        NguoidungServicesImpl userS = new NguoidungServicesImpl();
        for (Noidung noidung : lstTask) {
            if (noidung.getTrangThai().equals(TRANGTHAITASK.CANLAM.toString())) {
                // TODO
                String userName = "";
                List<Nguoidung> lstUser = userS.getAllByTask(noidung.getIDNoiDung());
                System.out.println(lstUser.size());
                for (Nguoidung nguoidung : lstUser) {
                    userName = userName + nguoidung.getHoTen()+",";
                }
                vbToDo.getChildren().add(getNode(noidung.getIDNoiDung(), noidung.getTieuDe(), userName));
            }
            if (noidung.getTrangThai().equals(TRANGTHAITASK.DANGLAM.toString())) {
                // TODO
                String userName = "";
                List<Nguoidung> lstUser = userS.getAllByTask(noidung.getIDNoiDung());
                System.out.println(lstUser.size());
                for (Nguoidung nguoidung : lstUser) {
                    userName = userName + nguoidung.getHoTen()+",";
                }
                vbInProgress.getChildren().add(getNode(noidung.getIDNoiDung(), noidung.getTieuDe(), userName));
            }
            if (noidung.getTrangThai().equals(TRANGTHAITASK.CHODUYET.toString())) {
                // TODO
                String userName = "";
                List<Nguoidung> lstUser = userS.getAllByTask(noidung.getIDNoiDung());
                System.out.println(lstUser.size());
                for (Nguoidung nguoidung : lstUser) {
                    userName = userName + nguoidung.getHoTen()+",";
                }
                vbSolved.getChildren().add(getNode(noidung.getIDNoiDung(), noidung.getTieuDe(), userName));
            }
            if (noidung.getTrangThai().equals(TRANGTHAITASK.HOANTHANH.toString())) {
                // TODO
                String userName = "";
                List<Nguoidung> lstUser = userS.getAllByTask(noidung.getIDNoiDung());
                System.out.println(lstUser.size());
                for (Nguoidung nguoidung : lstUser) {
                    userName = userName + nguoidung.getHoTen()+",";
                }
                vbComplete.getChildren().add(getNode(noidung.getIDNoiDung(), noidung.getTieuDe(), userName));
            }
        }
        
    }
    public Node getNode(String id, String tittle, String userName) throws IOException{
         Node node = (Node) FXMLLoader.load(getClass().getResource("/Views/panelMission.fxml"));
            Label lbID = (Label) node.lookup("#lbIDTask");
            lbID.setText(id);
            Label lbTitle = (Label) node.lookup("#lbTitleTask");
            lbTitle.setText(tittle);
            Label lbUserName = (Label) node.lookup("#lbUserName");
            lbUserName.setText(userName);
            return node;
    }
    
    public void initData(Duan DA){
//        setDa(DA);
    }

    @FXML
    private void addTask(MouseEvent event) throws IOException {
        System.out.println(lbID.getText());
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
