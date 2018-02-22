-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: totogp
-- ------------------------------------------------------
-- Server version	5.7.9

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bet`
--

DROP TABLE IF EXISTS `bet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bet` (
  `TYPE` varchar(31) COLLATE utf8_bin NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bet_date` datetime NOT NULL,
  `is_winning` bit(1) DEFAULT NULL,
  `points` float DEFAULT NULL,
  `is_first_winning` bit(1) DEFAULT NULL,
  `is_second_winning` bit(1) DEFAULT NULL,
  `is_third_winning` bit(1) DEFAULT NULL,
  `enrollment_id` int(11) DEFAULT NULL,
  `race_id` bigint(20) DEFAULT NULL,
  `poleman_id` bigint(20) DEFAULT NULL,
  `first_id` bigint(20) DEFAULT NULL,
  `second_id` bigint(20) DEFAULT NULL,
  `third_id` bigint(20) DEFAULT NULL,
  `winner_rider_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jwb9ba1cny6rex9aemqp1u9fj` (`race_id`,`enrollment_id`),
  KEY `FK_29l0cjudswp0phybt7oe35m5t` (`enrollment_id`),
  KEY `FK_gsttjtl9950xx5qeubd28gtg2` (`poleman_id`),
  KEY `FK_3enm4ya260dvakm0ic7eikby2` (`first_id`),
  KEY `FK_s7b56v3qon2mobno1qr7xlkyc` (`second_id`),
  KEY `FK_o0gjtvxo9q4bx2ccw8f45t0v1` (`third_id`),
  KEY `FK_BET_WINNER_RIDER` (`winner_rider_id`),
  CONSTRAINT `FK_29l0cjudswp0phybt7oe35m5t` FOREIGN KEY (`enrollment_id`) REFERENCES `enrollment` (`id`),
  CONSTRAINT `FK_3enm4ya260dvakm0ic7eikby2` FOREIGN KEY (`first_id`) REFERENCES `rider` (`id`),
  CONSTRAINT `FK_BET_WINNER_RIDER` FOREIGN KEY (`winner_rider_id`) REFERENCES `rider` (`id`),
  CONSTRAINT `FK_f14h72oe6mfg3ji0g7e6m6h4v` FOREIGN KEY (`race_id`) REFERENCES `race` (`id`),
  CONSTRAINT `FK_gsttjtl9950xx5qeubd28gtg2` FOREIGN KEY (`poleman_id`) REFERENCES `rider` (`id`),
  CONSTRAINT `FK_o0gjtvxo9q4bx2ccw8f45t0v1` FOREIGN KEY (`third_id`) REFERENCES `rider` (`id`),
  CONSTRAINT `FK_s7b56v3qon2mobno1qr7xlkyc` FOREIGN KEY (`second_id`) REFERENCES `rider` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `championship`
--

DROP TABLE IF EXISTS `championship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `championship` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `homepage` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(30) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_psg9hf1guaipan1ng9lk396nu` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `circuit`
--

DROP TABLE IF EXISTS `circuit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `circuit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `wikipedia_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_964v127aohkv7ubwkd59gx8h2` (`name`),
  KEY `FK_ihup3dqowsu9u5i2scyqruaw1` (`country_id`),
  CONSTRAINT `FK_ihup3dqowsu9u5i2scyqruaw1` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contest`
--

DROP TABLE IF EXISTS `contest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_bet_type` varchar(255) COLLATE utf8_bin NOT NULL,
  `open` bit(1) NOT NULL,
  `year` int(11) NOT NULL,
  `championship_id` bigint(20) DEFAULT NULL,
  `currentRace_id` bigint(20) DEFAULT NULL,
  `regulation_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_61r1bi83j7k1n29ws0e6abj8q` (`championship_id`,`regulation_id`),
  KEY `FK_eyqaiu6soiqt3asguyyu5grku` (`currentRace_id`),
  KEY `FK_qwrnrkhvprqxdvdug0g7j75gb` (`regulation_id`),
  CONSTRAINT `FK_9521p8hwyyc4cs7mlqads1c3k` FOREIGN KEY (`championship_id`) REFERENCES `championship` (`id`),
  CONSTRAINT `FK_eyqaiu6soiqt3asguyyu5grku` FOREIGN KEY (`currentRace_id`) REFERENCES `race` (`id`),
  CONSTRAINT `FK_qwrnrkhvprqxdvdug0g7j75gb` FOREIGN KEY (`regulation_id`) REFERENCES `regulation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_llidyp77h6xkeokpbmoy710d4` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrollment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contest_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ja34q666gktsdni63o2rc1jr9` (`contest_id`,`user_id`),
  KEY `FK_sd7o8vg10i3wr76pt6ivxasop` (`user_id`),
  CONSTRAINT `FK_eepy8logp1527cpl1dvrj43wb` FOREIGN KEY (`contest_id`) REFERENCES `contest` (`id`),
  CONSTRAINT `FK_sd7o8vg10i3wr76pt6ivxasop` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `race`
--

