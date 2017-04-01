
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import project_sanwa_new.Area;
import project_sanwa_new.DBArea;
import project_sanwa_new.DBRoom;
import project_sanwa_new.Floor;
import project_sanwa_new.FloorComboBoxRenderer;
import project_sanwa_new.Room;
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
public class FloorCombo extends JComboBox {

    DBRoom dbR;
    private JComboBox floorCom;
    private DefaultComboBoxModel<Floor> floorModel;

    public FloorCombo() {
        dbR = new DBRoom();
        dbR.connect();
        ResultSet rs = dbR.selectFloorBydragoConnexID(SelectComboBox.getDragoConnexID());
        floorModel = new DefaultComboBoxModel<Floor>();
        try {
            if (rs != null && rs.next()) {
                floorModel.addElement(new Floor(null,"Select All"));
                do {
                    floorModel.addElement(new Floor(rs.getString("floor"),rs.getString("floor")));
                } while (rs.next());
            }
            else{
                floorModel.addElement(new Floor("-1","-----"));
            }
                

        } catch (SQLException ex) {
            Logger.getLogger(AreaCombo.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.setModel(floorModel);
        FloorComboBoxRenderer renderer = new FloorComboBoxRenderer();
        this.setRenderer(renderer);
        setOpaque(false);

    }

}
