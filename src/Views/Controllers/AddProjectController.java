/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Controller.DuanJpaController;
import Backend.Controller.NguoidungJpaController;
import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.VITRI;
import Backend.Model.Duan;
import Backend.Model.Nguoidung;
import Backend.Sevices.Impl.DuanServicesImpl;
import Backend.Sevices.Impl.NguoidungServicesImpl;
import Foundation.Transdata;
import Foundation.AlertMess;
import Foundation.DateTimeParse;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.DateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class AddProjectController implements Initializable {

    @FXML
    private Label IDuser;

    @FXML
    private Label lbTitle;

    @FXML
    private JFXTextField txtMaDuAn;

    @FXML
    private JFXTextField txtTenProject;

    @FXML
    private DatePicker dtNgayBatDau;
    @FXML
    private CheckComboBox<bindDataComboBoxMemBer> ckcbThanhVienProject;
    @FXML
    private JFXButton Edit;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Create;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (Transdata.Instance().isIsEdit()) {
            loadFormEdit();
        } else {
            loadForm();
        }
    }

    public void loadForm() {
        lbTitle.setText("Create Project");
        Edit.setVisible(false);
        Save.setVisible(false);
        final ObservableList<bindDataComboBoxMemBer> cbMember = FXCollections.observableArrayList();
        NguoidungServicesImpl userS = new NguoidungServicesImpl();
        List<Nguoidung> lstUser = userS.getAll();
        for (Nguoidung nguoidung : lstUser) {
            if (nguoidung.getId() != Integer.parseInt(Transdata.Instance().getUserLoginID())) {
                cbMember.add(new bindDataComboBoxMemBer(nguoidung.getId(), nguoidung.getHoTen()));
            }
        }
        ckcbThanhVienProject.getItems().addAll(cbMember);
    }

    public void loadFormEdit() {
        DuanJpaController daJPA = new DuanJpaController();
        Duan d = daJPA.findDuan(Transdata.Instance().getProjectID());
        txtMaDuAn.setText(d.getIDDuAn());
        txtTenProject.setText(d.getTenDuAn());
        dtNgayBatDau.setValue(DateTimeParse.Instance().convert(d.getNgayBatDau()));
        txtMaDuAn.setEditable(false);
        txtTenProject.setEditable(false);
        dtNgayBatDau.setEditable(false);
        lbTitle.setText("Detail Project");
        Edit.setVisible(true);
        Create.setVisible(false);
        Save.setVisible(false);
        final ObservableList<bindDataComboBoxMemBer> cbMember = FXCollections.observableArrayList();
        NguoidungServicesImpl userS = new NguoidungServicesImpl();
        List<Nguoidung> lstUser = userS.getAllAvailable(Transdata.Instance().getProjectID());
        for (Nguoidung nguoidung : lstUser) {
            cbMember.add(new bindDataComboBoxMemBer(nguoidung.getId(), nguoidung.getHoTen()));
        }
        ckcbThanhVienProject.getItems().addAll(cbMember);
    }

    @FXML
    private void btnThemProject(MouseEvent event) {
        if (txtMaDuAn.getText().isEmpty()) {
            AlertMess.Instance().ShowMessError("Project's Id can not be empty");
            return;

        }
        if (txtTenProject.getText().isEmpty()) {
            AlertMess.Instance().ShowMessError("Project's name can not be empty");
            return;
        }
        if (dtNgayBatDau.getValue() == null) {
            AlertMess.Instance().ShowMessError("Start date can not be empty");
            return;
        }
        try {
            NguoidungJpaController userJPA = new NguoidungJpaController();
            DuanServicesImpl DA = new DuanServicesImpl();
            Date dt = DateTimeParse.Instance().convert(dtNgayBatDau.getValue());
            DA.create(Integer.parseInt(IDuser.getText()), txtMaDuAn.getText(), txtTenProject.getText(), dt, TRANGTHAIDUAN.DANGLAM.toString());
            List<bindDataComboBoxMemBer> lstBindMember = ckcbThanhVienProject.getCheckModel().getCheckedItems();
            List<Nguoidung> lstUserFind = new ArrayList<>();
            for (bindDataComboBoxMemBer dataComboBoxMemBer : lstBindMember) {
                lstUserFind.add(userJPA.findNguoidung(dataComboBoxMemBer.userID));
            }
            DA.addUser(Integer.parseInt(Transdata.Instance().getUserLoginID()), txtMaDuAn.getText(), lstUserFind);
            Stage stage = (Stage) Save.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnSaveProject(MouseEvent event) {
        if (txtMaDuAn.getText().isEmpty()) {
            AlertMess.Instance().ShowMessError("Project's Id can not be empty");
            return;

        }
        if (txtTenProject.getText().isEmpty()) {
            AlertMess.Instance().ShowMessError("Project's name can not be empty");
            return;
        }
        if (dtNgayBatDau.getValue() == null) {
            AlertMess.Instance().ShowMessError("Start date can not be empty");
            return;
        }
        try {
            NguoidungJpaController userJPA = new NguoidungJpaController();
            DuanServicesImpl DA = new DuanServicesImpl();
            Date dt = DateTimeParse.Instance().convert(dtNgayBatDau.getValue());
            DA.edit(Integer.parseInt(IDuser.getText()), txtMaDuAn.getText(), txtTenProject.getText(), dt, TRANGTHAIDUAN.DANGLAM.toString());
            List<bindDataComboBoxMemBer> lstBindMember = ckcbThanhVienProject.getCheckModel().getCheckedItems();
            List<Nguoidung> lstUserFind = new ArrayList<>();
            for (bindDataComboBoxMemBer dataComboBoxMemBer : lstBindMember) {
                lstUserFind.add(userJPA.findNguoidung(dataComboBoxMemBer.userID));
            }
            DA.addUser(Integer.parseInt(Transdata.Instance().getUserLoginID()), txtMaDuAn.getText(), lstUserFind);
            Stage stage = (Stage) Save.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnSuaProject(MouseEvent event) {
        txtMaDuAn.setEditable(false);
        txtTenProject.setEditable(true);
        dtNgayBatDau.setEditable(true);
        Edit.setVisible(false);
        Save.setVisible(true);
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

}
