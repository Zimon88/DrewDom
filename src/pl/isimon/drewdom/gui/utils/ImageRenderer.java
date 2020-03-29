/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.utils;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author eszyswi
 */
public class ImageRenderer extends DefaultTableCellRenderer {
    
    @Override
    public void setValue(Object value) {
        System.out.println("DEBUG: rendering " + value);
        setIcon(new ImageIcon((String)value));
        
//        setIcon(new ImageIcon("res/qr/1509.png"));
    }
}

