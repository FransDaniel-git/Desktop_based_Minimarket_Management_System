-- MySQL dump 10.13  Distrib 8.4.3, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_mart
-- ------------------------------------------------------
-- Server version	8.4.3

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
-- Table structure for table `detail_transaksi`
--

DROP TABLE IF EXISTS `detail_transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_transaksi` (
  `id_detail` int NOT NULL AUTO_INCREMENT,
  `id_transaksi` int NOT NULL,
  `id_produk` int NOT NULL,
  `id_kasir` int NOT NULL,
  `jumlah` int NOT NULL,
  `sub_total` double NOT NULL,
  PRIMARY KEY (`id_detail`),
  KEY `fk_detail_transaksi_produk` (`id_produk`),
  KEY `fk_detail_transaksi_transaksi` (`id_transaksi`),
  KEY `fk_detail_transaksi_kasir` (`id_kasir`),
  CONSTRAINT `fk_detail_transaksi_kasir` FOREIGN KEY (`id_kasir`) REFERENCES `kasir` (`id_kasir`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_detail_transaksi_produk` FOREIGN KEY (`id_produk`) REFERENCES `produk` (`id_produk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_detail_transaksi_transaksi` FOREIGN KEY (`id_transaksi`) REFERENCES `transaksi` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_transaksi`
--

LOCK TABLES `detail_transaksi` WRITE;
/*!40000 ALTER TABLE `detail_transaksi` DISABLE KEYS */;
INSERT INTO `detail_transaksi` VALUES (33,637304942,14,11,2,30000),(34,637304942,21,11,3,30000),(35,637304942,18,11,4,140000),(36,637304942,23,11,5,40000),(37,637304942,20,11,1,450000),(38,159893620,14,12,2,30000),(39,159893620,23,12,16,128000),(40,159893620,19,12,3,240000),(41,159893620,17,12,12,144000),(42,159893620,15,12,1,120000),(43,1956268720,16,13,3,750000),(44,1956268720,17,13,20,240000),(45,1956268720,21,13,4,40000),(46,1956268720,23,13,5,40000),(47,1956268720,14,13,2,30000),(48,895151439,17,14,12,144000),(49,895151439,23,14,12,96000),(50,895151439,21,14,4,40000),(51,895151439,14,14,4,60000),(53,1117353860,23,15,10,80000),(54,1117353860,20,15,2,900000),(55,1117353860,16,15,2,500000),(56,1117353860,15,15,2,240000),(57,1117353860,18,15,5,175000);
/*!40000 ALTER TABLE `detail_transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kasir`
--

DROP TABLE IF EXISTS `kasir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kasir` (
  `id_kasir` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_kasir`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kasir`
--

LOCK TABLES `kasir` WRITE;
/*!40000 ALTER TABLE `kasir` DISABLE KEYS */;
INSERT INTO `kasir` VALUES (11,'Andi'),(12,'Budi'),(13,'Cici'),(14,'Dedi'),(15,'Eko'),(16,'Fani'),(17,'Gina'),(18,'Hana'),(19,'Iwan'),(20,'Joko');
/*!40000 ALTER TABLE `kasir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produk`
--

DROP TABLE IF EXISTS `produk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produk` (
  `id_produk` int NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `tipe` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `harga` double NOT NULL,
  `stok` int NOT NULL,
  PRIMARY KEY (`id_produk`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produk`
--

LOCK TABLES `produk` WRITE;
/*!40000 ALTER TABLE `produk` DISABLE KEYS */;
INSERT INTO `produk` VALUES (14,'Biskuit','Makanan dan Minuman',15000,40),(15,'Beras 10 kg','Bahan Pokok',120000,97),(16,'Panci','Produk Dapur',250000,13),(17,'Sabun Mandi','Produk Mandi',12000,156),(18,'Detergen','Produk Rumah Tangga',35000,66),(19,'Susu Formula','Produk Bayi dan Anak',80000,27),(20,'Blender','Produk Elektronik',450000,12),(21,'Susu UHT','Makanan dan Minuman',10000,139),(22,'Gula Pasir','Bahan Pokok',15000,77),(23,'Sikat Gigi','Produk Mandi',8000,52);
/*!40000 ALTER TABLE `produk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaksi`
--

DROP TABLE IF EXISTS `transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaksi` (
  `id_transaksi` int NOT NULL,
  `tanggal` date NOT NULL,
  `total` double NOT NULL,
  `nama_kasir` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `nama_pembeli` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_transaksi`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaksi`
--

LOCK TABLES `transaksi` WRITE;
/*!40000 ALTER TABLE `transaksi` DISABLE KEYS */;
INSERT INTO `transaksi` VALUES (159893620,'2024-06-20',662000,'Budi','Bima'),(637304942,'2024-06-20',690000,'Andi','Ahmad'),(895151439,'2024-06-20',340000,'Dedi','Dika'),(1117353860,'2024-06-20',1895000,'Eko','Efan'),(1956268720,'2024-06-20',1100000,'Cici','Cika');
/*!40000 ALTER TABLE `transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_mart'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-11-05 17:21:21
