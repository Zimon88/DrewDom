/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.gui.models.TableModelOkuciePozycja;
import pl.isimon.drewdom.gui.models.TableModelOpakowanie2;
import pl.isimon.drewdom.gui.utils.TableColumnAdjuster;

/**
 *
 * @author Simon
 */
public class PanelCellRaportOkucia extends javax.swing.JPanel {

    ArrayList<Opakowanie> lista;
    TableModelOpakowanie2 model;
    TableColumnAdjuster tca;
    /**
     * Creates new form PanelCellRaportSElementy
     */
    public PanelCellRaportOkucia() {
        initComponents();
        model = (TableModelOpakowanie2) tableOkucia.getModel();
        tableOkucia.getTableHeader().setEnabled(false);
        tca = new TableColumnAdjuster(tableOkucia);
        tca.setOnlyAdjustLarger(false);
    }

    public void setLista(ArrayList<Opakowanie> lista) {
        this.lista = lista;
        model.setModelData(lista);
        updateRowHeights();
        tca.adjustColumns();
        
    }
    
    private void updateRowHeights()
    {
        try
        {
            int totalHeight = 16;
            tableOkucia.getTableHeader().setPreferredSize(new Dimension(tableOkucia.getColumnModel().getTotalColumnWidth(), totalHeight));
            for (int row = 0; row < tableOkucia.getRowCount(); row++)
            {
                int rowHeight = tableOkucia.getRowHeight();

                for (int column = 0; column < tableOkucia.getColumnCount(); column++)
                {
                    int rowH = rowHeight;
                    Component comp = tableOkucia.prepareRenderer(tableOkucia.getCellRenderer(row, column), row, column);
                    if(column==0){
                    }
                    rowHeight = Math.max(rowH, comp.getPreferredSize().height);
                }
                totalHeight +=(rowHeight+0);
                tableOkucia.setRowHeight(row, rowHeight);
            }
            this.setPreferredSize(new Dimension(this.getWidth(), totalHeight));
            jScrollPane1.setPreferredSize(new Dimension(this.getWidth(), totalHeight));
            jScrollPane1.setViewportView(tableOkucia);
        }
        catch(ClassCastException e) {}
    }
    
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableOkucia = new javax.swing.JTable();

        setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableOkucia.setAutoCreateRowSorter(true);
        tableOkucia.setModel(new TableModelOkuciePozycja());
        jScrollPane1.setViewportView(tableOkucia);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableOkucia;
    // End of variables declaration//GEN-END:variables
}
