/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Enum.TRANGTHAIDUAN;
import Backend.Model.Duan;
import Backend.Model.Nguoidung;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Quang
 */
public interface DuanServices {
    public void create(int idNguoidung,String id,String name,Date start,String status);
    public void edit(int idNguoidung,String id,String name,Date start,String status);
    public void delete(int idNguoidung,String id);
    public void setEndDate(int idNguoidung,String id,Date end);
    public void addUser(int idNguoidung,String idDuan,List<Nguoidung> list);
    
    public List<Duan> getAllByStatus(int idNguoiDung, String trangThaiDuAn);
    
}
