-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : db
-- Généré le : mar. 15 déc. 2020 à 15:20
-- Version du serveur :  5.7.32
-- Version de PHP : 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `ppe3`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `Id_Categorie` int(11) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `descriptif` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`Id_Categorie`, `libelle`, `descriptif`) VALUES
(1, 'PC Portable', 'Mobile, léger, puissant'),
(2, 'Tour Gaming', 'Puissant, durable, Personnalisable'),
(3, 'Composants', 'Variés, Accessible');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `Id_Client` int(11) NOT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`Id_Client`, `prenom`, `nom`, `email`, `telephone`) VALUES
(3, 'test', 'TEST', 'test@test.com', '0606060606');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `Id_Commande` int(11) NOT NULL,
  `date_commande` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Id_Etat_Commande` int(11) NOT NULL,
  `Id_Client` int(11) NOT NULL,
  `Id_Personnel` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`Id_Commande`, `date_commande`, `Id_Etat_Commande`, `Id_Client`, `Id_Personnel`) VALUES
(3, '2020-11-02 19:09:07', 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `contenir`
--

CREATE TABLE `contenir` (
  `Id_Commande` int(11) NOT NULL,
  `Id_Produit` int(11) NOT NULL,
  `quantite` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `contenir`
--

INSERT INTO `contenir` (`Id_Commande`, `Id_Produit`, `quantite`) VALUES
(1, 1, 9),
(1, 3, 2),
(1, 6, 8);

-- --------------------------------------------------------

--
-- Structure de la table `etat_commande`
--

CREATE TABLE `etat_commande` (
  `Id_Etat_Commande` int(11) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `descriptif` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `etat_commande`
--

INSERT INTO `etat_commande` (`Id_Etat_Commande`, `libelle`, `descriptif`) VALUES
(15, 'non-payé', ''),
(16, 'payé', ''),
(17, 'en cours de préparation', ''),
(18, 'préparé', ''),
(19, 'expédié', ''),
(20, 'livré', ''),
(21, 'terminé', '');

-- --------------------------------------------------------

--
-- Structure de la table `personnel`
--

CREATE TABLE `personnel` (
  `Id_Personnel` int(11) NOT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `pseudo` varchar(50) DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `telephone` varchar(50) DEFAULT NULL,
  `email` varchar(180) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Id_Profil` int(11) NOT NULL,
  `roles` json NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `personnel`
--

INSERT INTO `personnel` (`Id_Personnel`, `prenom`, `nom`, `pseudo`, `password`, `telephone`, `email`, `Id_Profil`, `roles`) VALUES
(1, NULL, NULL, 'user', '$argon2id$v=19$m=65536,t=4,p=1$aYYjfqtF7u3mPlyHbd2hSw$ltwuuAzvN86+B+Jd0OQ5K781/CG4ymcHGGmZqp7DqEw', NULL, 'user@user.com', 2, '[\"ROLE_USER\"]'),
(2, NULL, NULL, 'root', '$argon2id$v=19$m=65536,t=4,p=1$oTlqBMSUQOX/+bSMGGfK5A$/t9wVJHpbAxQHx3Yx2kbtpBtaAoM/9fofXxa0F8GRBk', NULL, 'root@root.com', 1, '[\"ROLE_ADMIN\"]');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `Id_Produit` int(11) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `description` text NOT NULL,
  `tarif` decimal(19,4) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `note` decimal(15,2) DEFAULT NULL,
  `lien_image` text,
  `Id_Categorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`Id_Produit`, `libelle`, `description`, `tarif`, `stock`, `note`, `lien_image`, `Id_Categorie`) VALUES
(15, 'Asus ROG Strix G (G531GT-HN574T)', '', '1099.9900', 10, '8.00', '', 1),
(16, 'test2', '', '40.0000', 50, '6.00', '', 3),
(17, 'test3', '', '50.0000', 20, '8.00', '', 3),
(18, 'test4', '', '25.0000', 25, '9.00', '', 3),
(19, 'test5', '', '400.0000', 50, '6.00', '', 2),
(20, 'test6', '', '1200.0000', 9, '8.00', '', 2),
(21, 'test7', '', '2000.0000', 5, '9.00', '', 2);

-- --------------------------------------------------------

--
-- Structure de la table `profil`
--

CREATE TABLE `profil` (
  `Id_Profil` int(11) NOT NULL,
  `libelle` varchar(50) DEFAULT NULL,
  `descriptif` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `profil`
--

INSERT INTO `profil` (`Id_Profil`, `libelle`, `descriptif`) VALUES
(7, 'Administrateur', ''),
(8, 'Agent', '');

-- --------------------------------------------------------

--
-- Structure de la table `ref_produit`
--

CREATE TABLE `ref_produit` (
  `Id_Ref_Produit` int(11) NOT NULL,
  `Ref_Produit` varchar(50) DEFAULT NULL,
  `Id_Produit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`Id_Categorie`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`Id_Client`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`Id_Commande`),
  ADD KEY `Id_Etat_Commande` (`Id_Etat_Commande`),
  ADD KEY `Id_Client` (`Id_Client`),
  ADD KEY `Id_Personnel` (`Id_Personnel`);

--
-- Index pour la table `contenir`
--
ALTER TABLE `contenir`
  ADD PRIMARY KEY (`Id_Produit`,`Id_Commande`),
  ADD KEY `Id_Commande` (`Id_Commande`);

--
-- Index pour la table `etat_commande`
--
ALTER TABLE `etat_commande`
  ADD PRIMARY KEY (`Id_Etat_Commande`);

--
-- Index pour la table `personnel`
--
ALTER TABLE `personnel`
  ADD PRIMARY KEY (`Id_Personnel`),
  ADD KEY `Id_Profil` (`Id_Profil`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`Id_Produit`),
  ADD KEY `Id_Categorie` (`Id_Categorie`);

--
-- Index pour la table `profil`
--
ALTER TABLE `profil`
  ADD PRIMARY KEY (`Id_Profil`);

--
-- Index pour la table `ref_produit`
--
ALTER TABLE `ref_produit`
  ADD PRIMARY KEY (`Id_Ref_Produit`),
  ADD KEY `Id_Produit` (`Id_Produit`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `Id_Categorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `Id_Client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `Id_Commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `etat_commande`
--
ALTER TABLE `etat_commande`
  MODIFY `Id_Etat_Commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `personnel`
--
ALTER TABLE `personnel`
  MODIFY `Id_Personnel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `Id_Produit` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `profil`
--
ALTER TABLE `profil`
  MODIFY `Id_Profil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `ref_produit`
--
ALTER TABLE `ref_produit`
  MODIFY `Id_Ref_Produit` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
