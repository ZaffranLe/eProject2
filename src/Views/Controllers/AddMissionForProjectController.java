/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Controller.NguoidungJpaController;
import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.TRANGTHAITASK;
import Backend.Model.Nguoidung;
import Backend.Model.Noidung;
import Backend.Sevices.Impl.NguoidungServicesImpl;
import Backend.Sevices.Impl.NoidungServiceImpl;
import Foundation.DateTimeParse;
import Foundation.Transdata;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
    private CheckComboBox<bindDataComboBoxMemBer> ckcbMember;
    @FXML
    private JFXComboBox<bindDataComboBoxStatus> cbTrangThaiNV;
    @FXML
    private JFXTextField txtMaNhiemVu;
    @FXML
    private Label tittleForm;
    @FXML
    private JFXButton Add;
    @FXML
    private JFXButton Save;
    @FXML
    private JFXButton Edit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // checkcombobox demo
        if (Transdata.Instance().isIsEdit()) {
            loadFormDetail();
        } else {
            loadFormCreate();
        }

    }

    public void loadFormDetail() {
        // set khong cho edit
        Save.setVisible(false);
        Add.setVisible(false);
        txtMaNhiemVu.setEditable(false);
        txtTenNhiemVu.setEditable(false);
        txtMoTa.setEditable(false);
        dtNgayBatDau.setEditable(false);
        dtNgayKetThuc.setEditable(false);

        // get du lieu
        NoidungServiceImpl ndS = new NoidungServiceImpl();
        NguoidungServicesImpl userS = new NguoidungServicesImpl();
        Noidung nd = ndS.findOne(Transdata.Instance().getTaskID());
        txtMaNhiemVu.setText(nd.getIDNoiDung());
        txtTenNhiemVu.setText(nd.getTieuDe());
        txtMoTa.setText(nd.getNoiDung());
        dtNgayBatDau.setValue(DateTimeParse.Instance().convert(nd.getNgayBatDau()));
        dtNgayKetThuc.setValue(DateTimeParse.Instance().convert(nd.getNgayKetThuc()));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus((TRANGTHAITASK.valueOf(nd.getTrangThai()))));
        cbTrangThaiNV.getSelectionModel().selectFirst();
    }

    public void loadFormCreate() {
        Edit.setVisible(false);
        Save.setVisible(false);
        final ObservableList<bindDataComboBoxMemBer> cbMember = FXCollections.observableArrayList();
        NguoidungServicesImpl userS = new NguoidungServicesImpl();
        List<Nguoidung> listUser = userS.getAllByProject(Transdata.Instance().getProjectID());
        for (Nguoidung nguoidung : listUser) {
            cbMember.add(new bindDataComboBoxMemBer(nguoidung.getId(), nguoidung.getHoTen()));
        }
        ckcbMember.getItems().addAll(cbMember);
        ckcbMember.getCheckModel().getCheckedItems().addListener(new ListChangeListener<bindDataComboBoxMemBer>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends bindDataComboBoxMemBer> c) {
                System.out.println(ckcbMember.getCheckModel().getCheckedItems());
            }

        });

        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.CANLAM));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.DANGLAM));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.CHODUYET));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.HOANTHANH));
    }

    @FXML
    private void btnThemNhiemVu(MouseEvent event) {
        NoidungServiceImpl nd = new NoidungServiceImpl();
        NguoidungJpaController userJPA = new NguoidungJpaController();
        String idTask = txtMaNhiemVu.getText();
        String tittle = txtTenNhiemVu.getText();
        String status = cbTrangThaiNV.getValue().getStatus().toString();
        int userID = Integer.parseInt(Transdata.Instance().getUserLoginID());
        Date dateStart = DateTimeParse.Instance().convert(dtNgayBatDau.getValue());
        Date dateEnd = DateTimeParse.Instance().convert(dtNgayKetThuc.getValue());
        String description = txtMoTa.getText();
        nd.create(userID, Transdata.Instance().getProjectID(), idTask, tittle, description, status, dateStart, dateEnd);
        List<bindDataComboBoxMemBer> lstBindMember = ckcbMember.getCheckModel().getCheckedItems();
        List<Nguoidung> lstUserFind = new ArrayList<>();
        for (bindDataComboBoxMemBer dataComboBoxMemBer : lstBindMember) {
            lstUserFind.add(userJPA.findNguoidung(dataComboBoxMemBer.userID));
        }
        nd.AddUsers(userID, idTask, lstUserFind);
        txtMaNhiemVu.setText("");
        txtMoTa.setText("");
        txtTenNhiemVu.setText("");
    }

    @FXML
    private void btnSave(MouseEvent event) {
        NoidungServiceImpl nd = new NoidungServiceImpl();
        NguoidungJpaController userJPA = new NguoidungJpaController();
        String idTask = txtMaNhiemVu.getText();
        String tittle = txtTenNhiemVu.getText();
        String status = cbTrangThaiNV.getValue().getStatus().toString();
        int userID = Integer.parseInt(Transdata.Instance().getUserLoginID());
        Date dateStart = DateTimeParse.Instance().convert(dtNgayBatDau.getValue());
        Date dateEnd = DateTimeParse.Instance().convert(dtNgayKetThuc.getValue());
        String description = txtMoTa.getText();
        nd.edit(userID, Transdata.Instance().getProjectID(), idTask, tittle, description, status, dateStart, dateEnd);
        List<bindDataComboBoxMemBer> lstBindMember = ckcbMember.getCheckModel().getCheckedItems();
        List<Nguoidung> lstUserFind = new ArrayList<>();
        for (bindDataComboBoxMemBer dataComboBoxMemBer : lstBindMember) {
            lstUserFind.add(userJPA.findNguoidung(dataComboBoxMemBer.userID));
        }
        nd.AddUsers(userID, idTask, lstUserFind);
    }

    @FXML
    private void btnSuaNhiemVu(MouseEvent event) {
        Save.setVisible(true);
        Edit.setVisible(false);
        txtMaNhiemVu.setEditable(true);
        txtTenNhiemVu.setEditable(true);
        txtMoTa.setEditable(true);
        dtNgayBatDau.setEditable(true);
        dtNgayKetThuc.setEditable(true);

        final ObservableList<bindDataComboBoxMemBer> cbMember = FXCollections.observableArrayList();
        NoidungServiceImpl ndS = new NoidungServiceImpl();
        NguoidungServicesImpl userS = new NguoidungServicesImpl();
        List<Nguoidung> lstUser = userS.getAllAvailableInProject(Transdata.Instance().getProjectID(), Transdata.Instance().getTaskID());
        for (Nguoidung nguoidung : lstUser) {
            cbMember.add(new bindDataComboBoxMemBer(nguoidung.getId(), nguoidung.getHoTen()));
        }
        ckcbMember.getItems().addAll(cbMember);
        cbTrangThaiNV.getItems().clear();
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.CANLAM));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.DANGLAM));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.CHODUYET));
        cbTrangThaiNV.getItems().add(new bindDataComboBoxStatus(TRANGTHAITASK.HOANTHANH));
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

        public bindDataComboBoxStatus(TRANGTHAITASK statusEnum) {
            setStatus(statusEnum);
            if (statusEnum.equals(TRANGTHAITASK.CANLAM)) {
                this.Name = "To do";
            }
            if (statusEnum.equals(TRANGTHAITASK.DANGLAM)) {
                this.Name = "Inprogress";
            }
            if (statusEnum.equals(TRANGTHAITASK.CHODUYET)) {
                this.Name = "Solved";
            }
            if (statusEnum.equals(TRANGTHAITASK.HOANTHANH)) {
                this.Name = "Complete";
            }

        }
        public TRANGTHAITASK Status;

        public TRANGTHAITASK getStatus() {
            return Status;
        }

        public void setStatus(TRANGTHAITASK Status) {
            this.Status = Status;
        }
        public String Name;

        @Override
        public String toString() {
            return this.Name;
        }
    }

}
