
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import project_sanwa_new.Area;
import project_sanwa_new.DBArea;
import project_sanwa_new.DBSites;
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
public class SiteTabList extends JTabbedPane implements ChangeListener{

    DBSites dbS;
    ResultSet rs;

    public SiteTabList() {
        dbS = new DBSites();
        dbS.connect();
        rs = dbS.selectAllSite();
        try {
            if (rs != null && rs.next()) {
                do {
                    this.addTab(rs.getString("description"),new SiteTab(rs.getString("siteID"), rs.getString("description"), new ViewTable()));
                    
                } while (rs.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SiteTabList.class.getName()).log(Level.SEVERE, null, ex);
        }
//        SiteTab tabPanel = (SiteTab) this.getComponentAt(0);
//        tabPanel.siteID;
        
        this.addChangeListener(this);


        
        
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        SiteTab siteTab = (SiteTab) this.getComponent(this.getSelectedIndex());
        ViewTable viewPanel = (ViewTable) siteTab.getComponent(0);
        
        SelectComboBox.setSiteID(siteTab.getSiteID());
        System.out.println(SelectComboBox.getSiteID());
        DBArea dbA = new DBArea();
        dbA.connect();
        ResultSet rs = dbA.selectAreaBySiteID(SelectComboBox.getSiteID());
        DefaultComboBoxModel<Area> AreaModel = new DefaultComboBoxModel<Area>();
        
        try {
            if(rs !=null && rs.next())
            { 
                AreaModel.addElement(new Area(null, "Select All"));
                do{
                AreaModel.addElement(new Area(rs.getString("areaID"), rs.getString("description")));
                }while (rs.next());
            }else{
                AreaModel.addElement(new Area(null, "-----"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaCombo.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        viewPanel.setAreaCombo(AreaModel);
       
    }

 


}
