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
public class DBBuilding extends Database {

    public void insertBuilding(String connexID, String name, String user) {
        try {
            String sql = "INSERT INTO building ( `connexID`, `building_name`, `create_date`, `create_by`, `update_date`, `update_by`) VALUES ('" + connexID + "', '" + name + "',NOW(), '" + user + "',NOW(), '" + user + "')";
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

//    public void updateBuilding(String building_id, String name, String user) throws SQLException {
//        String sql = "UPDATE building SET `name`='" + name + "',`update_date`=NOW(),`update_by`='" + user + "' WHERE building_id = '" + building_id + "'";
//        s.execute(sql);
//    }
//
//    public void deleteBuilding(String building_id) throws SQLException {
//        String sql = "DELETE FROM `building` WHERE building_id = '" + building_id + "'";
//        s.execute(sql);
//    }
    public ResultSet selectBuilding() {
        String sql = "SELECT a.building_ID, a.building_name, a.connexID  FROM building a ";
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
    public ResultSet selectBuildingByconnexID( String connexID) {
        String sql = "SELECT a.building_ID FROM building a where connexID = '"+connexID+"'";
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

}
