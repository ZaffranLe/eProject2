/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

import Backend.Controller.NguoidungJpaController;
import Backend.Controller.exceptions.IllegalOrphanException;
import Backend.Controller.exceptions.NonexistentEntityException;
import Backend.Enum.TRANGTHAITASK;
import Backend.Sevices.Impl.DuanServicesImpl;
import Backend.Sevices.Impl.NguoidungServicesImpl;
import Backend.Sevices.Impl.NoidungServiceImpl;
import java.util.Date;


/**
 *
 * @author vukho
 */
public class test {
    public static NguoidungJpaController nd;
    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException {

        System.out.println("coi trong db co chua!");
        NoidungServiceImpl nds = new NoidungServiceImpl();
        nds.create(1, "1", "xxx", "test", "aa", TRANGTHAITASK.DANGLAM.toString(), new Date(), new Date());

    }
}
