/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  a.masvidal
 * Created: 22 sept. 2020
 */
USE `ppe3`;

INSERT INTO `Profil` VALUES 
(1,"Administrateur",""),
(2,"Agent","");

INSERT INTO `Personnel`(nom,prenom,pseudo,mdp,telephone,email,Id_Profil) VALUES
("Sardou", "Michel", "MichelRafou", "", "06 00 00 00 00", "michel.rafou@hotmail.com",2),
("aNePasFaire","tropObvious", "root", "root", "06 06 06 06 06","root@hotmail.com",1);

INSERT INTO `Categorie`(Id_Categorie, libelle, descriptif) VALUES
(1,"PC Portable","Mobile, léger, puissant"),
(2,"Tour Gaming","Puissant, durable, Personnalisable"),
(3,"Composants", "Variés, Accessible");

INSERT INTO `Produit`(libelle,tarif,Stock,Note,Id_Categorie) VALUES
("Asus ROG Strix G (G531GT-HN574T)",1099.99,10,8,1),
("test2",40.00,50,6,3),
("test3",50.00,20,8,3),
("test4",25.00,25,9,3),
("test5",400.00,50,6,2),
("test6",1200.00,9,8,2),
("test7",2000.00,5,9,2);