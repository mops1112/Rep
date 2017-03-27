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
public class DragoConnexComboBoxRenderer extends JLabel implements ListCellRenderer {

    public DragoConnexComboBoxRenderer() {
        setOpaque(false);
        
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            DragoConnex obj = (DragoConnex) value;
            SelectComboBox.setDragoConnexID(((DragoConnex) value).getDragoConnexID());
        }
        setText(value.toString());

        return this;
    }

}
