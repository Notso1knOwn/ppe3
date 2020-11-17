/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.lurcat.ppe3.Interface;



import Reutilisable.gestionPanel;
import dev.lurcat.ppe3.Class.BaseDeDonnee.DaoSIO;
import java.awt.Component;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adrien
 */
public final class Gestion extends javax.swing.JFrame {

    private Integer id_personnel = 0;
    private ArrayList<String> lesCategories;
    private ArrayList<ArrayList<String>> lesRequêtes;
    
    /**
     * Creates new form Gestion
     *
     * @throws java.sql.SQLException
     */
    public Gestion() throws SQLException {
        lesCategories = new ArrayList<String>();
        lesRequêtes = new ArrayList<ArrayList<String>>();
        InitAttributLesCategories();
        InitAttributLesRequêtes();
        ImageIcon img = new ImageIcon("F:\\DEV\\2TAK Hardware\\www\\Icones\\icons8-processeur-64.png");
        this.setIconImage(img.getImage());
        
        initComponents();
        
        InitTableCommande();
        InitTableStatStock();
        gestionPanelCommande.setVisible(false);
    }

    public Gestion(Integer pId_Personnel)throws SQLException{

        id_personnel = pId_Personnel;
        lesCategories = new ArrayList<String>();
        lesRequêtes = new ArrayList<ArrayList<String>>();
        InitAttributLesCategories();
        InitAttributLesRequêtes();
        
        ImageIcon img = new ImageIcon("F:\\DEV\\2TAK Hardware\\www\\Icones\\icons8-processeur-64.png");
        this.setIconImage(img.getImage());
        initComponents();
        
        InitTableCommande();
        InitTableStatStock();
        gestionPanelCommande.setVisible(false);
    }
    

    public void InitAttributLesCategories(){
        lesCategories.add("Profil");
        lesCategories.add("Personnel");
        lesCategories.add("Produit");
        lesCategories.add("Client");
    }
    
    public void InitAttributLesRequêtes(){
        ArrayList<String> lesRequêtesProfils = new ArrayList<>();
        lesRequêtesProfils.add("SELECT * FROM Profil ORDER BY Id_Profil");
        lesRequêtesProfils.add("INSERT INTO Profil VALUES (null,?,?)");
        lesRequêtesProfils.add("UPDATE Profil SET libelle = ? , descriptif = ? WHERE Id_Profil = ?");
        lesRequêtesProfils.add("DELETE FROM Profil WHERE Id_Profil = ?");
        
        ArrayList<String> lesRequêtesPersonnels = new ArrayList<>();
        lesRequêtesPersonnels.add("SELECT * FROM Personnel WHERE Id_Profil != 1 ORDER BY Id_Personnel");
        lesRequêtesPersonnels.add("INSERT INTO Personnel VALUES (null,?,?,?,?,?,?,?)");
        lesRequêtesPersonnels.add("UPDATE Personnel SET prenom = ? , nom = ? , pseudo = ? , mdp = ? , telephone = ? , email = ?, Id_Profil = ? WHERE Id_Personnel = ?");
        lesRequêtesPersonnels.add("DELETE FROM Personnel WHERE Id_Personnel = ? AND Id_Profil != 1");
        
        ArrayList<String> lesRequêtesProduits = new ArrayList<>();
        lesRequêtesProduits.add("SELECT * FROM Produit ORDER BY Id_Produit");
        lesRequêtesProduits.add("INSERT INTO Personnel VALUES (null,?,?,?,?,?,?,?)");
        lesRequêtesProduits.add("UPDATE Personnel SET libelle = ? , description = ? , tarif = ? , stock = ? , note = ? , lien_image = ?, Id_Categorie = ? WHERE Id_Produit = ?");
        lesRequêtesProduits.add("DELETE FROM Produit WHERE Id_Produit = ?");
        
        ArrayList<String> lesRequêtesClients = new ArrayList<>();
        lesRequêtesClients.add("SELECT * FROM Client ORDER BY Id_Client");
        lesRequêtesClients.add("INSERT INTO Client VALUES (null,?,?,?,?)");
        lesRequêtesClients.add("UPDATE Client SET prenom = ? , nom = ? , email = ? , telephone = ? WHERE Id_Client = ?");
        lesRequêtesClients.add("DELETE FROM Client WHERE Id_Client = ?");
        
        
        this.lesRequêtes.add(lesRequêtesProfils);
        this.lesRequêtes.add(lesRequêtesPersonnels);
        this.lesRequêtes.add(lesRequêtesProduits);
        this.lesRequêtes.add(lesRequêtesClients);
    }
    
    
    /**
     * Initialise le tableau de la section vente avec l'ensemble des commande dans la BDD
     * @throws SQLException 
     */
    public void InitTableCommande() throws SQLException{
        DefaultTableModel model = (DefaultTableModel) jTableCommande.getModel();
        
            ResultSet lesCommandes = DaoSIO.getInstance().requeteSelection("SELECT Id_Commande,date_commande,Client.nom,Client.prenom,Personnel.nom,Personnel.prenom FROM Commande, Client, Personnel WHERE Commande.Id_Client = Client.Id_Client AND Commande.Id_Personnel = Personnel.Id_Personnel");

            while (lesCommandes.next()) {
                model.addRow(new Object[]{lesCommandes.getInt("Id_Commande"), lesCommandes.getString("date_commande"), lesCommandes.getString("client.nom")+" "+lesCommandes.getString("client.prenom"),lesCommandes.getString("personnel.nom")+" "+lesCommandes.getString("personnel.prenom")});
            }
    }
    
