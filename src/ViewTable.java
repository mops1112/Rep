
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.ui.RefineryUtilities;
import project_sanwa_new.Area;
import project_sanwa_new.DBDetail;
import project_sanwa_new.DBDragoConnex;
import project_sanwa_new.DBRoom;
import project_sanwa_new.DragoConnex;
import project_sanwa_new.Floor;
import project_sanwa_new.LineChart;
import project_sanwa_new.Sanwa;
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
public class ViewTable extends javax.swing.JPanel implements ActionListener{

    /**
     * Creates new form ViewTable
     */
    static Map<String, String> startDateTime;
    private JLabel dateTime;
    private JTable table;
    private JButton updateButton;

    //JComboBox areaCombo;
    private FloorCombo floorCombo;
    private AreaCombo areaCombo;
    private DragoConnexCombo dragoConnexCombo;

    public void setTable(DefaultTableModel model) {
        this.table.setModel(model);
    }

    public void setAreaCombo(DefaultComboBoxModel<Area> AreaModel) {
        this.areaCombo.setModel(AreaModel);
    
    }

    public void setFloorCombo(DefaultComboBoxModel<Floor> FloorModel) {
        this.floorCombo.setModel(FloorModel);
    }

    public void setDragoConnexCombo(DefaultComboBoxModel<DragoConnex> dragoConnexModel) {
       this.dragoConnexCombo.setModel(dragoConnexModel);
    }

    public ViewTable() {
        initComponents();
        this.setOpaque(false);
        dateTime = new JLabel("DateTime :");
        updateButton = new JButton("UPDATE");
        updateButton.setBackground(new java.awt.Color(255, 61, 0));
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.addActionListener(this);
        table = new JTable() {

            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int vColIndex) {
                Component c = super.prepareRenderer(renderer, rowIndex, vColIndex);
                if (rowIndex % 2 == 0 && !isCellSelected(rowIndex, vColIndex)) {
                    c.setBackground(new Color(243,242,243));
                } else if (isCellSelected(rowIndex, vColIndex)) {
                    c.setBackground(new Color(174,209,241));
                    c.setForeground(new Color(0,0,0));
                } else {
                        //c.setBackground(getBackground());
                     c.setBackground(new Color(255,255,255));
                }
                
                float wFw = Float.valueOf((table.getValueAt(rowIndex,3)).toString());
                float limit = Float.valueOf((table.getValueAt(rowIndex,4)).toString());
                if(wFw > limit) {
                     c.setBackground(new Color(244,196,55));
                     
                } 
                    
                
                return c;
            }

        };
        // table.setFont(new Font("times new roman", Font.PLAIN, 14));
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Century Gothic",0, 12));
        JScrollPane scrollPane = new JScrollPane(table);
        //loadData();
        SelectComboBox selectComboBox = new SelectComboBox();
        areaCombo = new AreaCombo();
        areaCombo.addActionListener(this);
        dragoConnexCombo = new DragoConnexCombo();
        dragoConnexCombo.addActionListener(this);
        floorCombo = new FloorCombo();

        JPanel group = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 3));
        group.setOpaque(false);
        group.add(areaCombo);
        group.add(dragoConnexCombo);
        group.add(floorCombo);
        group.add(dateTime);
        group.add(updateButton);
        add(group, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);

        
       

    }

    private void loadData() {
        DBRoom dbR = new DBRoom();
        DBDetail dbD = new DBDetail();
        dbR.connect();
        dbD.connect();
        String columnNames[] = {"Status","RoomNumber", "Floor", "Water Flow (wFw)", "HighTreshold"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
        Sanwa sw = new Sanwa();
        startDateTime = sw.getCurentDateTime();
        try {
            ResultSet rs = dbD.selectAllWaterByDateTimeSiteIDAreaIDDragoConnexIDFloor(startDateTime.get("dateTimeLocal"),SelectComboBox.getSiteID(),SelectComboBox.getAreaID(),SelectComboBox.getDragoConnexID(),SelectComboBox.getFloorNumber());
             
             while (rs.next()) {
                String status = "Normal";
                float wFw = Float.valueOf(rs.getString("wFw"));
                float limit = Float.valueOf(rs.getString("w_limit"));
                if(wFw>limit){
                    status = "Warning";
                }
                Object[] obj = {status,rs.getString("roomNumber"), rs.getString("floor"), rs.getString("wFw"), rs.getString("w_limit")};
                model.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        this.table.setRowSorter(sorter);
        this.setTable(model);
//        table.setRowSorter(sorter);
//        table.setModel(model);
        this.dateTime.setText(startDateTime.get("dateTimeLocal"));
        System.out.println(startDateTime.get("dateTimeLocal"));

        dbR.close();
        dbD.close();

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private JPopupMenu createYourPopUp(int rowindex) {

        JPopupMenu popup = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem(new AbstractAction("View Line Graph(WATER)") {
            public void actionPerformed(ActionEvent evt) {

                LineChart chart = new LineChart(
                        "Water Vs Time",
                        "WATER",
                        table.getModel().getValueAt(rowindex, 2).toString(),
                        startDateTime.get("dateTimeLocal"));

                chart.pack();
                RefineryUtilities.centerFrameOnScreen(chart);
                chart.setVisible(true);
            }

        });
        popup.add(menuItem1);
        return popup;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            loadData();
        } else if (e.getSource() == areaCombo) {
            DBDragoConnex dbDrago = new DBDragoConnex();
            dbDrago.connect();
            ResultSet rs = dbDrago.selectDragoConnexByAreaID(SelectComboBox.getAreaID());
            DefaultComboBoxModel<DragoConnex> dragoConnexModel = new DefaultComboBoxModel<DragoConnex>();
            try {
                if (rs != null && rs.next()) {
                    dragoConnexModel.addElement(new DragoConnex(null, "Select All"));
                    do {
                        dragoConnexModel.addElement(new DragoConnex(rs.getString("dragoConnexID"), rs.getString("description")));
                    } while (rs.next());

                } else {
                    dragoConnexModel.addElement(new DragoConnex(null, "-----"));
                }
               this.setDragoConnexCombo(dragoConnexModel);
              

            } catch (SQLException ex) {
                Logger.getLogger(AreaCombo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }else if(e.getSource() == dragoConnexCombo){
            DBRoom dbR = new DBRoom();
            dbR.connect();
            DefaultComboBoxModel<Floor> floorModel = new DefaultComboBoxModel<Floor>();
            ResultSet rs = dbR.selectFloorBydragoConnexID(SelectComboBox.getDragoConnexID());
            try {
                if(rs!=null && rs.next()){
                    floorModel.addElement(new Floor(null, "Select All"));
                    do{
                        floorModel.addElement(new Floor(rs.getString("floor"), rs.getString("floor")));
                    }while(rs.next());
                }else{
                    floorModel.addElement(new Floor(null, "-----"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setFloorCombo(floorModel);
        }
    }

}


