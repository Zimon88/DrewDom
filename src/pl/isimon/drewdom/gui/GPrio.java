/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import java.awt.Component;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.swing.JTable;
import pl.isimon.drewdom.CSVData;
import pl.isimon.drewdom.Priorytety;
import pl.isimon.drewdom.Zamowienie;
import pl.isimon.drewdom.ZamowieniePozycja;
import pl.isimon.drewdom.gui.models.ComboBoxModelPrio;
import pl.isimon.drewdom.gui.models.ComboBoxModelZamowienie;
import pl.isimon.drewdom.gui.models.TableModelPriorytety;
import pl.isimon.drewdom.gui.models.TableModelPriorytetyDetails;
import pl.isimon.drewdom.gui.models.TableModelZamowieniePozycja;
import pl.isimon.drewdom.gui.utils.ElementyRaportSzczegolowyCellRenderer;
import pl.isimon.drewdom.gui.utils.MultiLineCellRenderer;
import pl.isimon.drewdom.gui.utils.TableColumnAdjuster;

/**
 *
 * @author eszyswi
 */
public class GPrio extends javax.swing.JPanel {

    /**
     * Creates new form GPrio
     */
    
    ArrayList<Integer> prioList = new ArrayList();
    ArrayList<Priorytety> priorytet = new ArrayList();
    Priorytety priorytety = new Priorytety();
    Zamowienie zamowienie = new Zamowienie();
    ZamowieniePozycja zPoz = new ZamowieniePozycja();
    TableModelPriorytety tabelModelP;
    int newListId = 0;
    
    public GPrio() {
        initComponents();
        tabelModelP = (TableModelPriorytety)prioTable.getModel();
        prioList = new Priorytety().getLists();
        ((ComboBoxModelZamowienie)comboZamowienia.getModel()).setModelData(new Zamowienie().getData());       
        ((ComboBoxModelPrio)comboPrio.getModel()).setModelData(prioList);
        detailsButton.setEnabled(false);
        
    }
    
    public void loadData(int listId) {
        ArrayList<Priorytety> pList = priorytety.getListFullData(listId);
        ((TableModelPriorytetyDetails)tablePrioDetails.getModel()).setModelData(pList);
    }
    
    private void updateRowHeights(){
        try{
            for (int row = 0; row < tablePrioDetails.getRowCount(); row++)
            {
                int rowHeight = tablePrioDetails.getRowHeight(row);
                for (int column = 2; column < tablePrioDetails.getColumnCount(); column++)
                {
                    Component comp = tablePrioDetails.prepareRenderer(tablePrioDetails.getCellRenderer(row, column), row, column);
                    int height = comp.getPreferredSize().height;
                    rowHeight = Math.max(rowHeight, height);
                }
                tablePrioDetails.setRowHeight(row, rowHeight);
            }
            //resized = true;
        }
        catch(ClassCastException e) {}
    }

