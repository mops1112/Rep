
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
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
public class RoomManagement extends JPanel implements MouseListener, ActionListener {

    /**
     * Creates new form RoomManagement
     */
    JTable table;
    JTextField roomNumber;
    JTextField floor;
    JTextField w_highTreshold;
    JTextField w_lowTreshold;
    JTextField w_maxPerday;
    JButton saveButton;
    JButton updateButton;
    JPanel showTable;
    JPanel edit;
    DefaultTableModel model;
    DBRoom dbR;
    int rowTable;
    JPopupMenu popupMenu;
    JMenuItem menuItemSetFloor;
    JMenuItem menuItemSetHighTreshold;
    JMenuItem menuItemSetLowTreshold;
    JMenuItem menuItemSetMaximumPerDay;
    int[] multiRows;

    public RoomManagement() {
        initComponents();
        dbR = new DBRoom();

        table = new JTable() {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    c.setBackground(new Color(243, 242, 243));
                } else if (isCellSelected(rowIndex, vColIndex)) {
                    c.setBackground(new Color(174, 209, 241));
                    c.setForeground(new Color(0, 0, 0));
                } else {
                    //c.setBackground(getBackground());
                    c.setBackground(new Color(255, 255, 255));
                }
                return c;
            }

        };

        table.setPreferredScrollableViewportSize(new Dimension(650, 400));
        table.setFont(new Font("Century Gothic", 0, 12));
        
        table.setFillsViewportHeight(true);
        table.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(table);
        
        roomNumber = new JTextField("Room Number", 50);
        roomNumber.setMaximumSize(roomNumber.getPreferredSize());
        floor = new JTextField("Floor Number", 50);
        floor.setMaximumSize(floor.getPreferredSize());
        w_highTreshold = new JTextField("HighTreshold", 50);
        w_highTreshold.setMaximumSize(w_highTreshold.getPreferredSize());
        w_lowTreshold = new JTextField("LowThreshold", 50);
        w_lowTreshold.setMaximumSize(w_lowTreshold.getPreferredSize());
        w_maxPerday = new JTextField("Maximun Per Day", 50);
        w_maxPerday.setMaximumSize(w_maxPerday.getPreferredSize());
        saveButton = new JButton("Save");
        saveButton.setEnabled(false);
        saveButton.addMouseListener(this);
        updateButton = new JButton("Update");
        updateButton.addMouseListener(this);

        edit = new JPanel();
        edit.setLayout(new BoxLayout(edit, BoxLayout.Y_AXIS));
        //edit.setPreferredSize(new Dimension(10, 100));
       
        edit.add(roomNumber);
        edit.add(w_highTreshold);
        edit.add(w_lowTreshold);
        edit.add(w_maxPerday);
        edit.add(floor);
        edit.add(saveButton);

        getDataToTable();
        showTable = new JPanel(new BorderLayout());
        showTable.add(updateButton, BorderLayout.NORTH);
        showTable.add(scrollPane, BorderLayout.CENTER);

        add(showTable, BorderLayout.LINE_START);
        add(edit, BorderLayout.CENTER);
    }

    private void getDataToTable() {
        String columnNames[] = {"roomID", "DragoConnex", "Room Number", "Meter", "HighTreshold", "LowTreshold", "Maximum Per Day", "Floor"};
        model = new DefaultTableModel(new Object[0][0], columnNames);
        dbR.connect();
        ResultSet rs = dbR.selectAllRoomDragoConnex();
        try {
            while (rs.next()) {
                Object[] obj = {rs.getString("room_ID"), rs.getString("description"), (rs.getString("roomNumber")).replaceFirst("^0+(?!$)", ""), (rs.getString("w_mBusID")).replaceFirst("^0+(?!$)", ""), rs.getString("w_high_treshold"), rs.getString("w_low_treshold"), rs.getString("w_max_per_day"), rs.getString("floor")};
                model.addRow(obj);
            }
//            RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
//            table.setRowSorter(sorter);
            table.setModel(model);
            table.removeColumn(table.getColumnModel().getColumn(0));
        } catch (SQLException ex) {
            Logger.getLogger(SiteManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == table) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                saveButton.setEnabled(true);
                rowTable = table.rowAtPoint(e.getPoint());
                roomNumber.setText(model.getValueAt(rowTable, 2).toString());
                w_highTreshold.setText(model.getValueAt(rowTable, 4).toString());
                w_lowTreshold.setText(model.getValueAt(rowTable, 5).toString());
                w_maxPerday.setText(model.getValueAt(rowTable, 6).toString());
                floor.setText(model.getValueAt(rowTable, 7).toString());
            } else {

                popupMenu = new JPopupMenu();
                menuItemSetFloor = new JMenuItem("Set Floor");
                menuItemSetHighTreshold = new JMenuItem("Set HighTreshold");
                menuItemSetLowTreshold = new JMenuItem("Set LowTreshold");
                menuItemSetMaximumPerDay = new JMenuItem("Set Maximum Per Day");
                menuItemSetFloor.addActionListener(this);
                menuItemSetHighTreshold.addActionListener(this);
                menuItemSetLowTreshold.addActionListener(this);
                menuItemSetMaximumPerDay.addActionListener(this);

                popupMenu.add(menuItemSetHighTreshold);
                popupMenu.add(menuItemSetLowTreshold);
                popupMenu.add(menuItemSetMaximumPerDay);
                popupMenu.add(menuItemSetFloor);
                
                popupMenu.show(e.getComponent(), e.getX(), e.getY());

            }

        } else if (e.getSource() == saveButton) {
            int rs = dbR.updateRoom(model.getValueAt(rowTable, 0).toString(), roomNumber.getText(), w_highTreshold.getText(),w_lowTreshold.getText(),w_maxPerday.getText(), floor.getText());
            if (rs != 0) {
                getDataToTable();
                roomNumber.setText("");
                w_highTreshold.setText("");
                w_lowTreshold.setText("");
                w_maxPerday.setText("");
                floor.setText("");
                saveButton.setEnabled(false);
            }
        } else if (e.getSource() == updateButton) {
            getDataToTable();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        multiRows = table.getSelectedRows();
        if (e.getSource() == menuItemSetFloor) {
            //multiRows = table.getSelectedRows();
            ArrayList updateFloor = new ArrayList();
            for (int i = 0; i < multiRows.length; i++) {
                updateFloor.add(model.getValueAt(multiRows[i], 0));
            }
            DialogSetFloor dialogSetFloor = new DialogSetFloor(updateFloor);
            dialogSetFloor.setPreferredSize(new Dimension(200, 100));
            dialogSetFloor.setLocationRelativeTo(null);
            dialogSetFloor.setVisible(true);

        }else if(e.getSource() == menuItemSetHighTreshold){
            
            ArrayList updateHighTreshold = new ArrayList();
            for (int i = 0; i < multiRows.length; i++) {
                updateHighTreshold.add(model.getValueAt(multiRows[i], 0));
            }
            DialogSetHighTreshold dialogSetHighTreshold = new DialogSetHighTreshold(updateHighTreshold);
            dialogSetHighTreshold.setPreferredSize(new Dimension(200, 100));
            dialogSetHighTreshold.setLocationRelativeTo(null);
            dialogSetHighTreshold.setVisible(true);
        }else if(e.getSource() == menuItemSetLowTreshold){
          
            ArrayList updateLowTreshold = new ArrayList();
            for (int i = 0; i < multiRows.length; i++) {
                updateLowTreshold.add(model.getValueAt(multiRows[i], 0));
            }
            DialogSetLowTreshold dialogSetLowTreshold = new DialogSetLowTreshold(updateLowTreshold);
            dialogSetLowTreshold.setPreferredSize(new Dimension(200, 100));
            dialogSetLowTreshold.setLocationRelativeTo(null);
            dialogSetLowTreshold.setVisible(true);
        }else if(e.getSource() == menuItemSetMaximumPerDay){
          
            ArrayList updateMaxPerDay = new ArrayList();
            for (int i = 0; i < multiRows.length; i++) {
                updateMaxPerDay.add(model.getValueAt(multiRows[i], 0));
            }
            DialogSetMaxPerDay dialogSetMaxPerDay = new DialogSetMaxPerDay(updateMaxPerDay);
            dialogSetMaxPerDay.actionPerformed(e);
            dialogSetMaxPerDay.setPreferredSize(new Dimension(200, 100));
            dialogSetMaxPerDay.setLocationRelativeTo(null);
            dialogSetMaxPerDay.setVisible(true);
        }
        

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
