/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.lurcat.ppe3.Class.BaseDeDonnee;

import Reutilisable.CardProduit;
import dev.lurcat.ppe3.Interface.Catalogue;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author a.masvidal
 */
public class Bdd {

    private Catalogue index;

    public void setIndex(Catalogue index) {
        this.index = index;
    }

    /**
     * Constructeur par défault set les attribut avec valeur de serveur par
     * défault et init connexion
     */
    public Bdd() {
    }

    /**
     * Demande l'Id_Profil d'un personnel possédant les informations passées en
     * paramètre retour l'id ou 0
     *
     * @param pUser
     * @param pPassword
     * @return
     */
    public ArrayList<Integer> authentification(String pUser, String pPassword) {
        ArrayList<Integer> infoPersonnel = new ArrayList<Integer>();
        try {
            String query = "SELECT Id_Personnel,Id_Profil FROM personnel WHERE pseudo = '" + pUser + "' AND mdp = '" + pPassword + "'";
            ResultSet result = DaoSIO.getInstance().requeteSelection(query);
            if (result.next()) {
                infoPersonnel.add(Integer.parseInt(result.getString("Id_Personnel")));
                infoPersonnel.add(Integer.parseInt(result.getString("Id_Profil")));
            } else {
                infoPersonnel.add(0);
                infoPersonnel.add(0);
            }
            return infoPersonnel;
        } catch (SQLException ex) {
            Logger.getLogger(Bdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        infoPersonnel.add(0);
        infoPersonnel.add(0);
        return infoPersonnel;
    }
    
    public Integer InsererCommande(HashMap <Integer, Integer> pPanier, Integer id_Client,Integer id_Personnel){
        ArrayList<String> lesValeurs = new ArrayList<String>();
        lesValeurs.add(String.valueOf(id_Client));
        lesValeurs.add(String.valueOf(id_Personnel));
        String query = "INSERT INTO commande(date_commande,Id_Etat_Commande,Id_Client,Id_Personnel) VALUES(NOW(),1,?,?);";
        Integer idCommande = DaoSIO.getInstance().requetePrepareActionKey(query, lesValeurs);
        if(idCommande != 0){
        query = "INSERT INTO contenir VALUES ";
         int i = 1;
        for (Integer id_Produit : pPanier.keySet()) {
            if(i != pPanier.size()){
                query += "("+idCommande+","+id_Produit+","+pPanier.get(id_Produit)+"),";
            }else{
                query += "("+idCommande+","+id_Produit+","+pPanier.get(id_Produit)+");";
            }
            i++;
        }
//        query += " END";
        
        System.out.println(query);
        
        DaoSIO.getInstance().requeteAction(query);
        }
        return idCommande;
    }

    public ResultSet GetProfil(){
        ResultSet Response = null;
        String query = "SELECT Id_Profil, libelle, description FROM profil ORDER by Id_Profil";
        Response = DaoSIO.getInstance().requeteSelection(query);
        return Response;
    }
    
    public Integer getStockProduit(Integer idProduit) throws SQLException{
        ResultSet rsStockProduit = DaoSIO.getInstance().requeteSelection("SELECT Stock FROM produit WHERE Id_Produit = " + idProduit);
        Integer stockProduit = 0;
        if (rsStockProduit.next()) {
            stockProduit = rsStockProduit.getInt(1);
        }
        return stockProduit;
    }
}
