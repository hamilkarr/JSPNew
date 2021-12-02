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
  `gid` bigint DEFAULT NULL,
  `status` enum('normal','tip','notice') DEFAULT 'normal',
  `postTitle` varchar(65) NOT NULL,
  `content` text NOT NULL,
  `memId` varchar(30) NOT NULL,
  `viewCnt` int unsigned DEFAULT '0',
  `commentCnt` int unsigned DEFAULT '0',
  `regDt` datetime DEFAULT CURRENT_TIMESTAMP,
  `isNotice` tinyint(1) DEFAULT '0' COMMENT '공지사항 여부 - 0 - 일반 게시글, 1 - 공지사항',
  PRIMARY KEY (`postNm`),
  KEY `ix_isNotice` (`isNotice` DESC,`regDt` DESC)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,NULL,'normal','2','4','hamilkarr',0,0,'2021-11-24 23:12:16',1),(2,NULL,'tip','5','5','hamilkarr',0,0,'2021-11-24 23:12:29',0),(3,NULL,'normal','2444','555','hamilkarr',0,0,'2021-11-25 18:18:36',0),(4,NULL,'normal','4','44','',0,0,'2021-11-25 23:53:15',0),(5,NULL,'tip','5','55','',0,0,'2021-11-25 23:53:26',0),(6,NULL,'tip','6','66','hamilkarr',0,0,'2021-11-25 23:53:46',0),(7,NULL,'normal','7','77','hamilkarr',0,0,'2021-11-25 23:53:58',0),(8,NULL,'normal','2','2','hamilkarr',0,0,'2021-11-25 23:56:30',0),(9,NULL,'normal','4','4','hamilkarr',0,0,'2021-11-25 23:56:37',0),(10,NULL,'normal','correction','correction','test1234',0,0,'2021-11-27 21:36:41',0),(11,NULL,NULL,'222','555','test1234',0,0,'2021-11-27 21:36:57',0),(12,NULL,'normal','t666','correction','test1234',0,0,'2021-11-27 21:37:04',0),(14,NULL,NULL,'4','4','hamilkarr',0,0,'2021-11-27 22:30:24',0),(15,NULL,NULL,'5','5','hamilkarr',0,0,'2021-11-27 22:30:32',0),(16,NULL,NULL,'7','7','hamilkarr',0,0,'2021-11-27 22:30:50',0),(17,NULL,NULL,'99','9','hamilkarr',0,0,'2021-11-27 22:31:37',0),(18,NULL,NULL,'44','555','hamilkarr',0,0,'2021-11-28 00:16:40',1),(19,NULL,NULL,'공지사항 일껄요','하하','hamilkarr',2,3,'2021-11-28 00:16:56',1),(20,NULL,NULL,'44','555','hamilkarr',0,0,'2021-11-28 13:34:05',0),(22,NULL,'normal','22','44','hamilkarr',0,0,'2021-11-29 23:01:07',0),(23,NULL,'tip','testtest','testest','hamilkarr',0,0,'2021-11-29 23:01:27',0),(25,NULL,'normal','444','<p><img src=\"/TeamBoardBum/resources/upload/1638283515629_artwork-painting-sailboats-sea-wallpaper-preview.jpg\" /></p>\r\n','test1234',3,2,'2021-11-30 23:45:38',0),(27,NULL,'normal','444','<p><img src=\"/TeamBoard/resources/upload/5\" /></p>\r\n','hamilkarr',2,1,'2021-12-01 02:00:56',0),(30,1638348245378,'tip','댓글수정 테스트','<p>테스트</p>\r\n','hamilkarr',3,2,'2021-12-01 17:44:17',0),(31,1638350200534,'normal','그림테스트','<p><img src=\"/TeamBoard/resources/upload/6\" /></p>\r\n','hamilkarr',2,0,'2021-12-01 18:17:34',0),(32,1638361500671,'normal','BoardDao 수정 테스트','<p>ㅌㅌㅌ</p>\r\n','hamilkarr',1,0,'2021-12-01 21:25:19',0),(33,1638361700393,'normal','boardDao 수정 사진 업로드 테스트1','<p>11<img src=\"/TeamBoard/resources/upload/7\" /></p>\r\n','hamilkarr',1,0,'2021-12-01 21:28:42',0),(34,1638362353060,'normal','BoardDao55','<p><img src=\"/TeamBoard/resources/upload/8\" /></p>\r\n','hamilkarr',2,0,'2021-12-01 21:39:37',0),(35,1638363271199,'tip','BoardDao66','<p><img src=\"/TeamBoard/resources/upload/10\" /></p>\r\n','hamilkarr',2,0,'2021-12-01 21:54:44',0),(37,1638366035727,'notice','Status Notice','<p><img src=\"/TeamBoard/resources/upload/12\" />test</p>\r\n','administartor',2,0,'2021-12-01 22:40:49',1),(38,1638405819648,'tip','Class Test','<p><img src=\"/TeamBoard/resources/upload/13\" /></p>\r\n','test1234',3,0,'2021-12-02 09:44:03',0),(39,1638415433001,'normal','12124','<p>124124</p>\r\n','hamilkarr',0,0,'2021-12-02 12:23:57',0),(40,1638430308380,'normal','1212','<p>01034812101</p>\r\n','hamilkarr',1,0,'2021-12-02 16:32:02',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boardcomment`
--

LOCK TABLES `boardcomment` WRITE;
/*!40000 ALTER TABLE `boardcomment` DISABLE KEYS */;
INSERT INTO `boardcomment` VALUES (1,19,'hamilkarr','댓글테스트1','2021-12-01 16:10:48'),(2,19,'hamilkarr','댓글 테스트2','2021-12-01 16:10:55'),(3,19,'hamilkarr','댓글 테스트 3','2021-12-01 16:24:50'),(4,25,'hamilkarr','ㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅁㄴㅇㄹ','2021-12-01 17:02:57'),(5,25,'hamilkarr','ㅇㄴㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇ','2021-12-01 17:03:33'),(6,27,'hamilkarr','악플입니다','2021-12-01 17:12:12'),(8,30,'hamilkarr','진짜 착한 댓글','2021-12-01 17:44:53'),(9,30,'hamilkarr','좋은 댓글 착한 댓글','2021-12-01 21:07:07');
/*!40000 ALTER TABLE `boardcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `boardview`
--

DROP TABLE IF EXISTS `boardview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boardview` (
  `postNm` int NOT NULL,
  `browserId` varchar(45) NOT NULL,
  PRIMARY KEY (`postNm`,`browserId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='게시글 조회 기록';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boardview`
--

LOCK TABLES `boardview` WRITE;
/*!40000 ALTER TABLE `boardview` DISABLE KEYS */;
INSERT INTO `boardview` VALUES (19,'a154ba10b6054d206ce761ce273a9f3b'),(19,'b59f24cc5b3a3418ffa38cfbeff81b78'),(24,'b59f24cc5b3a3418ffa38cfbeff81b78'),(25,'a154ba10b6054d206ce761ce273a9f3b'),(25,'b59f24cc5b3a3418ffa38cfbeff81b78'),(25,'c1a911f41d6bfe916a8be712c9c75a40'),(26,'a154ba10b6054d206ce761ce273a9f3b'),(26,'c1a911f41d6bfe916a8be712c9c75a40'),(27,'a154ba10b6054d206ce761ce273a9f3b'),(27,'b59f24cc5b3a3418ffa38cfbeff81b78'),(28,'b59f24cc5b3a3418ffa38cfbeff81b78'),(29,'b59f24cc5b3a3418ffa38cfbeff81b78'),(30,'a154ba10b6054d206ce761ce273a9f3b'),(30,'b59f24cc5b3a3418ffa38cfbeff81b78'),(30,'c1a911f41d6bfe916a8be712c9c75a40'),(31,'a154ba10b6054d206ce761ce273a9f3b'),(31,'b59f24cc5b3a3418ffa38cfbeff81b78'),(32,'b59f24cc5b3a3418ffa38cfbeff81b78'),(33,'a154ba10b6054d206ce761ce273a9f3b'),(34,'b59f24cc5b3a3418ffa38cfbeff81b78'),(34,'c1a911f41d6bfe916a8be712c9c75a40'),(35,'b59f24cc5b3a3418ffa38cfbeff81b78'),(35,'c1a911f41d6bfe916a8be712c9c75a40'),(36,'c1a911f41d6bfe916a8be712c9c75a40'),(37,'16d7a4fca7442dda3ad93c9a726597e4'),(37,'c1a911f41d6bfe916a8be712c9c75a40'),(38,'16d7a4fca7442dda3ad93c9a726597e4'),(38,'a154ba10b6054d206ce761ce273a9f3b'),(38,'c1a911f41d6bfe916a8be712c9c75a40'),(40,'b59f24cc5b3a3418ffa38cfbeff81b78');
/*!40000 ALTER TABLE `boardview` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fileinfo`
--

LOCK TABLES `fileinfo` WRITE;
/*!40000 ALTER TABLE `fileinfo` DISABLE KEYS */;
INSERT INTO `fileinfo` VALUES (6,1638350200534,'painting-canal-leonid-afremov-gondolas-wallpaper-preview.jpg','image/jpeg',1,'2021-12-01 18:16:52'),(7,1638361700393,'painting-canal-leonid-afremov-gondolas-wallpaper-preview.jpg','image/jpeg',0,'2021-12-01 21:28:40'),(8,1638362353060,'frontend.png','image/png',0,'2021-12-01 21:39:33'),(9,1638362695818,'cityscape-painting-leonid-afremov-wallpaper-preview.jpg','image/jpeg',0,'2021-12-01 21:45:15'),(10,1638363271199,'5470.jpg','image/jpeg',1,'2021-12-01 21:54:42'),(11,1638364034991,'autumn-lights-park-rain-wallpaper-preview.jpg','image/jpeg',1,'2021-12-01 22:07:38'),(12,1638366035727,'5470.jpg','image/jpeg',1,'2021-12-01 22:42:37'),(13,1638405819648,'1636504596839_artwork-painting-sailboats-sea-wallpaper-preview.jpg','image/jpeg',1,'2021-12-02 09:44:01');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'hamilkarr','','','준영','admin','01033334444','kakao','1996108180','2021-11-24 20:31:07',NULL),(2,'test1234','$2a$10$pvQyIhScZV/zDO187emogeWJegRCy8aFAJ8grYmwvTv22C49QH1Ri','hint','test1234','silver','01022223333','none','','2021-11-24 22:55:20',NULL),(3,'test3456','$2a$10$0xmaqMVLVOhKRMv96dKvVemZV5YRHg6n.jMwGJREL7if7pRlzvq0m','hint','tester','silver','01055556666','none','','2021-11-28 16:10:38',NULL),(4,'administartor','$2a$10$sHZspGpG0/Z2PYhuhVIuoelfoUSB4/WELaoDpYaHOhnNjtzD.57Fi','asd','admin','silver','01033335555','none','','2021-12-01 00:50:21',NULL);
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

-- Dump completed on 2021-12-02 18:43:42
