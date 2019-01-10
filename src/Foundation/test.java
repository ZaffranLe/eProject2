/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import Backend.Model.Nguoidung;

/**
 *
 * @author vukho
 */
public class test {
    public static void main(String[] args) {
        cachingSevices s = new cachingSevices();
        Nguoidung user = new Nguoidung("khoidz@gmail.com", "hihihih", "vccc", "0123456789", "hahahaahahha", true);
        s.cacheUerLogin(user);
        System.out.println(s.getUerLogin().getEmail());
    }
}
