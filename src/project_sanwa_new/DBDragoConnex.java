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
       public ResultSet selectDragoConnexByAreaID(String areaID) {
        ResultSet rs = null;
        String sql = "SELECT * FROM dragoConnexes a";
        String where = " ";
        if (areaID != null) {
            where = where + "WHERE a.areaID = '" + areaID + "'";
        }
        sql = sql + where;
        try {
            s = connect.createStatement();
            rs = s.executeQuery(sql);
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
        return rs;
    }
       public int selectTimeAlertByDragoConnexID(String dragoConnexID) {
        int time_alert = 0;
        ResultSet rs = null;
        String sql = "SELECT a.time_alert FROM dragoConnexes a";
        String where = " ";
        if (dragoConnexID != null) {
            where = where + "WHERE a.dragoConnexID = '" + dragoConnexID + "'";
        }
        sql = sql + where;
        try {
            s = connect.createStatement();
            rs = s.executeQuery(sql);
            if (s != null) {
                try {
                    s.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(rs!=null && rs.next()){
            time_alert = rs.getInt("time_alert");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return time_alert;
    }
    public ResultSet selectAllDragoConnex() {
        ResultSet rs = null;
        String sql = "SELECT c.dragoConnexID, c.time_alert, c.description AS dragoConnexName, a.description AS siteName, b.description AS  areaName FROM (sites AS a INNER JOIN areas AS b ON a.siteID = b.siteID) INNER JOIN dragoConnexes AS c ON b.areaID = c.areaID";

        try {
            s = connect.createStatement();
            rs = s.executeQuery(sql);
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
        return rs;
    }
    public int updateDragoConnex(String dragoConnexID, int time_alert) {
        int rs = 0;
        String sql = "UPDATE dragoConnexes a SET a.time_alert = '"+time_alert+"' WHERE dragoConnexID = '"+dragoConnexID+"' ";
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
}
