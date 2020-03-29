/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.raports.RaportSzczegolowy;

/**
 *
 * @author Swiercz
 */
public class TableModelRaportSzczegolwy extends javax.swing.table.AbstractTableModel{
    // TODO all
    private ArrayList<RaportSzczegolowy> lista = null;
        private final static Object[] columnNames = {"","",""};
        
        private final static int IDX_LP = 0;
        private final static int IDX_MEBEL_DANE = 1;
        private final static int IDX_ELEMENTY_LISTA = 2;
       
        /**
         * 
         */
        public TableModelRaportSzczegolwy() {
        }
        
        public TableModelRaportSzczegolwy(boolean a) {
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
            RaportSzczegolowy o = lista.get(rowIndex);
            switch (columnIndex){
                case IDX_LP: {
                    if (o.pozycjaZamowienia.pozycja == 0 ) return rowIndex+1;
                    else return o.pozycjaZamowienia.pozycja;
                }
                case IDX_MEBEL_DANE: return o.pozycjaZamowienia.toString();
                case IDX_ELEMENTY_LISTA: return o.listaElementow;
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
        
        public void setModelData(ArrayList<RaportSzczegolowy> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public RaportSzczegolowy getRaportSzczegolowy(int position){
            return lista.get(position);
        }
        
        public void addRaportSzczegolowy(RaportSzczegolowy o){
            this.lista.add(o);
            fireTableDataChanged();
        }
        
        public void removeRaportSzczegolowy(RaportSzczegolowy o){
            this.lista.remove(o);
            fireTableDataChanged();
        }

}
