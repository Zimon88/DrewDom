/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.Pracownik;

/**
 *
 * @author Swiercz
 */
public class TableModelPracownik extends javax.swing.table.AbstractTableModel{
        private ArrayList<Pracownik> lista = null;
        private final static Object[] columnNames = {"id","Imię","Nazwisko"};
        
        private final static int LP_IDX = 0;
        private final static int IDX_IMIE = 1;
        private final static int IDX_NAZWISKO = 2;
       
        /**
         * 
         */
        public TableModelPracownik() {
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
            Pracownik o = lista.get(rowIndex);
            switch (columnIndex){
                case LP_IDX: return o.id;
                case IDX_IMIE: return o.imie;
                case IDX_NAZWISKO: return o.nazwisko;
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
        
        public void setModelData(ArrayList<Pracownik> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public Pracownik getPracownik(int position){
            return lista.get(position);
        }
        
        public void removePracownik(Pracownik p){
            this.lista.remove(p);
            fireTableDataChanged();
        }
        
        public void removePracownik(int position){
            this.lista.remove(position);
            fireTableDataChanged();
        }
        

}
