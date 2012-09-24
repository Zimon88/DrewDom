/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.Mebel;

/**
 *
 * @author Swiercz
 */
public class TableModelMebleLista extends javax.swing.table.AbstractTableModel{
        private ArrayList<Mebel> lista = null;
        private final static Object[] columnNames = {"Lp","Numer","Nazwa","Kod"};
        
        private final static int IDX_LP = 0;
        private final static int IDX_NUMER = 1;
        private final static int IDX_NAZWA = 2;
        private final static int IDX_KOD = 3;
        /**
         * 
         */
        public TableModelMebleLista() {
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
            Mebel o = lista.get(rowIndex);
            switch (columnIndex){
                case IDX_LP: return rowIndex + 1;
                case IDX_NUMER: return o.numerKatalogowy;
                case IDX_NAZWA: return o.nazwa;
                case IDX_KOD: return o.kod;
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
        
        public void setModelData(ArrayList<Mebel> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public Mebel getMebel(int position){
            return lista.get(position);
        }
        
        public void addMebel(Mebel o){
            this.lista.add(o);
            fireTableDataChanged();
        }

}
