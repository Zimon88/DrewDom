/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Simon
 */
public class Element extends SQLiteConnection{
    public int id;
    public String nazwa;
    public int wym1;
    public int wym2;
    public int wym3;
    public int obrobka_wym1;
    public int obrobka_wym2;
    public int zadanie;
    public int wydajnosc;
    public Mebel mebel = null;
    public ArrayList<Program> programs;
    
    private final static String TABLE_NAME = "element";
    
    private final static String COL_ID = "id";
    private final static String COL_NAZWA = "nazwa";
    private final static String COL_WYM1 = "wymiar_x";
    private final static String COL_WYM2 = "wymiar_y";
    private final static String COL_WYM3 = "wymiar_z";
    private final static String COL_OB_WYM1 = "obrobka_x";
    private final static String COL_OB_WYM2 = "obrobka_y";
    private final static String COL_ZADANIE = "zadanie";
    private final static String COL_WYDAJNOSC = "wydajnosc";
    
    private final static String COL_MEBEL_KOD = "mebel_numer";
    private final static String COL_MEBEL_NAZWA = "mebel_nazwa";
    
    private String qrSavePath;
    private int qrSize;
    
    public Element(){
//        if(mebel == null) mebel = new Mebel();
        this.qrData();
        this.programs=new ArrayList();
    }
    public Element(Mebel mebel){
        this.mebel = mebel;
        this.qrData();
    }
    
    private void qrData(){
        Properties prop = getP().getProperties();
        this.qrSize = Integer.parseInt(prop.getProperty("qr_image_size"));
        this.qrSavePath = prop.getProperty("qr_path");
    }
    
    private String qrResolvePattern(){
        return qrResolvePattern(new Program());
    }
    
    private String qrResolvePattern(Program prog){
        String[] keys = {COL_WYM1, COL_WYM2, COL_WYM3, COL_ID, COL_NAZWA, "prog"};
        String pattern = prog.getPattern();
        String replace = "";
        for (String key: keys){
            switch (key) {
                case COL_WYM1:  {replace = normalize((this.wym1+this.obrobka_wym1)*10,5); break;}
                case COL_WYM2:  {replace = normalize((this.wym2+this.obrobka_wym2)*10,5); break;}
                case COL_WYM3:  {replace = normalize(this.wym3*10,3); break;}
                case COL_ID:    {replace = String.valueOf(this.id); break;}
                case COL_NAZWA: {replace = this.nazwa; break;}
                case "prog":    {replace = prog.getProgramName() + ".pcni"; break;}
            }
            
            pattern = pattern.replaceAll("%"+key+"%", replace);
            
        }
        System.out.println(pattern);
        return pattern;
    }
    
    private String normalize(int wym, int lenght) {
        String normalized;
        switch (lenght) {
            case 5: {normalized = String.format ("%05d", wym); break;}
            case 3: {normalized = String.format ("%03d", wym); break;}
            default: normalized = String.valueOf(wym);
        }
        
        return normalized;
    }
    
