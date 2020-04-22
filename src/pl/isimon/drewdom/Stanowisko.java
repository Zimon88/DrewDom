/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author eszyswi
 */
public class Stanowisko extends SQLiteConnection {

   
    enum StanowiskoIndex {
        FREZ (4),
        CNC  (3),  //calls constructor with value 3
        PILA (2),  //calls constructor with value 2
        KLEJ (1)   //calls constructor with value 1
        ; // semicolon needed when fields / methods follow


        private final int indexCode;

        StanowiskoIndex(int indexCode) {
            this.indexCode = indexCode;
        }

        public int getIndexCode() {
            return this.indexCode;
        }
    }
    
}
