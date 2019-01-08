/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices.Impl;

import Backend.Controller.DuanJpaController;
import Backend.Model.Duan;
import Backend.Sevices.DuanServices;
import java.util.List;

/**
 *
 * @author Quang
 */
public class DuanServicesImpl implements DuanServices{
    DuanJpaController duAnController = new DuanJpaController();
    @Override
    public List<Duan> getAllByStatus(int idNguoiDung, String trangThaiDuAn) {
        return duAnController.getAllByStatus(idNguoiDung, trangThaiDuAn);
    }
    
}
