
import javax.swing.JTabbedPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yotsathon
 */
public class ManageTab extends JTabbedPane{
    SiteManagement siteManagement;
    AreaManagement areaManagement;
    RoomManagement roomManagement;
    
    public ManageTab() {
        siteManagement = new SiteManagement();
        roomManagement = new RoomManagement();
        areaManagement = new AreaManagement();
        this.addTab("Site", siteManagement);
        this.addTab("Area", areaManagement);
        this.addTab("Room", roomManagement);
    }
    
    
}
