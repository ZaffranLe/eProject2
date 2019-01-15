/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import Foundation.AlertMess;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vukho
 */
public class Validate {

    private Pattern pattern;
    private Matcher matcher;
    public static Validate instance;

    public static Validate Instance() {
        if (instance == null) {
            instance = new Validate();
        }
        return instance;
    }

    public boolean validateEmail(String Email) {
        if (Email.isEmpty()) {
            AlertMess.Instance().ShowMessError("Email is empty!");
            return false;
        }
        if (Email.length() > 50) {
            AlertMess.Instance().ShowMessError("Email can't be longer than 50 characters!");
            return false;
        }
        if (!Email.isEmpty()) {
            pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            matcher = pattern.matcher(Email);
            if (!matcher.matches()) {
                AlertMess.Instance().ShowMessError("Email is not valid");
                return false;
            }
        }
        return true;
    }

    public boolean validatePassword(String Pass) {
        if (Pass.isEmpty()) {
            AlertMess.Instance().ShowMessError("Password is empty!");
            return false;
        }
        return true;
    }

    public boolean validateUserName(String UseName) {
        if (UseName.isEmpty()) {
            AlertMess.Instance().ShowMessError("username is empty!");
            return false;
        }
        return true;
    }
    public boolean validatePhoneNumber(String Phone) {
        if (Phone.isEmpty()) {
            AlertMess.Instance().ShowMessError("Phone number is empty!");
            return false;
        }
        if (Phone.length() != 10) {
            AlertMess.Instance().ShowMessError("Phone number must be 10 characters long");
            return false;
        }
         if (!Phone.isEmpty()) {
            pattern = Pattern.compile("\\d{10}");
            matcher = pattern.matcher(Phone);
            if (!matcher.matches()) {
                AlertMess.Instance().ShowMessError("Phone is not valid");
                return false;
            }
        }
        return true;
    }
    public boolean validateAddress(String address) {
        if (address.isEmpty()) {
            AlertMess.Instance().ShowMessError("Address number is empty!");
            return false;
        }
        return true;
    }
}
