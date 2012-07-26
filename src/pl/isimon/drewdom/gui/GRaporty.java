/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import java.awt.Component;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import pl.isimon.drewdom.Zamowienie;
import pl.isimon.drewdom.ZamowieniePozycja;
import pl.isimon.drewdom.gui.models.ComboBoxModelZamowienie;
import pl.isimon.drewdom.gui.models.TableModelRaportSzczegolwy;
import pl.isimon.drewdom.gui.utils.ElementyRaportSzczegolowyCellRenderer;
import pl.isimon.drewdom.gui.utils.MultiLineCellRenderer;
import pl.isimon.drewdom.gui.utils.TableColumnAdjuster;
import pl.isimon.drewdom.raports.RaportSzczegolowy;

/**
 *
 * @author Simon
 */
public class GRaporty extends javax.swing.JPanel {

    
    private ComboBoxModelZamowienie cbmz;
    ArrayList<ZamowieniePozycja> pozycjeLista;
    ArrayList<Zamowienie> zamowienieLista = null;
    ZamowieniePozycja pozycja;
    TableModelRaportSzczegolwy tmrs;
    RaportSzczegolowy raportS;
    TableColumnAdjuster tca;
    Zamowienie zamowienie;
    /**
     * Creates new form GRpaorty
     */
    public GRaporty() {
        initComponents();
        zamowienie = new Zamowienie();
        raportS = new RaportSzczegolowy();
        cbmz = (ComboBoxModelZamowienie) cbListaZamowien.getModel();
        pozycja= new ZamowieniePozycja();
        tmrs = (TableModelRaportSzczegolwy) tableRaportSzczegolwy.getModel();
        int rowHeight = tableRaportSzczegolwy.getRowHeight();
        tableRaportSzczegolwy.setRowHeight(rowHeight*3);
        tableRaportSzczegolwy.getColumnModel().getColumn(1).setCellRenderer(new ElementyRaportSzczegolowyCellRenderer());
        tableRaportSzczegolwy.getColumnModel().getColumn(0).setCellRenderer(new MultiLineCellRenderer());
        tca = new TableColumnAdjuster(tableRaportSzczegolwy);
    }
    
    public void loadData(){
        if(zamowienieLista == null) zamowienieLista = new ArrayList();
        zamowienieLista = zamowienie.getData();
        cbmz.setModelData(zamowienieLista);
    }
    
    public void loadDataRaportSzczegolwy(Zamowienie z){
        tmrs.setModelData(raportS.getData(z));
        zamowienie = z;
        labelRaportSzczegolowyNumer.setText(z.numer);
        updateRowHeights();
        tca.adjustColumns();
    }
    
    private void updateRowHeights()
{
    try
    {
        for (int row = 0; row < tableRaportSzczegolwy.getRowCount(); row++)
        {
            int rowHeight = tableRaportSzczegolwy.getRowHeight();

            for (int column = 0; column < tableRaportSzczegolwy.getColumnCount(); column++)
            {
                Component comp = tableRaportSzczegolwy.prepareRenderer(tableRaportSzczegolwy.getCellRenderer(row, column), row, column);
                rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
            }

            tableRaportSzczegolwy.setRowHeight(row, rowHeight);
        }
    }
    catch(ClassCastException e) {}
}
            

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        frameRaportSzczegolowy = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRaportSzczegolwy = new javax.swing.JTable();
        buttonRaportSzczegolowyDrukuj = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        labelRaportSzczegolowyNumer = new javax.swing.JLabel();
        buttonRaportSzczegolwyPodglad = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cbListaZamowien = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        frameRaportSzczegolowy.setMinimumSize(new java.awt.Dimension(600, 500));

        tableRaportSzczegolwy.setModel(new TableModelRaportSzczegolwy());
        jScrollPane1.setViewportView(tableRaportSzczegolwy);

