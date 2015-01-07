/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.raports;

import java.util.ArrayList;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.Mebel;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.OpakowaniePozycja;
import pl.isimon.drewdom.Zamowienie;
import pl.isimon.drewdom.ZamowieniePozycja;

/**
 *
 * @author Simon
 */
public class RaportOpakowania3 {
    public ZamowieniePozycja pozycjaZamowienia = null;
    public ArrayList<OpakowaniePozycja> opakowanieLista;

    public RaportOpakowania3() {
        pozycjaZamowienia = new ZamowieniePozycja();
    }

    @Override
    public String toString() {
        return "RaportOpakowania3{\n" + "pozycjaZamowienia=" + pozycjaZamowienia + ", opakowanieLista=" + opakowanieLista + '}';
    }
    
    
    
    
    public ArrayList<RaportOpakowania3> getData(Zamowienie z){
        ArrayList<RaportOpakowania3> lista = new ArrayList();
        ArrayList<ZamowieniePozycja> listaMebli = pozycjaZamowienia.getPozycjeZamowienia(z.numer);
        for(int i = 0; i<listaMebli.size();i++){
            RaportOpakowania3 r = new RaportOpakowania3();
            ZamowieniePozycja m = listaMebli.get(i);
            r.pozycjaZamowienia = m;
            r.opakowanieLista= (new OpakowaniePozycja()).getData(m.mebel.numerKatalogowy,m.ilosc);
            lista.add(r);
        }
//        return laczKartony(lista);
        return lista;
    }
    
    public ArrayList<RaportOpakowania3> laczKartony(ArrayList<RaportOpakowania3> lista){
        ArrayList<RaportOpakowania3> listaNowa = new ArrayList();
        
        if(listaNowa.isEmpty()){
            listaNowa.add(lista.get(0));
        }
        
        for(int ii=1;ii<lista.size();ii++){
            RaportOpakowania3 old = lista.get(ii);
            for(int n =0;n<listaNowa.size();n++){
                RaportOpakowania3 current = listaNowa.get(n);
                for(int j=0;j<old.opakowanieLista.size();j++){
                    Opakowanie oOld = old.opakowanieLista.get(j).opakowanie;
                    for(int k=0;k<current.opakowanieLista.size();k++){
                        Opakowanie oCurrent = current.opakowanieLista.get(k).opakowanie;
                        if(oOld.equals(oCurrent)){
                            //System.out.println(current);
                            
                            current.opakowanieLista.get(k).sztuk += old.opakowanieLista.get(j).sztuk;
                            old.opakowanieLista.remove(j);
                        }
                    }
                }
            }
            if(!old.opakowanieLista.isEmpty()) listaNowa.add(old);
        }
        
        /*
        for(int i=1;i<lista.size();i++){
            boolean exists = false;
            boolean added = false;
            RaportOpakowania3 pozycjaOld = lista.get(i);
            for(int n=0;n<listaNowa.size();n++){
                exists = false;
                RaportOpakowania3 pozycjaNew = listaNowa.get(n);
                for(int j=0;j<pozycjaOld.opakowanieLista.size();j++){
                    Opakowanie opakowanieOld = pozycjaOld.opakowanieLista.get(j).opakowanie;
                    for(int m=0;m<pozycjaNew.opakowanieLista.size();m++){
                        Opakowanie opakowanieNew = pozycjaNew.opakowanieLista.get(m).opakowanie;
                        if(opakowanieOld.equals(opakowanieNew)){
                            exists = true;
                            break;
                        }
                    }
                    if(exists) break;
                }
                if(exists){
                    for(int j=0;j<pozycjaOld.opakowanieLista.size();j++){
                        Opakowanie opakowanieOld = pozycjaOld.opakowanieLista.get(j).opakowanie;
                        added = false;
                        for(int m=0;m<pozycjaNew.opakowanieLista.size();m++){
                            Opakowanie opakowanieNew = pozycjaNew.opakowanieLista.get(m).opakowanie;
                            if(opakowanieOld.equals(opakowanieNew)){
//                                pozycjaNew.pozycjaZamowienia.mebel.nazwa+=" | "+pozycjaOld.pozycjaZamowienia.mebel.nazwa;
                                pozycjaNew.opakowanieLista.get(m).sztuk+=pozycjaOld.opakowanieLista.get(j).sztuk;
                                added = true;
                                break;
                            } else {
//                                if(!added) {
//                                    pozycjaNew.opakowanieLista.add(pozycjaOld.opakowanieLista.get(j));
//                                }
                            }
                        }
                        if(!added) {
                            pozycjaNew.opakowanieLista.add(pozycjaOld.opakowanieLista.get(j));
                            //pozycjaOld.
                        }
                    }
//                    exists = false;
                }
            }
            if(!exists & !added){
                listaNowa.add(lista.get(i));
                //lista.remove(i);
                
            } else {
//                exists = false;
//                added = false;
            }
        }
        */
        return listaNowa;
    }
    
}
