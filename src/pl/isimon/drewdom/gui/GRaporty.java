/*
 * To change this template, choose Tools | Templates
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
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import pl.isimon.drewdom.CSVData;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.OpakowaniePozycja;
import pl.isimon.drewdom.Zamowienie;
import pl.isimon.drewdom.ZamowieniePozycja;
import pl.isimon.drewdom.gui.models.ComboBoxModelZamowienie;
import pl.isimon.drewdom.gui.models.TableModelRaportKody;
import pl.isimon.drewdom.gui.models.TableModelRaportOkucia;
import pl.isimon.drewdom.gui.models.TableModelRaportOpakowania;
import pl.isimon.drewdom.gui.models.TableModelRaportOpakowania3;
import pl.isimon.drewdom.gui.models.TableModelRaportSzczegolwy;
import pl.isimon.drewdom.gui.utils.ElementyRaportSzczegolowyCellRenderer;
import pl.isimon.drewdom.gui.utils.MultiLineCellRenderer;
import pl.isimon.drewdom.gui.utils.OkucieCellRenderer;
import pl.isimon.drewdom.gui.utils.OpakowaniaCellRenderer;
import pl.isimon.drewdom.gui.utils.OpakowaniaCellRenderer3;
import pl.isimon.drewdom.gui.utils.TableColumnAdjuster;
import pl.isimon.drewdom.raports.RaportOkucia;
import pl.isimon.drewdom.raports.RaportOpakowania;
import pl.isimon.drewdom.raports.RaportOpakowania3;
import pl.isimon.drewdom.raports.RaportSzczegolowy;

/**
 *
 * @author Simon
 */
public class GRaporty extends javax.swing.JPanel {

    
    private ComboBoxModelZamowienie cbmz;
    ComboBoxModelZamowienie cbmz3;
    ArrayList<ZamowieniePozycja> pozycjeLista;
    ArrayList<Zamowienie> zamowienieLista = null;
    ZamowieniePozycja pozycja;
    OpakowaniePozycja opakowaniePozycja;
    TableModelRaportSzczegolwy tmrs;
    TableModelRaportOpakowania tmro;
    TableModelRaportOpakowania3 tmro3;
    TableModelRaportKody tmrk;
    TableModelRaportOkucia tmrok;
    RaportOkucia raportOk;
    RaportOpakowania raportO;
    RaportOpakowania3 raportO3;
    RaportSzczegolowy raportS;
    TableColumnAdjuster tca;
    TableColumnAdjuster tca2;
    TableColumnAdjuster tcaKody;
    TableColumnAdjuster tcaokucia;
    TableColumnAdjuster tca3;
    Zamowienie zamowienie;
    Opakowanie opakowanie;
    boolean resized=false;
    /**
     * Creates new form GRpaorty
     */
    public GRaporty() {
        initComponents();
        opakowanie = new Opakowanie();
        zamowienie = new Zamowienie();
        raportS = new RaportSzczegolowy();
        raportO = new RaportOpakowania();
        raportO3 = new RaportOpakowania3();
        raportOk = new RaportOkucia();
        opakowaniePozycja = new OpakowaniePozycja();
        cbmz = (ComboBoxModelZamowienie) cbListaZamowien.getModel();
        cbmz3 = (ComboBoxModelZamowienie) cbZamowienia.getModel();
        pozycja= new ZamowieniePozycja();
        tmrs = (TableModelRaportSzczegolwy) tableRaportSzczegolwy.getModel();
        tmro = (TableModelRaportOpakowania) tableRaportOpakowania.getModel();
        tmrk = (TableModelRaportKody) tableRaportKody.getModel();
        tmrok = (TableModelRaportOkucia) tableOkucia.getModel();
        tmro3 = (TableModelRaportOpakowania3) tableRaportOpakowania1.getModel();
        
        tableRaportOpakowania.getColumnModel().getColumn(4).setCellRenderer(new OpakowaniaCellRenderer());
        tableRaportOpakowania1.getColumnModel().getColumn(3).setCellRenderer(new OpakowaniaCellRenderer3());
//        tableRaportOpakowania.getColumnModel().getColumn(0).setCellRenderer(new MultiLineCellRenderer());
        tableRaportSzczegolwy.getColumnModel().getColumn(2).setCellRenderer(new ElementyRaportSzczegolowyCellRenderer());
        tableRaportSzczegolwy.getColumnModel().getColumn(1).setCellRenderer(new MultiLineCellRenderer());
        tableOkucia.getColumnModel().getColumn(4).setCellRenderer(new OkucieCellRenderer());
//        tableRaportSzczegolwy.setRowHeight(10000);
        tca = new TableColumnAdjuster(tableRaportSzczegolwy);
        tca2 = new TableColumnAdjuster(tableRaportOpakowania);
        tca3 = new TableColumnAdjuster(tableRaportOpakowania1);
        tcaKody = new TableColumnAdjuster(tableRaportKody);
        tcaokucia = new TableColumnAdjuster(tableOkucia);
        
    }
    
