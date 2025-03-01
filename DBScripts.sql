-- MySQL dump 10.13  Distrib 9.2.0, for Linux (x86_64)
--
-- Host: localhost    Database: Postify
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Table structure for table `follows`
--

DROP TABLE IF EXISTS `follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follows` (
  `follower_user_id` int NOT NULL,
  `following_user_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`follower_user_id`,`following_user_id`),
  KEY `followed_user_id` (`following_user_id`),
  CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`follower_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`following_user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follows`
--

LOCK TABLES `follows` WRITE;
/*!40000 ALTER TABLE `follows` DISABLE KEYS */;
/*!40000 ALTER TABLE `follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `body` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `user_id` int DEFAULT NULL,
  `status` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO posts (id, title, body, user_id, status, created_at) VALUES
(1, 'Java Programming from A to Z', 'Java programming tutorial...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-07 12:00:00'),
(2, 'Learning Spring Boot', 'Spring Boot is a powerful framework...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-08 12:00:00'),
(3, 'How to Build RESTful API', 'Guide to creating REST API...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-09 12:00:00'),
(4, 'SQL Query Optimization', 'Tips for optimizing SQL...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-10 12:00:00'),
(5, 'Docker Tutorial', 'Docker helps containerize...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-11 12:00:00'),
(6, 'Introduction to Machine Learning', 'Basic introduction to ML...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-12 12:00:00'),
(7, 'Building Microservices Systems', 'Microservices help applications...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-13 12:00:00'),
(8, 'Understanding Kubernetes', 'Kubernetes is a management platform...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-14 12:00:00'),
(9, 'Data Analysis with Python', 'Using Pandas, NumPy...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-15 12:00:00'),
(10, 'Web Application Security', 'How to secure web applications...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-16 12:00:00'),
(11, 'Modern C++ Programming', 'C++ 17 and its new features...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-17 12:00:00'),
(12, 'ReactJS and Building UIs', 'React is a JavaScript library...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-18 12:00:00'),
(13, 'NodeJS and Express', 'How to build API with Express...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-19 12:00:00'),
(14, 'Learning VueJS from Basic to Advanced', 'VueJS is a frontend framework...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-20 12:00:00'),
(15, 'Mobile Development with Flutter', 'Flutter is Google’s framework...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-21 12:00:00'),
(16, 'Building AI Chatbots', 'Chatbot using NLP...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-22 12:00:00'),
(17, 'Laravel - Powerful PHP Framework', 'Laravel helps web development...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-23 12:00:00'),
(18, 'Django - Python Web Framework', 'Django helps build backend...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-24 12:00:00'),
(19, 'Mastering Elasticsearch', 'Elasticsearch helps in search...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-25 12:00:00'),
(20, 'Learning PostgreSQL from Basics', 'PostgreSQL is a database system...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-26 12:00:00'),
(21, 'Building REST API with FastAPI', 'FastAPI is a Python framework...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-27 12:00:00'),
(22, 'CI/CD with Jenkins', 'Jenkins helps automate...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-28 12:00:00'),
(23, 'Understanding GraphQL', 'GraphQL is a way to query APIs...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-29 12:00:00'),
(24, 'Building Message Queue Systems', 'Kafka, RabbitMQ...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-30 12:00:00'),
(25, 'Deep Learning with TensorFlow', 'Deep learning with TensorFlow...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-01-31 12:00:00'),
(26, 'Data Structures and Algorithms', 'DSA is an important foundation...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-01 12:00:00'),
(27, 'Advanced Python Programming', 'Python and advanced techniques...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-02 12:00:00'),
(28, 'Game Development with Unity', 'Unity helps build games...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-03 12:00:00'),
(29, 'Machine Learning with Scikit-Learn', 'Popular ML library...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-04 12:00:00'),
(30, 'Programming with Golang', 'Golang helps backend development...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-05 12:00:00'),
(31, 'Programming with Ruby on Rails', 'Rails is a powerful framework...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-06 12:00:00'),
(32, 'API Development with NestJS', 'NestJS helps build backend...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-07 12:00:00'),
(33, 'Learning Solidity and Smart Contract', 'Solidity is a programming language...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-08 12:00:00'),
(34, 'Writing Extensions for VS Code', 'Guide to creating extensions for VS Code...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-09 12:00:00'),
(35, 'iOS Programming with Swift', 'Swift is the main language...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-10 12:00:00'),
(36, 'Building Blogs with WordPress', 'WordPress is a popular CMS...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-11 12:00:00'),
(37, 'SEO Optimization for Websites', 'SEO helps improve ranking...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-12 12:00:00'),
(38, 'Game Development with Unreal Engine', 'Unreal Engine supports high-quality graphics...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-13 12:00:00'),
(39, 'Embedded Programming with Arduino', 'Arduino helps program devices...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-14 12:00:00'),
(40, 'Advanced Kubernetes', 'Guide to managing Kubernetes...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-15 12:00:00'),
(41, 'Learning AWS from Basics to Advanced', 'Popular AWS services...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-16 12:00:00'),
(42, 'Creating Telegram Bot with Python', 'Telegram bot automation...', FLOOR(RAND() * 9) + 2, 'APPROVED', '2024-02-17 12:00:00');
(43, 'Building Desktop Applications with Electron
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'dduongdev','$2a$10$evQTmEBaUOdj6TP4ol1BKOfiUN2Aoq6PH9091MwvVNQV2pAvEUKNu','2025-02-26 12:24:17','CUSTOMER'),(2,'harry','$2a$10$pA4vVg8iP.vT61K/Gvowee8gZvNmyY3HzdootZmQbftJuKtm1q836','2025-02-26 13:02:40','CUSTOMER'),(3,'bob','$2a$10$ffO0zi9LfTyptT2h.5wIC.GjHhvaILbOIGUR6dgLCAkcwCBLvDfKG','2025-02-26 13:02:55','CUSTOMER'),(4,'namdev','$2a$10$WZtPz7ehXCfCSxz5o3OzJe81KbvQDSEBG9ZOoZlJ.ve2BamDg0Kja','2025-02-26 13:03:11','CUSTOMER'),(5,'hungdev','$2a$10$EktK/U4BY0M3Y0VwHa5uvejI8rqDhYdax1KTuoe.c/XMgjlcd.Qgy','2025-02-26 13:03:28','CUSTOMER'),(6,'dduongdev_neva_sleeps','$2a$10$B.pDaTVZy2T423Cm/f3pXeyfK9tZgGq0FCbkJR/MSexrSavECiivu','2025-02-26 13:03:40','CUSTOMER'),(7,'frank_tester','$2a$10$hNQE/I621Av8rjIh0YcYZemN9VXbA.uCd9bGg9ssJmMcvhlooZ9sK','2025-02-26 13:04:14','CUSTOMER'),(8,'thuan_ba','$2a$10$kIRFknT.JIgurrpkfFxQtuNFouqeOm0sxUobANBEycdSo8mprMXr6','2025-02-26 13:04:27','CUSTOMER'),(9,'sasuke','$2a$10$36E7W5ml6uDMjl.aVqzS3.EeRwCcmjN9WD611fy82NiwcAmnQQ8i2','2025-02-26 13:04:45','CUSTOMER'),(10,'chiphuong','$2a$10$pqE482xPifzpzPgA35fQ7OZJI4TNO7AWuXS3Of7S4lHUHP7HmjUSq','2025-02-26 13:05:07','CUSTOMER');
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

-- Dump completed on 2025-02-26 13:18:00
