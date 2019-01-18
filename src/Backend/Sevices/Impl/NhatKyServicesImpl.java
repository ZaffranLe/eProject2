/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices.Impl;

import Backend.Controller.DuanJpaController;
import Backend.Controller.NhatkyJpaController;
import Backend.Model.Nhatky;
import Backend.Sevices.NhatKyServices;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Quang
 */
public class NhatKyServicesImpl implements NhatKyServices {

    NhatkyJpaController nhatKyController = new NhatkyJpaController();
    DuanJpaController duAnController = new DuanJpaController();

    @Override
    public void create(String idDuAn, String noidung, Date thoigian) {
        Nhatky nhatky = new Nhatky();
        nhatky.setIDDuAn(duAnController.findDuan(idDuAn));
        nhatky.setNoiDung(noidung);
        nhatky.setThoiGian(thoigian);
        nhatKyController.create(nhatky);
    }

    @Override
    public List<Nhatky> getByDuAn(String idDuAn) {
        return nhatKyController.getAllByProject(idDuAn);
    }

}
