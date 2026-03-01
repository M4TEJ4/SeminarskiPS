/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.35 : Database - fitnes_model
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fitnes_model` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `fitnes_model`;

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `idKlijent` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prezime` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datumRodjenja` date NOT NULL,
  `brojGodina` int NOT NULL,
  `pol` enum('MUSKI','ZENSKI') COLLATE utf8mb4_unicode_ci NOT NULL,
  `visina` double NOT NULL,
  `tezina` double NOT NULL,
  `BMR` double NOT NULL,
  `brojTelefona` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idKlijent`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `klijent` */

insert  into `klijent`(`idKlijent`,`ime`,`prezime`,`datumRodjenja`,`brojGodina`,`pol`,`visina`,`tezina`,`BMR`,`brojTelefona`) values 
(1,'Ana','Petrovic','1998-02-14',27,'ZENSKI',168,62,1400,'+38160111222'),
(2,'Nikola','Ilic','1995-08-09',30,'MUSKI',182,84,1750,'+38164123456');

/*Table structure for table `plantreninga` */

DROP TABLE IF EXISTS `plantreninga`;

CREATE TABLE `plantreninga` (
  `idPlanTreninga` int NOT NULL AUTO_INCREMENT,
  `datumPocetka` date NOT NULL,
  `datumKraja` date NOT NULL,
  `brojTreningaNedeljno` int NOT NULL,
  `ukupanBrojVezbi` int NOT NULL,
  `faktorAktivnosti` double NOT NULL,
  `dnevniUnosKalorija` int NOT NULL,
  `idTrener` int NOT NULL,
  `idKlijent` int NOT NULL,
  PRIMARY KEY (`idPlanTreninga`),
  KEY `idx_plan_trener` (`idTrener`),
  KEY `idx_plan_klijent` (`idKlijent`),
  CONSTRAINT `fk_plan_klijent` FOREIGN KEY (`idKlijent`) REFERENCES `klijent` (`idKlijent`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_plan_trener` FOREIGN KEY (`idTrener`) REFERENCES `trener` (`idTrener`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `plantreninga` */

insert  into `plantreninga`(`idPlanTreninga`,`datumPocetka`,`datumKraja`,`brojTreningaNedeljno`,`ukupanBrojVezbi`,`faktorAktivnosti`,`dnevniUnosKalorija`,`idTrener`,`idKlijent`) values 
(4,'2025-10-10','2025-10-15',1,1,1,1,1,1);

/*Table structure for table `samostalantrening` */

DROP TABLE IF EXISTS `samostalantrening`;

CREATE TABLE `samostalantrening` (
  `idSamostalanTrening` int NOT NULL AUTO_INCREMENT,
  `idKlijent` int NOT NULL,
  `teretana` tinyint(1) NOT NULL,
  `kardio` tinyint(1) NOT NULL,
  PRIMARY KEY (`idSamostalanTrening`),
  KEY `fk_st_klijent` (`idKlijent`),
  CONSTRAINT `fk_st_klijent` FOREIGN KEY (`idKlijent`) REFERENCES `klijent` (`idKlijent`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `samostalantrening` */

insert  into `samostalantrening`(`idSamostalanTrening`,`idKlijent`,`teretana`,`kardio`) values 
(1,1,1,1),
(2,2,1,0),
(3,1,1,0),
(4,2,0,1);

/*Table structure for table `stavkaplanatreninga` */

DROP TABLE IF EXISTS `stavkaplanatreninga`;

CREATE TABLE `stavkaplanatreninga` (
  `idPlanTreninga` int NOT NULL,
  `rb` int NOT NULL,
  `brojSerija` int NOT NULL,
  `brojPonavljanja` int NOT NULL,
  `napomena` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `idVezba` int NOT NULL,
  PRIMARY KEY (`idPlanTreninga`,`rb`),
  KEY `idx_stavka_vezba` (`idVezba`),
  CONSTRAINT `fk_stavka_plan` FOREIGN KEY (`idPlanTreninga`) REFERENCES `plantreninga` (`idPlanTreninga`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stavka_vezba` FOREIGN KEY (`idVezba`) REFERENCES `vezba` (`idVezba`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `stavkaplanatreninga` */

insert  into `stavkaplanatreninga`(`idPlanTreninga`,`rb`,`brojSerija`,`brojPonavljanja`,`napomena`,`idVezba`) values 
(4,1,2,2,'2',2),
(4,2,3,3,'3',3);

/*Table structure for table `tiptrenera` */

DROP TABLE IF EXISTS `tiptrenera`;

CREATE TABLE `tiptrenera` (
  `idTipTrenera` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(60) COLLATE utf8mb4_unicode_ci NOT NULL,
  `opis` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idTipTrenera`),
  UNIQUE KEY `naziv` (`naziv`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `tiptrenera` */

insert  into `tiptrenera`(`idTipTrenera`,`naziv`,`opis`) values 
(1,'Snaga','Fokus na trening snage i hipertrofiju'),
(2,'Kondicija','Fokus na kondiciju, izdrzljivost i kardio'),
(3,'Rehabilitacija','Korektivni i rehabilitacioni treninzi');

/*Table structure for table `trener` */

DROP TABLE IF EXISTS `trener`;

CREATE TABLE `trener` (
  `idTrener` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prezime` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `korisnickoIme` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lozinka` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idTrener`),
  UNIQUE KEY `korisnickoIme` (`korisnickoIme`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `trener` */

insert  into `trener`(`idTrener`,`ime`,`prezime`,`korisnickoIme`,`lozinka`) values 
(1,'Marko','Markovic','marko.m','hash1'),
(2,'Jelena','Jovanovic','jelena.j','hash2');

/*Table structure for table `treningsatrenerom` */

DROP TABLE IF EXISTS `treningsatrenerom`;

CREATE TABLE `treningsatrenerom` (
  `idTreningSaTrenerom` int NOT NULL AUTO_INCREMENT,
  `idKlijent` int NOT NULL,
  `nivoPodrske` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `zdravstvenoStanje` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idTreningSaTrenerom`),
  KEY `fk_tst_klijent` (`idKlijent`),
  CONSTRAINT `fk_tst_klijent` FOREIGN KEY (`idKlijent`) REFERENCES `klijent` (`idKlijent`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `treningsatrenerom` */

insert  into `treningsatrenerom`(`idTreningSaTrenerom`,`idKlijent`,`nivoPodrske`,`zdravstvenoStanje`) values 
(3,1,'aa','AA');

/*Table structure for table `trtr` */

DROP TABLE IF EXISTS `trtr`;

CREATE TABLE `trtr` (
  `idTrener` int NOT NULL,
  `idTipTrenera` int NOT NULL,
  `datumSticanja` date NOT NULL,
  `nivoStrucnosti` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idTrener`,`idTipTrenera`),
  KEY `fk_trtr_tip` (`idTipTrenera`),
  CONSTRAINT `fk_trtr_tip` FOREIGN KEY (`idTipTrenera`) REFERENCES `tiptrenera` (`idTipTrenera`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_trtr_trener` FOREIGN KEY (`idTrener`) REFERENCES `trener` (`idTrener`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `trtr` */

insert  into `trtr`(`idTrener`,`idTipTrenera`,`datumSticanja`,`nivoStrucnosti`) values 
(1,1,'2022-05-10','Senior'),
(1,2,'2023-03-20','Medior'),
(2,3,'2021-11-01','Senior');

/*Table structure for table `vezba` */

DROP TABLE IF EXISTS `vezba`;

CREATE TABLE `vezba` (
  `idVezba` int NOT NULL AUTO_INCREMENT,
  `naziv` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `grupaMisica` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `oprema` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idVezba`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `vezba` */

insert  into `vezba`(`idVezba`,`naziv`,`grupaMisica`,`oprema`) values 
(1,'Cucanj','Noge','Sipka'),
(2,'Benc potisak','Grudi','Sipka'),
(3,'Zgibovi','Ledja','Sipka'),
(4,'Trcanje','Kardio','Traka');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
