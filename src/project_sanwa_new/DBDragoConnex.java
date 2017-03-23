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
public class DBDragoConnex extends Database {
     public void insertDragoConnex(String dragoConnexID, String areaID, String description, String createDateTime, String updateDateTime, String isActive ) {
        try {
            String sql = "INSERT INTO dragoconnexes(dragoConnexID, areaID, description, createdDateTime, updatedDateTime, isActive) VALUES('"+dragoConnexID+"','"+areaID+"','"+description+"','"+createDateTime+"','"+updateDateTime+"','"+isActive+"') ";
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
     public void deleteAlldragoConnex() {
       
        String sql = "DELETE FROM dragoConnexes";
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
