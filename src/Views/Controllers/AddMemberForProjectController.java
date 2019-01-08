/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import com.jfoenix.controls.JFXTextField;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class AddMemberForProjectController implements Initializable {

    @FXML
    private TableView<String> tblAllMember;
    @FXML
    private TableColumn<?, ?> colNameMember;
    @FXML
    private TableView<?> tblProjectView;
    @FXML
    private TableColumn<?, ?> colNameProjectMember;
    @FXML
    private JFXTextField txtTimKiem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String>  list = new ArrayList<>();
        for(int i = 0; i <6; i++ ){
            String str = String.format("dcm khoi %d",i);
            list.add(str);
        }
    }    

    @FXML
    private void btnSearch(MouseEvent event) {
    }

    @FXML
    private void btnAddMember(MouseEvent event) {
    }

    @FXML
    private void btnRemoveMember(MouseEvent event) {
    }
    
}
