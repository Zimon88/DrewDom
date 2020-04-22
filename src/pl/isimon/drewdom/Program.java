/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.toIntExact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author eszyswi
 */
public class Program extends SQLiteConnection{
    
    private int element_id;
    private String prog_name;
    private String pattern;
    
    public HashMap<String,String> program_list = new HashMap<String,String>();
    public HashMap<String,String> patterns_list = new HashMap<String,String>();
    private boolean intitialized = false;
    
    private final static String TABLE_NAME = "element_programs";
    
    private final static String COL_ID = "element_id";
    private final static String COL_NAZWA = "nazwa";
    private final static String COL_PATTERN = "pattern";
    
    public Program(boolean init) {
        this.loadData();
    }
    
    public Program(int eid, String name) {
        this.element_id = eid;
        this.prog_name = name;
    }
    
    public void loadData() {
        JSONParser parser = new JSONParser();
        
        try {
            File f = new File("res\\programs.json");
            Object obj = parser.parse(new FileReader(f));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            JSONArray json_patterns = (JSONArray) jsonObject.get("patterns");
            
            for (Object json_pattern: json_patterns ) {
                String pattern_id = (String) ((JSONObject)json_pattern).get("id");
                String pattern_t = (String) ((JSONObject)json_pattern).get("pattern");
      
                this.patterns_list.put(pattern_id, pattern_t);
            }
            
            JSONArray json_programs = (JSONArray) jsonObject.get("programs");

            for (Object program: json_programs) {
                String name = (String) ((JSONObject)program).get("name");
                String pattern_id;
                pattern_id = (String) ((JSONObject)program).get("pattern");
                if (pattern_id == null) pattern_id="default";
                program_list.put(name, this.patterns_list.get(pattern_id));
                
            }

        } catch (FileNotFoundException e) {
            System.err.println("programs.json File cannot be found");
        } catch (IOException | ParseException e) {
            System.err.println("Parsing cannot be performed");
        }
        
        this.intitialized=true;
    }

    public Program() {
        
    }
    
    public String getPattern(){
        if (this.pattern == null || !this.intitialized) {
            loadData();
            this.pattern = program_list.get(this.prog_name);
            
        }
        return this.pattern;
    }
    
    public String getProgramName(){
        return this.prog_name;
    }
    
    public int getElementId() {
        return this.element_id;
    }
    
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    
    public void setProgramName(String name) {
        this.prog_name = name;
    }
    
    public void setElementId(int i){
        this.element_id = i;
    }
    
    public List<Program> getProgramsByElementId(int id){
        List list = new ArrayList();
        
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE "+ COL_ID +" = "+id+";";
        System.out.println(sql);
        connect();
        try {
            
            ResultSet w = stmt.executeQuery(sql);
            int wynik =0;
            while (w.next()) {
                wynik++;
                Program p = new Program();
                p.setElementId(w.getInt(COL_ID));
                p.setProgramName(w.getString(COL_NAZWA));
                //p.setPattern(w.getString(COL_PATTERN));
                list.add(p);
            }
            printSelect(sql, wynik); 
        } catch (SQLException ex) {
            printSqlErr(sql, ex);
        } finally {
            disconnect();
        }
        return list;
    }
    
    public void dodaj(){
        dodaj(this);
    }
    
    public void dodaj(Program p){
        connect();
        String sql = "INSERT INTO "+TABLE_NAME+" VALUES ("+p.getElementId()+",'"+p.getProgramName()+"','');";
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
    
    public void usun(int elementId, String progName) {
        connect();
        String sql = "DELETE FROM "+TABLE_NAME+" WHERE "+COL_ID+"='"+elementId+"' AND "+COL_NAZWA+ "='"+progName+"';";
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
    
    public Set<String> getProgramListName(){
        if (!this.intitialized) this.loadData();
        
        return this.program_list.keySet();
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        return this.element_id == ((Program) o).element_id && (this.pattern == null ? ((Program) o).pattern == null : this.pattern.equals(((Program) o).pattern));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.element_id;
        hash = 97 * hash + Objects.hashCode(this.prog_name);
        return hash;
    }
}
