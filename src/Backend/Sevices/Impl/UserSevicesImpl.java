/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices.Impl;

import Foundation.LibraryMD5;
import Backend.Controller.NguoidungDuanJpaController;
import Backend.Sevices.UserSevices;
import Backend.Controller.NguoidungJpaController;
import Backend.Model.Nguoidung;
import Foundation.AlertMess;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.util.List;

/**
 *
 * @author vukho
 */
public class UserSevicesImpl implements UserSevices {

    NguoidungJpaController UserJpa = new NguoidungJpaController();
    LibraryMD5 md5 = new LibraryMD5();

    @Override
    public boolean Login(String Email, String Pass) {
        try {
            List<Nguoidung> users = UserJpa.findNguoidungByEmail(Email);
            if (users.size() == 1) {
                for (Nguoidung user : users) {
                    String PassHash = md5.getMd5(Pass);
                    if (user.getMatKhau().equals(PassHash)) {
//                        xet trang thai dang nhap
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        AlertMess.Instance().ShowMessError("Email or Password is not correct");
        return false;
    }

    @Override
    public boolean Register(String Email, String Pass, String UserName, String Phone, String Address) {
        //To change body of generated methods, choose Tools | Templates.
        try {
            String PassHash = md5.getMd5(Pass);
            Nguoidung nguoidung = new Nguoidung(Email, PassHash, UserName, Phone, Address, false);
            UserJpa.create(nguoidung);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
