/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.utils;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.OpakowaniePozycja;
import pl.isimon.drewdom.gui.PanelCellRaportOpakowania3;

/**
 *
 * @author Simon
 */
public class OpakowaniaCellRenderer3 implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ArrayList<OpakowaniePozycja> lista = (ArrayList) value;
        
        PanelCellRaportOpakowania3 rendererComponent = new PanelCellRaportOpakowania3();;
        rendererComponent.setLista(lista);        
        return rendererComponent;
    }
    
}
