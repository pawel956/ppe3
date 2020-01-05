-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Dim 05 Janvier 2020 à 23:35
-- Version du serveur :  5.5.58-0+deb8u1
-- Version de PHP :  7.2.4-1+0~20180405085552.20+jessie~1.gbpbff9f0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `ppe3`
--

-- --------------------------------------------------------

--
-- Structure de la table `cv`
--

CREATE TABLE IF NOT EXISTS `cv` (
`id` int(11) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `signature` text NOT NULL,
  `nom_maitrise` varchar(255) NOT NULL,
  `maitrise` text NOT NULL,
  `id_utilisateur` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `experience_pro`
--

CREATE TABLE IF NOT EXISTS `experience_pro` (
`id` int(11) NOT NULL,
  `entreprise` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `annee_debut` date NOT NULL,
  `annee_fin` date NOT NULL,
  `id_cv` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE IF NOT EXISTS `formation` (
`id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `annee_debut` date NOT NULL,
  `annee_fin` date NOT NULL,
  `id_cv` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `info_comp`
--

CREATE TABLE IF NOT EXISTS `info_comp` (
`id` int(11) NOT NULL,
  `intitule` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `id_cv` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `pays`
--

CREATE TABLE IF NOT EXISTS `pays` (
`id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE IF NOT EXISTS `utilisateurs` (
`id` int(11) NOT NULL,
  `statut` int(1) NOT NULL DEFAULT '0',
  `identifiant` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `num_telephone` varchar(14) NOT NULL,
  `courriel` varchar(255) NOT NULL,
  `date_de_naissance` date NOT NULL,
  `photo` longblob NOT NULL,
  `num_telephone_deux` varchar(14) DEFAULT NULL,
  `site_web` varchar(255) DEFAULT NULL,
  `id_ville` int(11) NOT NULL,
  `num_rue` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `info_complementaire` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `villes`
--

CREATE TABLE IF NOT EXISTS `villes` (
`id` int(11) NOT NULL,
  `id_pays` int(11) NOT NULL,
  `code_postal` int(5) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `cv`
--
ALTER TABLE `cv`
 ADD PRIMARY KEY (`id`), ADD KEY `id_utilisateur` (`id_utilisateur`);

--
-- Index pour la table `experience_pro`
--
ALTER TABLE `experience_pro`
 ADD PRIMARY KEY (`id`), ADD KEY `id_cv` (`id_cv`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
 ADD PRIMARY KEY (`id`), ADD KEY `id_cv` (`id_cv`);

--
-- Index pour la table `info_comp`
--
ALTER TABLE `info_comp`
 ADD PRIMARY KEY (`id`), ADD KEY `id_cv` (`id_cv`);

--
-- Index pour la table `pays`
--
ALTER TABLE `pays`
 ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
 ADD PRIMARY KEY (`id`,`identifiant`), ADD KEY `id_ville` (`id_ville`);

--
-- Index pour la table `villes`
--
ALTER TABLE `villes`
 ADD PRIMARY KEY (`id`), ADD KEY `id_pays` (`id_pays`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `cv`
--
ALTER TABLE `cv`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `experience_pro`
--
ALTER TABLE `experience_pro`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `info_comp`
--
ALTER TABLE `info_comp`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT pour la table `pays`
--
ALTER TABLE `pays`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `villes`
--
ALTER TABLE `villes`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `cv`
--
ALTER TABLE `cv`
ADD CONSTRAINT `cv_ibfk_1` FOREIGN KEY (`id_utilisateur`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `experience_pro`
--
ALTER TABLE `experience_pro`
ADD CONSTRAINT `experience_pro_ibfk_1` FOREIGN KEY (`id_cv`) REFERENCES `cv` (`id`);

--
-- Contraintes pour la table `formation`
--
ALTER TABLE `formation`
ADD CONSTRAINT `formation_ibfk_1` FOREIGN KEY (`id_cv`) REFERENCES `cv` (`id`);

--
-- Contraintes pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
ADD CONSTRAINT `utilisateurs_ibfk_1` FOREIGN KEY (`id_ville`) REFERENCES `villes` (`id`);

--
-- Contraintes pour la table `villes`
--
ALTER TABLE `villes`
ADD CONSTRAINT `villes_ibfk_1` FOREIGN KEY (`id_pays`) REFERENCES `pays` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
