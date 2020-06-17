-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.3.16-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for virtuallearning
CREATE DATABASE IF NOT EXISTS `virtuallearning` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `virtuallearning`;

-- Dumping structure for table virtuallearning.assignment_solution
CREATE TABLE IF NOT EXISTS `assignment_solution` (
  `solution_id` bigint(20) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `grade` int(11) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `lecture_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`solution_id`),
  KEY `FK18wok3yk5ph68liqxhtn0tx78` (`lecture_id`),
  KEY `FKp5f2worxfmayv2rkcy1yxor94` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.assignment_solution: 1 rows
/*!40000 ALTER TABLE `assignment_solution` DISABLE KEYS */;
INSERT INTO `assignment_solution` (`solution_id`, `fileName`, `grade`, `path`, `lecture_id`, `user_id`) VALUES
	(27, 'Installing JavaTaskPdf04.pdf', 0, 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseSolutions\\', 14, 3);
/*!40000 ALTER TABLE `assignment_solution` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.assignment_task
CREATE TABLE IF NOT EXISTS `assignment_task` (
  `task_id` bigint(20) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.assignment_task: 25 rows
/*!40000 ALTER TABLE `assignment_task` DISABLE KEYS */;
INSERT INTO `assignment_task` (`task_id`, `fileName`, `path`) VALUES
	(12, 'Installing JavaTask01.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(15, 'Running our First Java ProgramTask02.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(18, 'Downloading EclipseTask03.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(21, 'VariablesTask04.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(24, 'Getting User InputTask05.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(30, 'Lecture 01TaskPdf12.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(33, 'Lecture 02TaskPdf08.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(36, 'Lecture 03TaskPdf13.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(39, 'Lecture 04TaskPdf15.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(42, 'Lecture 05TaskPdf09.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(47, 'Lecture 01TaskPdf15.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(50, 'Lecture 02TaskPdf05.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(53, 'Lecture 03Task12.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(58, 'Lecture 01Task11.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(61, 'Lecture 02TaskPdf05.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(66, 'Lecture 01TaskPdf06.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(69, 'Lecture 02TaskPdf13.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(74, 'Lecture 01Task13.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(77, 'Lecture 02TaskPdf03.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(82, 'Lecture 01TaskPdf13.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(85, 'Lecture 02Task13.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(90, 'Lecture 01Task11.txt', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(93, 'Lecture 02TaskPdf11.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(98, 'Quadratic functionsTaskPdf12.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\'),
	(101, 'Boolean AlgebraTaskPdf05.pdf', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\courseTasks\\');
/*!40000 ALTER TABLE `assignment_task` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.completed_courses
CREATE TABLE IF NOT EXISTS `completed_courses` (
  `user_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  KEY `FK1w529e7ynys2pgh5mybqe0hq6` (`course_id`),
  KEY `FKn9u0s779u1pu52tkti0afbelu` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.completed_courses: 0 rows
/*!40000 ALTER TABLE `completed_courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `completed_courses` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.courses
CREATE TABLE IF NOT EXISTS `courses` (
  `course_id` bigint(20) NOT NULL,
  `avg_rating` double NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `enabled` bit(1) DEFAULT b'0',
  `grade` double DEFAULT 0,
  `title` varchar(255) NOT NULL,
  `total_votes` int(11) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `FK51k53m6m5gi9n91fnlxkxgpmv` (`user_id`),
  KEY `FKlvrhaosps2wkj26qi1nmomd4p` (`topic_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.courses: 9 rows
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` (`course_id`, `avg_rating`, `description`, `enabled`, `grade`, `title`, `total_votes`, `user_id`, `topic_id`) VALUES
	(11, 0, 'What you\'ll learn\r\nYou will Learn Java the MODERN WAY - Step By Step\r\nYou will Understand the BEST PRACTICES in Writing High Quality Java Code\r\nYou will Solve a Wide Range of Hands-on Programming EXERCISES with Java\r\nYou will Learn to Write AWESOME Object Oriented Programs with Java\r\nYou will Acquire ALL the SKILLS to demonstrate an EXPERTISE with Java Programming in Your Job Interviews\r\nYou will learn ADVANCED Object Oriented Programming Concepts - Abstraction, Inheritance, Encapsulation and Polymorphism\r\nYou will learn the Basics of Object Oriented Programming - Interfaces, Inheritance, Abstract Class and Constructors\r\nYou will learn the Basics of Programming - variables, choosing a data type, conditional execution, loops, writing great methods, breaking down problems into sub problems and implementing great Exception Handling\r\nYou will learn Basics of Functional Programming with Java\r\nYou will learn about a wide variety of Java Collections - List, Map, Set and Queue Interfaces', b'1', 50, 'Java for Beginners ', 0, 3, 1),
	(29, 0, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', b'1', 60, 'Android For Beginners', 0, 3, 1),
	(46, 0, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', b'1', 55, 'Python for Beginners', 0, 3, 1),
	(57, 0, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', b'1', 50, 'Boxing for Beginners', 0, 4, 11),
	(65, 0, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', b'1', 60, 'Guitar for Beginners', 0, 4, 12),
	(73, 0, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\r\n', b'1', 60, 'HTML5 + CSS3', 0, 4, 1),
	(81, 0, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\r\n', b'1', 50, 'Java for Intermediate', 0, 3, 1),
	(89, 0, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\r\n', b'1', 60, 'Microsoft Office', 0, 3, 5),
	(97, 0, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.\r\n', b'1', 70, 'Mathematics for University', 0, 3, 13);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.course_photos
CREATE TABLE IF NOT EXISTS `course_photos` (
  `course_photos_id` bigint(20) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`course_photos_id`),
  KEY `FK6i1jr04vidl5r0b2bm0ivfmef` (`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.course_photos: 9 rows
/*!40000 ALTER TABLE `course_photos` DISABLE KEYS */;
INSERT INTO `course_photos` (`course_photos_id`, `fileName`, `path`, `course_id`) VALUES
	(10, 'Java Programming for Complete Beginners coursePhoto06.png', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\coursePhotos\\', 11),
	(28, 'Android For BeginnerscoursePhoto09.png', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\coursePhotos\\', 29),
	(45, 'Python for BeginnerscoursePhoto08.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\coursePhotos\\', 46),
	(56, 'Boxing for BeginnerscoursePhoto10.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\coursePhotos\\', 57),
	(64, 'Guitar for beginnerscoursePhoto12.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\coursePhotos\\', 65),
	(72, 'HTML5 + CSS3coursePhoto13.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\coursePhotos\\', 73),
	(80, 'Java for IntermediatecoursePhoto010.png', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\coursePhotos\\', 81),
	(88, 'Microsoft OfficecoursePhoto14.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\coursePhotos\\', 89),
	(96, 'Mathematics for unicoursePhoto15.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\coursePhotos\\', 97);
/*!40000 ALTER TABLE `course_photos` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.enrolled_courses
CREATE TABLE IF NOT EXISTS `enrolled_courses` (
  `user_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  KEY `FK35tccn1iailx1d8x2p389srka` (`course_id`),
  KEY `FKrvr5hj3vlgs3fp4nmqrvb4ypf` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.enrolled_courses: 1 rows
/*!40000 ALTER TABLE `enrolled_courses` DISABLE KEYS */;
INSERT INTO `enrolled_courses` (`user_id`, `course_id`) VALUES
	(3, 11);
/*!40000 ALTER TABLE `enrolled_courses` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.hibernate_sequence: 7 rows
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(112),
	(112),
	(112),
	(112),
	(112),
	(112),
	(112);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.lectures
CREATE TABLE IF NOT EXISTS `lectures` (
  `id` bigint(20) NOT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `video_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhq1gl1uyj0ognfv01otvuy04m` (`task_id`),
  KEY `FKsj4m8ipr4qnehoyxk7kbu3ide` (`course_id`),
  KEY `FKb4x5n3wh5e8hj2exjn2r2ft0j` (`video_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.lectures: 25 rows
/*!40000 ALTER TABLE `lectures` DISABLE KEYS */;
INSERT INTO `lectures` (`id`, `description`, `title`, `task_id`, `course_id`, `video_id`) VALUES
	(14, 'It is recommended, before you proceed with online installation you may want to disable your Internet firewall. In some cases the default firewall settings are set to reject all automatic or online installations such as the Java online installation. If the firewall is not configured appropriately it may stall the download/install operation of Java under certain conditions. Refer to your specific Internet firewall manual for instructions on how to disable your Internet Firewall.', 'Installing Java', 12, 11, 13),
	(17, 'The name of the class defined by the program is HelloWorld, which is same as name of file(HelloWorld.java). This is not a coincidence. In Java, all codes must reside inside a class and there is at most one public class which contain main() method. By convention, the name of the main class(class which contain main method) should match the name of the file that holds the program.', 'Running our First Java Program', 15, 11, 16),
	(20, 'You may want to print these instructions before proceeding, so that you can refer to them while downloading and installing Eclipse. Or, just keep this document in your browser. You should read each step completely before performing the action that it describes. This document shows downloading and installing Eclipse (Oxygen) on Windows 7 in Summer 2017. You should download and install the latest version of Eclipse. The current latest (as of Summer 2017) is Eclipse (Oxygen).', 'Downloading Eclipse', 18, 11, 19),
	(23, 'A variable is a container which holds the value while the java program is executed. A variable is assigned with a datatype.  Variable is a name of memory location. There are three types of variables in java: local, instance and static.  There are two types of data types in java: primitive and non-primitive.', 'Variables', 21, 11, 22),
	(26, 'The Scanner class is used to get user input, and it is found in the java.util package. To use the Scanner class, create an object of the class and use any of the available methods found in the Scanner class documentation. In our example, we will use the nextLine() method, which is used to read Strings', 'Getting User Input', 24, 11, 25),
	(32, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 01', 30, 29, 31),
	(35, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 02', 33, 29, 34),
	(38, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 03', 36, 29, 37),
	(41, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 04', 39, 29, 40),
	(44, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 05', 42, 29, 43),
	(49, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 01', 47, 46, 48),
	(52, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 02', 50, 46, 51),
	(55, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 03', 53, 46, 54),
	(60, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 01', 58, 57, 59),
	(63, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 02', 61, 57, 62),
	(68, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 01', 66, 65, 67),
	(71, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 02', 69, 65, 70),
	(76, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 01', 74, 73, 75),
	(79, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 02', 77, 73, 78),
	(84, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 01', 82, 81, 83),
	(87, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 02', 85, 81, 86),
	(92, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 01', 90, 89, 91),
	(95, 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry\'s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.', 'Lecture 02', 93, 89, 94),
	(100, 'In algebra, a quadratic function, a quadratic polynomial, a polynomial of degree 2, or simply a quadratic, is a polynomial function with one or more variables in which the highest-degree term is of the second degree. For example, a quadratic function in three variables x, y, and z contains exclusively terms x2, y2, z2, xy, xz, yz, x, y, z, and a constant.', 'Quadratic functions', 98, 97, 99),
	(103, 'In mathematics and mathematical logic, Boolean algebra is the branch of algebra in which the values of the variables are the truth values true and false, usually denoted 1 and 0 respectively. Instead of elementary algebra where the values of the variables are numbers, and the prime operations are addition and multiplication, the main operations of Boolean algebra are the conjunction and denoted as ?, the disjunction or denoted as ?, and the negation not denoted as ¬. It is thus a formalism for describing logical relations in the same way that elementary algebra describes numeric relations.', 'Boolean Algebra', 101, 97, 102);
/*!40000 ALTER TABLE `lectures` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.lecture_video
CREATE TABLE IF NOT EXISTS `lecture_video` (
  `video_id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `watched` bit(1) DEFAULT b'0',
  PRIMARY KEY (`video_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.lecture_video: 25 rows
/*!40000 ALTER TABLE `lecture_video` DISABLE KEYS */;
INSERT INTO `lecture_video` (`video_id`, `code`, `title`, `watched`) VALUES
	(13, 'Hl-zzrqQoSE', NULL, b'0'),
	(16, '5u8rFbpdvds', NULL, b'0'),
	(19, 'CE8UIbb_4iM', NULL, b'0'),
	(22, 'gtQJXzi3Yns', NULL, b'0'),
	(25, '5DdacOkrTgo', NULL, b'0'),
	(31, 'QAbQgLGKd3Y', NULL, b'0'),
	(34, 'zEsDwzjPJ5c', NULL, b'0'),
	(37, 'r4oIez0sfvY', NULL, b'0'),
	(40, 'qKRWC3Q8wRw', NULL, b'0'),
	(43, '-pdTqBq2TFQ', NULL, b'0'),
	(48, 'QXeEoD0pB3E', NULL, b'0'),
	(51, 'hEgO047GxaQ', NULL, b'0'),
	(54, 'mbryl4MZJms', NULL, b'0'),
	(59, '44HcoLNwRQg', NULL, b'0'),
	(62, 'Hl05kFaPq14', NULL, b'0'),
	(67, '7silbMA9UME', NULL, b'0'),
	(70, '8wdYqdV6WLg', NULL, b'0'),
	(75, 'y3UH2gAhwPI', NULL, b'0'),
	(78, 'gqOEoUR5RHg', NULL, b'0'),
	(83, 'vW53w7me4AE', NULL, b'0'),
	(86, 'Qi09pWsc7nA', NULL, b'0'),
	(91, '8lXerL3DHRw', NULL, b'0'),
	(94, 'TxLuuNprjXg', NULL, b'0'),
	(99, 'KEuNUgIiIyI', NULL, b'0'),
	(102, 'oUAJnmW8BMQ', NULL, b'0');
/*!40000 ALTER TABLE `lecture_video` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.photos
CREATE TABLE IF NOT EXISTS `photos` (
  `photo_id` bigint(20) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`photo_id`),
  KEY `FKnm381g1ktlpsorbtpco2ljhuv` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.photos: 17 rows
/*!40000 ALTER TABLE `photos` DISABLE KEYS */;
INSERT INTO `photos` (`photo_id`, `fileName`, `path`, `user_id`) VALUES
	(1, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 1),
	(2, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 2),
	(3, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 3),
	(4, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 4),
	(5, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 5),
	(6, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 6),
	(7, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 7),
	(8, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 8),
	(9, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 9),
	(104, 'vickitoprofilePic04.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 3),
	(105, 'pesho@gmail.comprofilePic08.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 4),
	(106, 'megat0nprofilePic11.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 2),
	(107, 'rootprofilePic03.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 1),
	(108, 'misho@gmail.comprofilePic06.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 5),
	(109, 'gogo@gmail.comprofilePic02.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 8),
	(110, 'pepi@gmail.comprofilePic08.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 9),
	(111, 'mimi@gmail.comprofilePic16.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 7);
/*!40000 ALTER TABLE `photos` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.rated_course
CREATE TABLE IF NOT EXISTS `rated_course` (
  `course_rating_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_rating` int(11) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`course_rating_id`),
  KEY `FKg1dr07c2727axpeuenf6w00cf` (`course_id`),
  KEY `FKdgqaxnkx89ncnhrhqv2y35loo` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.rated_course: 0 rows
/*!40000 ALTER TABLE `rated_course` DISABLE KEYS */;
/*!40000 ALTER TABLE `rated_course` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.roles: 9 rows
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`role_id`, `role_name`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_ADMIN'),
	(3, 'ROLE_TEACHER'),
	(4, 'ROLE_TEACHER'),
	(5, 'ROLE_TEACHER'),
	(6, 'ROLE_STUDENT'),
	(7, 'ROLE_STUDENT'),
	(8, 'ROLE_STUDENT'),
	(9, 'ROLE_STUDENT');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.topics
CREATE TABLE IF NOT EXISTS `topics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7tuhnscjpohbffmp7btit1uff` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.topics: 13 rows
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` (`id`, `name`) VALUES
	(1, 'Development'),
	(2, 'Business'),
	(3, 'Finance & Accounting'),
	(4, 'IT and Software'),
	(5, 'Office Productivity'),
	(6, 'Personal Development'),
	(7, 'Design'),
	(8, 'Marketing'),
	(9, 'Lifestyle'),
	(10, 'Photography'),
	(11, 'Health and Fitness'),
	(12, 'Music'),
	(13, 'Academics');
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `hasRequested` bit(1) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.users: 9 rows
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `email`, `enabled`, `first_name`, `hasRequested`, `last_name`, `password`, `username`) VALUES
	(1, 'root@gmail.com', b'1', 'root', b'0', 'root', '$2a$10$V9I3a6vzOEm/A4h6VARbBueQSTQ53sqKriSIcIfE2Evczbl3AKka6', 'root'),
	(2, 'ivsmile96@gmail.com', b'1', 'Ivan', b'0', 'Simeonov', '$2a$10$/URE.2Die7iKy00RZydJIeU3ts2abtxE32brDZh/wadqqNUFzH8GG', 'megat0n'),
	(3, 'vikiiism@mail.bg', b'1', 'Vicky', b'0', 'Georgieva', '$2a$10$UyX0w7bKEESEg73uzTGiVO/Ip7za1J5RCGgyNZSQy2NSKbqsQBqzS', 'vickito'),
	(4, 'pesho@gmail.com', b'1', 'Pesho', b'0', 'Peshov', '$2a$10$8m/jnbRwOCryTM5idDfyMunW/F3IW5eYBcZhYYANrXsTk4rlhyhGW', 'pesho@gmail.com'),
	(5, 'misho@gmail.com', b'1', 'Mihail', b'0', 'Mihailov', '$2a$10$OmwlCMxM17suXUPaahYEweYd8pC6K1P5SXy2kWkfc4yPs5S/XS0JO', 'misho@gmail.com'),
	(6, 'didi@gmail.com', b'1', 'Didi', b'0', 'Didieva', '$2a$10$8FwCC71KXCLbzezTyvNY2eufJxSPQuexb/Cu0vyO2UE1aakuN3N7q', 'didi@gmail.com'),
	(7, 'mimi@gmail.com', b'1', 'Mariq', b'0', 'Markova', '$2a$10$S58Img67RqhbS02yomzbSOtU9jiN26tmjz2UJRjEhwA.6kOl5uE.q', 'mimi@gmail.com'),
	(8, 'gogo@gmail.com', b'1', 'Georgi', b'0', 'Georgiev', '$2a$10$BX5coyxIN8MU1T6beBnFOuQN3zGJ8OBOAdkclC2txwTIvBOiKHInO', 'gogo@gmail.com'),
	(9, 'pepi@gmail.com', b'1', 'Petar', b'0', 'Petrov', '$2a$10$oOAjdpjTNjQG/U/YsxEfq.8kKy4iXS4WTwutYzRAxbsu38h31y/za', 'pepi@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  KEY `FKj345gk1bovqvfame88rcx7yyx` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.user_role: 9 rows
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 6),
	(7, 7),
	(8, 8),
	(9, 9);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.user_video_already_watched
CREATE TABLE IF NOT EXISTS `user_video_already_watched` (
  `user_id` bigint(20) NOT NULL,
  `video_id` bigint(20) NOT NULL,
  KEY `FK3mbdjgtjpaw0yxsxvkl7i2go2` (`video_id`),
  KEY `FKnw1fkepu78thca8kq5wmcrpe6` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.user_video_already_watched: 1 rows
/*!40000 ALTER TABLE `user_video_already_watched` DISABLE KEYS */;
INSERT INTO `user_video_already_watched` (`user_id`, `video_id`) VALUES
	(3, 13);
/*!40000 ALTER TABLE `user_video_already_watched` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
