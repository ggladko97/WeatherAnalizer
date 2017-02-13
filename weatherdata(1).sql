-- phpMyAdmin SQL Dump
-- version 4.4.15.8
-- https://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2017 at 11:33 AM
-- Server version: 5.6.31
-- PHP Version: 5.3.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `weatherdata`
--
CREATE DATABASE IF NOT EXISTS `weatherdata` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `weatherdata`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `account_count`(IN `city_input` VARCHAR(50), IN `date_input` DATE)
BEGIN
DECLARE TEMPERATURE_CONDITION INT DEFAULT 0;
DECLARE TEMPERATURE_PREDICTION_LOW INT DEFAULT 0;
DECLARE TEMPERATURE_PREDICTION_HIGH INT DEFAULT 0;
DECLARE input_city_id INT ;
DECLARE input_date_id INT ;

SELECT city_id INTO input_city_id FROM cities WHERE city_name=city_input LIMIT 1;


SELECT date_id INTO input_date_id FROM dates WHERE dates.date=date_input LIMIT 1;
SELECT input_date_id;

SELECT condition_temperature INTO TEMPERATURE_CONDITION FROM conditon WHERE condition_city_id=input_city_id && condition_date_id=input_date_id;
SELECT TEMPERATURE_CONDITION;

SELECT prediction_temperature_low INTO TEMPERATURE_PREDICTION_LOW FROM prediction WHERE prediction_city_id=input_city_id && prediction_date_id=input_date_id;


SELECT prediction_temperature_high INTO TEMPERATURE_PREDICTION_HIGH FROM prediction WHERE prediction_city_id=input_city_id && prediction_date_id=input_date_id;

