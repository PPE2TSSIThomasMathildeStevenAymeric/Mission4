-- phpMyAdmin SQL Dump
-- version 4.5.4.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Mer 13 Mars 2019 à 14:06
-- Version du serveur :  5.7.11
-- Version de PHP :  5.6.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ffbsq`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id` int(11) NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`id`, `libelle`) VALUES
(1, 'Enfant'),
(2, 'Adulte'),
(3, 'Senior');

-- --------------------------------------------------------

--
-- Structure de la table `club`
--

CREATE TABLE `club` (
  `numAffiliation_Club` int(11) NOT NULL,
  `designation_Club` varchar(255) NOT NULL,
  `adrRueSiege_Club` varchar(255) NOT NULL,
  `adrVilleSiege_Club` varchar(255) NOT NULL,
  `adrCPSiege_Club` int(11) NOT NULL,
  `anneeAffiliation_Club` int(11) NOT NULL,
  `tel_Club` varchar(255) NOT NULL,
  `email_Club` varchar(255) NOT NULL,
  `adrRueCourrier_Club` varchar(255) NOT NULL,
  `adrVilleCourrier_Club` varchar(255) NOT NULL,
  `adrCPCourrier_Club` int(11) NOT NULL,
  `numPrefecture_Club` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `club`
--

INSERT INTO `club` (`numAffiliation_Club`, `designation_Club`, `adrRueSiege_Club`, `adrVilleSiege_Club`, `adrCPSiege_Club`, `anneeAffiliation_Club`, `tel_Club`, `email_Club`, `adrRueCourrier_Club`, `adrVilleCourrier_Club`, `adrCPCourrier_Club`, `numPrefecture_Club`) VALUES
(1, 'LeClub', '13 rue du camp de droite', 'Boulogne', 62200, 2018, '0321576210', 'leclub@gmail.com', '12 place Foch', 'Samer', 62830, 'w6210');

-- --------------------------------------------------------

--
-- Structure de la table `concours`
--

CREATE TABLE `concours` (
  `id_concours` int(11) NOT NULL,
  `date_concours` date DEFAULT NULL,
  `club_org` varchar(255) DEFAULT NULL,
  `commentaires` varchar(255) DEFAULT NULL,
  `grille_points` varchar(255) DEFAULT NULL,
  `nature` varchar(255) DEFAULT NULL,
  `niveau` varchar(255) DEFAULT NULL,
  `categorie` varchar(255) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `concours`
--

INSERT INTO `concours` (`id_concours`, `date_concours`, `club_org`, `commentaires`, `grille_points`, `nature`, `niveau`, `categorie`, `sexe`) VALUES
(14, '2019-02-08', 'LeClub', 'fez', 'Grille B', 'Doublette', 'Régional', 'Senior', ''),
(15, '2019-03-22', NULL, NULL, NULL, 'Individuelle', NULL, 'Adulte', 'Homme'),
(16, '2019-03-15', NULL, NULL, NULL, 'Individuelle', NULL, 'Adulte', 'Homme');

-- --------------------------------------------------------

--
-- Structure de la table `connexion`
--

CREATE TABLE `connexion` (
  `id_conn` int(11) NOT NULL,
  `log_conn` varchar(255) NOT NULL,
  `mdp_conn` varchar(255) NOT NULL,
  `id_club` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `connexion`
--

INSERT INTO `connexion` (`id_conn`, `log_conn`, `mdp_conn`, `id_club`) VALUES
(1, 'root', 'root', 0);

-- --------------------------------------------------------

--
-- Structure de la table `licencie`
--

CREATE TABLE `licencie` (
  `numlicencie` int(11) NOT NULL,
  `nomlicencie` varchar(255) NOT NULL,
  `prenomlicencie` varchar(255) NOT NULL,
  `datedenaissance` date NOT NULL,
  `sexelicencie` varchar(255) NOT NULL,
  `categorielicencie` varchar(255) NOT NULL,
  `positionlicencie` varchar(255) NOT NULL,
  `adr_licencie` varchar(255) NOT NULL,
  `adr_cp_licencie` int(11) NOT NULL,
  `adr_ville_licencie` varchar(255) NOT NULL,
  `tel_licencie` varchar(255) NOT NULL,
  `mail_licencie` varchar(255) NOT NULL,
  `nationnalite_licencie` varchar(255) NOT NULL,
  `classification_licencie` varchar(11) NOT NULL,
  `validite_CM` date NOT NULL,
  `premiere_licence` int(11) NOT NULL,
  `annee_reprise` int(11) NOT NULL,
  `id_ClubLicencie` int(11) DEFAULT NULL,
  `validation` int(11) NOT NULL,
  `photolicencie` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contenu de la table `licencie`
--

INSERT INTO `licencie` (`numlicencie`, `nomlicencie`, `prenomlicencie`, `datedenaissance`, `sexelicencie`, `categorielicencie`, `positionlicencie`, `adr_licencie`, `adr_cp_licencie`, `adr_ville_licencie`, `tel_licencie`, `mail_licencie`, `nationnalite_licencie`, `classification_licencie`, `validite_CM`, `premiere_licence`, `annee_reprise`, `id_ClubLicencie`, `validation`, `photolicencie`) VALUES
(1, 'Lhomme', 'Aymeric', '1999-03-16', 'Masculin', 'Adulte', 'Normal', '47 résidence de l\'Aumonerie', 62830, 'Samer', '0321876699', 'aymericlhomme@orange.fr', 'Française', 'Promotion', '2020-12-05', 2019, 2019, 1, 1, '1.jpeg'),
(2, 'Fievet', 'Thomas', '1997-04-26', 'Masculin', 'Adulte', 'Normal', '58 allée aux oeufs', 62830, 'Samer', '0321587463', 'thomasfievet@orange.fr', 'Française', 'Promotion', '2020-12-05', 2019, 2019, 2, 1, '');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `club`
--
ALTER TABLE `club`
  ADD PRIMARY KEY (`numAffiliation_Club`);

--
-- Index pour la table `concours`
--
ALTER TABLE `concours`
  ADD PRIMARY KEY (`id_concours`);

--
-- Index pour la table `connexion`
--
ALTER TABLE `connexion`
  ADD PRIMARY KEY (`id_conn`);

--
-- Index pour la table `licencie`
--
ALTER TABLE `licencie`
  ADD PRIMARY KEY (`numlicencie`),
  ADD KEY `fk_licencie_club` (`id_ClubLicencie`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `club`
--
ALTER TABLE `club`
  MODIFY `numAffiliation_Club` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `concours`
--
ALTER TABLE `concours`
  MODIFY `id_concours` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT pour la table `connexion`
--
ALTER TABLE `connexion`
  MODIFY `id_conn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `licencie`
--
ALTER TABLE `licencie`
  MODIFY `numlicencie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
