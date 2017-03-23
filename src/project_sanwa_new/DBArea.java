/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yotsathon
 */
public class DBArea extends Database  {
     public void insertArea(String areaID, String siteID, String code, String description) {
        try {
            String sql = "INSERT INTO areas(areaID, siteID, code, description) VALUES('"+areaID+"','"+siteID+"','"+code+"','"+description+"') ";
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
     public void deleteAllArea() {
       
        String sql = "DELETE FROM areas";
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
