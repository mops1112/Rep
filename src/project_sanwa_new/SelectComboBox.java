/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yotsathon
 */
public class SelectComboBox {
    private static String siteID;
    private static String areaID;
    private static String dragoConnexID;
    private static String floorNumber;

    public SelectComboBox() {
        DBArea dbA = new DBArea();
        dbA.connect();
        ResultSet rs = dbA.selectArea();
        try {
            if(rs!=null && rs.next()){
                SelectComboBox.siteID = rs.getString("siteID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SelectComboBox.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String getSiteID() {
        return siteID;
    }

    public static void setSiteID(String siteID) {
        SelectComboBox.siteID = siteID;
    }

    public static String getAreaID() {
        return areaID;
    }

    public static void setAreaID(String areaID) {
        SelectComboBox.areaID = areaID;
    }

    public static String getDragoConnexID() {
        return dragoConnexID;
    }

    public static void setDragoConnexID(String dragoConnexID) {
        SelectComboBox.dragoConnexID = dragoConnexID;
    }

    public static String getFloorNumber() {
        return floorNumber;
    }

    public static void setFloorNumber(String floorNumber) {
        SelectComboBox.floorNumber = floorNumber;
    }



    
    
}
