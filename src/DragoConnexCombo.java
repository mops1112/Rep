
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import project_sanwa_new.DBDragoConnex;
import project_sanwa_new.DragoConnex;
import project_sanwa_new.DragoConnexComboBoxRenderer;
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
public class DragoConnexCombo extends JComboBox {

    DBDragoConnex dbDrago;
    private JComboBox dragoConnexCom;
    private DefaultComboBoxModel<DragoConnex> dragoConnexModel;

    public DragoConnexCombo() {
        dbDrago = new DBDragoConnex();
        dbDrago.connect();
        ResultSet rs = dbDrago.selectDragoConnexByAreaID(SelectComboBox.getAreaID());
        dragoConnexModel = new DefaultComboBoxModel<DragoConnex>();

        try {
            if (rs != null && rs.next()) {
                dragoConnexModel.addElement(new DragoConnex(null, "Select All"));
                do {
                    dragoConnexModel.addElement(new DragoConnex(rs.getString("dragoConnexID"), rs.getString("description")));
                } while (rs.next());
            }else{
                 dragoConnexModel.addElement(new DragoConnex(null, "-----"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(AreaCombo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //dragoConnexCom = new JComboBox();
        //dragoConnexCom.setModel(dragoConnexModel);
        this.setModel(dragoConnexModel);
        DragoConnexComboBoxRenderer renderer = new DragoConnexComboBoxRenderer();
        //renderer.setPreferredSize(new Dimension(200, 130));
        //dragoConnexCom.setRenderer(renderer);
        this.setRenderer(renderer);
        //dragoConnexCom.addActionListener(this);
        setOpaque(false);
        //this.add(dragoConnexCom);

    }

}
