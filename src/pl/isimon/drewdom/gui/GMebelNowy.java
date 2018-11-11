/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.isimon.drewdom.gui;

import java.util.ArrayList;
import pl.isimon.drewdom.Element;
import pl.isimon.drewdom.ElementPozycja;
import pl.isimon.drewdom.Mebel;
import pl.isimon.drewdom.Okucie;
import pl.isimon.drewdom.OkuciePozycja;
import pl.isimon.drewdom.Opakowanie;
import pl.isimon.drewdom.gui.models.ComboBoxModelOkucia;
import pl.isimon.drewdom.gui.models.TableModelMebelElement;
import pl.isimon.drewdom.gui.models.TableModelMebelElementPozycja;
import pl.isimon.drewdom.gui.models.TableModelOkuciePozycja;
import pl.isimon.drewdom.gui.models.TableModelOpakowanie;
import pl.isimon.drewdom.gui.utils.CheckBoxCellRenderer;
import pl.isimon.drewdom.gui.utils.TableColumnAdjuster;
import pl.isimon.drewdom.gui.utils.ZadaniaCellRenderer;

/**
 *
 * @author Simon
 */
public class GMebelNowy extends javax.swing.JPanel {

    /**
     * Creates new form GMebelNowy
     */
    public boolean edycja = false;
    private boolean edycjaOpakowania = false;
    private boolean edycjaElement = false;
    private TableModelOpakowanie tmo;
    private ArrayList<Opakowanie> opakowanieLista = null;
    private TableModelOkuciePozycja tmop;
    private ArrayList<OkuciePozycja> okucieLista = null;
    private ComboBoxModelOkucia ocbm;
    private Okucie okucie;
    private TableModelMebelElementPozycja tmmep;
    private ArrayList<ElementPozycja> elementLista = null;
    private ElementPozycja elementPozycja;
    private TableColumnAdjuster tcaTE;
    private TableColumnAdjuster tcaTIE;
    private TableModelMebelElement tmme;
    private Element element;
    private Mebel mebel;
    private boolean preview = false;
    private String oldNumber;

    public ArrayList<Opakowanie> getOpakowanieLista() {
        return opakowanieLista;
    }

    public ArrayList<OkuciePozycja> getOkucieLista() {
        return okucieLista;
    }

    public ArrayList<ElementPozycja> getElementLista() {
        return elementLista;
    }

    public String getNazwa() {
        return textNazwa.getText();
    }

    public String getNumer() {
        return textNumer.getText();
    }
    
    public String getKod() {
        return textKod.getText();
    }

    public void setPreview(boolean preview) {
        this.preview = preview;
    }
    
    public GMebelNowy() {
        initComponents();
        mebel = new Mebel();
        elementPozycja = new ElementPozycja();
        okucie = new Okucie();
        element = new Element();
        opakowanieLista = new ArrayList();
        okucieLista = new ArrayList();
        elementLista = new ArrayList();
        ocbm = (ComboBoxModelOkucia)cbOkucia.getModel();
        tmo = (TableModelOpakowanie)tableOpakowanie.getModel();
        tmop = (TableModelOkuciePozycja)tableOkucia.getModel();
        tmmep = (TableModelMebelElementPozycja) tableElementy.getModel();
        tmme = (TableModelMebelElement) tableIstniejaceElementy.getModel();
        //tmo.setModelData(opakowanieLista);
        //tmop.setModelData(okucieLista);
        //tmmep.setModelData(elementLista);
        tableElementy.getColumnModel().getColumn(4).setCellRenderer(new ZadaniaCellRenderer());
        tableElementy.getColumnModel().getColumn(5).setCellRenderer(new CheckBoxCellRenderer());
        tcaTE = new TableColumnAdjuster(tableElementy);
        tcaTIE = new TableColumnAdjuster(tableIstniejaceElementy);
        dialogEdycjaWarning.setLocationRelativeTo(null);
    }
    
    public void loadData(){
        tmmep.setModelData(new ArrayList());
        ocbm.setModelData(okucie.getData());
        tmme.setModelData(element.getData());
        tableIstniejaceElementy.getColumnModel().getColumn(5).setCellRenderer(new ZadaniaCellRenderer());
        tcaTIE.adjustColumns();
        tcaTE.adjustColumns();
    }
    
