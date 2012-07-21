/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Simon
 */
public class Zamowienie extends SQLiteConnection{
    public String data;
    public String dataRealizacji;
    public String numer;
    private ZamowieniePozycja pozycje;
    public ArrayList<ZamowieniePozycja> lista;
    
    public Zamowienie(){
        super();
        pozycje = new ZamowieniePozycja();
    }
    
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
    
    public void zapiszZamowienie(String numer, ArrayList<ZamowieniePozycja> lista, Date dataZamowienia, Date dataRealizacji){
        java.sql.Date sqlDate = null;
        String s1 = "CURRENT_DATE";
        String s2 = "null";
        if(dataZamowienia!=null){
            sqlDate = new java.sql.Date(dataZamowienia.getTime());
            s1 = sqlDate.toString();
        }
        if(dataRealizacji!=null){
            sqlDate = new java.sql.Date(dataRealizacji.getTime());
            s2 = sqlDate.toString();
        }
        
        String sql = "INSERT INTO zamowienie VALUES('"+numer+"',"+s1+","+s2+");";
        
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
        pozycje.dodaj(numer, lista);
        
    }
    
    public Zamowienie pobierzDane(String numer){
        Zamowienie z = new Zamowienie();
        
        return z;
    }
    
    public void usun(Zamowienie o){
        String sql = "DELETE FROM zamowienie WHERE nr_zamowienia = '" + o.numer + "';";
        int wynik;
        connect();
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
            pozycje.usun(o.numer);
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            disconnect();
        }
    }
    
    
    
}
