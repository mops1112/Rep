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
public class DBDetail extends Database {

    public void insertDetailRoomWater(String room_ID, String dateTime, String wFl, String wFw, String wRv, String wVo) {
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);
        String sql = "INSERT INTO detail_room_w (room_ID, detail_date, detail_time, wFl, wFw, wRv, wVo) VALUES ( '" + room_ID + "', '" + date + "', '" + time + "', '" + wFl + "', '" + wFw + "', '" + wRv + "', '" + wVo + "' )";
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
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertDetailRoomElec(String roomID, String dateTime, String eAE) {
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);
        String sql = "INSERT INTO detail_room_e ( room_ID, detail_date, detail_time, eAE) VALUES ( '" + roomID + "', '" + date + "', '" + time + "', '" + eAE + "')";
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
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ResultSet selectWaterDetail(String roomNumber, String dateTime) {
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);
        String sql = "SELECT b.wFw, a.w_limit,  b.detail_date, b.detail_time FROM room a INNER JOIN detail_room_w b ON a.room_ID = b.room_ID where a.room_number = '" + roomNumber + "' and b.detail_date = '" + date + "'";
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

    public ResultSet selectWaterDetailToAlert(String roomID, String dateTime) {
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);
        String sql = "SELECT a.wFw,  b.roomNumber, b.w_mBusID, b.w_limit, c.description AS dragoConnexName, d.description AS areaName, e.description AS siteName\n"
                + "FROM( (((detail_room_w a INNER JOIN room b ON a.room_ID = b.room_ID )\n"
                + " INNER JOIN dragoConnexes  c ON b.DragoConnexID = c.DragoConnexID)\n"
                + " INNER JOIN areas d ON c.areaID = d.areaID)\n"
                + "INNER JOIN sites e ON d.siteID = e.siteID)\n"
                + "WHERE a.room_ID = '"+roomID+"' and a.wFw > b.w_limit and detail_date ='"+date+"' and detail_time='"+time+"'";
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
     public ResultSet selectAllWaterByDateTime(String dateTime) {
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);
        String sql = "SELECT a.wFw, b.roomNumber, b.w_mBusID, b.w_limit,b.floor, c.description AS dragoConnexName, d.description AS areaName, e.description AS siteName\n"
                + "FROM (((detail_room_w AS a INNER JOIN room AS b ON a.room_ID = b.room_ID) INNER JOIN dragoConnexes AS c ON b.DragoConnexID = c.DragoConnexID) INNER JOIN areas AS d ON c.areaID = d.areaID) INNER JOIN sites AS e ON d.siteID = e.siteID\n"
                + "WHERE  detail_date ='"+date+"' and detail_time='"+time+"';";

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
     public void deleteAllDetail() {
       
        String sql = "DELETE FROM detail_room_w";
        try {
            s = connect.createStatement();
            s.executeUpdate(sql);
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
