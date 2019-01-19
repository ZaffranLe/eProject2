/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import Backend.Model.Nguoidung;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author vukho
 */
public class DateTimeParse {

    public static DateTimeParse instance;
    

    public static DateTimeParse Instance() {
        if (instance == null) {
            instance = new DateTimeParse();
        }
        return instance;
    }
    public LocalDate convert(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public Date convert(LocalDate lcDate){
        return Date.from(lcDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
