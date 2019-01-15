/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Controller.NguoidungDuanJpaController;
import Backend.Controller.NguoidungJpaController;
import Backend.Controller.exceptions.IllegalOrphanException;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.TRANGTHAITASK;
import Backend.Enum.VITRI;
import Backend.Model.NguoidungDuan;
import Backend.Model.NguoidungDuanPK;
import Backend.Sevices.Impl.DuanServicesImpl;
import Backend.Sevices.Impl.NguoidungServicesImpl;
import Backend.Sevices.Impl.NoidungServiceImpl;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vukho
 */
public class test {

    public static NguoidungJpaController nd;

    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException {

//        System.out.println("coi trong db co chua!");
//        NoidungServiceImpl nds = new NoidungServiceImpl();
//        nds.create(1, "1", "xxx", "test", "aa", TRANGTHAITASK.DANGLAM.toString(), new Date(), new Date());
        DuanServicesImpl da = new DuanServicesImpl();
        da.create(1, "nh 7", "quang", new Date(), TRANGTHAIDUAN.DANGLAM.toString());
//          NguoidungDuanJpaController da = new NguoidungDuanJpaController();
//        try {
//            da.create(new NguoidungDuan(new NguoidungDuanPK(2, "7"), VITRI.NHANVIEN.toString()));
//        } catch (Exception ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
