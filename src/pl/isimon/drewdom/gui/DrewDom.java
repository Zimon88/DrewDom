/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import pl.isimon.drewdom.Element;
import pl.isimon.drewdom.Mebel;
import pl.isimon.drewdom.Okucie;
import pl.isimon.drewdom.Pracownik;
import pl.isimon.drewdom.Zamowienie;
import pl.isimon.drewdom.gui.utils.RotatedLabel;
import pl.isimon.drewdom.gui.utils.TableColumnAdjuster;

/**
 *
 * @author Simon
 * @version 0.0.0.2
 * 
 */
public class DrewDom extends javax.swing.JFrame {

    /**
     * Creates new form DrewDom
     */
    public Zamowienie zamowienie;
    public Pracownik pracownik;
    public Mebel mebel;
    public ArrayList<Mebel> listaMebli;
    public Okucie okucie;
    public Element element;
    public ArrayList<Okucie> listaOkuc;
    public ArrayList<Zamowienie> listaZamowien;
    private boolean kontynuujDodawanie = false;
    
    
    public DrewDom() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        zamowienie = new Zamowienie();
        pracownik = new Pracownik();
        element = new Element();
        mebel = new Mebel();
        okucie = new Okucie();
//        listaMebli = mebel.getData();
//        listaOkuc = okucie.getData();
//        listaZamowien = zamowienie.getData();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changelog = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        changelogPanel = new javax.swing.JEditorPane();
        rotatedLabel1 = new pl.isimon.drewdom.gui.utils.RotatedLabel();
        noweZamowienie = new javax.swing.JFrame();
        panelNoweZamowienie = new pl.isimon.drewdom.gui.GZamowienieNew(listaMebli);
        buttonNZZapisz = new javax.swing.JButton();
        butonNZWyczysc = new javax.swing.JButton();
        buttonNZAnuluj = new javax.swing.JButton();
        nowyMebel = new javax.swing.JFrame();
        buttonNMZapisz = new javax.swing.JButton();
        buttonAnuluj = new javax.swing.JButton();
        buttonWyczysc = new javax.swing.JButton();
        panelNowyMebel = new pl.isimon.drewdom.gui.GMebelNowy();
        nowyMebelDialog = new javax.swing.JDialog();
        buttonOk = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbContinue = new javax.swing.JCheckBox();
        szukajKartonu = new javax.swing.JFrame();
        gSzukajKartonu1 = new pl.isimon.drewdom.gui.GSzukajKartonu();
        tabPanel = new javax.swing.JTabbedPane();
        panelTabZamowienia = new pl.isimon.drewdom.gui.GZamowienieList();
        panelTabMeble = new pl.isimon.drewdom.gui.GMebleLista();
        panelTabOkucia = new pl.isimon.drewdom.gui.GOkucia();
        panelTabRaporty = new pl.isimon.drewdom.gui.GRaporty();
        tollBar = new javax.swing.JToolBar();
        buttonChangelog = new javax.swing.JButton();
        buttonNoweZamowienie = new javax.swing.JButton();
        buttonNowyMebel = new javax.swing.JButton();
        buttonSzukajKartonu = new javax.swing.JButton();

        changelog.setTitle("ChangeLog");
        changelog.setMinimumSize(new java.awt.Dimension(640, 480));

        jLabel1.setText("Changelog:");

