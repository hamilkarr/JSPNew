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
  `isNotice` tinyint(1) DEFAULT '0' COMMENT '공지사항 여부 - 0 - 일반 게시글, 1 - 공지사항',
  `viewCnt` int unsigned DEFAULT '0',
  `commentCnt` int unsigned DEFAULT '0',
  `regDt` datetime DEFAULT CURRENT_TIMESTAMP,
  `memLv` enum('silver','gold','platinum','admin') NOT NULL DEFAULT 'silver',
  PRIMARY KEY (`postNm`),
  KEY `ix_isNotice` (`isNotice` DESC,`regDt` DESC)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (34,1638417824389,'notice','공지사항','<p>뀨</p>\r\n\r\n<p><img src=\"/Test/resources/upload/3\" /></p>\r\n','admin123',1,0,0,'2021-12-02 13:04:00','admin'),(35,1638417843228,'normal','(수정)공지사항2','<p>뀨뀨</p>\r\n','admin123',1,0,6,'2021-12-02 13:04:12','admin'),(36,1638417868473,'normal','글','<p>내용</p>\r\n','abc123456',0,0,0,'2021-12-02 13:04:35','gold'),(37,1638418365517,'normal','글','<p>내용</p>\r\n','test123456',0,0,0,'2021-12-02 13:12:57','gold'),(38,1638427072565,'normal','글','<p>내용</p>\r\n\r\n<p>&nbsp;</p>\r\n','qwe123456',0,0,0,'2021-12-02 15:37:57','silver'),(39,1638427085893,'tip','글','<p>내용</p>\r\n','asd123456',0,0,1,'2021-12-02 15:38:13','platinum');
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
  `memLv` enum('silver','gold','platinum','admin') NOT NULL DEFAULT 'silver',
  PRIMARY KEY (`commentNm`),
  KEY `postNm_idx` (`postNm`) /*!80000 INVISIBLE */,
  CONSTRAINT `postNm` FOREIGN KEY (`postNm`) REFERENCES `board` (`postNm`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boardcomment`
--

LOCK TABLES `boardcomment` WRITE;
/*!40000 ALTER TABLE `boardcomment` DISABLE KEYS */;
INSERT INTO `boardcomment` VALUES (3,35,'admin123','ㄹㅇㅋㅋ','2021-12-02 15:59:01','silver'),(4,35,'qwe123456','ㄹㅇㅋㅋ','2021-12-02 16:49:30','silver'),(5,35,'admin123','ㄹㅇㅋㅋ','2021-12-02 16:49:51','silver'),(7,39,'test123456','ㄹㅇㅋㅋ','2021-12-02 17:30:52','silver'),(8,35,'admin123','fdzz\r\n','2021-12-02 18:15:55','admin'),(9,35,'qwe123456','ㅎ2','2021-12-02 18:28:31','silver'),(10,35,'asd123456','g2','2021-12-02 18:28:46','platinum');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fileinfo`
--

LOCK TABLES `fileinfo` WRITE;
/*!40000 ALTER TABLE `fileinfo` DISABLE KEYS */;
INSERT INTO `fileinfo` VALUES (3,1638417824389,'풍경.jpg','image/jpeg',1,'2021-12-02 13:03:59');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'test123456','$2a$10$iF5tH.vBVJhfF76V49NVk.MC5IEQVvodj5V//JhBOcmKzEtyT/z1S','a@123456','실험체1','gold','','none','','2021-12-02 12:52:59',NULL),(2,'abc123456','$2a$10$U8vJeW3eTUap1FMSYXexr.ORVx9dfqHPfq4EtVQ0tJEKtPFYwRYrK','a@123456','실험체2','gold','','none','','2021-12-02 12:53:12',NULL),(3,'def123456','$2a$10$g8QRWZEBQlMFEYalJtbO4OxASv5F8bhGk4V36ihEmu51gtAGd6jIe','a@123456','실험체3','platinum','','none','','2021-12-02 12:53:37',NULL),(4,'qwe123456','$2a$10$SEbSWXYSMz/K1Fe8LojvvOuyvKsBUuGPYpydVJzsaO.ScJTwkjhl6','a@123456','실험체4','silver','','none','','2021-12-02 12:53:57',NULL),(5,'asd123456','$2a$10$CPr4osgJdlu5o1H8GdfgZueM9fWj0P/8zFgTlF4Op14KeV5xa8Yq6','a@123456','실험체5','platinum','','none','','2021-12-02 12:54:09',NULL),(6,'admin123','$2a$10$2RIwgCS3fnsDrMpq2v8lDus5fHjQlClLdUtGS4P5/DS.eViNgeFUW','a@123456','관리자','admin','','none','','2021-12-02 13:01:28',NULL),(7,'zxc123456','$2a$10$CzwRVoU2CPblp4hFZKrE9eX5.9ROCV0fUGS6xZU13pX7X1GqysIZC','a@123456','실험체6','silver','','none','','2021-12-02 14:13:22',NULL);
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

-- Dump completed on 2021-12-02 18:33:32
