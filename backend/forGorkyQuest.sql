-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: gorky_quest
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `districts`
--

DROP TABLE IF EXISTS `districts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `districts` (
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `districts`
--

LOCK TABLES `districts` WRITE;
/*!40000 ALTER TABLE `districts` DISABLE KEYS */;
INSERT INTO `districts` VALUES ('Автозаводский','Крупнейший промышленный район города, известный своими заводами.'),('Нижегородский','Живописный район с видом на Волгу, популярный среди туристов.'),('Сормовский','Один из старейших районов Нижнего Новгорода с богатой историей.'),('Центральный','Исторический центр города, место сосредоточения культурных объектов.');
/*!40000 ALTER TABLE `districts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promo_codes`
--

DROP TABLE IF EXISTS `promo_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promo_codes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL,
  `description` text,
  `valid_from` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `valid_until` timestamp NOT NULL,
  `discount_percentage` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  CONSTRAINT `promo_codes_chk_1` CHECK ((`discount_percentage` between 1 and 100))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promo_codes`
--

LOCK TABLES `promo_codes` WRITE;
/*!40000 ALTER TABLE `promo_codes` DISABLE KEYS */;
INSERT INTO `promo_codes` VALUES (1,'WELCOME10','Скидка 10% на первое участие в квестах.','2024-11-19 16:19:10','2024-12-30 21:00:00',10,'2024-11-19 16:19:10'),(2,'AUTUMN20','Скидка 20% на квесты в осенний период.','2024-11-19 16:19:10','2024-11-29 21:00:00',20,'2024-11-19 16:19:10'),(3,'NNTOUR50','Скидка 50% для туристов.','2024-11-19 16:19:10','2024-12-30 21:00:00',50,'2024-11-19 16:19:10');
/*!40000 ALTER TABLE `promo_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quests`
--

DROP TABLE IF EXISTS `quests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quests` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `task` varchar(255) NOT NULL,
  `difficulty` varchar(255) NOT NULL,
  `point` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL,
  `reward` varchar(255) NOT NULL,
  `district_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_district_name` (`district_name`),
  CONSTRAINT `fk_district_name` FOREIGN KEY (`district_name`) REFERENCES `districts` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quests`
--

LOCK TABLES `quests` WRITE;
/*!40000 ALTER TABLE `quests` DISABLE KEYS */;
INSERT INTO `quests` VALUES (1,'По следам купцов','Найдите здание бывшей купеческой усадьбы.','easy','ул. Коминтерна, д. 10','усадьба','10','Сормовский'),(2,'Сормовский парк','Сфотографируйтесь у главного входа в парк.','medium','Сормовский парк','главный вход','15','Сормовский'),(3,'Автозаводская площадь','Считайте количество колонн на здании ДК.','easy','ДК Автозаводский','6','10','Автозаводский'),(4,'Промышленное наследие','Найдите памятник в честь первых рабочих.','medium','Площадь рабочих','рабочий памятник','20','Автозаводский'),(5,'Кремль Нижнего Новгорода','Укажите название башни, расположенной напротив главного входа.','hard','Нижегородский Кремль','Дмитриевская','50','Центральный'),(6,'Памятник Минину','Назовите год, указанный на памятнике Минину.','medium','Площадь Минина и Пожарского','1818','20','Центральный'),(7,'Набережная Федоровского','Найдите место с лучшим видом на Волгу и сделайте фото.','easy','Набережная Федоровского','фото','15','Нижегородский'),(8,'Чкаловская лестница','Сколько ступеней на Чкаловской лестнице?','hard','Чкаловская лестница','560','50','Нижегородский');
/*!40000 ALTER TABLE `quests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_purchases`
--

DROP TABLE IF EXISTS `user_purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_purchases` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `promo_code_id` bigint NOT NULL,
  `purchase_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `expiration_date` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `promo_code_id` (`promo_code_id`),
  CONSTRAINT `user_purchases_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `user_purchases_ibfk_2` FOREIGN KEY (`promo_code_id`) REFERENCES `promo_codes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_purchases`
--

LOCK TABLES `user_purchases` WRITE;
/*!40000 ALTER TABLE `user_purchases` DISABLE KEYS */;
INSERT INTO `user_purchases` VALUES (1,1,1,'2024-11-01 09:00:00','2024-11-30 21:00:00'),(2,2,2,'2024-11-10 12:00:00','2024-11-29 21:00:00');
/*!40000 ALTER TABLE `user_purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_quest_progress`
--

DROP TABLE IF EXISTS `user_quest_progress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_quest_progress` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `quest_id` bigint NOT NULL,
  `status` enum('not_started','in_progress','completed') DEFAULT 'not_started',
  `start_time` timestamp NULL DEFAULT NULL,
  `end_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `quest_id` (`quest_id`),
  CONSTRAINT `user_quest_progress_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `user_quest_progress_ibfk_2` FOREIGN KEY (`quest_id`) REFERENCES `quests` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_quest_progress`
--

LOCK TABLES `user_quest_progress` WRITE;
/*!40000 ALTER TABLE `user_quest_progress` DISABLE KEYS */;
INSERT INTO `user_quest_progress` VALUES (1,1,1,'completed','2024-11-01 07:00:00','2024-11-01 07:30:00'),(2,2,3,'in_progress','2024-11-19 06:00:00',NULL),(3,3,5,'not_started',NULL,NULL);
/*!40000 ALTER TABLE `user_quest_progress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tokens` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Иван Иванов','ivanov@example.com','password123',100,'2024-11-19 16:19:10'),(2,'Мария Смирнова','smirnova@example.com','securepass',50,'2024-11-19 16:19:10'),(3,'Алексей Кузнецов','kuznetsov@example.com','mypassword',30,'2024-11-19 16:19:10');
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

-- Dump completed on 2024-11-21 13:03:02
