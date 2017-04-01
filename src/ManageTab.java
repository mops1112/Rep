
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
    RoomManagement roomManagement;
    public ManageTab() {
        siteManagement = new SiteManagement();
        roomManagement = new RoomManagement();
        this.addTab("Site", siteManagement);
        this.addTab("Room", roomManagement);
    }
    
    
}
