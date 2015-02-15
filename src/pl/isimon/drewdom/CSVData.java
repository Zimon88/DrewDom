/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    
    public void exportCSV(ArrayList<CSVData> d, String nr){
        
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH-mm");
        //System.out.println(data.format(new Date()));
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
            if(z==4)                    arkusz = "P2130";
            else if(x<=250)             arkusz = "P0300";
            else if(250<x && x<=300)    arkusz = "P0350";
            else if(300<x && x<=350)    arkusz = "P0400";
            else if(350<x && x<=400)    arkusz = "P0450";
            else if(400<x && x<=450)    arkusz = "P0500";
            else if(450<x && x<=500)    arkusz = "P0550";
            else if(500<x && x<=550)    arkusz = "P0600";
            else if(550<x && x<=600)    arkusz = "P0650";
            else if(600<x && x<=650)    arkusz = "P0700";
            else if(650<x && x<=700)    arkusz = "P0750";
            else if(700<x && x<=750)    arkusz = "P0800";
            else if(750<x && x<=800)    arkusz = "P0850";
            else if(800<x && x<=850)    arkusz = "P0900";
            else if(850<x && x<=900)    arkusz = "P0950";
            else if(900<x && x<=950)    arkusz = "P1000";
            else if(950<x && x<=1000)   arkusz = "P1050";
            else if(1000<x && x<=1050)  arkusz = "P1100";
            else if(1050<x && x<=1100)  arkusz = "P1150";
            else if(1100<x && x<=1150)  arkusz = "P1200";
            else if(1150<x && x<=1200)  arkusz = "P1250";
            else if(1200<x && x<=1250)  arkusz = "P1300";
            else if(1250<x && x<=1300)  arkusz = "P1350";
            else if(1300<x && x<=1350)  arkusz = "P1400";
            else if(1350<x && x<=1400)  arkusz = "P1450";
            else if(1400<x && x<=1450)  arkusz = "P1500";
            else if(1450<x && x<=1500)  arkusz = "P1550";
            else if(1500<x && x<=1550)  arkusz = "P1600";
            else if(1550<x && x<=1600)  arkusz = "P1650";
            else if(1600<x && x<=1650)  arkusz = "P1700";
            else if(1650<x && x<=1700)  arkusz = "P1750";
            else if(1700<x && x<=1750)  arkusz = "P1800";
            else if(1750<x && x<=1800)  arkusz = "P1850";
            else if(1800<x && x<=1850)  arkusz = "P1900";
            else if(1850<x && x<=1900)  arkusz = "P1950";
            else if(1900<x && x<=1950)  arkusz = "P2000";
            else if(1950<x && x<=2000)  arkusz = "P2050";
            else if(2000<x && x<=2050)  arkusz = "P2100";
            else if(2050<x && x<=2100)  arkusz = "P2150";
            else if(2100<x && x<=2150)  arkusz = "P2200";
            else if(2150<x && x<=2200)  arkusz = "P2250";
            else if(2200<x && x<=2250)  arkusz = "P2300";
            else if(2250<x && x<=2300)  arkusz = "P2350";
            else if(2300<x && x<=2350)  arkusz = "P2400";
            else if(2350<x && x<=2400)  arkusz = "P2450";
            arkusz = arkusz+"Z"+z;
            
            
            
            String data = c.elementX+"|"+c.elementY+"|"+c.ilosc+"|"+opis+"|"+struktura+"|"+"NNNN||"+arkusz+"|||||||\n";
            
            try{
                //String data = " This content will append to the end of the file";
                String filename = nr+"_"+z+".csv";
//                System.out.println("plik: " + filename);
                File file =new File(path+filename);

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

            }catch(IOException e){
                    e.printStackTrace();
            }
            
        }
        
        
    }
}
