-- MySQL dump 10.13  Distrib 8.0.23, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: hair
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
-- Table structure for table `broken_promise`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `broken_promise` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '顾客失信表id',
  `customer_id` bigint NOT NULL COMMENT '顾客id（取自user表id）',
  `broken_number` int NOT NULL DEFAULT '0' COMMENT '失信次数 默认为0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `broken_promise`
--

INSERT INTO `broken_promise` VALUES (2,5,8),(3,8,6),(4,12,6);

--
-- Table structure for table `business`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '业务id',
  `business_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务大类',
  `business_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务名称',
  `business_price` double(10,2) NOT NULL COMMENT '服务所需花费价格',
  `business_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务所需花费时间(小时计)',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

INSERT INTO `business` VALUES (1,'基础类','理发',10.00,'0.25',0),(2,'基础类','烫发',50.00,'1',0),(3,'基础类','染发',101.00,'2',0),(4,'拓展类','织眉',20.00,'0.5',0),(5,'拓展类','纹身',500.00,'3',0),(6,'基础类','洗发',5.00,'0.1',0),(7,'拓展类','美甲',25.00,'0.5',0),(8,'测试类','测试业务',100.00,'100',1),(23,'测试类','测试业务',100.00,'1',1);

--
-- Table structure for table `captcha`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `captcha` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验证码',
  `date` datetime NOT NULL COMMENT '发送时间',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用途 1注册，2登录，3找回密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `captcha`
--

INSERT INTO `captcha` VALUES (33,'13100992832','测试会员','738339','2020-05-13 13:52:52','2'),(35,'15865554869','测试用户','281557','2020-05-26 09:13:20','2'),(39,'13705437212','老师','559768','2020-05-31 09:22:32','2'),(48,'17854337608','admin','349978','2021-11-27 18:34:30','2');

--
-- Table structure for table `goods`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goods_price` double(10,2) DEFAULT NULL,
  `goods_num` int DEFAULT NULL,
  `is_deleted` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

INSERT INTO `goods` VALUES (1,'洗发水',10.00,87,0),(2,'染发剂',20.00,73,0);

--
-- Table structure for table `orderr`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderr` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `goods_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customer_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_pay` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1081 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderr`
--

INSERT INTO `orderr` VALUES (1048,2,5,'染发剂','会员1',1),(1065,1,8,'洗发水','会员2',1),(1070,1,1,'洗发水','admin',0),(1072,2,1,'染发剂','admin',0),(1074,2,5,'染发剂','会员1',0),(1075,2,5,'染发剂','会员1',0),(1076,2,5,'染发剂','会员1',0),(1077,2,5,'染发剂','会员1',0),(1078,2,5,'染发剂','会员1',0),(1079,2,12,'染发剂','老师',1),(1080,1,5,'洗发水','会员1',1);

--
-- Table structure for table `permission`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限id 自增',
  `parent_id` bigint NOT NULL COMMENT '父权限id',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限中文名称',
  `enname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限英文名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授权路径',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限描述',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '删除标识，0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--


--
-- Table structure for table `publish_info`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publish_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '店内公告表id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店内公告标题',
  `info` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店内公告信息',
  `publish_time` datetime(6) NOT NULL COMMENT '公告发布时间',
  `picture` varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '店内公告图片',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publish_info`
--

INSERT INTO `publish_info` VALUES (1,'系统通知1','该系统将于今晚凌晨2点到5点进行升级维护','2020-04-28 20:00:00.000000',NULL,0),(2,'系统通知2','明天理发师7请假无法预约','2020-04-27 16:51:07.000000',NULL,0);