        buttonRaportSzczegolowyDrukuj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/fileprint.png"))); // NOI18N
        buttonRaportSzczegolowyDrukuj.setText("Drukuj");
        buttonRaportSzczegolowyDrukuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRaportSzczegolowyDrukujActionPerformed(evt);
            }
        });

        jLabel5.setText("Zamowienie nr:");

        labelRaportSzczegolowyNumer.setText(" ");

        javax.swing.GroupLayout frameRaportSzczegolowyLayout = new javax.swing.GroupLayout(frameRaportSzczegolowy.getContentPane());
        frameRaportSzczegolowy.getContentPane().setLayout(frameRaportSzczegolowyLayout);
        frameRaportSzczegolowyLayout.setHorizontalGroup(
            frameRaportSzczegolowyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameRaportSzczegolowyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameRaportSzczegolowyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(frameRaportSzczegolowyLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonRaportSzczegolowyDrukuj))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, frameRaportSzczegolowyLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelRaportSzczegolowyNumer)
                .addContainerGap())
        );
        frameRaportSzczegolowyLayout.setVerticalGroup(
            frameRaportSzczegolowyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportSzczegolowyLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelRaportSzczegolowyNumer)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(frameRaportSzczegolowyLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRaportSzczegolowyDrukuj)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        buttonRaportSzczegolwyPodglad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/viewmag.png"))); // NOI18N
        buttonRaportSzczegolwyPodglad.setText("Podgląd");
        buttonRaportSzczegolwyPodglad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRaportSzczegolwyPodgladActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/viewmag.png"))); // NOI18N
        jButton2.setText("Podgląd");
        jButton2.setEnabled(false);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/viewmag.png"))); // NOI18N
        jButton3.setText("Podgląd");
        jButton3.setEnabled(false);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/viewmag.png"))); // NOI18N
        jButton4.setText("Podgląd");
        jButton4.setEnabled(false);

        cbListaZamowien.setModel(new ComboBoxModelZamowienie());

        jLabel1.setText("Zestawienie zamówienia wraz z wyzczególnieniem ilości elementów");

        jLabel2.setText("Lista opakowowań dla zamówienia");

        jLabel3.setText("Lista okuń dla zamówienia");

        jLabel4.setText("Raport");

        jCheckBox1.setText("Ostatnie zamówienie");
        jCheckBox1.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jCheckBox1)
                        .addGap(18, 18, 18)
                        .addComponent(cbListaZamowien, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonRaportSzczegolwyPodglad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonRaportSzczegolwyPodglad, jButton2, jButton3, jButton4});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbListaZamowien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRaportSzczegolwyPodglad)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton4))
                .addContainerGap(193, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonRaportSzczegolwyPodgladActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRaportSzczegolwyPodgladActionPerformed
         // TODO add your handling code here:
        Zamowienie z = (Zamowienie) cbListaZamowien.getSelectedItem();
        loadDataRaportSzczegolwy(z);
        frameRaportSzczegolowy.setVisible(true);
    }//GEN-LAST:event_buttonRaportSzczegolwyPodgladActionPerformed

    private void buttonRaportSzczegolowyDrukujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRaportSzczegolowyDrukujActionPerformed
        try {
              MessageFormat headerFormat = new MessageFormat("Zamowienie nr: "+ zamowienie.numer+" z dnia: "+zamowienie.data);
              MessageFormat footerFormat = new MessageFormat("Strona {0} ");
              tableRaportSzczegolwy.print(JTable.PrintMode.NORMAL, headerFormat, footerFormat);
            } catch (PrinterException pe) {
              System.err.println("Error printing: " + pe.getMessage());
            }
    }//GEN-LAST:event_buttonRaportSzczegolowyDrukujActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonRaportSzczegolowyDrukuj;
    private javax.swing.JButton buttonRaportSzczegolwyPodglad;
    private javax.swing.JComboBox cbListaZamowien;
    private javax.swing.JFrame frameRaportSzczegolowy;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelRaportSzczegolowyNumer;
    private javax.swing.JTable tableRaportSzczegolwy;
    // End of variables declaration//GEN-END:variables
}
