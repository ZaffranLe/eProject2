/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import Backend.Model.Nguoidung;

import Backend.Controller.DuanJpaController;
import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.TRANGTHAITASK;
import Backend.Model.Duan;
import Backend.Model.Nguoidung;
import Backend.Model.Noidung;
import Backend.Sevices.Impl.DuanServicesImpl;
import Backend.Sevices.Impl.NguoidungServicesImpl;
import Backend.Sevices.Impl.NoidungServiceImpl;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vukho
 */
public class test {

    public static void main(String[] args) {
        System.out.println("khởi dz bướng vcc");
        DuanJpaController dajpa = new DuanJpaController();
        DuanServicesImpl da = new DuanServicesImpl();
        NoidungServiceImpl nd = new NoidungServiceImpl();
        NguoidungServicesImpl nguoidung = new NguoidungServicesImpl();
        List<Duan> list = da.getAllByStatus(1, TRANGTHAIDUAN.DANGLAM.toString());
        try {

            //da.edit("1", "test edit", new Date(1998, 02, 11), TRANGTHAIDUAN.DANGLAM.toString());
            //da.delete("4");
            //da.setEndDate("1", new Date());
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Duan a : list) {
            System.out.println(a.getTenDuAn());

        }

        //nd.create("5", "5", "a", "a", TRANGTHAITASK.DANGLAM.toString(), new Date(), new Date());
        nd.edit(1,"abc", "1", "a", "fuck", TRANGTHAITASK.DANGLAM.toString(), new Date(), new Date());


 
        List<Nguoidung> ListNguoidung = nguoidung.getAllByProject("1");

        for (Nguoidung nguoidung1 : ListNguoidung) {
            System.out.println(nguoidung1.getHoTen());
        }
        da.create(1, "du an moi", "hahaha  ", new Date(), TRANGTHAIDUAN.DANGLAM.toString());
    }
}
