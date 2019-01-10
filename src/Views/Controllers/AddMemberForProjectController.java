/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import Backend.Model.Nguoidung;
import com.jfoenix.controls.JFXTextField;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class AddMemberForProjectController implements Initializable {

    @FXML
    private TableView<Nguoidung> tblAllMember;
    @FXML
    private TableColumn<Nguoidung, String> colNameMember;
    @FXML
    private TableView<?> tblProjectView;
    @FXML
    private TableColumn<?, ?> colNameProjectMember;
    @FXML
    private JFXTextField txtTimKiem;

    private Nguoidung nguoidung;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     colNameMember.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
       ObservableList<Nguoidung> list = getUserList();
      tblAllMember.setItems(list);
 
      tblAllMember.getColumns().add(colNameMember);
    }    

     private ObservableList<Nguoidung> getUserList() {
 
      Nguoidung user1 = new Nguoidung( "1","asdasd", "asdasd@gmail.com", 
              "", "", true);
      Nguoidung user2 = new Nguoidung("2", "mcneil", "mcneil@gmail.com", 
              "", "", true);
      Nguoidung user3 = new Nguoidung("3", "asdasdas", "asdasdasdasd@gmail.com", 
              "", "", false);
 
     ObservableList<Nguoidung> list = FXCollections.observableArrayList(user1, user2, user3);
      return list;
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
