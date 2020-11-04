/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Adrien
 * Created: 19 sept. 2020
 */


USE `ppe3`;

INSERT INTO `profil` VALUES 
(1,"Administrateur","gère: profils, agents, produits et catégories,clients, ventes, stats,factures et jeu d'essai"),
(2,"Agent","affichage des info bdd concernant produits, créer/mdofier/afficher clients, effectuer ventes bon de commande et factures");

INSERT INTO `personnel`(nom,prenom,pseudo,mdp,telephone,email,Id_Profil) VALUES 
("Sardou","Michel","MichelRafou","InLove2Sardou","06 47 60 19 14","fan2@Sardou.fr",2),
("aNePasFaire","tropObvious","root","toor","06 00 00 00 00","root@root.com",1);

INSERT INTO ``