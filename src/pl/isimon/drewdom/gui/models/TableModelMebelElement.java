/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import pl.isimon.drewdom.Element;

/**
 *
 * @author Swiercz
 */
public class TableModelMebelElement extends javax.swing.table.AbstractTableModel{
    // TODO all
    private ArrayList<Element> lista = null;
        private final static Object[] columnNames = {"","Nr Mebla","Nazwa Mebla","Nazwa","Wymiar","Zadanie"};
        
        private final static int LP_IDX = 0;
        private final static int IDX_NR_MEBLA = 1;
        private final static int IDX_NAZWA_MEBLA = 2;
        private final static int IDX_NAZWA = 3;
        private final static int IDX_WYMIAR = 4;
        private final static int IDX_ZADANIE = 5;
       
        /**
         * 
         */
        public TableModelMebelElement() {
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
            Element o = lista.get(rowIndex);
            switch (columnIndex){
                case LP_IDX: return rowIndex+1;
                case IDX_NAZWA: return o.nazwa;
                case IDX_NAZWA_MEBLA: return o.mebel.nazwa;
                case IDX_NR_MEBLA: return o.mebel.numerKatalogowy;
                case IDX_WYMIAR: return o.wym1+"x"+o.wym2+"x"+o.wym3;
                case IDX_ZADANIE: return o.zadanie;
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
        
        public void setModelData(ArrayList<Element> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public Element getElement(int position){
            return lista.get(position);
        }
        
        public void addElement(Element o){
            this.lista.add(o);
            fireTableDataChanged();
        }
        
        public void removeElement(Element o){
            this.lista.remove(o);
            fireTableDataChanged();
        }

}