--
-- Table structure for table `role`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色表id 自增',
  `parent_id` bigint NOT NULL COMMENT '父级角色',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色中文名称',
  `enname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色英文名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色描述',
  `is_deleted` int NOT NULL DEFAULT '0' COMMENT '删除标识，0未删除，1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

INSERT INTO `role` VALUES (1,0,'管理员','admin','系统管理员',0),(2,1,'理发师','barber','理发师',0),(3,1,'会员','member','注册的顾客',0);

--
-- Table structure for table `role_permission`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id 自增',
  `role_id` bigint NOT NULL COMMENT '角色id',
  `permission_id` bigint NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--


--
-- Table structure for table `skill`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '技能表id',
  `barber_id` bigint NOT NULL COMMENT '理发师id，取自user表id',
  `barber_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '理发师name，取自user表name',
  `business_id` bigint DEFAULT NULL COMMENT '业务id,取自业务表id',
  `business_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '业务名称，取自业务表name',
  `is_closed` int DEFAULT '0' COMMENT '禁用标识 0启用，1禁用 ',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

INSERT INTO `skill` VALUES (1,2,'理发师1',1,'理发',1),(2,2,'理发师1',2,'烫发',1),(3,2,'理发师1',3,'染发',1),(5,2,'理发师1',5,'纹身',1),(6,2,'理发师1',6,'洗发',1),(7,2,'理发师1',7,'美甲',1),(8,3,'理发师2',1,'理发',0),(9,3,'理发师2',2,'烫发',0),(10,3,'理发师2',3,'染发',0),(11,3,'理发师2',6,'洗发',0),(13,4,'理发师3',5,'纹身',0),(38,4,'理发师3',7,'美甲',0),(57,2,'理发师1',4,'织眉',1),(58,4,'理发师3',4,'织眉',0),(93,13,'测试员工',6,'洗发',1),(94,13,'测试员工',3,'染发',1);

--
-- Table structure for table `skill_temp`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill_temp` (
  `line_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `barber_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `business_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `barber_id` bigint DEFAULT NULL,
  `business_id` bigint DEFAULT NULL,
  `is_closed` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill_temp`
--


--
-- Table structure for table `task`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务表id',
  `customer_id` bigint DEFAULT NULL COMMENT '顾客id 取自user表id',
  `barber_id` bigint DEFAULT NULL COMMENT '理发师id 取自user表id',
  `business_id` bigint DEFAULT NULL COMMENT '业务id',
  `task_spend_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预计花费时间 取自business表business_time',
  `task_start` datetime(6) DEFAULT NULL COMMENT '服务开始时间（预约时间）',
  `task_end` datetime(6) DEFAULT NULL COMMENT '服务结束时间 (预约时间+花费时间）',
  `is_remind` int DEFAULT '0' COMMENT '是否已提醒  0未提醒， 1已提醒',
  `is_deleted` int DEFAULT '0' COMMENT '删除标识 0未删除，1已删除',
  `created_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

INSERT INTO `task` VALUES (1,5,2,1,'0.25','2020-04-26 15:00:00.000000','2020-04-26 15:15:00.000000',0,1,'2020-04-25 15:53:55.000000'),(2,8,3,2,'1','2020-04-27 08:00:00.000000','2020-04-27 09:00:00.000000',0,1,'2020-04-25 15:59:36.000000'),(3,5,4,4,'0.5','2020-04-27 12:37:00.000000','2020-04-27 13:07:00.000000',0,1,'2020-04-26 22:31:24.427000'),(4,5,3,2,'1','2020-05-19 16:00:00.000000','2020-05-19 17:00:00.000000',1,1,'2020-04-27 14:46:28.321000'),(5,8,4,5,'3','2020-05-01 10:00:00.000000','2020-05-01 13:00:00.000000',1,1,'2020-04-27 14:49:32.711000'),(6,5,4,7,'0.5','2020-04-29 13:03:00.000000','2020-04-29 13:33:00.000000',0,1,'2020-04-27 16:21:53.697000'),(7,5,2,3,'2','2020-05-15 10:00:00.000000','2020-05-15 12:00:00.000000',0,1,'2020-04-27 19:14:36.224000'),(8,5,3,1,'0.25','2020-04-28 20:30:28.000000','2020-05-02 13:39:00.000000',0,1,'2020-04-28 18:03:07.676000'),(9,5,2,1,'0.25','2020-04-29 00:30:57.000000','2020-04-29 00:30:09.000000',1,1,'2020-04-28 20:27:19.000000'),(10,5,2,3,'2','2020-05-14 08:00:00.000000','2020-05-14 10:00:00.000000',0,1,'2020-05-13 13:18:36.746000'),(11,8,4,4,'0.5','2020-05-14 09:00:00.000000','2020-05-14 09:30:00.000000',1,1,'2020-05-13 13:21:57.580000'),(24,10,2,1,'0.25','2020-05-26 10:20:00.000000','2020-05-26 10:35:00.000000',1,1,'2020-05-26 09:17:14.656000'),(27,5,2,3,'2','2020-06-01 09:30:00.000000','2020-06-01 11:30:00.000000',0,1,'2020-05-29 21:57:36.353000'),(28,8,3,1,'0.25','2020-06-01 14:00:00.000000','2020-06-01 14:15:00.000000',1,0,'2020-05-29 21:58:21.645000'),(29,8,2,1,'0.25','2020-06-16 10:00:00.000000','2020-06-16 10:15:00.000000',0,1,'2020-05-30 20:24:54.273000'),(30,5,2,4,'0.5','2020-06-10 11:00:00.000000','2020-06-10 11:30:00.000000',1,0,'2020-05-31 08:53:16.659000'),(31,12,2,1,'0.25','2020-05-31 10:30:00.000000','2020-05-31 10:45:00.000000',1,0,'2020-05-31 09:25:31.071000');

--
-- Table structure for table `test`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

INSERT INTO `test` VALUES (1);

--
-- Table structure for table `test2`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test2` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test2`
--


--
-- Table structure for table `user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `is_deleted` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

INSERT INTO `user` VALUES (1,'admin','123456','1','17854337608','160552257@qq.com',NULL,0),(2,'理发师1','123456','1','17854337609','1605522570@qq.com','2020-05-18',1),(3,'理发师2','123456','1','17854337611','1605522570@qq.com',NULL,0),(4,'理发师3','123456','2','17854337608','1605522570@qq.com',NULL,0),(5,'会员1','123456','1','17854317608','1605522570@qq.com','1997-03-13',0),(8,'会员2','123456','2','17854337608','1605522570@qq.com','1997-03-13',0),(12,'老师','123456','1','13705437212','1234@qq.com','2020-05-05',0),(13,'测试员工','123456','1','17854331111',NULL,NULL,1),(14,'会员3','123456','1','18888888888','123456@qq.com','2021-10-31',0);

--
-- Table structure for table `user_role`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint NOT NULL COMMENT '角色id',
  `role_id` bigint NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` VALUES (1,5,3),(2,1,1),(5,8,3),(6,2,2),(7,3,2),(8,4,2),(18,1010,3),(19,1011,3),(20,1012,2),(21,1013,2),(22,9,3),(23,10,3),(24,11,2),(25,12,3),(26,13,2),(27,14,3);
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-28 17:25:25
