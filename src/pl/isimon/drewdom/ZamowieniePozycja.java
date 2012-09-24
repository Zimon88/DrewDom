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
public class ZamowieniePozycja extends SQLiteConnection{
    public String nrZamowienia;
    public Mebel mebel;
    public int ilosc;
    
    private final static String TABLE_NAME = "pozycja_zamowienia";
    private final static String COL_ID = "id";
    private final static String COL_NRZAMOWIENIA = "nr_zamowienia";
    private final static String COL_NRMEBLA = "mebel_nr";
    private final static String COL_ILOSC = "ilosc";
    
    public void dodaj(String numer, ArrayList<ZamowieniePozycja> lista){
     /*
        String sql = "INSERT INTO pozycja_zamowienia VALUES ";
        for(int i=0; i<lista.size();i++){
            
            sql += "(null,'"+numer+"','"+lista.get(i).mebel.numerKatalogowy+"',"+lista.get(i).ilosc+")";
            if(i==lista.size()-1){
                sql += ";";
            } else {
                sql += ", ";
            }
        }
        connect();
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql,ex);
        } finally {
            disconnect();
        }
    */
        
        for(int i=0; i<lista.size();i++){
            String sql = "INSERT INTO pozycja_zamowienia VALUES (null,'"+numer+"','"+lista.get(i).mebel.numerKatalogowy+"',"+lista.get(i).ilosc+");";
            connect();
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
    }
    
    public ArrayList<ZamowieniePozycja> getPozycjeZamowienia(String numer){
        ArrayList<ZamowieniePozycja> lista = new ArrayList();
        String sql = "SELECT * FROM "+ TABLE_NAME +" WHERE "+COL_NRZAMOWIENIA+" = '"+numer+"';";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                wynik++;
                ZamowieniePozycja o = new ZamowieniePozycja();
                o.ilosc = w.getInt(COL_ILOSC);
                o.mebel = (new Mebel()).getMebel(w.getString(COL_NRMEBLA));
                o.nrZamowienia = numer;
                lista.add(o);
            }
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return lista;
    }
    
    public void usun(String numer){
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_NRZAMOWIENIA+" = '" + numer + "';";
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

    @Override
    public String toString() {
        return mebel + " Sztuk:" + ilosc;
    }

    public void usun(ZamowieniePozycja z) {
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE " 
                + COL_NRZAMOWIENIA+" = '" + z.nrZamowienia + "' "
                + "AND " + COL_NRMEBLA + " = '" + z.mebel.numerKatalogowy + "'"
                + ";";
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

    public void dodaj(String numer, ZamowieniePozycja poz) {
        String sql = "INSERT INTO pozycja_zamowienia VALUES (null,'"+numer+"','"+poz.mebel.numerKatalogowy+"',"+poz.ilosc+");";
        connect();
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
    
}
