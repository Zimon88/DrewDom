/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import pl.isimon.drewdom.gui.models.TableModelPracownik;
import java.util.ArrayList;
import pl.isimon.drewdom.Pracownik;

/**
 *
 * @author Simon
 */
public class GPracownik extends javax.swing.JPanel {

    /**
     * Creates new form GPracownik
     */
    private Pracownik pracownik;
    private boolean edycja;
    private TableModelPracownik tmp;
    
    public GPracownik() {
        initComponents();
        tmp = (TableModelPracownik)tablePracownik.getModel();
        pracownik = new Pracownik();
    }
    
    public GPracownik(ArrayList<Pracownik> lista) {
        this();
        tmp.setModelData(lista);
    }
    
    private void setFormData(Pracownik p){
        textImie.setText(p.imie);
        textNazwisko.setText(p.nazwisko);
    }
    
    private void setPracownikDataFromTable(){
        pracownik = ((TableModelPracownik)tablePracownik.getModel()).getPracownik(tablePracownik.getSelectedRow());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablePracownik = new javax.swing.JTable();
        textImie = new javax.swing.JTextField();
        labelImie = new javax.swing.JLabel();
        labelNazwisko = new javax.swing.JLabel();
        textNazwisko = new javax.swing.JTextField();
        buttonZapisz = new javax.swing.JButton();
        labelForm = new javax.swing.JLabel();
        buttonUsun = new javax.swing.JButton();
        buttonEdytuj = new javax.swing.JButton();
        buttonAnuluj = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(620, 0));

        tablePracownik.setModel(new TableModelPracownik());
        jScrollPane1.setViewportView(tablePracownik);

        labelImie.setText("Imię");

        labelNazwisko.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelNazwisko.setText("Nazwisko");

        buttonZapisz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/filesave.png"))); // NOI18N
        buttonZapisz.setText("Zapisz");
        buttonZapisz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonZapiszActionPerformed(evt);
            }
        });

        labelForm.setText("Nowy Pracownik");

        buttonUsun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/edittrash.png"))); // NOI18N
        buttonUsun.setText("Usuń");
        buttonUsun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUsunActionPerformed(evt);
            }
        });

        buttonEdytuj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/editpaste.png"))); // NOI18N
        buttonEdytuj.setText("Edytuj");
        buttonEdytuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEdytujActionPerformed(evt);
            }
        });

        buttonAnuluj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_cancel.png"))); // NOI18N
        buttonAnuluj.setText("Anuluj");
        buttonAnuluj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnulujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelForm)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelImie, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textImie, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNazwisko, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textNazwisko, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonEdytuj)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonUsun))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonZapisz)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAnuluj)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonAnuluj, buttonEdytuj, buttonUsun, buttonZapisz});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonEdytuj)
                    .addComponent(buttonUsun))
                .addGap(14, 14, 14)
                .addComponent(labelForm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAnuluj)
                    .addComponent(buttonZapisz)
                    .addComponent(labelImie)
                    .addComponent(textImie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNazwisko)
                    .addComponent(textNazwisko, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelImie, labelNazwisko, textImie, textNazwisko});

    }// </editor-fold>//GEN-END:initComponents

    private void buttonEdytujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEdytujActionPerformed
        // TODO add your handling code here:
        labelForm.setText("Edycja danych pracownika");
        edycja = true;
        setPracownikDataFromTable();
        setFormData(pracownik);
    }//GEN-LAST:event_buttonEdytujActionPerformed

    private void buttonUsunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUsunActionPerformed
        setPracownikDataFromTable();
        pracownik.usun(pracownik);
        tmp.removePracownik(pracownik);
//        tmp.setModelData(pracownik.getData());
    }//GEN-LAST:event_buttonUsunActionPerformed

    private void buttonAnulujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnulujActionPerformed
        if(edycja){
            edycja = false;
            labelForm.setText("Nowy Pracownik");
        }
        textImie.setText("");
        textNazwisko.setText("");
    }//GEN-LAST:event_buttonAnulujActionPerformed

    private void buttonZapiszActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonZapiszActionPerformed
        // TODO add your handling code here:
        pracownik.imie = textImie.getText();
        pracownik.nazwisko = textNazwisko.getText();
        if(edycja){
            pracownik.edytuj(pracownik);
            labelForm.setText("Nowy Pracownik");
        } else {
            pracownik.id = 0;
            pracownik.dodaj(pracownik);
        }
        tmp.setModelData(pracownik.getData());
        textImie.setText("");
        textNazwisko.setText("");
    }//GEN-LAST:event_buttonZapiszActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAnuluj;
    private javax.swing.JButton buttonEdytuj;
    private javax.swing.JButton buttonUsun;
    private javax.swing.JButton buttonZapisz;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelForm;
    private javax.swing.JLabel labelImie;
    private javax.swing.JLabel labelNazwisko;
    private javax.swing.JTable tablePracownik;
    private javax.swing.JTextField textImie;
    private javax.swing.JTextField textNazwisko;
    // End of variables declaration//GEN-END:variables
}
