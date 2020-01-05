/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.modification;

import com.karimandco.auth.*;
import com.pradyna.components.importexport.ImportExportCSV;
import com.pradyna.components.importexport.ImportExportJSON;
import com.pradyna.components.PanneauInformationsCompte;
import com.pradyna.components.Pays;
import com.pradyna.components.Ville;
import com.pradyna.components.importexport.ImportExportXML;
import com.pradyna.components.connexionmdp.DialogFormConnexionMdp;
import com.pradyna.components.choixfichier.DialogChoixFichier;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Damien F, Pawel R, Théo M
 */
public class PanneauFormModification extends javax.swing.JPanel {

    public com.pradyna.components.connexionmdp.DialogFormConnexionMdp cConnexionMdp;
    public com.pradyna.components.choixfichier.DialogChoixFichier cChoixFichier;

    javax.swing.JDialog panneauPereInscription = null;

    private Boolean nomOK = false;
    private Boolean prenomOK = false;
    private Boolean identifiantOK = true;
    private Boolean courrielOK = false;
    private Boolean numeroTelephoneOK = false;
    private Boolean dateNaissanceOK = false;
    private Boolean mdpOK = true;
    private Boolean mdpConfOK = true;

    private Boolean modificationOK = false;

    /**
     * Ce constructeur permet d'initialiser le nom des labels et de générer les
     * KeyListener pour capturer les actions.
     */
    public PanneauFormModification() {
        initComponents();

        panneauNom.setjLabelNomChamp("Nom *");
        panneauPrenom.setjLabelNomChamp("Prénom *");
        panneauIdentifiant.setjLabelNomChamp("Identifiant *");
        panneauCourriel.setjLabelNomChamp("Courriel *");
        panneauNumeroTelephone.setjLabelNomChamp("Numéro de téléphone *");
        panneauDateNaissance.setjLabelNomChamp("Date de naissance (jj/mm/aaaa) *");

        panneauMdp1.getjLabelNomChamp().setText("Mot de passe (6 à 12 chiffres) *");
        panneauMdp2.getjLabelNomChamp().setText("Confirmation du mot de passe *");

        KeyListener(panneauNom, 0);
        KeyListener(panneauPrenom, 1);
        KeyListener(panneauIdentifiant, 2);
        KeyListener(panneauCourriel, 3);
        KeyListener(panneauNumeroTelephone, 4);
        KeyListener(panneauDateNaissance, 5);

        panneauIdentifiant.getChamp2().setEditable(false);

        panneauNom.getChamp2().setText(Utilisateur.getInstance().getNom());
        panneauPrenom.getChamp2().setText(Utilisateur.getInstance().getPrenom());
        panneauIdentifiant.getChamp2().setText(Utilisateur.getIdentifiant());
        panneauCourriel.getChamp2().setText(Utilisateur.getInstance().getCourriel());
        panneauNumeroTelephone.getChamp2().setText(Utilisateur.getInstance().getNumeroTelephone());
        panneauDateNaissance.getChamp2().setText(Utilisateur.getInstance().getDateNaissance());

        panneauInformationsCompte1.getPanneauNumeroTelephoneDeux().getChamp2().setText(Utilisateur.getInstance().getNumeroTelephoneDeux());
        panneauInformationsCompte1.getPanneauSiteWeb().getChamp2().setText(Utilisateur.getInstance().getSiteWeb());
        panneauInformationsCompte1.getPanneauPays().getChamp2().setText(Utilisateur.getInstance().getPays());
        panneauInformationsCompte1.getPanneauCodePostal().getChamp2().setText(String.valueOf(Utilisateur.getInstance().getCodePostal()));
        panneauInformationsCompte1.getPanneauVille().getChamp2().setText(Utilisateur.getInstance().getVille());
        panneauInformationsCompte1.getPanneauNumeroRue().getChamp2().setText(String.valueOf(Utilisateur.getInstance().getNumeroRue()));
        panneauInformationsCompte1.getPanneauAdresse().getChamp2().setText(Utilisateur.getInstance().getAdresse());
        panneauInformationsCompte1.getPanneauInfoComp().getChamp2().setText(Utilisateur.getInstance().getInfoComplementaire());

        updateAllJLabelEtat();

        choisirImage1.afficherImage(Utilisateur.getInstance().getPhotoBDD());
        choisirImage1.setImageOK(true);
        choisirImage1.getjLabelSuccess().setForeground(Color.blue);
        choisirImage1.getjLabelSuccess().setText("Image ok");
    }

