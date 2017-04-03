
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import project_sanwa_new.DBDragoConnex;
import project_sanwa_new.DBRoom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yotsathon
 */
public class DialogSetWarningToAlert extends JDialog implements ActionListener{

    JComboBox warningToAlert;
   
    JButton saveButton;
    JOptionPane optionPane;
    ArrayList dragoConnexID;
    DBRoom dbR ;
    public DialogSetWarningToAlert(ArrayList dragoConnexID) {
        this.dragoConnexID = dragoConnexID;
        dbR = new DBRoom();
        String[] time = {"00:15", "00:30", "00:45", "01:00", "01:15"};
        warningToAlert = new JComboBox(time);
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        Object[] array = {warningToAlert, saveButton};
        optionPane = new JOptionPane(array,JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{}, null);
        add(optionPane);
        pack(); 
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      dbR.connect();
      for(int i=0;i<dragoConnexID.size();i++){
        int rs = dbR.updateWarningToAlert(dragoConnexID.get(i).toString(), warningToAlert.getSelectedIndex()+1);
      }
      
      this.dispose();
    }

}
