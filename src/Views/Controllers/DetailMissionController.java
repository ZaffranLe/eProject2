/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class DetailMissionController implements Initializable {

    @FXML
    private JFXComboBox<?> cbTrangThai;
    @FXML
    private JFXTextField txtTenNhiemVu;
    @FXML
    private JFXTextArea txtMoTa;
    @FXML
    private DatePicker dtNgayBatDau;
    @FXML
    private DatePicker dtNgayKetThuc;
    @FXML
    private CheckComboBox<?> ckcbThanhVien;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnSuaNhiemVu(MouseEvent event) {
    }

    @FXML
    private void btnLuuNhiemVu(MouseEvent event) {
    }
    
}
