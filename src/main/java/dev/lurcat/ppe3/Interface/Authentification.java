/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.lurcat.ppe3.Interface;

import dev.lurcat.ppe3.Class.BaseDeDonnee.Bdd;
import dev.lurcat.ppe3.Class.BaseDeDonnee.DaoSIO;
import dev.lurcat.ppe3.Class.BaseDeDonnee.Data;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;




/**
 *
 * @author Adrien
 */
public class Authentification extends javax.swing.JFrame {

    private final Bdd data;
    
    /**
     * Creates new form Authentification
     */
    public Authentification() {
        initComponents();
        data = new Bdd();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelUsername = new javax.swing.JLabel();
        jLabelPassword = new javax.swing.JLabel();
        jTextFieldCxn = new javax.swing.JTextField();
        jPasswordFieldCxn = new javax.swing.JPasswordField();
        jButtonAuthentification = new javax.swing.JButton();
        jLabelReponse = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Authentification");

        jLabelUsername.setText("Utilisateur :");

        jLabelPassword.setText("Mot de passe :");

        jTextFieldCxn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldCxn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCxnActionPerformed(evt);
            }
        });

        jPasswordFieldCxn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldCxnActionPerformed(evt);
            }
        });

        jButtonAuthentification.setText("Connexion");
        jButtonAuthentification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAuthentificationMouseClicked(evt);
            }
        });
        jButtonAuthentification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAuthentificationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addGap(8, 8, 8))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jButtonAuthentification)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelReponse, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelUsername)
                            .addComponent(jLabelPassword))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldCxn)
                            .addComponent(jPasswordFieldCxn))))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitle)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsername)
                    .addComponent(jTextFieldCxn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPassword)
                    .addComponent(jPasswordFieldCxn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(jButtonAuthentification)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelReponse, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAuthentificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAuthentificationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAuthentificationActionPerformed

    /**
     * Selectionne le personnel ayant les informations données dnas le cas ou c'est un admin ouvre une page de gestion dans l'autre cas ouvre le catalogue puis ferme la page courante
     * @param evt 
     */
    private void jButtonAuthentificationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAuthentificationMouseClicked
        ArrayList<Integer> infoPersonnel = this.data.authentification(jTextFieldCxn.getText(), jPasswordFieldCxn.getText());
        switch (infoPersonnel.get(1)) {
            case 1: {
                dev.lurcat.ppe3.Interface.Gestion unPageGestion;
                try {
                    unPageGestion = new Gestion();
                    unPageGestion.setVisible(true);
                    this.dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(Authentification.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            case 2: {
                dev.lurcat.ppe3.Interface.Catalogue unIndex = new Catalogue();
                unIndex.setId_Personnel(infoPersonnel.get(0));
                unIndex.setVisible(true);
                this.dispose();
                //                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                break;
            }
            default: {
                jLabelReponse.setText("Cet utilisateur n'est pas existant");
                break;
            }
        }
    }//GEN-LAST:event_jButtonAuthentificationMouseClicked

    private void jPasswordFieldCxnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldCxnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldCxnActionPerformed

    private void jTextFieldCxnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCxnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCxnActionPerformed

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
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Authentification.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Authentification().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAuthentification;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JLabel jLabelReponse;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelUsername;
    private javax.swing.JPasswordField jPasswordFieldCxn;
    private javax.swing.JTextField jTextFieldCxn;
    // End of variables declaration//GEN-END:variables
}
