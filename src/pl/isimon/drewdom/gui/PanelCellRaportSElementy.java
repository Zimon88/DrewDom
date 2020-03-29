/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.gui.models.TableModelMebelElementPozycjaRS;
import pl.isimon.drewdom.gui.utils.ImageRenderer;
import pl.isimon.drewdom.gui.utils.TableColumnAdjuster;
import pl.isimon.utils.PropertiesU;

/**
 *
 * @author Simon
 */
public class PanelCellRaportSElementy extends javax.swing.JPanel {

    ArrayList<ElementPozycja> lista;
    TableModelMebelElementPozycjaRS model;
    TableColumnAdjuster tca;
    Properties properties;
    boolean resized = false;
    boolean renderqr = false;
    /**
     * Creates new form PanelCellRaportSElementy
     */
    public PanelCellRaportSElementy() {
        initComponents();
        model = (TableModelMebelElementPozycjaRS) tableElementy.getModel();
        tca = new TableColumnAdjuster(tableElementy);
        PropertiesU prop = new PropertiesU();
        prop.loadProperties();
        properties = prop.getProperties();
    }
    public void setLista(ArrayList<ElementPozycja> lista) {
        this.setLista(lista, false);
    }

    public void setLista(ArrayList<ElementPozycja> lista, boolean renderQR) {
        this.lista = lista;
        this.renderqr = renderQR;
        Collections.sort(this.lista, new ElementComparator());
        model.setModelData(lista);
        updateRowHeights();
        updateColumWidth(renderQR);
    }
    
    public void sortByName(){
        tableElementy.getRowSorter().toggleSortOrder(0);
    }
    
    private void updateColumWidth(boolean renderQR){
//        tableElementy.getColumnModel().getColumn(2).setMinWidth(10);
        tableElementy.getColumnModel().getColumn(0).setMinWidth(100);
        tableElementy.getColumnModel().getColumn(0).setMaxWidth(250);
        tableElementy.getColumnModel().getColumn(0).setPreferredWidth(Integer.parseInt(properties.getProperty("column1")));
        tableElementy.getColumnModel().getColumn(1).setMinWidth(100);
        tableElementy.getColumnModel().getColumn(1).setMaxWidth(250);        
        tableElementy.getColumnModel().getColumn(1).setPreferredWidth(Integer.parseInt(properties.getProperty("column2")));
        tableElementy.getColumnModel().getColumn(2).setMinWidth(50);
        tableElementy.getColumnModel().getColumn(2).setMaxWidth(100);
        tableElementy.getColumnModel().getColumn(2).setPreferredWidth(Integer.parseInt(properties.getProperty("column3")));
        tableElementy.getColumnModel().getColumn(4).setMinWidth(400);
        tableElementy.getColumnModel().getColumn(2).setPreferredWidth(500);
        int qrW = 0;
        if (renderQR) {
            tableElementy.getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer());
            tableElementy.getColumnModel().getColumn(3).setMinWidth(120);
            tableElementy.getColumnModel().getColumn(3).setMaxWidth(180);
            qrW = Integer.parseInt(properties.getProperty("column_qr"));
        } else {
            tableElementy.getColumnModel().getColumn(3).setMaxWidth(0);
            tableElementy.getColumnModel().getColumn(3).setMinWidth(0);
        }
        System.out.println("DEBUG: setting qr width to: " + qrW);
        tableElementy.getColumnModel().getColumn(3).setPreferredWidth(qrW);
    }
    
    private void updateRowHeights()
    {
        try
        {
            int totalHeight = 18;
            tableElementy.getTableHeader().setPreferredSize(new Dimension(tableElementy.getColumnModel().getTotalColumnWidth(), totalHeight));
            for (int row = 0; row < tableElementy.getRowCount(); row++)
            {
                
                int rowHeight = Integer.parseInt(properties.getProperty("height"));
                rowHeight = renderqr ? Integer.parseInt(properties.getProperty("qr_image_size")) : rowHeight;
                if(!resized) {
                    rowHeight = Integer.parseInt(properties.getProperty("height"));
                    rowHeight = renderqr ? Integer.parseInt(properties.getProperty("qr_image_size")) : rowHeight;
                }
                for (int column = 0; column < tableElementy.getColumnCount(); column++)
                {
                    int rowH = rowHeight;
                    Component comp = tableElementy.prepareRenderer(tableElementy.getCellRenderer(row, column), row, column);
                   
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

    private class ElementComparator implements Comparator<ElementPozycja> {
        @Override
        public int compare(ElementPozycja o1, ElementPozycja o2) {
            return o1.element.nazwa.compareTo(o2.element.nazwa);
        }
    }
}
