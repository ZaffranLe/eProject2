/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Model.Nguoidung;
import java.util.List;

/**
 *
 * @author Quang
 */
public interface NguoidungServices {
    public List<Nguoidung> getAllByProject(String idDuAn);
    public List<Nguoidung> getAllByTask(String idTask);
}
