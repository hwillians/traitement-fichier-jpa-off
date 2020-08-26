-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le :  lun. 24 août 2020 à 22:30
-- Version du serveur :  5.7.26
-- Version de PHP :  7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `aliments`
--
CREATE DATABASE IF NOT EXISTS `aliments` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `aliments`;

-- --------------------------------------------------------

--
-- Structure de la table `additif`
--

DROP TABLE IF EXISTS `additif`;
CREATE TABLE IF NOT EXISTS `additif` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `allergene`
--

DROP TABLE IF EXISTS `allergene`;
CREATE TABLE IF NOT EXISTS `allergene` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `compo_addi`
--

DROP TABLE IF EXISTS `compo_addi`;
CREATE TABLE IF NOT EXISTS `compo_addi` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `id_pro_ad` int(10) DEFAULT NULL,
  `id_addi` int(10) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ADD_COMP` (`id_addi`),
  KEY `FK_PRO_ADD_COMP` (`id_pro_ad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `compo_allerg`
--

DROP TABLE IF EXISTS `compo_allerg`;
CREATE TABLE IF NOT EXISTS `compo_allerg` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `id_pro_al` int(11) DEFAULT NULL,
  `id_allerg` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ALL_COMP` (`id_allerg`),
  KEY `FK_PRO_ALL_COMP` (`id_pro_al`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `compo_ing`
--

DROP TABLE IF EXISTS `compo_ing`;
CREATE TABLE IF NOT EXISTS `compo_ing` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `id_pro_ing` int(11) DEFAULT NULL,
  `id_ing` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ING_COMP` (`id_ing`),
  KEY `FK_PRO_ING_COMP` (`id_pro_ing`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE IF NOT EXISTS `ingredient` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(1000) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

DROP TABLE IF EXISTS `marque`;
CREATE TABLE IF NOT EXISTS `marque` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `nom` varchar(250) NOT NULL,
  `ID_mrq` int(11) NOT NULL,
  `ID_cat` int(11) NOT NULL,
  `grade` varchar(5) NOT NULL,
  `energie` int(4) DEFAULT NULL,
  `graisse` decimal(6,3) DEFAULT NULL,
  `sucre` decimal(6,3) DEFAULT NULL,
  `fibre` decimal(6,3) DEFAULT NULL,
  `proteine` decimal(6,3) DEFAULT NULL,
  `sel` decimal(6,3) DEFAULT NULL,
  `vitA` decimal(6,3) DEFAULT NULL,
  `vitD` decimal(6,3) DEFAULT NULL,
  `vitE` decimal(6,3) DEFAULT NULL,
  `vitK` decimal(6,3) DEFAULT NULL,
  `vitC` decimal(6,3) DEFAULT NULL,
  `vitB1` decimal(6,3) DEFAULT NULL,
  `vitB2` decimal(6,3) DEFAULT NULL,
  `vitPp` decimal(6,3) DEFAULT NULL,
  `vitB6` decimal(6,3) DEFAULT NULL,
  `vitB9` decimal(6,3) DEFAULT NULL,
  `vitB12` decimal(6,3) DEFAULT NULL,
  `ca` decimal(6,3) DEFAULT NULL,
  `mg` decimal(6,3) DEFAULT NULL,
  `iron` decimal(6,3) DEFAULT NULL,
  `fer` decimal(6,3) DEFAULT NULL,
  `betaCaro` decimal(6,3) DEFAULT NULL,
  `huilePalme` decimal(6,3) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_mrq_pro` (`ID_mrq`),
  KEY `FK_cat_pro` (`ID_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
