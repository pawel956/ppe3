/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.connexion.facebook;

import javax.swing.JButton;
import javax.swing.JOptionPane;

// constants
import com.pradyna.constants.Constants;

/**
 *
 * @author p.radyna
 */
public class PanneauConnexionFacebook extends javax.swing.JPanel {

    private String[] informationsUtilisateur;

    /**
     * Creates new form PanneauConnexionFacebook
     */
    public PanneauConnexionFacebook() {
        initComponents();
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public String[] getInformationsUtilisateur() {
        return informationsUtilisateur;
    }

    public void setInformationsUtilisateur(String[] informationsUtilisateur) {
        this.informationsUtilisateur = informationsUtilisateur;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/facebook.png"))); // NOI18N
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
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (Constants.SIGN_UP_VIA_FACEBOOK_IS_ENABLED) {
            FacebookOAuth2 facebook = new FacebookOAuth2();
            this.setInformationsUtilisateur(facebook.getInformationsUtilisateur());
        } else {
            JOptionPane.showMessageDialog(null,
                    "La connexion via Facebook a été désactivée",
                    "Connexion via Facebook",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
