/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import pl.isimon.drewdom.gui.PanelCellZadania;

/**
 *
 * @author Simon
 */
public class ZadaniaCellRenderer implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        PanelCellZadania rendererComponent = new PanelCellZadania();
        int v = (int) value;
        switch(v){
            case 0: rendererComponent.setSelection(false,false,false); break;
            case 1: rendererComponent.setSelection(true,false,false); break;
            case 2: rendererComponent.setSelection(false,true,true); break;
            case 3: rendererComponent.setSelection(true,true,false); break;
            case 4: rendererComponent.setSelection(false,false,true); break;
            case 5: rendererComponent.setSelection(true,false,true); break;
            case 6: rendererComponent.setSelection(false,true,true); break;
            case 7: rendererComponent.setSelection(true,true,true); break;
        }
        if (isSelected) {
            rendererComponent.setSelectionColor(table.getSelectionBackground(), table.getSelectionForeground());
        } else {
            if(row%2==0){
                rendererComponent.setSelectionColor(Color.WHITE, table.getForeground());
            } else {
                rendererComponent.setSelectionColor(table.getBackground(), table.getForeground());
            }
        }
        return rendererComponent;
    }
    
}