    private void qrCodeImageGenerate(String text, int width, int height, String filePath) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    }
    
    public void qrImageGenerate(Element e){
        qrImageGenerate(e, false);
    }
    
    public void qrImageGenerate(Element e, boolean skipCheck){
        for (Program prog: e.programs) {
            File image = new File(qrSavePath+e.id+"_"+prog.getProgramName()+".png");
            if (!image.exists() || skipCheck) {
                this.qrCodeImageGenerate(e.qrResolvePattern(prog), e.qrSize, e.qrSize, qrSavePath+e.id+"_"+prog.getProgramName()+".png");
                System.out.println("INFO: QR image generated: " + qrSavePath+e.id+"_"+prog.getProgramName()+".png");
            } else {
                System.out.println("INFO: QR image exists: " + qrSavePath+e.id+".png");
            }
        }
    }
    
    public Set getQRImagesPaths() {
        Set s = new HashSet();
        for (Program p: this.programs) {
            String path = qrSavePath+this.id+"_"+p.getProgramName()+".png";
            s.add(path);
            qrImageGenerate(this);
            System.out.println(path);
        }
        return s;
    }
    
    public Vector getProgramList() {
        List v = new Vector();
        for (Program p: this.programs){
            v.add(p.getProgramName());
        }
        return (Vector) v;
    }
    
    public Element getElement(int id){
        Element e = new Element();
        
        String sql = "SELECT * FROM element WHERE id = "+id+";";
        System.out.println(sql);
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
                wynik++;
                e.id = w.getInt(COL_ID);
                e.nazwa = w.getString(COL_NAZWA);
                e.wym1 = w.getInt(COL_WYM1);
                e.wym2 = w.getInt(COL_WYM2);
                e.wym3 = w.getInt(COL_WYM3);
                e.obrobka_wym1 = w.getInt(COL_OB_WYM1);
                e.obrobka_wym2 = w.getInt(COL_OB_WYM2);
                e.zadanie = w.getInt(COL_ZADANIE);
                e.wydajnosc = w.getInt(COL_WYDAJNOSC);
                e.programs = (ArrayList<Program>) new Program().getProgramsByElementId(e.id);
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return e;
    }
    
    public ArrayList<Element> getElements(String numerMebla){
        ArrayList<Element> lista = new ArrayList();
        
        String sql = "SELECT * FROM element WHERE id IN (SELECT element_id FROM mebel_elementy WHERE mebel_nr = '"+numerMebla+"');";
        
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Element e = new Element(new Mebel());
                wynik++;
                e.id = w.getInt(COL_ID);
                e.nazwa = w.getString(COL_NAZWA);
                e.wym1 = w.getInt(COL_WYM1);
                e.wym2 = w.getInt(COL_WYM2);
                e.wym3 = w.getInt(COL_WYM3);
                e.obrobka_wym1 = w.getInt(COL_OB_WYM1);
                e.obrobka_wym2 = w.getInt(COL_OB_WYM2);
                e.zadanie = w.getInt(COL_ZADANIE);
                e.wydajnosc = w.getInt(COL_WYDAJNOSC);
                e.programs = (ArrayList<Program>) new Program().getProgramsByElementId(e.id);
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
    
    public ArrayList<Element> getData(String nazwaCzesci, String nazwaMebla, String numerMebla) {
        ArrayList<Element> lista = new ArrayList();
        String sql = "SELECT "
                + "element.id,"
                + "element.nazwa,"
                + "element.wymiar_x,"
                + "element.wymiar_y,"
                + "element.wymiar_z,"
                + "element.obrobka_x,"
                + "element.obrobka_y," 
                + "element.zadanie,"
                + "element.wydajnosc,"
                + "mebel.nr_katalogowy as mebel_numer,"
                + "mebel.nazwa as mebel_nazwa "
                + " FROM "
                + "element,"
                + "mebel,"
                + "mebel_elementy "
                + "WHERE "
                + "mebel_elementy.mebel_nr = mebel.nr_katalogowy "
                + "AND mebel_elementy.element_id = element.id ";
//        sql = "kod LIKE '%"+prod.kod+"%'" +sql;
        if(nazwaCzesci!=null & nazwaCzesci!=""){
            sql += "AND element.nazwa LIKE '%"+nazwaCzesci+"%' ";
        }
        if(nazwaMebla!=null & nazwaMebla!=""){
            sql += "AND mebel.nazwa LIKE '%"+nazwaMebla+"%' ";
        }
        if(numerMebla!=null & numerMebla!=""){
            sql += "AND mebel.nr_katalogowy LIKE '%"+numerMebla+"%' ";
        }
        sql += ";";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Element e = new Element(new Mebel());
                wynik++;
                e.id = w.getInt(COL_ID);
                e.nazwa = w.getString(COL_NAZWA);
                e.wym1 = w.getInt(COL_WYM1);
                e.wym2 = w.getInt(COL_WYM2);
                e.wym3 = w.getInt(COL_WYM3);
                e.obrobka_wym1 = w.getInt(COL_OB_WYM1);
                e.obrobka_wym2 = w.getInt(COL_OB_WYM2);
                e.zadanie = w.getInt(COL_ZADANIE);
                e.wydajnosc = w.getInt(COL_WYDAJNOSC);
                e.mebel.numerKatalogowy = w.getString(COL_MEBEL_KOD);
                e.mebel.nazwa = w.getString(COL_MEBEL_NAZWA);
                e.programs = (ArrayList<Program>) new Program().getProgramsByElementId(e.id);
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
    
    public ArrayList<Element> getData(){
        ArrayList<Element> lista = new ArrayList();
        String sql = "SELECT element.id,element.nazwa,element.wymiar_x,element.wymiar_y,element.wymiar_z,element.obrobka_x,element.obrobka_y,element.zadanie,mebel.nr_katalogowy as mebel_numer,mebel.nazwa as mebel_nazwa "
                + " FROM element,mebel,mebel_elementy WHERE mebel_elementy.mebel_nr = mebel.nr_katalogowy AND mebel_elementy.element_id = element.id;";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while(w.next()){
                Element e = new Element(new Mebel());
                wynik++;
                e.id = w.getInt(COL_ID);
                e.nazwa = w.getString(COL_NAZWA);
                e.wym1 = w.getInt(COL_WYM1);
                e.wym2 = w.getInt(COL_WYM2);
                e.wym3 = w.getInt(COL_WYM3);
                e.obrobka_wym1 = w.getInt(COL_OB_WYM1);
                e.obrobka_wym2 = w.getInt(COL_OB_WYM2);
                e.zadanie = w.getInt(COL_ZADANIE);
                e.wydajnosc = w.getInt(COL_WYDAJNOSC);
                e.mebel.numerKatalogowy = w.getString(COL_MEBEL_KOD);
                e.mebel.nazwa = w.getString(COL_MEBEL_NAZWA);
                e.programs = (ArrayList<Program>) new Program().getProgramsByElementId(e.id);
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
    
    public int dodaj(Element e){
        int last_id = 0;
        connect();
        String sql = "INSERT INTO "+TABLE_NAME+" VALUES (null,'"+e.nazwa+"',"+e.wym1+","+e.wym2+","+e.wym3+",'',"+e.zadanie+","+e.wydajnosc+","+e.obrobka_wym1+","+e.obrobka_wym2+");";
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            disconnect();
        }
        sql = "SELECT id AS last_id FROM "+TABLE_NAME+" ORDER BY id DESC limit 1;";
        connect();
        try {
            ResultSet w = stmt.executeQuery(sql);
            wynik =0;
            while(w.next()){
                wynik++;
                last_id = w.getInt("last_id");
            }
            printSelect(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            e.id = last_id;
            this.qrImageGenerate(e, true);
            disconnect();
        }
        
        for (Program p : e.programs) {
            p.setElementId(last_id);
            p.dodaj();
        }
        
        return last_id;
    }
    
    void edytuj(Element e) {
        connect();
        String sql = "UPDATE "+TABLE_NAME+" SET "
                + COL_NAZWA + " = '" + e.nazwa + "', "
                + COL_WYM1 + " = " + e.wym1 + ", "
                + COL_WYM2 + " = " + e.wym2 + ", "
                + COL_WYM3 + " = " + e.wym3 + ", "
                + COL_OB_WYM1 + " = " + e.obrobka_wym1 + ", "
                + COL_OB_WYM2 + " = " + e.obrobka_wym2 + ", "
                + COL_ZADANIE + " = " + e.zadanie + ", "
                + COL_WYDAJNOSC + " = " + e.wydajnosc + " "
                + "WHERE " + COL_ID + "=" + e.id+";";
        int wynik;
        try {
            wynik = stmt.executeUpdate(sql);
            printSucces(sql, wynik);
        } catch (SQLException ex) {
            printSqlErr(sql);
        } finally {
            e.programs = (ArrayList<Program>) new Program().getProgramsByElementId(e.id);
            qrImageGenerate(e,true);
            disconnect();
        }
    }
}