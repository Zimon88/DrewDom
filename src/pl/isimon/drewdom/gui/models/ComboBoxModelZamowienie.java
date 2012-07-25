/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import pl.isimon.drewdom.Okucie;
import pl.isimon.drewdom.Zamowienie;

/**
 *
 * @author Simon
 */
public class ComboBoxModelZamowienie extends AbstractListModel implements ComboBoxModel {

    private ArrayList<Zamowienie> lista = null;
    private Zamowienie selectedItem;
    
    @Override
    public int getSize() {
        if(lista == null) {
            return 0;
        } else {
            return lista.size();
        }
    }

    @Override
    public Zamowienie getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Zamowienie) anItem;
    }

    @Override
    public Zamowienie getSelectedItem() {
        return selectedItem;
    }
    
    public void setModelData(ArrayList<Zamowienie>data ){
        this.lista = data;
    }

		
    
}
