/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.Okucie;

/**
 *
 * @author Swiercz
 */
public class TableModelOkucie extends javax.swing.table.AbstractTableModel{
        private ArrayList<Okucie> lista = null;
        private final static Object[] columnNames = {"L.p.","Nazwa",};
        
        private final static int IDX_ID = 0;
        private final static int IDX_NAZWA = 1;
       
        /**
         * 
         */
        public TableModelOkucie() {
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
            Okucie o = lista.get(rowIndex);
            switch (columnIndex){
                case IDX_ID: return rowIndex+1;
                case IDX_NAZWA: return o.nazwa;
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
        
        public void setModelData(ArrayList<Okucie> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public Okucie getOkucie(int position){
            return lista.get(position);
        }
        
        public void removeOkucie(Okucie o){
            lista.remove(o);
            fireTableDataChanged();
        }
        
        

}
