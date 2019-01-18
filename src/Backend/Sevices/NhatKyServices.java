/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;
import java.util.List;
import java.util.Date;
import Backend.Model.Nhatky;

/**
 *
 * @author Quang
 */
public interface NhatKyServices {
    public void create(String idDuAn,String noidung,Date thoigian);
    public List<Nhatky> getByDuAn(String idDuAn);
}
