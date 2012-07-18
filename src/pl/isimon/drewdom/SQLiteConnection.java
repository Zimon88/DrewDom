/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Simon
 */

public class SQLiteConnection {
    
    private String filename= "drewdom.db";
    public Connection polaczenie;
    public Statement stmt;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public SQLiteConnection() {
       /* 
        try {
            Class.forName("org.sqlite.JDBC");
            polaczenie = DriverManager.getConnection("jdbc:sqlite:res\\"+filename);
            stmt = polaczenie.createStatement();
            polaczenie.setAutoCommit(true);
        } catch (ClassNotFoundException ex) {
            System.err.println("Nie można załadować sterownika bazy danych" + ex);
        } catch (SQLException ex) {
            System.err.println("Nie można połączyć z bazą danych" + ex);
        } finally{
            try {
                polaczenie.close();
                polaczenie = null;
                stmt = null;
            } catch (SQLException ex) {
                System.err.println("Nie można zakończyć połączenia z bazą danych" + ex);
            }
        }
        * 
        */
    }

    public void connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            polaczenie = DriverManager.getConnection("jdbc:sqlite:res\\"+filename);
            stmt = polaczenie.createStatement();
            polaczenie.setAutoCommit(true);
        } catch (ClassNotFoundException ex) {
            System.err.println("Nie można załadować sterownika bazy danych" + ex);
        } catch (SQLException ex) {
            System.err.println("Nie można połączyć z bazą danych" + ex);
        }
    }
    
    public void disconnect(){
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (polaczenie != null){
                polaczenie.close();
                polaczenie = null;
            }
                
        } catch (SQLException ex) {
            System.err.println("Nie można rozłączyć z bazą danych"+ ex);
        }
    }

    public void printSqlErr(String sql){
        System.err.println("Błąd przy wykonywaniu zapytania "+ANSI_PURPLE+sql+ANSI_RESET);
    }
    
    public void printSqlErr(String sql, SQLException ex){
        System.err.println("Błąd przy wykonywaniu zapytania "+ANSI_PURPLE+sql+ANSI_RESET);
        System.err.println(ex);
    }
    
    public void printSucces(String sql, int wynik){
        System.out.println(ANSI_GREEN+"Wykonano zapytanie "+ANSI_PURPLE+sql+ANSI_GREEN+" Liczba Dodanych/Zaktualizowanych/Usuniętych rekordów: " + wynik +ANSI_RESET);
        try {
            polaczenie.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printSelect(String sql, int wynik){
        System.out.println(ANSI_GREEN+"Wykonano zapytanie "+ANSI_PURPLE+sql+ANSI_GREEN+" Liczba pobranych rekordów: " + wynik + ANSI_RESET);
    }
    
    
}