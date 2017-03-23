/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yotsathon
 */
public class Database {

    Connection connect;
    Statement s;

    public Database() {
        connect = null;
        s = null;
    }

    public void connect() {
        try {
            File dbFile = new File("db_sanwa.mdb");
            String path = dbFile.getAbsolutePath();
            //connect = DriverManager.getConnection("jdbc:ucanaccess://C:/tmp/PROJECT_SANWA_NEW/src/project_sanwa_new/db_sanwa.mdb;memory=false");
            connect = DriverManager.getConnection("jdbc:ucanaccess://" + path + ";memory=false");
            if (connect != null) {
                System.out.println("Database Connected.");
            } else {
                System.out.println("Database Connect Failed.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getStatusStartProgram() {
        String sql = "SELECT a.status FROM statusProgram a WHERE a.ID = 1";
               
        ResultSet rs = null;
        boolean status = false;
        try {
            s = this.connect.createStatement();
            rs = s.executeQuery(sql);
            if (s != null) {
                s.close();
                    if(rs.next() && rs.getString("status").equals("1")){
                        status = true;
                    }
                   
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public boolean setStatusStartProgram(String statusValue) {
        String sql = "UPDATE status a SET a.status = '" + statusValue + "' WHERE a.ID='1'";
        boolean rs = false;

        try {
            s = this.connect.createStatement();
            rs = s.execute(sql);
            if (s != null) {
                s.close();
                rs = true;

            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

}
