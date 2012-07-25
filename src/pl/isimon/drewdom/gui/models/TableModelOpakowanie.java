/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.Opakowanie;

/**
 *
 * @author Swiercz
 */
public class TableModelOpakowanie extends javax.swing.table.AbstractTableModel{
        private ArrayList<Opakowanie> lista = null;
        private final static Object[] columnNames = {"L.p.","Wymiar 1","Wymiar 2", "Wymiar 3"};
        
        private final static int IDX_ID = 0;
        private final static int IDX_WYMIAR = 1;
        private final static int IDX_WYMIAR2 = 2;
        private final static int IDX_WYMIAR3 = 3;
       
        /**
         * 
         */
        public TableModelOpakowanie() {
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
            Opakowanie o = lista.get(rowIndex);
            switch (columnIndex){
                case IDX_ID: return rowIndex+1;
//                case IDX_WYMIAR: return o.wymiar_x+"x"+o.wymiar_y+"x"+o.wymiar_z;
                case IDX_WYMIAR: return o.wymiar_x;
                case IDX_WYMIAR2: return o.wymiar_y;
                case IDX_WYMIAR3:return o.wymiar_z;
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
        
        public void setModelData(ArrayList<Opakowanie> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public Opakowanie getOpakowanie(int position){
            return lista.get(position);
        }

}
