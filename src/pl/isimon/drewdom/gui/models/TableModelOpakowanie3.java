/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.OpakowaniePozycja;

/**
 *
 * @author Swiercz
 */
public class TableModelOpakowanie3 extends javax.swing.table.AbstractTableModel{
        private ArrayList<OpakowaniePozycja> lista = null;
        private final static Object[] columnNames = {"L.p.","Wymiar","sztuk"};
//        private final static Object[] columnNames = {"",""};
        
        private final static int IDX_ID = 0;
        private final static int IDX_WYMIAR = 1;
        private final static int IDX_SZTUK = 2;
        /**
         * 
         */
        public TableModelOpakowanie3() {
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
            OpakowaniePozycja op = lista.get(rowIndex);
            Opakowanie o = op.opakowanie;
            switch (columnIndex){
                case IDX_ID: return rowIndex+1;
                case IDX_WYMIAR: return o.wymiar_x+"x"+o.wymiar_y+"x"+o.wymiar_z;
                case IDX_SZTUK: return op.sztuk;
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
        
        public void setModelData(ArrayList<OpakowaniePozycja> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public OpakowaniePozycja getOpakowanie(int position){
            return lista.get(position);
        }
        

}
