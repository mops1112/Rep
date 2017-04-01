/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author yotsathon
 */
public class FloorComboBoxRenderer extends JLabel implements ListCellRenderer {

    public FloorComboBoxRenderer() {
        setOpaque(false);
        
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            Floor obj = (Floor) value;
            SelectComboBox.setFloorNumber(((Floor) value).getFloorID());
        }
        setText(value.toString());

        return this;
    }

}
