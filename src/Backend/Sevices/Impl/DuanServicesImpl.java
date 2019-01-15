/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices.Impl;

import Backend.Controller.DuanJpaController;
import Backend.Controller.NguoidungDuanJpaController;
import Backend.Controller.exceptions.IllegalOrphanException;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Enum.VITRI;
import Backend.Model.Duan;
import Backend.Model.NguoidungDuan;
import Backend.Model.NguoidungDuanPK;
import Backend.Sevices.DuanServices;
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

    @Override
    public List<Duan> getAllByStatus(int idNguoiDung, String trangThaiDuAn) {
        return duAnController.getAllByStatus(idNguoiDung, trangThaiDuAn);
    }

    @Override
    public void create(int idNguoidung, String id, String name, Date start, String status) {

        try {
            duAnController.create(new Duan(id, name, start, status));

        } catch (Exception ex) {
            Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            NguoidungDuan ndda = new NguoidungDuan(new NguoidungDuanPK(idNguoidung, id), VITRI.QUANLY.toString());
            nguoiDungDuAn.create(ndda);
        } catch (Exception ex) {
            Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(String id, String name, Date start, String status) {
        try {
            Duan da = duAnController.findDuan(id);
            da.setTenDuAn(name);
            da.setNgayBatDau(start);
            da.setTrangThai(status);
            duAnController.edit(da);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(String id) {
        try {
            duAnController.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(DuanServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setEndDate(String id, Date end) {
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

}
