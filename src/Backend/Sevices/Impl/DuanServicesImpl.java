/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices.Impl;

import Backend.Controller.DuanJpaController;
import Backend.Controller.NguoidungDuanJpaController;
import Backend.Controller.NguoidungJpaController;
import Backend.Controller.exceptions.IllegalOrphanException;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Enum.TRANGTHAIDUAN;
import Backend.Enum.VITRI;
import Backend.Model.Duan;
import Backend.Model.Nguoidung;
import Backend.Model.NguoidungDuan;
import Backend.Model.NguoidungDuanPK;
import Backend.Sevices.DuanServices;
import Foundation.AlertMess;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang
 */
public class DuanServicesImpl implements DuanServices {

    DuanJpaController duAnController = new DuanJpaController();
    NguoidungDuanJpaController nguoiDungDuAn = new NguoidungDuanJpaController();
    NhatKyServicesImpl nhatKy = new NhatKyServicesImpl();
    NguoidungJpaController nguoiDungController = new NguoidungJpaController();

    @Override
    public List<Duan> getAllByStatus(int idNguoiDung, String trangThaiDuAn) {
        return duAnController.getAllByStatus(idNguoiDung, trangThaiDuAn);
    }

    @Override
    public void create(int idNguoidung, String id, String name, Date start, String status) {
        if (duAnController.findDuan(id) != null) {
            AlertMess.Instance().ShowMessError("Project is existed!");
            return;

        }
        try {
            Duan duan = new Duan(id, name, start, status);
            duAnController.create(duan);
            nhatKy.create(duan.getIDDuAn(), "Create project by " + nguoiDungController.findNguoidung(idNguoidung).getHoTen(), start);
            AlertMess.Instance().ShowMessSuccess("Create project success!");
        } catch (Exception ex) {
            Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            AlertMess.Instance().ShowMessError("Create project fail!");
        }
        try {
            NguoidungDuan ndda = new NguoidungDuan(new NguoidungDuanPK(idNguoidung, id), VITRI.QUANLY.toString());
            nguoiDungDuAn.create(ndda);

        } catch (Exception ex) {
            Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void edit(int idNguoidung, String id, String name, Date start, String status) {
        try {
            if (haveRole(idNguoidung, id)) {
                try {
                    Duan da = duAnController.findDuan(id);
                    da.setTenDuAn(name);
                    da.setNgayBatDau(start);
                    da.setTrangThai(status);
                    duAnController.edit(da);
                    nhatKy.create(da.getIDDuAn(), "Edit project by " + nguoiDungController.findNguoidung(idNguoidung).getHoTen(), start);
                    AlertMess.Instance().ShowMessSuccess("Edit project success!");
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
                    AlertMess.Instance().ShowMessError("Project is not existed!");
                } catch (Exception ex) {
                    Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
                    AlertMess.Instance().ShowMessError("Edit project fail!");
                }
            }
        } catch (Exception e) {
            AlertMess.Instance().ShowMessError("You might not have permission to do this function!");

        }

    }

    @Override
    public void delete(int idNguoidung, String id) {
        try {
            if (haveRole(idNguoidung, id)) {
                try {
                    duAnController.destroy(id);
                } catch (IllegalOrphanException ex) {
                    Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            System.out.println("Khong co quyen");
        }
    }

    @Override
    public void setEndDate(int idNguoidung, String id, Date end) {
        try {
            if (haveRole(idNguoidung, id)) {
                Duan da = duAnController.findDuan(id);
                da.setNgayKetThuc(end);
                try {
                    duAnController.edit(da);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {
            System.out.println("Khong co quyen");
        }
    }

    private boolean haveRole(int idNguoidung, String id) {
        if (nguoiDungDuAn.findNguoidungDuan(new NguoidungDuanPK(idNguoidung, id)).getViTri().equals(VITRI.QUANLY.toString())) {

            return true;
        }
        return false;

    }

    @Override
    public void addUser(int idNguoidung, String idDuan, List<Nguoidung> list) {
        if (haveRole(idNguoidung, idDuan)) {
            for (Nguoidung nguoidung : list) {
                try {
                    nguoiDungDuAn.create(new NguoidungDuan(new NguoidungDuanPK(nguoidung.getId(), idDuan),
                            VITRI.NHANVIEN.toString()));
                } catch (Exception ex) {
                    Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            nhatKy.create(idDuan, "Add user to project by " + nguoiDungController.findNguoidung(idNguoidung).getHoTen(), new Date());

        } else {
            AlertMess.Instance().ShowMessError("You might not have permission to add user!");

        }
    }

    @Override
    public void setStatus(int idNguoidung, String idDuAn, TRANGTHAIDUAN status) {
        if (haveRole(idNguoidung, idDuAn)) {
            try {
                Duan duan = duAnController.findDuan(idDuAn);
                duan.setTrangThai(status.toString());
                duAnController.edit(duan);
                nhatKy.create(idDuAn, "Change project status by" + nguoiDungController.findNguoidung(idNguoidung).getHoTen(), new Date());
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
