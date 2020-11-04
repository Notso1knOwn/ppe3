/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.lurcat.ppe3.Class.BaseDeDonnee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author a.masvidal
 */
public class Data {
    
    /**
     * Demande l'Id_Profil d'un personnel possédant les informations passées en paramètre
     * retourne l'id ou 0
     * @param pUser
     * @param pPassword
     * @return 
     */
    public int authentification(String pUser, String pPassword) {
        
        if (DaoSIO.getInstance().connexionActive()) {
            try {
                String query = "SELECT Id_Profil FROM Personnel WHERE pseudo = '" + pUser + "' AND mdp = '" + pPassword + "'";
                ResultSet result = DaoSIO.getInstance().requeteSelection(query);

                if (result.next()) {
                    return Integer.parseInt(result.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Bdd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }
    
    
    /**
     * Retourne les Profils
     * @return 
     */
    
    
}
