/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eszyswi
 */
public class Priorytety extends SQLiteConnection {
    public int id;
    public ZamowieniePozycja poz; 
    
    private final static String TABLE_NAME = "priorytety";
    private final static String COL_ID = "list_id";
    private final static String COL_POZYCJA = "pozycja";

    public Priorytety(int plist, ZamowieniePozycja z) {
        this.id = plist;
        this.poz = z;
    }

    public Priorytety() {
    }
    
    public int getNewId(){
        String sql = "SELECT MAX("+COL_ID+") FROM "+TABLE_NAME+";";
        int newId=0;
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            if (w != null) {
                newId = w.getInt("MAX("+COL_ID+")");
                printSelect(sql, 1);
            }
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            disconnect();
        }
        return (newId+1);
    }
    
    public void dodaj (int id, ZamowieniePozycja zp){
        String sql = "INSERT INTO "+TABLE_NAME+" VALUES ("+id+","+zp.id+");";
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
    
    public void dodaj (int id, ArrayList<Priorytety> lista) {
        for(int i=0; i<lista.size();i++){
            String sql = "INSERT INTO "+TABLE_NAME+" VALUES ("+id+","+lista.get(i).poz.id+");";
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

    public void usun (int listId){
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_ID+" = " + listId + ";";
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
    
        public void usun (int listId, int p){
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_ID+" = " + listId + " AND "+COL_POZYCJA+" = "+p+";";
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
    
    public ArrayList<Integer> getLists(){
        String sql = "SELECT DISTINCT "+COL_ID+" from "+TABLE_NAME+";";
        ArrayList<Integer> lista = new ArrayList();
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik=0;
            while(w.next()){
                wynik++;
                lista.add(w.getInt(COL_ID));
                System.err.println(w.getInt(COL_ID));
            }
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            disconnect();
        }
        return lista;
    }
    
    public ArrayList<Priorytety> getList(int listId) {
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL_ID+" = "+listId+";";
        ArrayList<Priorytety> lista = new ArrayList();
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                wynik++;
                Priorytety o = new Priorytety();
                ZamowieniePozycja pz = new ZamowieniePozycja().getPozycjaZamowienia(w.getInt(COL_POZYCJA),false);
                o.poz = pz;
                o.id = w.getInt(COL_ID);
                System.out.println(o.toString());
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
    
     public ArrayList<Priorytety> getListFullData(int listId) {
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+COL_ID+" = "+listId+";";
        ArrayList<Priorytety> lista = new ArrayList();
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                wynik++;
                Priorytety o = new Priorytety();
                ZamowieniePozycja pz = new ZamowieniePozycja().getPozycjaZamowienia(w.getInt(COL_POZYCJA),true);
                o.poz = pz;
                o.id = w.getInt(COL_ID);
                System.out.println(o.toString());
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
}
