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

    public ArrayList<Mebel> szukaj(int x, int y, int z, int r){
        int xmin = x-r;
        int xmax = x+r;
        int ymin = y-r;
        int ymax = y+r;
        int zmin = z-r;
        int zmax = z+r;        
        
        ArrayList<Mebel> mebleLista = new ArrayList();
        Mebel mebel= new Mebel();
        
//        String sql = "SELECT * FROM "+TABLE_NAME + " WHERE "
//                + "((" + COL_WYM1 + " >= " +xmin+ " AND " + COL_WYM1 + " <= " + xmax + ") OR (" + COL_WYM1 + " >= " +ymin+ " AND " + COL_WYM1 + " <= " + ymax + ") OR (" +COL_WYM1 + " >= " +zmin+ " AND " + COL_WYM1 + " <= " + zmax + ")) AND "
//                + "((" + COL_WYM2 + " >= " +xmin+ " AND " + COL_WYM2 + " <= " + xmax + ") OR (" + COL_WYM2 + " >= " +ymin+ " AND " + COL_WYM2 + " <= " + ymax + ") OR (" +COL_WYM2 + " >= " +zmin+ " AND " + COL_WYM2 + " <= " + zmax + ")) AND "
//                + "((" + COL_WYM3 + " >= " +xmin+ " AND " + COL_WYM3 + " <= " + xmax + ") OR (" + COL_WYM3 + " >= " +ymin+ " AND " + COL_WYM3 + " <= " + ymax + ") OR (" +COL_WYM3 + " >= " +zmin+ " AND " + COL_WYM3 + " <= " + zmax + "));";
        String sql = "SELECT * FROM "+TABLE_NAME + " WHERE "
                + "((" + COL_WYM1 + " >= " +xmin+ " AND " + COL_WYM1 + " <= " + xmax + ") AND (" + COL_WYM2 + " >= " +ymin+ " AND " + COL_WYM2 + " <= " + ymax + ") AND (" +COL_WYM3 + " >= " +zmin+ " AND " + COL_WYM3 + " <= " + zmax + ")) OR "
                + "((" + COL_WYM1 + " >= " +xmin+ " AND " + COL_WYM1 + " <= " + xmax + ") AND (" + COL_WYM2 + " >= " +zmin+ " AND " + COL_WYM2 + " <= " + zmax + ") AND (" +COL_WYM3 + " >= " +ymin+ " AND " + COL_WYM3 + " <= " + ymax + ")) OR "
                + "((" + COL_WYM1 + " >= " +ymin+ " AND " + COL_WYM1 + " <= " + ymax + ") AND (" + COL_WYM2 + " >= " +xmin+ " AND " + COL_WYM2 + " <= " + xmax + ") AND (" +COL_WYM3 + " >= " +zmin+ " AND " + COL_WYM3 + " <= " + zmax + ")) OR "
                + "((" + COL_WYM1 + " >= " +ymin+ " AND " + COL_WYM1 + " <= " + ymax + ") AND (" + COL_WYM2 + " >= " +zmin+ " AND " + COL_WYM2 + " <= " + zmax + ") AND (" +COL_WYM3 + " >= " +xmin+ " AND " + COL_WYM3 + " <= " + xmax + ")) OR "
                + "((" + COL_WYM1 + " >= " +zmin+ " AND " + COL_WYM1 + " <= " + zmax + ") AND (" + COL_WYM2 + " >= " +xmin+ " AND " + COL_WYM2 + " <= " + xmax + ") AND (" +COL_WYM3 + " >= " +ymin+ " AND " + COL_WYM3 + " <= " + ymax + ")) OR "
                + "((" + COL_WYM1 + " >= " +zmin+ " AND " + COL_WYM1 + " <= " + zmax + ") AND (" + COL_WYM2 + " >= " +ymin+ " AND " + COL_WYM2 + " <= " + ymax + ") AND (" +COL_WYM3 + " >= " +xmin+ " AND " + COL_WYM3 + " <= " + xmax + "));";
        connect();
        try {
             ResultSet w = stmt.executeQuery(sql);
             while(w.next()){
                 Mebel e = mebel.getMebel(w.getString(COL_MEBELNR));
                 e.kod = w.getInt(COL_WYM1)+"x"+w.getInt(COL_WYM2)+"x"+w.getInt(COL_WYM3);
                 mebleLista.add(e);
             }
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return mebleLista;
    }
    
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
                + " WHERE "+COL_MEBELNR+"='"+numer+"' AND "+COL_ID+" = " +opakowanie.id;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Opakowanie other = (Opakowanie) obj;
        if (this.wymiar_x != other.wymiar_x) {
            return false;
        }
        if (this.wymiar_y != other.wymiar_y) {
            return false;
        }
        if (this.wymiar_z != other.wymiar_z) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.wymiar_x;
        hash = 59 * hash + this.wymiar_y;
        hash = 59 * hash + this.wymiar_z;
        return hash;
    }

    @Override
    public String toString() {
        return "Opakowanie{" + "wymiar_x=" + wymiar_x + ", wymiar_y=" + wymiar_y + ", wymiar_z=" + wymiar_z + ", nrMebla=" + nrMebla + ", id=" + id + '}';
    }
    
    
}
