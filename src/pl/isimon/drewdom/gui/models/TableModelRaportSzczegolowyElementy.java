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
public class TableModelRaportSzczegolowyElementy extends javax.swing.table.AbstractTableModel{
        private ArrayList<Zamowienie> lista = null;
        private final static Object[] columnNames = {"Element","Wymiar","Sztuk","Klejenie","Piła","CnC"};
        
        private final static int IDX_ELEMENT = 1;
        private final static int IDX_WYMIAR = 2;
        private final static int IDX_ILOSC = 3;
        private final static int IDX_KLEJ = 4;
        private final static int IDX_PILA = 5;
        private final static int IDX_CNC = 6;
       
        /**
         * 
         */
        public TableModelRaportSzczegolowyElementy() {
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
//            if(column==1) return true;
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
