/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.raports.RaportOpakowania;
import pl.isimon.drewdom.raports.RaportOpakowania3;

/**
 *
 * @author Swiercz
 */
public class TableModelRaportOpakowania3 extends javax.swing.table.AbstractTableModel{
    private ArrayList<RaportOpakowania3> lista = null;
        private final static Object[] columnNames = {"LP","Nazwa","Numer","Opakowanie"};
        
        private final static int IDX_LP = 0;
        private final static int IDX_MEBEL_NAZWA = 1;
        private final static int IDX_MEBEL_NUMER = 2;
        private final static int IDX_ELEMENTY_LISTA = 3;
       
        /**
         * 
         */
        public TableModelRaportOpakowania3() {
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
            RaportOpakowania3 o = lista.get(rowIndex);
            switch (columnIndex){
                case IDX_LP: return rowIndex+1;
                case IDX_MEBEL_NAZWA: return o.pozycjaZamowienia.mebel.nazwa;
                case IDX_MEBEL_NUMER: return o.pozycjaZamowienia.mebel.numerKatalogowy;
                case IDX_ELEMENTY_LISTA: return o.opakowanieLista;
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
        
        public void setModelData(ArrayList<RaportOpakowania3> pozycje){
            this.lista = pozycje;
            Collections.sort(this.lista, new Sort());
            fireTableDataChanged();
        }
        public ArrayList<RaportOpakowania3> getModelData(){
            return this.lista;
        }
        
        public RaportOpakowania3 getRaportSzczegolowy(int position){
            return lista.get(position);
        }
        
        public void addRaportSzczegolowy(RaportOpakowania3 o){
            this.lista.add(o);
            fireTableDataChanged();
        }
        
        public void removeRaportSzczegolowy(RaportOpakowania o){
            this.lista.remove(o);
            fireTableDataChanged();
        }

    
        
private class Sort implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        RaportOpakowania3 e1 = (RaportOpakowania3) o1;
        RaportOpakowania3 e2 = (RaportOpakowania3) o2;
        return e1.pozycjaZamowienia.mebel.numerKatalogowy.compareTo(e2.pozycjaZamowienia.mebel.numerKatalogowy);
    }
    
}

}
