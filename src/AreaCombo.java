
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import project_sanwa_new.Area;
import project_sanwa_new.AreaComboBoxRenderer;
import project_sanwa_new.DBArea;
import project_sanwa_new.SelectComboBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yotsathon
 */
public class AreaCombo extends JComboBox{

    DBArea dbA;
    private DefaultComboBoxModel<Area> AreaModel;

    public AreaCombo() {

        dbA = new DBArea();
        dbA.connect();
        ResultSet rs = dbA.selectAreaBySiteID(SelectComboBox.getSiteID());

        AreaModel = new DefaultComboBoxModel<Area>();
        try {
            if (rs != null && rs.next()) {
                AreaModel.addElement(new Area(null, "Select All"));
                do {
                    AreaModel.addElement(new Area(rs.getString("areaID"), rs.getString("description")));
                } while (rs.next());
            } else {
                AreaModel.addElement(new Area(null, "-----"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaCombo.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setModel(AreaModel);
        AreaComboBoxRenderer renderer = new AreaComboBoxRenderer();
        this.setRenderer(renderer);


    }



//    public void setAreaModel(DefaultComboBoxModel<Area> AreaModel) {
//        this.AreaModel = AreaModel;
//        this.setModel(AreaModel);
//       
//    }

}
