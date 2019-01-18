/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Controller.NguoidungDuanJpaController;
import Backend.Controller.NguoidungJpaController;
import Backend.Controller.NoidungJpaController;
import Backend.Controller.exceptions.IllegalOrphanException;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.TRANGTHAITASK;
import Backend.Enum.VITRI;
import Backend.Model.Nguoidung;
import Backend.Model.Noidung;
import Backend.Sevices.Impl.DuanServicesImpl;
import Backend.Sevices.Impl.NguoidungServicesImpl;
import Backend.Sevices.Impl.NhatKyServicesImpl;
import Backend.Sevices.Impl.NoidungServiceImpl;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vukho
 */
public class test {

    public static NguoidungJpaController nd;

    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException {

        // System.out.println("coi trong db co chua!");
        NoidungServiceImpl nds = new NoidungServiceImpl();
        NguoidungServicesImpl nguoidung = new NguoidungServicesImpl();
        DuanServicesImpl duanService = new DuanServicesImpl();
        List<Noidung> listnd = nds.getAllByDuAn("1");
        List<Nguoidung> listnguoidung1 = nguoidung.getAllByProject("1");
        NoidungJpaController ndjpa = new NoidungJpaController();
        Collection<Nguoidung> aaa = ndjpa.findNoidung("1").getNguoidungCollection();
        System.out.println("noi dung" + aaa);


//          NguoidungDuanJpaController da = new NguoidungDuanJpaController();
//        try {
//            da.create(new NguoidungDuan(new NguoidungDuanPK(2, "7"), VITRI.NHANVIEN.toString()));
//        } catch (Exception ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
        NhatKyServicesImpl nhatky = new NhatKyServicesImpl();
        nhatky.create("1", "hahaha", new Date());
        System.out.println("test"+nguoidung.getAllAvailableInProject("1", "1"));

    }
}
