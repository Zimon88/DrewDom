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
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author eszyswi
 */
public class Arkusz {
    private String name;
    private int xmin;
    private int xmax;
    
    public Arkusz(){};
    
    public int getXmin() {
        return this.xmin;
    }
    
    public int getXmax() {
        return xmax;
    }
    
    public String getName() {
        return name;
    }

    public ArrayList<Arkusz> getArkusze(){
        ArrayList<Arkusz> arkusze = new ArrayList();

        JSONParser parser = new JSONParser();
        try {
            File f = new File("res\\arkusze.json");
            Object obj = parser.parse(new FileReader(f));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            JSONArray parsedArray = (JSONArray) jsonObject.get("arkusze");

            for (int i = 0; i < parsedArray.size(); i++) {
                Arkusz a = new Arkusz();
                a.xmin = toIntExact((Long)((JSONObject)parsedArray.get(i)).get("xmin"));
                a.xmax = toIntExact((Long)((JSONObject)parsedArray.get(i)).get("xmin"));
                a.name = (String)((JSONObject)parsedArray.get(i)).get("arkusz");
                arkusze.add(a);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return arkusze;
    }
}
