/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.connexionv2;

import com.karimandco.auth.*;
import com.karimandco.bdd.DaoSIO;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Damien F, Pawel R, Théo M
 */
public class PanneauFormConnexionv2 extends javax.swing.JPanel {

    javax.swing.JDialog panneauPereConnexion = null;

    private Boolean identifiantOK = false;

    private Boolean connexionOK = false;

    public void setIdentifiantOK(Boolean identifiantOK) {
        this.identifiantOK = identifiantOK;
    }

    public JButton getjButtonConnexion() {
        return jButtonConnexion;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setFenParentConnexion(javax.swing.JDialog i) {
        this.panneauPereConnexion = i;
    }

    public Boolean getConnexionOK() {
        return connexionOK;
    }

    public void setConnexionOK(Boolean connexionOK) {
        this.connexionOK = connexionOK;
    }

    public JLabel getjLabelEtatConnexion() {
        return jLabelEtatConnexion;
    }

    public PanneauChamp getPanneauIdentifiant() {
        return panneauIdentifiant;
    }

    /**
     * Ce constructeur permet d'initialiser le nom des labels et de générer les
     * KeyListener pour capturer les actions.
     */
    public PanneauFormConnexionv2() {
        initComponents();

        panneauIdentifiant.setjLabelNomChamp("Identifiant");
        panneauMdp.getjLabelNomChamp().setText("Mot de passe (6 à 12 chiffres)");

        panneauIdentifiant.getChamp2().addKeyListener(new KeyListener() {
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
                updateJLabelEtatChamp();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panneauIdentifiant = new com.karimandco.auth.PanneauChamp();
        jButtonConnexion = new javax.swing.JButton();
        jLabelEtatConnexion = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panneauMdp = new com.pradyna.components.connexionmdp.PanneauMdp();
        jButton1 = new javax.swing.JButton();

        jButtonConnexion.setText("Se connecter");
        jButtonConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConnexionActionPerformed(evt);
            }
        });

        jLabelEtatConnexion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Connexion");

        jButton1.setText("DEBUG");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonConnexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jLabelEtatConnexion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panneauIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panneauMdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(panneauIdentifiant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panneauMdp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConnexion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEtatConnexion, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConnexionActionPerformed
        if (identifiantOK && this.panneauMdp.getMdpOK()) {
            String mdp_sha256 = Cryptage.sha256(Cryptage.sha256(String.valueOf(this.panneauMdp.getjPasswordFieldResultat().getPassword())));
            ResultSet lesResultats = DaoSIO.getInstance().requeteSelection("SELECT * FROM utilisateurs WHERE identifiant='" + this.panneauIdentifiant.getChamp2().getText() + "' AND mot_de_passe='" + mdp_sha256 + "'");
            try {
                if (lesResultats.next()) {
                    jLabelEtatConnexion.setForeground(Color.blue);
                    jLabelEtatConnexion.setText("Connexion réussie");
                    Utilisateur.setIdentifiant(this.panneauIdentifiant.getChamp2().getText());
                    Utilisateur.getInstance().chargerInformationsUtilisateur();
//                    Utilisateur.getInstance().getAll();
                    this.setConnexionOK(true);
                } else {
                    jLabelEtatConnexion.setForeground(Color.red);
                    jLabelEtatConnexion.setText("Identifiant et/ou mot de passe incorrect(s)");
                    this.setConnexionOK(false);
                }
            } catch (SQLException e) {
            }
        } else {
            jLabelEtatConnexion.setForeground(Color.red);
            jLabelEtatConnexion.setText("Champ(s) manquant(s)");
            this.setConnexionOK(false);
        }
    }//GEN-LAST:event_jButtonConnexionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        panneauIdentifiant.getChamp2().setText("Toto");
        panneauMdp.getjPasswordFieldResultat().setText("111111");
        updateJLabelEtatChamp();
        panneauMdp.updateJLabelEtat();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Cette méthode permet de mettre à jour le JLabelEtat du champs identifiant
     */
    public void updateJLabelEtatChamp() {
        if (!panneauIdentifiant.getChamp2().getText().equals("")) {
            if (panneauIdentifiant.getChamp2().verifIdentifiant()) {
                panneauIdentifiant.setjLabelEtatChamp(Color.blue);
                panneauIdentifiant.setjLabelEtatChamp("Format ok");
                setIdentifiantOK(true);
            } else {
                panneauIdentifiant.setjLabelEtatChamp(Color.red);
                panneauIdentifiant.setjLabelEtatChamp("Format non ok");
                setIdentifiantOK(false);
            }
        } else {
            panneauIdentifiant.setjLabelEtatChamp(Color.black);
            panneauIdentifiant.setjLabelEtatChamp("");
            setIdentifiantOK(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonConnexion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelEtatConnexion;
    private com.karimandco.auth.PanneauChamp panneauIdentifiant;
    private com.pradyna.components.connexionmdp.PanneauMdp panneauMdp;
    // End of variables declaration//GEN-END:variables
}
