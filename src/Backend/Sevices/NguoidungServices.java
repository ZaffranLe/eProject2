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

    public List<Nguoidung> getAll();

    public List<Nguoidung> getAllByProject(String idDuAn);

    public List<Nguoidung> getAllAvailableInProject(String idDuAn, String idTask);

    public List<Nguoidung> getAllByTask(String idTask);

    public List<Nguoidung> findByEmail(String Email);

    public List<Nguoidung> findByEmailAndProject(String idProject, String Email);
}
