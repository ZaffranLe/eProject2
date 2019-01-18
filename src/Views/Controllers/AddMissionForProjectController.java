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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private CheckComboBox<String> ckcbMember;
    @FXML
    private JFXComboBox<bindDataComboBoxStatus> cbTrangThaiNV;
    @FXML
    private JFXTextField txtMaNhiemVu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // checkcombobox demo
        final ObservableList<String> strings = FXCollections.observableArrayList();
        for (int i = 0; i <= 10; i++) {
            strings.add("Item " + i);
        }
        ckcbMember.getItems().addAll(strings);
        ckcbMember.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>(){
            @Override
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                    System.out.println(ckcbMember.getCheckModel().getCheckedItems());            }
        
        });
        //
        NguoidungServicesImpl userS = new NguoidungServicesImpl();
        List<Nguoidung> listUser = userS.getAllByProject(Transdata.Instance().getProjectID());
        for (Nguoidung nguoidung : listUser) {
            cbThanhVien.getItems().add(new bindDataComboBoxMemBer(nguoidung.getId(), nguoidung.getHoTen()));
        }

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
        int userID = cbThanhVien.getValue().userID;
        LocalDate dateStartL = dtNgayBatDau.getValue();
        LocalDate dateEndL = dtNgayKetThuc.getValue();
        Date dateStart = Date.from(dateStartL.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateEnd = Date.from(dateEndL.atStartOfDay(ZoneId.systemDefault()).toInstant());
        String description = txtMoTa.getText();
        
        nd.create(userID, Transdata.Instance().getProjectID() , idTask, tittle, description, status, dateStart, dateEnd);
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
