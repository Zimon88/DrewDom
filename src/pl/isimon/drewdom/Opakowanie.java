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
public class Opakowanie extends SQLiteConnection{
    public int wymiar_x;
    public int wymiar_y;
    public int wymiar_z;
    public String nrMebla;
    public int id;
    
    private final static String TABLE_NAME = "opakowanie";

    void dodaj(String numer, ArrayList<Opakowanie> opakowanieLista) {
        for(int i = 0; i<opakowanieLista.size();i++){
            connect();
            Opakowanie o = opakowanieLista.get(i);
            String sql = "INSERT INTO "+TABLE_NAME+" VALUES (null,'"+numer+"',"+o.wymiar_x+","+o.wymiar_y+","+o.wymiar_z+");";
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
