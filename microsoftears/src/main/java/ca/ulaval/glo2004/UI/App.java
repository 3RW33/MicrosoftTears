
package ca.ulaval.glo2004.UI;

import ca.ulaval.glo2004.MicroRoulotte.MesureEnum;
import ca.ulaval.glo2004.MicroRoulotte.MicroRoulotteController;


import java.awt.GridLayout;
import java.awt.Point;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
// import org.graalvm.compiler.loop.DerivedOffsetInductionVariable;

public class App extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    private final MicroRoulotteController controller;
    private final DrawingPanel drawingPanel;
    public App(){
        
        initComponents();
        
        DrawingPanelContainer.setLayout(new GridLayout());

        controller = new MicroRoulotteController();
        drawingPanel = new DrawingPanel(controller);
        DrawingPanelContainer.add(drawingPanel);
        
        // Hide option panels by default
        hideOptionPanels();
       
        remplirChamps();
        
        repaint();
        
        checkUndoRedo();
        
        ImageIcon trashIcon = new ImageIcon(getClass().getResource("/trash.png"));
        jButtonSupprimerMurSeparateur.setIcon(trashIcon);
        jButtonSupprimerFenetre.setIcon(trashIcon);
        jButtonSupprimerMatelas.setIcon(trashIcon);
        jButtonSupprimerHumain.setIcon(trashIcon);
        
        ImageIcon undoIcon = new ImageIcon(getClass().getResource("/undo.png"));
        btnUndo.setIcon(undoIcon);
        
        ImageIcon redoIcon = new ImageIcon(getClass().getResource("/redo.png"));
        btnRedo.setIcon(redoIcon);
    }
    
    private void checkUndoRedo(){
        if(controller.getHistoire().isCourrantOnFirstElement())
            btnUndo.setEnabled(false);
        else
            btnUndo.setEnabled(true);
        
        if(controller.getHistoire().isCourrantOnLastElement())
            btnRedo.setEnabled(false);
        else
            btnRedo.setEnabled(true);
        

    }
    
    private void remplirChamps(){
        String s = controller.getMicroRoulotte().getLargeur().getString();
        microroulotteLargeur.setText(s);
        
        s = controller.getMicroRoulotte().getHauteur().getString();
        microroulotteHauteur.setText(s);
        
        s = controller.getMicroRoulotte().getPlancher().getEpaisseur().getString();
        plancherEpaisseur.setText(s);
        
        s = controller.getMicroRoulotte().getPlancher().getMargeAvant().getString();
        plancherMargeAvant.setText(s);
        
        s = controller.getMicroRoulotte().getPlancher().getMargeArriere().getString();
        plancherMargeArriere.setText(s);
        
        if(controller.getMurSeparateur() != null){
            s = controller.getMurSeparateur().getEpaisseurDado().getString();
            murEpaisseur.setText(s);

            s = controller.getMurSeparateur().getDistancePoutreArriere().getString();
            murDistancePoutre.setText(s);

            s = controller.getMurSeparateur().getDistanceDuPlancher().getString();
            murDistancePlancher.setText(s);
        }
        
        
        s = controller.getToit().getEpaisseurDado().getString();
        toitEpaisseur.setText(s);
        
        //Poutre Arrière
        String poutreArrierePositionX = controller.getPoutreArriere().getPositionX().getString();
        poutrePositionX.setText(poutreArrierePositionX);
        
        String poutreArrierePositionY = controller.getPoutreArriere().getPositionY().getString();
        poutrePositionY.setText(poutreArrierePositionY);
        
        s = controller.getMicroRoulotte().getPoutreArriere().getLargeur().getString();
        poutreLargeur.setText(s);
        
        s = controller.getMicroRoulotte().getPoutreArriere().getHauteur().getString();
        poutreHauteur.setText(s);
        
        //Hayon
        s = controller.getMicroRoulotte().getHayon().getEpaisseur().getString();
        hayonEpaisseur.setText(s);        
        
        s = controller.getMicroRoulotte().getHayon().getDistanceDePoutre().getString();
        hayonDistancePoutre.setText(s);
                
        s = controller.getMicroRoulotte().getHayon().getDistanceDuPlancher().getString();
        hayonDistancePlancher.setText(s);
                
        s = controller.getMicroRoulotte().getHayon().getTraitDeScie().getString();
        hayonTrait.setText(s);
        
        s = Double.toString(controller.getHayon().getPoids());
        hayonPoids.setText(s);
  
        
        //Ellipses
        
        if(controller.isProfilClassique()){
            for(int i = 0 ; i < controller.getEllipses().size();i++){
                if(controller.getEllipses().get(i).getCoin() == 0)
                {
                    String ellipseHGHauteur = controller.getEllipses().get(i).getHauteur().getString();
                    EllipseHGHauteur.setText(ellipseHGHauteur);
                    String ellipseHGLargeur = controller.getEllipses().get(i).getLargeur().getString();
                    EllipseHGLargeur.setText(ellipseHGLargeur);

                    String ellipseHGPositionX = controller.getEllipses().get(i).getPositionX().getString();
                    EllipseHGPositionX.setText(ellipseHGPositionX);
                    String ellipseHGPositionY = controller.getEllipses().get(i).getPositionY().getString();
                    EllipseHGPositionY.setText(ellipseHGPositionY);

                }
                else if(controller.getEllipses().get(i).getCoin() == 1)
                {
                    String ellipseHDHauteur = controller.getEllipses().get(i).getHauteur().getString();
                    EllipseHDHauteur.setText(ellipseHDHauteur);
                    String ellipseHDLargeur = controller.getEllipses().get(i).getLargeur().getString();
                    EllipseHDLargeur.setText(ellipseHDLargeur);

                    String ellipseHDPositionX = controller.getEllipses().get(i).getPositionX().getString();
                    EllipseHDPositionX.setText(ellipseHDPositionX);
                    String ellipseHDPositionY = controller.getEllipses().get(i).getPositionY().getString();
                    EllipseHDPositionY.setText(ellipseHDPositionY);

                }
                else if(controller.getEllipses().get(i).getCoin() == 2)
                {
                    String ellipseBGHauteur = controller.getEllipses().get(i).getHauteur().getString();
                    EllipseBGHauteur.setText(ellipseBGHauteur);
                    String ellipseBGLargeur = controller.getEllipses().get(i).getLargeur().getString();
                    EllipseBGLargeur.setText(ellipseBGLargeur);

                    String ellipseBGPositionX = controller.getEllipses().get(i).getPositionX().getString();
                    EllipseBGPositionX.setText(ellipseBGPositionX);
                    String ellipseBGPositionY = controller.getEllipses().get(i).getPositionY().getString();
                    EllipseBGPositionY.setText(ellipseBGPositionY);

                }
                else if(controller.getEllipses().get(i).getCoin() == 3)
                {
                    String ellipseBDHauteur = controller.getEllipses().get(i).getHauteur().getString();
                    EllipseBDHauteur.setText(ellipseBDHauteur);
                    String ellipseBDLargeur = controller.getEllipses().get(i).getLargeur().getString();
                    EllipseBDLargeur.setText(ellipseBDLargeur);

                    String ellipseBDPositionX = controller.getEllipses().get(i).getPositionX().getString();
                    EllipseBDPositionX.setText(ellipseBDPositionX);
                    String ellipseBDPositionY = controller.getEllipses().get(i).getPositionY().getString();
                    EllipseBDPositionY.setText(ellipseBDPositionY);
                }
            }
        }
        
        
        
        
        
        
        //Grille Daide mettre les valeurs dans les champs
        String grilleDaideMesureString = controller.getGrilleAideDTO().getMesure().getString();
        grilleDaideMesure.setText(grilleDaideMesureString);
        
        
        //Ressort
        
       String ressortLongueurString = controller.getHayon().getRessort().getLongeur().getString();
       ressortLongueur.setText(ressortLongueurString);
       
       String ressortForceString = Double.toString(controller.getHayon().getRessort().getForce());
       ressortForce.setText(ressortForceString);
       
       if(controller.getHayon().getArea()!= null){
       String ressortLongueurEnExtensionString = Double.toString(controller.getRessort().getExtendedLength());
       ressortLongueur.setText(ressortLongueurEnExtensionString);
       
       String ressortLongueurStrokeString = Double.toString(controller.getRessort().getStokeLength());
       ressortStrokeLongueur.setText(ressortLongueurStrokeString);
       
       String ressortnoMcMasterString = controller.getRessort().getNoMcMaster();
       ressortNoMcMaster.setText(ressortnoMcMasterString);
       }
       
       porteLargeur.setText(controller.getPorte().getLargeur().getString());
       porteHauteur.setText(controller.getPorte().getHauteur().getString());
       porteDistanceToit.setText(controller.getPorte().getDistanceDeBoutToit().getString());
       

       if(controller.getFenetre() != null){
            fenetreLargeur.setText(controller.getFenetre().getLargeur().getString());
            fenetreHauteur.setText(controller.getFenetre().getHauteur().getString());
            fenetreDistanceToit.setText(controller.getFenetre().getDistanceFromToit().getString());
            fenetreDistanceDevant.setText(controller.getFenetre().getDistanceFromFront().getString());
       }
       

       //Panneau Interne
       epaisseurPanneauMurInterne.setText(controller.getPanneauInterne().getEpaisseur().getString());
       
       //Panneau millieu
       epaisseurPanneauMurMilieu.setText(controller.getPanneauMillieu().getEpaisseur().getString());
       
       //Panneau externe
       epaisseurPanneauMurExterne.setText(controller.getPanneauExterne().getEpaisseur().getString());

       if (controller.getMatelas() != null){
           matelasLargeur.setText(controller.getMatelas().getLargeur().getString());
           matelasHauteur.setText(controller.getMatelas().getHauteur().getString());
       }
       
       if(controller.getHumain() != null){
           humainLargeur.setText(controller.getHumain().getLargeur().getString());
           humainHauteur.setText(controller.getHumain().getHauteur().getString());
           humainDistanceBoutPlancher.setText(controller.getHumain().getDistanceBoutPlancher().getString());
       }

    }
    
    private void hideOptionPanels(){
        
        microRoulotteOptions.setVisible(false);
        plancherOptions.setVisible(false);
        murOptions.setVisible(false);
        toitOptions.setVisible(false);
        poutreOptions.setVisible(false);
        hayonOptions.setVisible(false);
        Ellipses.setVisible(false);
        EllipsesHGOptions.setVisible(false);
        EllipsesHDOptions.setVisible(false);
        EllipsesBGOptions.setVisible(false);
        EllipsesBDOptions.setVisible(false);
        ressortOptions.setVisible(false);
        porteOptions.setVisible(false);
        fenetreOptions.setVisible(false);
        panneauMurInterneOptions.setVisible(false);
        panneauMurMilieuOptions.setVisible(false);
        panneauMurExterneOptions.setVisible(false);
        matelasOptions.setVisible(false);
        humainOptions.setVisible(false);
    }

    private void afficheEllipsesPanel(){
        Ellipses.setVisible(true);
        EllipsesHGOptions.setVisible(true);
        EllipsesHDOptions.setVisible(true);
        EllipsesBGOptions.setVisible(true);
        EllipsesBDOptions.setVisible(true);
    }
    
    private void desafficheEllipsesPanel(){
        Ellipses.setVisible(false);
        EllipsesHGOptions.setVisible(false);
        EllipsesHDOptions.setVisible(false);
        EllipsesBGOptions.setVisible(false);
        EllipsesBDOptions.setVisible(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupEchelleMesures = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanelEchelleMesures = new javax.swing.JPanel();
        lvlPanelEchelleMesures = new javax.swing.JLabel();
        jRadioBtnImperial = new javax.swing.JRadioButton();
        jRadioBtnMetrique = new javax.swing.JRadioButton();
        jPanelAffichage = new javax.swing.JPanel();
        btnUndo = new javax.swing.JButton();
        btnRedo = new javax.swing.JButton();
        jchkboxGrillePlacement = new javax.swing.JCheckBox();
        DrawingPanelContainer = new javax.swing.JPanel();
        zoomMinus = new javax.swing.JButton();
        zoomPlus = new javax.swing.JButton();
        grilleDaideMesure = new javax.swing.JTextField();
        jPanelElements = new javax.swing.JPanel();
        lblPanelElements = new javax.swing.JLabel();
        jButtonMurSeparateur = new javax.swing.JButton();
        jButtonFenetre = new javax.swing.JButton();
        jButtonMatelas = new javax.swing.JButton();
        jButtonHumain = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelMesures = new javax.swing.JPanel();
        lblPanelMesures = new javax.swing.JLabel();
        microRoulotteOptions = new javax.swing.JPanel();
        microroulotteHauteur = new javax.swing.JTextField();
        microroulotteLargeur = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        changeToClassique = new javax.swing.JButton();
        changeToBezier = new javax.swing.JButton();
        vuePanneauMillieu = new javax.swing.JButton();
        vueMurInterne = new javax.swing.JButton();
        vueMurExterne = new javax.swing.JButton();
        plancherOptions = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        plancherEpaisseur = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        plancherMargeAvant = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        plancherMargeArriere = new javax.swing.JTextField();
        poutreOptions = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        poutreLargeur = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        poutreHauteur = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        poutrePositionX = new javax.swing.JTextField();
        poutrePositionY = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        hayonOptions = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        hayonEpaisseur = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        hayonDistancePoutre = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        hayonDistancePlancher = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        hayonTrait = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        hayonPoids = new javax.swing.JTextField();
        Ellipses = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        EllipsesHGOptions = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        EllipseHGHauteur = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        EllipseHGLargeur = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        EllipseHGPositionX = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        EllipseHGPositionY = new javax.swing.JTextField();
        EllipsesHDOptions = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        EllipseHDHauteur = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        EllipseHDLargeur = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        EllipseHDPositionX = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        EllipseHDPositionY = new javax.swing.JTextField();
        EllipsesBGOptions = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        EllipseBGHauteur = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        EllipseBGLargeur = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        EllipseBGPositionX = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        EllipseBGPositionY = new javax.swing.JTextField();
        EllipsesBDOptions = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        EllipseBDHauteur = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        EllipseBDLargeur = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        EllipseBDPositionX = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        EllipseBDPositionY = new javax.swing.JTextField();
        ressortOptions = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabelRessortLongueur = new javax.swing.JLabel();
        ressortLongueur = new javax.swing.JTextField();
        jLabelStrokeLongueur = new javax.swing.JLabel();
        ressortStrokeLongueur = new javax.swing.JTextField();
        jLabelressortNoMcMaster = new javax.swing.JLabel();
        ressortNoMcMaster = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        ressortForce = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        ressortPositionSurMur = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        ressortPositionSurHayon = new javax.swing.JTextField();
        murOptions = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        murEpaisseur = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        murDistancePoutre = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        murDistancePlancher = new javax.swing.JTextField();
        jButtonSupprimerMurSeparateur = new javax.swing.JButton();
        toitOptions = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        toitEpaisseur = new javax.swing.JTextField();
        porteOptions = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        porteLargeur = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        porteHauteur = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        porteDistanceToit = new javax.swing.JTextField();
        fenetreOptions = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        fenetreLargeur = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        fenetreHauteur = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        fenetreDistanceToit = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        fenetreDistanceDevant = new javax.swing.JTextField();
        jButtonSupprimerFenetre = new javax.swing.JButton();
        matelasOptions = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        matelasLargeur = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        matelasHauteur = new javax.swing.JTextField();
        jButtonSupprimerMatelas = new javax.swing.JButton();
        humainOptions = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        humainLargeur = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        humainHauteur = new javax.swing.JTextField();
        jButtonSupprimerHumain = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        humainDistanceBoutPlancher = new javax.swing.JTextField();
        panneauMurInterneOptions = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        epaisseurPanneauMurInterne = new javax.swing.JTextField();
        panneauMurMilieuOptions = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        epaisseurPanneauMurMilieu = new javax.swing.JTextField();
        panneauMurExterneOptions = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        epaisseurPanneauMurExterne = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFichier = new javax.swing.JMenu();
        jMenuItemOuvrir = new javax.swing.JMenuItem();
        jMenuItemSauvegarder = new javax.swing.JMenuItem();
        jMenuExporter = new javax.swing.JMenu();
        jMenuItemExporterJPG = new javax.swing.JMenuItem();
        jMenuItemExporterSVG = new javax.swing.JMenuItem();
        jMenuEditer = new javax.swing.JMenu();
        jMenuItemUndo = new javax.swing.JMenuItem();
        jMenuItemRedo = new javax.swing.JMenuItem();
        jCheckBoxMenuItemEditerProfil = new javax.swing.JCheckBoxMenuItem();
        jMenuVue = new javax.swing.JMenu();
        jMenuObjets = new javax.swing.JMenu();
        jCheckBoxMenuItemHayon = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemPlancher = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemPoutreArriere = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemRessort = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemProfil = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemMurSeparateur = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemToit = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemPorte = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemFenetre = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemMatelas = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemHumain = new javax.swing.JCheckBoxMenuItem();
        jMenuItemAfficherTout = new javax.swing.JMenuItem();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Microsoftears");

        jPanelEchelleMesures.setBackground(new java.awt.Color(204, 204, 204));
        jPanelEchelleMesures.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanelEchelleMesures.setPreferredSize(new java.awt.Dimension(150, 100));

        lvlPanelEchelleMesures.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lvlPanelEchelleMesures.setText("Échelle de mesures");

        btnGroupEchelleMesures.add(jRadioBtnImperial);
        jRadioBtnImperial.setSelected(true);
        jRadioBtnImperial.setText(" Impérial (\")");
        jRadioBtnImperial.setToolTipText("");
        jRadioBtnImperial.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jRadioBtnImperialStateChanged(evt);
            }
        });
        jRadioBtnImperial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBtnImperialActionPerformed(evt);
            }
        });

        btnGroupEchelleMesures.add(jRadioBtnMetrique);
        jRadioBtnMetrique.setText("Métrique (mm)");
        jRadioBtnMetrique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioBtnMetriqueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelEchelleMesuresLayout = new javax.swing.GroupLayout(jPanelEchelleMesures);
        jPanelEchelleMesures.setLayout(jPanelEchelleMesuresLayout);
        jPanelEchelleMesuresLayout.setHorizontalGroup(
            jPanelEchelleMesuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEchelleMesuresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEchelleMesuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lvlPanelEchelleMesures)
                    .addComponent(jRadioBtnImperial, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioBtnMetrique))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanelEchelleMesuresLayout.setVerticalGroup(
            jPanelEchelleMesuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEchelleMesuresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lvlPanelEchelleMesures)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioBtnImperial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioBtnMetrique)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanelAffichage.setBackground(new java.awt.Color(204, 204, 204));
        jPanelAffichage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanelAffichage.setMaximumSize(new java.awt.Dimension(800, 600));

        btnUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUndoActionPerformed(evt);
            }
        });

        btnRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRedoActionPerformed(evt);
            }
        });

        jchkboxGrillePlacement.setSelected(true);
        jchkboxGrillePlacement.setText("Grille d'aide");
        jchkboxGrillePlacement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jchkboxGrillePlacementActionPerformed(evt);
            }
        });

        DrawingPanelContainer.setBackground(new java.awt.Color(187, 187, 187));
        DrawingPanelContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        DrawingPanelContainer.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                DrawingPanelContainerMouseDragged(evt);
            }
        });
        DrawingPanelContainer.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                DrawingPanelContainerMouseWheelMoved(evt);
            }
        });
        DrawingPanelContainer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DrawingPanelContainerMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DrawingPanelContainerMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DrawingPanelContainerMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout DrawingPanelContainerLayout = new javax.swing.GroupLayout(DrawingPanelContainer);
        DrawingPanelContainer.setLayout(DrawingPanelContainerLayout);
        DrawingPanelContainerLayout.setHorizontalGroup(
            DrawingPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        DrawingPanelContainerLayout.setVerticalGroup(
            DrawingPanelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        zoomMinus.setText("-");
        zoomMinus.setToolTipText("");
        zoomMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomMinusActionPerformed(evt);
            }
        });

        zoomPlus.setText("+");
        zoomPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomPlusActionPerformed(evt);
            }
        });

        grilleDaideMesure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grilleDaideMesureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAffichageLayout = new javax.swing.GroupLayout(jPanelAffichage);
        jPanelAffichage.setLayout(jPanelAffichageLayout);
        jPanelAffichageLayout.setHorizontalGroup(
            jPanelAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAffichageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DrawingPanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelAffichageLayout.createSequentialGroup()
                        .addComponent(btnUndo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRedo)
                        .addGap(24, 24, 24)
                        .addGroup(jPanelAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jchkboxGrillePlacement, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grilleDaideMesure, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zoomMinus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(zoomPlus)
                        .addGap(276, 276, 276)))
                .addContainerGap())
        );
        jPanelAffichageLayout.setVerticalGroup(
            jPanelAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAffichageLayout.createSequentialGroup()
                .addGroup(jPanelAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAffichageLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelAffichageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUndo)
                            .addComponent(btnRedo)
                            .addComponent(zoomMinus)
                            .addComponent(zoomPlus)))
                    .addGroup(jPanelAffichageLayout.createSequentialGroup()
                        .addComponent(jchkboxGrillePlacement)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grilleDaideMesure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(DrawingPanelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelElements.setBackground(new java.awt.Color(204, 204, 204));
        jPanelElements.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblPanelElements.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPanelElements.setText("Éléments");

        jButtonMurSeparateur.setText("Mur séparateur");
        jButtonMurSeparateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMurSeparateurActionPerformed(evt);
            }
        });

        jButtonFenetre.setText("Fenêtre");
        jButtonFenetre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFenetreActionPerformed(evt);
            }
        });

        jButtonMatelas.setText("Matelas");
        jButtonMatelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMatelasActionPerformed(evt);
            }
        });

        jButtonHumain.setText("Humain couché");
        jButtonHumain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHumainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelElementsLayout = new javax.swing.GroupLayout(jPanelElements);
        jPanelElements.setLayout(jPanelElementsLayout);
        jPanelElementsLayout.setHorizontalGroup(
            jPanelElementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelElementsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelElementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPanelElements, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelElementsLayout.createSequentialGroup()
                        .addComponent(jButtonMurSeparateur, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonFenetre, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonMatelas, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonHumain, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(154, Short.MAX_VALUE))
        );

        jPanelElementsLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonFenetre, jButtonHumain, jButtonMatelas, jButtonMurSeparateur});

        jPanelElementsLayout.setVerticalGroup(
            jPanelElementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelElementsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPanelElements)
                .addGap(18, 18, 18)
                .addGroup(jPanelElementsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonMurSeparateur)
                    .addComponent(jButtonFenetre)
                    .addComponent(jButtonMatelas)
                    .addComponent(jButtonHumain))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanelMesures.setBackground(new java.awt.Color(204, 204, 204));
        jPanelMesures.setPreferredSize(new java.awt.Dimension(150, 1800));

        lblPanelMesures.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblPanelMesures.setText("Mesures");

        microroulotteHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                microroulotteHauteurActionPerformed(evt);
            }
        });

        microroulotteLargeur.setToolTipText("");
        microroulotteLargeur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                microroulotteLargeurFocusLost(evt);
            }
        });
        microroulotteLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                microroulotteLargeurActionPerformed(evt);
            }
        });

        jLabel1.setText("Micro-Roulotte");

        jLabel2.setText("Largeur");

        jLabel3.setText("Hauteur");

        changeToClassique.setText("Profil classique");
        changeToClassique.setMaximumSize(new java.awt.Dimension(92, 22));
        changeToClassique.setMinimumSize(new java.awt.Dimension(92, 22));
        changeToClassique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeToClassiqueActionPerformed(evt);
            }
        });

        changeToBezier.setLabel("Profil Bezier");
        changeToBezier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeToBezierActionPerformed(evt);
            }
        });

        vuePanneauMillieu.setText("Mur Millieu");
        vuePanneauMillieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vuePanneauMillieuActionPerformed(evt);
            }
        });

        vueMurInterne.setText("Mur Interne");
        vueMurInterne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vueMurInterneActionPerformed(evt);
            }
        });

        vueMurExterne.setText("Mur Externe");
        vueMurExterne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vueMurExterneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout microRoulotteOptionsLayout = new javax.swing.GroupLayout(microRoulotteOptions);
        microRoulotteOptions.setLayout(microRoulotteOptionsLayout);
        microRoulotteOptionsLayout.setHorizontalGroup(
            microRoulotteOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(microRoulotteOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(microRoulotteOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(microroulotteLargeur)
                    .addComponent(microroulotteHauteur)
                    .addComponent(changeToClassique, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(microRoulotteOptionsLayout.createSequentialGroup()
                        .addGroup(microRoulotteOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(changeToBezier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vuePanneauMillieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vueMurInterne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vueMurExterne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        microRoulotteOptionsLayout.setVerticalGroup(
            microRoulotteOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, microRoulotteOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(microroulotteLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(microroulotteHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(changeToClassique, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(changeToBezier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vuePanneauMillieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vueMurInterne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vueMurExterne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Plancher");

        jLabel5.setText("Épaisseur");

        plancherEpaisseur.setToolTipText("");
        plancherEpaisseur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                plancherEpaisseurFocusLost(evt);
            }
        });
        plancherEpaisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plancherEpaisseurActionPerformed(evt);
            }
        });

        jLabel6.setText("Marge avant");

        plancherMargeAvant.setToolTipText("");
        plancherMargeAvant.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                plancherMargeAvantFocusLost(evt);
            }
        });
        plancherMargeAvant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plancherMargeAvantActionPerformed(evt);
            }
        });

        jLabel7.setText("Marge arrière");

        plancherMargeArriere.setToolTipText("");
        plancherMargeArriere.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                plancherMargeArriereFocusLost(evt);
            }
        });
        plancherMargeArriere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plancherMargeArriereActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout plancherOptionsLayout = new javax.swing.GroupLayout(plancherOptions);
        plancherOptions.setLayout(plancherOptionsLayout);
        plancherOptionsLayout.setHorizontalGroup(
            plancherOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plancherOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plancherOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(plancherEpaisseur)
                    .addComponent(plancherMargeAvant)
                    .addGroup(plancherOptionsLayout.createSequentialGroup()
                        .addGroup(plancherOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(plancherMargeArriere))
                .addContainerGap())
        );
        plancherOptionsLayout.setVerticalGroup(
            plancherOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plancherOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plancherEpaisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plancherMargeAvant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plancherMargeArriere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel8.setText("Poutre arrière");

        jLabel9.setText("Largeur");

        poutreLargeur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                poutreLargeurFocusLost(evt);
            }
        });
        poutreLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poutreLargeurActionPerformed(evt);
            }
        });

        jLabel10.setText("Hauteur");

        poutreHauteur.setToolTipText("");
        poutreHauteur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                poutreHauteurFocusLost(evt);
            }
        });
        poutreHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poutreHauteurActionPerformed(evt);
            }
        });

        jLabel11.setText("Position X");

        poutrePositionX.setToolTipText("");
        poutrePositionX.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                poutrePositionXFocusLost(evt);
            }
        });
        poutrePositionX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poutrePositionXActionPerformed(evt);
            }
        });

        poutrePositionY.setToolTipText("");
        poutrePositionY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                poutrePositionYFocusLost(evt);
            }
        });
        poutrePositionY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poutrePositionYActionPerformed(evt);
            }
        });

        jLabel32.setText("Position Y");

        javax.swing.GroupLayout poutreOptionsLayout = new javax.swing.GroupLayout(poutreOptions);
        poutreOptions.setLayout(poutreOptionsLayout);
        poutreOptionsLayout.setHorizontalGroup(
            poutreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(poutreOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(poutreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(poutreLargeur)
                    .addGroup(poutreOptionsLayout.createSequentialGroup()
                        .addGroup(poutreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel32))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(poutreHauteur)
                    .addComponent(poutrePositionX)
                    .addComponent(poutrePositionY))
                .addContainerGap())
        );
        poutreOptionsLayout.setVerticalGroup(
            poutreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(poutreOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poutreLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poutreHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poutrePositionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poutrePositionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel12.setText("Hayon");

        jLabel13.setText("Épaisseur");

        hayonEpaisseur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hayonEpaisseurFocusLost(evt);
            }
        });
        hayonEpaisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hayonEpaisseurActionPerformed(evt);
            }
        });

        jLabel14.setText("Distance de la poutre");

        hayonDistancePoutre.setToolTipText("");
        hayonDistancePoutre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hayonDistancePoutreFocusLost(evt);
            }
        });
        hayonDistancePoutre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hayonDistancePoutreActionPerformed(evt);
            }
        });

        jLabel15.setText("Distance du plancher");

        hayonDistancePlancher.setToolTipText("");
        hayonDistancePlancher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hayonDistancePlancherFocusLost(evt);
            }
        });
        hayonDistancePlancher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hayonDistancePlancherActionPerformed(evt);
            }
        });

        jLabel16.setText("Trait de scie");

        hayonTrait.setToolTipText("");
        hayonTrait.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hayonTraitFocusLost(evt);
            }
        });
        hayonTrait.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hayonTraitActionPerformed(evt);
            }
        });

        jLabel44.setText("Poids");

        hayonPoids.setToolTipText("");
        hayonPoids.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hayonPoidsFocusLost(evt);
            }
        });
        hayonPoids.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hayonPoidsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout hayonOptionsLayout = new javax.swing.GroupLayout(hayonOptions);
        hayonOptions.setLayout(hayonOptionsLayout);
        hayonOptionsLayout.setHorizontalGroup(
            hayonOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hayonOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(hayonOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hayonEpaisseur)
                    .addComponent(hayonDistancePoutre)
                    .addComponent(hayonDistancePlancher)
                    .addGroup(hayonOptionsLayout.createSequentialGroup()
                        .addGroup(hayonOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel44))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(hayonTrait)
                    .addComponent(hayonPoids))
                .addContainerGap())
        );
        hayonOptionsLayout.setVerticalGroup(
            hayonOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hayonOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hayonEpaisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hayonDistancePoutre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hayonDistancePlancher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hayonTrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hayonPoids, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel29.setText("Ellipses");

        EllipsesHGOptions.setBackground(new java.awt.Color(153, 153, 153));

        jLabel17.setText("Haut Gauche");

        jLabel18.setText("Hauteur");

        EllipseHGHauteur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseHGHauteurFocusLost(evt);
            }
        });
        EllipseHGHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseHGHauteurActionPerformed(evt);
            }
        });

        jLabel19.setText("Largeur");

        EllipseHGLargeur.setToolTipText("");
        EllipseHGLargeur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseHGLargeurFocusLost(evt);
            }
        });
        EllipseHGLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseHGLargeurActionPerformed(evt);
            }
        });

        jLabel30.setText("Position X");

        EllipseHGPositionX.setToolTipText("");
        EllipseHGPositionX.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseHGPositionXFocusLost(evt);
            }
        });
        EllipseHGPositionX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseHGPositionXActionPerformed(evt);
            }
        });

        jLabel31.setText("Position Y");

        EllipseHGPositionY.setToolTipText("");
        EllipseHGPositionY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseHGPositionYFocusLost(evt);
            }
        });
        EllipseHGPositionY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseHGPositionYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EllipsesHGOptionsLayout = new javax.swing.GroupLayout(EllipsesHGOptions);
        EllipsesHGOptions.setLayout(EllipsesHGOptionsLayout);
        EllipsesHGOptionsLayout.setHorizontalGroup(
            EllipsesHGOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesHGOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EllipsesHGOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EllipseHGHauteur)
                    .addComponent(EllipseHGLargeur)
                    .addGroup(EllipsesHGOptionsLayout.createSequentialGroup()
                        .addGroup(EllipsesHGOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(EllipseHGPositionX)
                    .addComponent(EllipseHGPositionY))
                .addContainerGap())
        );
        EllipsesHGOptionsLayout.setVerticalGroup(
            EllipsesHGOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesHGOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseHGHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseHGLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseHGPositionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseHGPositionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        EllipsesHDOptions.setBackground(new java.awt.Color(153, 153, 153));

        jLabel20.setText("Haut Droit");

        jLabel21.setText("Hauteur");

        EllipseHDHauteur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseHDHauteurFocusLost(evt);
            }
        });
        EllipseHDHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseHDHauteurActionPerformed(evt);
            }
        });

        jLabel22.setText("Largeur");

        EllipseHDLargeur.setToolTipText("");
        EllipseHDLargeur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseHDLargeurFocusLost(evt);
            }
        });
        EllipseHDLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseHDLargeurActionPerformed(evt);
            }
        });

        jLabel34.setText("Position X");

        EllipseHDPositionX.setToolTipText("");
        EllipseHDPositionX.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseHDPositionXFocusLost(evt);
            }
        });
        EllipseHDPositionX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseHDPositionXActionPerformed(evt);
            }
        });

        jLabel35.setText("Position Y");

        EllipseHDPositionY.setToolTipText("");
        EllipseHDPositionY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseHDPositionYFocusLost(evt);
            }
        });
        EllipseHDPositionY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseHDPositionYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EllipsesHDOptionsLayout = new javax.swing.GroupLayout(EllipsesHDOptions);
        EllipsesHDOptions.setLayout(EllipsesHDOptionsLayout);
        EllipsesHDOptionsLayout.setHorizontalGroup(
            EllipsesHDOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesHDOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EllipsesHDOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EllipseHDHauteur)
                    .addComponent(EllipseHDLargeur)
                    .addGroup(EllipsesHDOptionsLayout.createSequentialGroup()
                        .addGroup(EllipsesHDOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel34)
                            .addComponent(jLabel35))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(EllipseHDPositionX)
                    .addComponent(EllipseHDPositionY))
                .addContainerGap())
        );
        EllipsesHDOptionsLayout.setVerticalGroup(
            EllipsesHDOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesHDOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseHDHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseHDLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseHDPositionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseHDPositionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        EllipsesBGOptions.setBackground(new java.awt.Color(153, 153, 153));

        jLabel23.setText("Bas Gauche");

        jLabel24.setText("Hauteur");

        EllipseBGHauteur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseBGHauteurFocusLost(evt);
            }
        });
        EllipseBGHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseBGHauteurActionPerformed(evt);
            }
        });

        jLabel25.setText("Largeur");

        EllipseBGLargeur.setToolTipText("");
        EllipseBGLargeur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseBGLargeurFocusLost(evt);
            }
        });
        EllipseBGLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseBGLargeurActionPerformed(evt);
            }
        });

        jLabel36.setText("Position X");

        EllipseBGPositionX.setToolTipText("");
        EllipseBGPositionX.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseBGPositionXFocusLost(evt);
            }
        });
        EllipseBGPositionX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseBGPositionXActionPerformed(evt);
            }
        });

        jLabel37.setText("Position Y");

        EllipseBGPositionY.setToolTipText("");
        EllipseBGPositionY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseBGPositionYFocusLost(evt);
            }
        });
        EllipseBGPositionY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseBGPositionYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EllipsesBGOptionsLayout = new javax.swing.GroupLayout(EllipsesBGOptions);
        EllipsesBGOptions.setLayout(EllipsesBGOptionsLayout);
        EllipsesBGOptionsLayout.setHorizontalGroup(
            EllipsesBGOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesBGOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EllipsesBGOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EllipseBGHauteur)
                    .addComponent(EllipseBGLargeur)
                    .addGroup(EllipsesBGOptionsLayout.createSequentialGroup()
                        .addGroup(EllipsesBGOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(EllipseBGPositionX)
                    .addComponent(EllipseBGPositionY))
                .addContainerGap())
        );
        EllipsesBGOptionsLayout.setVerticalGroup(
            EllipsesBGOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesBGOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseBGHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseBGLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseBGPositionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseBGPositionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        EllipsesBDOptions.setBackground(new java.awt.Color(153, 153, 153));

        jLabel26.setText("Bas Droit");

        jLabel27.setText("Hauteur");

        EllipseBDHauteur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseBDHauteurFocusLost(evt);
            }
        });
        EllipseBDHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseBDHauteurActionPerformed(evt);
            }
        });

        jLabel28.setText("Largeur");

        EllipseBDLargeur.setToolTipText("");
        EllipseBDLargeur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseBDLargeurFocusLost(evt);
            }
        });
        EllipseBDLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseBDLargeurActionPerformed(evt);
            }
        });

        jLabel38.setText("Position X");

        EllipseBDPositionX.setToolTipText("");
        EllipseBDPositionX.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseBDPositionXFocusLost(evt);
            }
        });
        EllipseBDPositionX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseBDPositionXActionPerformed(evt);
            }
        });

        jLabel39.setText("Position Y");

        EllipseBDPositionY.setToolTipText("");
        EllipseBDPositionY.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                EllipseBDPositionYFocusLost(evt);
            }
        });
        EllipseBDPositionY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EllipseBDPositionYActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EllipsesBDOptionsLayout = new javax.swing.GroupLayout(EllipsesBDOptions);
        EllipsesBDOptions.setLayout(EllipsesBDOptionsLayout);
        EllipsesBDOptionsLayout.setHorizontalGroup(
            EllipsesBDOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesBDOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EllipsesBDOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EllipseBDHauteur)
                    .addComponent(EllipseBDLargeur)
                    .addGroup(EllipsesBDOptionsLayout.createSequentialGroup()
                        .addGroup(EllipsesBDOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(EllipseBDPositionX)
                    .addComponent(EllipseBDPositionY))
                .addContainerGap())
        );
        EllipsesBDOptionsLayout.setVerticalGroup(
            EllipsesBDOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesBDOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseBDHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseBDLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseBDPositionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipseBDPositionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EllipsesLayout = new javax.swing.GroupLayout(Ellipses);
        Ellipses.setLayout(EllipsesLayout);
        EllipsesLayout.setHorizontalGroup(
            EllipsesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EllipsesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EllipsesLayout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(EllipsesHGOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EllipsesHDOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EllipsesBGOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EllipsesBDOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        EllipsesLayout.setVerticalGroup(
            EllipsesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EllipsesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipsesHGOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipsesHDOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EllipsesBGOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(EllipsesBDOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel33.setText("Ressort");

        jLabelRessortLongueur.setText("Longueur en extension");

        ressortLongueur.setEnabled(false);
        ressortLongueur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ressortLongueurFocusLost(evt);
            }
        });
        ressortLongueur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ressortLongueurActionPerformed(evt);
            }
        });

        jLabelStrokeLongueur.setText("Stroke Longueur");

        ressortStrokeLongueur.setEnabled(false);
        ressortStrokeLongueur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ressortStrokeLongueurFocusLost(evt);
            }
        });
        ressortStrokeLongueur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ressortStrokeLongueurActionPerformed(evt);
            }
        });

        jLabelressortNoMcMaster.setText("Ressort McMaster");

        ressortNoMcMaster.setEnabled(false);
        ressortNoMcMaster.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ressortNoMcMasterFocusLost(evt);
            }
        });
        ressortNoMcMaster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ressortNoMcMasterActionPerformed(evt);
            }
        });

        jLabel41.setText("Force");

        ressortForce.setToolTipText("");
        ressortForce.setEnabled(false);
        ressortForce.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ressortForceFocusLost(evt);
            }
        });
        ressortForce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ressortForceActionPerformed(evt);
            }
        });

        jLabel42.setText("Position sur mur");

        ressortPositionSurMur.setToolTipText("");
        ressortPositionSurMur.setEnabled(false);
        ressortPositionSurMur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ressortPositionSurMurFocusLost(evt);
            }
        });
        ressortPositionSurMur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ressortPositionSurMurActionPerformed(evt);
            }
        });

        jLabel43.setText("Position sur hayon");

        ressortPositionSurHayon.setToolTipText("");
        ressortPositionSurHayon.setEnabled(false);
        ressortPositionSurHayon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                ressortPositionSurHayonFocusLost(evt);
            }
        });
        ressortPositionSurHayon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ressortPositionSurHayonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ressortOptionsLayout = new javax.swing.GroupLayout(ressortOptions);
        ressortOptions.setLayout(ressortOptionsLayout);
        ressortOptionsLayout.setHorizontalGroup(
            ressortOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ressortOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ressortOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ressortLongueur)
                    .addComponent(ressortForce)
                    .addComponent(ressortPositionSurMur)
                    .addGroup(ressortOptionsLayout.createSequentialGroup()
                        .addGroup(ressortOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabelRessortLongueur)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42)
                            .addComponent(jLabel43)
                            .addComponent(jLabelStrokeLongueur)
                            .addComponent(jLabelressortNoMcMaster))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ressortPositionSurHayon)
                    .addComponent(ressortStrokeLongueur)
                    .addComponent(ressortNoMcMaster))
                .addContainerGap())
        );
        ressortOptionsLayout.setVerticalGroup(
            ressortOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ressortOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addComponent(jLabelRessortLongueur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ressortLongueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelStrokeLongueur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ressortStrokeLongueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelressortNoMcMaster)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ressortNoMcMaster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ressortForce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ressortPositionSurMur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ressortPositionSurHayon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel45.setText("Mur séparateur");

        jLabel46.setText("Épaisseur");

        murEpaisseur.setToolTipText("");
        murEpaisseur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                murEpaisseurFocusLost(evt);
            }
        });
        murEpaisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                murEpaisseurActionPerformed(evt);
            }
        });

        jLabel47.setText("Distance de la poutre");

        murDistancePoutre.setToolTipText("");
        murDistancePoutre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                murDistancePoutreFocusLost(evt);
            }
        });
        murDistancePoutre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                murDistancePoutreActionPerformed(evt);
            }
        });

        jLabel48.setText("Distance du plancher");

        murDistancePlancher.setToolTipText("");
        murDistancePlancher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                murDistancePlancherFocusLost(evt);
            }
        });
        murDistancePlancher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                murDistancePlancherActionPerformed(evt);
            }
        });

        jButtonSupprimerMurSeparateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerMurSeparateurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout murOptionsLayout = new javax.swing.GroupLayout(murOptions);
        murOptions.setLayout(murOptionsLayout);
        murOptionsLayout.setHorizontalGroup(
            murOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(murOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(murOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(murEpaisseur)
                    .addComponent(murDistancePoutre)
                    .addComponent(murDistancePlancher)
                    .addGroup(murOptionsLayout.createSequentialGroup()
                        .addGroup(murOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel45)
                            .addComponent(jLabel47)
                            .addComponent(jLabel48)
                            .addGroup(murOptionsLayout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(47, 47, 47)
                                .addComponent(jButtonSupprimerMurSeparateur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        murOptionsLayout.setVerticalGroup(
            murOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(murOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addGroup(murOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(murOptionsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel46))
                    .addGroup(murOptionsLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jButtonSupprimerMurSeparateur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(murEpaisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(murDistancePoutre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(murDistancePlancher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jLabel49.setText("Toit");

        jLabel50.setText("Épaisseur");

        toitEpaisseur.setToolTipText("");
        toitEpaisseur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                toitEpaisseurFocusLost(evt);
            }
        });
        toitEpaisseur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toitEpaisseurActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout toitOptionsLayout = new javax.swing.GroupLayout(toitOptions);
        toitOptions.setLayout(toitOptionsLayout);
        toitOptionsLayout.setHorizontalGroup(
            toitOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toitOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(toitOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toitEpaisseur)
                    .addGroup(toitOptionsLayout.createSequentialGroup()
                        .addGroup(toitOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel49)
                            .addComponent(jLabel50))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        toitOptionsLayout.setVerticalGroup(
            toitOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(toitOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel49)
                .addGap(18, 18, 18)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toitEpaisseur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel53.setText("Porte");

        jLabel54.setText("Largeur");

        porteLargeur.setText("jTextField1");
        porteLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porteLargeurActionPerformed(evt);
            }
        });

        jLabel55.setText("Hauteur");

        porteHauteur.setText("jTextField2");
        porteHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porteHauteurActionPerformed(evt);
            }
        });

        jLabel56.setText("Distance du toit");

        porteDistanceToit.setText("jTextField3");
        porteDistanceToit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porteDistanceToitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout porteOptionsLayout = new javax.swing.GroupLayout(porteOptions);
        porteOptions.setLayout(porteOptionsLayout);
        porteOptionsLayout.setHorizontalGroup(
            porteOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(porteOptionsLayout.createSequentialGroup()
                .addGroup(porteOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(porteOptionsLayout.createSequentialGroup()
                        .addGroup(porteOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(porteOptionsLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel53))
                            .addGroup(porteOptionsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel54))
                            .addGroup(porteOptionsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel55))
                            .addGroup(porteOptionsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel56)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(porteOptionsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(porteOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(porteLargeur)
                            .addComponent(porteHauteur)
                            .addComponent(porteDistanceToit))))
                .addContainerGap())
        );
        porteOptionsLayout.setVerticalGroup(
            porteOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(porteOptionsLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel53)
                .addGap(9, 9, 9)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(porteLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(porteHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(porteDistanceToit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel59.setText("Fenêtre");

        jLabel60.setText("Largeur");

        fenetreLargeur.setText("jTextField1");
        fenetreLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fenetreLargeurActionPerformed(evt);
            }
        });

        jLabel61.setText("Hauteur");

        fenetreHauteur.setText("jTextField2");
        fenetreHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fenetreHauteurActionPerformed(evt);
            }
        });

        jLabel62.setText("Distance du toit");

        fenetreDistanceToit.setText("jTextField3");
        fenetreDistanceToit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fenetreDistanceToitActionPerformed(evt);
            }
        });

        jLabel63.setText("Distance du devant");

        fenetreDistanceDevant.setText("jTextField1");
        fenetreDistanceDevant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fenetreDistanceDevantActionPerformed(evt);
            }
        });

        jButtonSupprimerFenetre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerFenetreActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout fenetreOptionsLayout = new javax.swing.GroupLayout(fenetreOptions);
        fenetreOptions.setLayout(fenetreOptionsLayout);
        fenetreOptionsLayout.setHorizontalGroup(
            fenetreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fenetreOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fenetreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fenetreHauteur)
                    .addComponent(fenetreLargeur)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fenetreDistanceToit)
                    .addComponent(fenetreDistanceDevant)
                    .addGroup(fenetreOptionsLayout.createSequentialGroup()
                        .addGroup(fenetreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel61)
                            .addComponent(jLabel63)
                            .addGroup(fenetreOptionsLayout.createSequentialGroup()
                                .addGroup(fenetreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel59)
                                    .addComponent(jLabel60))
                                .addGap(41, 41, 41)
                                .addComponent(jButtonSupprimerFenetre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        fenetreOptionsLayout.setVerticalGroup(
            fenetreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fenetreOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(fenetreOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(fenetreOptionsLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel60))
                    .addComponent(jButtonSupprimerFenetre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fenetreLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fenetreHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fenetreDistanceToit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fenetreDistanceDevant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel64.setText("Matelas");

        jLabel65.setText("Largeur");

        matelasLargeur.setText("jTextField1");
        matelasLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matelasLargeurActionPerformed(evt);
            }
        });

        jLabel66.setText("Hauteur");

        matelasHauteur.setText("jTextField2");
        matelasHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matelasHauteurActionPerformed(evt);
            }
        });

        jButtonSupprimerMatelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerMatelasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout matelasOptionsLayout = new javax.swing.GroupLayout(matelasOptions);
        matelasOptions.setLayout(matelasOptionsLayout);
        matelasOptionsLayout.setHorizontalGroup(
            matelasOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matelasOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(matelasOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matelasHauteur, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                    .addComponent(matelasLargeur)
                    .addGroup(matelasOptionsLayout.createSequentialGroup()
                        .addGroup(matelasOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel66)
                            .addGroup(matelasOptionsLayout.createSequentialGroup()
                                .addGroup(matelasOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel64)
                                    .addComponent(jLabel65))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSupprimerMatelas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        matelasOptionsLayout.setVerticalGroup(
            matelasOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(matelasOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(matelasOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(matelasOptionsLayout.createSequentialGroup()
                        .addComponent(jLabel64)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel65)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addComponent(jButtonSupprimerMatelas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matelasLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matelasHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel67.setText("Matelas");

        jLabel68.setText("Largeur");

        humainLargeur.setText("jTextField1");
        humainLargeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                humainLargeurActionPerformed(evt);
            }
        });

        jLabel69.setText("Hauteur");

        humainHauteur.setText("jTextField2");
        humainHauteur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                humainHauteurActionPerformed(evt);
            }
        });

        jButtonSupprimerHumain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerHumainActionPerformed(evt);
            }
        });

        jLabel57.setText("Distance fin du plancher");

        humainDistanceBoutPlancher.setText("jTextField1");
        humainDistanceBoutPlancher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                humainDistanceBoutPlancherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout humainOptionsLayout = new javax.swing.GroupLayout(humainOptions);
        humainOptions.setLayout(humainOptionsLayout);
        humainOptionsLayout.setHorizontalGroup(
            humainOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(humainOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(humainOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(humainHauteur)
                    .addComponent(humainLargeur)
                    .addComponent(humainDistanceBoutPlancher)
                    .addGroup(humainOptionsLayout.createSequentialGroup()
                        .addGroup(humainOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69)
                            .addGroup(humainOptionsLayout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSupprimerHumain, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel57)
                            .addComponent(jLabel68))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        humainOptionsLayout.setVerticalGroup(
            humainOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(humainOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(humainOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67)
                    .addComponent(jButtonSupprimerHumain, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(humainLargeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(humainHauteur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(humainDistanceBoutPlancher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel40.setText("Épaisseur");

        epaisseurPanneauMurInterne.setText("jTextField1");
        epaisseurPanneauMurInterne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                epaisseurPanneauMurInterneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panneauMurInterneOptionsLayout = new javax.swing.GroupLayout(panneauMurInterneOptions);
        panneauMurInterneOptions.setLayout(panneauMurInterneOptionsLayout);
        panneauMurInterneOptionsLayout.setHorizontalGroup(
            panneauMurInterneOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauMurInterneOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panneauMurInterneOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panneauMurInterneOptionsLayout.createSequentialGroup()
                        .addComponent(jLabel40)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(epaisseurPanneauMurInterne, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addContainerGap())
        );
        panneauMurInterneOptionsLayout.setVerticalGroup(
            panneauMurInterneOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauMurInterneOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(epaisseurPanneauMurInterne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel51.setText("Épaisseur");

        epaisseurPanneauMurMilieu.setText("jTextField1");
        epaisseurPanneauMurMilieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                epaisseurPanneauMurMilieuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panneauMurMilieuOptionsLayout = new javax.swing.GroupLayout(panneauMurMilieuOptions);
        panneauMurMilieuOptions.setLayout(panneauMurMilieuOptionsLayout);
        panneauMurMilieuOptionsLayout.setHorizontalGroup(
            panneauMurMilieuOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauMurMilieuOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panneauMurMilieuOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panneauMurMilieuOptionsLayout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(0, 84, Short.MAX_VALUE))
                    .addComponent(epaisseurPanneauMurMilieu))
                .addContainerGap())
        );
        panneauMurMilieuOptionsLayout.setVerticalGroup(
            panneauMurMilieuOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauMurMilieuOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(epaisseurPanneauMurMilieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel52.setText("Épaisseur");

        epaisseurPanneauMurExterne.setText("jTextField1");
        epaisseurPanneauMurExterne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                epaisseurPanneauMurExterneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panneauMurExterneOptionsLayout = new javax.swing.GroupLayout(panneauMurExterneOptions);
        panneauMurExterneOptions.setLayout(panneauMurExterneOptionsLayout);
        panneauMurExterneOptionsLayout.setHorizontalGroup(
            panneauMurExterneOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panneauMurExterneOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panneauMurExterneOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panneauMurExterneOptionsLayout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addGap(0, 60, Short.MAX_VALUE))
                    .addComponent(epaisseurPanneauMurExterne))
                .addContainerGap())
        );
        panneauMurExterneOptionsLayout.setVerticalGroup(
            panneauMurExterneOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panneauMurExterneOptionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(epaisseurPanneauMurExterne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelMesuresLayout = new javax.swing.GroupLayout(jPanelMesures);
        jPanelMesures.setLayout(jPanelMesuresLayout);
        jPanelMesuresLayout.setHorizontalGroup(
            jPanelMesuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMesuresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMesuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(poutreOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plancherOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMesuresLayout.createSequentialGroup()
                        .addComponent(lblPanelMesures, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(hayonOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Ellipses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ressortOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(microRoulotteOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(murOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toitOptions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(porteOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fenetreOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(matelasOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(humainOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panneauMurInterneOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panneauMurMilieuOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panneauMurExterneOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelMesuresLayout.setVerticalGroup(
            jPanelMesuresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMesuresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPanelMesures)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(microRoulotteOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panneauMurInterneOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panneauMurExterneOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panneauMurMilieuOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(porteOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fenetreOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(matelasOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(humainOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plancherOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(murOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toitOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poutreOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hayonOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ressortOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ellipses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanelMesures);

        jMenuFichier.setText("Fichier");

        jMenuItemOuvrir.setText("Ouvrir");
        jMenuItemOuvrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOuvrirActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemOuvrir);

        jMenuItemSauvegarder.setText("Sauvegarder");
        jMenuItemSauvegarder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSauvegarderActionPerformed(evt);
            }
        });
        jMenuFichier.add(jMenuItemSauvegarder);

        jMenuExporter.setText("Exporter Plan");

        jMenuItemExporterJPG.setText("Exporter JPG");
        jMenuItemExporterJPG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExporterJPGActionPerformed(evt);
            }
        });
        jMenuExporter.add(jMenuItemExporterJPG);

        jMenuItemExporterSVG.setText("Exporter SVG");
        jMenuItemExporterSVG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExporterSVGActionPerformed(evt);
            }
        });
        jMenuExporter.add(jMenuItemExporterSVG);

        jMenuFichier.add(jMenuExporter);

        jMenuBar1.add(jMenuFichier);

        jMenuEditer.setText("Editer");

        jMenuItemUndo.setText("Undo");
        jMenuEditer.add(jMenuItemUndo);

        jMenuItemRedo.setText("Redo");
        jMenuEditer.add(jMenuItemRedo);

        jCheckBoxMenuItemEditerProfil.setText("Editer Profil");
        jCheckBoxMenuItemEditerProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemEditerProfilActionPerformed(evt);
            }
        });
        jMenuEditer.add(jCheckBoxMenuItemEditerProfil);

        jMenuBar1.add(jMenuEditer);

        jMenuVue.setText("Vue");

        jMenuObjets.setText("Objets");

        jCheckBoxMenuItemHayon.setSelected(true);
        jCheckBoxMenuItemHayon.setText("Hayon");
        jCheckBoxMenuItemHayon.setToolTipText("");
        jCheckBoxMenuItemHayon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemHayonActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemHayon);

        jCheckBoxMenuItemPlancher.setSelected(true);
        jCheckBoxMenuItemPlancher.setText("Plancher");
        jCheckBoxMenuItemPlancher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemPlancherActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemPlancher);

        jCheckBoxMenuItemPoutreArriere.setSelected(true);
        jCheckBoxMenuItemPoutreArriere.setText("Poutre arrière");
        jCheckBoxMenuItemPoutreArriere.setToolTipText("");
        jCheckBoxMenuItemPoutreArriere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemPoutreArriereActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemPoutreArriere);

        jCheckBoxMenuItemRessort.setSelected(true);
        jCheckBoxMenuItemRessort.setText("Ressort");
        jCheckBoxMenuItemRessort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemRessortActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemRessort);

        jCheckBoxMenuItemProfil.setSelected(true);
        jCheckBoxMenuItemProfil.setText("Profil");
        jCheckBoxMenuItemProfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemProfilActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemProfil);

        jCheckBoxMenuItemMurSeparateur.setSelected(true);
        jCheckBoxMenuItemMurSeparateur.setText("Mur Separateur");
        jCheckBoxMenuItemMurSeparateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemMurSeparateurActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemMurSeparateur);

        jCheckBoxMenuItemToit.setSelected(true);
        jCheckBoxMenuItemToit.setText("Toit");
        jCheckBoxMenuItemToit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemToitActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemToit);

        jCheckBoxMenuItemPorte.setSelected(true);
        jCheckBoxMenuItemPorte.setText("Porte");
        jCheckBoxMenuItemPorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemPorteActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemPorte);

        jCheckBoxMenuItemFenetre.setSelected(true);
        jCheckBoxMenuItemFenetre.setText("Fenetre");
        jCheckBoxMenuItemFenetre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemFenetreActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemFenetre);

        jCheckBoxMenuItemMatelas.setSelected(true);
        jCheckBoxMenuItemMatelas.setText("Matelas");
        jCheckBoxMenuItemMatelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemMatelasActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemMatelas);

        jCheckBoxMenuItemHumain.setSelected(true);
        jCheckBoxMenuItemHumain.setText("Humain");
        jCheckBoxMenuItemHumain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemHumainActionPerformed(evt);
            }
        });
        jMenuObjets.add(jCheckBoxMenuItemHumain);

        jMenuVue.add(jMenuObjets);

        jMenuItemAfficherTout.setText("RéafficherTout");
        jMenuItemAfficherTout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAfficherToutActionPerformed(evt);
            }
        });
        jMenuVue.add(jMenuItemAfficherTout);

        jMenuBar1.add(jMenuVue);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                    .addComponent(jPanelEchelleMesures, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanelElements, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanelAffichage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelEchelleMesures, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelElements, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelAffichage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUndoActionPerformed
        controller.undo();
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
        remplirChamps();
        checkUndoRedo();
    }//GEN-LAST:event_btnUndoActionPerformed

    private void jCheckBoxMenuItemPlancherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPlancherActionPerformed
        
        //Si le checkbox est à faux il faut enlever l'élément de la liste
        if(!jCheckBoxMenuItemPlancher.getState()){
            controller.setPlancherAffiche(false);
            plancherOptions.setVisible(false);
        } else {
            controller.setPlancherAffiche(true);
        }
        
        //Il faut aussi repaint
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemPlancherActionPerformed

    private void DrawingPanelContainerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrawingPanelContainerMouseClicked
        
        if(controller.selectionPointControle(evt.getPoint())){
            return;
        }
        
        
        hideOptionPanels();
        String selected = controller.selectionElement(evt.getPoint());
        
        switch(selected){
            case "Micro Roulotte":
                microRoulotteOptions.setVisible(true);
                break;
            case "Panneau Millieu":
                microRoulotteOptions.setVisible(true);
                panneauMurMilieuOptions.setVisible(true);
                break;
            case "Panneau Interne":
                microRoulotteOptions.setVisible(true);
                panneauMurInterneOptions.setVisible(true);
                break;
            case "Panneau Externe":
                microRoulotteOptions.setVisible(true);
                panneauMurExterneOptions.setVisible(true);
                break;
            case "Plancher":
                plancherOptions.setVisible(true);
                break;
            case "Mur":
                murOptions.setVisible(true);
                break;
            case "Toit":
                toitOptions.setVisible(true);
                break;
            case "PoutreArriere":
                poutreOptions.setVisible(true);
                break;
            case "Hayon":
                hayonOptions.setVisible(true);
                break;
            case "Ressort":
                ressortOptions.setVisible(true);
                break;
            case "Porte":
                porteOptions.setVisible(true);
                break;
            case "Fenetre":
                fenetreOptions.setVisible(true);
                break;
            case "Matelas":
                matelasOptions.setVisible(true);
                break;
            case "Humain":
                humainOptions.setVisible(true);
                break;
        }
        
        if (jCheckBoxMenuItemEditerProfil.getState()){
           afficheEllipsesPanel();
        }
        else {
            desafficheEllipsesPanel();
        }
        
        
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
        remplirChamps();
    }//GEN-LAST:event_DrawingPanelContainerMouseClicked

    private void microroulotteLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_microroulotteLargeurActionPerformed
        try {
            controller.setMicroRoulotteLargeur(evt.getActionCommand());
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
           String s = controller.getMicroRoulotte().getLargeur().getString();
           microroulotteLargeur.setText(s);            
           JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }   
        
        
    }//GEN-LAST:event_microroulotteLargeurActionPerformed

    private void microroulotteHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_microroulotteHauteurActionPerformed
        try {
            controller.setMicroRoulotteHauteur(evt.getActionCommand());
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getHauteur().getString();
            microroulotteHauteur.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        } 
        
    }//GEN-LAST:event_microroulotteHauteurActionPerformed

    private void jCheckBoxMenuItemHayonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemHayonActionPerformed
        //Si le checkbox est à faux il faut enlever l'élément de la liste
        if(!jCheckBoxMenuItemHayon.getState()){
            controller.setHayonAffiche(false);
            hayonOptions.setVisible(false);
        } else {
            controller.setHayonAffiche(true);
        }
        
        //Il faut aussi repaint
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemHayonActionPerformed

    private void jCheckBoxMenuItemPoutreArriereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPoutreArriereActionPerformed
        if(!jCheckBoxMenuItemPoutreArriere.getState()){
            controller.setPoutreArriereAffiche(false);
            poutreOptions.setVisible(false);
        } else {
            controller.setPoutreArriereAffiche(true);
        }
        
        //Il faut aussi repaint
        drawingPanel.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemPoutreArriereActionPerformed

    private void plancherEpaisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plancherEpaisseurActionPerformed
        
        try {
            controller.setPlancherEpaisseur(evt.getActionCommand());
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPlancher().getEpaisseur().getString();
            plancherEpaisseur.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_plancherEpaisseurActionPerformed

    private void hayonEpaisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hayonEpaisseurActionPerformed
        try {
            controller.setHayonEpaisseur(evt.getActionCommand());
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getHayon().getEpaisseur().getString();
            hayonEpaisseur.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        } 
        
    }//GEN-LAST:event_hayonEpaisseurActionPerformed

    private void microroulotteLargeurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_microroulotteLargeurFocusLost
        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setMicroRoulotteLargeur(src.getText());
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getLargeur().getString();
        microroulotteLargeur.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        DrawingPanelContainer.repaint();       
    }//GEN-LAST:event_microroulotteLargeurFocusLost

    private void poutreLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poutreLargeurActionPerformed
        try {
            controller.setPoutreArriereLargeur(evt.getActionCommand());
            DrawingPanelContainer.repaint();
        
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPoutreArriere().getLargeur().getString();
            poutreLargeur.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        } 
        
    }//GEN-LAST:event_poutreLargeurActionPerformed

    private void plancherMargeAvantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plancherMargeAvantActionPerformed
        
        try {
            controller.setPlancherMargeAvant(evt.getActionCommand());
            DrawingPanelContainer.repaint();
        
            controller.pushToHistory();
            checkUndoRedo();
            
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPlancher().getMargeAvant().getString();
        plancherMargeAvant.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        } 
        
    }//GEN-LAST:event_plancherMargeAvantActionPerformed

    private void plancherMargeArriereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plancherMargeArriereActionPerformed
        try {
            controller.setPlancherMargeArriere(evt.getActionCommand());
            DrawingPanelContainer.repaint();
        
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPlancher().getMargeArriere().getString();
        plancherMargeArriere.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }  
        
    }//GEN-LAST:event_plancherMargeArriereActionPerformed

    private void plancherEpaisseurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_plancherEpaisseurFocusLost

        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setPlancherEpaisseur(src.getText());
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPlancher().getEpaisseur().getString();
        plancherEpaisseur.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        } 
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_plancherEpaisseurFocusLost

    private void plancherMargeAvantFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_plancherMargeAvantFocusLost
        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setPlancherMargeAvant(src.getText());
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPlancher().getMargeAvant().getString();
        plancherMargeAvant.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_plancherMargeAvantFocusLost

    private void plancherMargeArriereFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_plancherMargeArriereFocusLost
        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setPlancherMargeArriere(src.getText());
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPlancher().getMargeArriere().getString();
        plancherMargeArriere.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_plancherMargeArriereFocusLost

    private void poutreLargeurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_poutreLargeurFocusLost
        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setPoutreArriereLargeur(src.getText());

        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPoutreArriere().getLargeur().getString();
        poutreLargeur.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_poutreLargeurFocusLost

    private void poutreHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poutreHauteurActionPerformed
        try {
            controller.setPoutreArriereHauteur(evt.getActionCommand());
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPoutreArriere().getHauteur().getString();
        poutreHauteur.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_poutreHauteurActionPerformed

    private void poutreHauteurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_poutreHauteurFocusLost
        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setPoutreArriereHauteur(src.getText());
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getPoutreArriere().getHauteur().getString();
        poutreHauteur.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_poutreHauteurFocusLost

    private void hayonDistancePoutreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hayonDistancePoutreActionPerformed
        try {
            controller.setHayonDistancePoutre(evt.getActionCommand());
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getHayon().getDistanceDePoutre().getString();
        hayonDistancePoutre.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_hayonDistancePoutreActionPerformed

    private void hayonDistancePoutreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hayonDistancePoutreFocusLost
        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setHayonDistancePoutre(src.getText());
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getHayon().getDistanceDePoutre().getString();
        hayonDistancePoutre.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_hayonDistancePoutreFocusLost

    private void hayonDistancePlancherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hayonDistancePlancherActionPerformed
        try {
            controller.setHayonDistancePlancher(evt.getActionCommand());
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getHayon().getDistanceDuPlancher().getString();
        hayonDistancePlancher.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_hayonDistancePlancherActionPerformed

    private void hayonDistancePlancherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hayonDistancePlancherFocusLost

        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setHayonDistancePlancher(src.getText());
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getHayon().getDistanceDuPlancher().getString();
        hayonDistancePlancher.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_hayonDistancePlancherFocusLost

    private void hayonTraitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hayonTraitActionPerformed
        try {
            controller.setHayonTrait(evt.getActionCommand());
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getHayon().getTraitDeScie().getString();
        hayonTrait.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_hayonTraitActionPerformed

    private void hayonTraitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hayonTraitFocusLost
        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setHayonTrait(src.getText());
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getHayon().getTraitDeScie().getString();
        hayonTrait.setText(s);
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_hayonTraitFocusLost

    private void hayonEpaisseurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hayonEpaisseurFocusLost
        try {
            JTextField src = (JTextField)evt.getSource();
            controller.setHayonEpaisseur(src.getText());
            DrawingPanelContainer.repaint();
        }
        catch(Exception e) {
            String s = controller.getMicroRoulotte().getHayon().getEpaisseur().getString();
        hayonEpaisseur.setText(s);    
           JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_hayonEpaisseurFocusLost

    /**
     * ZOOM
     * @param evt 
     */
    private void DrawingPanelContainerMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_DrawingPanelContainerMouseWheelMoved

        Point center = evt.getPoint();
        drawingPanel.zoom(evt.getWheelRotation(), evt.getScrollAmount(),center);
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();

    }//GEN-LAST:event_DrawingPanelContainerMouseWheelMoved

    private void jCheckBoxMenuItemEditerProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemEditerProfilActionPerformed
        
        hideOptionPanels();
        
        if(jCheckBoxMenuItemEditerProfil.getState()){
            //Enlever les éléments non nécéssaire.
            controller.setPlancherAffiche(false);
            controller.setHayonAffiche(false);
            controller.setPoutreArriereAffiche(false);
            if(controller.getMurSeparateur() != null){
                controller.setMurSeparateurAffiche(false);
            }
            controller.setToitAffiche(false);
            controller.setRessortAffiche(false);
            controller.setPorteAffiche(false);
            if(controller.getFenetre() != null){
                controller.setFenetreAffiche(false);
            }
            if(controller.getMatelas() != null){
                controller.setMatelasAffiche(false);
            }
            if(controller.getHumain() != null){
                controller.setHumainAffiche(false);
            }
            
            //Mettre les panels des mesures des éllipses
           afficheEllipsesPanel();
            
            //Afficher les éllipses
            controller.setAfficheEllipses(true);
            
            //Décocher les affichage des éléments non nécéssaire
            jCheckBoxMenuItemHayon.setState(false);
            jCheckBoxMenuItemPlancher.setState(false);
            jCheckBoxMenuItemPoutreArriere.setState(false);
            jCheckBoxMenuItemProfil.setState(true);
            jCheckBoxMenuItemMurSeparateur.setState(false);
            jCheckBoxMenuItemToit.setState(false);
            jCheckBoxMenuItemRessort.setState(false);
            jCheckBoxMenuItemPorte.setState(false);
            jCheckBoxMenuItemFenetre.setState(false);
                    
            
            jCheckBoxMenuItemHayon.setEnabled(false);
            jCheckBoxMenuItemPlancher.setEnabled(false);
            jCheckBoxMenuItemPoutreArriere.setEnabled(false);
            jCheckBoxMenuItemProfil.setEnabled(false);
            jCheckBoxMenuItemMurSeparateur.setEnabled(false);
            jCheckBoxMenuItemToit.setEnabled(false);
            jCheckBoxMenuItemRessort.setEnabled(false);
            jCheckBoxMenuItemPorte.setEnabled(false);
            jCheckBoxMenuItemFenetre.setEnabled(false);
            
            jButtonMurSeparateur.setEnabled(false);
            jButtonFenetre.setEnabled(false);
            jButtonMatelas.setEnabled(false);
            jButtonHumain.setEnabled(false);

            
        }
        else{
            //Enlever les panels des mesures des éllipses
            desafficheEllipsesPanel();
            //Remettre les éléments nécéssaire
            
           //Afficher les éllipses
            controller.setPlancherAffiche(true);
            controller.setHayonAffiche(true);
            controller.setPoutreArriereAffiche(true);
            if(controller.getMurSeparateur() != null){
                controller.setMurSeparateurAffiche(false);
            }
            controller.setToitAffiche(true);
            controller.setAfficheEllipses(false);
            controller.setRessortAffiche(true);
            controller.setPorteAffiche(true);
            if(controller.getFenetre() != null){
                controller.setFenetreAffiche(true);
            }
            if(controller.getMatelas() != null){
                controller.setMatelasAffiche(true);
            }
            if(controller.getHumain() != null){
                controller.setHumainAffiche(true);
            }

            
            jCheckBoxMenuItemHayon.setState(true);
            jCheckBoxMenuItemPlancher.setState(true);
            jCheckBoxMenuItemPoutreArriere.setState(true);
            jCheckBoxMenuItemProfil.setState(true);
            jCheckBoxMenuItemMurSeparateur.setState(true);
            jCheckBoxMenuItemToit.setState(true);
            jCheckBoxMenuItemRessort.setState(true);
            jCheckBoxMenuItemPorte.setState(true);
            jCheckBoxMenuItemFenetre.setState(true);
            
            jCheckBoxMenuItemHayon.setEnabled(true);
            jCheckBoxMenuItemPlancher.setEnabled(true);
            jCheckBoxMenuItemPoutreArriere.setEnabled(true);
            jCheckBoxMenuItemProfil.setEnabled(true);
            jCheckBoxMenuItemMurSeparateur.setEnabled(true);
            jCheckBoxMenuItemToit.setEnabled(true);
            jCheckBoxMenuItemRessort.setEnabled(true);
            jCheckBoxMenuItemPorte.setEnabled(true);
            jCheckBoxMenuItemFenetre.setEnabled(true);
            
            jButtonMurSeparateur.setEnabled(true);
            jButtonFenetre.setEnabled(true);
            jButtonMatelas.setEnabled(true);
            jButtonHumain.setEnabled(true);
        }
        
        repaint();
        
    }//GEN-LAST:event_jCheckBoxMenuItemEditerProfilActionPerformed

    private void EllipseHGHauteurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseHGHauteurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseHGHauteurFocusLost

    private void EllipseHGHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseHGHauteurActionPerformed
        
        //Aller cherche la valeur dans le champ et la stocké
        
     
        //Deplacer le centre de l'éllipse et changer la hauteur
        try {
            controller.getEllipses().get(0).setHauteur(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
        
        
        
    }//GEN-LAST:event_EllipseHGHauteurActionPerformed

    private void EllipseHGLargeurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseHGLargeurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseHGLargeurFocusLost

    private void EllipseHGLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseHGLargeurActionPerformed

        
        try {
            controller.getEllipses().get(0).setLargeur(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseHGLargeurActionPerformed

    private void EllipseHDHauteurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseHDHauteurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseHDHauteurFocusLost

    private void EllipseHDHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseHDHauteurActionPerformed
        
        try {
            controller.getEllipses().get(1).setHauteur(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseHDHauteurActionPerformed

    private void EllipseHDLargeurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseHDLargeurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseHDLargeurFocusLost

    private void EllipseHDLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseHDLargeurActionPerformed
        
        try {
            controller.getEllipses().get(1).setLargeur(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseHDLargeurActionPerformed

    private void EllipseBGHauteurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseBGHauteurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseBGHauteurFocusLost

    private void EllipseBGHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseBGHauteurActionPerformed
       
        try {
            controller.getEllipses().get(2).setHauteur(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseBGHauteurActionPerformed

    private void EllipseBGLargeurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseBGLargeurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseBGLargeurFocusLost

    private void EllipseBGLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseBGLargeurActionPerformed
        
        try {
            controller.getEllipses().get(2).setLargeur(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseBGLargeurActionPerformed

    private void EllipseBDHauteurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseBDHauteurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseBDHauteurFocusLost

    private void EllipseBDHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseBDHauteurActionPerformed
        
        try {
            controller.getEllipses().get(3).setHauteur(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseBDHauteurActionPerformed

    private void EllipseBDLargeurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseBDLargeurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseBDLargeurFocusLost

    private void EllipseBDLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseBDLargeurActionPerformed
        
        try {
            controller.getEllipses().get(3).setLargeur(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseBDLargeurActionPerformed

    private void EllipseHGPositionXFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseHGPositionXFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseHGPositionXFocusLost

    private void EllipseHGPositionXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseHGPositionXActionPerformed
        
        try {
            controller.getEllipses().get(0).setPositionX(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseHGPositionXActionPerformed

    private void EllipseHGPositionYFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseHGPositionYFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseHGPositionYFocusLost

    private void EllipseHGPositionYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseHGPositionYActionPerformed
        try {
            controller.getEllipses().get(0).setPositionY(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseHGPositionYActionPerformed

    private void EllipseHDPositionXFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseHDPositionXFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseHDPositionXFocusLost

    private void EllipseHDPositionXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseHDPositionXActionPerformed
        try {
            controller.getEllipses().get(1).setPositionX(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseHDPositionXActionPerformed

    private void EllipseHDPositionYFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseHDPositionYFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseHDPositionYFocusLost

    private void EllipseHDPositionYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseHDPositionYActionPerformed
        try {
            controller.getEllipses().get(1).setPositionY(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseHDPositionYActionPerformed

    private void EllipseBGPositionXFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseBGPositionXFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseBGPositionXFocusLost

    private void EllipseBGPositionXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseBGPositionXActionPerformed
        try {
            controller.getEllipses().get(2).setPositionX(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseBGPositionXActionPerformed

    private void EllipseBGPositionYFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseBGPositionYFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseBGPositionYFocusLost

    private void EllipseBGPositionYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseBGPositionYActionPerformed
        try {
            controller.getEllipses().get(2).setPositionY(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseBGPositionYActionPerformed

    private void EllipseBDPositionXFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseBDPositionXFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseBDPositionXFocusLost

    private void EllipseBDPositionXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseBDPositionXActionPerformed
        try {
            controller.getEllipses().get(3).setPositionX(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseBDPositionXActionPerformed

    private void EllipseBDPositionYFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EllipseBDPositionYFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_EllipseBDPositionYFocusLost

    private void EllipseBDPositionYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EllipseBDPositionYActionPerformed
        try {
            controller.getEllipses().get(3).setPositionY(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_EllipseBDPositionYActionPerformed

    private void poutrePositionXFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_poutrePositionXFocusLost
        
    }//GEN-LAST:event_poutrePositionXFocusLost

    private void poutrePositionXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poutrePositionXActionPerformed
        try {
            controller.getPoutreArriere().setPositionX(controller.newMesureString(evt.getActionCommand()));
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
        checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        
    }//GEN-LAST:event_poutrePositionXActionPerformed

    private void poutrePositionYFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_poutrePositionYFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_poutrePositionYFocusLost

    private void poutrePositionYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poutrePositionYActionPerformed
        try {
            controller.getPoutreArriere().setPositionY(controller.newMesureString(evt.getActionCommand()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
        controller.pushToHistory();
        checkUndoRedo();
    }//GEN-LAST:event_poutrePositionYActionPerformed

    private void zoomMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomMinusActionPerformed
        Point center = new Point(0,0);
        drawingPanel.zoom(1, 1,center);
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_zoomMinusActionPerformed

    private void zoomPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomPlusActionPerformed
        Point center = new Point(0,0);
        drawingPanel.zoom(-1, 1,center);
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_zoomPlusActionPerformed

    private void grilleDaideMesureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grilleDaideMesureActionPerformed
        try {
            controller.setGrilleDaideMesure(controller.newMesureString(evt.getActionCommand()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }

        
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_grilleDaideMesureActionPerformed

    private void jchkboxGrillePlacementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jchkboxGrillePlacementActionPerformed
        
        
        if(jchkboxGrillePlacement.isSelected())
            controller.setGrilleDaideisAffiche(true);
        else
            controller.setGrilleDaideisAffiche(false);
        
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jchkboxGrillePlacementActionPerformed

    private void btnRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRedoActionPerformed
        controller.redo();
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
        remplirChamps();
        checkUndoRedo();
    }//GEN-LAST:event_btnRedoActionPerformed

    private void jMenuItemExporterJPGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExporterJPGActionPerformed
        
        
        JFileChooser fileChooser= new JFileChooser();
        fileChooser.setSelectedFile(new File("MicroRoulotte.jpg"));
        
        int returnValue = fileChooser.showOpenDialog(this.getParent());
        
        if ( returnValue == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            if(fileToSave.getName().contains(".jpg"))
            {
                try {
                    drawingPanel.saveImage(fileToSave);
                    JOptionPane.showMessageDialog(null, "Exportation en JPG reussi!", "Exportation", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Nom doit contenir .jpg", "Erreur!", JOptionPane.ERROR_MESSAGE);
            
        }
        else
            JOptionPane.showMessageDialog(null, "Exportation JPG annulée.", "Erreur!", JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_jMenuItemExporterJPGActionPerformed

    private void jMenuItemExporterSVGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExporterSVGActionPerformed
        JFileChooser fileChooser= new JFileChooser();
        fileChooser.setSelectedFile(new File("MicroRoulotte.svg"));
        
        int returnValue = fileChooser.showOpenDialog(this.getParent());
        
        if ( returnValue == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            if(fileToSave.getName().contains(".svg"))
            {
                try {
                    controller.exportSVG(fileToSave);
                    JOptionPane.showMessageDialog(null, "Exportation en SVG reussi!", "Exportation", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Nom doit contenir .svg", "Erreur!", JOptionPane.ERROR_MESSAGE);
            
            
        }
        else
            JOptionPane.showMessageDialog(null, "Sauvegarde annulée.", "Erreur!", JOptionPane.ERROR_MESSAGE);
        
        
        
    }//GEN-LAST:event_jMenuItemExporterSVGActionPerformed

    private void jMenuItemSauvegarderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSauvegarderActionPerformed
        
        JFileChooser fileChooser= new JFileChooser();
        fileChooser.setSelectedFile(new File("MicroRoulotte.ser"));
        
        int returnValue = fileChooser.showOpenDialog(this.getParent());
        
        if ( returnValue == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            try {
                controller.sauvegarderProjet(fileToSave);
                DrawingPanelContainer.revalidate();
                DrawingPanelContainer.repaint();
                remplirChamps();
                JOptionPane.showMessageDialog(null, "Sauvegarde reussi!", "Sauvegarde", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            }
            
            
        }
        else
            JOptionPane.showMessageDialog(null, "Sauvegarde annulée.", "Erreur!", JOptionPane.ERROR_MESSAGE);
        
        
    }//GEN-LAST:event_jMenuItemSauvegarderActionPerformed

    private void jMenuItemOuvrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOuvrirActionPerformed
        
        JFileChooser fileChooser= new JFileChooser();
        
        int returnValue = fileChooser.showOpenDialog(this.getParent());
        
        if ( returnValue == JFileChooser.APPROVE_OPTION) {
            File fileToOpen = fileChooser.getSelectedFile();
            try {
                controller.ouvrirProjet(fileToOpen);
                DrawingPanelContainer.revalidate();
                DrawingPanelContainer.repaint();
                remplirChamps();
                JOptionPane.showMessageDialog(null, "Ouverture reussi!", "Ouverture", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            }
            
            
        }
        else
            JOptionPane.showMessageDialog(null, "Ouverture annulée.", "Erreur!", JOptionPane.ERROR_MESSAGE);
        
        
        
    }//GEN-LAST:event_jMenuItemOuvrirActionPerformed

    private void ressortLongueurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ressortLongueurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortLongueurFocusLost

    private void ressortLongueurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ressortLongueurActionPerformed
        
        
        try {
            controller.setRessortLongueur(evt.getActionCommand());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
        
        
    }//GEN-LAST:event_ressortLongueurActionPerformed

    private void ressortForceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ressortForceFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortForceFocusLost

    private void ressortForceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ressortForceActionPerformed
        try {
            controller.setRessortForce(evt.getActionCommand());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
        
        
    }//GEN-LAST:event_ressortForceActionPerformed

    private void ressortPositionSurMurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ressortPositionSurMurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortPositionSurMurFocusLost

    private void ressortPositionSurMurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ressortPositionSurMurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortPositionSurMurActionPerformed

    private void ressortPositionSurHayonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ressortPositionSurHayonFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortPositionSurHayonFocusLost

    private void ressortPositionSurHayonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ressortPositionSurHayonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortPositionSurHayonActionPerformed

    private void jCheckBoxMenuItemRessortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemRessortActionPerformed
        controller.setRessortAffiche(jCheckBoxMenuItemRessort.isSelected());
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemRessortActionPerformed

    private void hayonPoidsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hayonPoidsFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_hayonPoidsFocusLost

    private void hayonPoidsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hayonPoidsActionPerformed
        try {
            controller.setHayonPoids(evt.getActionCommand());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_hayonPoidsActionPerformed

    private void murEpaisseurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_murEpaisseurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_murEpaisseurFocusLost

    private void murEpaisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_murEpaisseurActionPerformed
        try {
            controller.setMurEpaisseur(evt.getActionCommand());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_murEpaisseurActionPerformed

    private void murDistancePoutreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_murDistancePoutreFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_murDistancePoutreFocusLost

    private void murDistancePoutreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_murDistancePoutreActionPerformed
        try {
            controller.setMurDistancePoutre(evt.getActionCommand());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_murDistancePoutreActionPerformed

    private void murDistancePlancherFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_murDistancePlancherFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_murDistancePlancherFocusLost

    private void murDistancePlancherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_murDistancePlancherActionPerformed
        try {
            controller.setMurDistancePlancher(evt.getActionCommand());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_murDistancePlancherActionPerformed

    private void toitEpaisseurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_toitEpaisseurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_toitEpaisseurFocusLost

    private void toitEpaisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toitEpaisseurActionPerformed
        try {
            controller.setToitEpaisseur(evt.getActionCommand());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_toitEpaisseurActionPerformed

    private void jRadioBtnImperialStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jRadioBtnImperialStateChanged
        // TODO add your handling code here:
        // TODO: Remove this
    }//GEN-LAST:event_jRadioBtnImperialStateChanged

    private void jRadioBtnImperialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBtnImperialActionPerformed
        // TODO add your handling code here:
        controller.switchMesure(MesureEnum.Imperial);
        repaint();
        remplirChamps();
    }//GEN-LAST:event_jRadioBtnImperialActionPerformed

    private void jRadioBtnMetriqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioBtnMetriqueActionPerformed
        // TODO add your handling code here:
        controller.switchMesure(MesureEnum.Metrique);
        repaint();
        remplirChamps();
    }//GEN-LAST:event_jRadioBtnMetriqueActionPerformed

    private void jCheckBoxMenuItemProfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemProfilActionPerformed
        controller.setProfilAffiche(jCheckBoxMenuItemProfil.isSelected());
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemProfilActionPerformed

    private void jCheckBoxMenuItemMurSeparateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemMurSeparateurActionPerformed
        controller.setMurSeparateurAffiche(jCheckBoxMenuItemMurSeparateur.isSelected());
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemMurSeparateurActionPerformed

    private void jCheckBoxMenuItemToitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemToitActionPerformed
        controller.setToitAffiche(jCheckBoxMenuItemToit.isSelected());
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemToitActionPerformed

    private void jButtonMurSeparateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMurSeparateurActionPerformed
        if (!controller.isMurAffiche()){
            controller.newMurSeparateur();
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            remplirChamps();
        }
    }//GEN-LAST:event_jButtonMurSeparateurActionPerformed
    private void changeToBezierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeToBezierActionPerformed

        controller.changeProfilTo("bezier");
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_changeToBezierActionPerformed

    private void changeToClassiqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeToClassiqueActionPerformed

        controller.changeProfilTo("classique");
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_changeToClassiqueActionPerformed

    private void jButtonSupprimerMurSeparateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerMurSeparateurActionPerformed
        controller.supprimerMurSeparateur();
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
        murOptions.setVisible(false);
    }//GEN-LAST:event_jButtonSupprimerMurSeparateurActionPerformed

    private void ressortStrokeLongueurFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ressortStrokeLongueurFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortStrokeLongueurFocusLost

    private void ressortStrokeLongueurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ressortStrokeLongueurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortStrokeLongueurActionPerformed

    private void ressortNoMcMasterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ressortNoMcMasterFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortNoMcMasterFocusLost

    private void ressortNoMcMasterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ressortNoMcMasterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ressortNoMcMasterActionPerformed

    private void porteLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porteLargeurActionPerformed
        try {
            controller.setPorteLargeur(porteLargeur.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }   
    }//GEN-LAST:event_porteLargeurActionPerformed

    private void porteHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porteHauteurActionPerformed
        try {
            controller.setPorteHauteur(porteHauteur.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }  
    }//GEN-LAST:event_porteHauteurActionPerformed

    private void porteDistanceToitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_porteDistanceToitActionPerformed
        try {
            controller.setPorteDistanceDeBoutToit(porteDistanceToit.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_porteDistanceToitActionPerformed

    private void fenetreLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fenetreLargeurActionPerformed
        try {
            controller.setFenetreLargeur(fenetreLargeur.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_fenetreLargeurActionPerformed

    private void fenetreHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fenetreHauteurActionPerformed
        try {
            controller.setFenetreHauteur(fenetreHauteur.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_fenetreHauteurActionPerformed

    private void fenetreDistanceToitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fenetreDistanceToitActionPerformed
        try {
            controller.setFenetreDistanceFromToit(fenetreDistanceToit.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_fenetreDistanceToitActionPerformed

    private void fenetreDistanceDevantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fenetreDistanceDevantActionPerformed
        try {
            controller.setFenetreDistanceFromFront(fenetreDistanceDevant.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_fenetreDistanceDevantActionPerformed

    private void jCheckBoxMenuItemPorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPorteActionPerformed
        controller.setPorteAffiche(jCheckBoxMenuItemPorte.isSelected());
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemPorteActionPerformed

    private void jCheckBoxMenuItemFenetreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemFenetreActionPerformed
        controller.setFenetreAffiche(jCheckBoxMenuItemFenetre.isSelected());
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemFenetreActionPerformed

    private void jButtonSupprimerFenetreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerFenetreActionPerformed
        controller.supprimerFenetre();
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
        fenetreOptions.setVisible(false);
    }//GEN-LAST:event_jButtonSupprimerFenetreActionPerformed

    private void jButtonFenetreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFenetreActionPerformed
        if (!controller.isMurAffiche()){
            controller.newFenetre();
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            remplirChamps();
        }
    }//GEN-LAST:event_jButtonFenetreActionPerformed

    private void DrawingPanelContainerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrawingPanelContainerMouseDragged
        // TODO add your handling code here:
        if(controller.getCurrentPointControle() != null){
            controller.dragPointControle(evt.getPoint());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
        }
    }//GEN-LAST:event_DrawingPanelContainerMouseDragged

    private void DrawingPanelContainerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrawingPanelContainerMousePressed
        if(controller.selectionPointControle(evt.getPoint())){
            return;
        }
        
        
        hideOptionPanels();
        String selected = controller.selectionElement(evt.getPoint());
        
        switch(selected){
            case "Micro Roulotte":
                microRoulotteOptions.setVisible(true);
                break;
            case "Plancher":
                plancherOptions.setVisible(true);
                break;
            case "Mur":
                murOptions.setVisible(true);
                break;
            case "Toit":
                toitOptions.setVisible(true);
                break;
            case "PoutreArriere":
                poutreOptions.setVisible(true);
                break;
            case "Hayon":
                hayonOptions.setVisible(true);
                break;
            case "Ressort":
                ressortOptions.setVisible(true);
                remplirChamps();
                break;
            case "Porte":
                porteOptions.setVisible(true);
                break;
            case "Fenetre":
                fenetreOptions.setVisible(true);
                break;
        }
        
        if (jCheckBoxMenuItemEditerProfil.getState()){
           afficheEllipsesPanel();
        }
        else {
            desafficheEllipsesPanel();
        }
    }//GEN-LAST:event_DrawingPanelContainerMousePressed

    private void DrawingPanelContainerMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrawingPanelContainerMouseReleased
        controller.setCurrentPointControle(null);
    }//GEN-LAST:event_DrawingPanelContainerMouseReleased

    private void vuePanneauMillieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vuePanneauMillieuActionPerformed
        //Plancher
        jCheckBoxMenuItemPlancher.setState(false);
        controller.setPlancherAffiche(false);
        
        //Hayon
        jCheckBoxMenuItemHayon.setState(false);
        controller.setHayonAffiche(false);
        
        //Poutre Arriere
        jCheckBoxMenuItemPoutreArriere.setState(false);
        controller.setPoutreArriereAffiche(false);
        
        //Toit
        jCheckBoxMenuItemToit.setState(false);
        controller.setToitAffiche(false);
        
        //Ressort
        jCheckBoxMenuItemRessort.setState(false);
        controller.setRessortAffiche(false);
        
        //Porte
        jCheckBoxMenuItemPorte.setState(false);
        controller.setPorteAffiche(false);
        
        //Profil
        jCheckBoxMenuItemProfil.setState(false);
        controller.setProfilAffiche(false);
        
        //Panneaux
        controller.setPanneauMillieuAffiche(true);
        controller.setPanneauExterneAffiche(false);
        controller.setPanneauInterneAffiche(false);
        
        //Panneaux Options
        panneauMurInterneOptions.setVisible(false);
        panneauMurMilieuOptions.setVisible(true);
        panneauMurExterneOptions.setVisible(false);
        
        //Mur Séparateur
        if(controller.getMurSeparateur()!=null)
            controller.setMurSeparateurAffiche(false);
        jCheckBoxMenuItemMurSeparateur.setState(false);
        
        //Fenetre
        if(controller.getFenetre() !=null)
            controller.setFenetreAffiche(false);
        jCheckBoxMenuItemFenetre.setState(false);
        
        //Matelas
        if(controller.getMatelas() != null)
            controller.setMatelasAffiche(false);
        jCheckBoxMenuItemMatelas.setState(false);
        
        //Humain
        if(controller.getHumain() != null){
            controller.setHumainAffiche(false);
        }
        jCheckBoxMenuItemMatelas.setState(false);
                
        //Redessiner
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
        
        
        
    }//GEN-LAST:event_vuePanneauMillieuActionPerformed

    private void vueMurInterneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vueMurInterneActionPerformed
        //Profil
        jCheckBoxMenuItemProfil.setState(false);
        controller.setProfilAffiche(false);
        
        //Plancher
        jCheckBoxMenuItemPlancher.setState(false);
        controller.setPlancherAffiche(false);
        
        //Hayon
        jCheckBoxMenuItemHayon.setState(false);
        controller.setHayonAffiche(false);
        
        //Poutre Arriere
        jCheckBoxMenuItemPoutreArriere.setState(false);
        controller.setPoutreArriereAffiche(false);
        
        //Toit
        jCheckBoxMenuItemToit.setState(false);
        controller.setToitAffiche(false);
        
        //Ressort
        jCheckBoxMenuItemRessort.setState(false);
        controller.setRessortAffiche(false);
        
        //Porte
        jCheckBoxMenuItemPorte.setState(false);
        controller.setPorteAffiche(false);
        
        //Murs
        controller.setPanneauMillieuAffiche(false);
        controller.setPanneauExterneAffiche(false);
        controller.setPanneauInterneAffiche(true);
        
        //Panneaux Options
        panneauMurInterneOptions.setVisible(true);
        panneauMurMilieuOptions.setVisible(false);
        panneauMurExterneOptions.setVisible(false);
        
        //Mur Séparateur
        if(controller.getMurSeparateur()!=null)
            controller.setMurSeparateurAffiche(false);
        jCheckBoxMenuItemMurSeparateur.setState(false);
        
        //Fenetre
        if(controller.getFenetre() !=null)
            controller.setFenetreAffiche(false);
        jCheckBoxMenuItemFenetre.setState(false);
        
        //Matelas
        if(controller.getMatelas() != null)
            controller.setMatelasAffiche(false);
        jCheckBoxMenuItemMatelas.setState(false);
        
        //Humain
        if(controller.getHumain() != null){
            controller.setHumainAffiche(false);
        }
        jCheckBoxMenuItemMatelas.setState(false);
        
        //Redessiner
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_vueMurInterneActionPerformed

    private void vueMurExterneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vueMurExterneActionPerformed
        
        //Profil
        jCheckBoxMenuItemProfil.setState(false);
        controller.setProfilAffiche(false);
        
        //Plancher
        jCheckBoxMenuItemPlancher.setState(false);
        controller.setPlancherAffiche(false);
        
        //Hayon
        jCheckBoxMenuItemHayon.setState(false);
        controller.setHayonAffiche(false);
        
        //Poutre Arriere
        jCheckBoxMenuItemPoutreArriere.setState(false);
        controller.setPoutreArriereAffiche(false);
        
        //Toit
        jCheckBoxMenuItemToit.setState(false);
        controller.setToitAffiche(false);
        
        //Ressort
        jCheckBoxMenuItemRessort.setState(false);
        controller.setRessortAffiche(false);
        
        //Porte
        jCheckBoxMenuItemPorte.setState(false);
        controller.setPorteAffiche(false);
        
        //Panneaux
        controller.setPanneauMillieuAffiche(false);
        controller.setPanneauExterneAffiche(true);
        controller.setPanneauInterneAffiche(false);
        
        //Panneaux Options
        panneauMurInterneOptions.setVisible(false);
        panneauMurMilieuOptions.setVisible(false);
        panneauMurExterneOptions.setVisible(true);
        
        //Mur Séparateur
        if(controller.getMurSeparateur()!=null)
            controller.setMurSeparateurAffiche(false);
        jCheckBoxMenuItemMurSeparateur.setState(false);
        
        
        //Fenetre
        if(controller.getFenetre() !=null)
            controller.setFenetreAffiche(false);
        jCheckBoxMenuItemFenetre.setState(false);
        
        //Matelas
        if(controller.getMatelas() != null)
            controller.setMatelasAffiche(false);
        jCheckBoxMenuItemMatelas.setState(false);
        
        //Humain
        if(controller.getHumain() != null){
            controller.setHumainAffiche(false);
        }
        jCheckBoxMenuItemHumain.setState(false);
        
        
        //Redessiner
        
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_vueMurExterneActionPerformed

    private void panneauMillieuEpaisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_panneauMillieuEpaisseurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_panneauMillieuEpaisseurActionPerformed

    private void panneauInterneEpaisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_panneauInterneEpaisseurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_panneauInterneEpaisseurActionPerformed

    private void panneauExterneEpaisseurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_panneauExterneEpaisseurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_panneauExterneEpaisseurActionPerformed

    private void jMenuItemAfficherToutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAfficherToutActionPerformed
        hideOptionPanels();
        
        //Profil
        jCheckBoxMenuItemProfil.setState(true);
        controller.setProfilAffiche(true);
        
        //Plancher
        jCheckBoxMenuItemPlancher.setState(true);
        controller.setPlancherAffiche(true);
        
        //Hayon
        jCheckBoxMenuItemHayon.setState(true);
        controller.setHayonAffiche(true);
        
        //Poutre Arriere
        jCheckBoxMenuItemPoutreArriere.setState(true);
        controller.setPoutreArriereAffiche(true);
        
        //Toit
        jCheckBoxMenuItemToit.setState(true);
        controller.setToitAffiche(true);
        
        //Ressort
        jCheckBoxMenuItemRessort.setState(true);
        controller.setRessortAffiche(true);
        
        //Porte
        jCheckBoxMenuItemPorte.setState(true);
        controller.setPorteAffiche(true);
        
        //Panneaux
        controller.setPanneauMillieuAffiche(false);
        controller.setPanneauExterneAffiche(false);
        controller.setPanneauInterneAffiche(false);
        
        //Panneaux Options
        panneauMurInterneOptions.setVisible(false);
        panneauMurMilieuOptions.setVisible(false);
        panneauMurExterneOptions.setVisible(false);
        
        //fenettre
        if(controller.getFenetre() != null){
            jCheckBoxMenuItemFenetre.setState(true);
            controller.setFenetreAffiche(true);
        }
        
        //Mur
        if(controller.getMurSeparateur() != null){
            jCheckBoxMenuItemMurSeparateur.setState(true);
            controller.setMurSeparateurAffiche(true);
        }
        
        //Matelas
        if(controller.getMatelas() != null){
            jCheckBoxMenuItemMatelas.setState(true);
            controller.setMatelasAffiche(true);
        }
        
        //Humain
        if(controller.getHumain() != null){
            jCheckBoxMenuItemHumain.setState(true);
            controller.setHumainAffiche(true);
        }
        
        //Redessiner
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jMenuItemAfficherToutActionPerformed
    private void matelasLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matelasLargeurActionPerformed
        try {
            controller.setMatelasLargeur(matelasLargeur.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_matelasLargeurActionPerformed

    private void matelasHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matelasHauteurActionPerformed
        try {
            controller.setMatelasHauteur(matelasHauteur.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_matelasHauteurActionPerformed

    private void jButtonMatelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMatelasActionPerformed
        if (!controller.isMurAffiche()){
            controller.newMatelas();
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            remplirChamps();
        }
    }//GEN-LAST:event_jButtonMatelasActionPerformed

    private void jButtonSupprimerMatelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerMatelasActionPerformed
        controller.supprimerMatelas();
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
        matelasOptions.setVisible(false);
    }//GEN-LAST:event_jButtonSupprimerMatelasActionPerformed

    private void humainDistanceBoutPlancherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_humainDistanceBoutPlancherActionPerformed
        try {
            controller.setHumainDistanceBoutPlancher(humainDistanceBoutPlancher.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_humainDistanceBoutPlancherActionPerformed

    private void jButtonSupprimerHumainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerHumainActionPerformed
        controller.supprimerHumain();
        DrawingPanelContainer.revalidate();
        DrawingPanelContainer.repaint();
        humainOptions.setVisible(false);
    }//GEN-LAST:event_jButtonSupprimerHumainActionPerformed

    private void humainHauteurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_humainHauteurActionPerformed
        try {
            controller.setHumainHauteur(humainHauteur.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_humainHauteurActionPerformed

    private void humainLargeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_humainLargeurActionPerformed
        try {
            controller.setHumainLargeur(humainLargeur.getText());
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            controller.pushToHistory();
            checkUndoRedo();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur!", JOptionPane.ERROR_MESSAGE);
            remplirChamps();
        }
    }//GEN-LAST:event_humainLargeurActionPerformed

    private void jButtonHumainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHumainActionPerformed
        if (!controller.isMurAffiche()){
            controller.newHumain();
            DrawingPanelContainer.revalidate();
            DrawingPanelContainer.repaint();
            remplirChamps();
        }
    }//GEN-LAST:event_jButtonHumainActionPerformed

    private void epaisseurPanneauMurInterneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_epaisseurPanneauMurInterneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_epaisseurPanneauMurInterneActionPerformed

    private void epaisseurPanneauMurMilieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_epaisseurPanneauMurMilieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_epaisseurPanneauMurMilieuActionPerformed

    private void epaisseurPanneauMurExterneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_epaisseurPanneauMurExterneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_epaisseurPanneauMurExterneActionPerformed

    private void jCheckBoxMenuItemMatelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemMatelasActionPerformed
        //Si le checkbox est à faux il faut enlever l'élément de la liste
        if(!jCheckBoxMenuItemMatelas.getState()){
            if(controller.getMatelas() != null){
                controller.setMatelasAffiche(false);
                matelasOptions.setVisible(false);
            }
        } else {
            controller.setMatelasAffiche(true);
        }
        
        //Il faut aussi repaint
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemMatelasActionPerformed

    private void jCheckBoxMenuItemHumainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemHumainActionPerformed
        if(!jCheckBoxMenuItemHumain.getState()){
            if(controller.getHumain() != null){
                controller.setHumainAffiche(false);
                matelasOptions.setVisible(false);
            }
        } else {
            controller.setHumainAffiche(true);
        }
        
        DrawingPanelContainer.repaint();
    }//GEN-LAST:event_jCheckBoxMenuItemHumainActionPerformed


    
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
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(App.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new App().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DrawingPanelContainer;
    private javax.swing.JTextField EllipseBDHauteur;
    private javax.swing.JTextField EllipseBDLargeur;
    private javax.swing.JTextField EllipseBDPositionX;
    private javax.swing.JTextField EllipseBDPositionY;
    private javax.swing.JTextField EllipseBGHauteur;
    private javax.swing.JTextField EllipseBGLargeur;
    private javax.swing.JTextField EllipseBGPositionX;
    private javax.swing.JTextField EllipseBGPositionY;
    private javax.swing.JTextField EllipseHDHauteur;
    private javax.swing.JTextField EllipseHDLargeur;
    private javax.swing.JTextField EllipseHDPositionX;
    private javax.swing.JTextField EllipseHDPositionY;
    private javax.swing.JTextField EllipseHGHauteur;
    private javax.swing.JTextField EllipseHGLargeur;
    private javax.swing.JTextField EllipseHGPositionX;
    private javax.swing.JTextField EllipseHGPositionY;
    private javax.swing.JPanel Ellipses;
    private javax.swing.JPanel EllipsesBDOptions;
    private javax.swing.JPanel EllipsesBGOptions;
    private javax.swing.JPanel EllipsesHDOptions;
    private javax.swing.JPanel EllipsesHGOptions;
    private javax.swing.ButtonGroup btnGroupEchelleMesures;
    private javax.swing.JButton btnRedo;
    private javax.swing.JButton btnUndo;
    private javax.swing.JButton changeToBezier;
    private javax.swing.JButton changeToClassique;
    private javax.swing.JTextField epaisseurPanneauMurExterne;
    private javax.swing.JTextField epaisseurPanneauMurInterne;
    private javax.swing.JTextField epaisseurPanneauMurMilieu;
    private javax.swing.JTextField fenetreDistanceDevant;
    private javax.swing.JTextField fenetreDistanceToit;
    private javax.swing.JTextField fenetreHauteur;
    private javax.swing.JTextField fenetreLargeur;
    private javax.swing.JPanel fenetreOptions;
    private javax.swing.JTextField grilleDaideMesure;
    private javax.swing.JTextField hayonDistancePlancher;
    private javax.swing.JTextField hayonDistancePoutre;
    private javax.swing.JTextField hayonEpaisseur;
    private javax.swing.JPanel hayonOptions;
    private javax.swing.JTextField hayonPoids;
    private javax.swing.JTextField hayonTrait;
    private javax.swing.JTextField humainDistanceBoutPlancher;
    private javax.swing.JTextField humainHauteur;
    private javax.swing.JTextField humainLargeur;
    private javax.swing.JPanel humainOptions;
    private javax.swing.JButton jButtonFenetre;
    private javax.swing.JButton jButtonHumain;
    private javax.swing.JButton jButtonMatelas;
    private javax.swing.JButton jButtonMurSeparateur;
    private javax.swing.JButton jButtonSupprimerFenetre;
    private javax.swing.JButton jButtonSupprimerHumain;
    private javax.swing.JButton jButtonSupprimerMatelas;
    private javax.swing.JButton jButtonSupprimerMurSeparateur;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemEditerProfil;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemFenetre;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemHayon;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemHumain;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemMatelas;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemMurSeparateur;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPlancher;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPorte;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPoutreArriere;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemProfil;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemRessort;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemToit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelRessortLongueur;
    private javax.swing.JLabel jLabelStrokeLongueur;
    private javax.swing.JLabel jLabelressortNoMcMaster;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuEditer;
    private javax.swing.JMenu jMenuExporter;
    private javax.swing.JMenu jMenuFichier;
    private javax.swing.JMenuItem jMenuItemAfficherTout;
    private javax.swing.JMenuItem jMenuItemExporterJPG;
    private javax.swing.JMenuItem jMenuItemExporterSVG;
    private javax.swing.JMenuItem jMenuItemOuvrir;
    private javax.swing.JMenuItem jMenuItemRedo;
    private javax.swing.JMenuItem jMenuItemSauvegarder;
    private javax.swing.JMenuItem jMenuItemUndo;
    private javax.swing.JMenu jMenuObjets;
    private javax.swing.JMenu jMenuVue;
    private javax.swing.JPanel jPanelAffichage;
    private javax.swing.JPanel jPanelEchelleMesures;
    private javax.swing.JPanel jPanelElements;
    private javax.swing.JPanel jPanelMesures;
    private javax.swing.JRadioButton jRadioBtnImperial;
    private javax.swing.JRadioButton jRadioBtnMetrique;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JCheckBox jchkboxGrillePlacement;
    private javax.swing.JLabel lblPanelElements;
    private javax.swing.JLabel lblPanelMesures;
    private javax.swing.JLabel lvlPanelEchelleMesures;
    private javax.swing.JTextField matelasHauteur;
    private javax.swing.JTextField matelasLargeur;
    private javax.swing.JPanel matelasOptions;
    private javax.swing.JPanel microRoulotteOptions;
    private javax.swing.JTextField microroulotteHauteur;
    private javax.swing.JTextField microroulotteLargeur;
    private javax.swing.JTextField murDistancePlancher;
    private javax.swing.JTextField murDistancePoutre;
    private javax.swing.JTextField murEpaisseur;
    private javax.swing.JPanel murOptions;
    private javax.swing.JPanel panneauMurExterneOptions;
    private javax.swing.JPanel panneauMurInterneOptions;
    private javax.swing.JPanel panneauMurMilieuOptions;
    private javax.swing.JTextField plancherEpaisseur;
    private javax.swing.JTextField plancherMargeArriere;
    private javax.swing.JTextField plancherMargeAvant;
    private javax.swing.JPanel plancherOptions;
    private javax.swing.JTextField porteDistanceToit;
    private javax.swing.JTextField porteHauteur;
    private javax.swing.JTextField porteLargeur;
    private javax.swing.JPanel porteOptions;
    private javax.swing.JTextField poutreHauteur;
    private javax.swing.JTextField poutreLargeur;
    private javax.swing.JPanel poutreOptions;
    private javax.swing.JTextField poutrePositionX;
    private javax.swing.JTextField poutrePositionY;
    private javax.swing.JTextField ressortForce;
    private javax.swing.JTextField ressortLongueur;
    private javax.swing.JTextField ressortNoMcMaster;
    private javax.swing.JPanel ressortOptions;
    private javax.swing.JTextField ressortPositionSurHayon;
    private javax.swing.JTextField ressortPositionSurMur;
    private javax.swing.JTextField ressortStrokeLongueur;
    private javax.swing.JTextField toitEpaisseur;
    private javax.swing.JPanel toitOptions;
    private javax.swing.JButton vueMurExterne;
    private javax.swing.JButton vueMurInterne;
    private javax.swing.JButton vuePanneauMillieu;
    private javax.swing.JButton zoomMinus;
    private javax.swing.JButton zoomPlus;
    // End of variables declaration//GEN-END:variables
}
