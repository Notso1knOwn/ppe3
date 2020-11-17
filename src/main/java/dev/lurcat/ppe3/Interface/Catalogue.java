/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.lurcat.ppe3.Interface;



import Reutilisable.CardProduit;
import dev.lurcat.ppe3.Class.BaseDeDonnee.Bdd;
import dev.lurcat.ppe3.Class.BaseDeDonnee.DaoSIO;
import dev.lurcat.ppe3.Class.Client;

import java.awt.Component;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Adrien
 */
public class Catalogue extends javax.swing.JFrame {

//    private final Bdd bdd;
    private HashMap <Integer, Integer> panier;
    private Client leClient = null;
    private Integer Id_Personnel;

    /**
     * Creates new form Index
     */
    public Catalogue() {
        ImageIcon img = new ImageIcon("F:\\DEV\\2TAK Hardware\\www\\Icones\\icons8-processeur-64.png");
        this.setIconImage(img.getImage());
        initComponents();
//        bdd = new Bdd();
//        bdd.setIndex(this);
//        bdd.InitTabbedPaneCategorie(jTabbedPaneCategorie);
//        bdd.InitTabbedPaneProduit(jTabbedPaneCategorie);

        InitTabbedPaneCategorie();
        InitTabbedPaneProduit();
        panier = new HashMap<Integer, Integer>();
    }

    public HashMap<Integer, Integer> getPanier() {
        return panier;
    }

    public Client getLeClient() {
        return leClient;
    }

    public void setLeClient(Client leClient) {
        this.leClient = leClient;
    }

    public Integer getId_Personnel() {
        return Id_Personnel;
    }

    public void setId_Personnel(Integer Id_Personnel) {
        this.Id_Personnel = Id_Personnel;
    }
    
    public void setLabelClient(String nomClient, String prenomClient){
        jLabelClient.setText(nomClient+" "+prenomClient);
    }
    
   /**
    * Initialise les Tab du TabbedPanel en fonction des catégories dans la bdd
    * 
    */
    public void InitTabbedPaneCategorie() {
            try {
                String query = "SELECT Id_Categorie, libelle FROM categorie ORDER by Id_Categorie";
                ResultSet lesCategories = DaoSIO.getInstance().requeteSelection(query);

                while (lesCategories.next()) {
                    JPanel tempPanel = new JPanel();
                    tempPanel.setName(lesCategories.getString(1));
                    jTabbedPaneCategorie.addTab(lesCategories.getString(2), tempPanel);
                }

                lesCategories.close();
            } catch (SQLException ex) {
                Logger.getLogger(Bdd.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    /**
     * Initialise les produits dans les jPanels de catégorie avec des card (JPanel).
     * 
     */
    public void InitTabbedPaneProduit() {
        
            try {
                String query = "SELECT *";
                query += " FROM produit";
                query += " ORDER BY produit.Id_Categorie";
                ResultSet lesProduits = DaoSIO.getInstance().requeteSelection(query);
                

                while (lesProduits.next()) {
                    for (Component unComponent : jTabbedPaneCategorie.getComponents()) {
                        if (unComponent instanceof JPanel) {
                            if (lesProduits.getString("Id_Categorie").equals(unComponent.getName())) {
                                
                                ArrayList<String> unProduit = new ArrayList<>();
                                
                                for (int i = 1; i < 9; i++) {
                                    unProduit.add(lesProduits.getString(i));
                                }
                                
                                CardProduit uneCard = new CardProduit();
                                uneCard.setteur(unProduit, this);
                                uneCard.remplissage();
                                
                                ((JPanel) unComponent).add(uneCard);
                            }
                        }
                    }
                }

                lesProduits.close();
            } catch (SQLException ex) {
                Logger.getLogger(Bdd.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    /**
     * Ajoute un produit au Panier en fonction du stock et affiche un Message dialogue dans la fenêtre qui a appelé la fonction
     * @param form
     * @param idProduit 
     * @throws java.sql.SQLException 
     */
    public void addProduitPanier(Component form, int idProduit, int stock) throws SQLException{
        if (panier.get(idProduit) != null) {
            if(stock > panier.get(idProduit)){
                panier.put(idProduit, panier.get(idProduit)+1);
                JOptionPane.showMessageDialog(form, "Ajout effectué");
            }else{
                JOptionPane.showMessageDialog(form, "Il n'y a plus de stock");
            }
        } else {
            panier.put(idProduit, 1);
        }
        
    }
    
    /**
     * décrémente ou supprime le produit du panier et affiche un Message dialogue dans la fenêtre qui a appelé la fonction
     * @param form
     * @param idProduit
     * @throws SQLException 
     */
    public void decrementerProduitPanier(Component form, int idProduit) throws SQLException{
        
            if(panier.get(idProduit) != 1){
                panier.put(idProduit, panier.get(idProduit)-1);
                JOptionPane.showMessageDialog(form, "Produit décrémenté");
            }else if(panier.get(idProduit) == 1){
                panier.remove(idProduit);
                JOptionPane.showMessageDialog(form, "Produit supprimé");
            }
        
    }
    
    /**
     * Supprime un produit du panier et affiche un Message dialogue dans la fenêtre qui a appelé la fonction
     * @param form
     * @param idProduit
     * @throws SQLException 
     */
    public void supprimerProduitPanier(Component form, int idProduit)throws SQLException{
        
        panier.remove(idProduit);
        JOptionPane.showMessageDialog(form, "Produit supprimé");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLibelle = new javax.swing.JLabel();
        jTabbedPaneCategorie = new javax.swing.JTabbedPane();
        jButtonPanier = new javax.swing.JButton();
        jButtonClient = new javax.swing.JButton();
        jLabelClient = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" 2TAK HARDWARE | Produits");

        jLabelLibelle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelLibelle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLibelle.setText("Produits");

        jButtonPanier.setText("Panier");
        jButtonPanier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonPanierMouseClicked(evt);
            }
        });

        jButtonClient.setText("Client");
        jButtonClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonClientMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPaneCategorie, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addComponent(jLabelLibelle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelClient, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPanier)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelClient, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonPanier)
                        .addComponent(jButtonClient)))
                .addGap(24, 24, 24)
                .addComponent(jLabelLibelle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPaneCategorie, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPanierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonPanierMouseClicked
        try {
            Panier unPanier = new Panier(this);
            unPanier.setVisible(true);
            unPanier.setLocationRelativeTo(this);
        } catch (SQLException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPanierMouseClicked

    private void jButtonClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonClientMouseClicked
        ClientForm unClientForm = new ClientForm(this);
        unClientForm.setVisible(true);
        unClientForm.setLocationRelativeTo(this);

    }//GEN-LAST:event_jButtonClientMouseClicked

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
            java.util.logging.Logger.getLogger(Catalogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Catalogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Catalogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Catalogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Catalogue().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClient;
    private javax.swing.JButton jButtonPanier;
    private javax.swing.JLabel jLabelClient;
    private javax.swing.JLabel jLabelLibelle;
    private javax.swing.JTabbedPane jTabbedPaneCategorie;
    // End of variables declaration//GEN-END:variables
}
