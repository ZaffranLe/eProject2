/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Controller.NguoidungDuanJpaController;
import Backend.Enum.VITRI;
import Backend.Model.Nguoidung;
import Backend.Model.NguoidungDuan;
import java.util.List;

/**
 *
 * @author Quang
 */
public class NguoidungDuanServices {
    NguoidungDuanJpaController nddaController = new NguoidungDuanJpaController();
    public void add(List<Nguoidung> list, String idDuAn){
        for (Nguoidung nguoidung : list) {
            NguoidungDuan ndda = new NguoidungDuan(nguoidung.getId(), idDuAn);
            ndda.setViTri(VITRI.NHANVIEN.toString());
        }
    }
   
}
