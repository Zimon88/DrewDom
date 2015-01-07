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
public class Element extends SQLiteConnection{
    public int id;
    public String nazwa;
    public int wym1;
    public int wym2;
    public int wym3;
    public int zadanie;
    public int wydajnosc;
    public Mebel mebel = null;
    
    private final static String TABLE_NAME = "element";
    
    private final static String COL_ID = "id";
    private final static String COL_NAZWA = "nazwa";
    private final static String COL_WYM1 = "wymiar_x";
    private final static String COL_WYM2 = "wymiar_y";
    private final static String COL_WYM3 = "wymiar_z";
    private final static String COL_ZADANIE = "zadanie";
    private final static String COL_WYDAJNOSC = "wydajnosc";
    
    private final static String COL_MEBEL_KOD = "mebel_numer";
    private final static String COL_MEBEL_NAZWA = "mebel_nazwa";
    
    public Element(){
//        if(mebel == null) mebel = new Mebel();
    }
    public Element(Mebel mebel){
        this.mebel = mebel;
    }
    
    public ArrayList<Element> getData(String nazwaCzesci, String nazwaMebla, String numerMebla) {
        ArrayList<Element> lista = new ArrayList();
        String sql = "SELECT "
                + "element.id,"
                + "element.nazwa,"
                + "element.wymiar_x,"
                + "element.wymiar_y,"
                + "element.wymiar_z,"
                + "element.zadanie,"
                + "element.wydajnosc,"
                + "mebel.nr_katalogowy as mebel_numer,"
                + "mebel.nazwa as mebel_nazwa "
                + " FROM "
                + "element,"
                + "mebel,"
                + "mebel_elementy "
                + "WHERE "
                + "mebel_elementy.mebel_nr = mebel.nr_katalogowy "
                + "AND mebel_elementy.element_id = element.id ";
//        sql = "kod LIKE '%"+prod.kod+"%'" +sql;
        if(nazwaCzesci!=null & nazwaCzesci!=""){
            sql += "AND element.nazwa LIKE '%"+nazwaCzesci+"%' ";
        }
        if(nazwaMebla!=null & nazwaMebla!=""){
            sql += "AND mebel.nazwa LIKE '%"+nazwaMebla+"%' ";
        }
        if(numerMebla!=null & numerMebla!=""){
            sql += "AND mebel.nr_katalogowy LIKE '%"+numerMebla+"%' ";
        }
        sql += ";";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Element e = new Element(new Mebel());
                wynik++;
                e.id = w.getInt(COL_ID);
                e.nazwa = w.getString(COL_NAZWA);
                e.wym1 = w.getInt(COL_WYM1);
                e.wym2 = w.getInt(COL_WYM2);
                e.wym3 = w.getInt(COL_WYM3);
                e.zadanie = w.getInt(COL_ZADANIE);
                e.wydajnosc = w.getInt(COL_WYDAJNOSC);
                e.mebel.numerKatalogowy = w.getString(COL_MEBEL_KOD);
                e.mebel.nazwa = w.getString(COL_MEBEL_NAZWA);
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
    
    public ArrayList<Element> getData(){
        ArrayList<Element> lista = new ArrayList();
        String sql = "SELECT element.id,element.nazwa,element.wymiar_x,element.wymiar_y,element.wymiar_z,element.zadanie,mebel.nr_katalogowy as mebel_numer,mebel.nazwa as mebel_nazwa "
                + " FROM element,mebel,mebel_elementy WHERE mebel_elementy.mebel_nr = mebel.nr_katalogowy AND mebel_elementy.element_id = element.id;";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Element e = new Element(new Mebel());
                wynik++;
                e.id = w.getInt(COL_ID);
                e.nazwa = w.getString(COL_NAZWA);
                e.wym1 = w.getInt(COL_WYM1);
                e.wym2 = w.getInt(COL_WYM2);
                e.wym3 = w.getInt(COL_WYM3);
                e.zadanie = w.getInt(COL_ZADANIE);
                e.wydajnosc = w.getInt(COL_WYDAJNOSC);
                e.mebel.numerKatalogowy = w.getString(COL_MEBEL_KOD);
                e.mebel.nazwa = w.getString(COL_MEBEL_NAZWA);
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
    
    public int dodaj(Element e){
        int last_id = 0;
        connect();
        String sql = "INSERT INTO "+TABLE_NAME+" VALUES (null,'"+e.nazwa+"',"+e.wym1+","+e.wym2+","+e.wym3+",'',"+e.zadanie+","+e.wydajnosc+");";
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            disconnect();
        }
        sql = "SELECT id AS last_id FROM "+TABLE_NAME+" ORDER BY id DESC limit 1;";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            wynik =0;
            while(w.next()){
                wynik++;
                last_id = w.getInt("last_id");
            }
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        
        return last_id;
    }
    
    void edytuj(Element e) {
        connect();
        String sql = "UPDATE "+TABLE_NAME+" SET "
                + COL_NAZWA + " = '" + e.nazwa + "', "
                + COL_WYM1 + " = " + e.wym1 + ", "
                + COL_WYM2 + " = " + e.wym2 + ", "
                + COL_WYM3 + " = " + e.wym3 + ", "
                + COL_ZADANIE + " = " + e.zadanie + ", "
                + COL_WYDAJNOSC + " = " + e.wydajnosc + " "
                + "WHERE " + COL_ID + "=" + e.id+";";
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