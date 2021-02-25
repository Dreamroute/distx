DROP TABLE IF EXISTS `tx_message`;
DROP TABLE IF EXISTS `tx_message_del`;
DROP TABLE IF EXISTS `tx_message_commit`;

CREATE TABLE `tx_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `topic` varchar(255) NOT NULL COMMENT '投递到MQ的TOPIC',
  `tag` varchar(255) NOT NULL COMMENT '投递到MQ的TAG',
  `body` varchar(255) NOT NULL COMMENT '消息内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `faild_times` int(11) DEFAULT '0' COMMENT '失败次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tx_message_del` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `topic` varchar(255) NOT NULL COMMENT '投递到MQ的TOPIC',
  `tag` varchar(255) NOT NULL COMMENT '投递到MQ的TAG',
  `body` varchar(255) NOT NULL COMMENT '消息内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `faild_times` int(11) DEFAULT '0' COMMENT '失败次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tx_message_commit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `body` varchar(255) NOT NULL COMMENT '消息内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `message_table_id` bigint(20) NOT NULL COMMENT '消息表主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1503 DEFAULT CHARSET=utf8mb4;