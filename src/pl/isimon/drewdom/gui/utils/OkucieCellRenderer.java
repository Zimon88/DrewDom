/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.utils;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import pl.isimon.drewdom.OkuciePozycja;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.gui.PanelCellRaportOkucia;
import pl.isimon.drewdom.gui.PanelCellRaportOpakowania;

/**
 *
 * @author Simon
 */
public class OkucieCellRenderer implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ArrayList<OkuciePozycja> lista = (ArrayList) value;
        
        PanelCellRaportOkucia rendererComponent = new PanelCellRaportOkucia();;
        rendererComponent.setLista(lista);        
        return rendererComponent;
    }
    
}
