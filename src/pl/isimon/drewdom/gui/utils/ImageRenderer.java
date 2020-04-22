/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author eszyswi
 */
public class ImageRenderer implements TableCellRenderer {

//    @Override
//    public void setValue(Object value) {
//        System.out.println("DEBUG: rendering " + value);
//        setIcon(new ImageIcon((String) value));
//
////        setIcon(new ImageIcon("res/qr/1509.png"));
//    }
    

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        Set paths = (HashSet) value;
        JPanel panel = new JPanel();
        
        panel.setLayout(new GridLayout(1,paths.size()));
        panel.setBackground(Color.white);
        
        if (paths.size()>0){
            //panel.setBackground(Color.red);
            panel.setPreferredSize(new Dimension(150*paths.size(),150));
            panel.setBorder(new CompoundBorder());
            for (Object path : paths) {
                String prog_name = (((String)path).replaceAll("^.*_", "")).replaceAll(".png", "");
                JLabel lbl = new JLabel(prog_name, new ImageIcon((String) path), JLabel.HORIZONTAL);
                lbl.setHorizontalTextPosition(JLabel.CENTER);
                lbl.setVerticalTextPosition(JLabel.BOTTOM);
//                lbl.setIcon(new ImageIcon((String) path));
                panel.add(lbl);

            }
        }
        return panel;
    }
}