    public void setNomOK(Boolean nomOK) {
        this.nomOK = nomOK;
    }

    public void setPrenomOK(Boolean prenomOK) {
        this.prenomOK = prenomOK;
    }

    public void setIdentifiantOK(Boolean identifiantOK) {
        this.identifiantOK = identifiantOK;
    }

    public void setCourrielOK(Boolean courrielOK) {
        this.courrielOK = courrielOK;
    }

    public void setNumeroTelephoneOK(Boolean numeroTelephoneOK) {
        this.numeroTelephoneOK = numeroTelephoneOK;
    }

    public void setDateNaissanceOK(Boolean dateNaissanceOK) {
        this.dateNaissanceOK = dateNaissanceOK;
    }

    public void setMdpOK(Boolean mdpOK) {
        this.mdpOK = mdpOK;
    }

    public void setMdpConfOK(Boolean mdpConfOK) {
        this.mdpConfOK = mdpConfOK;
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public void setFenParentModification(javax.swing.JDialog i) {
        this.panneauPereInscription = i;
    }

    public Boolean getModificationOK() {
        return modificationOK;
    }

    public void setModificationOK(Boolean modificationOK) {
        this.modificationOK = modificationOK;
    }

    public PanneauChamp getPanneauCourriel() {
        return panneauCourriel;
    }

    public PanneauChamp getPanneauDateNaissance() {
        return panneauDateNaissance;
    }

    public PanneauChamp getPanneauNom() {
        return panneauNom;
    }

    public PanneauChamp getPanneauPrenom() {
        return panneauPrenom;
    }

    public PanneauInformationsCompte getPanneauInformationsCompte1() {
        return panneauInformationsCompte1;
    }

    public PanneauChamp getPanneauNumeroTelephone() {
        return panneauNumeroTelephone;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabelEtatMaj = new javax.swing.JLabel();
        jLabelEtatExportationImportation = new javax.swing.JLabel();
        jButtonImporter = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelInfoImp = new javax.swing.JPanel();
        panneauNom = new com.karimandco.auth.PanneauChamp();
        panneauPrenom = new com.karimandco.auth.PanneauChamp();
        panneauIdentifiant = new com.karimandco.auth.PanneauChamp();
        panneauCourriel = new com.karimandco.auth.PanneauChamp();
        panneauNumeroTelephone = new com.karimandco.auth.PanneauChamp();
        panneauDateNaissance = new com.karimandco.auth.PanneauChamp();
        jPanelInfoComp = new javax.swing.JPanel();
        panneauInformationsCompte1 = new com.pradyna.components.PanneauInformationsCompte();
        jPanelMdp = new javax.swing.JPanel();
        panneauMdp1 = new com.pradyna.components.connexionmdp.PanneauMdp();
        panneauMdp2 = new com.pradyna.components.connexionmdp.PanneauMdp();
        jLabelEtatMdp = new javax.swing.JLabel();
        jPanelPhoto = new javax.swing.JPanel();
        choisirImage1 = new com.karimandco.photo.ChoisirImage();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mon compte");

        jButton1.setText("Mettre à jour les informations");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelEtatMaj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabelEtatExportationImportation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButtonImporter.setText("Importer compte");
        jButtonImporter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImporterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInfoImpLayout = new javax.swing.GroupLayout(jPanelInfoImp);
        jPanelInfoImp.setLayout(jPanelInfoImpLayout);
        jPanelInfoImpLayout.setHorizontalGroup(
            jPanelInfoImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoImpLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelInfoImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelInfoImpLayout.createSequentialGroup()
                        .addComponent(panneauNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panneauPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInfoImpLayout.createSequentialGroup()
                        .addComponent(panneauIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panneauCourriel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelInfoImpLayout.createSequentialGroup()
                        .addComponent(panneauNumeroTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panneauDateNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanelInfoImpLayout.setVerticalGroup(
            jPanelInfoImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInfoImpLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addGroup(jPanelInfoImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panneauPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauCourriel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelInfoImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauNumeroTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauDateNaissance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(74, 74, 74))
        );

        jTabbedPane1.addTab("Info importantes", jPanelInfoImp);

        javax.swing.GroupLayout jPanelInfoCompLayout = new javax.swing.GroupLayout(jPanelInfoComp);
        jPanelInfoComp.setLayout(jPanelInfoCompLayout);
        jPanelInfoCompLayout.setHorizontalGroup(
            jPanelInfoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInfoCompLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(panneauInformationsCompte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanelInfoCompLayout.setVerticalGroup(
            jPanelInfoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoCompLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panneauInformationsCompte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Info complémentaires", jPanelInfoComp);

        jLabelEtatMdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelMdpLayout = new javax.swing.GroupLayout(jPanelMdp);
        jPanelMdp.setLayout(jPanelMdpLayout);
        jPanelMdpLayout.setHorizontalGroup(
            jPanelMdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMdpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMdpLayout.createSequentialGroup()
                        .addComponent(panneauMdp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panneauMdp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelEtatMdp, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelMdpLayout.setVerticalGroup(
            jPanelMdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMdpLayout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addGroup(jPanelMdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauMdp1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauMdp2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEtatMdp, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mot de passe", jPanelMdp);

        javax.swing.GroupLayout jPanelPhotoLayout = new javax.swing.GroupLayout(jPanelPhoto);
        jPanelPhoto.setLayout(jPanelPhotoLayout);
        jPanelPhotoLayout.setHorizontalGroup(
            jPanelPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPhotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(choisirImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPhotoLayout.setVerticalGroup(
            jPanelPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPhotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(choisirImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Photo", jPanelPhoto);

        jLabel2.setText("* = obligatoire");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabelEtatMaj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelEtatExportationImportation, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(jButtonImporter)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel2)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonImporter)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEtatExportationImportation, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEtatMaj, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateJLabelEtat();
        if (nomOK && prenomOK && identifiantOK && courrielOK && numeroTelephoneOK && dateNaissanceOK && mdpOK && mdpConfOK && panneauInformationsCompte1.getNumeroTelephoneDeuxOK() && panneauInformationsCompte1.getSiteWebOK() && panneauInformationsCompte1.getPaysOK() && panneauInformationsCompte1.getCodePostalOK() && panneauInformationsCompte1.getVilleOK() && panneauInformationsCompte1.getNumeroRueOK() && panneauInformationsCompte1.getAdresseOK() && panneauInformationsCompte1.getInfoCompOK() && choisirImage1.getImageOK()) {
            if (!String.valueOf(panneauMdp1.getjPasswordFieldResultat().getPassword()).equals("") && !String.valueOf(panneauMdp2.getjPasswordFieldResultat().getPassword()).equals("")) {
                Utilisateur.getInstance().setMdp(Cryptage.sha256(Cryptage.sha256(String.valueOf(this.panneauMdp1.getjPasswordFieldResultat().getPassword()))));
            }

            Utilisateur.getInstance().setNom(this.panneauNom.getChamp2().getText());
            Utilisateur.getInstance().setPrenom(this.panneauPrenom.getChamp2().getText());
            Utilisateur.getInstance().setNumeroTelephone(this.panneauNumeroTelephone.getChamp2().getText());
            Utilisateur.getInstance().setCourriel(this.panneauCourriel.getChamp2().getText());
            Utilisateur.getInstance().setDateNaissance(this.panneauDateNaissance.getChamp2().getText());

            if (choisirImage1.file != null) {
                Utilisateur.getInstance().setPhoto(choisirImage1.encodeToBlob());
            }

            Utilisateur.getInstance().setNumeroTelephoneDeux(this.panneauInformationsCompte1.getPanneauNumeroTelephoneDeux().getChamp2().getText());
            Utilisateur.getInstance().setSiteWeb(this.panneauInformationsCompte1.getPanneauSiteWeb().getChamp2().getText());
            Utilisateur.getInstance().setPays(Pays.getInstance().getPays()[0]);
            Utilisateur.getInstance().setCodePostal(Integer.parseInt(this.panneauInformationsCompte1.getPanneauCodePostal().getChamp2().getText()));
            Utilisateur.getInstance().setVille(Ville.getInstance().getVraiNomVille(this.panneauInformationsCompte1.getPanneauVille().getChamp2().getText()));
            Utilisateur.getInstance().setNumeroRue(Integer.parseInt(this.panneauInformationsCompte1.getPanneauNumeroRue().getChamp2().getText()));
            Utilisateur.getInstance().setAdresse(this.panneauInformationsCompte1.getPanneauAdresse().getChamp2().getText());
            Utilisateur.getInstance().setInfoComplementaire(this.panneauInformationsCompte1.getPanneauInfoComp().getChamp2().getText());

            cConnexionMdp = new DialogFormConnexionMdp(this);
            cConnexionMdp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            cConnexionMdp.getPanneauConnexionMdp1().getjButtonValider().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    if (cConnexionMdp.getPanneauConnexionMdp1().getConnexionMdp()) {
                        cConnexionMdp.setVisible(false);
                        cConnexionMdp.dispose();
                        if (Utilisateur.getInstance().updateInformationsUtilisateurBDD()) {
                            Utilisateur.getInstance().chargerInformationsUtilisateur();
                            jLabelEtatMaj.setForeground(Color.blue);
                            jLabelEtatMaj.setText("Mise à jour réussie");
                            setModificationOK(true);
                        } else {
                            jLabelEtatMaj.setForeground(Color.red);
                            jLabelEtatMaj.setText("Echec de la mise à jour");
                            setModificationOK(false);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            cConnexionMdp.setLocationRelativeTo(this);
            cConnexionMdp.setModal(true);
            cConnexionMdp.setVisible(true);
        } else {
            jLabelEtatMaj.setForeground(Color.red);
            jLabelEtatMaj.setText("Champ(s) manquant(s)");
            this.setModificationOK(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonImporterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImporterActionPerformed
        cChoixFichier = new DialogChoixFichier(this);
        cChoixFichier.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cChoixFichier.getPanneauChoixFichier().getjButtonValider().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (cChoixFichier.getPanneauChoixFichier().getChoixFichierOK() && cChoixFichier.getPanneauChoixFichier().getFormatFichier() != null) {
                    cChoixFichier.setVisible(false);
                    cChoixFichier.dispose();

                    String formatFichier = cChoixFichier.getPanneauChoixFichier().getFormatFichier();
                    String cheminFichier = cChoixFichier.getPanneauChoixFichier().getjTextFieldChemin().getText();
                    Boolean resultat = null;
                    List<Map<String, Object>> compte = null;

                    if (formatFichier.equals("json")) {
                        ImportExportJSON objJSON = new ImportExportJSON(cheminFichier, "compte");
                        resultat = objJSON.importerFichier();
                        if (resultat != null && resultat == true) {
                            compte = objJSON.getDonneesImporte_Utilisateur();
                        }
                    } else if (formatFichier.equals("xml")) {
                        ImportExportXML objXML = new ImportExportXML(cheminFichier, "compte");
                        resultat = objXML.importerFichier();
                        if (resultat != null && resultat == true) {
                            compte = objXML.getDonneesImporte_Utilisateur();
                        }
                    } else if (formatFichier.equals("csv")) {
                        ImportExportCSV objCSV = new ImportExportCSV(cheminFichier, "compte");
                        resultat = objCSV.importerFichier();
                        if (resultat != null && resultat == true) {
                            compte = objCSV.getDonneesImporte_Utilisateur();
                        }
                    }

                    if (resultat != null && resultat == true) {
                        if (compte != null && compte.size() == 1) {
                            if (!((String) compte.get(0).get("id")).equals("") && !((String) compte.get(0).get("identifiant")).equals("")) {
                                if (Integer.valueOf((String) compte.get(0).get("id")).equals(Utilisateur.getInstance().getId()) && ((String) compte.get(0).get("identifiant")).equals(Utilisateur.getIdentifiant())) {
                                    updateAllJTextField(compte);
                                    JOptionPane.showMessageDialog(null,
                                            "Importation réussie, veuillez confirmer pour mettre à jour les informations",
                                            "Importation du fichier",
                                            JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    jLabelEtatExportationImportation.setForeground(Color.red);
                                    jLabelEtatExportationImportation.setText("Echec de l'importation, l'id et/ou l'identifiant ne correspondent pas au compte");
                                }
                            } else {
                                jLabelEtatExportationImportation.setForeground(Color.red);
                                jLabelEtatExportationImportation.setText("Echec de l'importation, il manque l'id et/ou l'identifiant");
                            }
                        } else {
                            if (compte != null && compte.size() > 1) {
                                jLabelEtatExportationImportation.setForeground(Color.red);
                                jLabelEtatExportationImportation.setText("Echec de l'importation, vous tentez d'importer plusieurs comptes à la fois");
                            } else {
                                jLabelEtatExportationImportation.setForeground(Color.red);
                                jLabelEtatExportationImportation.setText("Echec de l'importation");
                            }
                        }
                    } else {
                        jLabelEtatExportationImportation.setForeground(Color.red);
                        jLabelEtatExportationImportation.setText("Echec de l'importation, mauvais format du fichier");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        cChoixFichier.setLocationRelativeTo(this);
        cChoixFichier.setModal(true);
        cChoixFichier.setVisible(true);
    }//GEN-LAST:event_jButtonImporterActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.karimandco.photo.ChoisirImage choisirImage1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonImporter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelEtatExportationImportation;
    private javax.swing.JLabel jLabelEtatMaj;
    private javax.swing.JLabel jLabelEtatMdp;
    private javax.swing.JPanel jPanelInfoComp;
    private javax.swing.JPanel jPanelInfoImp;
    private javax.swing.JPanel jPanelMdp;
    private javax.swing.JPanel jPanelPhoto;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.karimandco.auth.PanneauChamp panneauCourriel;
    private com.karimandco.auth.PanneauChamp panneauDateNaissance;
    private com.karimandco.auth.PanneauChamp panneauIdentifiant;
    private com.pradyna.components.PanneauInformationsCompte panneauInformationsCompte1;
    private com.pradyna.components.connexionmdp.PanneauMdp panneauMdp1;
    private com.pradyna.components.connexionmdp.PanneauMdp panneauMdp2;
    private com.karimandco.auth.PanneauChamp panneauNom;
    private com.karimandco.auth.PanneauChamp panneauNumeroTelephone;
    private com.karimandco.auth.PanneauChamp panneauPrenom;
    // End of variables declaration//GEN-END:variables

    /**
     * Cette méthode permet de générer un KeyListener pour les panneaux champ.
     *
     * @param champ PanneauChamp champ
     * @param numeroVerif Integer numéro du champ
     */
    private void KeyListener(PanneauChamp champ, Integer numeroVerif) {
        champ.getChamp2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                updateJLabelEtat(champ, numeroVerif);
            }
        });
    }

    public void updateAllJLabelEtat() {
        updateJLabelEtat(panneauNom, 0);
        updateJLabelEtat(panneauPrenom, 1);
        updateJLabelEtat(panneauIdentifiant, 2);
        updateJLabelEtat(panneauCourriel, 3);
        updateJLabelEtat(panneauNumeroTelephone, 4);
        updateJLabelEtat(panneauDateNaissance, 5);

        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauNumeroTelephoneDeux(), 0);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauSiteWeb(), 1);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauPays(), 2);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauCodePostal(), 3);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauVille(), 4);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauNumeroRue(), 5);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauAdresse(), 6);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauInfoComp(), 7);
    }

    /**
     * Cette méthode permet de mettre à jour les JLabelEtat des champs
     *
     * @param champ PanneauChamp champ
     * @param numeroVerif Integer numéro du champ à vérifier
     */
    public void updateJLabelEtat(PanneauChamp champ, Integer numeroVerif) {
        Boolean resultat = null;
        if (!champ.getChamp2().getText().equals("")) {
            if (numeroVerif == 0) {
                resultat = champ.getChamp2().verifNom();
                setNomOK(resultat);
            } else if (numeroVerif == 1) {
                resultat = champ.getChamp2().verifPrenom();
                setPrenomOK(resultat);
            } else if (numeroVerif == 2) {
                resultat = champ.getChamp2().verifIdentifiant();
                setIdentifiantOK(resultat);
            } else if (numeroVerif == 3) {
                resultat = champ.getChamp2().verifCourriel();
                setCourrielOK(resultat);
            } else if (numeroVerif == 4) {
                resultat = champ.getChamp2().verifNumeroTelephone();
                setNumeroTelephoneOK(resultat);
            } else if (numeroVerif == 5) {
                resultat = champ.getChamp2().verifDateNaissance();
                setDateNaissanceOK(resultat);
            }

            if (resultat == true) {
                champ.setjLabelEtatChamp(Color.blue);
                champ.setjLabelEtatChamp("Format ok");
            } else {
                champ.setjLabelEtatChamp(Color.red);
                champ.setjLabelEtatChamp("Format non ok");
            }
        } else {
            if (numeroVerif == 0) {
                setNomOK(champ.getChamp2().verifNom());
            } else if (numeroVerif == 1) {
                setPrenomOK(champ.getChamp2().verifPrenom());
            } else if (numeroVerif == 2) {
                setIdentifiantOK(champ.getChamp2().verifIdentifiant());
            } else if (numeroVerif == 3) {
                setCourrielOK(champ.getChamp2().verifCourriel());
            } else if (numeroVerif == 4) {
                setNumeroTelephoneOK(champ.getChamp2().verifNumeroTelephone());
            } else if (numeroVerif == 5) {
                setDateNaissanceOK(champ.getChamp2().verifDateNaissance());
            }

            champ.setjLabelEtatChamp(Color.black);
            champ.setjLabelEtatChamp("");
        }
    }

    /**
     * Cette méthode permet de mettre à jour le JLabelEtatInscription pour les
     * champs mdp
     */
    public void updateJLabelEtat() {
        if (!String.valueOf(panneauMdp1.getjPasswordFieldResultat().getPassword()).equals("") && !String.valueOf(panneauMdp2.getjPasswordFieldResultat().getPassword()).equals("")) {
            if (panneauMdp1.getMdpOK() && panneauMdp2.getMdpOK()) {
                if (String.valueOf(panneauMdp1.getjPasswordFieldResultat().getPassword()).equals(String.valueOf(panneauMdp2.getjPasswordFieldResultat().getPassword()))) {
                    setMdpOK(true);
                    setMdpConfOK(true);
                    jLabelEtatMdp.setForeground(Color.blue);
                    jLabelEtatMdp.setText("Correspondance ok");
                } else {
                    setMdpOK(false);
                    setMdpConfOK(false);
                    jLabelEtatMdp.setForeground(Color.red);
                    jLabelEtatMdp.setText("Correspondance non ok");
                }
            } else {
                setMdpOK(false);
                setMdpConfOK(false);
                jLabelEtatMdp.setForeground(Color.red);
                jLabelEtatMdp.setText("Format non ok");
            }
        } else if (String.valueOf(panneauMdp1.getjPasswordFieldResultat().getPassword()).equals("") && !String.valueOf(panneauMdp2.getjPasswordFieldResultat().getPassword()).equals("")) {
            setMdpOK(false);
            setMdpConfOK(false);
            jLabelEtatMdp.setForeground(Color.red);
            jLabelEtatMdp.setText("Mdp 1 manquant");
        } else if (!String.valueOf(panneauMdp1.getjPasswordFieldResultat().getPassword()).equals("") && String.valueOf(panneauMdp2.getjPasswordFieldResultat().getPassword()).equals("")) {
            setMdpOK(false);
            setMdpConfOK(false);
            jLabelEtatMdp.setForeground(Color.red);
            jLabelEtatMdp.setText("Mdp 2 manquant");
        } else {
            setMdpOK(true);
            setMdpConfOK(true);
            jLabelEtatMdp.setForeground(Color.black);
            jLabelEtatMdp.setText("");
        }
    }

    public void updateAllJTextField(List<Map<String, Object>> compte) {
        getPanneauNom().getChamp2().setText((String) compte.get(0).get("nom"));
        getPanneauPrenom().getChamp2().setText((String) compte.get(0).get("prenom"));
        getPanneauDateNaissance().getChamp2().setText((String) compte.get(0).get("dateNaissance"));
        getPanneauInformationsCompte1().getPanneauNumeroRue().getChamp2().setText((String) compte.get(0).get("numeroRue"));
        getPanneauInformationsCompte1().getPanneauAdresse().getChamp2().setText((String) compte.get(0).get("rue"));
        getPanneauInformationsCompte1().getPanneauInfoComp().getChamp2().setText((String) compte.get(0).get("infoComplementaire"));
        getPanneauInformationsCompte1().getPanneauCodePostal().getChamp2().setText((String) compte.get(0).get("codePostal"));
        getPanneauInformationsCompte1().getPanneauVille().getChamp2().setText((String) compte.get(0).get("ville"));
        getPanneauInformationsCompte1().getPanneauPays().getChamp2().setText((String) compte.get(0).get("pays"));
        getPanneauCourriel().getChamp2().setText((String) compte.get(0).get("courriel"));
        getPanneauNumeroTelephone().getChamp2().setText((String) compte.get(0).get("numeroTelephoneUn"));
        getPanneauInformationsCompte1().getPanneauNumeroTelephoneDeux().getChamp2().setText((String) compte.get(0).get("numeroTelephoneDeux"));
        getPanneauInformationsCompte1().getPanneauSiteWeb().getChamp2().setText((String) compte.get(0).get("siteWeb"));

        updateAllJLabelEtat();
    }

}
