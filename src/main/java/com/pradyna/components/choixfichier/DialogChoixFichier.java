/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.choixfichier;

import javax.swing.JPanel;

/**
 *
 * @author pawel
 */
public class DialogChoixFichier extends javax.swing.JDialog {

    javax.swing.JPanel objAutreFenetre = null;

    /**
     * Creates new form DialogChoixFichierDossier
     */
    public DialogChoixFichier() {
        initComponents();
    }

    public DialogChoixFichier(javax.swing.JPanel fen) {
        this.objAutreFenetre = fen;
        initComponents();
        panneauChoixFichier.setPanneauPereChoixFichier(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panneauChoixFichier = new com.pradyna.components.choixfichier.PanneauChoixFichier();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Choix fichier");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panneauChoixFichier, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panneauChoixFichier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public PanneauChoixFichier getPanneauChoixFichier() {
        return panneauChoixFichier;
    }

    public void setPanneauChoixFichier(PanneauChoixFichier panneauChoixFichier) {
        this.panneauChoixFichier = panneauChoixFichier;
    }

    public JPanel getObjAutreFenetre() {
        return objAutreFenetre;
    }

    public void setObjAutreFenetre(JPanel objAutreFenetre) {
        this.objAutreFenetre = objAutreFenetre;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.pradyna.components.choixfichier.PanneauChoixFichier panneauChoixFichier;
    // End of variables declaration//GEN-END:variables
}