    public void loadData(){
        if(zamowienieLista == null) zamowienieLista = new ArrayList();
        zamowienieLista = zamowienie.getData();
        cbmz.setModelData(zamowienieLista);
    }
    
    public void loadDataRaportSzczegolwy(Zamowienie z){
//        tableRaportSzczegolwy.setModel(new TableModelRaportSzczegolwy(),qrCheckBox.isSelected());
        tmrs.setModelData(raportS.getData(z,usunPrioCB.isSelected()));
        labelRaportSzczegolowyNumer.setText(z.numer);
//        if(!zamowienie.equals(z)) 
            updateRowHeights();
        zamowienie = z;
        tca.adjustColumns();
    }
    
    public void loadDataRaportOpakowania(Zamowienie z){
        tmro.setModelData(raportO.getData(z));
        zamowienie=z;
        updateRowHeights(tableRaportOpakowania);
        tca2.adjustColumns();
    }
    public void loadDataRaportOpakowania3(Zamowienie z){
        tmro3.setModelData(raportO3.getData(z));
        cbmz3.setModelData(zamowienieLista);
        zamowienie=z;
        updateRowHeights(tableRaportOpakowania1);
        tca3.adjustColumns();
    }
    
    public void loadDataRaportKody(Zamowienie z){
        tmrk.setModelData((new ZamowieniePozycja()).getPozycjeZamowienia(z.numer));
        zamowienie=z;
        tcaKody.adjustColumns();
    }
    
    private void loadDataRaportOkucia(Zamowienie z) {
        tmrok.setModelData(raportOk.getData(z));
        zamowienie=z;
        updateRowHeights(tableOkucia);
        tcaokucia.adjustColumns();
    }
    
    private void updateRowHeights(){
        try{
            for (int row = 0; row < tableRaportSzczegolwy.getRowCount(); row++)
            {
                int rowHeight = tableRaportSzczegolwy.getRowHeight(row);
                for (int column = 2; column < tableRaportSzczegolwy.getColumnCount(); column++)
                {
                    Component comp = tableRaportSzczegolwy.prepareRenderer(tableRaportSzczegolwy.getCellRenderer(row, column), row, column);
                    int height = comp.getPreferredSize().height;
                    rowHeight = Math.max(rowHeight, height);
                }
                tableRaportSzczegolwy.setRowHeight(row, rowHeight);
            }
            resized = true;
        }
        catch(ClassCastException e) {}
    }
    
    private void updateRowHeights(JTable table){
        try{
            for (int row = 0; row < table.getRowCount(); row++)
            {
                int rowHeight = table.getRowHeight(row);
                for (int column = 1; column < table.getColumnCount(); column++)
                {
                    Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
                    int height = comp.getPreferredSize().height;
//                    System.out.println("column "+ column+" height " + height);
                    rowHeight = Math.max(rowHeight, height);
                }
//                    System.out.println(rowHeight);
                table.setRowHeight(row, rowHeight);
            }
            resized = true;
        }
        catch(ClassCastException e) {}
    }
    
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
    
