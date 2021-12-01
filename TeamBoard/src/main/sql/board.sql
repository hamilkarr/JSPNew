CREATE TABLE `board` (
  `postNm` int NOT NULL AUTO_INCREMENT,
  `gid` bigint DEFAULT NULL,
  `status` enum('normal','tip') DEFAULT 'normal',
  `postTitle` varchar(65) NOT NULL,
  `content` text NOT NULL,
  `memId` varchar(30) NOT NULL,
  `isNotice` tinyint(1) DEFAULT '0' COMMENT '공지사항 여부 - 0 - 일반 게시글, 1 - 공지사항',
  `viewCnt` int unsigned DEFAULT '0',
  `commentCnt` int unsigned DEFAULT '0',
  `regDt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`postNm`),
  KEY `ix_isNotice` (`isNotice` DESC,`regDt` DESC)
);