INSERT INTO `logi_analize`(`logi_id`, `logi_city`, `logi_date`, `logi_diff_high`, `logi_diff_low`) VALUES ('',input_city_id,input_date_id,ABS(TEMPERATURE_PREDICTION_HIGH-TEMPERATURE_CONDITION),ABS(TEMPERATURE_PREDICTION_LOW-TEMPERATURE_CONDITION));
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_city`(IN `city_in` VARCHAR(50))
    NO SQL
begin
  SELECT AVG(condition_temperature) FROM conditon WHERE condition_city_id = (SELECT city_id FROM cities WHERE city_name = city_in);
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `avg_temp`(IN `day` DATE)
begin

  SELECT AVG(condition_temperature) FROM conditon WHERE condition_date_id = (SELECT date_id FROM dates WHERE dates.date = day);

end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

CREATE TABLE IF NOT EXISTS `cities` (
  `city_id` int(10) unsigned NOT NULL,
  `city_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `country` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `region` varchar(40) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cities`
--

INSERT INTO `cities` (`city_id`, `city_name`, `country`, `region`) VALUES
(1, 'Example', 'Laplandia', 'Cold'),
(8, 'Ð¼Ð¾ÑÐºÐ²Ð°', 'random', 'huilo'),
(9, 'lublin', 'random', 'huilo'),
(11, 'paris', 'random', 'huilo'),
(12, 'moscow', 'random', 'huilo'),
(15, 'kiev', 'random', 'huilo'),
(18, 'katowice', 'random', 'huilo'),
(19, 'lviv', 'Metaland', 'Zemsnariad'),
(20, 'portland', 'Metaland', 'Zemsnariad'),
(23, 'kursk', 'Metaland', 'Zemsnariad'),
(30, 'sumy', 'Metaland', 'Zemsnariad'),
(32, 'london', 'Metaland', 'Zemsnariad'),
(37, 'oslo', 'Metaland', 'Zemsnariad'),
(40, 'kharkiv', 'Metaland', 'Zemsnariad'),
(42, 'tokio', 'Metaland', 'Zemsnariad'),
(47, 'madrid', 'Metaland', 'Zemsnariad'),
(48, 'toronto', 'Metaland', 'Zemsnariad'),
(49, 'pert', 'Metaland', 'Zemsnariad'),
(55, '', 'Metaland', 'Zemsnariad'),
(58, 'stokholm', 'Metaland', 'Zemsnariad');

-- --------------------------------------------------------

--
-- Table structure for table `codes`
--

CREATE TABLE IF NOT EXISTS `codes` (
  `code_id` int(10) NOT NULL,
  `code` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3201 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `codes`
--

INSERT INTO `codes` (`code_id`, `code`) VALUES
(1, 'hurricane'),
(2, 'tropical storm'),
(3, 'tornado'),
(4, 'vsfd'),
(5, 'vsfd'),
(6, 'vsfd'),
(7, 'vsfd'),
(8, 'vsfd'),
(9, 'vsfd'),
(10, 'vsfd'),
(11, 'vsfd'),
(12, 'vsfd'),
(13, 'vsfd'),
(14, 'vsfd'),
(15, 'vsfd'),
(16, 'vsfd'),
(17, 'vsfd'),
(18, 'vsfd'),
(19, 'vsfd'),
(20, 'vsfd'),
(21, 'vsfd'),
(22, 'vsfd'),
(23, 'vsfd'),
(24, 'vsfd'),
(25, 'vsfd'),
(26, 'vsfd'),
(27, 'vsfd'),
(28, 'vsfd'),
(29, 'vsfd'),
(30, 'vsfd'),
(31, 'vsfd'),
(33, 'vsfd'),
(34, 'vsfd'),
(35, 'vsfd'),
(36, 'vsfd'),
(37, 'vsfd'),
(38, 'vsfd'),
(39, 'vsfd'),
(40, 'vsfd'),
(41, 'vsfd'),
(42, 'vsfd'),
(3200, 'vsfd');

-- --------------------------------------------------------

--
-- Stand-in structure for view `condition_full`
--
CREATE TABLE IF NOT EXISTS `condition_full` (
`condition_id` int(10) unsigned
,`condition_temperature` int(11)
,`Predicted` int(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `conditon`
--

CREATE TABLE IF NOT EXISTS `conditon` (
  `condition_id` int(10) unsigned NOT NULL,
  `condition_city_id` int(10) unsigned NOT NULL,
  `condition_date_id` int(10) unsigned NOT NULL,
  `condition_temperature` int(11) DEFAULT NULL,
  `condition_wind` int(11) DEFAULT NULL,
  `condition_humidity` int(11) DEFAULT NULL,
  `condition_code` int(10) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `conditon`
--

INSERT INTO `conditon` (`condition_id`, `condition_city_id`, `condition_date_id`, `condition_temperature`, `condition_wind`, `condition_humidity`, `condition_code`) VALUES
(37, 49, 34, 39, 7, 84, NULL),
(38, 49, 34, 39, 7, 84, NULL),
(39, 49, 34, 39, 7, 84, NULL),
(40, 49, 34, 39, 7, 84, NULL),
(41, 49, 34, 39, 7, 84, 26),
(42, 49, 38, 100, 5, 66, 22),
(43, 32, 40, 33, 7, 94, 26),
(44, 58, 41, 31, 11, 95, 27);

--
-- Triggers `conditon`
--
DELIMITER $$
CREATE TRIGGER `tg_cond_weather` AFTER INSERT ON `conditon`
 FOR EACH ROW BEGIN
  INSERT INTO `weather`(`weather_id`, `weather_city_id`, `weather_date_id`, `weather_prediction_id`, `weather_condition_id`) VALUES ('',new.condition_city_id,new.condition_date_id,NULL,new.condition_id);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `dates`
--

CREATE TABLE IF NOT EXISTS `dates` (
  `date_id` int(10) unsigned NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `dates`
--

INSERT INTO `dates` (`date_id`, `date`) VALUES
(9, '0000-00-00'),
(1, '2017-01-16'),
(2, '2017-01-17'),
(3, '2017-01-18'),
(4, '2017-01-19'),
(5, '2017-01-20'),
(34, '2017-01-21'),
(38, '2017-01-22'),
(39, '2017-01-23'),
(40, '2017-01-25'),
(41, '2017-01-26'),
(45, '2017-01-27');

-- --------------------------------------------------------

--
-- Table structure for table `logi_analize`
--

CREATE TABLE IF NOT EXISTS `logi_analize` (
  `logi_id` int(10) unsigned NOT NULL,
  `logi_city` int(11) NOT NULL,
  `logi_date` int(11) NOT NULL,
  `logi_diff_high` int(11) NOT NULL,
  `logi_diff_low` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `logi_analize`
--

INSERT INTO `logi_analize` (`logi_id`, `logi_city`, `logi_date`, `logi_diff_high`, `logi_diff_low`) VALUES
(5, 49, 38, 61, 69);

-- --------------------------------------------------------

--
-- Table structure for table `prediction`
--

CREATE TABLE IF NOT EXISTS `prediction` (
  `prediction_id` int(10) unsigned NOT NULL,
  `prediction_city_id` int(10) unsigned NOT NULL,
  `prediction_date_id` int(10) unsigned NOT NULL,
  `prediction_temperature_low` int(11) DEFAULT NULL,
  `prediction_temperature_high` int(11) DEFAULT NULL,
  `prediction_wind` int(11) DEFAULT NULL,
  `prediction_humidity` int(11) DEFAULT NULL,
  `prediction_code` int(10) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `prediction`
--

INSERT INTO `prediction` (`prediction_id`, `prediction_city_id`, `prediction_date_id`, `prediction_temperature_low`, `prediction_temperature_high`, `prediction_wind`, `prediction_humidity`, `prediction_code`) VALUES
(16, 1, 4, 15, 29, 0, 0, 26),
(17, 1, 4, 15, 29, 0, 0, 26),
(18, 15, 5, 5, 7, 0, 0, 20),
(19, 23, 9, 15, 29, 0, 0, 26),
(20, 23, 9, 15, 29, 0, 0, 26),
(21, 23, 9, 15, 29, 0, 0, 26),
(22, 23, 9, 15, 29, 0, 0, 26),
(23, 23, 9, 15, 29, 0, 0, 26),
(25, 30, 9, 27, 36, 0, 0, 26),
(26, 30, 9, 27, 36, 0, 0, 26),
(27, 30, 9, 11, 20, 0, 0, 27),
(28, 15, 38, 99, 100, 0, 0, 12),
(29, 49, 38, 31, 39, 0, 0, 26),
(30, 32, 41, 28, 39, 0, 0, 30),
(31, 32, 41, 28, 39, 0, 0, 30),
(32, 58, 45, 28, 37, 0, 0, 30);

--
-- Triggers `prediction`
--
DELIMITER $$
CREATE TRIGGER `tg_pred_weather` AFTER INSERT ON `prediction`
 FOR EACH ROW BEGIN
  INSERT INTO `weather`(`weather_id`, `weather_city_id`, `weather_date_id`, `weather_prediction_id`, `weather_condition_id`) VALUES ('',new.prediction_city_id,new.prediction_date_id,new.prediction_id,NULL);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `SalePerOrder`
--
CREATE TABLE IF NOT EXISTS `SalePerOrder` (
`condition_city_id` int(10) unsigned
,`condition_temperature` int(11)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `view_conditon`
--
CREATE TABLE IF NOT EXISTS `view_conditon` (
`condition_id` int(10) unsigned
,`condition_city_id` int(10) unsigned
,`condition_date_id` int(10) unsigned
,`condition_temperature` int(11)
,`condition_wind` int(11)
,`condition_humidity` int(11)
,`condition_code` int(10)
);

-- --------------------------------------------------------

--
-- Table structure for table `weather`
--

CREATE TABLE IF NOT EXISTS `weather` (
  `weather_id` int(10) unsigned NOT NULL,
  `weather_city_id` int(10) unsigned NOT NULL,
  `weather_date_id` int(10) unsigned NOT NULL,
  `weather_prediction_id` int(10) unsigned DEFAULT NULL,
  `weather_condition_id` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `weather`
--

INSERT INTO `weather` (`weather_id`, `weather_city_id`, `weather_date_id`, `weather_prediction_id`, `weather_condition_id`) VALUES
(13, 49, 34, NULL, 37),
(14, 49, 34, NULL, 38),
(15, 49, 34, NULL, 39),
(16, 49, 34, NULL, 40),
(17, 49, 34, NULL, 41),
(18, 49, 38, 29, NULL),
(19, 49, 38, NULL, 42),
(20, 32, 41, 30, NULL),
(21, 32, 40, NULL, 43),
(22, 32, 41, 31, NULL),
(23, 58, 41, NULL, 44),
(24, 58, 45, 32, NULL);

-- --------------------------------------------------------

--
-- Structure for view `condition_full`
--
DROP TABLE IF EXISTS `condition_full`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `condition_full` AS select `conditon`.`condition_id` AS `condition_id`,`conditon`.`condition_temperature` AS `condition_temperature`,`prediction`.`prediction_temperature_low` AS `Predicted` from (`conditon` join `prediction` on((`conditon`.`condition_city_id` = `prediction`.`prediction_city_id`)));

-- --------------------------------------------------------

--
-- Structure for view `SalePerOrder`
--
DROP TABLE IF EXISTS `SalePerOrder`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `SalePerOrder` AS select `conditon`.`condition_city_id` AS `condition_city_id`,`conditon`.`condition_temperature` AS `condition_temperature` from `conditon` group by `conditon`.`condition_city_id` order by `conditon`.`condition_temperature` desc;

-- --------------------------------------------------------

--
-- Structure for view `view_conditon`
--
DROP TABLE IF EXISTS `view_conditon`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_conditon` AS select `conditon`.`condition_id` AS `condition_id`,`conditon`.`condition_city_id` AS `condition_city_id`,`conditon`.`condition_date_id` AS `condition_date_id`,`conditon`.`condition_temperature` AS `condition_temperature`,`conditon`.`condition_wind` AS `condition_wind`,`conditon`.`condition_humidity` AS `condition_humidity`,`conditon`.`condition_code` AS `condition_code` from `conditon`;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
  ADD PRIMARY KEY (`city_id`),
  ADD UNIQUE KEY `city_name` (`city_name`);

