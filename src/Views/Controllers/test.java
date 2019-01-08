/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import Backend.Controller.DuanJpaController;
import Backend.Controller.NguoidungJpaController;
import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.TRANGTHAITASK;
import Backend.Model.Duan;
import Backend.Model.Noidung;
import Backend.Sevices.Impl.DuanServicesImpl;
import Backend.Sevices.Impl.NoidungServiceImpl;
import java.util.List;

/**
 *
 * @author vukho
 */
public class test {

    public static void main(String[] args) {
        System.out.println("khởi dz bướng vcc");
        DuanServicesImpl da = new DuanServicesImpl();
        NoidungServiceImpl nd = new NoidungServiceImpl();

        List<Duan> list = da.getAllByStatus(1, TRANGTHAIDUAN.DANGLAM.toString());
        for (Duan a : list) {
            System.out.println(a.getTenDuAn());

        }

        List<Noidung> listNoidung = nd.getAllByStatus("abc", TRANGTHAITASK.DANGLAM.toString());

        for (Noidung a : listNoidung) {
            System.out.println(a.getTieuDe());

        }
    }
}
