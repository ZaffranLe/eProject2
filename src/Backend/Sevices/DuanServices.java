/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Enum.TRANGTHAIDUAN;
import Backend.Model.Duan;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Quang
 */
public interface DuanServices {
    public void create(String id,String name,Date start,String status);
    public void edit(String id,String name,Date start,String status);
    public void delete(String id);
    public void setEndDate(String id,Date end);
    
    public List<Duan> getAllByStatus(int idNguoiDung, String trangThaiDuAn);
    
}
