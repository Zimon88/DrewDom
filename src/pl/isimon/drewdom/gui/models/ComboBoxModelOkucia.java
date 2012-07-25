/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import pl.isimon.drewdom.Okucie;

/**
 *
 * @author Simon
 */
public class ComboBoxModelOkucia extends AbstractListModel implements ComboBoxModel {

    private ArrayList<Okucie> lista = null;
    private Okucie selectedItem;
    
    @Override
    public int getSize() {
        if(lista == null) {
            return 0;
        } else {
            return lista.size();
        }
    }

    @Override
    public Okucie getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Okucie) anItem;
    }

    @Override
    public Okucie getSelectedItem() {
        return selectedItem;
    }
    
    public void setModelData(ArrayList<Okucie>data ){
        this.lista = data;
    }

		
    
}
