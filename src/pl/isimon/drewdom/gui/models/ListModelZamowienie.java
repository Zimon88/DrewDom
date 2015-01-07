/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.models;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import pl.isimon.drewdom.Okucie;

/**
 *
 * @author Simon
 */
public class ListModelZamowienie extends AbstractListModel implements ListModel {

    private ArrayList<String> lista = null;
    private String selectedItem;
    
    @Override
    public int getSize() {
        if(lista == null) {
            return 0;
        } else {
            return lista.size();
        }
    }

    @Override
    public String getElementAt(int index) {
        return lista.get(index);
    }
    
    public void setModelData(ArrayList<String>data ){
        this.lista = data;
    }

		
    
}
