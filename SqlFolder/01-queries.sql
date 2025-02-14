CREATE DATABASE  IF NOT EXISTS `traini8database`;
USE `traini8database`;



-- Table structure for table `address`

--
DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `detailed_address` varchar(500) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50),
  `pincode` int,
  PRIMARY KEY (`id`)	
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


--
-- Table structure for table `training_center`



DROP TABLE IF EXISTS `training_center`;

CREATE TABLE `training_center` (
  `training_center_id` int NOT NULL AUTO_INCREMENT,
  `center_name` varchar(40) NOT NULL ,
  `center_code` varchar(12) NOT NULL UNIQUE,
  -- add the mapping for address by creating another table
  `address_id` int UNIQUE,
  `student_capacity` int ,
  -- add the mapping for courses offered by creating another table 
  `courses` int ,
  `created_on` int ,
  `contact_email` varchar(68) ,
  `contact_phone` varchar(10), 
  PRIMARY KEY (`training_center_id`),
  CONSTRAINT `training_center_address_fk`
  FOREIGN KEY(`address_id`)
  REFERENCES `address`(`id`)
  ON DELETE SET NULL ON UPDATE CASCADE
  
  
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

--
-- Table structure for table `courses_offered`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`(
 `id` int NOT NULL AUTO_INCREMENT,
 `training_center_id` int ,
 `course` varchar(50) ,
 PRIMARY KEY(`id`),
 UNIQUE KEY `training_center_course_key` (`training_center_id`,`course`),
 
 
 CONSTRAINT `training_center_course_ref`
 FOREIGN KEY(`training_center_id`) 
 REFERENCES `training_center`(`training_center_id`)
 
 ON DELETE NO ACTION ON UPDATE NO ACTION
)ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


