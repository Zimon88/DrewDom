/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.toIntExact;

/**
 *
 * @author Simon
 */
public class CSVData extends SQLiteConnection {
    public int pozycja;
    public String mebelNazwa;
    public String mebelNr;
    public String elementNazwa;
    public int elementX;
    public int elementY;
    public int elementZ;
    public int wydajnosc;
    public int ilosc;
    private ArrayList<Arkusz> arkusze;
    
    public CSVData(){
        
    }

    public ArrayList<CSVData> getData(String z){
        
        ArrayList<CSVData> lista = new ArrayList<>();
        
        String sql = 
                "SELECT "
                    + "pozycja_zamowienia.pozycja, "
                    + "mebel.nazwa AS mebel_nazwa,"
                    + "mebel.nr_katalogowy AS mebel_nr, "
                    + "element.nazwa AS element_nazwa,"
                    + "element.wymiar_x AS elementX, "
                    + "element.wymiar_y AS elementY, "
                    + "element.wymiar_z AS elementZ, "
                    + "element.wydajnosc AS wydajnosc, "
                    + "(SUM(mebel_elementy.ilosc)*pozycja_zamowienia.ilosc) AS ilosc "
                + "FROM zamowienie, pozycja_zamowienia, element, mebel_elementy, mebel "
                + "WHERE zamowienie.nr_zamowienia = '"+z+"' "
                    + "AND pozycja_zamowienia.nr_zamowienia=zamowienie.nr_zamowienia "
                    + "AND pozycja_zamowienia.mebel_nr=mebel.nr_katalogowy "
                    + "AND mebel_elementy.mebel_nr=mebel.nr_katalogowy "
                    + "AND element.id=mebel_elementy.element_id "
//                + "GROUP BY element.wymiar_x, element.wymiar_y, element.wymiar_z, mebel.nazwa. element.id  "
                + "GROUP BY mebel.nr_katalogowy, element.id  "
                + "ORDER BY element.wymiar_z, element.wymiar_x, element.wymiar_y DESC;";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                CSVData e  = new CSVData();
                wynik++;
                e.pozycja = w.getInt("pozycja");
                e.mebelNazwa = w.getString("mebel_nazwa");
                e.mebelNr = w.getString("mebel_nr");
                e.elementNazwa = w.getString("element_nazwa");
                e.elementX = w.getInt("elementX");
                e.elementY = w.getInt("elementY");
                e.elementZ = w.getInt("elementZ");
                e.wydajnosc = w.getInt("wydajnosc");
                
                e.ilosc = w.getInt("ilosc");
                int ei = e.ilosc;
                int ew = e.wydajnosc;
                if ((ei % ew)==0){
                    e.ilosc = ei/ew;
                } else {
                    e.ilosc = (ei/ew) + 1;
                }
                
                lista.add(e);
            }
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return lista;
    }
    
    public ArrayList<CSVData> getData2(String z){
        
        ArrayList<CSVData> lista = new ArrayList<>();
        
        String sql = 
                "SELECT "
                    + "pozycja_zamowienia.pozycja, "
                    + "pozycja_zamowienia.id, "
                    + "mebel.nazwa AS mebel_nazwa,"
                    + "mebel.nr_katalogowy AS mebel_nr, "
                    + "element.nazwa AS element_nazwa,"
                    + "element.wymiar_x AS elementX, "
                    + "element.wymiar_y AS elementY, "
                    + "element.wymiar_z AS elementZ, "
                    + "element.wydajnosc AS wydajnosc, "
                    + "(SUM(mebel_elementy.ilosc)*pozycja_zamowienia.ilosc) AS ilosc "
                + "FROM zamowienie, pozycja_zamowienia, element, mebel_elementy, mebel "
                + "WHERE zamowienie.nr_zamowienia = '"+z+"' "
                    + "AND pozycja_zamowienia.nr_zamowienia=zamowienie.nr_zamowienia "
                    + "AND pozycja_zamowienia.id NOT IN (SELECT pozycja FROM priorytety) "
                    + "AND pozycja_zamowienia.mebel_nr=mebel.nr_katalogowy "
                    + "AND mebel_elementy.mebel_nr=mebel.nr_katalogowy "
                    + "AND element.id=mebel_elementy.element_id "
//                + "GROUP BY element.wymiar_x, element.wymiar_y, element.wymiar_z, mebel.nazwa. element.id  "
                + "GROUP BY mebel.nr_katalogowy, element.id  "
                + "ORDER BY element.wymiar_z, element.wymiar_x, element.wymiar_y DESC;";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                CSVData e  = new CSVData();
                wynik++;
                e.pozycja = w.getInt("pozycja");
                e.mebelNazwa = w.getString("mebel_nazwa");
                e.mebelNr = w.getString("mebel_nr");
                e.elementNazwa = w.getString("element_nazwa");
                e.elementX = w.getInt("elementX");
                e.elementY = w.getInt("elementY");
                e.elementZ = w.getInt("elementZ");
                e.wydajnosc = w.getInt("wydajnosc");
                
                e.ilosc = w.getInt("ilosc");
                int ei = e.ilosc;
                int ew = e.wydajnosc;
                if ((ei % ew)==0){
                    e.ilosc = ei/ew;
                } else {
                    e.ilosc = (ei/ew) + 1;
                }
                
                lista.add(e);
            }
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return lista;
    }
    
    public ArrayList<CSVData> getData2(int z){
        
        ArrayList<CSVData> lista = new ArrayList<>();
        
        String sql = "SELECT "
                    + "SUM(mebel_elementy.ilosc*pozycja_zamowienia.ilosc) AS ilosc, "
                    + "pozycja_zamowienia.pozycja, "
                    + "mebel.nazwa AS mebel_nazwa, "
                    + "mebel.nr_katalogowy AS mebel_nr, "
                    + "element.nazwa AS element_nazwa, "
                    + "element.wymiar_x AS elementX, "
                    + "element.wymiar_y AS elementY, "
                    + "element.wymiar_z AS elementZ, "
                    + "element.wydajnosc AS wydajnosc "
//                    + ", * "
                + "FROM priorytety,pozycja_zamowienia,mebel,mebel_elementy,element "
                + "WHERE priorytety.list_id="+z+" "
                    + "AND priorytety.pozycja=pozycja_zamowienia.id "
                    + "AND pozycja_zamowienia.mebel_nr=mebel.nr_katalogowy "
                    + "AND mebel_elementy.mebel_nr=mebel.nr_katalogowy "
                    + "AND element.id=mebel_elementy.element_id "
                + "GROUP BY mebel.nr_katalogowy, element.id "
                + "ORDER BY element.wymiar_z, element.wymiar_x, element.wymiar_y DESC;";
        
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                CSVData e  = new CSVData();
                wynik++;
                e.pozycja = w.getInt("pozycja");
                e.mebelNazwa = w.getString("mebel_nazwa");
                e.mebelNr = w.getString("mebel_nr");
                e.elementNazwa = w.getString("element_nazwa");
                e.elementX = w.getInt("elementX");
                e.elementY = w.getInt("elementY");
                e.elementZ = w.getInt("elementZ");
                e.wydajnosc = w.getInt("wydajnosc");
                e.ilosc = w.getInt("ilosc");
                int ei = e.ilosc;
                int ew = e.wydajnosc;
                if ((ei % ew)==0){
                    e.ilosc = ei/ew;
                } else {
                    e.ilosc = (ei/ew) + 1;
                }
                
                lista.add(e);
            }
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return lista;
    }
    
    private String getArkusz(int x) {

        for (int i = 0; i < this.arkusze.size(); i++) {
            if(arkusze.get(i).getXmin()<x && x<=arkusze.get(i).getXmax()) {
                System.out.println(arkusze.get(i));
                return arkusze.get(i).getName();
            }
        }
        return null;
    }
    
    public void exportCSV(ArrayList<CSVData> d, String nr){
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        this.arkusze = new Arkusz().getArkusze();
        String s1 = date.format(new Date());
                
        String path = "csv\\"+nr+"_"+s1;
        File dir = new File(path);
        if (!dir.exists()) {
            System.out.println("creating directory: " + path);
            boolean result = false;

            try{
                dir.mkdirs();
                result = true;
             } catch(SecurityException se){
                //handle it
             }        
             if(result) {    
               System.out.println("DIR created");  
             }
        }
                
        path = path + "\\";

        CSVData c = new CSVData();

        for(int i=0; i<d.size(); i++){
            c = d.get(i);
            int z = c.elementZ;
            String struktura = "TAK";
            
            String opis = 
                nr+"-"+c.pozycja+" "
                //+c.mebelNazwa+" "
                +c.elementNazwa;
            if(c.wydajnosc>1) opis = opis +" ("+c.wydajnosc+"z1)";
            
            
            if ((z == 4) && c.elementNazwa.toLowerCase().contains("szuf")){
                struktura = "NIE";
            }
            
            String arkusz = "P300";
            int x = c.elementX;
            if(z==4) {
                arkusz = "P2130";
            } else {
                arkusz=getArkusz(x);
            }

            String data = c.elementX+"|"+c.elementY+"|"+c.ilosc+"|"+opis+"|"+struktura+"|"+"NNNN||"+arkusz+"Z"+z+"|||||||\n";
            System.out.println(data);
            try {
                //String data = " This content will append to the end of the file";
                String filename = nr+"_"+z+".csv";
//                System.out.println("plik: " + filename);
                File file = new File(path+filename);

                if(!file.exists()){
                        file.createNewFile();
                        System.out.println("plik: " + filename);
                }
                //true = append file
                FileWriter fileWritter = new FileWriter(path+file.getName(),true);
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write(data);
                bufferWritter.close();

                //System.out.println("Added " + data + " \n\tto " + file.getName());

            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
