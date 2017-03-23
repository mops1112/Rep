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
public class DBSites extends Database {

    public void insertSite(String siteID, String code, String description, String createDateTime, String updateDateTime, String isActive, String address) {
        try {
            String sql = "INSERT INTO sites(siteID, code, description, createDateTime, updateDateTime, address, isActive) VALUES('" + siteID + "','" + code + "','" + description + "','" + createDateTime + "','" + updateDateTime + "','" + address + "','" + isActive + "') ";
            s = connect.createStatement();
            s.execute(sql);
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet selectAllSite() {
        String sql = "SELECT a.siteID, a.description, a.address FROM sites a ";
        ResultSet rs = null;

        try {
            s = this.connect.createStatement();
            rs = s.executeQuery(sql);
            if (s != null) {
                s.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;

    }

    public int updateSite(String siteID, String description, String address) {
        int rs = 0;
        String sql = "UPDATE sites a SET a.description = '"+description+"', a.address = '"+address+"' WHERE siteID = '"+siteID+"' ";
        try {
            s = connect.createStatement();
            rs = s.executeUpdate(sql);
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBSites.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }
    public void deleteAllSite() {
       
        String sql = "DELETE FROM sites";
        try {
            s = connect.createStatement();
            s.execute(sql);
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBSites.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    

}
