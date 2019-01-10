/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices.Impl;

import Backend.Controller.NoidungJpaController;
import Backend.Model.Noidung;
import Backend.Sevices.NoidungServices;
import java.util.List;

/**
 *
 * @author Quang
 */
public class NoidungServiceImpl implements NoidungServices{
    NoidungJpaController noiDungController = new NoidungJpaController();
    @Override
    public List<Noidung> getAllByStatus(String idDuAn,String trangThaiTask) {
        return noiDungController.getAllByStatus(idDuAn, trangThaiTask);
    }
    
}
