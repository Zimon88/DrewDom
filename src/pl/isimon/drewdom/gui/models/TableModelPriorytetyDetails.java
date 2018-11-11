/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.Priorytety;

/**
 *
 * @author Swiercz
 */
public class TableModelPriorytetyDetails extends javax.swing.table.AbstractTableModel{
    // TODO all
    private ArrayList<Priorytety> lista = null;
    private final static Object[] columnNames = {"","","",""};

    private final static int IDX_LP = 0;
    private final static int IDX_ZAM_DET = 1;
    private final static int IDX_MEBEL_DANE = 2;
    private final static int IDX_ELEMENTY_LISTA = 3;

    /**
     * 
     */
    public TableModelPriorytetyDetails() {
    }

    @Override
    public int getRowCount() {
        if(lista == null) return 0;
        else return lista.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(lista == null) return null;
        Priorytety o = lista.get(rowIndex);
        switch (columnIndex){
            case IDX_LP: {
               return rowIndex+1;
            }
            case IDX_ZAM_DET: return o.poz.nrZamowienia+":"+o.poz.pozycja;
            case IDX_MEBEL_DANE: return o.poz.toString();
            case IDX_ELEMENTY_LISTA: return o.poz.mebel.lista;
            default:
                return o;
        }
    }

    @Override
    public String getColumnName(int column){
        return columnNames[column].toString();
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }

    public void setModelData(ArrayList<Priorytety> pozycje){
        this.lista = pozycje;
        fireTableDataChanged();
    }


}
