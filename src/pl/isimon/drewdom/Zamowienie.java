/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author Simon
 */
public class Zamowienie extends SQLiteConnection{
    public String data;
    public String dataRealizacji;
    public String numer;
    private final static String COL_NUMER           = "nr_zamowienia";
    private final static String COL_DATA            = "data";
    private final static String COL_DATA_REALIZACJI = "data_realizacji";
    
    public ArrayList<Zamowienie> getData(){
        ArrayList<Zamowienie> lista = new ArrayList();
        String sql = "SELECT * FROM zamowienie";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Zamowienie z = new Zamowienie();
                z.numer = w.getString(COL_NUMER);
                z.data  = w.getString(COL_DATA);
                z.dataRealizacji = w.getString(COL_DATA_REALIZACJI);
                lista.add(z);
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
