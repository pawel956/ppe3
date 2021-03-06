/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.main;

import com.karimandco.auth.Utilisateur;
import com.pradyna.components.DialogAdministration;
import com.pradyna.components.Utilitaire;
import com.pradyna.components.choixdossier.DialogChoixDossier;
import com.pradyna.components.connexionv2.DialogFormConnexionv2;
import com.pradyna.components.importexport.ImportExportCSV;
import com.pradyna.components.importexport.ImportExportJSON;
import com.pradyna.components.importexport.ImportExportPDF;
import com.pradyna.components.importexport.ImportExportXML;
import com.pradyna.components.inscriptionv2.DialogFormInscriptionv2;
import com.pradyna.components.modification.DialogFormModification;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author pawel
 */
public class ppe3 extends javax.swing.JFrame {

    public com.pradyna.components.connexionv2.DialogFormConnexionv2 cConnexion;
    public com.pradyna.components.inscriptionv2.DialogFormInscriptionv2 cInscription;
    public com.pradyna.components.modification.DialogFormModification cModification;
    public com.pradyna.components.choixdossier.DialogChoixDossier cChoixDossier;
    public com.pradyna.components.DialogAdministration cAdministration;
    public com.pradyna.main.aPropos cAPropos;

    /**
     * Creates new form ppe3
     */
    public ppe3() {
        initComponents();

        updateInterface();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        creationDuCV1 = new com.karimandco.cv.CreationDuCV();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuCompte = new javax.swing.JMenu();
        jMenuItemConnexion = new javax.swing.JMenuItem();
        jMenuItemInscription = new javax.swing.JMenuItem();
        jMenuItemMonCompte = new javax.swing.JMenuItem();
        jMenuItemDeconnexion = new javax.swing.JMenuItem();
        jMenuExporterDonnees = new javax.swing.JMenu();
        jMenuAdministration = new javax.swing.JMenu();
        jMenuAPropos = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PPE3");
        setResizable(false);

        jMenuCompte.setText("Compte");

        jMenuItemConnexion.setText("Connexion");
        jMenuItemConnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConnexionActionPerformed(evt);
            }
        });
        jMenuCompte.add(jMenuItemConnexion);

        jMenuItemInscription.setText("Inscription");
        jMenuItemInscription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInscriptionActionPerformed(evt);
            }
        });
        jMenuCompte.add(jMenuItemInscription);

        jMenuItemMonCompte.setText("Mon compte");
        jMenuItemMonCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemMonCompteActionPerformed(evt);
            }
        });
        jMenuCompte.add(jMenuItemMonCompte);

        jMenuItemDeconnexion.setText("Déconnexion");
        jMenuItemDeconnexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeconnexionActionPerformed(evt);
            }
        });
        jMenuCompte.add(jMenuItemDeconnexion);

        jMenuBar1.add(jMenuCompte);

        jMenuExporterDonnees.setText("Exporter les données");
        jMenuExporterDonnees.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuExporterDonneesMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuExporterDonnees);

        jMenuAdministration.setText("Administration");
        jMenuAdministration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuAdministrationMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuAdministration);

        jMenuAPropos.setText("A propos");
        jMenuAPropos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuAProposMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuAPropos);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(creationDuCV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(creationDuCV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemInscriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInscriptionActionPerformed
        cInscription = new DialogFormInscriptionv2(this);
        cInscription.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cInscription.getPanneauFormInscriptionv2().getjButton1().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (cInscription.getPanneauFormInscriptionv2().getInscriptionOK()) {
                    cInscription.setVisible(false);
                    cInscription.dispose();
                    Utilisateur.getInstance().chargerInformationsUtilisateur();
                    createDialogFormConnexion();
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
        cInscription.setLocationRelativeTo(this);
        cInscription.setModal(true);
        cInscription.setVisible(true);
    }//GEN-LAST:event_jMenuItemInscriptionActionPerformed

    private void jMenuItemConnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConnexionActionPerformed
        createDialogFormConnexion();
    }//GEN-LAST:event_jMenuItemConnexionActionPerformed

    private void jMenuItemDeconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeconnexionActionPerformed
        Utilisateur.getInstance().setEstConnecte(false);
        updateInterface();
    }//GEN-LAST:event_jMenuItemDeconnexionActionPerformed

    private void jMenuAdministrationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAdministrationMouseClicked
        cAdministration = new DialogAdministration(this);
        cAdministration.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cAdministration.setLocationRelativeTo(this);
        cAdministration.setModal(true);
        cAdministration.setVisible(true);
    }//GEN-LAST:event_jMenuAdministrationMouseClicked

    private void jMenuAProposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuAProposMouseClicked
        cAPropos = new aPropos();
        cAPropos.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cAPropos.setLocationRelativeTo(this);
        cAPropos.setModal(true);
        cAPropos.setVisible(true);
    }//GEN-LAST:event_jMenuAProposMouseClicked

    private void jMenuItemMonCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMonCompteActionPerformed
        cModification = new DialogFormModification(this);
        cModification.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cModification.getPanneauFormModification1().getjButton1().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (cModification.getPanneauFormModification1().getModificationOK()) {
                    cModification.setVisible(false);
                    cModification.dispose();
                    updateInterface();
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
        cModification.setLocationRelativeTo(this);
        cModification.setModal(true);
        cModification.setVisible(true);
    }//GEN-LAST:event_jMenuItemMonCompteActionPerformed

    private void jMenuExporterDonneesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuExporterDonneesMouseClicked
        cChoixDossier = new DialogChoixDossier(this);
        cChoixDossier.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cChoixDossier.getPanneauChoixDossier().getjButtonValider().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

                if (cChoixDossier.getPanneauChoixDossier().getChoixDossierOK() && cChoixDossier.getPanneauChoixDossier().getFormatFichier() != null && cChoixDossier.getPanneauChoixDossier().getDonneesAExporter() != null) {
                    String cheminFichier = cChoixDossier.getPanneauChoixDossier().getjTextFieldChemin().getText() + "\\export_" + Utilisateur.getIdentifiant() + "_" + Utilitaire.getDate();
                    Boolean resultat = null;

                    List<Integer> id_utilisateurs = new ArrayList<>();
                    List<Integer> id_cvs = new ArrayList<>();

                    if (cChoixDossier.getPanneauChoixDossier().getDonneesAExporter().equals("Tout")) {
                        id_utilisateurs.add(Utilisateur.getInstance().getId());
                        id_cvs.add(creationDuCV1.getIdCV());
                    } else if (cChoixDossier.getPanneauChoixDossier().getDonneesAExporter().equals("Compte")) {
                        id_utilisateurs.add(Utilisateur.getInstance().getId());
                    } else if (cChoixDossier.getPanneauChoixDossier().getDonneesAExporter().equals("CV")) {
                        id_cvs.add(creationDuCV1.getIdCV());
                    }

                    String extensionFichier = cChoixDossier.getPanneauChoixDossier().getFormatFichier();

                    if (extensionFichier.equals("JSON")) {
                        cheminFichier += ".json";
                        ImportExportJSON objJSON = new ImportExportJSON(cheminFichier, id_utilisateurs, id_cvs);
                        resultat = objJSON.exporterFichier();
                    } else if (extensionFichier.equals("XML")) {
                        ImportExportXML objXML = new ImportExportXML(cheminFichier, id_utilisateurs, id_cvs);
                        resultat = objXML.exporterFichier();
                    } else if (extensionFichier.equals("CSV")) {
                        ImportExportCSV objCSV = new ImportExportCSV(cheminFichier, id_utilisateurs, id_cvs);
                        resultat = objCSV.exporterFichier();
                    } else if (extensionFichier.equals("PDF")) {
                        cheminFichier += ".pdf";
                        ImportExportPDF objPDF = new ImportExportPDF(cheminFichier, id_utilisateurs, id_cvs);
                        resultat = objPDF.exporterFichier();
                    }

                    if (resultat != null && resultat) {
                        cChoixDossier.setVisible(false);
                        cChoixDossier.dispose();

                        JOptionPane.showMessageDialog(null,
                                "Exportation dans le fichier ou les fichiers " + cheminFichier + " réussie",
                                "Exportation des données",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Echec de l'exportation, veuillez réessayer",
                                "Exportation des données",
                                JOptionPane.WARNING_MESSAGE);
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
        cChoixDossier.setLocationRelativeTo(this);
        cChoixDossier.setModal(true);
        cChoixDossier.setVisible(true);
    }//GEN-LAST:event_jMenuExporterDonneesMouseClicked

    public void updateInterface() {
        if (Utilisateur.getInstance().getEstConnecte()) {
            creationDuCV1.setIdUtilisateur(Utilisateur.getInstance().getId());
            creationDuCV1.updateJComboBoxListeCVs();

            creationDuCV1.setVisible(true);
            jMenuItemMonCompte.setVisible(true);
            jMenuItemDeconnexion.setVisible(true);
            jMenuItemConnexion.setVisible(false);
            jMenuItemInscription.setVisible(false);

            jMenuExporterDonnees.setVisible(true);

            if (Utilisateur.getInstance().getStatut().equals(1)) {
                jMenuAdministration.setVisible(true);
            }
        } else {
            creationDuCV1.setVisible(false);
            jMenuItemMonCompte.setVisible(false);
            jMenuItemDeconnexion.setVisible(false);
            jMenuItemConnexion.setVisible(true);
            jMenuItemInscription.setVisible(true);
            jMenuAdministration.setVisible(false);

            jMenuExporterDonnees.setVisible(false);
        }
    }

    private void createDialogFormConnexion() {
        cConnexion = new DialogFormConnexionv2(this);
        cConnexion.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cConnexion.getPanneauFormConnexion().getjButtonConnexion().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (cConnexion.getPanneauFormConnexion().getConnexionOK()) {
                    cConnexion.setVisible(false);
                    cConnexion.dispose();
                    Utilisateur.getInstance().chargerInformationsUtilisateur();
                    updateInterface();
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
        cConnexion.setLocationRelativeTo(this);
        cConnexion.setModal(true);
        cConnexion.setVisible(true);
    }

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
            java.util.logging.Logger.getLogger(ppe3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ppe3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ppe3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ppe3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ppe3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.karimandco.cv.CreationDuCV creationDuCV1;
    private javax.swing.JMenu jMenuAPropos;
    private javax.swing.JMenu jMenuAdministration;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCompte;
    private javax.swing.JMenu jMenuExporterDonnees;
    private javax.swing.JMenuItem jMenuItemConnexion;
    private javax.swing.JMenuItem jMenuItemDeconnexion;
    private javax.swing.JMenuItem jMenuItemInscription;
    private javax.swing.JMenuItem jMenuItemMonCompte;
    // End of variables declaration//GEN-END:variables
}
