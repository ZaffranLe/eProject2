/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Model.Noidung;
import java.util.List;

/**
 *
 * @author Quang
 */
public interface NoidungServices {
    public List<Noidung> getAllByStatus(String idDuAn, String trangThaiTask);
    
}