        changelogPanel.setContentType("text/html"); // NOI18N
        changelogPanel.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n    <p style=\"margin-top: 0\">\r\n      \r<ul>\n                <li>\n                     0.0.0.1 2012-07-18\n                     <ul>\n                         <li>- panel lista zamowien</li>\n                         <li>- panel pracownik</li>\n                         <li>- panel lista mebli</li>\n                     </ul>\n                </li>\n            </ul>\n    </p>\r\n  </body>\r\n</html>\r\n");
        jScrollPane2.setViewportView(changelogPanel);
        changelogPanel.getAccessibleContext().setAccessibleDescription("");

        rotatedLabel1.setText("rotatedLabel1");

        javax.swing.GroupLayout changelogLayout = new javax.swing.GroupLayout(changelog.getContentPane());
        changelog.getContentPane().setLayout(changelogLayout);
        changelogLayout.setHorizontalGroup(
            changelogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changelogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changelogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                    .addGroup(changelogLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(changelogLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(rotatedLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        changelogLayout.setVerticalGroup(
            changelogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changelogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(rotatedLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );

        noweZamowienie.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        noweZamowienie.setTitle("Nowe Zamówienie");
        noweZamowienie.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        noweZamowienie.setForeground(java.awt.Color.white);
        noweZamowienie.setMinimumSize(new java.awt.Dimension(660, 720));

        panelNoweZamowienie.setEnabled(false);
        panelNoweZamowienie.setMinimumSize(new java.awt.Dimension(620, 580));
        panelNoweZamowienie.setPreferredSize(new java.awt.Dimension(620, 580));

        buttonNZZapisz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/filesave.png"))); // NOI18N
        buttonNZZapisz.setText("Zapisz");
        buttonNZZapisz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNZZapiszActionPerformed(evt);
            }
        });

        butonNZWyczysc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/editdelete.png"))); // NOI18N
        butonNZWyczysc.setText("Wyczyść");
        butonNZWyczysc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonNZWyczyscActionPerformed(evt);
            }
        });

        buttonNZAnuluj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_cancel.png"))); // NOI18N
        buttonNZAnuluj.setText("Anuluj");
        buttonNZAnuluj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNZAnulujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout noweZamowienieLayout = new javax.swing.GroupLayout(noweZamowienie.getContentPane());
        noweZamowienie.getContentPane().setLayout(noweZamowienieLayout);
        noweZamowienieLayout.setHorizontalGroup(
            noweZamowienieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noweZamowienieLayout.createSequentialGroup()
                .addContainerGap(5493, Short.MAX_VALUE)
                .addComponent(buttonNZAnuluj)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(butonNZWyczysc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonNZZapisz)
                .addContainerGap())
            .addComponent(panelNoweZamowienie, javax.swing.GroupLayout.DEFAULT_SIZE, 5800, Short.MAX_VALUE)
        );

        noweZamowienieLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {butonNZWyczysc, buttonNZAnuluj, buttonNZZapisz});

        noweZamowienieLayout.setVerticalGroup(
            noweZamowienieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(noweZamowienieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNoweZamowienie, javax.swing.GroupLayout.PREFERRED_SIZE, 573, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(noweZamowienieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNZZapisz)
                    .addComponent(butonNZWyczysc)
                    .addComponent(buttonNZAnuluj))
                .addContainerGap())
        );

        nowyMebel.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        nowyMebel.setMinimumSize(new java.awt.Dimension(720, 640));

        buttonNMZapisz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/filesave.png"))); // NOI18N
        buttonNMZapisz.setText("Zapisz");
        buttonNMZapisz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNMZapiszActionPerformed(evt);
            }
        });

        buttonAnuluj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/cancel.png"))); // NOI18N
        buttonAnuluj.setText("Anuluj");
        buttonAnuluj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnulujActionPerformed(evt);
            }
        });

        buttonWyczysc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/editdelete.png"))); // NOI18N
        buttonWyczysc.setText("Wyczyść");
        buttonWyczysc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonWyczyscActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nowyMebelLayout = new javax.swing.GroupLayout(nowyMebel.getContentPane());
        nowyMebel.getContentPane().setLayout(nowyMebelLayout);
        nowyMebelLayout.setHorizontalGroup(
            nowyMebelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nowyMebelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nowyMebelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(nowyMebelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonNMZapisz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonWyczysc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAnuluj))
                    .addComponent(panelNowyMebel, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
                .addContainerGap())
        );
        nowyMebelLayout.setVerticalGroup(
            nowyMebelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nowyMebelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelNowyMebel, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(nowyMebelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonWyczysc)
                    .addComponent(buttonAnuluj)
                    .addComponent(buttonNMZapisz))
                .addContainerGap())
        );

        nowyMebelDialog.setLocationByPlatform(true);
        nowyMebelDialog.setMinimumSize(new java.awt.Dimension(260, 150));
        nowyMebelDialog.setModal(true);

        buttonOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_ok.png"))); // NOI18N
        buttonOk.setText("OK");
        buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x32/apply.png"))); // NOI18N
        jLabel2.setText("Dodano nowy mebel do bazy danych");

        cbContinue.setSelected(true);
        cbContinue.setText("Kontynuuj dodawanie");

        javax.swing.GroupLayout nowyMebelDialogLayout = new javax.swing.GroupLayout(nowyMebelDialog.getContentPane());
        nowyMebelDialog.getContentPane().setLayout(nowyMebelDialogLayout);
        nowyMebelDialogLayout.setHorizontalGroup(
            nowyMebelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nowyMebelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nowyMebelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addGroup(nowyMebelDialogLayout.createSequentialGroup()
                        .addComponent(cbContinue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonOk)))
                .addContainerGap())
        );
        nowyMebelDialogLayout.setVerticalGroup(
            nowyMebelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nowyMebelDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(nowyMebelDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbContinue)
                    .addComponent(buttonOk))
                .addContainerGap())
        );

        szukajKartonu.setTitle("Szukaj kartonu");
        szukajKartonu.setMinimumSize(new java.awt.Dimension(560, 340));

        javax.swing.GroupLayout szukajKartonuLayout = new javax.swing.GroupLayout(szukajKartonu.getContentPane());
        szukajKartonu.getContentPane().setLayout(szukajKartonuLayout);
        szukajKartonuLayout.setHorizontalGroup(
            szukajKartonuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, szukajKartonuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gSzukajKartonu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        szukajKartonuLayout.setVerticalGroup(
            szukajKartonuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(szukajKartonuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gSzukajKartonu1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DrewDom ver 0.0.0.1");
        setMinimumSize(new java.awt.Dimension(680, 700));
        setName("DrewDom ver. 0.0.0.1"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tabPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabPanel.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabPanelStateChanged(evt);
            }
        });
        tabPanel.addTab("Zamowienia", new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x22/spreadsheet.png")), panelTabZamowienia); // NOI18N
        tabPanel.addTab("Meble", new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x22/furniture.png")), panelTabMeble); // NOI18N
        tabPanel.addTab("Okucia", new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x22/exec.png")), panelTabOkucia); // NOI18N
        tabPanel.addTab("Raporty", panelTabRaporty);

        tollBar.setFloatable(false);
        tollBar.setRollover(true);

        buttonChangelog.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x48/make.png"))); // NOI18N
        buttonChangelog.setText("Changelog");
        buttonChangelog.setEnabled(false);
        buttonChangelog.setFocusable(false);
        buttonChangelog.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonChangelog.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonChangelog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonChangelogActionPerformed(evt);
            }
        });
        tollBar.add(buttonChangelog);

        buttonNoweZamowienie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x48/spreadsheet.png"))); // NOI18N
        buttonNoweZamowienie.setText("Nowe Zamowienie");
        buttonNoweZamowienie.setFocusable(false);
        buttonNoweZamowienie.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNoweZamowienie.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNoweZamowienie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNoweZamowienieActionPerformed(evt);
            }
        });
        tollBar.add(buttonNoweZamowienie);

        buttonNowyMebel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x48/furniture.png"))); // NOI18N
        buttonNowyMebel.setText("Nowy Mebel");
        buttonNowyMebel.setFocusable(false);
        buttonNowyMebel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonNowyMebel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonNowyMebel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNowyMebelActionPerformed(evt);
            }
        });
        tollBar.add(buttonNowyMebel);

        buttonSzukajKartonu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x48/filefind.png"))); // NOI18N
        buttonSzukajKartonu.setText("Szukaj Kartonu");
        buttonSzukajKartonu.setFocusable(false);
        buttonSzukajKartonu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSzukajKartonu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonSzukajKartonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSzukajKartonuActionPerformed(evt);
            }
        });
        tollBar.add(buttonSzukajKartonu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tollBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(tollBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tabPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPanel.getAccessibleContext().setAccessibleName("Zamowienia");
        tabPanel.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonChangelogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonChangelogActionPerformed
        changelog.setVisible(true);
    }//GEN-LAST:event_buttonChangelogActionPerformed

    private void buttonNoweZamowienieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNoweZamowienieActionPerformed
        panelNoweZamowienie.clear();
        noweZamowienie.setVisible(true);
        panelNoweZamowienie.loadData();
    }//GEN-LAST:event_buttonNoweZamowienieActionPerformed

    private void buttonNowyMebelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNowyMebelActionPerformed
        panelNowyMebel.reset();
        nowyMebel.setVisible(true);
    }//GEN-LAST:event_buttonNowyMebelActionPerformed

    private void buttonNZAnulujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNZAnulujActionPerformed
        panelNoweZamowienie.clear();
        noweZamowienie.dispose();
    }//GEN-LAST:event_buttonNZAnulujActionPerformed

    private void buttonNZZapiszActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNZZapiszActionPerformed
        panelNoweZamowienie.save();
        noweZamowienie.dispose();
        panelTabZamowienia.loadData();
        panelNoweZamowienie.clear();
    }//GEN-LAST:event_buttonNZZapiszActionPerformed

    private void butonNZWyczyscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonNZWyczyscActionPerformed
        panelNoweZamowienie.clear();
    }//GEN-LAST:event_butonNZWyczyscActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         // TODO add your handling code here:
        try{
            String source = "res\\drewdom.db";
            String dest ="backup\\drewdom.db.";
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd-HH_mm");
	    Date date = new Date();
            dest += dateFormat.format(date);
            dest += ".backup";
            File f1 = new File(source);
            File f2 = new File(dest);
            InputStream in = new FileInputStream(f1);
            OutputStream out = new FileOutputStream(f2);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied. "+ dest);
         } catch(FileNotFoundException ex){
            System.out.println(ex.getMessage() + " in the specified directory.");
            System.exit(0);
         } catch(IOException e){
            System.out.println(e.getMessage());  
         }
  
        
        System.out.println("UTWORZONO BACKUP");
    }//GEN-LAST:event_formWindowClosing

    private void buttonNMZapiszActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNMZapiszActionPerformed
        
        panelNowyMebel.actionSave();
        nowyMebelDialog.setVisible(true);
        if(!kontynuujDodawanie) nowyMebel.dispose();
        panelNowyMebel.reset();
        panelTabMeble.loadData();
    }//GEN-LAST:event_buttonNMZapiszActionPerformed

    private void buttonAnulujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnulujActionPerformed
        nowyMebel.dispose();
        panelNowyMebel.reset();
    }//GEN-LAST:event_buttonAnulujActionPerformed

    private void buttonWyczyscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonWyczyscActionPerformed
        panelNowyMebel.reset();
    }//GEN-LAST:event_buttonWyczyscActionPerformed

    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
         // TODO add your handling code here:
        nowyMebelDialog.setVisible(false);
        kontynuujDodawanie = cbContinue.isSelected();
    }//GEN-LAST:event_buttonOkActionPerformed

    private void tabPanelStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabPanelStateChanged
         int selection = tabPanel.getSelectedIndex();
         switch (selection){
             case 0: panelTabZamowienia.loadData(); break;
             case 1: panelTabMeble.loadData(); break;
             case 2: panelTabOkucia.loadData(); break;
             case 3: panelTabRaporty.loadData(); break;
         }
                 
    }//GEN-LAST:event_tabPanelStateChanged

    private void buttonSzukajKartonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSzukajKartonuActionPerformed
        szukajKartonu.setVisible(true);
    }//GEN-LAST:event_buttonSzukajKartonuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DrewDom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DrewDom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DrewDom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DrewDom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DrewDom().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butonNZWyczysc;
    private javax.swing.JButton buttonAnuluj;
    private javax.swing.JButton buttonChangelog;
    private javax.swing.JButton buttonNMZapisz;
    private javax.swing.JButton buttonNZAnuluj;
    private javax.swing.JButton buttonNZZapisz;
    private javax.swing.JButton buttonNoweZamowienie;
    private javax.swing.JButton buttonNowyMebel;
    private javax.swing.JButton buttonOk;
    private javax.swing.JButton buttonSzukajKartonu;
    private javax.swing.JButton buttonWyczysc;
    private javax.swing.JCheckBox cbContinue;
    private javax.swing.JFrame changelog;
    private javax.swing.JEditorPane changelogPanel;
    private pl.isimon.drewdom.gui.GSzukajKartonu gSzukajKartonu1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JFrame noweZamowienie;
    private javax.swing.JFrame nowyMebel;
    private javax.swing.JDialog nowyMebelDialog;
    private pl.isimon.drewdom.gui.GZamowienieNew panelNoweZamowienie;
    private pl.isimon.drewdom.gui.GMebelNowy panelNowyMebel;
    private pl.isimon.drewdom.gui.GMebleLista panelTabMeble;
    private pl.isimon.drewdom.gui.GOkucia panelTabOkucia;
    private pl.isimon.drewdom.gui.GRaporty panelTabRaporty;
    private pl.isimon.drewdom.gui.GZamowienieList panelTabZamowienia;
    private pl.isimon.drewdom.gui.utils.RotatedLabel rotatedLabel1;
    private javax.swing.JFrame szukajKartonu;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JToolBar tollBar;
    // End of variables declaration//GEN-END:variables
}
