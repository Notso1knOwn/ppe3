-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  ven. 13 nov. 2020 à 15:03
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ppe3`
--
CREATE DATABASE IF NOT EXISTS `ppe3` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `ppe3`;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `Id_Categorie` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) DEFAULT NULL,
  `descriptif` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id_Categorie`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `Id_Client` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id_Client`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `Id_Commande` int(11) NOT NULL AUTO_INCREMENT,
  `date_commande` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Id_Etat_Commande` int(11) NOT NULL,
  `Id_Client` int(11) NOT NULL,
  `Id_Personnel` int(11) NOT NULL,
  PRIMARY KEY (`Id_Commande`),
  KEY `Id_Etat_Commande` (`Id_Etat_Commande`),
  KEY `Id_Client` (`Id_Client`),
  KEY `Id_Personnel` (`Id_Personnel`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Structure de la table `contenir`
--

DROP TABLE IF EXISTS `contenir`;
CREATE TABLE IF NOT EXISTS `contenir` (
  `Id_Commande` int(11) NOT NULL,
  `Id_Produit` int(11) NOT NULL,
  `quantite` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_Produit`,`Id_Commande`),
  KEY `Id_Commande` (`Id_Commande`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Structure de la table `etat_commande`
--

DROP TABLE IF EXISTS `etat_commande`;
CREATE TABLE IF NOT EXISTS `etat_commande` (
  `Id_Etat_Commande` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) DEFAULT NULL,
  `descriptif` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id_Etat_Commande`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

DROP TABLE IF EXISTS `personnel`;
CREATE TABLE IF NOT EXISTS `personnel` (
  `Id_Personnel` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `pseudo` varchar(50) DEFAULT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `Id_Profil` int(11) NOT NULL,
  PRIMARY KEY (`Id_Personnel`),
  KEY `Id_Profil` (`Id_Profil`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `personnel`
--

INSERT INTO `personnel` (`Id_Personnel`, `prenom`, `nom`, `pseudo`, `mdp`, `telephone`, `email`, `Id_Profil`) VALUES
(1, 'user', 'user', 'user', 'user', '06 00 00 00 00', 'user@hotmail.com', 2),
(2, '', '', 'root', 'root', '06 06 06 06 06', 'root@hotmail.com', 1);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `Id_Produit` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) DEFAULT NULL,
  `description` text NOT NULL,
  `tarif` decimal(19,4) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `note` decimal(15,2) DEFAULT NULL,
  `lien_image` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `Id_Categorie` int(11) NOT NULL,
  PRIMARY KEY (`Id_Produit`),
  KEY `Id_Categorie` (`Id_Categorie`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

DROP TABLE IF EXISTS `profil`;
CREATE TABLE IF NOT EXISTS `profil` (
  `Id_Profil` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) DEFAULT NULL,
  `descriptif` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id_Profil`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `profil`
--

INSERT INTO `profil` (`Id_Profil`, `libelle`, `descriptif`) VALUES
(1, 'Administrateur', ''),
(2, 'Agent', '');

-- --------------------------------------------------------

--
-- Structure de la table `ref_produit`
--

DROP TABLE IF EXISTS `ref_produit`;
CREATE TABLE IF NOT EXISTS `ref_produit` (
  `Id_Ref_Produit` int(11) NOT NULL AUTO_INCREMENT,
  `Ref_Produit` varchar(50) DEFAULT NULL,
  `Id_Produit` int(11) NOT NULL,
  PRIMARY KEY (`Id_Ref_Produit`),
  KEY `Id_Produit` (`Id_Produit`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
