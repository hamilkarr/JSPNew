-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: miniblog
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `blog`
--

DROP TABLE IF EXISTS `blog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `subject` varchar(250) NOT NULL,
  `content` text NOT NULL,
  `regDt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog`
--

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` VALUES (1,'정답은','<h1>Leonid Afremov 입니다.</h1>\r\n','2021-10-13 22:56:46'),(2,'테스트1','<p>11</p>\r\n','2021-10-13 22:57:03'),(3,'테스트2','<p>22</p>\r\n','2021-10-13 22:57:09'),(4,'테스트3','<p>33</p>\r\n','2021-10-13 22:57:15'),(5,'테스트4','<p>44</p>\r\n','2021-10-13 22:57:21'),(6,'테스트5','<p>55</p>\r\n','2021-10-13 22:57:27'),(7,'그림1','<p><img src=\"/MiniBlog/resources/upload/1634133477318_다운로드.jpg\" style=\"width:80%\" /></p>\r\n','2021-10-13 22:57:59'),(8,'그림2','<p><img src=\"/MiniBlog/resources/upload/1634133525932_따뜻한_색감의_세계_5.jpg\" style=\"width:80%\" /></p>\r\n','2021-10-13 22:58:47'),(9,'그림3','<p><img src=\"/MiniBlog/resources/upload/1634133549143_street-light-fall-painting-leonid-afremov-wallpaper-preview.jpg\" style=\"width:80%\" /></p>\r\n','2021-10-13 22:59:11'),(10,'그림4','<p><img src=\"/MiniBlog/resources/upload/1634133573624_painting-canal-leonid-afremov-gondolas-wallpaper-preview.jpg\" style=\"width:80%\" /></p>\r\n','2021-10-13 22:59:35'),(11,'그림5','<p><img src=\"/MiniBlog/resources/upload/1634133584973_cityscape-painting-leonid-afremov-wallpaper-preview.jpg\" style=\"width:80%\" /></p>\r\n','2021-10-13 22:59:46'),(12,'누구일까요','<p><img src=\"/MiniBlog/resources/upload/1634133601032_autumn-lights-park-rain-wallpaper-preview.jpg\" style=\"width:80%\" /></p>\r\n','2021-10-13 23:00:02'),(13,'이 그림의 화가는','<p><img src=\"/MiniBlog/resources/upload/1634133616662_artwork-painting-sailboats-sea-wallpaper-preview.jpg\" style=\"width:80%\" /></p>\r\n','2021-10-13 23:00:17');
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-10 18:44:23
