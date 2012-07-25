/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Simon
 */
public class CheckBoxCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,boolean isFocused, int row, int col) {
       boolean marked = (Boolean) value;
       JCheckBox rendererComponent = new JCheckBox();
       if (marked) {
          rendererComponent.setSelected(true);
       }
       
       if (isSelected) {
            rendererComponent.setBackground(table.getSelectionBackground());
            rendererComponent.setForeground(table.getSelectionForeground());
        } else {
            if(row%2==0){
                rendererComponent.setBackground(Color.WHITE);
            } else {
                rendererComponent.setBackground(table.getBackground());
            }
            rendererComponent.setForeground(table.getForeground());
        }
       
       return rendererComponent;
    }
}
