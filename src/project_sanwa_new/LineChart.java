/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author yotsathon
 */
public class LineChart extends ApplicationFrame {

    public LineChart(String title, String chartTitle, String roomNumber, String dateTime) {
        super(title);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Time(H)", "Water(m^3/h)",
                createDataset(roomNumber, dateTime),
                PlotOrientation.VERTICAL,
                true, true, false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(String roomNumber, String dateTime) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try {

            DBDetail dbD = new DBDetail();
            dbD.connect();
            ResultSet rs = dbD.selectWaterDetail(roomNumber, dateTime);
            while (rs.next()) {

                dataset.addValue(Double.parseDouble(rs.getString("wFw")), "Water", rs.getString("detail_time"));

                dataset.addValue(Double.parseDouble(rs.getString("w_limit")), "ERROR", rs.getString("detail_time"));


            }

        } catch (SQLException ex) {
            Logger.getLogger(LineChart.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataset;
    }
    public void windowClosing(final WindowEvent evt) {
                if (evt.getWindow() == this) {
                    dispose();

                }
            }

//    public static void main(String[] args) {
//        LineChart chart = new LineChart(
//                "School Vs Years",
//                "Numer of Schools vs years");
//
//        chart.pack();
//        RefineryUtilities.centerFrameOnScreen(chart);
//        chart.setVisible(true);
//    }
}
