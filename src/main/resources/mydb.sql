-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customerId` int DEFAULT NULL,
  `catalogId` int DEFAULT NULL,
  `appointmentDate` timestamp NULL DEFAULT NULL,
  `status` enum('OPEN','CANCELED','PAID','COMPLETE') DEFAULT NULL,
  `reviewId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CustomerId_idx` (`customerId`),
  KEY `CatalogId_idx` (`catalogId`),
  KEY `reviewId_idx` (`reviewId`),
  CONSTRAINT `CatalogId` FOREIGN KEY (`catalogId`) REFERENCES `catalog` (`id`),
  CONSTRAINT `CustomerId` FOREIGN KEY (`customerId`) REFERENCES `users` (`id`),
  CONSTRAINT `reviewId` FOREIGN KEY (`reviewId`) REFERENCES `review` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES (41,18,19,'2021-03-25 10:00:00','COMPLETE',8),(60,18,53,'2021-03-26 11:00:00','COMPLETE',NULL),(61,18,47,'2021-03-25 06:00:00','OPEN',NULL),(62,18,35,'2021-03-26 11:00:00','OPEN',NULL);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalog`
--

DROP TABLE IF EXISTS `catalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `catalog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `masterId` int NOT NULL,
  `serviceId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `masterId_idx` (`masterId`),
  KEY `serviceId_idx` (`serviceId`),
  CONSTRAINT `masterId` FOREIGN KEY (`masterId`) REFERENCES `users` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `serviceId` FOREIGN KEY (`serviceId`) REFERENCES `service` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalog`
--

LOCK TABLES `catalog` WRITE;
/*!40000 ALTER TABLE `catalog` DISABLE KEYS */;
INSERT INTO `catalog` VALUES (4,9,4),(5,9,5),(9,9,6),(18,12,7),(19,12,4),(21,9,7),(24,9,7),(31,13,6),(33,13,5),(34,13,6),(35,13,5),(36,13,4),(37,13,6),(38,13,5),(39,13,7),(40,13,7),(41,13,7),(42,13,7),(43,13,7),(44,13,7),(45,13,7),(46,13,4),(47,13,5),(48,13,6),(49,13,4),(50,9,4),(51,9,4),(52,9,4),(53,13,4),(54,13,4),(55,13,4);
/*!40000 ALTER TABLE `catalog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` int NOT NULL AUTO_INCREMENT,
  `comment` varchar(200) DEFAULT NULL,
  `rating` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (5,'jhfdjtj d ydtk uykl',8),(6,'tgrdfgdfg',8),(7,'авпіпвіпі',10),(8,'vfd 1 8g 7g7 df',7);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `serviceName` varchar(45) NOT NULL,
  `serviceDuration` varchar(45) DEFAULT NULL,
  `servicePrice` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (4,'Покраска волос','40',150),(5,'Стрижка','30',120),(6,'Маникюр','25',80),(7,'Макияж','120',200);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role` enum('GUEST','CLIENT','ADMIN','MASTER') NOT NULL,
  `enabled` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (9,'admin','A8A76790FF950B754DE0B8FA5824CEEE','admin@mail.com','ADMIN',1),(12,'Edward','A8A76790FF950B754DE0B8FA5824CEEE','edward@mail.com','ADMIN',1),(13,'Jake','A8A76790FF950B754DE0B8FA5824CEEE','jake@mail.com','MASTER',1),(18,'John','A8A76790FF950B754DE0B8FA5824CEEE','john@mail.com','CLIENT',1),(19,'Master','A8A76790FF950B754DE0B8FA5824CEEE','master@mail.com','MASTER',1),(20,'admin','6E60A28384BC05FA5B33CC579D040C56','admin@mail.com','ADMIN',1),(21,'Master','6E60A28384BC05FA5B33CC579D040C56','master@mail.com','MASTER',1),(22,'Client','6E60A28384BC05FA5B33CC579D040C56','client@mail.com','CLIENT',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-25 15:45:31
