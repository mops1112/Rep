
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
public class DialogSetLowTreshold extends JDialog implements ActionListener{

    JTextField lowTreshold;
    JButton saveButton;
    JOptionPane optionPane;
    ArrayList roomID;
    DBRoom dbR ;
    public DialogSetLowTreshold(ArrayList roomID) {
        this.roomID = roomID;
        dbR = new DBRoom();
        lowTreshold = new JTextField("LowTreshold");
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        Object[] array = {lowTreshold, saveButton};
        optionPane = new JOptionPane(array,JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,new Object[]{}, null);
        add(optionPane);
        pack(); 
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      dbR.connect();
      for(int i=0;i<roomID.size();i++){
        int rs = dbR.updateLowTreshold(roomID.get(i).toString(), lowTreshold.getText());
      }
      
      this.dispose();
    }

}
