
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
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
import org.json.JSONArray;
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
public class ViewTable extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form ViewTable
     */
    static Map<String, String> currentDateTime;
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
                    c.setBackground(new Color(243, 242, 243));
                } else if (isCellSelected(rowIndex, vColIndex)) {
                    c.setBackground(new Color(174, 209, 241));
                    c.setForeground(new Color(0, 0, 0));
                } else {
                    //c.setBackground(getBackground());
                    c.setBackground(new Color(255, 255, 255));
                }
                String highlight = table.getValueAt(rowIndex, 0).toString();
                if (!highlight.equals("Normal")) {
                    c.setBackground(new Color(244, 196, 55));
                }


                return c;
            }

        };
        // table.setFont(new Font("times new roman", Font.PLAIN, 14));
        table.setPreferredScrollableViewportSize(new Dimension(800, 600));
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Century Gothic", 0, 12));
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

    public static void getRoomDetailFromServerByStart() throws IOException, SQLException {
        DBRoom dbR = new DBRoom();
        DBDetail dbD = new DBDetail();
        dbR.connect();
        dbD.connect();

        URL url;
        BufferedReader in;
        JSONArray jsonArray;

        url = new URL("http://dragoservices.azurewebsites.net/api/DragoServices/MeterRecord?s=" + startDateTime.get("dateTimeServer") + "%2B0700");
        in = new BufferedReader(new InputStreamReader(url.openStream()));
        String line = in.readLine();
        if (!"[]".equals(line)) {
            jsonArray = new JSONArray(line);
            float count = jsonArray.length();
            for (int i = 0; i < jsonArray.length(); i++) {
                //String t = jsonArray.getJSONObject(i).getJSONArray("records").getJSONObject(0).get("t").toString();
                String mBusID = jsonArray.getJSONObject(i).get("mBusID").toString();
                String dragoConnexID = jsonArray.getJSONObject(i).get("dragoConnexID").toString();
                dragoConnexID = dragoConnexID.substring(2);
                String wFl = jsonArray.getJSONObject(i).getJSONArray("records").getJSONObject(0).get("wFl").toString();
                int rs = dbR.selectIDBymBusIDDateTime(dragoConnexID, mBusID, startDateTime.get("dateTimeLocal"));
                if (rs == 0) {
                    ResultSet roomID = dbR.selectRoomIDBymBusID(dragoConnexID, mBusID);
                    if (roomID.next()) {
                        dbD.insertDetailRoomWater(roomID.getString("room_ID").toString(), startDateTime.get("dateTimeLocal"), wFl, jsonArray.getJSONObject(i).getJSONArray("records").getJSONObject(0).get("wFw").toString(), jsonArray.getJSONObject(i).getJSONArray("records").getJSONObject(0).get("wRv").toString(), jsonArray.getJSONObject(i).getJSONArray("records").getJSONObject(0).get("wVo").toString());

                    }

                }

            }

        }
        dbD.close();
        dbR.close();
    }

    private void loadData() {
        DBRoom dbR = new DBRoom();
        DBDetail dbD = new DBDetail();
        dbR.connect();
        dbD.connect();
        String columnNames[] = {"Status", "RoomNumber", "Floor", " wFl(FlowRate)", "Today"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
        Sanwa sw = new Sanwa();
        currentDateTime = sw.getCurentDateTime();
        startDateTime = sw.getStartDateTime();
        try {
            ResultSet allRoom = dbD.selectAllWaterBySiteIDAreaIDDragoConnexIDFloor(SelectComboBox.getSiteID(), SelectComboBox.getAreaID(), SelectComboBox.getDragoConnexID(), SelectComboBox.getFloorNumber());
            ResultSet rs_current = dbD.selectAllWaterByDateTimeSiteIDAreaIDDragoConnexIDFloor(currentDateTime.get("dateTimeLocal"), SelectComboBox.getSiteID(), SelectComboBox.getAreaID(), SelectComboBox.getDragoConnexID(), SelectComboBox.getFloorNumber());
            ResultSet rs_start = dbD.selectAllWaterByDateTimeSiteIDAreaIDDragoConnexIDFloor(startDateTime.get("dateTimeLocal"), SelectComboBox.getSiteID(), SelectComboBox.getAreaID(), SelectComboBox.getDragoConnexID(), SelectComboBox.getFloorNumber());

            if (allRoom != null && allRoom.next()) {
                do {
                    String status = "";
                    String status_warning = "";
                    String status_perday = "";
                    float perDay = 0;
                    float wFl = 0;
                    boolean found = false;
                    boolean found2 = false;

                    while (rs_current.next()) {
                        if (allRoom.getString("roomnumber").equals(rs_current.getString("roomNumber"))) {
                            wFl = Float.valueOf(rs_current.getString("wFl"));
                            float w_high_treshold = Float.valueOf(rs_current.getString("w_high_treshold"));
                            float w_low_treshold = Float.valueOf(rs_current.getString("w_low_treshold"));
                            if (wFl > w_high_treshold) {
                                status_warning = "Warning : Over";
                            }
                            if ((wFl < w_low_treshold && wFl > 0)) {
                                status_warning = "Warning : Leak";
                            }

                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        status_warning = "Warning : Disconnect";
                    } else {
                        // calculate perDay
                        while (rs_start.next()) {
                            perDay = 0;
                            if (allRoom.getString("roomnumber").equals(rs_start.getString("roomNumber"))) {
                                perDay = Float.valueOf(rs_current.getString("wFw")) - Float.valueOf(rs_start.getString("wFw"));
                                Float w_max_perDay = Float.valueOf(allRoom.getString("w_max_per_day"));
                                if (perDay > w_max_perDay) {
                                    status_perday = "Warning : Over perDay";
                                }
                                found2 = true;
                                break;
                            }
                        }
                    }
                    if (status_warning.equals("") && status_perday.equals("")) {
                        status = "Normal";
                    } else {
                        String comma = "";
                        if (!status_warning.equals("") && !status_perday.equals("")) {
                            comma = ", ";
                        }
                        status = status_warning + comma + status_perday;
                    }

                    Object[] obj = {status, (allRoom.getString("roomNumber")).replaceFirst("^0+(?!$)", ""), allRoom.getString("floor"), wFl, String.format("%.3f", perDay)};
                    model.addRow(obj);

                } while (allRoom.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        this.table.setRowSorter(sorter);
        this.setTable(model);
//        table.setRowSorter(sorter);
//        table.setModel(model);
        this.dateTime.setText(currentDateTime.get("dateTimeLocal"));
//        System.out.println(currentDateTime.get("dateTimeLocal"));

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
                        currentDateTime.get("dateTimeLocal"));

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
            //System.out.println(SelectComboBox.getSiteID()+" "+SelectComboBox.getAreaID()+" "+ SelectComboBox.getDragoConnexID()+" "+SelectComboBox.getFloorNumber());
            loadData();
        } else if (e.getSource() == areaCombo) {
            DBDragoConnex dbDrago = new DBDragoConnex();
            dbDrago.connect();
            ResultSet rs = dbDrago.selectDragoConnexByAreaID(SelectComboBox.getAreaID());
            DefaultComboBoxModel<DragoConnex> dragoConnexModel = new DefaultComboBoxModel<DragoConnex>();
            DefaultComboBoxModel<Floor> floorModel = new DefaultComboBoxModel<Floor>();
            try {
                if (rs != null && rs.next()) {
                    dragoConnexModel.addElement(new DragoConnex(null, "Select All"));
                    do {
                        dragoConnexModel.addElement(new DragoConnex(rs.getString("dragoConnexID"), rs.getString("description")));
                    } while (rs.next());

                } else {
                    dragoConnexModel.addElement(new DragoConnex("-1", "-----"));
                    floorModel.addElement(new Floor("-1", "-----"));
                    this.setFloorCombo(floorModel);
                }
                this.setDragoConnexCombo(dragoConnexModel);

            } catch (SQLException ex) {
                Logger.getLogger(AreaCombo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (e.getSource() == dragoConnexCombo) {
            DBRoom dbR = new DBRoom();
            dbR.connect();
            DefaultComboBoxModel<Floor> floorModel = new DefaultComboBoxModel<Floor>();
            ResultSet rs = dbR.selectFloorBydragoConnexID(SelectComboBox.getDragoConnexID());
            try {
                if (rs != null && rs.next()) {
                    floorModel.addElement(new Floor(null, "Select All"));
                    do {
                        floorModel.addElement(new Floor(rs.getString("floor"), rs.getString("floor")));
                    } while (rs.next());
                } else {
                    floorModel.addElement(new Floor("-1", "-----"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ViewTable.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setFloorCombo(floorModel);
        }
    }

}
