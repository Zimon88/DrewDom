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
import org.sqlite.SQLiteJDBCLoader;
import pl.isimon.utils.PropertiesU;


/**
 *
 * @author Simon
 */

public class SQLiteConnection {
    
    private String databaseFilename;
    private String databaseDirectory;
    private PropertiesU p;
    private boolean console = false;
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
        p = new PropertiesU();
        p.loadProperties();
        databaseFilename = p.getProperties().getProperty("database_filename");
        databaseDirectory = p.getProperties().getProperty("database_directory");
        console = Boolean.getBoolean(p.getProperties().getProperty("console"));
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
            polaczenie = DriverManager.getConnection("jdbc:sqlite:"+databaseDirectory+databaseFilename);
            stmt = polaczenie.createStatement();
            polaczenie.setAutoCommit(true);
            //System.out.println(String.format("%s mode", SQLiteJDBCLoader.isNativeMode() ? "native" : "pure-java"));
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
        String msg;
        if(console) {
            msg = "Błąd przy wykonywaniu zapytania "+sql;
        } else {
            msg = "Błąd przy wykonywaniu zapytania "+ANSI_PURPLE+sql+ANSI_RESET;
        }
        System.err.println(msg);
    }
    
    public void printSqlErr(String sql, SQLException ex){
        String msg;
        if(console) {
            msg = "Błąd przy wykonywaniu zapytania "+sql;
        } else {
            msg = "Błąd przy wykonywaniu zapytania "+ANSI_PURPLE+sql+ANSI_RESET;
        }
        System.err.println(msg);
        System.err.println(ex);
    }
    
    public void printSucces(String sql, int wynik){
         String msg;
        if(console) {
            msg = "Wykonano zapytanie "+sql+" Liczba Dodanych/Zaktualizowanych/Usuniętych rekordów: " + wynik;
        } else {
            msg = ANSI_GREEN+"Wykonano zapytanie "+ANSI_PURPLE+sql+ANSI_GREEN+" Liczba Dodanych/Zaktualizowanych/Usuniętych rekordów: " + wynik +ANSI_RESET;
        }
        System.out.println(msg);
    }
    
    public void printSelect(String sql, int wynik){
        System.out.println(ANSI_GREEN+"Wykonano zapytanie "+ANSI_PURPLE+sql+ANSI_GREEN+" Liczba pobranych rekordów: " + wynik + ANSI_RESET);
    }
    
    
}