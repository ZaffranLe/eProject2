/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Controller.NguoidungJpaController;
import Backend.Controller.exceptions.IllegalOrphanException;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Model.Nguoidung;
import javax.persistence.Persistence;

/**
 *
 * @author vukho
 */
public class test {
    public static NguoidungJpaController nd;
    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException {
        nd = new NguoidungJpaController();
//        System.out.println(nd.findNguoidung(1).getEmail());
//        Nguoidung n = new Nguoidung(3, "hahaha", "hahaha", "hahaha", "hahaha", "hahaha");
//        nd.create(n);
        nd.destroy(2);
        System.out.println("coi trong db co chua!");

    }
}
