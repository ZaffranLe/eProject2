/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Controller.NguoidungJpaController;
import Backend.Model.Nguoidung;
import java.util.List;

/**
 *
 * @author vukho
 */
public class NguoiDungSevices {

    private static NguoidungJpaController user;

    public static boolean login(String email, String pass) {
        user = new NguoidungJpaController();
        try {
            List<Nguoidung> listUserResult = user.findNguoidungByEmail(email);
            if (listUserResult.size() == 1) {
                for (Nguoidung nguoidung : listUserResult) {
                    if (nguoidung.getEmail().equals(pass)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
    public static boolean Register(String email, String pass) {
        user = new NguoidungJpaController();
        try {
            List<Nguoidung> listUserResult = user.findNguoidungByEmail(email);
            if (listUserResult.size() == 1) {
                for (Nguoidung nguoidung : listUserResult) {
                    if (nguoidung.getEmail().equals(pass)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
    

    public static void main(String[] args) {
        if (login("kkk", "kkk")) {
            System.out.println("qua hop li");
        } else {
            System.out.println("sai passs roi");
        }
    }
}
