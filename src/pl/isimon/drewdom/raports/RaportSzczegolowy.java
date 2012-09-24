/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.raports;

import java.util.ArrayList;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.Mebel;
import pl.isimon.drewdom.Zamowienie;
import pl.isimon.drewdom.ZamowieniePozycja;

/**
 *
 * @author Simon
 */
public class RaportSzczegolowy {
    public Zamowienie zamowienie = null;;
    public ZamowieniePozycja pozycjaZamowienia = null;;
    public ArrayList<ElementPozycja> listaElementow = null;

    public RaportSzczegolowy() {
        pozycjaZamowienia = new ZamowieniePozycja();
    }
    
    public ArrayList<RaportSzczegolowy> getData(Zamowienie z){
        ArrayList<RaportSzczegolowy> lista = new ArrayList();
        ArrayList<ZamowieniePozycja> listaMebli = pozycjaZamowienia.getPozycjeZamowienia(z.numer);
        for(int i = 0; i<listaMebli.size();i++){
            RaportSzczegolowy r = new RaportSzczegolowy();
            ZamowieniePozycja m = listaMebli.get(i);
            r.zamowienie = z;
            r.pozycjaZamowienia = m;
            r.listaElementow = (new ElementPozycja()).getData(z.numer, m.mebel.numerKatalogowy,m.ilosc);
            lista.add(r);
        }
        return lista;
    }
    
}
