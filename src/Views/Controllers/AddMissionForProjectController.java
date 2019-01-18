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
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
public class AddMissionForProjectController implements Initializable {

    @FXML
    private JFXComboBox<?> cbThanhVien;
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
    private JFXComboBox<?> cbTrangThaiNV;
    @FXML
    private JFXTextField txtMaNhiemVu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }

    @FXML
    private void btnThemNhiemVu(MouseEvent event) {
    }


}
