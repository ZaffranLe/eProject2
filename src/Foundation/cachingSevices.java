/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import Backend.Model.Nguoidung;

/**
 *
 * @author vukho
 */
public class cachingSevices {
    private LibraryMD5 lbr;
    private final String userLogin = "userlogin";
    CacheToMemory<String, Nguoidung> cacheUser = new CacheToMemory(100000, 10, 100);

    public void cacheUerLogin(Nguoidung user) {
        try {
            cacheUser.cleanup();
            cacheUser.put(userLogin, user);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
    public Nguoidung getUerLogin() {
        try {
            return cacheUser.get(userLogin);
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }
    
    public void removeUserLogin(){
        try {
            cacheUser.remove(userLogin);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    
}
