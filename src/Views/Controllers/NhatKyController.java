/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import Backend.Model.Nhatky;
import Backend.Sevices.Impl.NhatKyServicesImpl;
import Foundation.Transdata;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.table.DefaultTableModel;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class NhatKyController implements Initializable {

    @FXML
    private TableView<Nhatky> tblNhatKy;
    @FXML
    private TableColumn<Nhatky, String> colThoiGian;
    @FXML
    private TableColumn<Nhatky, String> colContent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colThoiGian.setCellValueFactory(new PropertyValueFactory<Nhatky, String>("thoiGian"));
        colContent.setCellValueFactory(new PropertyValueFactory<Nhatky, String>("noiDung"));
        NhatKyServicesImpl nkS = new NhatKyServicesImpl();
        final ObservableList<Nhatky> data = FXCollections.observableArrayList();
        List<Nhatky> lstNk = nkS.getByDuAn(Transdata.Instance().getProjectID());
        for (Nhatky nhatky : lstNk) {
            data.add(nhatky);
        }
        tblNhatKy.setItems(data);

    }

}
