/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class TableModelMebelElementPozycjaRS extends javax.swing.table.AbstractTableModel{
    // TODO all
    private ArrayList<ElementPozycja> lista = null;
        private final static Object[] columnNames = {"Element","Wymiar","Szt","QR","Notatki"};
        
        private final static int IDX_NAZWA = 0;
        private final static int IDX_WYMIAR = 1;
        private final static int IDX_ILOSC = 2;
        private final static int IDX_QR = 3;
        private final static int IDX_NOTATKI = 4;
//        private final static int IDX_ZADANIE_K = 3;
//        private final static int IDX_ZADANIE_P = 4;
//        private final static int IDX_ZADANIE_C = 5;
//        private final static String IKS = "XXXXXXXXXXXXXXXXXXXXX";
        
        private boolean hideQR = false;
       
        /**
         * 
         */
        public TableModelMebelElementPozycjaRS() {
        }
        public TableModelMebelElementPozycjaRS(boolean qr) {
            this.hideQR = qr;
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
            int x = o.element.zadanie;
            switch (columnIndex){
                case IDX_NAZWA: {
                    if(o.element.wydajnosc>1) return o.element.nazwa+" ("+o.element.wydajnosc+" z 1)";
                    return o.element.nazwa;
                }
                case IDX_WYMIAR: return o.element.wym1+"x"+o.element.wym2+"x"+o.element.wym3;
                case IDX_ILOSC: return o.ilosc;
                case IDX_QR: return o.element.getQRImagesPaths();
                case IDX_NOTATKI: return "";
//                case IDX_ZADANIE_K: if(x == 2 | x == 4 | x==6) return IKS; return "";
//                case IDX_ZADANIE_P: if(x == 1 | x == 4 | x==5) return IKS; return "";
//                case IDX_ZADANIE_C: if(x == 1 | x == 2 | x==3) return IKS; return "";
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
            Collections.sort(this.lista, new Sort());
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
        
private class Sort implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            ElementPozycja e1 = (ElementPozycja) o1;
            ElementPozycja e2 = (ElementPozycja) o2;
            return e1.element.nazwa.compareTo(e2.element.nazwa);
        }
    
}
}
