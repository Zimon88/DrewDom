/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.Zamowienie;

/**
 *
 * @author Swiercz
 */
public class TableModelZamowienie extends javax.swing.table.AbstractTableModel{
        private ArrayList<Zamowienie> lista = null;
        private final static Object[] columnNames = {"","Numer","Data wpływu","Data Realizacji"};
        
        private final static int LP_IDX = 0;
        private final static int IDX_NUMER = 1;
        private final static int IDX_DATA = 2;
        private final static int IDX_DATA_REALIZACJI = 3;
       
        /**
         * 
         */
        public TableModelZamowienie() {
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
            Zamowienie o = lista.get(rowIndex);
            switch (columnIndex){
                case LP_IDX: return rowIndex+1;
                case IDX_NUMER: return o.numer;
                case IDX_DATA: return o.data;
                case IDX_DATA_REALIZACJI: return o.dataRealizacji;
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
        
        public void setModelData(ArrayList<Zamowienie> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public Zamowienie getZamowienie(int position){
            return lista.get(position);
        }
        
        public void removeZamowienie(Zamowienie o){
            lista.remove(o);
            fireTableDataChanged();
        }

}
