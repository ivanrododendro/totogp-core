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
-- Dumping data for table `rider`
--

LOCK TABLES `rider` WRITE;
/*!40000 ALTER TABLE `rider` DISABLE KEYS */;
INSERT INTO `rider` VALUES (1,'http://www.motogp.com/en/riders/Andrea+Dovizioso','Andrea','Dovizioso','Dovi',4,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_5885.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@AndreaDovizioso',1),(2,'http://www.motogp.com/en/riders/Stefan+Bradl','Sterfan','Bradl',NULL,6,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6267.jpg?version=2016-02-05.r00e67c3c1e','','@stefanbradl',4),(3,'http://www.motogp.com/en/riders/Hector+Barbera','Hector','Barbera',NULL,8,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6066.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,2),(4,'http://www.motogp.com/en/riders/Danilo+Petrucci','Danilo','Petrucci','Petrux',9,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_8148.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@Petrux9',1),(5,'http://www.motogp.com/en/riders/Alvaro+Bautista','Alvaro','Bautista',NULL,19,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_4323.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@19Bautista',2),(6,'http://www.motogp.com/en/riders/Maverick+Vi%C3%B1ales','Maverick','Vinales',NULL,25,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7409.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@maverickmack25',2),(7,'http://www.motogp.com/en/riders/Dani+Pedrosa','Daniel','Pedros','Dani',26,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_5515.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@26_DaniPedrosa',2),(8,'http://www.motogp.com/en/riders/Andrea+Iannone','Andrea','Iannone','The Maniac',29,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6848.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@andreaiannone29',1),(9,'http://www.motogp.com/en/riders/Cal+Crutchlow','Cal','Crutchlow',NULL,35,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_8010.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@calcrutchlow',3),(10,'http://www.motogp.com/en/riders/Bradley+Smith','Bradley','Smith',NULL,38,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7030.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@BradleySmith38',3),(11,'http://www.motogp.com/en/riders/Aleix+Espargaro','Aleix','Espargaro',NULL,41,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6854.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@AleixEspargaro',2),(12,'http://www.motogp.com/en/riders/Jack+Miller','Jack','Mille','Jackass',43,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_8049.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,5),(13,'http://www.motogp.com/en/riders/Pol+Espargaro','Pol','Espargaro','Policcio',44,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7086.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@polespargaro',2),(14,'http://www.motogp.com/en/riders/Scott+Redding','Scott','Redding',NULL,45,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7173.jpg?version=2016-02-05.r00e67c3c1e		',NULL,'@scottredding45',3),(15,'http://www.motogp.com/en/riders/Valentino+Rossi','Valentino','Rossi','The Doctor',46,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_158.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@ValeYellow46',1),(16,'http://www.motogp.com/en/riders/Eugene+Laverty','Eugene','Laverty',NULL,50,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6828.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,6),(17,'http://www.motogp.com/en/riders/Tito+Rabat','Tito','Rabat',NULL,53,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7013.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,2),(18,'http://www.motogp.com/en/riders/Yonny+Hernandez','Yonny','Hernandez',NULL,68,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7761.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,7),(19,'http://www.motogp.com/en/riders/Loris+Baz','Loris','Baz',NULL,76,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7018.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,8),(20,'http://www.motogp.com/en/riders/Marc+Marquez','Marc','Marquez',NULL,46,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7444.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@marcmarquez93',2),(21,'http://www.motogp.com/en/riders/Jorge+Lorenzo','Jorge','Lorenzo',NULL,99,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6060.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@lorenzo99',2);
/*!40000 ALTER TABLE `rider` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-21 18:17:29
