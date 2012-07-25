/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

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
}
