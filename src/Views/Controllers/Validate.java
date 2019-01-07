/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vukho
 */
public class Validate {

    private Pattern pattern;
    private Matcher matcher;

    public boolean validateEmail(String Email) {
        pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        matcher = pattern.matcher(Email);
        return matcher.matches();
    }
}
