/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom;

import java.util.ArrayList;

/**
 *
 * @author Simon
 */
public class OpakowaniePozycja {
//    Mebel mebel;
    public Opakowanie opakowanie;
    public int sztuk;
    
    public ArrayList<OpakowaniePozycja> getData(String numer, int sztuk){
        ArrayList<OpakowaniePozycja> lista = new ArrayList();
        ArrayList<Opakowanie> listaO = (new Opakowanie()).getData(numer);
        for(int i = 0; i<listaO.size();i++){
            OpakowaniePozycja o = new OpakowaniePozycja();
            o.opakowanie = listaO.get(i);
            o.sztuk = sztuk;
            lista.add(o);
        }
        return lista;
    }

    @Override
    public String toString() {
        return "OpakowaniePozycja{" + "opakowanie=" + opakowanie + ", sztuk=" + sztuk + '}';
    }
    
    
}
