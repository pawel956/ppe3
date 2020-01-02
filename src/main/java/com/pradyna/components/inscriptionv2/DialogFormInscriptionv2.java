/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pradyna.components.inscriptionv2;

import com.pradyna.components.inscriptionv2.PanneauFormInscriptionv2;
import com.pradyna.main.ppe3;

/**
 *
 * @author pawel
 */
public class DialogFormInscriptionv2 extends javax.swing.JDialog {

    com.pradyna.main.ppe3 objAutreFenetre = null;

    /**
     * Creates new form DialogFormInscription
     */
    public DialogFormInscriptionv2() {
        initComponents();
    }

    public DialogFormInscriptionv2(com.pradyna.main.ppe3 fen) {
        this.objAutreFenetre = fen;
        initComponents();
        panneauFormInscriptionv2.setFenParentInscription(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panneauFormInscriptionv2 = new com.pradyna.components.inscriptionv2.PanneauFormInscriptionv2();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panneauFormInscriptionv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panneauFormInscriptionv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public ppe3 getObjAutreFenetre() {
        return objAutreFenetre;
    }

    public void setObjAutreFenetre(ppe3 objAutreFenetre) {
        this.objAutreFenetre = objAutreFenetre;
    }

    public PanneauFormInscriptionv2 getPanneauFormInscriptionv2() {
        return panneauFormInscriptionv2;
    }

    public void setPanneauFormInscriptionv2(PanneauFormInscriptionv2 panneauFormInscriptionv2) {
        this.panneauFormInscriptionv2 = panneauFormInscriptionv2;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.pradyna.components.inscriptionv2.PanneauFormInscriptionv2 panneauFormInscriptionv2;
    // End of variables declaration//GEN-END:variables
}