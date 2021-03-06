use totogp;

/*!40000 ALTER TABLE `championship` DISABLE KEYS */;
INSERT INTO `championship` VALUES (1,'http://www.motogp.com','MotoGP');
/*!40000 ALTER TABLE `championship` ENABLE KEYS */;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (5,'Australia'),(7,'Colombia'),(8,'France'),(4,'Germany'),(6,'Ireland'),(1,'Italy'),(2,'Spain'),(3,'UK'), (9, 'Malaysia');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
/*!40000 ALTER TABLE `circuit` DISABLE KEYS */;
INSERT INTO `circuit` VALUES (1,'Losalil International Circuit','https://en.wikipedia.org/wiki/Losail_International_Circuit',9);
/*!40000 ALTER TABLE `circuit` ENABLE KEYS */;

/*!40000 ALTER TABLE `race` DISABLE KEYS */;
INSERT INTO `race` VALUES (1,'http://www.motogp.com/en/Results+Statistics','http://www.motogp.com/en/event/Qatar','2016-03-16 00:00:00',1,1);
/*!40000 ALTER TABLE `race` ENABLE KEYS */;
/*!40000 ALTER TABLE `rider` DISABLE KEYS */;
INSERT INTO `rider` VALUES (1,'http://www.motogp.com/en/riders/Andrea+Dovizioso','Andrea','Dovizioso','Dovi',4,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_5885.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@AndreaDovizioso',1),(2,'http://www.motogp.com/en/riders/Stefan+Bradl','Sterfan','Bradl',NULL,6,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6267.jpg?version=2016-02-05.r00e67c3c1e','','@stefanbradl',4),(3,'http://www.motogp.com/en/riders/Hector+Barbera','Hector','Barbera',NULL,8,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6066.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,2),(4,'http://www.motogp.com/en/riders/Danilo+Petrucci','Danilo','Petrucci','Petrux',9,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_8148.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@Petrux9',1),(5,'http://www.motogp.com/en/riders/Alvaro+Bautista','Alvaro','Bautista',NULL,19,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_4323.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@19Bautista',2),(6,'http://www.motogp.com/en/riders/Maverick+Vi%C3%B1ales','Maverick','Vinales',NULL,25,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7409.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@maverickmack25',2),(7,'http://www.motogp.com/en/riders/Dani+Pedrosa','Daniel','Pedros','Dani',26,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_5515.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@26_DaniPedrosa',2),(8,'http://www.motogp.com/en/riders/Andrea+Iannone','Andrea','Iannone','The Maniac',29,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6848.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@andreaiannone29',1),(9,'http://www.motogp.com/en/riders/Cal+Crutchlow','Cal','Crutchlow',NULL,35,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_8010.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@calcrutchlow',3),(10,'http://www.motogp.com/en/riders/Bradley+Smith','Bradley','Smith',NULL,38,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7030.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@BradleySmith38',3),(11,'http://www.motogp.com/en/riders/Aleix+Espargaro','Aleix','Espargaro',NULL,41,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6854.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@AleixEspargaro',2),(12,'http://www.motogp.com/en/riders/Jack+Miller','Jack','Mille','Jackass',43,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_8049.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,5),(13,'http://www.motogp.com/en/riders/Pol+Espargaro','Pol','Espargaro','Policcio',44,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7086.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@polespargaro',2),(14,'http://www.motogp.com/en/riders/Scott+Redding','Scott','Redding',NULL,45,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7173.jpg?version=2016-02-05.r00e67c3c1e		',NULL,'@scottredding45',3),(15,'http://www.motogp.com/en/riders/Valentino+Rossi','Valentino','Rossi','The Doctor',46,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_158.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@ValeYellow46',1),(16,'http://www.motogp.com/en/riders/Eugene+Laverty','Eugene','Laverty',NULL,50,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6828.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,6),(17,'http://www.motogp.com/en/riders/Tito+Rabat','Tito','Rabat',NULL,53,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7013.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,2),(18,'http://www.motogp.com/en/riders/Yonny+Hernandez','Yonny','Hernandez',NULL,68,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7761.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,7),(19,'http://www.motogp.com/en/riders/Loris+Baz','Loris','Baz',NULL,76,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7018.jpg?version=2016-02-05.r00e67c3c1e',NULL,NULL,8),(20,'http://www.motogp.com/en/riders/Marc+Marquez','Marc','Marquez',NULL,46,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_7444.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@marcmarquez93',2),(21,'http://www.motogp.com/en/riders/Jorge+Lorenzo','Jorge','Lorenzo',NULL,99,'http://www.motogp.com/tpl/css.motogp.com/img/riders/2016/206x181/rider_6060.jpg?version=2016-02-05.r00e67c3c1e',NULL,'@lorenzo99',2);
/*!40000 ALTER TABLE `rider` ENABLE KEYS */;

LOCK TABLES `contest` WRITE;
/*!40000 ALTER TABLE `contest` DISABLE KEYS */;

INSERT INTO `contest` (`id`, `current_bet_type`, `open`, `year`, `championship_id`, `currentRace_id`, `regulation_id`) VALUES	(1,'winner_blind_bet',1,2018,1,1,1);

/*!40000 ALTER TABLE `contest` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table enrollment
# ------------------------------------------------------------



# Dump of table race
# ------------------------------------------------------------

LOCK TABLES `race` WRITE;
/*!40000 ALTER TABLE `race` DISABLE KEYS */;

INSERT INTO `race` (`id`, `championship_rece_stats_url`, `championship_rece_url`, `date`, `championship_id`, `circuit_id`) VALUES (1,'http://www.motogp.com/en/Results+Statistics','http://www.motogp.com/en/event/Qatar','2016-03-16 00:00:00',1,1);

/*!40000 ALTER TABLE `race` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `activation_token`, `active`, `email`, `firstname`, `lastname`, `password`, `wants_to_be_notified`, `country_id`, `rider_id`) VALUES(1,NULL,b'1','ivan.rododendro@gmail.com','Ivan','Rododendro',NULL,b'0',1,1);

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;