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
public class DBRoom extends Database {

    public int countRoom() {
        String sql = "SELECT count(*) AS count FROM building a INNER JOIN room b ON a.building_ID = b.Building_ID ";
        int count = 0;
        ResultSet rs = null;
        try {
            s = this.connect.createStatement();
            rs = s.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt("count");
            }
            if (s != null) {
                s.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;

    }

    public ResultSet selectRoomByBuildingFloor(String building_ID, String floor_number) {
        String sql = "SELECT a.building_ID, a.building_name, b.floor, b.room_ID,  b.room_number, b.e_meter, b.w_meter FROM building a INNER JOIN room b ON a.building_ID = b.Building_ID ";
        String where = "";

        if (building_ID != null) {
            where = "WHERE a.building_ID = " + building_ID;
        }
        if (floor_number != null) {
            if (where != "") {
                where = "WHERE ";
            } else {
                where = "AND ";
            }
            where = where + "b.floor=" + floor_number;
        }
        sql = sql + where;
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

   

    public ResultSet selectAllWaterByRoomIDDateTime(String roomID, String dateTime) {
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);
        String sql = "SELECT*FROM detail_room_w a WHERE a.room_ID = '" + roomID + "' and a.detail_date='" + date + "' and detail_time ='" + time + "'";
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

    public ResultSet selectAllElecByRoomIDDateTime(String roomID, String dateTime) {
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);
        String sql = "SELECT*FROM detail_room_e a WHERE a.room_ID = '" + roomID + "' and a.detail_date='" + date + "' and detail_time ='" + time + "'";
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
//    public void updateRoom(String room_id, String building_id, String room_number, String e_meter, String w_meter, String user) throws SQLException {
//        String sql = "UPDATE `room` SET `building_id`='" + building_id + "',`room_number`='" + room_number + "',`e_meter`='" + e_meter + "',`w_meter`='" + w_meter + "',`update_date`=NOW(),`update_by`='" + user + "' WHERE id = '" + room_id + "'";
//        s.execute(sql);
//    }
//
//    public void deleteRoom(String room_id) throws SQLException {
//        String sql = "DELETE FROM `room` WHERE building_id = '" + room_id + "'";
//        s.execute(sql);
//    }

    public ResultSet selectFloorByBuilding(String buildingID) {
        //String sql = "SELECT Nz(MAX(a.floor),'0') FROM room a";
        String sql = "SELECT DISTINCT a.floor FROM room a";
        String where = "";
        if (buildingID != null) {
            where = " WHERE a.building_ID = '" + buildingID + "'";
        }
        sql = sql + where;
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

//    public void insertRoom(String building_id, String room_number, String e_meter, String w_meter, String user) throws SQLException {
//        String sql = "INSERT INTO `room`(`building_id`, `room_number`, `e_meter`, `w_meter`, `create_date`, `create_by`, `update_date`, `update_by`) VALUES (" + building_id + ",'" + room_number + "',\"" + e_meter + "\", \"" + w_meter + "\",NOW(),'" + user + "',NOW(),'" + user + "')";
//        s = connect.createStatement();
//        s.execute(sql);
//        if (s != null) {
//            try {
//                s.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    public void insertRoom(String w_mBusID, String dragoConnexID, String roomNumber, String w_limit) throws SQLException {
        try {
            String sql = "INSERT INTO room(w_mBusID, dragoConnexID, roomNumber, w_limit, floor) VALUES ('" + w_mBusID + "','" + dragoConnexID + "','" + roomNumber + "', '"+w_limit+"','0')";
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

    public ResultSet selectRoomIDByBuildingFloor(String buildingID, String floor) {
        String sql = "SELECT a.room_ID, a.room_number, a.e_meter FROM room a WHERE a.building_ID ='" + buildingID + "' and a.floor = '" + floor + "'";
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

    public int selectIDBymBusIDDateTime(String dragoConnexID, String mBusID, String dateTime) throws SQLException {
        String date = dateTime.substring(0, 10);
        String time = dateTime.substring(11);
        ResultSet room_ID = selectRoomIDBymBusID(dragoConnexID, mBusID);
        String sql = "";
        int state = 1;
        ResultSet rs = null;
        if (room_ID.next()) {
            sql = "SELECT b.detail_ID FROM detail_room_w b WHERE b.room_ID =" + room_ID.getString("room_ID") + " and b.detail_date='" + date + "' and b.detail_time ='" + time + "'";
            try {
                s = this.connect.createStatement();
                rs = s.executeQuery(sql);
                if (s != null) {
                    s.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!rs.next()) {
                state = 0;
            }
        }

        return state;

    }

    public ResultSet selectRoomIDBymBusID(String dragoConnexID, String mBusID) {

        String sql = "SELECT a.room_ID FROM room a WHERE a.dragoConnexID = '" + dragoConnexID + "' and a.w_mBusID = '" + mBusID + "'";

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

    public ResultSet selectAllRoom() {

        String sql = "SELECT a.room_ID FROM room a ";

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
    public ResultSet selectAllRoomDragoConnex(){
     String sql = "SELECT a.room_ID, a.roomNumber, a.w_mBusID, a.w_limit, a.floor, b.description FROM room a INNER JOIN dragoConnexes b ON a.dragoConnexID = b.dragoConnexID ORDER BY b.description, a.roomNumber";

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
    public int updateRoom(String roomID,String roomNumber, String w_limit, String floor) {
        int rs = 0;
        String sql = "UPDATE room a SET a.roomNumber = '"+roomNumber+"', a.w_limit = '"+w_limit+"', a.floor = '"+floor+"' WHERE room_ID = '"+roomID+"' ";
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
     public int updateFloor(String roomID,String floor) {
        int rs = 0;
        String sql = "UPDATE room a SET a.floor = '"+floor+"' WHERE room_ID = '"+roomID+"' ";
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
