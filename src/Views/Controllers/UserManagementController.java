/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Backend.Model.Nguoidung;
import Backend.Sevices.Impl.UserSevicesImpl;
import Backend.Sevices.UserSevices;
import Foundation.AlertMess;
import Foundation.Transdata;
import Views.Controllers.Validate;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author OS
 */
public class UserManagementController implements Initializable {

    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtPhone;
    @FXML
    private JFXButton btnEdit;

    @FXML
    private JFXButton btnSaveProfile;
    @FXML
    private AnchorPane btnSave;
    @FXML
    private JFXPasswordField txtConfirmNewPassword;
    @FXML
    private JFXPasswordField txtNewPassWord;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadForm();
    }

    public void loadForm() {

        Nguoidung user = Transdata.Instance().getUserLogin();
        txtEmail.setText(user.getEmail());
        txtAddress.setText(user.getDiaChi());
        txtUsername.setText(user.getHoTen());
        txtPhone.setText(user.getSdt());
        btnEdit.setVisible(true);
        btnSaveProfile.setVisible(false);
        txtEmail.setEditable(false);
        txtAddress.setEditable(false);
        txtUsername.setEditable(false);
        txtPhone.setEditable(false);
    }

    @FXML
    private void btnEditClick(MouseEvent event) {
        btnEdit.setVisible(false);
        btnSaveProfile.setVisible(true);
        txtEmail.setEditable(true);
        txtAddress.setEditable(true);
        txtUsername.setEditable(true);
        txtPhone.setEditable(true);

    }


    public boolean checkvalid(int id) {
        UserSevicesImpl userS = new UserSevicesImpl();
        if (!userS.checkEmailExistUpdate(txtEmail.getText(),id)) {
            System.out.println("false roi :");
            return false;
        }
        if (!Validate.Instance().validateEmail(txtEmail.getText())) {
            return false;
        }
        if (!Validate.Instance().validateAddress(txtAddress.getText())) {
            return false;
        }
        if (!Validate.Instance().validateUserName(txtUsername.getText())) {
            return false;
        }
        if (!Validate.Instance().validatePhoneNumber(txtPhone.getText())) {
            return false;
        }
        return true;
    }

    @FXML
    private void btnSaveClick(MouseEvent event) {
            UserSevicesImpl userS = new UserSevicesImpl();
            Nguoidung user = Transdata.Instance().getUserLogin();
        if (checkvalid(user.getId())) {
            user.setEmail(txtEmail.getText());
            user.setHoTen(txtUsername.getText());
            user.setDiaChi(txtAddress.getText());
            user.setSdt(txtPhone.getText());
            if (userS.Edit(user)) {
                AlertMess.Instance().ShowMessSuccess("Edit profile succeed.");
                btnSaveProfile.setVisible(false);
                btnEdit.setVisible(true);
                loadForm();
            } else {
                AlertMess.Instance().ShowMessError("Edit profile failed.");
            }
        }
    }

}
