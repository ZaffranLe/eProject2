/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices.Impl;

import Backend.Controller.DuanJpaController;
import Backend.Controller.NoidungJpaController;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Model.Noidung;
import Backend.Sevices.NoidungServices;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Quang
 */
public class NoidungServiceImpl implements NoidungServices {

    NoidungJpaController noiDungController = new NoidungJpaController();
    DuanJpaController duAnController = new DuanJpaController();

    @Override
    public List<Noidung> getAllByStatus(String idDuAn, String trangThaiTask) {
        return noiDungController.getAllByStatus(idDuAn, trangThaiTask);
    }

    @Override
    public void create(String iDDuAn, String iDNoiDung, String tieuDe, String noiDung, String trangThai, Date ngayBatDau, Date ngayKetThuc) {
        try {
            Noidung nd = new Noidung();
            nd.setIDNoiDung(iDNoiDung);
            nd.setTieuDe(tieuDe);
            nd.setNoiDung(noiDung);
            nd.setTrangThai(trangThai);
            nd.setNgayBatDau(ngayBatDau);
            nd.setNgayKetThuc(ngayKetThuc);
            nd.setIDDuAn((duAnController.findDuan(iDDuAn)));
            noiDungController.create(nd);
        } catch (Exception ex) {
            Logger.getLogger(NoidungServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(String iDDuAn,String iDNoiDung, String tieuDe, String noiDung, String trangThai, Date ngayBatDau, Date ngayKetThuc) {
        Noidung nd = noiDungController.findNoidung(iDNoiDung);
        nd.setTieuDe(tieuDe);
        nd.setNoiDung(noiDung);
        nd.setTrangThai(trangThai);
        nd.setNgayBatDau(ngayBatDau);
        nd.setNgayKetThuc(ngayKetThuc);
        nd.setIDDuAn((duAnController.findDuan(iDDuAn)));
        try {
            noiDungController.edit(nd);
        } catch (Exception ex) {
            Logger.getLogger(NoidungServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
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

}
