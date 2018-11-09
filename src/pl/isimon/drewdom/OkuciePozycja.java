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
public class OkuciePozycja extends SQLiteConnection {
    public Okucie okucie;
    public int ilosc;
    
    private final static String TABLE_NAME = "okucia_mebel";
    private final static String COL_MEBELNR = "mebel_nr";
    private final static String COL_ILOSC = "ilosc";
    private final static String COL_ID = "id";
    private final static String COL_NAZWA = "nazwa";
    private final static String COL_OKUCIEID = "okucie_id";

    void dodaj(String numer, ArrayList<OkuciePozycja> okucieLista) {
        for(int i = 0; i<okucieLista.size();i++){
            connect();
            OkuciePozycja o = okucieLista.get(i);
            String sql = "INSERT INTO "+TABLE_NAME+" VALUES ('"+numer+"',"+o.okucie.id+","+o.ilosc+");";
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

    public ArrayList<OkuciePozycja> getData(String numerKatalogowy) {
        ArrayList<OkuciePozycja> lista = new ArrayList();
        String sql = "SELECT * FROM " + TABLE_NAME + ",okucia WHERE "+TABLE_NAME+".mebel_nr = '"+numerKatalogowy+ "' AND okucia.id="+TABLE_NAME+".okucie_id;";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                OkuciePozycja o = new OkuciePozycja();
                o.ilosc = w.getInt(COL_ILOSC);
                o.okucie = new Okucie();
                o.okucie.id = w.getInt(COL_ID);
                o.okucie.nazwa = w.getString(COL_NAZWA);
                lista.add(o);
                wynik++;
            }
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return lista;
    }

    public void dodaj(String numer, OkuciePozycja o) {
        connect();
        String sql = "INSERT INTO "+TABLE_NAME+" VALUES ('"+numer+"',"+o.okucie.id+","+o.ilosc+");";
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

    public void usun(String numer, OkuciePozycja op) {
        connect();
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "
                +COL_MEBELNR+" = '"+numer+"' AND "
                +COL_OKUCIEID+" = "+op.okucie.id+";";
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
    
    public void edytuj(String stary, String nowy){
        connect();
        String sql = "UPDATE "+TABLE_NAME+" "
                + "SET "+COL_MEBELNR+" = '"+nowy+"' "
                + "WHERE "+COL_MEBELNR+" = '"+stary+"';";
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