    /**
     * This method is called from within the const ructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prioDetailsFrame = new javax.swing.JFrame();
        jLabel3 = new javax.swing.JLabel();
        prioLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePrioDetails = new javax.swing.JTable();
        buttonRDrukuj = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        comboZamowienia = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        zamowieniaTable = new javax.swing.JTable();
        comboPrio = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        addToListButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        prioTable = new javax.swing.JTable();
        newListButton = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        usunButton = new javax.swing.JButton();
        detailsButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        prioDetailsFrame.setMinimumSize(new java.awt.Dimension(800, 600));

        jLabel3.setText("Lista priorytetów:");

        tablePrioDetails.setModel(new TableModelPriorytetyDetails()
        );
        jScrollPane3.setViewportView(tablePrioDetails);

        buttonRDrukuj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/fileprint.png"))); // NOI18N
        buttonRDrukuj.setText("Drukuj");
        buttonRDrukuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRDrukujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout prioDetailsFrameLayout = new javax.swing.GroupLayout(prioDetailsFrame.getContentPane());
        prioDetailsFrame.getContentPane().setLayout(prioDetailsFrameLayout);
        prioDetailsFrameLayout.setHorizontalGroup(
            prioDetailsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prioDetailsFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(prioDetailsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(prioDetailsFrameLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRDrukuj))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        prioDetailsFrameLayout.setVerticalGroup(
            prioDetailsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prioDetailsFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(prioDetailsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonRDrukuj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(prioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                .addContainerGap())
        );

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/filesave.png"))); // NOI18N
        saveButton.setText("Zapisz");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        comboZamowienia.setModel(new ComboBoxModelZamowienie());
        comboZamowienia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboZamowieniaActionPerformed(evt);
            }
        });

        jLabel1.setText("Zamowienie");

        zamowieniaTable.setModel(new TableModelZamowieniePozycja());
        jScrollPane1.setViewportView(zamowieniaTable);

        comboPrio.setModel(new ComboBoxModelPrio());
        comboPrio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPrioItemStateChanged(evt);
            }
        });
        comboPrio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPrioActionPerformed(evt);
            }
        });

        jLabel2.setText("Lista priorytetow");

        addToListButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_ok.png"))); // NOI18N
        addToListButton.setText("Dodaj do listy");
        addToListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToListButtonActionPerformed(evt);
            }
        });

        prioTable.setModel(new TableModelPriorytety());
        jScrollPane2.setViewportView(prioTable);

        newListButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/filenew.png"))); // NOI18N
        newListButton.setText("Nowa Lista");
        newListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newListButtonActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/editdelete.png"))); // NOI18N
        jButton4.setText("Usuń");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        usunButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/edittrash.png"))); // NOI18N
        usunButton.setText("Usuń");
        usunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usunButtonActionPerformed(evt);
            }
        });

        detailsButton.setText("Szczegóły");
        detailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailsButtonActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/fileprint.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CSV Export");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboZamowienia, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(detailsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addToListButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboPrio, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(newListButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usunButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboZamowienia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addToListButton)
                    .addComponent(comboPrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(newListButton)
                    .addComponent(usunButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(jButton4)
                    .addComponent(detailsButton)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addToListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToListButtonActionPerformed
        // TODO add your handling code here:
        ZamowieniePozycja z = ((TableModelZamowieniePozycja)zamowieniaTable.getModel()).getZamowieniePozycja(zamowieniaTable.getSelectedRow());
        int plist = ((ComboBoxModelPrio)comboPrio.getModel()).getSelectedItem();
        Priorytety p = new Priorytety(plist,z);

        if(this.newListId != 0) {
            tabelModelP.addPrio(p);
            priorytet.add(p);
        } else {
            priorytety.dodaj(plist, z);
        }
        (new TableColumnAdjuster(prioTable)).adjustColumns();
    }//GEN-LAST:event_addToListButtonActionPerformed

    private void comboZamowieniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboZamowieniaActionPerformed
        // TODO add your handling code here:
        ((TableModelZamowieniePozycja)zamowieniaTable.getModel()).setModelData(zPoz.getPozycjeZamowienia(comboZamowienia.getModel().getSelectedItem().toString()));
    }//GEN-LAST:event_comboZamowieniaActionPerformed

    private void newListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newListButtonActionPerformed
        this.newListId = priorytety.getNewId();
        this.priorytet.clear();
        if (!prioList.contains(this.newListId)) {
            prioList.add(this.newListId);
            ((ComboBoxModelPrio)comboPrio.getModel()).setModelData(prioList);
            comboPrio.setSelectedIndex(prioList.indexOf(newListId));
            comboPrio.setEnabled(false);
            detailsButton.setEnabled(false);
            newListButton.setText("Anuluj");
        } else {
            comboPrio.setEnabled(true);
            detailsButton.setEnabled(true);
            newListButton.setText("Nowa Lista");
            prioList.remove(this.newListId);
            comboPrio.setSelectedIndex(prioList.indexOf(prioList.size()-1));
            this.newListId=0;
        }
        
    }//GEN-LAST:event_newListButtonActionPerformed

    private void usunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usunButtonActionPerformed
        // TODO add your handling code here:
        int selected = ((ComboBoxModelPrio)comboPrio.getModel()).getSelectedItem();
        priorytety.usun(selected);
        prioList.remove(selected);
        comboPrio.setSelectedIndex(prioList.indexOf(prioList.size()-1));
        
    }//GEN-LAST:event_usunButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        if (this.newListId != 0) {
            priorytety.dodaj(this.newListId,priorytet);
            comboPrio.setEnabled(true);
            newListButton.setText("Nowa Lista");
            detailsButton.setEnabled(true);
            this.newListId = 0;
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here
        int selection = prioTable.getSelectedRow();
        if (selection!=-1){
            Priorytety p = tabelModelP.getPriorytet(selection);
            if ( this.newListId == 0) {
                priorytety.usun(p.id, p.poz.id);
                
            } 
            priorytet.remove(p);
            tabelModelP.removePriorytet(p);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void comboPrioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPrioActionPerformed

    }//GEN-LAST:event_comboPrioActionPerformed

    private void comboPrioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPrioItemStateChanged
        
        int selected = ((ComboBoxModelPrio)comboPrio.getModel()).getSelectedItem();
        tabelModelP.setModelData(priorytety.getList(selected));
        (new TableColumnAdjuster(prioTable)).adjustColumns();
        detailsButton.setEnabled(true);
    }//GEN-LAST:event_comboPrioItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int selected = ((ComboBoxModelPrio)comboPrio.getModel()).getSelectedItem();
            MessageFormat headerFormat = new MessageFormat("Lista priorytetów: "+ selected);
            MessageFormat footerFormat = new MessageFormat("Strona {0} ");
            prioTable.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
        } catch (PrinterException pe) {
            System.err.println("Error printing: " + pe.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void detailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailsButtonActionPerformed
        // TODO add your handling code here:
        tablePrioDetails.getColumnModel().getColumn(3).setCellRenderer(new ElementyRaportSzczegolowyCellRenderer());
        tablePrioDetails.getColumnModel().getColumn(2).setCellRenderer(new MultiLineCellRenderer());
        int selected = ((ComboBoxModelPrio)comboPrio.getModel()).getSelectedItem();
        prioDetailsFrame.setVisible(true);
        loadData(selected);
        prioLabel.setText(""+selected);
        (new TableColumnAdjuster(tablePrioDetails)).adjustColumns();
        updateRowHeights();
    }//GEN-LAST:event_detailsButtonActionPerformed

    private void buttonRDrukujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRDrukujActionPerformed
        // TODO add your handling code here:
        this.printTable(tablePrioDetails, "Lista Priorytetów: "+((ComboBoxModelPrio)comboPrio.getModel()).getSelectedItem(), null);
    }//GEN-LAST:event_buttonRDrukujActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selected = ((ComboBoxModelPrio)comboPrio.getModel()).getSelectedItem();
        CSVData csv = new CSVData();
        ArrayList<CSVData> d = new ArrayList();
        d = csv.getData2(selected);
        csv.exportCSV(d, "Prio_"+selected);
    }//GEN-LAST:event_jButton2ActionPerformed

    void printTable(JTable table, String header, String footer){
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            PageFormat pf = job.defaultPage();
            Paper paper = pf.getPaper();
            double margin = 20.;
            paper.setImageableArea(margin, margin, paper.getWidth() - 2* margin, paper.getHeight() - 2* margin);
            pf.setPaper(paper);
            MessageFormat headerFormat = header!=null?new MessageFormat(header):null;
            MessageFormat footerFormat = footer!=null?new MessageFormat(footer):null;
            job.setPrintable(table.getPrintable(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat), job.validatePage(pf));
            job.printDialog();
            job.print();
        } catch (PrinterException pe) {
          System.err.println("Error printing: " + pe.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToListButton;
    private javax.swing.JButton buttonRDrukuj;
    private javax.swing.JComboBox<String> comboPrio;
    private javax.swing.JComboBox<String> comboZamowienia;
    private javax.swing.JButton detailsButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton newListButton;
    private javax.swing.JFrame prioDetailsFrame;
    private javax.swing.JLabel prioLabel;
    private javax.swing.JTable prioTable;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable tablePrioDetails;
    private javax.swing.JButton usunButton;
    private javax.swing.JTable zamowieniaTable;
    // End of variables declaration//GEN-END:variables
}
