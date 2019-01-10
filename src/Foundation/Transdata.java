/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

/**
 *
 * @author vukho
 */
public class Transdata {
    public static String userLoginID;
    public static String projectID;
    public static String taskID;
    public static Transdata instance;


    public static Transdata Instance() {
        if (instance == null) {
            instance = new Transdata();
        }
        return instance;
    }

    public static String getUserLoginID() {
        return userLoginID;
    }

    public static void setUserLoginID(String userLoginID) {
        Transdata.userLoginID = userLoginID;
    }

    public static String getProjectID() {
        return projectID;
    }

    public static void setProjectID(String projectID) {
        Transdata.projectID = projectID;
    }

    public static String getTaskID() {
        return taskID;
    }

    public static void setTaskID(String taskID) {
        Transdata.taskID = taskID;
    }
    
    
}