    public void printTable(JTable table){
        this.printTable(table,null,null);
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
        frameRaportOpakowania = new javax.swing.JFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableRaportOpakowania = new javax.swing.JTable();
        buttonRODrukuj = new javax.swing.JButton();
        frameRaportOkucia = new javax.swing.JFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableOkucia = new javax.swing.JTable();
        bDrukujOkucia = new javax.swing.JButton();
        frameRaportKody = new javax.swing.JFrame();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableRaportKody = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        frameRaportOpakowania3 = new javax.swing.JFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableRaportOpakowania1 = new javax.swing.JTable();
        buttonRODrukuj1 = new javax.swing.JButton();
        cbZamowienia = new javax.swing.JComboBox();
        bDodaj = new javax.swing.JButton();
        bLacz = new javax.swing.JButton();
        buttonRaportSzczegolwy = new javax.swing.JButton();
        buttonRaportOpakowania = new javax.swing.JButton();
        buttonRaportOkucia = new javax.swing.JButton();
        buttonRaportKody = new javax.swing.JButton();
        cbListaZamowien = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        bCSVExport = new javax.swing.JButton();
        usunPrioCB = new javax.swing.JCheckBox();
        qrCheckBox = new javax.swing.JCheckBox();

        frameRaportSzczegolowy.setMinimumSize(new java.awt.Dimension(1024, 800));

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
            .addGroup(frameRaportSzczegolowyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameRaportSzczegolowyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameRaportSzczegolowyLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameRaportSzczegolowyLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelRaportSzczegolowyNumer, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameRaportSzczegolowyLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonRaportSzczegolowyDrukuj)
                        .addGap(10, 10, 10))))
        );
        frameRaportSzczegolowyLayout.setVerticalGroup(
            frameRaportSzczegolowyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportSzczegolowyLayout.createSequentialGroup()
                .addGroup(frameRaportSzczegolowyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRaportSzczegolowyNumer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonRaportSzczegolowyDrukuj)
                .addContainerGap())
        );

        frameRaportOpakowania.setMinimumSize(new java.awt.Dimension(800, 500));

        tableRaportOpakowania.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tableRaportOpakowania.setModel(new TableModelRaportOpakowania());
        jScrollPane2.setViewportView(tableRaportOpakowania);

        buttonRODrukuj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/fileprint.png"))); // NOI18N
        buttonRODrukuj.setText("Drukuj");
        buttonRODrukuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRODrukujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameRaportOpakowaniaLayout = new javax.swing.GroupLayout(frameRaportOpakowania.getContentPane());
        frameRaportOpakowania.getContentPane().setLayout(frameRaportOpakowaniaLayout);
        frameRaportOpakowaniaLayout.setHorizontalGroup(
            frameRaportOpakowaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportOpakowaniaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameRaportOpakowaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameRaportOpakowaniaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonRODrukuj))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        frameRaportOpakowaniaLayout.setVerticalGroup(
            frameRaportOpakowaniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportOpakowaniaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRODrukuj)
                .addContainerGap())
        );

        frameRaportOkucia.setMinimumSize(new java.awt.Dimension(640, 480));

        tableOkucia.setModel(new TableModelRaportOkucia());
        jScrollPane4.setViewportView(tableOkucia);

        bDrukujOkucia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/fileprint.png"))); // NOI18N
        bDrukujOkucia.setText("Drukuj");
        bDrukujOkucia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDrukujOkuciaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameRaportOkuciaLayout = new javax.swing.GroupLayout(frameRaportOkucia.getContentPane());
        frameRaportOkucia.getContentPane().setLayout(frameRaportOkuciaLayout);
        frameRaportOkuciaLayout.setHorizontalGroup(
            frameRaportOkuciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportOkuciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameRaportOkuciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameRaportOkuciaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(bDrukujOkucia))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
                .addContainerGap())
        );
        frameRaportOkuciaLayout.setVerticalGroup(
            frameRaportOkuciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportOkuciaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bDrukujOkucia)
                .addContainerGap())
        );

        frameRaportKody.setMinimumSize(new java.awt.Dimension(640, 480));

        tableRaportKody.setModel(new TableModelRaportKody());
        jScrollPane3.setViewportView(tableRaportKody);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/fileprint.png"))); // NOI18N
        jButton1.setText("Drukuj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameRaportKodyLayout = new javax.swing.GroupLayout(frameRaportKody.getContentPane());
        frameRaportKody.getContentPane().setLayout(frameRaportKodyLayout);
        frameRaportKodyLayout.setHorizontalGroup(
            frameRaportKodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportKodyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameRaportKodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, frameRaportKodyLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        frameRaportKodyLayout.setVerticalGroup(
            frameRaportKodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportKodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        frameRaportOpakowania3.setMinimumSize(new java.awt.Dimension(800, 500));

        tableRaportOpakowania1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        tableRaportOpakowania1.setModel(new TableModelRaportOpakowania3());
        jScrollPane5.setViewportView(tableRaportOpakowania1);

        buttonRODrukuj1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/fileprint.png"))); // NOI18N
        buttonRODrukuj1.setText("Drukuj");
        buttonRODrukuj1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRODrukuj1ActionPerformed(evt);
            }
        });

        cbZamowienia.setModel(new ComboBoxModelZamowienie());

        bDodaj.setText("Dodaj kartony");
        bDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDodajActionPerformed(evt);
            }
        });

        bLacz.setText("Łącz");
        bLacz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLaczActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout frameRaportOpakowania3Layout = new javax.swing.GroupLayout(frameRaportOpakowania3.getContentPane());
        frameRaportOpakowania3.getContentPane().setLayout(frameRaportOpakowania3Layout);
        frameRaportOpakowania3Layout.setHorizontalGroup(
            frameRaportOpakowania3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportOpakowania3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(frameRaportOpakowania3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(frameRaportOpakowania3Layout.createSequentialGroup()
                        .addComponent(cbZamowienia, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDodaj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bLacz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonRODrukuj1))
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );
        frameRaportOpakowania3Layout.setVerticalGroup(
            frameRaportOpakowania3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(frameRaportOpakowania3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(frameRaportOpakowania3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRODrukuj1)
                    .addComponent(cbZamowienia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bDodaj)
                    .addComponent(bLacz))
                .addContainerGap())
        );

        buttonRaportSzczegolwy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/viewmag.png"))); // NOI18N
        buttonRaportSzczegolwy.setText("Podgląd");
        buttonRaportSzczegolwy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRaportSzczegolwyActionPerformed(evt);
            }
        });

        buttonRaportOpakowania.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/viewmag.png"))); // NOI18N
        buttonRaportOpakowania.setText("Podgląd");
        buttonRaportOpakowania.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRaportOpakowaniaActionPerformed(evt);
            }
        });

        buttonRaportOkucia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/viewmag.png"))); // NOI18N
        buttonRaportOkucia.setText("Podgląd");
        buttonRaportOkucia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRaportOkuciaActionPerformed(evt);
            }
        });

        buttonRaportKody.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/viewmag.png"))); // NOI18N
        buttonRaportKody.setText("Podgląd");
        buttonRaportKody.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRaportKodyActionPerformed(evt);
            }
        });

        cbListaZamowien.setModel(new ComboBoxModelZamowienie());
        cbListaZamowien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbListaZamowienActionPerformed(evt);
            }
        });

        jLabel1.setText("Zestawienie zamówienia wraz z elementami");

        jLabel2.setText("Lista opakowowań dla zamówienia");

        jLabel3.setText("Lista okuc dla zamówienia");

        jLabel4.setText("Kody kreskowe");

        jButton2.setText("Kartony");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bCSVExport.setText("CSV Export");
        bCSVExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCSVExportActionPerformed(evt);
            }
        });

        usunPrioCB.setSelected(true);
        usunPrioCB.setText("Usuń priorytety");

        qrCheckBox.setText("QR");
        qrCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qrCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usunPrioCB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbListaZamowien, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(qrCheckBox)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(buttonRaportSzczegolwy, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                .addComponent(buttonRaportOpakowania, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(buttonRaportOkucia, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                                .addComponent(buttonRaportKody, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addComponent(bCSVExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {buttonRaportKody, buttonRaportOkucia, buttonRaportOpakowania, buttonRaportSzczegolwy});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbListaZamowien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usunPrioCB))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRaportSzczegolwy)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qrCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(buttonRaportOpakowania)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(buttonRaportOkucia))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(buttonRaportKody))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(bCSVExport)
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {buttonRaportOpakowania, jButton2});

    }// </editor-fold>//GEN-END:initComponents

    private void buttonRaportSzczegolwyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRaportSzczegolwyActionPerformed
        Zamowienie z = (Zamowienie) cbListaZamowien.getSelectedItem();
        tableRaportSzczegolwy.getColumnModel().getColumn(2).setCellRenderer(new ElementyRaportSzczegolowyCellRenderer(qrCheckBox.isSelected()));
        loadDataRaportSzczegolwy(z);
        frameRaportSzczegolowy.setVisible(true);
    }//GEN-LAST:event_buttonRaportSzczegolwyActionPerformed

    private void buttonRaportSzczegolowyDrukujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRaportSzczegolowyDrukujActionPerformed
        this.printTable(tableRaportSzczegolwy,"Zamowienie nr: " + zamowienie.numer,"Strona {0}");
    }//GEN-LAST:event_buttonRaportSzczegolowyDrukujActionPerformed

    private void buttonRaportOpakowaniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRaportOpakowaniaActionPerformed
        Zamowienie z = (Zamowienie) cbListaZamowien.getSelectedItem();
        loadDataRaportOpakowania(z);
        frameRaportOpakowania.setVisible(true);
    }//GEN-LAST:event_buttonRaportOpakowaniaActionPerformed

    private void buttonRODrukujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRODrukujActionPerformed
        this.printTable(tableRaportOpakowania,"Zamowienie nr: " + zamowienie.numer,"Strona {0}");
    }//GEN-LAST:event_buttonRODrukujActionPerformed

    private void buttonRaportKodyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRaportKodyActionPerformed
        Zamowienie z = (Zamowienie) cbListaZamowien.getSelectedItem();
        loadDataRaportKody(z);
        frameRaportKody.setVisible(true);
    }//GEN-LAST:event_buttonRaportKodyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        printTable(tableRaportKody,"Zamowienie nr: " + zamowienie.numer,"Strona {0}");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonRaportOkuciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRaportOkuciaActionPerformed
        Zamowienie z = (Zamowienie) cbListaZamowien.getSelectedItem();
        loadDataRaportOkucia(z);
        frameRaportOkucia.setVisible(true);
    }//GEN-LAST:event_buttonRaportOkuciaActionPerformed

    private void bDrukujOkuciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDrukujOkuciaActionPerformed
         printTable(tableOkucia,"Zamowienie nr: " + zamowienie.numer,"Strona {0}");
    }//GEN-LAST:event_bDrukujOkuciaActionPerformed

    private void buttonRODrukuj1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRODrukuj1ActionPerformed
        this.printTable(tableRaportOpakowania1,"Zamowienie nr: " + zamowienie.numer,"Strona {0}");
    }//GEN-LAST:event_buttonRODrukuj1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Zamowienie z = (Zamowienie) cbListaZamowien.getSelectedItem();
        loadDataRaportOpakowania3(z);
        frameRaportOpakowania3.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDodajActionPerformed
        Zamowienie z = (Zamowienie) cbZamowienia.getSelectedItem();
        ArrayList<RaportOpakowania3> data = raportO3.getData(z);
        ArrayList<RaportOpakowania3> modelData = tmro3.getModelData();
        ArrayList<RaportOpakowania3> newList = new ArrayList();
        newList.addAll(modelData);
        boolean exist = false;
        
        newList.addAll(data);

        newList = raportO3.laczKartony(newList);
        tmro3.setModelData(newList);
