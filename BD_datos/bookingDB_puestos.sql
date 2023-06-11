-- MySQL dump 10.13  Distrib 8.0.33, for Linux (x86_64)
--
-- Host: localhost    Database: bookingDB
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `puestos`
--

DROP TABLE IF EXISTS `puestos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puestos` (
  `id` int NOT NULL,
  `estado` tinyint NOT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  `reservas` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puestos`
--

LOCK TABLES `puestos` WRITE;
/*!40000 ALTER TABLE `puestos` DISABLE KEYS */;
INSERT INTO `puestos` VALUES (1,1,'Ordenador','[{\"id\":8,\"detalle\":\"admin\"},{\"id\":9,\"detalle\":\"\"},{\"id\":10,\"detalle\":\"admin1\"},{\"id\":11,\"detalle\":\"admin\"},{\"id\":12,\"detalle\":\"\"},{\"id\":13,\"detalle\":\"\"},{\"id\":14,\"detalle\":\"\"},{\"id\":15,\"detalle\":\"\"}]'),(2,1,'Ordenador','[{\"id\": 8, \"detalle\": \"\"}, {\"id\": 9, \"detalle\": \"\"}, {\"id\": 10, \"detalle\": \"\"}, {\"id\": 11, \"detalle\": \"\"}, {\"id\": 12, \"detalle\": \"\"}, {\"id\": 13, \"detalle\": \"\"}, {\"id\": 14, \"detalle\": \"\"}, {\"id\": 15, \"detalle\": \"\"}]'),(3,1,'Ordenador','[{\"id\": 8, \"detalle\": \"\"}, {\"id\": 9, \"detalle\": \"\"}, {\"id\": 10, \"detalle\": \"\"}, {\"id\": 11, \"detalle\": \"\"}, {\"id\": 12, \"detalle\": \"\"}, {\"id\": 13, \"detalle\": \"\"}, {\"id\": 14, \"detalle\": \"\"}, {\"id\": 15, \"detalle\": \"\"}]'),(4,1,'Sala de Reuniones','[{\"id\":8,\"detalle\":\"a\"},{\"id\":9,\"detalle\":\"a\"},{\"id\":10,\"detalle\":\"a\"},{\"id\":11,\"detalle\":\"a\"},{\"id\":12,\"detalle\":\"a\"},{\"id\":13,\"detalle\":\"a\"},{\"id\":14,\"detalle\":\"a\"},{\"id\":15,\"detalle\":\"a\"}]');
/*!40000 ALTER TABLE `puestos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-24 12:23:00
