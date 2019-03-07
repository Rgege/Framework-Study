CREATE TABLE `tbl_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `age` int(4) DEFAULT NULL,
  `creat_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT '0' COMMENT '状态 0：正常 1：失效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;