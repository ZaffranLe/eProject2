/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Model.Nguoidung;
import Backend.Model.Noidung;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Quang
 */
public interface NoidungServices {

    public void create(int idNguoidung,String iDDuAn, String iDNoiDung, String tieuDe, String noiDung, String trangThai, Date ngayBatDau, Date ngayKetThuc);

    public void edit(int idNguoidung,String iDDuAn, String iDNoiDung, String tieuDe, String noiDung, String trangThai, Date ngayBatDau, Date ngayKetThuc);

    public void delete(String iDNoiDung);
    
    public void AddUsers(int idNguoidung,String idNoidung,List<Nguoidung> list);

    public List<Noidung> getAllByDuAn(String idDuAn);
    

}