    public void loadData(Mebel m){
        tmmep.setModelData(new ArrayList());
        System.out.println("1");
        //ocbm.setModelData(okucie.getData());
        //tmme.setModelData(element.getData());
        opakowanieLista = (new Opakowanie()).getData(m.numerKatalogowy);
        okucieLista = (new OkuciePozycja()).getData(m.numerKatalogowy);
        elementLista = elementPozycja.getData(m.numerKatalogowy,1);
        tableIstniejaceElementy.getColumnModel().getColumn(5).setCellRenderer(new ZadaniaCellRenderer());
        tcaTIE.adjustColumns();
        tmo.setModelData(opakowanieLista);
        tmop.setModelData(okucieLista);
        tmmep.setModelData(elementLista);
        textNazwa.setText(m.nazwa);
        textKod.setText(m.kod);
        textNumer.setText(m.numerKatalogowy);
        tcaTE.adjustColumns();
        if(edycja) {
            this.oldNumber = m.numerKatalogowy;
            //textNumer.setEnabled(false);
        }
        
    }
    
    public void actionSave(){
        Mebel m = new Mebel();
        m.nazwa = getNazwa();
        m.numerKatalogowy = getNumer();
        m.kod = getKod();
        if(edycja){
            
            if (m.numerKatalogowy == null ? oldNumber != null : !m.numerKatalogowy.equals(oldNumber)){
                System.out.println("UPDATING PRODUCT NUMBER");
                mebel.edytuj(m, getElementLista(), getOkucieLista(), getOpakowanieLista(), oldNumber);
            } else {
                mebel.edytuj(m, getElementLista(), getOkucieLista(), getOpakowanieLista());
            }
            edycja = false;
        } else {
            mebel.dodaj(m, getElementLista(), getOkucieLista(), getOpakowanieLista());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogEdycjaWarning = new javax.swing.JDialog();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        labelNumer = new javax.swing.JLabel();
        labelNazwa = new javax.swing.JLabel();
        textNumer = new javax.swing.JTextField();
        textNazwa = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelElementy = new javax.swing.JPanel();
        buttonUsun = new javax.swing.JButton();
        buttonEdycja = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableElementy = new javax.swing.JTable();
        tabpanelElement = new javax.swing.JTabbedPane();
        panelNowyElement = new javax.swing.JPanel();
        buttonElementNowyDodaj = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbZadKlej = new javax.swing.JCheckBox();
        cbZadPila = new javax.swing.JCheckBox();
        cbZadCnc = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        textElementNowyNazwa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        spinnerNeW1 = new javax.swing.JSpinner();
        spinnerNeW2 = new javax.swing.JSpinner();
        spinnerNeW3 = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        spinnerNeIlosc = new javax.swing.JSpinner();
        jLabel14 = new javax.swing.JLabel();
        spinnerWyd = new javax.swing.JSpinner();
        jLabel15 = new javax.swing.JLabel();
        panelIstnejacyElement = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableIstniejaceElementy = new javax.swing.JTable();
        buttonElementIstSzukaj = new javax.swing.JButton();
        textSzukajNazwaMebla = new javax.swing.JTextField();
        textSzukajNumerMebla = new javax.swing.JTextField();
        textSzukajNazwaCzesci = new javax.swing.JTextField();
        buttonIEDodaj = new javax.swing.JButton();
        spinnerIEIlosc = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        panelOkucia = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableOkucia = new javax.swing.JTable();
        cbOkucia = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        buttonODodaj = new javax.swing.JButton();
        buttonOUsun = new javax.swing.JButton();
        spinnerOIlosc = new javax.swing.JSpinner();
        panelOpakowanie = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableOpakowanie = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        buttonOpakUsun = new javax.swing.JButton();
        buttonOpakEdytuj = new javax.swing.JButton();
        buttonOpakZapisz = new javax.swing.JButton();
        spinnerWym1 = new javax.swing.JSpinner();
        spinnerWym2 = new javax.swing.JSpinner();
        spinnerWym3 = new javax.swing.JSpinner();
        buttonOpakAnuluj = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        textKod = new javax.swing.JTextField();

        dialogEdycjaWarning.setMinimumSize(new java.awt.Dimension(300, 100));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_ok.png"))); // NOI18N
        jButton1.setText("Ok");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x32/messagebox_warning.png"))); // NOI18N
        jLabel13.setText("<html><b>Uwaga!!</b><br />Edytując ten element zmienisz również parametry innych mebli, do których jest on przypisany </html>");

        javax.swing.GroupLayout dialogEdycjaWarningLayout = new javax.swing.GroupLayout(dialogEdycjaWarning.getContentPane());
        dialogEdycjaWarning.getContentPane().setLayout(dialogEdycjaWarningLayout);
        dialogEdycjaWarningLayout.setHorizontalGroup(
            dialogEdycjaWarningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogEdycjaWarningLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dialogEdycjaWarningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialogEdycjaWarningLayout.createSequentialGroup()
                        .addGap(0, 208, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        dialogEdycjaWarningLayout.setVerticalGroup(
            dialogEdycjaWarningLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogEdycjaWarningLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(12, 12, 12))
        );

        setMinimumSize(new java.awt.Dimension(620, 550));
        setPreferredSize(new java.awt.Dimension(620, 550));

        labelNumer.setText("Numer");

        labelNazwa.setText("Nazwa");

        buttonUsun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/edittrash.png"))); // NOI18N
        buttonUsun.setText("Usuń");
        buttonUsun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUsunActionPerformed(evt);
            }
        });

