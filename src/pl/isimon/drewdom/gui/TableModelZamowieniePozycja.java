/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import java.util.ArrayList;
import pl.isimon.drewdom.ZamowieniePozycja;

/**
 *
 * @author Swiercz
 */
public class TableModelZamowieniePozycja extends javax.swing.table.AbstractTableModel{
    // TODO all
    private ArrayList<ZamowieniePozycja> lista = null;
        private final static Object[] columnNames = {"","Numer katalogowy","Nazwa","Ilość"};
        
        private final static int LP_IDX = 0;
        private final static int IDX_NUMER = 1;
        private final static int IDX_NAZWA = 2;
        private final static int IDX_ILOSC = 3;
       
        /**
         * 
         */
        public TableModelZamowieniePozycja() {
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
            ZamowieniePozycja o = lista.get(rowIndex);
            switch (columnIndex){
                case LP_IDX: return rowIndex+1;
                case IDX_NUMER: return o.mebel.numerKatalogowy;
                case IDX_NAZWA: return o.mebel.nazwa;
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
        
        public void setModelData(ArrayList<ZamowieniePozycja> pozycje){
            this.lista = pozycje;
        }
        
        public ZamowieniePozycja getZamowienie(int position){
            return lista.get(position);
        }

}
