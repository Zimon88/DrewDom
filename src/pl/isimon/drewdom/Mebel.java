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
public class Mebel extends SQLiteConnection{
   public String numerKatalogowy;
   public String nazwa;
   public ArrayList<Element> elementy;
   public Karton karton;
   public Pracownik pracownik;
   
   private final static String COL_NR = "nr_katalogowy";
   private final static String COL_NAZWA = "nazwa";
   private final static String COL_PRACOWNIK =  "pracownik";
   
   public ArrayList<Mebel> getData(){
        ArrayList<Mebel> lista = new ArrayList();
        String sql = "SELECT * FROM mebel";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Mebel o = new Mebel();
                o.numerKatalogowy = w.getString(COL_NR);
                o.nazwa = w.getString(COL_NAZWA);
                //TODO o.pracownik = 
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
}
