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
public class Opakowanie extends SQLiteConnection{
    public int wymiar_x;
    public int wymiar_y;
    public int wymiar_z;
    public String nrMebla;
    public int id;
    
    private final static String TABLE_NAME = "opakowanie";
    private final static String COL_MEBELNR = "mebel_nr";
    private final static String COL_WYM1 = "wymiar_x";
    private final static String COL_WYM2 = "wymiar_y";
    private final static String COL_WYM3 = "wymiar_z";
    private final static String COL_ID = "id";

    public ArrayList<Opakowanie> getData(String numer){
        ArrayList<Opakowanie> lista = new ArrayList();
        String sql = "SELECT * FROM "+TABLE_NAME + " WHERE "+COL_MEBELNR+"='" + numer + "';";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Opakowanie o = new Opakowanie();
                o.id = w.getInt(COL_ID);
                o.nrMebla = numer;
                o.wymiar_x = w.getInt(COL_WYM1);
                o.wymiar_y = w.getInt(COL_WYM2);
                o.wymiar_z = w.getInt(COL_WYM3);
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

    public void edytuj(String numer, Opakowanie opakowanie) {
        connect();
        String sql = "UPDATE "+TABLE_NAME+ " SET "
                + COL_WYM1 + " = "+opakowanie.wymiar_x+ ", "
                + COL_WYM2 + " = "+opakowanie.wymiar_y+ ", "
                + COL_WYM3 + " = "+opakowanie.wymiar_z+ " "
                + " WHERE "+COL_MEBELNR+"='"+numer+"'";
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

    public void dodaj(String numer, Opakowanie o) {
        connect();
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

    public void usun(String numer, Opakowanie opakowanie) {
        connect();
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_ID+" = "+opakowanie.id;
                
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
