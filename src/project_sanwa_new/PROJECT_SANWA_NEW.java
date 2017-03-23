/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author yotsathon
 */
public class PROJECT_SANWA_NEW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        try {
            Database db = new Database();
            Sanwa sw = new Sanwa();
            sw.setRooms();
            

            int x=1;
        } catch (IOException ex) {
            Logger.getLogger(PROJECT_SANWA_NEW.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