--
-- Indexes for table `codes`
--
ALTER TABLE `codes`
  ADD PRIMARY KEY (`code_id`);

--
-- Indexes for table `conditon`
--
ALTER TABLE `conditon`
  ADD PRIMARY KEY (`condition_id`),
  ADD KEY `condition_code` (`condition_code`),
  ADD KEY `fk_conditon_dates` (`condition_date_id`),
  ADD KEY `condition_temperature` (`condition_temperature`),
  ADD KEY `condition_id` (`condition_id`),
  ADD KEY `condition_city_id` (`condition_city_id`),
  ADD KEY `condition_temperature_2` (`condition_temperature`),
  ADD KEY `condition_id_2` (`condition_id`);

--
-- Indexes for table `dates`
--
ALTER TABLE `dates`
  ADD PRIMARY KEY (`date_id`),
  ADD UNIQUE KEY `date` (`date`);

--
-- Indexes for table `logi_analize`
--
ALTER TABLE `logi_analize`
  ADD PRIMARY KEY (`logi_id`);

--
-- Indexes for table `prediction`
--
ALTER TABLE `prediction`
  ADD PRIMARY KEY (`prediction_id`),
  ADD UNIQUE KEY `prediction_id` (`prediction_id`),
  ADD KEY `fk_pred_code` (`prediction_code`),
  ADD KEY `fk_pred_date` (`prediction_date_id`),
  ADD KEY `prediction_city_id` (`prediction_city_id`),
  ADD KEY `prediction_temperature_low` (`prediction_temperature_low`),
  ADD KEY `prediction_temperature_high` (`prediction_temperature_high`),
  ADD KEY `prediction_temperature_low_2` (`prediction_temperature_low`),
  ADD KEY `prediction_temperature_low_3` (`prediction_temperature_low`);

