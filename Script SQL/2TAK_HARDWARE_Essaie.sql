-- --------------------------------------------------------

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`Id_Categorie`, `libelle`, `descriptif`) VALUES
(1, 'PC Portable', 'Mobile, léger, puissant'),
(2, 'Tour Gaming', 'Puissant, durable, Personnalisable'),
(3, 'Composants', 'Variés, Accessible');

-- --------------------------------------------------------


INSERT INTO `client` (`Id_Client`, `prenom`, `nom`, `email`, `telephone`) VALUES
(1, 'leS', '100', 'leS@100.com', '00 00 00 00 00');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`Id_Commande`, `date_commande`, `Id_Etat_Commande`, `Id_Client`, `Id_Personnel`) VALUES
(30, '2020-11-02 19:09:07', 1, 1, 1),
(29, '2020-10-30 16:28:32', 1, 1, 1),
(28, '2020-10-30 16:27:28', 1, 1, 1);

-- --------------------------------------------------------

--
-- Déchargement des données de la table `contenir`
--

INSERT INTO `contenir` (`Id_Commande`, `Id_Produit`, `quantite`) VALUES
(30, 6, 8),
(30, 3, 2),
(30, 1, 9),
(29, 6, 8),
(29, 3, 2),
(29, 1, 9),
(28, 6, 8),
(28, 3, 2),
(28, 1, 9);

-- --------------------------------------------------------


--
-- Déchargement des données de la table `etat_commande`
--

INSERT INTO `etat_commande` (`Id_Etat_Commande`, `libelle`, `descriptif`) VALUES
(1, 'non-payé', ''),
(2, 'payé', ''),
(3, 'en cours de préparation', ''),
(4, 'préparé', ''),
(5, 'expédié', ''),
(6, 'livré', ''),
(7, 'terminé', '');

-- --------------------------------------------------------

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`Id_Produit`, `libelle`, `description`, `tarif`, `stock`, `note`, `lien_image`, `Id_Categorie`) VALUES
(1, 'Asus ROG Strix G (G531GT-HN574T)', '', '1099.9900', 10, '8.00', '', 1),
(2, 'test2', '', '40.0000', 50, '6.00', '', 3),
(3, 'test3', '', '50.0000', 20, '8.00', '', 3),
(4, 'test4', '', '25.0000', 25, '9.00', '', 3),
(5, 'test5', '', '400.0000', 50, '6.00', '', 2),
(6, 'test6', '', '1200.0000', 9, '8.00', '', 2),
(7, 'test7', '', '2000.0000', 5, '9.00', '', 2);


-- --------------------------------------------------------
