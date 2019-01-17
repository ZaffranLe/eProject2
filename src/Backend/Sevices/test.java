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
import Backend.Sevices.Impl.NoidungServiceImpl;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vukho
 */
public class test {

    public static NguoidungJpaController nd;

    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException {

//        System.out.println("coi trong db co chua!");
        NoidungServiceImpl nds = new NoidungServiceImpl();
        NguoidungServicesImpl nguoidung = new NguoidungServicesImpl();
        DuanServicesImpl duanService = new DuanServicesImpl();
        List<Noidung> listnd = nds.getAllByDuAn("1");
        List<Noidung> listnd2 = nds.getAllByDuAn("abc");
        System.out.println(listnd);
        for (Noidung noidung : listnd) {
            System.out.println(noidung.getTieuDe());
            for (Noidung noidung1 : listnd) {
                nds.edit(1, noidung1.getiDDuAn().getIDDuAn(), noidung1.getIDNoiDung(), "hohoho", "hohoho", TRANGTHAITASK.CANLAM.toString(), new Date(), new Date());
                List<Nguoidung> listnguoidung = nguoidung.getAllByTask(noidung1.getIDNoiDung());
                duanService.addUser(2, "7", listnguoidung);
            }
        }

//          NguoidungDuanJpaController da = new NguoidungDuanJpaController();
//        try {
//            da.create(new NguoidungDuan(new NguoidungDuanPK(2, "7"), VITRI.NHANVIEN.toString()));
//        } catch (Exception ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
