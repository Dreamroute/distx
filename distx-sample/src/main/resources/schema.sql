DROP TABLE IF EXISTS `tx_message`;
DROP TABLE IF EXISTS `tx_message_del`;

CREATE TABLE `tx_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `topic` varchar(255) NOT NULL COMMENT '投递到MQ的TOPIC',
  `tag` varchar(255) NOT NULL COMMENT '投递到MQ的TAG',
  `body` varchar(255) NOT NULL COMMENT '消息内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tx_message_del` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `topic` varchar(255) NOT NULL COMMENT '投递到MQ的TOPIC',
  `tag` varchar(255) NOT NULL COMMENT '投递到MQ的TAG',
  `body` varchar(255) NOT NULL COMMENT '消息内容',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;