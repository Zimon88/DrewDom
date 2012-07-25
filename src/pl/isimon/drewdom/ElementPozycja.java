/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class ElementPozycja extends SQLiteConnection{
    public Element element;
    public boolean nowy;
    public int ilosc;
    
    private final static String TABLE_NAME = "mebel_elementy";
    
    private final static String TABLE_NAME_ELEMENT = "element";
    
    private final static String COL_ID = "id";
    private final static String COL_NAZWA = "nazwa";
    private final static String COL_WYM1 = "wymiar_x";
    private final static String COL_WYM2 = "wymiar_y";
    private final static String COL_WYM3 = "wymiar_z";
    private final static String COL_ZADANIE = "zadanie";
    
    private final static String COL_ILOSC = "ilosc";

    public ElementPozycja() {
        element = new Element();
    }

    void dodaj(String numer, ArrayList<ElementPozycja> elementy) {
        for(int i = 0; i<elementy.size();i++){
            connect();
            ElementPozycja o = elementy.get(i);
            int element_id=0;
            if(o.nowy){
                element_id=element.dodaj(o.element);
            } else {
                element_id = o.element.id;
            }
            String sql = "INSERT INTO "+TABLE_NAME+" VALUES ('"+numer+"',"+element_id+","+o.ilosc+");";
            int wynik;
            try {
                wynik = stmt.executeUpdate(sql);
                printSucces(sql, wynik);
            } catch (SQLException ex) {
                printSqlErr(sql);
            } finally {
                disconnect();
            }
        }
    }
    
    public ArrayList<ElementPozycja> getData(String numer, int sztuk){
        int ilosc = 1;
        if(sztuk!=0) ilosc = sztuk;
        ArrayList<ElementPozycja> lista = new ArrayList();
        String sql = "SELECT "
                + "element.id,"
                + "element.nazwa,"
                + "element.wymiar_x,"
                + "element.wymiar_y,"
                + "element.wymiar_z,"
                + "element.zadanie,"
                + "mebel_elementy.ilosc"
                + " FROM "
                + "element,"
                + "mebel,"
                + "mebel_elementy "
                + "WHERE "
                + "mebel.nr_katalogowy = '"+numer+"' "
                + "AND mebel_elementy.mebel_nr = mebel.nr_katalogowy "
                + "AND mebel_elementy.element_id = element.id;";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                ElementPozycja e = new ElementPozycja();
                wynik++;
                e.element.id = w.getInt(COL_ID);
                e.element.nazwa = w.getString(COL_NAZWA);
                e.element.wym1 = w.getInt(COL_WYM1);
                e.element.wym2 = w.getInt(COL_WYM2);
                e.element.wym3 = w.getInt(COL_WYM3);
                e.element.zadanie = w.getInt(COL_ZADANIE);
                e.ilosc  = w.getInt(COL_ILOSC)*ilosc;
                lista.add(e);
            }
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return lista;
    }
    
    
}
