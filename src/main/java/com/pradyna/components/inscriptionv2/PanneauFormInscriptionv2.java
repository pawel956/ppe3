/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.inscriptionv2;

import com.karimandco.auth.*;
import com.karimandco.bdd.DaoSIO;
import com.pradyna.components.Pays;
import com.pradyna.components.Ville;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Damien F, Pawel R, Théo M
 */
public class PanneauFormInscriptionv2 extends javax.swing.JPanel {

    javax.swing.JDialog panneauPereInscription = null;

    private Boolean nomOK = false;
    private Boolean prenomOK = false;
    private Boolean identifiantOK = false;
    private Boolean courrielOK = false;
    private Boolean numeroTelephoneOK = false;
    private Boolean dateNaissanceOK = false;
    private Boolean mdpOK = false;
    private Boolean mdpConfOK = false;

    private Boolean inscriptionOK = false;

    /**
     * Ce constructeur permet d'initialiser le nom des labels et de générer les
     * KeyListener pour capturer les actions.
     */
    public PanneauFormInscriptionv2() {
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

        panneauConnexionAll1.getPanneauConnexionGoogle1().getjButton1().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String[] resultats = panneauConnexionAll1.getPanneauConnexionGoogle1().getInformationsUtilisateur();
                panneauNom.getChamp2().setText(resultats[0]);
                panneauPrenom.getChamp2().setText(resultats[1]);
                panneauCourriel.getChamp2().setText(resultats[2]);
                updateJLabelEtat(panneauNom, 0);
                updateJLabelEtat(panneauPrenom, 1);
                updateJLabelEtat(panneauCourriel, 3);
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

        panneauConnexionAll1.getPanneauConnexionFacebook1().getjButton1().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                String[] resultats = panneauConnexionAll1.getPanneauConnexionFacebook1().getInformationsUtilisateur();
                panneauNom.getChamp2().setText(resultats[0]);
                panneauPrenom.getChamp2().setText(resultats[1]);
                panneauCourriel.getChamp2().setText(resultats[2]);
                panneauDateNaissance.getChamp2().setText(resultats[3]);
                updateJLabelEtat(panneauNom, 0);
                updateJLabelEtat(panneauPrenom, 1);
                updateJLabelEtat(panneauCourriel, 3);
                updateJLabelEtat(panneauDateNaissance, 5);
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

    public void setFenParentInscription(javax.swing.JDialog i) {
        this.panneauPereInscription = i;
    }

    public Boolean getInscriptionOK() {
        return inscriptionOK;
    }

    public void setInscriptionOK(Boolean inscriptionOK) {
        this.inscriptionOK = inscriptionOK;
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
        jLabelEtatInscription = new javax.swing.JLabel();
        panneauConnexionAll1 = new com.pradyna.components.connexion.all.PanneauConnexionAll();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelInfoImp = new javax.swing.JPanel();
        panneauDateNaissance = new com.karimandco.auth.PanneauChamp();
        panneauNom = new com.karimandco.auth.PanneauChamp();
        panneauPrenom = new com.karimandco.auth.PanneauChamp();
        panneauIdentifiant = new com.karimandco.auth.PanneauChamp();
        panneauCourriel = new com.karimandco.auth.PanneauChamp();
        panneauNumeroTelephone = new com.karimandco.auth.PanneauChamp();
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
        jLabel1.setText("Inscription");

        jButton1.setText("S'inscrire");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabelEtatInscription.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton2.setText("DEBUG");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInfoImpLayout = new javax.swing.GroupLayout(jPanelInfoImp);
        jPanelInfoImp.setLayout(jPanelInfoImpLayout);
        jPanelInfoImpLayout.setHorizontalGroup(
            jPanelInfoImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoImpLayout.createSequentialGroup()
                .addGap(26, 26, 26)
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
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanelInfoImpLayout.setVerticalGroup(
            jPanelInfoImpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoImpLayout.createSequentialGroup()
                .addGap(71, 71, 71)
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
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Info importants", jPanelInfoImp);

        javax.swing.GroupLayout jPanelInfoCompLayout = new javax.swing.GroupLayout(jPanelInfoComp);
        jPanelInfoComp.setLayout(jPanelInfoCompLayout);
        jPanelInfoCompLayout.setHorizontalGroup(
            jPanelInfoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInfoCompLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(panneauInformationsCompte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanelInfoCompLayout.setVerticalGroup(
            jPanelInfoCompLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelInfoCompLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panneauInformationsCompte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Info complémentaires", jPanelInfoComp);

        jLabelEtatMdp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanelMdpLayout = new javax.swing.GroupLayout(jPanelMdp);
        jPanelMdp.setLayout(jPanelMdpLayout);
        jPanelMdpLayout.setHorizontalGroup(
            jPanelMdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMdpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMdpLayout.createSequentialGroup()
                        .addComponent(panneauMdp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panneauMdp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelEtatMdp, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMdpLayout.setVerticalGroup(
            jPanelMdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMdpLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanelMdpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panneauMdp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panneauMdp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEtatMdp, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Mot de passe", jPanelMdp);

        javax.swing.GroupLayout jPanelPhotoLayout = new javax.swing.GroupLayout(jPanelPhoto);
        jPanelPhoto.setLayout(jPanelPhotoLayout);
        jPanelPhotoLayout.setHorizontalGroup(
            jPanelPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPhotoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(choisirImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanelPhotoLayout.setVerticalGroup(
            jPanelPhotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPhotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(choisirImage1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
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
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(panneauConnexionAll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel2)
                                .addGap(68, 68, 68)
                                .addComponent(jButton2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabelEtatInscription, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panneauConnexionAll1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEtatInscription, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateJLabelEtat();
        if (nomOK && prenomOK && identifiantOK && courrielOK && numeroTelephoneOK && dateNaissanceOK && mdpOK && mdpConfOK && panneauInformationsCompte1.getNumeroTelephoneDeuxOK() && panneauInformationsCompte1.getSiteWebOK() && panneauInformationsCompte1.getPaysOK() && panneauInformationsCompte1.getCodePostalOK() && panneauInformationsCompte1.getVilleOK() && panneauInformationsCompte1.getNumeroRueOK() && panneauInformationsCompte1.getAdresseOK() && panneauInformationsCompte1.getInfoCompOK() && choisirImage1.getImageOK()) {
            try {
                String[] date_split = this.panneauDateNaissance.getChamp2().getText().split("/");
                String date_newFormat = date_split[2] + "-" + date_split[1] + "-" + date_split[0];

                String mdp_sha256 = Cryptage.sha256(Cryptage.sha256(String.valueOf(this.panneauMdp1.getjPasswordFieldResultat().getPassword())));

                Integer id_pays = null;
                ResultSet resultat_bdd_pays = DaoSIO.getInstance().requeteSelection("SELECT id FROM pays WHERE nom='" + Pays.getInstance().getPays()[0] + "'");
                if (resultat_bdd_pays.next()) {
                    id_pays = resultat_bdd_pays.getInt("id");
                } else {
                    DaoSIO.getInstance().requeteAction("INSERT INTO pays (nom) VALUES ('" + Pays.getInstance().getPays()[0] + "')");
                    ResultSet resultat_bdd_pays_bis = DaoSIO.getInstance().requeteSelection("SELECT id FROM pays WHERE nom='" + Pays.getInstance().getPays()[0] + "'");
                    if (resultat_bdd_pays_bis.next()) {
                        id_pays = resultat_bdd_pays_bis.getInt("id");
                    }
                }

                Integer id_ville = null;
                ResultSet resultat_bdd_ville = DaoSIO.getInstance().requeteSelection("SELECT id FROM villes WHERE id_pays='" + id_pays + "' AND code_postal='" + Ville.getCode_postal() + "' AND nom='" + Ville.getInstance().getVraiNomVille(this.panneauInformationsCompte1.getPanneauVille().getChamp2().getText()) + "'");
                if (resultat_bdd_ville.next()) {
                    id_ville = resultat_bdd_ville.getInt("id");
                } else {
                    Integer compteur = DaoSIO.getInstance().requeteAction("INSERT INTO villes (id_pays, code_postal, nom) VALUES (" + id_pays + ", " + Ville.getCode_postal() + ", '" + Ville.getInstance().getVraiNomVille(this.panneauInformationsCompte1.getPanneauVille().getChamp2().getText()) + "')");
                    if (compteur > 0) {
                        ResultSet resultat_bdd_ville_bis = DaoSIO.getInstance().requeteSelection("SELECT id FROM villes WHERE id_pays='" + id_pays + "' AND code_postal='" + Ville.getCode_postal() + "' AND nom='" + Ville.getInstance().getVraiNomVille(this.panneauInformationsCompte1.getPanneauVille().getChamp2().getText()) + "'");
                        if (resultat_bdd_ville_bis.next()) {
                            id_ville = resultat_bdd_ville_bis.getInt("id");
                        }
                    }
                }

                if (choisirImage1.encodeToBlob() != null) {
                    PreparedStatement maRequete = DaoSIO.getInstance().getConnexion().prepareStatement("INSERT INTO utilisateurs (nom, prenom, identifiant, courriel, num_telephone, date_de_naissance, mot_de_passe, photo, num_telephone_deux, site_web, id_ville, num_rue, adresse, info_complementaire) VALUES ('" + this.panneauNom.getChamp2().getText() + "', '" + this.panneauPrenom.getChamp2().getText() + "', '" + this.panneauIdentifiant.getChamp2().getText() + "', '" + this.panneauCourriel.getChamp2().getText() + "', '" + this.panneauNumeroTelephone.getChamp2().getText() + "', '" + date_newFormat + "', '" + mdp_sha256 + "', ?, '" + this.panneauInformationsCompte1.getPanneauNumeroTelephoneDeux().getChamp2().getText() + "', '" + this.panneauInformationsCompte1.getPanneauSiteWeb().getChamp2().getText() + "', '" + id_ville + "', '" + this.panneauInformationsCompte1.getPanneauNumeroRue().getChamp2().getText() + "', '" + this.panneauInformationsCompte1.getPanneauAdresse().getChamp2().getText() + "', '" + this.panneauInformationsCompte1.getPanneauInfoComp().getChamp2().getText() + "')");
                    maRequete.setBinaryStream(1, choisirImage1.encodeToBlob());
                    if (maRequete.executeUpdate() > 0) {
                        jLabelEtatInscription.setForeground(Color.blue);
                        jLabelEtatInscription.setText("Inscription réussi");
                        this.setInscriptionOK(true);
                    } else {
                        jLabelEtatInscription.setForeground(Color.red);
                        jLabelEtatInscription.setText("Echec de l'inscription");
                        this.setInscriptionOK(false);
                    }
                } else {
                    jLabelEtatInscription.setForeground(Color.red);
                    jLabelEtatInscription.setText("Echec de l'inscription");
                    this.setInscriptionOK(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PanneauFormInscriptionv2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            jLabelEtatInscription.setForeground(Color.red);
            jLabelEtatInscription.setText("Champ(s) manquant(s)");
            this.setInscriptionOK(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        panneauNom.getChamp2().setText("Toto");
        panneauPrenom.getChamp2().setText("Toto");
        panneauIdentifiant.getChamp2().setText("Toto");
        panneauCourriel.getChamp2().setText("toto@gmail.com");
        panneauNumeroTelephone.getChamp2().setText("0101010101");
        panneauDateNaissance.getChamp2().setText("01/01/2000");

        updateJLabelEtat(panneauNom, 0);
        updateJLabelEtat(panneauPrenom, 1);
        updateJLabelEtat(panneauIdentifiant, 2);
        updateJLabelEtat(panneauCourriel, 3);
        updateJLabelEtat(panneauNumeroTelephone, 4);
        updateJLabelEtat(panneauDateNaissance, 5);

        panneauInformationsCompte1.getPanneauNumeroTelephoneDeux().getChamp2().setText("0202020202");
        panneauInformationsCompte1.getPanneauSiteWeb().getChamp2().setText("https://www.sio-lurcat.fr");
        panneauInformationsCompte1.getPanneauPays().getChamp2().setText("France");
        panneauInformationsCompte1.getPanneauCodePostal().getChamp2().setText("66000");
        panneauInformationsCompte1.getPanneauNumeroRue().getChamp2().setText("13");
        panneauInformationsCompte1.getPanneauAdresse().getChamp2().setText("rue des champs");
        panneauInformationsCompte1.getPanneauInfoComp().getChamp2().setText("woow");

        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauNumeroTelephoneDeux(), 0);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauSiteWeb(), 1);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauPays(), 2);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauCodePostal(), 3);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauNumeroRue(), 5);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauAdresse(), 6);
        panneauInformationsCompte1.updateJLabelEtat(panneauInformationsCompte1.getPanneauInfoComp(), 7);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.karimandco.photo.ChoisirImage choisirImage1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelEtatInscription;
    private javax.swing.JLabel jLabelEtatMdp;
    private javax.swing.JPanel jPanelInfoComp;
    private javax.swing.JPanel jPanelInfoImp;
    private javax.swing.JPanel jPanelMdp;
    private javax.swing.JPanel jPanelPhoto;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.pradyna.components.connexion.all.PanneauConnexionAll panneauConnexionAll1;
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
            setMdpOK(false);
            setMdpConfOK(false);
            jLabelEtatMdp.setForeground(Color.black);
            jLabelEtatMdp.setText("");
        }
    }

}
