# --- !Ups

CREATE TABLE product (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(4) NOT NULL DEFAULT '0',
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT '',
  `price` decimal(15,2) DEFAULT NULL,
  `date_available` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `date_added` datetime NOT NULL,
  `date_modified` datetime NOT NULL,
  PRIMARY KEY (`product_id`,`status`),
  KEY `status` (`status`,`date_added`)
) ENGINE=InnoDB AUTO_INCREMENT=74919 DEFAULT CHARSET=utf8
