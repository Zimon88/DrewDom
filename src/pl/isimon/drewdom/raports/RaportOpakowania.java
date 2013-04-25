/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.raports;

import java.util.ArrayList;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.Mebel;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.Zamowienie;
import pl.isimon.drewdom.ZamowieniePozycja;

/**
 *
 * @author Simon
 */
public class RaportOpakowania {
    public ZamowieniePozycja pozycjaZamowienia = null;
    public ArrayList<Opakowanie> opakowanieLista;

    public RaportOpakowania() {
        pozycjaZamowienia = new ZamowieniePozycja();
    }
    
    
    public ArrayList<RaportOpakowania> getData(Zamowienie z){
        ArrayList<RaportOpakowania> lista = new ArrayList();
        ArrayList<ZamowieniePozycja> listaMebli = pozycjaZamowienia.getPozycjeZamowienia(z.numer);
        for(int i = 0; i<listaMebli.size();i++){
            RaportOpakowania r = new RaportOpakowania();
            ZamowieniePozycja m = listaMebli.get(i);
            r.pozycjaZamowienia = m;
            r.opakowanieLista= (new Opakowanie()).getData(m.mebel.numerKatalogowy);
            lista.add(r);
        }
        return lista;
    }
    
    public ArrayList<RaportOpakowania> laczKartony(Zamowienie z){
        
        ArrayList<RaportOpakowania> lista = new ArrayList();
        ArrayList<RaportOpakowania> listaNowa = new ArrayList();
        lista = this.getData(z);
        
        return listaNowa;
    }
    
}
