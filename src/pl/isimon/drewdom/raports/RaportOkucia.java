/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.raports;

import java.util.ArrayList;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.Mebel;
import pl.isimon.drewdom.OkuciePozycja;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.Zamowienie;
import pl.isimon.drewdom.ZamowieniePozycja;

/**
 *
 * @author Simon
 */
public class RaportOkucia {
    public ZamowieniePozycja pozycjaZamowienia = null;
    public ArrayList<OkuciePozycja> listaOkuc;

    public RaportOkucia() {
        pozycjaZamowienia = new ZamowieniePozycja();
    }
    
    
    public ArrayList<RaportOkucia> getData(Zamowienie z){
        ArrayList<RaportOkucia> lista = new ArrayList();
        ArrayList<ZamowieniePozycja> listaMebli = pozycjaZamowienia.getPozycjeZamowienia(z.numer);
        for(int i = 0; i<listaMebli.size();i++){
            RaportOkucia r = new RaportOkucia();
            ZamowieniePozycja m = listaMebli.get(i);
            r.pozycjaZamowienia = m;
            r.listaOkuc= (new OkuciePozycja()).getData(m.mebel.numerKatalogowy);
            lista.add(r);
        }
        return lista;
    }
    
}
