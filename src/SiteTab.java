
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yotsathon
 */
public class SiteTab extends JPanel{

    private String siteID;
    private String siteName;
    private JPanel content;

    public SiteTab(String siteID, String siteName, JPanel content) {
        this.siteID = siteID;
        this.siteName = siteName;
        this.content = content;
        add(content);
    }

    public String getSiteID() {
        return siteID;
    }

    public JPanel getContent() {
        return content;
    }



}
