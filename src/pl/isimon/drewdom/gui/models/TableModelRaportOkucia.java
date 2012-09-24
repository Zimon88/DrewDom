/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.raports.RaportOkucia;
import pl.isimon.drewdom.raports.RaportOpakowania;

/**
 *
 * @author Swiercz
 */
public class TableModelRaportOkucia extends javax.swing.table.AbstractTableModel{
    private ArrayList<RaportOkucia> lista = null;
        private final static Object[] columnNames = {"LP","Nazwa","Numer","Szt","Okucie"};
        
        private final static int IDX_LP = 0;
        private final static int IDX_MEBEL_NAZWA = 1;
        private final static int IDX_MEBEL_NUMER = 2;
        private final static int IDX_MEBEL_SZTUK = 3;
        private final static int IDX_ELEMENTY_LISTA = 4;
       
        /**
         * 
         */
        public TableModelRaportOkucia() {
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
            RaportOkucia o = lista.get(rowIndex);
            switch (columnIndex){
                case IDX_LP: return rowIndex+1;
                case IDX_MEBEL_NAZWA: return o.pozycjaZamowienia.mebel.nazwa;
                case IDX_MEBEL_NUMER: return o.pozycjaZamowienia.mebel.numerKatalogowy;
                case IDX_MEBEL_SZTUK: return o.pozycjaZamowienia.ilosc;
                case IDX_ELEMENTY_LISTA: return o.listaOkuc;
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
        
        public void setModelData(ArrayList<RaportOkucia> pozycje){
            this.lista = pozycje;
            Collections.sort(this.lista, new Sort());
            fireTableDataChanged();
        }
        
        public RaportOkucia getRaportSzczegolowy(int position){
            return lista.get(position);
        }
        
        public void addRaportSzczegolowy(RaportOkucia o){
            this.lista.add(o);
            fireTableDataChanged();
        }
        
        public void removeRaportSzczegolowy(RaportOkucia o){
            this.lista.remove(o);
            fireTableDataChanged();
        }
        
private class Sort implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        RaportOkucia e1 = (RaportOkucia) o1;
        RaportOkucia e2 = (RaportOkucia) o2;
        return e1.pozycjaZamowienia.mebel.numerKatalogowy.compareTo(e2.pozycjaZamowienia.mebel.numerKatalogowy);
    }
    
}

}
