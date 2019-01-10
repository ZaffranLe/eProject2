/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import javafx.scene.control.Alert;

/**
 *
 * @author vukho
 */
public class AlertMess {

    private String TittleError;
    private String TittleSuccess;
    public static AlertMess instance;

    public static AlertMess Instance() {
        if (instance == null) {
            instance = new AlertMess();
        }
        return instance;
    }

    public String getTittleError() {
        return TittleError;
    }

    public String getTittleSuccess() {
        return TittleSuccess;
    }

    public void setTittleSuccess(String TittleSuccess) {
        this.TittleSuccess = "Success";
    }

    public void setTittleError(String TittleError) {
        this.TittleError = "Error";
    }


    public void ShowMessError(String mess) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(TittleError);
        alert.setHeaderText(null);
        alert.setContentText(mess);
        alert.showAndWait();
    }

    public void ShowMessSuccess(String mess) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(TittleSuccess);
        alert.setContentText(mess);
        alert.showAndWait();
    }
}
