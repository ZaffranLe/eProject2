/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Enum.TRANGTHAIDUAN;
import Backend.Model.Nguoidung;
import Backend.Sevices.Impl.NguoidungServicesImpl;
import Backend.Sevices.Impl.NoidungServiceImpl;
import Foundation.Transdata;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class AddMissionForProjectController implements Initializable {

    @FXML
    private JFXComboBox<bindDataComboBoxMemBer> cbThanhVien;
    @FXML
    private JFXTextField txtTenNhiemVu;
    @FXML
    private JFXTextArea txtMoTa;
    @FXML
    private DatePicker dtNgayBatDau;
    @FXML
    private DatePicker dtNgayKetThuc;
    @FXML
    private JFXComboBox<String> cbTrangThaiNV;
    @FXML
    private JFXTextField txtMaNhiemVu;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<bindDataComboBoxMemBer> listTest = FXCollections.observableArrayList();
        NguoidungServicesImpl userS = new NguoidungServicesImpl();
        System.out.println(Transdata.Instance().getProjectID());
        List<Nguoidung> listUser = userS.getAllByProject(Transdata.Instance().getProjectID());
        for (Nguoidung nguoidung : listUser) {
            System.out.println(nguoidung.getHoTen());
            bindDataComboBoxMemBer memberCombo = new bindDataComboBoxMemBer(nguoidung.getId(), nguoidung.getHoTen());
            cbThanhVien.getItems().add(memberCombo);
        }
   
    }

    @FXML
    private void btnThemNhiemVu(MouseEvent event) {
        NoidungServiceImpl nd = new NoidungServiceImpl();
        System.out.println(cbThanhVien.getValue().userName);
    }

    public class bindDataComboBoxMemBer {

        public bindDataComboBoxMemBer(int id, String name) {
            this.userID = id;
            this.userName = name;
        }

        public int userID;
        public String userName;
        @Override
        public String toString(){
            return this.userName;
        }
    }

    

}
