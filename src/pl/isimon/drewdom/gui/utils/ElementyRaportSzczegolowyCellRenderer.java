/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.gui.PanelCellRaportSElementy;
import pl.isimon.drewdom.gui.PanelCellZadania;
import pl.isimon.drewdom.gui.models.TableModelMebelElementPozycja;
import pl.isimon.drewdom.gui.models.TableModelRaportSzczegolowyElementy;
import pl.isimon.drewdom.gui.models.TableModelMebelElementPozycjaRS;

/**
 *
 * @author Simon
 */
public class ElementyRaportSzczegolowyCellRenderer implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        ArrayList<ElementPozycja> lista = (ArrayList) value;
        
        PanelCellRaportSElementy rendererComponent = new PanelCellRaportSElementy();;
        rendererComponent.setLista(lista);        
        return rendererComponent;
    }
    
}
