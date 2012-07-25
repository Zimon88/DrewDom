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
   public ArrayList<ElementPozycja> lista = null;
//   public Pracownik pracownik;
   
   private ElementPozycja elementPozycja;
   private OkuciePozycja okuciePozycja;
   private Opakowanie opakowanie;
   
   private final static String COL_NR = "nr_katalogowy";
   private final static String COL_NAZWA = "nazwa";
   private final static String COL_PRACOWNIK =  "pracownik";
   
   private final static String TABLE_NAME = "mebel";
   
   public Mebel(){
       elementPozycja = new ElementPozycja();
       okuciePozycja = new OkuciePozycja();
       opakowanie = new Opakowanie();
   }
   
   public ArrayList<Mebel> getData(){
        ArrayList<Mebel> lista = new ArrayList();
        String sql = "SELECT * FROM "+TABLE_NAME;
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
   
   public Mebel getMebel(String nr){
       Mebel m = new Mebel();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL_NR+" = '"+nr+"';";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                m.numerKatalogowy = w.getString(COL_NR);
                m.nazwa = w.getString(COL_NAZWA);
            }
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return m;
   }
   
   public void dodaj(Mebel mebel, ArrayList<ElementPozycja> elementyLista,ArrayList<OkuciePozycja> okucieLista, ArrayList<Opakowanie> opakowanieLista){
        connect();
        String sql = "INSERT INTO "+TABLE_NAME+" VALUES ('"+mebel.numerKatalogowy+"','"+mebel.nazwa+"');";
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
            this.elementPozycja.dodaj(mebel.numerKatalogowy,elementyLista);
            this.okuciePozycja.dodaj(mebel.numerKatalogowy,okucieLista);
            this.opakowanie.dodaj(mebel.numerKatalogowy,opakowanieLista);
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            disconnect();
        }
   }

    @Override
    public String toString() {
        return  nazwa +"\n" + numerKatalogowy;
    }
   
}
