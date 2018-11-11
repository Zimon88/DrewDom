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
public class ComboBoxModelPrio extends AbstractListModel implements ComboBoxModel {

    private ArrayList<Integer> lista = null;
    private Integer selectedItem;
    
    @Override
    public int getSize() {
        if(lista == null) {
            return 0;
        } else {
            return lista.size();
        }
    }

    @Override
    public Integer getElementAt(int index) {
        return lista.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = (Integer) anItem;
    }

    @Override
    public Integer getSelectedItem() {
        return selectedItem;
    }
    
    public void setModelData(ArrayList<Integer>data ){
        this.lista = data;
        fireContentsChanged(this.lista, 0, this.lista.size());
    }

		
    
}
