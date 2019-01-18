/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices.Impl;

import Backend.Controller.NguoidungJpaController;
import Backend.Model.Nguoidung;
import Backend.Sevices.NguoidungServices;
import java.util.List;

/**
 *
 * @author Quang
 */
public class NguoidungServicesImpl implements NguoidungServices{
    NguoidungJpaController nguoidungController = new NguoidungJpaController();
    @Override
    public List<Nguoidung> getAllByProject(String idDuAn) {
        return nguoidungController.getAllByProject(idDuAn);
    }

    @Override
    public List<Nguoidung> getAllByTask(String idTask) {
        return nguoidungController.getAllByTask(idTask);
    }

    @Override
    public List<Nguoidung> findByEmail(String Email) {
        return nguoidungController.findByEmail(Email);
    }

    @Override
    public List<Nguoidung> findByEmailAndProject(String idProject, String Email) {
        return nguoidungController.findByEmailAndDuAn(idProject, Email);
    }
    
}
