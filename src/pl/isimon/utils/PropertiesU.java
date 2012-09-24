/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.utils;

/**
 *
 * @author Simon
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
 
 
public class PropertiesU {
        //Plik z konfiguracją
        private File f;
        //przyszły obiekt Properties
        private Properties properties;
 
        public PropertiesU(){
            f = new File("drewdom.properties");
            properties = new Properties();
        }
        
        public void loadProperties(){
                //Strumień wejściowy
                InputStream is;
                try {
                        is = new FileInputStream(f);
                        //ładujemy nasze ustawienia
                        properties.load(is);
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        
        public void saveProperties(String key, String value){
            OutputStream os;
            try {
                    os = new FileOutputStream(f);
                    properties.setProperty(key, value);
                    properties.store(os, null);
            } catch (FileNotFoundException e) {
                    e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }

    public Properties getProperties() {
        return properties;
    }
        
}
