/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Sevices;

/**
 *
 * @author vukho
 */
public interface UserSevices {
    public boolean Login(String Email, String Pass);
    public boolean Register(String Email, String Pass, String UserName, String Phone,String Address);
}
