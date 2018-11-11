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
    public String kod;
    public ArrayList<ElementPozycja> lista = null;
//   public Pracownik pracownik;
   
    private ElementPozycja elementPozycja;
    private OkuciePozycja okuciePozycja;
    private Opakowanie opakowanie;

    private final static String COL_NR = "nr_katalogowy";
    private final static String COL_NAZWA = "nazwa";
    private final static String COL_KOD = "kod";

    private final static String TABLE_NAME = "mebel";

    public Mebel(){
       elementPozycja = new ElementPozycja();
       okuciePozycja = new OkuciePozycja();
       opakowanie = new Opakowanie();
    }
   
    public ArrayList<Mebel> getData(){
        ArrayList<Mebel> mebelList = new ArrayList();
        String sql = "SELECT * FROM "+TABLE_NAME+" LIMIT 50";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Mebel o = new Mebel();
                o.numerKatalogowy = w.getString(COL_NR);
                o.nazwa = w.getString(COL_NAZWA);
                o.kod = w.getString(COL_KOD);
                //TODO o.pracownik = 
                mebelList.add(o);
                wynik++;
            }
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return mebelList;
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
                m.kod = w.getString(COL_KOD);
            }
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return m;
    }
    
    public Mebel getMebel(String nr, int sztuk){
        Mebel m = new Mebel();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL_NR+" = '"+nr+"';";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                m.numerKatalogowy = w.getString(COL_NR);
                m.nazwa = w.getString(COL_NAZWA);
                m.kod = w.getString(COL_KOD);
                m.lista = elementPozycja.getData2(nr, sztuk);
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
        String sql = "INSERT INTO "+TABLE_NAME+" VALUES ('"+mebel.numerKatalogowy+"','"+mebel.nazwa+"','"+mebel.kod+"');";
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
   
    public void edytuj(Mebel mebel, ArrayList<ElementPozycja> elementyLista,ArrayList<OkuciePozycja> okucieLista, ArrayList<Opakowanie> opakowanieLista){
        connect();
//        String sql = "INSERT INTO "+TABLE_NAME+" VALUES ('"+mebel.numerKatalogowy+"','"+mebel.nazwa+"','"+mebel.kod+"');";
        String sql = "UPDATE "+TABLE_NAME+ " SET "
                +COL_NAZWA+ "='"+mebel.nazwa+"', "
                +COL_KOD+ "='"+mebel.kod+"' "
                + " WHERE "+COL_NR+"='"+mebel.numerKatalogowy+"';";
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
            this.elementPozycja.edytuj(mebel.numerKatalogowy,elementyLista);
//            this.okuciePozycja.edytuj(mebel.numerKatalogowy,okucieLista);
//            this.opakowanie.dodaj(mebel.numerKatalogowy,opakowanieLista);
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            disconnect();
        }
    }
    
    public void edytuj(Mebel mebel, ArrayList<ElementPozycja> elementyLista,ArrayList<OkuciePozycja> okucieLista, ArrayList<Opakowanie> opakowanieLista, String stary){
        connect();
//        String sql = "INSERT INTO "+TABLE_NAME+" VALUES ('"+mebel.numerKatalogowy+"','"+mebel.nazwa+"','"+mebel.kod+"');";
        String sql = "UPDATE "+TABLE_NAME+ " SET "
                +COL_NR+ "='"+mebel.numerKatalogowy+"', "
                +COL_NAZWA+ "='"+mebel.nazwa+"', "
                +COL_KOD+ "='"+mebel.kod+"' "
                + " WHERE "+COL_NR+"='"+stary+"';";
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
            this.elementPozycja.edytuj(stary,mebel.numerKatalogowy);
            this.okuciePozycja.edytuj(stary,mebel.numerKatalogowy);
            this.opakowanie.edytuj(stary,mebel.numerKatalogowy);
            
            this.elementPozycja.edytuj(mebel.numerKatalogowy,elementyLista);
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            disconnect();
        }
   }
   
    public void usun(Mebel mebel){
        connect();
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_NR+" = '"+mebel.numerKatalogowy+"';";
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
//            this.elementPozycja.usun(mebel.numerKatalogowy);
            this.elementPozycja.usun(mebel.numerKatalogowy);
            this.okuciePozycja.usun(mebel.numerKatalogowy);
            this.opakowanie.usun(mebel.numerKatalogowy);
        } catch (SQLException ex) {
            printSqlErr(sql,ex);
        } finally {
            disconnect();
        }
   }

    @Override
    public String toString() {
        return  nazwa +"\n" + numerKatalogowy;
    }

    public ArrayList<Mebel> getData(String numer, String nazwa, String kod) {
        ArrayList<Mebel> listaMebli = new ArrayList();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE ";
        if(numer!=null & !"".equals(numer)){
            sql += COL_NR + " LIKE '%"+numer+"%' ";
        }
        if(nazwa!=null & !"".equals(nazwa)){
            if(numer!=null & !"".equals(numer)){
                sql += " AND ";
            }
            sql += COL_NAZWA + " LIKE '%"+nazwa+"%' ";
        }
        if(kod!=null & !"".equals(kod)){
            if((numer!=null & !"".equals(numer)) | (nazwa!=null & !"".equals(nazwa))){
                sql += " AND ";
            }
            sql += COL_KOD + " LIKE '%"+kod+"%' ";
        }
        sql += ";";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Mebel m = new Mebel();
                m.numerKatalogowy = w.getString(COL_NR);
                m.nazwa = w.getString(COL_NAZWA);
                m.kod = w.getString(COL_KOD);
                listaMebli.add(m);
            }
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return listaMebli;
    }
   
}
