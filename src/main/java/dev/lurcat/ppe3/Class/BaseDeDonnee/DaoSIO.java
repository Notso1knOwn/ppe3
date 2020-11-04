/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.lurcat.ppe3.Class.BaseDeDonnee;

/**
 *
 * @author c.nadal
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Classe d'accès aux données contenant des membres statiques afin de manipuler
 * la BDD ON implémente ici le Design Pattern Singleton
 *
 * @author nc
 */
public class DaoSIO {

    /**
     * Membres static (de classe)
     *
     */
    private static String nomServeur = "127.0.0.1";
    private static String port = "3308";
    private static String nomBdd = "ppe3";
    private static String nomUtilisateur = "root";
    private static String motDePasse = "";
//    private static String nomServeur = "10.0.10.137";
//    private static String port = "3306";
//    private static String nomBdd = "ppe3";
//    private static String nomUtilisateur = "root";
//    private static String motDePasse = "root";

    private static String chaineConnexion;

    //propriété non statique
    private Connection connexion;

    private static DaoSIO monDao = null;

    /**
     * Constructeur privé ! pour construire un objet, il faut utiliser la
     * méthode publique statique getDaoSIO
     *
     */
    private DaoSIO() {
        try {
            //Définition de l'emplacement de la BDD
            DaoSIO.chaineConnexion = "jdbc:mysql://" + DaoSIO.nomServeur + ":"+ DaoSIO.port +"/" + DaoSIO.nomBdd;

            //Création de la connexion à la BDD
            this.connexion = (Connection) DriverManager.getConnection(DaoSIO.chaineConnexion, DaoSIO.nomUtilisateur, DaoSIO.motDePasse);

        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Permet d'obtenir l'objet instancié
     * @return un Objet DaoSIO avec connexion active ... pour une certaine durée
     */
    public static DaoSIO getInstance() {

        if (DaoSIO.monDao==null ) {
            DaoSIO.monDao = new DaoSIO();
        }else{
            if(!DaoSIO.monDao.connexionActive()){
            DaoSIO.monDao = new DaoSIO();    
            }
        }
        return DaoSIO.monDao;
    }

    public Boolean connexionActive() {
        Boolean connexionActive = true;
        try {
            if (this.connexion.isClosed()) {
                connexionActive = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connexionActive;
    }
/**
 * 
 * @param sql, comportera un ordre selec
 * @return 
 */
    public ResultSet requeteSelection(String sql){
   
        try {
            Statement requete = DaoSIO.getInstance().connexion.createStatement();
            return requete.executeQuery(sql);
           
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
       
    }
    /**
     * 
     * @param sql, comportera un ordre insert, update, select, alter, etc.
     * @return le nombre de lignes impactées par la requête action
     * 
     */
      public Integer requeteAction(String sql){
   
        try {
            Statement requete=new DaoSIO().connexion.createStatement();
            return requete.executeUpdate(sql);
           
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    } 
      
      public ResultSet requetePrepareSelection(String sql, ArrayList<String> lesValeurs){
          try {
            PreparedStatement requete = new DaoSIO().connexion.prepareStatement(sql);
            int i = 0;
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
            
             for (String value  : lesValeurs) {
                 i++;
                 if (pattern.matcher(value).matches()) {
                     requete.setInt(i, Integer.parseInt(value));
                 }else{
                     requete.setString(i, value);
                 }
             }
            return requete.executeQuery(sql);
           
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
      }
      
     public Integer requetePrepareAction(String sql, ArrayList<String> lesValeurs){
         try {
            PreparedStatement requete = new DaoSIO().connexion.prepareStatement(sql);
            int i = 0;
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
            
             for (String value  : lesValeurs) {
                 i++;
                 if (pattern.matcher(value).matches()) {
                     requete.setInt(i, Integer.parseInt(value));
                 }else{
                     requete.setString(i, value);
                 }
             }
            return requete.executeUpdate();
           
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
     }
     
           public Integer requetePrepareActionKey(String sql, ArrayList<String> lesValeurs){
   
        try {
            PreparedStatement requete = new DaoSIO().connexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            int i = 0;
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
            
             for (String value  : lesValeurs) {
                 i++;
                 if (pattern.matcher(value).matches()) {
                     requete.setInt(i, Integer.parseInt(value));
                 }else{
                     requete.setString(i, value);
                 }
             }
             requete.executeUpdate();
             
             Integer id = 0;
             ResultSet rsId = requete.getGeneratedKeys();
             if(rsId.next()){
                 id = rsId.getInt(1);
             }
            return id;
           
        } catch (SQLException ex) {
            Logger.getLogger(DaoSIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    } 
}