--
-- Indexes for table `weather`
--
ALTER TABLE `weather`
  ADD PRIMARY KEY (`weather_id`),
  ADD KEY `fk_weather_cond` (`weather_condition_id`),
  ADD KEY `fk_weather_city` (`weather_city_id`),
  ADD KEY `fk_weather_date` (`weather_date_id`),
  ADD KEY `fk_weather_prediction` (`weather_prediction_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
  MODIFY `city_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=59;
--
-- AUTO_INCREMENT for table `codes`
--
ALTER TABLE `codes`
  MODIFY `code_id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3201;
--
-- AUTO_INCREMENT for table `conditon`
--
ALTER TABLE `conditon`
  MODIFY `condition_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT for table `dates`
--
ALTER TABLE `dates`
  MODIFY `date_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT for table `logi_analize`
--
ALTER TABLE `logi_analize`
  MODIFY `logi_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `prediction`
--
ALTER TABLE `prediction`
  MODIFY `prediction_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=33;
--
-- AUTO_INCREMENT for table `weather`
--
ALTER TABLE `weather`
  MODIFY `weather_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `conditon`
--
ALTER TABLE `conditon`
  ADD CONSTRAINT `fk_codes_conditon` FOREIGN KEY (`condition_code`) REFERENCES `codes` (`code_id`),
  ADD CONSTRAINT `fk_conditon_dates` FOREIGN KEY (`condition_date_id`) REFERENCES `dates` (`date_id`),
  ADD CONSTRAINT `fk_grade_id` FOREIGN KEY (`condition_city_id`) REFERENCES `cities` (`city_id`);

--
-- Constraints for table `prediction`
--
ALTER TABLE `prediction`
  ADD CONSTRAINT `fk_pred_city` FOREIGN KEY (`prediction_city_id`) REFERENCES `cities` (`city_id`),
  ADD CONSTRAINT `fk_pred_code` FOREIGN KEY (`prediction_code`) REFERENCES `codes` (`code_id`),
  ADD CONSTRAINT `fk_pred_date` FOREIGN KEY (`prediction_date_id`) REFERENCES `dates` (`date_id`);

--
-- Constraints for table `weather`
--
ALTER TABLE `weather`
  ADD CONSTRAINT `fk_weather_city` FOREIGN KEY (`weather_city_id`) REFERENCES `cities` (`city_id`),
  ADD CONSTRAINT `fk_weather_cond` FOREIGN KEY (`weather_condition_id`) REFERENCES `conditon` (`condition_id`),
  ADD CONSTRAINT `fk_weather_date` FOREIGN KEY (`weather_date_id`) REFERENCES `dates` (`date_id`),
  ADD CONSTRAINT `fk_weather_prediction` FOREIGN KEY (`weather_prediction_id`) REFERENCES `prediction` (`prediction_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
