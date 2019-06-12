-- Create Schema

CREATE DATABASE `recipe_app` /*!40100 DEFAULT CHARACTER SET latin1 */;

-- Create Tables

CREATE TABLE `categories` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `directions` (
  `direction_id` int(11) NOT NULL AUTO_INCREMENT,
  `direction_details` varchar(300) NOT NULL,
  PRIMARY KEY (`direction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ingredients` (
  `ingredient_id` int(11) NOT NULL AUTO_INCREMENT,
  `ingredient_name` varchar(45) NOT NULL,
  PRIMARY KEY (`ingredient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `recipes` (
  `recipe_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_name` varchar(50) NOT NULL,
  `category_id` int(11) NOT NULL,
  `recipe_description` varchar(400) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`recipe_id`),
  KEY `fk_recipes_categories` (`category_id`),
  CONSTRAINT `fk_recipes_categories` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `tags` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(45) NOT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ratings` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) NOT NULL,
  `score` decimal(2,0) NOT NULL,
  PRIMARY KEY (`rating_id`),
  KEY `fk_ratings_recipes` (`recipe_id`),
  CONSTRAINT `fk_ratings_recipes` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `recipes_directions` (
  `rd_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) NOT NULL,
  `direction_id` int(11) NOT NULL,
  `order` int(11) DEFAULT NULL,
  PRIMARY KEY (`rd_id`),
  KEY `fk_rd_recipes` (`recipe_id`),
  KEY `fk_rd_directions` (`direction_id`),
  CONSTRAINT `fk_rd_directions` FOREIGN KEY (`direction_id`) REFERENCES `directions` (`direction_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_rd_recipes` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `recipes_ingredients` (
  `ri_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) NOT NULL,
  `ingredient_id` int(11) NOT NULL,
  `amount` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ri_id`),
  KEY `fk_ri_recipes` (`recipe_id`),
  KEY `fk_ri_ingredients` (`ingredient_id`),
  CONSTRAINT `fk_ri_ingredients` FOREIGN KEY (`ingredient_id`) REFERENCES `ingredients` (`ingredient_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_ri_recipes` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `recipes_tags` (
  `rt_id` int(11) NOT NULL AUTO_INCREMENT,
  `recipe_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`rt_id`),
  KEY `fk_rt_recipes` (`recipe_id`),
  KEY `fk_rt_tags` (`tag_id`),
  CONSTRAINT `fk_rt_recipes` FOREIGN KEY (`recipe_id`) REFERENCES `recipes` (`recipe_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_rt_tags` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`tag_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;