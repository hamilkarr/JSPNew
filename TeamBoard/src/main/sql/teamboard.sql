-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: teamboard
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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `postNm` int NOT NULL AUTO_INCREMENT,
  `status` enum('normal','tip') DEFAULT 'normal',
  `postTitle` varchar(65) NOT NULL,
  `content` text NOT NULL,
  `memId` varchar(30) NOT NULL,
  `regDt` datetime DEFAULT CURRENT_TIMESTAMP,
  `isNotice` tinyint(1) DEFAULT '0' COMMENT '공지사항 여부 - 0 - 일반 게시글, 1 - 공지사항',
  PRIMARY KEY (`postNm`),
  KEY `ix_isNotice` (`isNotice` DESC,`regDt` DESC)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'normal','2','4','hamilkarr','2021-11-24 23:12:16',1),(2,'tip','5','5','hamilkarr','2021-11-24 23:12:29',0),(3,'normal','2444','555','hamilkarr','2021-11-25 18:18:36',0),(4,'normal','4','44','','2021-11-25 23:53:15',0),(5,'tip','5','55','','2021-11-25 23:53:26',0),(6,'tip','6','66','hamilkarr','2021-11-25 23:53:46',0),(7,'normal','7','77','hamilkarr','2021-11-25 23:53:58',0),(8,'normal','2','2','hamilkarr','2021-11-25 23:56:30',0),(9,'normal','4','4','hamilkarr','2021-11-25 23:56:37',0),(10,NULL,'444','555','test1234','2021-11-27 21:36:41',0),(11,NULL,'222','555','test1234','2021-11-27 21:36:57',0),(12,NULL,'t666','333','test1234','2021-11-27 21:37:04',0),(13,NULL,'222','555','test1234','2021-11-27 21:37:39',0),(14,NULL,'4','4','hamilkarr','2021-11-27 22:30:24',0),(15,NULL,'5','5','hamilkarr','2021-11-27 22:30:32',0),(16,NULL,'7','7','hamilkarr','2021-11-27 22:30:50',0),(17,NULL,'99','9','hamilkarr','2021-11-27 22:31:37',0),(18,NULL,'44','555','hamilkarr','2021-11-28 00:16:40',1),(19,NULL,'공지사항 일껄요','하하','hamilkarr','2021-11-28 00:16:56',1),(20,NULL,'44','555','hamilkarr','2021-11-28 13:34:05',0);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boardcomment`
--

DROP TABLE IF EXISTS `boardcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boardcomment` (
  `commentNm` int NOT NULL AUTO_INCREMENT,
  `postNm` int NOT NULL,
  `memId` varchar(45) NOT NULL,
  `content` text NOT NULL,
  `regDt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`commentNm`),
  KEY `postNm_idx` (`postNm`),
  CONSTRAINT `postNm` FOREIGN KEY (`postNm`) REFERENCES `board` (`postNm`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boardcomment`
--

LOCK TABLES `boardcomment` WRITE;
/*!40000 ALTER TABLE `boardcomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `boardcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fileinfo`
--

DROP TABLE IF EXISTS `fileinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fileinfo` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `gid` bigint DEFAULT NULL,
  `fileName` varchar(100) DEFAULT NULL,
  `fileType` varchar(65) DEFAULT NULL,
  `isFinish` tinyint(1) DEFAULT '0' COMMENT '파일을 업로드후 상품 등록, 또는 게시글 등록이 완됴되면 유효한 파일 업로드라는 표기.\n0 - 유효하지 않으므로 일괄적으로 파일 정리\n1 - 정상 업로드 - 유지',
  `regDt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`),
  KEY `ix_gid_regDt` (`gid`,`regDt`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fileinfo`
--

LOCK TABLES `fileinfo` WRITE;
/*!40000 ALTER TABLE `fileinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `fileinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `memNo` int NOT NULL AUTO_INCREMENT,
  `memId` varchar(30) NOT NULL,
  `memPw` varchar(65) NOT NULL,
  `memPwHint` varchar(50) NOT NULL,
  `memNm` varchar(30) NOT NULL,
  `memLv` enum('silver','gold','platinum','admin') DEFAULT 'silver' COMMENT '회원 등급',
  `cellPhone` varchar(11) DEFAULT NULL,
  `socialType` enum('none','kakao','naver') DEFAULT 'none',
  `socialId` varchar(65) DEFAULT NULL,
  `regDT` datetime DEFAULT CURRENT_TIMESTAMP,
  `modDt` datetime DEFAULT NULL,
  PRIMARY KEY (`memNo`),
  UNIQUE KEY `memId` (`memId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'hamilkarr','','','준영','admin','01033334444','kakao','1996108180','2021-11-24 20:31:07',NULL),(2,'test1234','$2a$10$pvQyIhScZV/zDO187emogeWJegRCy8aFAJ8grYmwvTv22C49QH1Ri','hint','test1234','silver','01022223333','none','','2021-11-24 22:55:20',NULL),(3,'test3456','$2a$10$0xmaqMVLVOhKRMv96dKvVemZV5YRHg6n.jMwGJREL7if7pRlzvq0m','hint','tester','silver','01055556666','none','','2021-11-28 16:10:38',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-29 21:11:25
