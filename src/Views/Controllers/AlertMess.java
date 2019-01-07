/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views.Controllers;

import javafx.scene.control.Alert;

/**
 *
 * @author vukho
 */
public class AlertMess {

    private String TittleError;
    private String TittleSuccess;
    private String Content;

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

    public AlertMess(String Content) {

        this.Content = Content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public void ShowMessError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(TittleError);
        alert.setHeaderText(null);
        alert.setContentText(Content);
        alert.showAndWait();
    }

    public void ShowMessSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(TittleSuccess);
        alert.setContentText(Content);
        alert.showAndWait();
    }
}