    public void InitTableStatStock() throws SQLException{
         DefaultTableModel model = (DefaultTableModel) jTableStatStock.getModel();
        
            ResultSet lesProduits = DaoSIO.getInstance().requeteSelection("SELECT Id_Produit,libelle,tarif,stock FROM Produit WHERE stock <= 10 ORDER BY stock ASC");

            while (lesProduits.next()) {
                model.addRow(new Object[]{lesProduits.getInt("Id_Produit"), lesProduits.getString("libelle"), lesProduits.getInt("tarif"),lesProduits.getInt("stock")});
            }
         
         ResultSet chiffreAffaire  = DaoSIO.getInstance().requeteSelection("SELECT SUM(Produit.tarif*Contenir.quantite) AS chiffreAffaire FROM Contenir, Produit");
         if(chiffreAffaire.next()){
             jLabelChiffreAffaire.setText("Chiffre d'affaire en cours : "+chiffreAffaire.getInt(1)+"€");
         }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelProfil = new javax.swing.JPanel();
        try {
            gestionPanelProfil = new Reutilisable.gestionPanel(lesCategories.get(0), lesRequêtes.get(0));
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jPanelAgent = new javax.swing.JPanel();
        try {
            gestionPanelAgent = new Reutilisable.gestionPanel(lesCategories.get(1), lesRequêtes.get(1));
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jPanelCatalogue = new javax.swing.JPanel();
        try {
            gestionPanelProduit = new Reutilisable.gestionPanel(lesCategories.get(2), lesRequêtes.get(2));
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jPanelClient = new javax.swing.JPanel();
        try {
            gestionPanelClient = new Reutilisable.gestionPanel(lesCategories.get(3), lesRequêtes.get(3));
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jPanelVente = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCommande = new javax.swing.JTable();
        try {
            gestionPanelCommande = new Reutilisable.gestionPanel();
        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
        }
        jPanelStat = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableStatStock = new javax.swing.JTable();
        jLabelChiffreAffaire = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelProfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelProfilMouseClicked(evt);
            }
        });
        jPanelProfil.add(gestionPanelProfil);

        jTabbedPane1.addTab("Profil", jPanelProfil);

        jPanelAgent.add(gestionPanelAgent);

        jTabbedPane1.addTab("Agent", jPanelAgent);

        jPanelCatalogue.add(gestionPanelProduit);

        jTabbedPane1.addTab("Catalogue", jPanelCatalogue);

        jPanelClient.add(gestionPanelClient);

        jTabbedPane1.addTab("Client", jPanelClient);

        jTableCommande.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Commande", "Date", "Client", "Personnel"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCommande.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableCommande.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableCommande.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCommandeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCommande);

        javax.swing.GroupLayout jPanelVenteLayout = new javax.swing.GroupLayout(jPanelVente);
        jPanelVente.setLayout(jPanelVenteLayout);
        jPanelVenteLayout.setHorizontalGroup(
            jPanelVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVenteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelVenteLayout.createSequentialGroup()
                        .addComponent(gestionPanelCommande, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelVenteLayout.setVerticalGroup(
            jPanelVenteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelVenteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(gestionPanelCommande, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vente", jPanelVente);

        jTableStatStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Produit", "Libelle", "Prix", "Quantite"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableStatStock.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableStatStock.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTableStatStock);

        jLabelChiffreAffaire.setText("jLabel1");

        javax.swing.GroupLayout jPanelStatLayout = new javax.swing.GroupLayout(jPanelStat);
        jPanelStat.setLayout(jPanelStatLayout);
        jPanelStatLayout.setHorizontalGroup(
            jPanelStatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelStatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(jPanelStatLayout.createSequentialGroup()
                        .addComponent(jLabelChiffreAffaire)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelStatLayout.setVerticalGroup(
            jPanelStatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelStatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelChiffreAffaire)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Stat", jPanelStat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelProfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelProfilMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelProfilMouseClicked

    /**
     * Actualise les reqêtes du gestion panel avec les informations de la jTableCommande
     * @param evt 
     */
    private void jTableCommandeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCommandeMouseClicked
        if(jTableCommande.getSelectedRow() != -1){
            try {
                ArrayList<String> lesRequêtesCommandes = new ArrayList<>();
                lesRequêtesCommandes.add("SELECT * FROM contenir WHERE Id_Commande = " + jTableCommande.getValueAt(jTableCommande.getSelectedRow(), 0));
                lesRequêtesCommandes.add("INSERT INTO contenir VALUES("+ jTableCommande.getValueAt(jTableCommande.getSelectedRow(), 0)+",?,?)");
                lesRequêtesCommandes.add("UPDATE contenir SET quantite = ? WHERE Id_Commande = ? AND Id_Produit = "+ jTableCommande.getValueAt(jTableCommande.getSelectedRow(), 1));
                lesRequêtesCommandes.add("DELETE FROM contenir WHERE Id_Commande = ? AND Id_Produit = "+ jTableCommande.getValueAt(jTableCommande.getSelectedRow(), 1));
                
                gestionPanelCommande.setNomCategorie("Produit Commande");
                gestionPanelCommande.setLesRequêtes(lesRequêtesCommandes);
                gestionPanelCommande.InitTable();
                gestionPanelCommande.InitPanelModif();
                gestionPanelCommande.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Vueillez selectionner un produit.");
        }
    }//GEN-LAST:event_jTableCommandeMouseClicked

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
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gestion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Gestion().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Reutilisable.gestionPanel gestionPanelAgent;
    private Reutilisable.gestionPanel gestionPanelClient;
    private Reutilisable.gestionPanel gestionPanelCommande;
    private Reutilisable.gestionPanel gestionPanelProduit;
    private Reutilisable.gestionPanel gestionPanelProfil;
    private javax.swing.JLabel jLabelChiffreAffaire;
    private javax.swing.JPanel jPanelAgent;
    private javax.swing.JPanel jPanelCatalogue;
    private javax.swing.JPanel jPanelClient;
    private javax.swing.JPanel jPanelProfil;
    private javax.swing.JPanel jPanelStat;
    private javax.swing.JPanel jPanelVente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCommande;
    private javax.swing.JTable jTableStatStock;
    // End of variables declaration//GEN-END:variables
}
