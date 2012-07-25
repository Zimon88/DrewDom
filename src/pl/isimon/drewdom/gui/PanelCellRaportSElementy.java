/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.table.TableColumn;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.gui.models.TableModelMebelElementPozycjaRS;
import pl.isimon.drewdom.gui.utils.TableColumnAdjuster;

/**
 *
 * @author Simon
 */
public class PanelCellRaportSElementy extends javax.swing.JPanel {

    ArrayList<ElementPozycja> lista;
    TableModelMebelElementPozycjaRS model;
    TableColumnAdjuster tca;
    /**
     * Creates new form PanelCellRaportSElementy
     */
    public PanelCellRaportSElementy() {
        initComponents();
        model = (TableModelMebelElementPozycjaRS) tableElementy.getModel();
        tca = new TableColumnAdjuster(tableElementy);
        
    }

    public void setLista(ArrayList<ElementPozycja> lista) {
        this.lista = lista;
        model.setModelData(lista);
        updateRowHeights();
        tca.adjustColumn(0);
        tca.adjustColumn(1);
        tca.adjustColumn(2);
    }
    
    private void updateRowHeights()
    {
        try
        {
            int totalHeight = 20;
//            int headerHeight;
            tableElementy.getTableHeader().setPreferredSize(new Dimension(tableElementy.getColumnModel().getTotalColumnWidth(), totalHeight));
//            totalHeight += headerHeight;
            for (int row = 0; row < tableElementy.getRowCount(); row++)
            {
                int rowHeight = tableElementy.getRowHeight();

                for (int column = 0; column < tableElementy.getColumnCount(); column++)
                {
                    int rowH = rowHeight;
                    Component comp = tableElementy.prepareRenderer(tableElementy.getCellRenderer(row, column), row, column);
                    if(column==0){
//                        rowH *=3;
                    }
                    rowHeight = Math.max(rowH, comp.getPreferredSize().height);
                }
                totalHeight +=(rowHeight+0);
                tableElementy.setRowHeight(row, rowHeight);
            }
            this.setPreferredSize(new Dimension(this.getWidth(), totalHeight));
            jScrollPane1.setPreferredSize(new Dimension(this.getWidth(), totalHeight));
//            tableElementy.setPreferredSize(new Dimension (this.getWidth(), totalHeight));
            jScrollPane1.setViewportView(tableElementy);
//            this.repaint();
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

        setLayout(new java.awt.GridLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        tableElementy.setModel(new TableModelMebelElementPozycjaRS());
        jScrollPane1.setViewportView(tableElementy);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableElementy;
    // End of variables declaration//GEN-END:variables
}