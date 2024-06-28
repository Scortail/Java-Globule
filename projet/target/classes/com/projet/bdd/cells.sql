-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 25 juin 2024 à 08:19
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cells`
--

-- --------------------------------------------------------

--
-- Structure de la table `bacteria`
--

CREATE TABLE `bacteria` (
  `id` int(11) NOT NULL,
  `position` int(100) NOT NULL,
  `etat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bacteria`
--

INSERT INTO `bacteria` (`id`, `position`, `etat`) VALUES
(1, 1, 'mort');

-- --------------------------------------------------------

--
-- Structure de la table `bcell`
--

CREATE TABLE `bcell` (
  `id` int(11) NOT NULL,
  `position` int(100) NOT NULL,
  `etat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `bcell`
--

INSERT INTO `bcell` (`id`, `position`, `etat`) VALUES
(1, 3, 'boost'),
(2, 3, 'boost');

-- --------------------------------------------------------

--
-- Structure de la table `redbloodcell`
--

CREATE TABLE `redbloodcell` (
  `id` int(11) NOT NULL,
  `position` int(100) NOT NULL,
  `etat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `redbloodcell`
--

INSERT INTO `redbloodcell` (`id`, `position`, `etat`) VALUES
(1, 1, 'mort');

-- --------------------------------------------------------

--
-- Structure de la table `tcell`
--

CREATE TABLE `tcell` (
  `id` int(11) NOT NULL,
  `position` int(100) NOT NULL,
  `etat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tcell`
--

INSERT INTO `tcell` (`id`, `position`, `etat`) VALUES
(1, 1, 'mort');

-- --------------------------------------------------------

--
-- Structure de la table `virus`
--

CREATE TABLE `virus` (
  `id` int(11) NOT NULL,
  `position` int(100) NOT NULL,
  `etat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `virus`
--

INSERT INTO `virus` (`id`, `position`, `etat`) VALUES
(1, 1, 'mort');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `bacteria`
--
ALTER TABLE `bacteria`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `bcell`
--
ALTER TABLE `bcell`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `redbloodcell`
--
ALTER TABLE `redbloodcell`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `tcell`
--
ALTER TABLE `tcell`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `virus`
--
ALTER TABLE `virus`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `bacteria`
--
ALTER TABLE `bacteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `bcell`
--
ALTER TABLE `bcell`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `redbloodcell`
--
ALTER TABLE `redbloodcell`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `tcell`
--
ALTER TABLE `tcell`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `virus`
--
ALTER TABLE `virus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
