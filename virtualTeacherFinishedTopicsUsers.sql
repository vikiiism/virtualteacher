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

-- Dumping data for table virtuallearning.assignment_solution: 0 rows
/*!40000 ALTER TABLE `assignment_solution` DISABLE KEYS */;
/*!40000 ALTER TABLE `assignment_solution` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.assignment_task
CREATE TABLE IF NOT EXISTS `assignment_task` (
  `task_id` bigint(20) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.assignment_task: 0 rows
/*!40000 ALTER TABLE `assignment_task` DISABLE KEYS */;
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

-- Dumping data for table virtuallearning.courses: 0 rows
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
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

-- Dumping data for table virtuallearning.course_photos: 0 rows
/*!40000 ALTER TABLE `course_photos` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_photos` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.enrolled_courses
CREATE TABLE IF NOT EXISTS `enrolled_courses` (
  `user_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  KEY `FK35tccn1iailx1d8x2p389srka` (`course_id`),
  KEY `FKrvr5hj3vlgs3fp4nmqrvb4ypf` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.enrolled_courses: 0 rows
/*!40000 ALTER TABLE `enrolled_courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrolled_courses` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.hibernate_sequence: 7 rows
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(10),
	(10),
	(10),
	(10),
	(10),
	(10),
	(10);
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

-- Dumping data for table virtuallearning.lectures: 0 rows
/*!40000 ALTER TABLE `lectures` DISABLE KEYS */;
/*!40000 ALTER TABLE `lectures` ENABLE KEYS */;

-- Dumping structure for table virtuallearning.lecture_video
CREATE TABLE IF NOT EXISTS `lecture_video` (
  `video_id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `watched` bit(1) DEFAULT b'0',
  PRIMARY KEY (`video_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- Dumping data for table virtuallearning.lecture_video: 0 rows
/*!40000 ALTER TABLE `lecture_video` DISABLE KEYS */;
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

-- Dumping data for table virtuallearning.photos: 9 rows
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
	(9, 'signup-image.jpg', 'C:\\Users\\megat0n\\Desktop\\VirtualTeacher13082019Last\\virtuallearning\\photos\\', 9);
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
	(13, 'Teaching and Academics');
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

-- Dumping data for table virtuallearning.user_video_already_watched: 0 rows
/*!40000 ALTER TABLE `user_video_already_watched` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_video_already_watched` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
