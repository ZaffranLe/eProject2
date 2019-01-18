/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices.Impl;

import Backend.Controller.DuanJpaController;
import Backend.Controller.NguoidungDuanJpaController;
import Backend.Controller.NguoidungJpaController;
import Backend.Controller.NoidungJpaController;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.VITRI;
import Backend.Model.Nguoidung;
import Backend.Model.NguoidungDuanPK;
import Backend.Model.Noidung;
import Backend.Sevices.NoidungServices;
import Foundation.AlertMess;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Quang
 */
public class NoidungServiceImpl implements NoidungServices {

    NoidungJpaController noiDungController = new NoidungJpaController();
    DuanJpaController duAnController = new DuanJpaController();
    NguoidungJpaController nguoidungController = new NguoidungJpaController();
    NguoidungDuanJpaController nguoiDungDuAn = new NguoidungDuanJpaController();
    NguoidungServicesImpl nguoidungServiceImpl = new NguoidungServicesImpl();
    NhatKyServicesImpl nhatKyServiceImpl = new NhatKyServicesImpl();

    @Override
    public List<Noidung> getAllByDuAn(String idDuAn) {
        return noiDungController.getAllByProject(idDuAn);
    }

    @Override
    public void create(int idNguoitao, String iDDuAn, String iDNoiDung, String tieuDe, String noiDung, String trangThai, Date ngayBatDau, Date ngayKetThuc) {
        if (noiDungController.findNoidung(iDNoiDung) != null) {
            AlertMess.Instance().ShowMessError("Task is existed!");
            return;
        }
        try {

            Nguoidung nguoitao = nguoidungController.findNguoidung(idNguoitao);

            Noidung nd = new Noidung();
            nd.setIDNoiDung(iDNoiDung);
            nd.setTieuDe(tieuDe);
            nd.setNoiDung(noiDung);
            nd.setTrangThai(trangThai);
            nd.setNgayBatDau(ngayBatDau);
            nd.setNgayKetThuc(ngayKetThuc);
            nd.setIDDuAn((duAnController.findDuan(iDDuAn)));

            noiDungController.create(nd);
            AlertMess.Instance().ShowMessSuccess("Create task success!");

            nhatKyServiceImpl.create(iDDuAn, "Create task" + iDNoiDung + " by " + nguoitao.getHoTen(), new Date());
        } catch (Exception ex) {
            Logger.getLogger(NoidungServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(int idNguoidung, String iDDuAn, String iDNoiDung, String tieuDe, String noiDung, String trangThai, Date ngayBatDau, Date ngayKetThuc) {
        try {
            if (haveRole(idNguoidung, iDNoiDung)) {
                Noidung nd = noiDungController.findNoidung(iDNoiDung);
                nd.setTieuDe(tieuDe);
                nd.setNoiDung(noiDung);
                nd.setTrangThai(trangThai);
                nd.setNgayBatDau(ngayBatDau);
                nd.setNgayKetThuc(ngayKetThuc);
                nd.setIDDuAn((duAnController.findDuan(iDDuAn)));
                nhatKyServiceImpl.create(iDDuAn, "Edit task" + iDNoiDung + " by " + nguoidungController.findNguoidung(idNguoidung).getHoTen(), ngayBatDau);
                try {
                    noiDungController.edit(nd);
                    AlertMess.Instance().ShowMessSuccess("Edit task success!");

                } catch (Exception ex) {
                    Logger.getLogger(NoidungServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            AlertMess.Instance().ShowMessError("You might not have permission to do this function!");

        }

    }

    @Override
    public void delete(String iDNoiDung) {
        try {
            noiDungController.destroy(iDNoiDung);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(NoidungServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean haveRole(int idNguoidung, String idTask) {
        Noidung task = noiDungController.findNoidung(idTask);
        System.out.println(task.getIDDuAn().getIDDuAn());
        if (nguoiDungDuAn.findNguoidungDuan(new NguoidungDuanPK(idNguoidung, task.getIDDuAn().getIDDuAn())).getViTri().equals(VITRI.QUANLY.toString())) {
            return true;
        } else {
            System.out.println("Khong co quyen");
        }
        System.out.println(nguoiDungDuAn.findNguoidungDuan(new NguoidungDuanPK(idNguoidung, task.getIDDuAn().getIDDuAn())).getViTri());
        System.out.println(VITRI.QUANLY.toString());
        return false;
    }

    @Override
    public void AddUsers(int idNguoidung, String idNoidung, List<Nguoidung> list) {
        try {
            Noidung noidung = noiDungController.findNoidung(idNoidung);
            for (Nguoidung nguoidung1 : list) {
                if (!(noidung.getNguoidungCollection().contains(nguoidung1))) {
                    noidung.getNguoidungCollection().add(nguoidung1);
                }

            }
            noiDungController.edit(noidung);
            nhatKyServiceImpl.create(noidung.getIDDuAn().getIDDuAn(), "Add user to task " + idNoidung + " by " + nguoidungController.findNguoidung(idNguoidung).getHoTen(), new Date());
        } catch (Exception ex) {
            Logger.getLogger(NoidungServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public Noidung findOne(String idNoiDung) {
        return noiDungController.findNoidung(idNoiDung);
    }
}
