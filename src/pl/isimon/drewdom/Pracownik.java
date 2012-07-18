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
public class Pracownik extends SQLiteConnection{
    public int id;
    public String imie;
    public String nazwisko;
    
    private final static String COL_ID          = "id";
    private final static String COL_IMIE        = "imie";
    private final static String COL_NAZWISKO    = "nazwisko";
    
    public ArrayList<Pracownik> getData(){
        ArrayList<Pracownik> lista = new ArrayList();
        String sql = "SELECT * FROM pracownik";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Pracownik o = new Pracownik();
                o.id = w.getInt(COL_ID);
                o.imie = w.getString(COL_IMIE);
                o.nazwisko = w.getString(COL_NAZWISKO);
                lista.add(o);
            }
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return lista;
    }
    
    public void dodaj(Pracownik p){
        connect();
        String sql = "INSERT INTO pracownik VALUES (null,'"+p.imie+"','"+p.nazwisko+"');";
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
    
    public void edytuj(Pracownik p){
        connect();
        int adm = 0;
        String sql = "UPDATE pracownik SET imie = '"+p.imie+"', nazwisko = '"+p.nazwisko+"' WHERE id = "+p.id+";";
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
    
    public void usun(Pracownik p) {
        String sql = "delete from pracownik where id = " + p.id + ";";
        int wynik;
        connect();
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
