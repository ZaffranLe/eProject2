/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.TRANGTHAITASK;
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
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Control;
import org.controlsfx.control.CheckComboBox;

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
    private JFXComboBox<bindDataComboBoxStatus> cbTrangThaiNV;
    @FXML
    private JFXTextField txtMaNhiemVu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NguoidungServicesImpl userS = new NguoidungServicesImpl();
        System.out.println(Transdata.Instance().getProjectID());
        final ObservableList<bindDataComboBoxMemBer> data = FXCollections.observableArrayList();
        List<Nguoidung> listUser = userS.getAllByProject(Transdata.Instance().getProjectID());
        for (Nguoidung nguoidung : listUser) {
            System.out.println(nguoidung.getHoTen());
            bindDataComboBoxMemBer memberCombo = new bindDataComboBoxMemBer(nguoidung.getId(), nguoidung.getHoTen());
            data.add(memberCombo);
        }
        final CheckComboBox<bindDataComboBoxMemBer> check = new CheckComboBox<bindDataComboBoxMemBer>(data);
        
        
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.CANLAM, "To Do"));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.DANGLAM, "Inprogress"));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.CHODUYET, "Solved"));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.HOANTHANH, "Complete"));
    }

    @FXML
    private void btnThemNhiemVu(MouseEvent event) {
        NoidungServiceImpl nd = new NoidungServiceImpl();
        String idTask = txtMaNhiemVu.getText();
        String tittle = txtTenNhiemVu.getText();
        String status = cbTrangThaiNV.getValue().Status.toString();

//        nd.create(0, iDDuAn, iDNoiDung, tieuDe, noiDung, trangThai, ngayBatDau, ngayKetThuc);

    }

    public class bindDataComboBoxMemBer {

        public bindDataComboBoxMemBer(int id, String name) {
            this.userID = id;
            this.userName = name;
        }

        public int userID;
        public String userName;

        @Override
        public String toString() {
            return this.userName;
        }
    }

    public class bindDataComboBoxStatus {

        public bindDataComboBoxStatus(TRANGTHAITASK statusEnum, String name) {
            this.Status = statusEnum;
            this.Name = name;
        }
        public TRANGTHAITASK Status;
        public String Name;

        @Override
        public String toString() {
            return this.Name;
        }
    }

}
