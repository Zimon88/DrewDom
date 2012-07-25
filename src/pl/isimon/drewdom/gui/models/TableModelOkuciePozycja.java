/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.OkuciePozycja;

/**
 *
 * @author Swiercz
 */
public class TableModelOkuciePozycja extends javax.swing.table.AbstractTableModel{
        private ArrayList<OkuciePozycja> lista = null;
        private final static Object[] columnNames = {"L.p.","Nazwa","Sztuk"};
        
        private final static int IDX_ID = 0;
        private final static int IDX_NAZWA = 1;
        private final static int IDX_ILOSC = 2;
       
        /**
         * 
         */
        public TableModelOkuciePozycja() {
        }
        
        @Override
        public int getRowCount() {
            if(lista == null) {
                return 0;
            }
            else {
                return lista.size();
            }
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if(lista == null) {
                return null;
            }
            OkuciePozycja o = lista.get(rowIndex);
            switch (columnIndex){
                case IDX_ID: return rowIndex+1;
                case IDX_NAZWA: return o.okucie.nazwa;
                case IDX_ILOSC: return o.ilosc;
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
        
        public void setModelData(ArrayList<OkuciePozycja> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public OkuciePozycja getOkuciePozycja(int position){
            return lista.get(position);
        }
        
        public void removeOkucie(OkuciePozycja o){
            lista.remove(o);
            fireTableDataChanged();
        }
        
        

}
