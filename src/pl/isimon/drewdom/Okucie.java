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
public class Okucie extends SQLiteConnection{
    public int id;
    public String nazwa;
    
    private final static String COL_ID = "id";
    private final static String COL_NAZWA = "nazwa";
    
    public ArrayList<Okucie> getData(){
        ArrayList<Okucie> lista = new ArrayList();
        String sql = "SELECT * FROM okucia";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Okucie o = new Okucie();
                o.id = w.getInt(COL_ID);
                o.nazwa = w.getString(COL_NAZWA);
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
    
    public void dodaj(Okucie o){
        connect();
        String sql = "INSERT INTO okucia VALUES (null,'"+o.nazwa+"');";
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql,ex);
        } finally {
            disconnect();
        }
    }
    
    public void usun(Okucie o){
        String sql = "DELETE FROM okucia WHERE id = " + o.id + ";";
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
    
    public void edytuj(Okucie o){
        connect();
        String sql = "UPDATE okucia SET nazwa= '"+o.nazwa+"' WHERE id = "+o.id+";";
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