//        tmro3.setModelData(raportO3.laczKartony(newList));
        updateRowHeights(tableRaportOpakowania1);
        tca3.adjustColumns();
//        tableRaportOpakowania1.
    }//GEN-LAST:event_bDodajActionPerformed

    private void bLaczActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLaczActionPerformed
        ArrayList<RaportOpakowania3> modelData = tmro3.getModelData();
        tmro3.setModelData(raportO3.laczKartony(modelData));
         updateRowHeights(tableRaportOpakowania1);
        tca3.adjustColumns();
    }//GEN-LAST:event_bLaczActionPerformed

    private void bCSVExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCSVExportActionPerformed
        if(!this.generatingCVS) {
            this.generatingCVS = true;
            Zamowienie z = (Zamowienie) cbListaZamowien.getSelectedItem();
            this.bCSVExport.setEnabled(false);
            this.bCSVExport.setText("Tworzenie CSV");
            CSVData csv = new CSVData();
            ArrayList<CSVData> d = new ArrayList();
            if (usunPrioCB.isSelected()){
                d = csv.getData2(z.numer);
            } else {
                d = csv.getData(z.numer);
            }
            csv.exportCSV(d, z.numer);
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(GRaporty.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.bCSVExport.setEnabled(true);
            this.bCSVExport.setText("Export CSV");
            this.generatingCVS = false;
        }
        
    }//GEN-LAST:event_bCSVExportActionPerformed

    private void cbListaZamowienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbListaZamowienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbListaZamowienActionPerformed

    private void qrCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qrCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qrCheckBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCSVExport;
    private javax.swing.JButton bDodaj;
    private javax.swing.JButton bDrukujOkucia;
    private javax.swing.JButton bLacz;
    private javax.swing.JButton buttonRODrukuj;
    private javax.swing.JButton buttonRODrukuj1;
    private javax.swing.JButton buttonRaportKody;
    private javax.swing.JButton buttonRaportOkucia;
    private javax.swing.JButton buttonRaportOpakowania;
    private javax.swing.JButton buttonRaportSzczegolowyDrukuj;
    private javax.swing.JButton buttonRaportSzczegolwy;
    private javax.swing.JComboBox cbListaZamowien;
    private javax.swing.JComboBox cbZamowienia;
    private javax.swing.JFrame frameRaportKody;
    private javax.swing.JFrame frameRaportOkucia;
    private javax.swing.JFrame frameRaportOpakowania;
    private javax.swing.JFrame frameRaportOpakowania3;
    private javax.swing.JFrame frameRaportSzczegolowy;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelRaportSzczegolowyNumer;
    private javax.swing.JCheckBox qrCheckBox;
    private javax.swing.JTable tableOkucia;
    private javax.swing.JTable tableRaportKody;
    private javax.swing.JTable tableRaportOpakowania;
    private javax.swing.JTable tableRaportOpakowania1;
    private javax.swing.JTable tableRaportSzczegolwy;
    private javax.swing.JCheckBox usunPrioCB;
    // End of variables declaration//GEN-END:variables
    private boolean generatingCVS = false;   
}
