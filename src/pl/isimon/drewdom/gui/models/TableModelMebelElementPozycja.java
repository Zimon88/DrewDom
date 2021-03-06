/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import pl.isimon.drewdom.ElementPozycja;

/**
 *
 * @author Swiercz
 */
public class TableModelMebelElementPozycja extends javax.swing.table.AbstractTableModel{
    // TODO all
    private ArrayList<ElementPozycja> lista = null;
        private final static Object[] columnNames = {"","Nazwa","Wymiar","Ilość","Zadanie","Zadanie","Wydajnosc"};
        
        private final static int LP_IDX = 0;
        private final static int IDX_NAZWA = 1;
        private final static int IDX_WYMIAR = 2;
        private final static int IDX_ILOSC = 3;
        private final static int IDX_WYDAJNOSC = 6;
        private final static int IDX_ZADANIE = 4;
        private final static int IDX_NEW = 5;
       
        /**
         * 
         */
        public TableModelMebelElementPozycja() {
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
            ElementPozycja o = lista.get(rowIndex);
            switch (columnIndex){
                case LP_IDX: return rowIndex+1;
                case IDX_NAZWA: return o.element.nazwa;
                case IDX_WYMIAR: return o.element.wym1+"x"+o.element.wym2+"x"+o.element.wym3;
                case IDX_ILOSC: return o.ilosc;
                case IDX_WYDAJNOSC: return o.element.wydajnosc;
                case IDX_ZADANIE: return o.element.zadanie;
                case IDX_NEW: return o.nowy;
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
        
        public void setModelData(ArrayList<ElementPozycja> pozycje){
            this.lista = pozycje;
            fireTableDataChanged();
        }
        
        public ElementPozycja getElementPozycja(int position){
            return lista.get(position);
        }
        
        public void addElementPozycja(ElementPozycja o){
            this.lista.add(o);
            fireTableDataChanged();
        }
        
        public void removeElementPozycja(ElementPozycja o){
            this.lista.remove(o);
            fireTableDataChanged();
        }
        

}
