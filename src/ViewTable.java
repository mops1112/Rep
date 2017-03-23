
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.OverlayLayout;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.ui.RefineryUtilities;
import project_sanwa_new.DBDetail;
import project_sanwa_new.DBRoom;
import project_sanwa_new.LineChart;
import project_sanwa_new.Room;
import project_sanwa_new.Sanwa;
import project_sanwa_new.StateCheck;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yotsathon
 */
public class ViewTable extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form ViewTable
     */
    static Map<String, String> startDateTime;
    private static JLabel dateTime;
    private static JTable table;
    private JButton updateButton;
    JComboBox siteList;
    JComboBox areaList;
    JComboBox dragoConnexList;

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
                    c.setBackground(new Color(107, 184, 245));
                } else if (isCellSelected(rowIndex, vColIndex)) {
                    c.setBackground(new Color(0, 176, 255));
                } else {
                    c.setBackground(getBackground());
                }
                return c;
            }

        };
        // table.setFont(new Font("times new roman", Font.PLAIN, 14));
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel group = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 3));
        group.setOpaque(false);
        group.add(dateTime);
        group.add(updateButton);
        add(group, BorderLayout.PAGE_START);
        //add(updateButton, BorderLayout.PAGE_START);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);

        loadData();
        //alertMessage();

    }

    public static void loadData() {
        DBRoom dbR = new DBRoom();
        DBDetail dbD = new DBDetail();
        dbR.connect();
        dbD.connect();
        String columnNames[] = {"Site", "Area", "DragoConnex", "RoomNumber","Floor", "Water Flow (wFw)", "HighTreshold"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
        Sanwa sw = new Sanwa();
        startDateTime = sw.getCurentDateTime();
        try {
            ResultSet rs = dbD.selectAllWaterByDateTime(startDateTime.get("dateTimeLocal"));
            while (rs.next()) {
                Object[] obj = {rs.getString("siteName"), rs.getString("areaName"), rs.getString("dragoConnexName"), rs.getString("roomNumber"),rs.getString("floor"), rs.getString("wFw"), rs.getString("w_limit")};
                model.addRow(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        table.setRowSorter(sorter);
        table.setModel(model);
        dateTime.setText(startDateTime.get("dateTimeLocal"));

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
        }
    }
}
