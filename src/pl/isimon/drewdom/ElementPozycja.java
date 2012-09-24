/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTextField;

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
    private final static String COL_MEBELNR = "mebel_nr";
    
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

    public void dodaj(String numer, ArrayList<ElementPozycja> elementy) {
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
    
    public void dodaj(String numer, ElementPozycja o) {
            connect();
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
    
    public ArrayList<ElementPozycja> getData(String nrZamowienia, String numer, int sztuk){
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
                + "zamowienie,"
                + "pozycja_zamowienia,"
                + "element,"
                + "mebel,"
                + "mebel_elementy "
                + "WHERE "
                + "mebel.nr_katalogowy = '"+numer+"' "
                + "AND mebel_elementy.mebel_nr = mebel.nr_katalogowy "
                + "AND mebel_elementy.element_id = element.id "
                + "AND mebel_elementy.mebel_nr = pozycja_zamowienia.mebel_nr "
                + "AND pozycja_zamowienia.nr_zamowienia = zamowienie.nr_zamowienia "
                + "AND zamowienie.nr_zamowienia = '"+nrZamowienia+"'"
                + "GROUP BY element.id;";
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
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return lista;
    }

    void usun(String numerKatalogowy) {
        connect();
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_MEBELNR+" = '"+numerKatalogowy+"';";
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

    public void edytuj(String textKod, ElementPozycja ep) {
        connect();
        String sql = "UPDATE "+TABLE_NAME+" "
                + "SET "+COL_ILOSC+" = "+ep.ilosc+" "
                + "WHERE "+COL_MEBELNR+" = '"+textKod+"' AND element_id="+ep.element.id+";";
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
            element.edytuj(ep.element);
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            disconnect();
        }
    }

    public void edytuj(String numer, ArrayList<ElementPozycja> elementyLista) {
        
        for(int i = 0;i<elementyLista.size();i++){
            ElementPozycja o = elementyLista.get(i);
            int element_id=0;
            if(o.nowy){
                connect();
                element_id=element.dodaj(o.element);
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
            } else {
                connect();
//            String sql = "INSERT INTO "+TABLE_NAME+" VALUES ('"+numer+"',"+element_id+","+o.ilosc+");";
                String sql = "UPDATE "+TABLE_NAME+ " SET "
                        + COL_ILOSC + " = "+o.ilosc+ " "
                        + "WHERE "+COL_MEBELNR+" = '"+numer+"' AND element_id="+o.element.id+";";
                int wynik = 0;
                try {
                    System.out.print(sql);
                    wynik = stmt.executeUpdate(sql);
                    printSucces(sql, wynik);
                } catch (SQLException ex) {
                    printSqlErr(sql);
                } finally {
                    disconnect();
                }
            }
        }
    }

    public void usun(ElementPozycja ep) {
        connect();
        
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "
                + COL_MEBELNR+" = '"+ep.element.mebel.kod+"' AND"
                + "element_id = "+ ep.element.id +";";
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
    
     public void usun(ElementPozycja ep, String numer) {
        connect();
        
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "
                + COL_MEBELNR+" = '"+numer+"' AND "
                + "element_id = "+ ep.element.id +";";
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

    public ArrayList<ElementPozycja> getData(String numer, int sztuk) {
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
                + "zamowienie,"
                + "pozycja_zamowienia,"
                + "element,"
                + "mebel,"
                + "mebel_elementy "
                + "WHERE "
                + "mebel.nr_katalogowy = '"+numer+"' "
                + "AND mebel_elementy.mebel_nr = mebel.nr_katalogowy "
                + "AND mebel_elementy.element_id = element.id "
                + "GROUP BY element.id;";
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
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return lista;
    }

}