DROP TABLE IF EXISTS `race`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `race` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `championship_rece_stats_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `championship_rece_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date` datetime NOT NULL,
  `championship_id` bigint(20) DEFAULT NULL,
  `circuit_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lcgyey4moanla85ytcdrpk9w5` (`championship_id`,`circuit_id`,`date`),
  KEY `FK_evx0fm0yt366b7i8pv6h959x8` (`circuit_id`),
  CONSTRAINT `FK_evx0fm0yt366b7i8pv6h959x8` FOREIGN KEY (`circuit_id`) REFERENCES `circuit` (`id`),
  CONSTRAINT `FK_h0vbjo9lwp6rbpumgth70clma` FOREIGN KEY (`championship_id`) REFERENCES `championship` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `race_event`
--

DROP TABLE IF EXISTS `race_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `race_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `race_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fw4te0dvljfm9qx1e596xux92` (`race_id`,`type_id`),
  KEY `FK_rnyd2ynxfipqamu9sbjqm0vhh` (`type_id`),
  CONSTRAINT `FK_g8is60iu68hm9xi2hu3q6et9v` FOREIGN KEY (`race_id`) REFERENCES `race` (`id`),
  CONSTRAINT `FK_rnyd2ynxfipqamu9sbjqm0vhh` FOREIGN KEY (`type_id`) REFERENCES `race_event_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `race_event_type`
--

DROP TABLE IF EXISTS `race_event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `race_event_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(3) COLLATE utf8_bin NOT NULL,
  `championship_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kg817aa8nfifyvhs3i8kh57v5` (`championship_id`,`name`),
  CONSTRAINT `FK_63435j5t0aapk46xcrmo7q5mc` FOREIGN KEY (`championship_id`) REFERENCES `championship` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `race_race_event`
--

DROP TABLE IF EXISTS `race_race_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `race_race_event` (
  `race_id` bigint(20) NOT NULL,
  `receEvents_id` bigint(20) NOT NULL,
  PRIMARY KEY (`race_id`,`receEvents_id`),
  UNIQUE KEY `UK_t225x84099c6ba3osxcewdeyg` (`receEvents_id`),
  CONSTRAINT `FK_nqvik7df8t2pamqp3hyw45j3` FOREIGN KEY (`race_id`) REFERENCES `race` (`id`),
  CONSTRAINT `FK_t225x84099c6ba3osxcewdeyg` FOREIGN KEY (`receEvents_id`) REFERENCES `race_event` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `regulation`
--

DROP TABLE IF EXISTS `regulation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regulation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `public_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `registrationCountry_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_omop4qb39t38m3yclmyanptx3` (`registrationCountry_id`),
  CONSTRAINT `FK_omop4qb39t38m3yclmyanptx3` FOREIGN KEY (`registrationCountry_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rider`
--

DROP TABLE IF EXISTS `rider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `championship_homepage_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `firstname` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `lastname` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `nickname` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `number` int(11) NOT NULL,
  `picture_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `twitterHashtags` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `twitterUser` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l6ntfflwhomdyogwwlwq4bm9y` (`firstname`,`lastname`),
  UNIQUE KEY `UK_39b59um6503lpkicg63bjpthe` (`twitterUser`),
  KEY `FK_sc2p9sr7iqkvfqu30g196humo` (`country_id`),
  CONSTRAINT `FK_sc2p9sr7iqkvfqu30g196humo` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `statistics`
--

DROP TABLE IF EXISTS `statistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `podium_first_winning_rate` float NOT NULL,
  `podium_second_winning_rate` float NOT NULL,
  `podium_third_winning_rate` float NOT NULL,
  `points_per_race` float NOT NULL,
  `points` float NOT NULL,
  `pole_winnning_rate` float NOT NULL,
  `winner_winning_rate` float NOT NULL,
  `afterRace_id` bigint(20) DEFAULT NULL,
  `enrollment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_han4kddhne17ngdhwst60phod` (`afterRace_id`,`enrollment_id`),
  KEY `FK_6nen4ltbr0k5a19hqqm6m11l8` (`enrollment_id`),
  CONSTRAINT `FK_1pt6mnwjn01nkgtcwejibggj0` FOREIGN KEY (`afterRace_id`) REFERENCES `race` (`id`),
  CONSTRAINT `FK_6nen4ltbr0k5a19hqqm6m11l8` FOREIGN KEY (`enrollment_id`) REFERENCES `enrollment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) COLLATE utf8_bin NOT NULL,
  `firstname` varchar(40) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(40) COLLATE utf8_bin NOT NULL,
  `wants_to_be_notified` bit(1) NOT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `rider_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FK_5wuijeiy2que04prc94ne74a4` (`country_id`),
  KEY `FK_edqtvivxqx5ucvhbrb1nsiwq5` (`rider_id`),
  CONSTRAINT `FK_5wuijeiy2que04prc94ne74a4` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `FK_edqtvivxqx5ucvhbrb1nsiwq5` FOREIGN KEY (`rider_id`) REFERENCES `rider` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-21 18:20:14
