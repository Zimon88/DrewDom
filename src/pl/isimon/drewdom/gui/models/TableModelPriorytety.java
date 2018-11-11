/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.Priorytety;

/**
 *
 * @author Swiercz
 */
public class TableModelPriorytety extends javax.swing.table.AbstractTableModel{
        private ArrayList<Priorytety> lista = new ArrayList();
        private final static Object[] columnNames = {"","Numer Zamowienia","Nr katalogowy", "Nazwa", "Ilość"};
        
        private final static int LP_IDX = 0;
        private final static int IDX_NR_ZAM = 1;
        private final static int IDX_NR_MEB = 2;
        private final static int IDX_NAZWA = 3;
        private final static int IDX_ILOSC = 4;
       
        /**
         * 
         */
        public TableModelPriorytety() {
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
            Priorytety o = lista.get(rowIndex);
            switch (columnIndex){
                case LP_IDX: return rowIndex+1;
                case IDX_NR_ZAM: return o.poz.nrZamowienia+":"+o.poz.pozycja;
                case IDX_NR_MEB: return o.poz.mebel.numerKatalogowy;
                case IDX_NAZWA: return o.poz.mebel.nazwa;
                case IDX_ILOSC: return o.poz.ilosc;
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
        
        public void setModelData(ArrayList<Priorytety> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public Priorytety getPriorytet(int position){
            return lista.get(position);
        }
        
        public void removePriorytet(Priorytety o){
            lista.remove(o);
            fireTableDataChanged();
        }
        
        public void addPrio(Priorytety o){
            lista.add(o);
            fireTableDataChanged();
        }

}