        buttonEdycja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/editpaste.png"))); // NOI18N
        buttonEdycja.setText("Edytuj");
        buttonEdycja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEdycjaActionPerformed(evt);
            }
        });

        tableElementy.setAutoCreateRowSorter(true);
        tableElementy.setModel(new TableModelMebelElementPozycja());
        tableElementy.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tableElementy);

        buttonElementNowyDodaj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_ok.png"))); // NOI18N
        buttonElementNowyDodaj.setText("Dodaj");
        buttonElementNowyDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonElementNowyDodajActionPerformed(evt);
            }
        });

        jLabel1.setText("Zadania");

        cbZadKlej.setText("Klejenie");

        cbZadPila.setText("Piła");

        cbZadCnc.setText("CnC");

        jLabel2.setText("Nazwa elementu:");

        jLabel4.setText("Wymiar:");

        spinnerNeW1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9999, 1));

        spinnerNeW2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9999, 1));

        spinnerNeW3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9999, 1));

        jLabel5.setText("x");

        jLabel6.setText("x");

        jLabel7.setText("Sztuk:");

        spinnerNeIlosc.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));

        jLabel14.setText("z jednego blatu");

        spinnerWyd.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));

        jLabel15.setText("Sztuk");

        javax.swing.GroupLayout panelNowyElementLayout = new javax.swing.GroupLayout(panelNowyElement);
        panelNowyElement.setLayout(panelNowyElementLayout);
        panelNowyElementLayout.setHorizontalGroup(
            panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNowyElementLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNowyElementLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNowyElementLayout.createSequentialGroup()
                        .addComponent(textElementNowyNazwa)
                        .addGap(18, 18, 18)
                        .addComponent(cbZadKlej, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNowyElementLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbZadPila, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelNowyElementLayout.createSequentialGroup()
                        .addComponent(spinnerNeW1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelNowyElementLayout.createSequentialGroup()
                                .addComponent(spinnerNeW2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spinnerNeW3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                                .addComponent(cbZadCnc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNowyElementLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNowyElementLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinnerNeIlosc, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonElementNowyDodaj))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNowyElementLayout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(spinnerWyd, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14)
                                        .addGap(23, 23, 23)))))))
                .addContainerGap())
        );
        panelNowyElementLayout.setVerticalGroup(
            panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNowyElementLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbZadKlej)
                    .addComponent(textElementNowyNazwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbZadPila)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbZadCnc)
                    .addComponent(spinnerNeW1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerNeW3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(spinnerNeW2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerWyd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(panelNowyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonElementNowyDodaj)
                    .addComponent(spinnerNeIlosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        tabpanelElement.addTab("Nowy element", panelNowyElement);

        tableIstniejaceElementy.setAutoCreateRowSorter(true);
        tableIstniejaceElementy.setModel(new TableModelMebelElement());
        jScrollPane3.setViewportView(tableIstniejaceElementy);

        buttonElementIstSzukaj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/viewmag.png"))); // NOI18N
        buttonElementIstSzukaj.setText("Szukaj");
        buttonElementIstSzukaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonElementIstSzukajActionPerformed(evt);
            }
        });

        textSzukajNazwaMebla.setToolTipText("Nazwa mebla");
        textSzukajNazwaMebla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSzukajNazwaMeblaActionPerformed(evt);
            }
        });

        textSzukajNumerMebla.setToolTipText("Numer Mebla");
        textSzukajNumerMebla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSzukajNumerMeblaActionPerformed(evt);
            }
        });

        textSzukajNazwaCzesci.setToolTipText("Nazwa części");
        textSzukajNazwaCzesci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textSzukajNazwaCzesciActionPerformed(evt);
            }
        });

        buttonIEDodaj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_ok.png"))); // NOI18N
        buttonIEDodaj.setText("Dodaj");
        buttonIEDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIEDodajActionPerformed(evt);
            }
        });

        spinnerIEIlosc.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));

        jLabel8.setText("Sztuk:");

        javax.swing.GroupLayout panelIstnejacyElementLayout = new javax.swing.GroupLayout(panelIstnejacyElement);
        panelIstnejacyElement.setLayout(panelIstnejacyElementLayout);
        panelIstnejacyElementLayout.setHorizontalGroup(
            panelIstnejacyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIstnejacyElementLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelIstnejacyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addGroup(panelIstnejacyElementLayout.createSequentialGroup()
                        .addComponent(textSzukajNazwaMebla, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSzukajNumerMebla, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textSzukajNazwaCzesci, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonElementIstSzukaj))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIstnejacyElementLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinnerIEIlosc, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonIEDodaj)))
                .addContainerGap())
        );

        panelIstnejacyElementLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {textSzukajNazwaCzesci, textSzukajNazwaMebla, textSzukajNumerMebla});

        panelIstnejacyElementLayout.setVerticalGroup(
            panelIstnejacyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIstnejacyElementLayout.createSequentialGroup()
                .addGroup(panelIstnejacyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonElementIstSzukaj)
                    .addComponent(textSzukajNazwaMebla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSzukajNumerMebla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textSzukajNazwaCzesci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelIstnejacyElementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonIEDodaj)
                    .addComponent(spinnerIEIlosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        tabpanelElement.addTab("Istnejący element", panelIstnejacyElement);

        javax.swing.GroupLayout panelElementyLayout = new javax.swing.GroupLayout(panelElementy);
        panelElementy.setLayout(panelElementyLayout);
        panelElementyLayout.setHorizontalGroup(
            panelElementyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelElementyLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelElementyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabpanelElement)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelElementyLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonEdycja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUsun)))
                .addContainerGap())
        );
        panelElementyLayout.setVerticalGroup(
            panelElementyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelElementyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelElementyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonUsun)
                    .addComponent(buttonEdycja))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabpanelElement, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Elementy", panelElementy);

        tableOkucia.setAutoCreateRowSorter(true);
        tableOkucia.setModel(new TableModelOkuciePozycja());
        jScrollPane1.setViewportView(tableOkucia);

        cbOkucia.setModel(new pl.isimon.drewdom.gui.models.ComboBoxModelOkucia());

        jLabel3.setText("Sztuk");

        buttonODodaj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_ok.png"))); // NOI18N
        buttonODodaj.setText("Dodaj");
        buttonODodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonODodajActionPerformed(evt);
            }
        });

        buttonOUsun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/edittrash.png"))); // NOI18N
        buttonOUsun.setText("Usuń");
        buttonOUsun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOUsunActionPerformed(evt);
            }
        });

        spinnerOIlosc.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));

        javax.swing.GroupLayout panelOkuciaLayout = new javax.swing.GroupLayout(panelOkucia);
        panelOkucia.setLayout(panelOkuciaLayout);
        panelOkuciaLayout.setHorizontalGroup(
            panelOkuciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOkuciaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOkuciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelOkuciaLayout.createSequentialGroup()
                        .addComponent(cbOkucia, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinnerOIlosc, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonODodaj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(buttonOUsun)))
                .addContainerGap())
        );
        panelOkuciaLayout.setVerticalGroup(
            panelOkuciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOkuciaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOkuciaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbOkucia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(spinnerOIlosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonODodaj)
                    .addComponent(buttonOUsun))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Okucia", panelOkucia);

        tableOpakowanie.setAutoCreateRowSorter(true);
        tableOpakowanie.setModel(new TableModelOpakowanie());
        jScrollPane4.setViewportView(tableOpakowanie);

        jLabel9.setText("x");

        jLabel10.setText("x");

        jLabel11.setText("Wymia:r");

        buttonOpakUsun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/edittrash.png"))); // NOI18N
        buttonOpakUsun.setText("Usuń");
        buttonOpakUsun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpakUsunActionPerformed(evt);
            }
        });

        buttonOpakEdytuj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/editpaste.png"))); // NOI18N
        buttonOpakEdytuj.setText("Edytuj");
        buttonOpakEdytuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpakEdytujActionPerformed(evt);
            }
        });

        buttonOpakZapisz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_ok.png"))); // NOI18N
        buttonOpakZapisz.setText("Dodaj");
        buttonOpakZapisz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpakZapiszActionPerformed(evt);
            }
        });

        spinnerWym1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9999, 1));

        spinnerWym2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9999, 1));

        spinnerWym3.setModel(new javax.swing.SpinnerNumberModel(0, 0, 9999, 1));

        buttonOpakAnuluj.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_cancel.png"))); // NOI18N
        buttonOpakAnuluj.setText("Anuluj");
        buttonOpakAnuluj.setEnabled(false);
        buttonOpakAnuluj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOpakAnulujActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpakowanieLayout = new javax.swing.GroupLayout(panelOpakowanie);
        panelOpakowanie.setLayout(panelOpakowanieLayout);
        panelOpakowanieLayout.setHorizontalGroup(
            panelOpakowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpakowanieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOpakowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(panelOpakowanieLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spinnerWym1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerWym2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerWym3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonOpakZapisz)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonOpakEdytuj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonOpakAnuluj)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonOpakUsun)))
                .addContainerGap())
        );

        panelOpakowanieLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {spinnerWym1, spinnerWym2, spinnerWym3});

        panelOpakowanieLayout.setVerticalGroup(
            panelOpakowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpakowanieLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOpakowanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(buttonOpakUsun)
                    .addComponent(buttonOpakEdytuj)
                    .addComponent(buttonOpakZapisz)
                    .addComponent(spinnerWym1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerWym2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerWym3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOpakAnuluj))
                .addContainerGap(310, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Opakowanie", panelOpakowanie);

        jLabel12.setText("Kod");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNumer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textNumer, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNazwa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textNazwa, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textKod, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {textKod, textNazwa, textNumer});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumer)
                    .addComponent(textNumer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNazwa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNazwa)
                    .addComponent(jLabel12)
                    .addComponent(textKod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOpakZapiszActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpakZapiszActionPerformed
        int wym1 = (int) spinnerWym1.getValue();
        int wym2 = (int) spinnerWym2.getValue();
        int wym3 = (int) spinnerWym3.getValue();
        if (wym1!=0 & wym2!=0 &wym3!=0){
            Opakowanie opakowanie = null;
            if(edycjaOpakowania){
                int selection = tableOpakowanie.getSelectedRow();
                opakowanie = tmo.getOpakowanie(selection);
                opakowanie.wymiar_x = wym1;
                opakowanie.wymiar_y = wym2;
                opakowanie.wymiar_z = wym3;
                opakowanieLista.set(selection,opakowanie);
                if(edycja) opakowanie.edytuj(getNumer(),opakowanie);
            } else {
                opakowanie = new Opakowanie();
                opakowanie.wymiar_x = wym1;
                opakowanie.wymiar_y = wym2;
                opakowanie.wymiar_z = wym3;
                opakowanieLista.add(opakowanie);
                if(edycja) opakowanie.dodaj(getNumer(),opakowanie);
            }
            tmo.setModelData(opakowanieLista);
            edycjaOpakowania = false;
                
        }
        buttonOpakZapisz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_ok.png")));
        buttonOpakZapisz.setText("Dodaj");
        spinnerWym1.setValue(0);
        spinnerWym2.setValue(0);
        spinnerWym3.setValue(0);
        
    }//GEN-LAST:event_buttonOpakZapiszActionPerformed

    private void buttonOpakUsunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpakUsunActionPerformed
        int selection = tableOpakowanie.getSelectedRow();
        if(selection!=-1) {
            Opakowanie opakowanie = tmo.getOpakowanie(selection);
            if(edycja){
                //TODO JEŻELI EDYCJA
                opakowanie.usun(getNumer(),opakowanie);
            }
            opakowanieLista.remove(opakowanie);
            tmo.setModelData(opakowanieLista);
        }
    }//GEN-LAST:event_buttonOpakUsunActionPerformed

    private void buttonOpakEdytujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpakEdytujActionPerformed
        buttonOpakZapisz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/filesave.png")));
        buttonOpakZapisz.setText("Zapisz");
        edycjaOpakowania = true;
        buttonOpakAnuluj.setEnabled(true);
        int selection = tableOpakowanie.getSelectedRow();
        if(selection!=-1) {
            Opakowanie opakowanie = tmo.getOpakowanie(selection);
            spinnerWym1.setValue(opakowanie.wymiar_x);
            spinnerWym2.setValue(opakowanie.wymiar_y);
            spinnerWym3.setValue(opakowanie.wymiar_z);
        }
        
    }//GEN-LAST:event_buttonOpakEdytujActionPerformed

    private void buttonOpakAnulujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOpakAnulujActionPerformed
        // TODO add your handling code here:
        spinnerWym1.setValue(0);
        spinnerWym2.setValue(0);
        spinnerWym3.setValue(0);
        edycjaOpakowania = false;
        buttonOpakAnuluj.setEnabled(false);
        buttonOpakZapisz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pl/isimon/drewdom/gui/images/x16/button_ok.png")));
        buttonOpakZapisz.setText("Dodaj");
    }//GEN-LAST:event_buttonOpakAnulujActionPerformed

    private void buttonODodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonODodajActionPerformed
         int ilosc = (int) spinnerOIlosc.getValue();
        if(ilosc!=0){
            OkuciePozycja o = new OkuciePozycja();
            o.okucie = (Okucie) cbOkucia.getSelectedItem();
            o.ilosc = ilosc;
            okucieLista.add(o);
            tmop.setModelData(okucieLista);
            cbOkucia.setSelectedIndex(-1);
            spinnerOIlosc.setValue(0);
            if(edycja){
                o.dodaj(getNumer(),o);
            }
        }
    }//GEN-LAST:event_buttonODodajActionPerformed

    private void buttonOUsunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOUsunActionPerformed
        int selection = tableOkucia.getSelectedRow();
        if(selection!=-1){
            OkuciePozycja op = tmop.getOkuciePozycja(selection);
            okucieLista.remove(op);
            tmop.setModelData(okucieLista);
            if(edycja){
                OkuciePozycja o = new OkuciePozycja();
                o.usun(getNumer(),op);
            }
        }
    }//GEN-LAST:event_buttonOUsunActionPerformed

    private void buttonElementNowyDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonElementNowyDodajActionPerformed
        int ilosc = (int)spinnerNeIlosc.getValue();
        int wydajnosc = 1;
        wydajnosc = (int)spinnerWyd.getValue();
        int wym1 = (int)spinnerNeW1.getValue();
        int wym2 = (int)spinnerNeW2.getValue();
        int wym3 = (int)spinnerNeW3.getValue();
        if(ilosc!=0 & wym1!=0 & wym2!=0 & wym3>=0){
            int zadanie = 0;
            if(cbZadCnc.isSelected()) zadanie+=4;
            if(cbZadPila.isSelected()) zadanie+=2;
            if(cbZadKlej.isSelected()) zadanie+=1;
            if(edycjaElement){
                ElementPozycja ep = elementPozycja;
                ElementPozycja epPom = elementPozycja;
                ep.element.nazwa = textElementNowyNazwa.getText();
                ep.ilosc = ilosc;
                ep.element.zadanie = zadanie;
                ep.element.wym1 = wym1;
                ep.element.wym2 = wym2;
                ep.element.wym3 = wym3;
                ep.element.wydajnosc = wydajnosc;
                if(!ep.nowy){
                    elementPozycja.edytuj(textKod.getText(), ep);
                }
                elementLista.set(elementLista.indexOf(epPom), ep);
                tmmep.setModelData(elementLista);
                edycjaElement = false;
                buttonElementNowyDodaj.setText("Dodaj");
                tabpanelElement.setTitleAt(0, "Nowy Element");
            } else {
                ElementPozycja ep = new ElementPozycja();
                ep.element.nazwa = textElementNowyNazwa.getText();
                ep.nowy = true;
                ep.ilosc = ilosc;
                ep.element.zadanie = zadanie;
                ep.element.wym1 = wym1;
                ep.element.wym2 = wym2;
                ep.element.wym3 = wym3;
                ep.element.wydajnosc = wydajnosc;
                elementLista.add(ep);
                tmmep.setModelData(elementLista);
                tcaTE.adjustColumns();
            }
            resetElement();
        }
        
        
    }//GEN-LAST:event_buttonElementNowyDodajActionPerformed

    private void buttonIEDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIEDodajActionPerformed
        int ilosc = (int) spinnerIEIlosc.getValue();
        int selection = tableIstniejaceElementy.getSelectedRow();
        if(ilosc!=0 & selection!=-1){
            int[] selectedRows = tableIstniejaceElementy.getSelectedRows();
//                System.out.println(selectedRows.length);
            for(int i=0;i<selectedRows.length;i++){
                Element e = tmme.getElement(selectedRows[i]);
                ElementPozycja ep = new ElementPozycja();
                ep.element.id = e.id;
                ep.element.nazwa = e.nazwa;
                ep.nowy = false;
                ep.ilosc = ilosc;
                ep.element.zadanie = e.zadanie;
                ep.element.wym1 = e.wym1;
                ep.element.wym2 = e.wym2;
                ep.element.wym3 = e.wym3;
                ep.element.wydajnosc = e.wydajnosc;
                elementLista.add(ep);
                if(edycja) ep.dodaj(textNumer.getText(), ep);
            }
            tmmep.setModelData(elementLista);
            tcaTE.adjustColumns();
            spinnerIEIlosc.setValue(0);
        }
    }//GEN-LAST:event_buttonIEDodajActionPerformed

    private void buttonUsunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUsunActionPerformed
        int selection = tableElementy.getSelectedRow();
        if(selection!=-1){
            String numer = textNumer.getText();
            int[] selectedRows = tableElementy.getSelectedRows();
            for(int i=0; i<selectedRows.length; i++){
                ElementPozycja ep = tmmep.getElementPozycja(selectedRows[i]);
                elementLista.remove(ep);
                if(edycja) {
                    ep.usun(ep,numer);
                }
            }
            tmmep.setModelData(elementLista);
        }
    }//GEN-LAST:event_buttonUsunActionPerformed

    private void buttonEdycjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEdycjaActionPerformed
        edycjaElement = true;
        int selection = tableElementy.getSelectedRow();
        if(selection!=-1){
            buttonElementNowyDodaj.setText("Zapisz");
            tabpanelElement.setTitleAt(0, "Edycja elementu");
            
            elementPozycja = tmmep.getElementPozycja(selection);
            if(!elementPozycja.nowy) {
                dialogEdycjaWarning.setVisible(true);
            }
            Element e = elementPozycja.element;
            textElementNowyNazwa.setText(e.nazwa);
            spinnerWyd.setValue(e.wydajnosc);
            spinnerNeW1.setValue(e.wym1);
            spinnerNeW2.setValue(e.wym2);
            spinnerNeW3.setValue(e.wym3);
            spinnerNeIlosc.setValue(elementPozycja.ilosc);
            int x = e.zadanie;
            int max = 7;
//            while (x!=0){
//                if(x>=max) {
//                    x=x-max;
//                    switch (max) {
//                        case 1: {cbZadKlej.setSelected(true); break;}
//                        case 2: {cbZadPila.setSelected(true); break;}
//                        case 4: {cbZadCnc.setSelected(true); break;}
//                    }
//                }
//                max = max/2;
//            }
        }
        
    }//GEN-LAST:event_buttonEdycjaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dialogEdycjaWarning.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonElementIstSzukajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonElementIstSzukajActionPerformed
        search();
    }//GEN-LAST:event_buttonElementIstSzukajActionPerformed

    private void textSzukajNazwaMeblaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSzukajNazwaMeblaActionPerformed
        search();
    }//GEN-LAST:event_textSzukajNazwaMeblaActionPerformed

    private void textSzukajNumerMeblaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSzukajNumerMeblaActionPerformed
        search();
    }//GEN-LAST:event_textSzukajNumerMeblaActionPerformed

    private void textSzukajNazwaCzesciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textSzukajNazwaCzesciActionPerformed
        search();
    }//GEN-LAST:event_textSzukajNazwaCzesciActionPerformed

    private void search(){
        String nazwaCzesci = textSzukajNazwaCzesci.getText();
        String nazwaMebla = textSzukajNazwaMebla.getText();
        String numerMebla = textSzukajNumerMebla.getText();
        tmme.setModelData(element.getData(nazwaCzesci,nazwaMebla,numerMebla));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEdycja;
    private javax.swing.JButton buttonElementIstSzukaj;
    private javax.swing.JButton buttonElementNowyDodaj;
    private javax.swing.JButton buttonIEDodaj;
    private javax.swing.JButton buttonODodaj;
    private javax.swing.JButton buttonOUsun;
    private javax.swing.JButton buttonOpakAnuluj;
    private javax.swing.JButton buttonOpakEdytuj;
    private javax.swing.JButton buttonOpakUsun;
    private javax.swing.JButton buttonOpakZapisz;
    private javax.swing.JButton buttonUsun;
    private javax.swing.JComboBox cbOkucia;
    private javax.swing.JCheckBox cbZadCnc;
    private javax.swing.JCheckBox cbZadKlej;
    private javax.swing.JCheckBox cbZadPila;
    private javax.swing.JDialog dialogEdycjaWarning;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelNazwa;
    private javax.swing.JLabel labelNumer;
    private javax.swing.JPanel panelElementy;
    private javax.swing.JPanel panelIstnejacyElement;
    private javax.swing.JPanel panelNowyElement;
    private javax.swing.JPanel panelOkucia;
    private javax.swing.JPanel panelOpakowanie;
    private javax.swing.JSpinner spinnerIEIlosc;
    private javax.swing.JSpinner spinnerNeIlosc;
    private javax.swing.JSpinner spinnerNeW1;
    private javax.swing.JSpinner spinnerNeW2;
    private javax.swing.JSpinner spinnerNeW3;
    private javax.swing.JSpinner spinnerOIlosc;
    private javax.swing.JSpinner spinnerWyd;
    private javax.swing.JSpinner spinnerWym1;
    private javax.swing.JSpinner spinnerWym2;
    private javax.swing.JSpinner spinnerWym3;
    private javax.swing.JTable tableElementy;
    private javax.swing.JTable tableIstniejaceElementy;
    private javax.swing.JTable tableOkucia;
    private javax.swing.JTable tableOpakowanie;
    private javax.swing.JTabbedPane tabpanelElement;
    private javax.swing.JTextField textElementNowyNazwa;
    private javax.swing.JTextField textKod;
    private javax.swing.JTextField textNazwa;
    private javax.swing.JTextField textNumer;
    private javax.swing.JTextField textSzukajNazwaCzesci;
    private javax.swing.JTextField textSzukajNazwaMebla;
    private javax.swing.JTextField textSzukajNumerMebla;
    // End of variables declaration//GEN-END:variables

    public void resetElement(){
        textElementNowyNazwa.setText("");
//        tmmep.setModelData(new ArrayList());
        cbZadCnc.setSelected(false);
        cbZadKlej.setSelected(false);
        cbZadPila.setSelected(false);
        spinnerNeW1.setValue(0);
        spinnerNeW2.setValue(0);
        spinnerNeW3.setValue(0);
        spinnerNeIlosc.setValue(0);
        spinnerWyd.setValue(1);
        //reload istnejace elementy
    }
    
    public void reset() {
        textNazwa.setText("");
        textNumer.setText("");
        textKod.setText("");
        elementLista = new ArrayList();
        okucieLista = new ArrayList();
        opakowanieLista = new ArrayList();
        resetElement();
        tmop.setModelData(new ArrayList());
        tmme.setModelData(element.getData());
        tmmep.setModelData(new ArrayList());
    }

    public boolean getPreview() {
        return this.preview;
    }

    


}
