/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import controller.NguoidungJpaController;
import entity.Nguoidung;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Quang
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaApplication1PU");
        NguoidungJpaController ndc = new NguoidungJpaController(emf);
        Nguoidung nd = new Nguoidung();
        nd.setHoTen("Nguyễn Tuấn Quang");
        nd.setDiaChi("a");
        nd.setEmail("abc@gmail.com");
        nd.setMatKhau("aa");
        nd.setSdt("1111");

        Nguoidung nd1 = ndc.findNguoidung(7);
        System.out.println(nd1.getHoTen());
        
    }
    
}
