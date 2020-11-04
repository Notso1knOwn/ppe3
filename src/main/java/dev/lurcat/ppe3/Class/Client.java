package dev.lurcat.ppe3.Class;

import java.util.ArrayList;

public class Client {
    private Integer id;
    private String prenom, nom, email, telephone;

    public Client(){
        id = 0;
        prenom = "";
        nom = "";
        email = "";
        telephone = "";
    }

    /**
     * Constructeur surchargé valorisant tous les attributs de la classe
     * @param pInfoClient ArrayList<String>
     */
    public Client(ArrayList<String> pInfoClient){
        id = Integer.parseInt(pInfoClient.get(0));
        prenom = pInfoClient.get(1);
        nom = pInfoClient.get(2);
        email = pInfoClient.get(3);
        telephone = pInfoClient.get(4);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Return toutes les infos clients en une chaîne de charactère
     * @return
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

}
