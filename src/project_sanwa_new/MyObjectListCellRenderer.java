/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_sanwa_new;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author yotsathon
 */
public class MyObjectListCellRenderer extends BasicComboBoxRenderer {

    public Component getListCellRendererComponent(
            JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value != null) {
            Building item = (Building) value;
            item.getID();
        }

        if (index == -1) {
            Building item = (Building) value;
            setText("");
        }
       
        return this;
    }
}
