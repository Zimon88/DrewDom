/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.OpakowaniePozycja;
import pl.isimon.drewdom.gui.models.TableModelOpakowanie3;
import pl.isimon.drewdom.gui.utils.TableColumnAdjuster;

/**
 *
 * @author Simon
 */
public class PanelCellRaportOpakowania3 extends javax.swing.JPanel {

    ArrayList<OpakowaniePozycja> lista;
    TableModelOpakowanie3 model;
    TableColumnAdjuster tca;
    /**
     * Creates new form PanelCellRaportSElementy
     */
    public PanelCellRaportOpakowania3() {
        initComponents();
        model = (TableModelOpakowanie3) tableElementy.getModel();
        tableElementy.getTableHeader().setEnabled(false);
        tca = new TableColumnAdjuster(tableElementy);
        tca.setOnlyAdjustLarger(false);
        tableElementy.getColumnModel().getColumn(1).setPreferredWidth(200);
    }

    public void setLista(ArrayList<OpakowaniePozycja> lista) {
        this.lista = lista;
        model.setModelData(lista);
        updateRowHeights();
        tca.adjustColumn(0);
        
    }
    
    private void updateRowHeights()
    {
        try
        {
            int totalHeight = 16;
            tableElementy.getTableHeader().setPreferredSize(new Dimension(tableElementy.getColumnModel().getTotalColumnWidth(), totalHeight));
            for (int row = 0; row < tableElementy.getRowCount(); row++)
            {
                int rowHeight = tableElementy.getRowHeight();

                for (int column = 0; column < tableElementy.getColumnCount(); column++)
                {
                    int rowH = rowHeight;
                    Component comp = tableElementy.prepareRenderer(tableElementy.getCellRenderer(row, column), row, column);
                    if(column==0){
                    }
                    rowHeight = Math.max(rowH, comp.getPreferredSize().height);
                }
                totalHeight +=(rowHeight+0);
                tableElementy.setRowHeight(row, rowHeight);
            }
            this.setPreferredSize(new Dimension(this.getWidth(), totalHeight));
            jScrollPane1.setPreferredSize(new Dimension(this.getWidth(), totalHeight));
            jScrollPane1.setViewportView(tableElementy);
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
        tableElementy = new javax.swing.JTable();

        setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableElementy.setAutoCreateRowSorter(true);
        tableElementy.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tableElementy.setModel(new TableModelOpakowanie3());
        jScrollPane1.setViewportView(tableElementy);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableElementy;
    // End of variables declaration//GEN-END:variables
}
