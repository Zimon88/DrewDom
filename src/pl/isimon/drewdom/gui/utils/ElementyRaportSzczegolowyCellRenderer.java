/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.utils;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.gui.PanelCellRaportSElementy;

/**
 *
 * @author Simon
 */
public class ElementyRaportSzczegolowyCellRenderer implements TableCellRenderer{
    
    private boolean renderQR = false;
    
    public ElementyRaportSzczegolowyCellRenderer() {
    }
    
    public ElementyRaportSzczegolowyCellRenderer(boolean renderQR) {
        this.renderQR = renderQR;
    }

    @Override
    @SuppressWarnings("empty-statement")
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ArrayList<ElementPozycja> lista = (ArrayList) value;
        
        PanelCellRaportSElementy rendererComponent = new PanelCellRaportSElementy();;
        rendererComponent.setLista(lista,this.renderQR);     
        return rendererComponent;
    }
    
}
