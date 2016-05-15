/*
Navicat MySQL Data Transfer

Source Server         : l1
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : l1

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-05-16 03:57:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_billstat
-- ----------------------------
DROP TABLE IF EXISTS `tb_billstat`;
CREATE TABLE `tb_billstat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_billstat
-- ----------------------------
INSERT INTO `tb_billstat` VALUES ('1', '待审批', '待审批');
INSERT INTO `tb_billstat` VALUES ('2', '已完成', '已完成');

-- ----------------------------
-- Table structure for tb_brand
-- ----------------------------
DROP TABLE IF EXISTS `tb_brand`;
CREATE TABLE `tb_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_brand
-- ----------------------------
INSERT INTO `tb_brand` VALUES ('4', 'Nike', 'Nike', '使用', null, '2016-04-12 14:19:04', null, '2016-04-12 14:41:24');
INSERT INTO `tb_brand` VALUES ('5', 'Adidas', 'Adi', '使用', null, '2016-04-12 14:41:45', null, null);
INSERT INTO `tb_brand` VALUES ('6', '123', '123', '停用', null, '2016-05-08 13:24:00', null, '2016-05-08 13:24:08');
INSERT INTO `tb_brand` VALUES ('7', 'ewr', 'werw', '使用', null, '2016-05-15 22:46:51', null, '2016-05-15 22:46:59');

-- ----------------------------
-- Table structure for tb_color
-- ----------------------------
DROP TABLE IF EXISTS `tb_color`;
CREATE TABLE `tb_color` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_color
-- ----------------------------
INSERT INTO `tb_color` VALUES ('4', '红色', 'red', '停用', null, '2016-04-12 12:38:49', null, null, null);
INSERT INTO `tb_color` VALUES ('5', '蓝色', 'blue', '停用', null, '2016-04-12 12:38:49', null, null, null);
INSERT INTO `tb_color` VALUES ('6', '白', 'white', '使用', null, '2016-04-12 12:45:01', null, '2016-04-14 00:00:30', '白');
INSERT INTO `tb_color` VALUES ('7', '绿色', 'green', '使用', null, '2016-04-12 12:54:49', null, '2016-04-14 00:00:44', '绿色');
INSERT INTO `tb_color` VALUES ('8', '123', '123', '使用', null, '2016-05-08 09:30:44', null, '2016-05-08 11:22:45', '123');
INSERT INTO `tb_color` VALUES ('9', '123', '123', '停用', null, '2016-05-08 11:22:36', null, null, '123');

-- ----------------------------
-- Table structure for tb_custom_props
-- ----------------------------
DROP TABLE IF EXISTS `tb_custom_props`;
CREATE TABLE `tb_custom_props` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `propName` varchar(127) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_custom_props
-- ----------------------------
INSERT INTO `tb_custom_props` VALUES ('1', '场景');
INSERT INTO `tb_custom_props` VALUES ('2', '风格');
INSERT INTO `tb_custom_props` VALUES ('3', '材质');

-- ----------------------------
-- Table structure for tb_custom_props_vals
-- ----------------------------
DROP TABLE IF EXISTS `tb_custom_props_vals`;
CREATE TABLE `tb_custom_props_vals` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `propId` bigint(20) NOT NULL,
  `val` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_custom_props_vals
-- ----------------------------
INSERT INTO `tb_custom_props_vals` VALUES ('1', '1', '场景1');
INSERT INTO `tb_custom_props_vals` VALUES ('2', '1', '场景2');
INSERT INTO `tb_custom_props_vals` VALUES ('4', '2', '风格1');
INSERT INTO `tb_custom_props_vals` VALUES ('5', '2', '风格2');
INSERT INTO `tb_custom_props_vals` VALUES ('6', '2', '风格3');
INSERT INTO `tb_custom_props_vals` VALUES ('7', '3', '材质1');
INSERT INTO `tb_custom_props_vals` VALUES ('8', '3', '材质3');
INSERT INTO `tb_custom_props_vals` VALUES ('9', '3', '材质3');
INSERT INTO `tb_custom_props_vals` VALUES ('10', '3', '123');
INSERT INTO `tb_custom_props_vals` VALUES ('11', '3', 'fdsfds');
INSERT INTO `tb_custom_props_vals` VALUES ('12', '3', 'dfsdf');
INSERT INTO `tb_custom_props_vals` VALUES ('23', '1', 'werew');
INSERT INTO `tb_custom_props_vals` VALUES ('24', '1', '场景4');

-- ----------------------------
-- Table structure for tb_dic
-- ----------------------------
DROP TABLE IF EXISTS `tb_dic`;
CREATE TABLE `tb_dic` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `keyWord` varchar(127) NOT NULL,
  `textField` varchar(127) NOT NULL,
  `valueField` int(10) NOT NULL,
  `ord` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dic
-- ----------------------------
INSERT INTO `tb_dic` VALUES ('1', 'warehouseState', '停用', '0', '0');
INSERT INTO `tb_dic` VALUES ('2', 'warehouseState', '使用', '1', '1');
INSERT INTO `tb_dic` VALUES ('3', 'rentStatus', '已取消', '-1', '1');
INSERT INTO `tb_dic` VALUES ('4', 'rentStatus', '已下单,未支付', '0', '2');
INSERT INTO `tb_dic` VALUES ('5', 'rentStatus', '已支付', '1', '3');
INSERT INTO `tb_dic` VALUES ('6', 'rentStatus', '已发货', '2', '4');
INSERT INTO `tb_dic` VALUES ('7', 'rentStatus', '已收货,待退还', '3', '5');
INSERT INTO `tb_dic` VALUES ('8', 'rentStatus', '已退还,待收货', '4', '6');
INSERT INTO `tb_dic` VALUES ('9', 'rentStatus', '已完成', '5', '7');
INSERT INTO `tb_dic` VALUES ('10', 'rentBillStatus', '未审核', '0', '0');
INSERT INTO `tb_dic` VALUES ('11', 'rentBillStatus', '已审核', '1', '1');
INSERT INTO `tb_dic` VALUES ('12', 'rentBillStatus', '已归还', '2', '2');
INSERT INTO `tb_dic` VALUES ('13', 'itemStatus', '停用', '0', '0');
INSERT INTO `tb_dic` VALUES ('14', 'itemStatus', '使用', '1', '1');
INSERT INTO `tb_dic` VALUES ('15', 'sizeStatus', '停用', '0', '0');
INSERT INTO `tb_dic` VALUES ('16', 'sizeStatus', '使用', '1', '1');
INSERT INTO `tb_dic` VALUES ('17', 'purchaseStatus', '未审核', '0', '0');
INSERT INTO `tb_dic` VALUES ('18', 'purchaseStatus', '已审核', '1', '1');
INSERT INTO `tb_dic` VALUES ('19', 'reversionStatus', '完整', '0', '0');
INSERT INTO `tb_dic` VALUES ('20', 'reversionStatus', '破损', '1', '1');
INSERT INTO `tb_dic` VALUES ('21', 'reversionStatus', '丢失', '2', '2');
INSERT INTO `tb_dic` VALUES ('22', 'reversionStatus', '购买', '3', '3');
INSERT INTO `tb_dic` VALUES ('23', 'stockOutStatus', '未审核', '0', '0');
INSERT INTO `tb_dic` VALUES ('24', 'stockOutStatus', '已审核', '1', '1');
INSERT INTO `tb_dic` VALUES ('25', 'stockOutStatus', '已完成', '2', '2');
INSERT INTO `tb_dic` VALUES ('26', 'stockInStatus', '未审核', '0', '0');
INSERT INTO `tb_dic` VALUES ('27', 'stockInStatus', '已审核', '1', '1');
INSERT INTO `tb_dic` VALUES ('28', 'stockInStatus', '已完成', '2', '2');
INSERT INTO `tb_dic` VALUES ('29', 'kctzStatus', '未审核', '0', '0');
INSERT INTO `tb_dic` VALUES ('30', 'kctzStatus', '已审核', '1', '1');

-- ----------------------------
-- Table structure for tb_inventory
-- ----------------------------
DROP TABLE IF EXISTS `tb_inventory`;
CREATE TABLE `tb_inventory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `skuId` bigint(20) DEFAULT NULL,
  `colorId` bigint(20) DEFAULT NULL,
  `sizeId` bigint(20) DEFAULT NULL,
  `style` varchar(100) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `locationId` bigint(20) DEFAULT NULL,
  `stat` bigint(20) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_inventory
-- ----------------------------
INSERT INTO `tb_inventory` VALUES ('1', '53', '6', '12', '商品1', '1', '1', null, null, null, null, null, null);
INSERT INTO `tb_inventory` VALUES ('2', '54', '6', '13', '商品1', '2', '1', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` tinyint(1) DEFAULT '1',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `brandId` bigint(20) DEFAULT NULL,
  `color` varchar(250) DEFAULT NULL,
  `primaryCategoryId` bigint(20) DEFAULT NULL,
  `minorCategoryId` bigint(20) DEFAULT NULL,
  `rental1` decimal(12,2) DEFAULT NULL,
  `rental2` decimal(12,2) DEFAULT NULL,
  `deposit` decimal(12,2) DEFAULT NULL,
  `style` varchar(50) DEFAULT NULL,
  `size` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES ('16', '商品1', '123', '1', null, '2016-05-15 23:08:58', null, null, '4', '6,7,8', '4', '4', '123.00', '123.00', '123.00', '123', '12,13');

-- ----------------------------
-- Table structure for tb_minor_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_minor_category`;
CREATE TABLE `tb_minor_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_minor_category
-- ----------------------------
INSERT INTO `tb_minor_category` VALUES ('4', '牛仔裤11', 'niuzai', '使用', null, '2016-04-13 21:35:21', null, null);
INSERT INTO `tb_minor_category` VALUES ('5', '西裤22', 'xiku', '使用', null, '2016-04-13 21:35:40', null, '2016-05-08 11:31:54');

-- ----------------------------
-- Table structure for tb_primary_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_primary_category`;
CREATE TABLE `tb_primary_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `sizeid` bigint(20) DEFAULT NULL,
  `sizename` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_primary_category
-- ----------------------------
INSERT INTO `tb_primary_category` VALUES ('4', '西裤34234', '001', '使用', null, '2016-04-12 14:54:51', null, '2016-05-08 09:04:02', null, null);
INSERT INTO `tb_primary_category` VALUES ('5', '牛仔裤', '002', '使用', null, '2016-04-12 14:55:12', null, '2016-04-12 15:01:58', null, null);
INSERT INTO `tb_primary_category` VALUES ('6', 'ddd', 'ddd', '停用', null, '2016-05-08 08:58:41', null, '2016-05-15 22:47:12', null, null);

-- ----------------------------
-- Table structure for tb_purchase
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase`;
CREATE TABLE `tb_purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `supplierId` bigint(20) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `stat` int(11) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `warehouseName` varchar(50) DEFAULT NULL,
  `supplierName` varchar(50) DEFAULT NULL,
  `statName` varchar(50) DEFAULT NULL,
  `billDate` date DEFAULT NULL,
  `total` decimal(12,2) DEFAULT NULL,
  `makerId` bigint(20) DEFAULT NULL,
  `makerName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purchase
-- ----------------------------
INSERT INTO `tb_purchase` VALUES ('7', 'CG20160515000004', '15', '1', '0', null, '2016-05-15 23:18:34', null, null, null, null, null, null, '369.00', null, null);

-- ----------------------------
-- Table structure for tb_purchasedtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchasedtl`;
CREATE TABLE `tb_purchasedtl` (
  `dtlid` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `itemName` varchar(50) DEFAULT NULL,
  `itemPrice` decimal(12,2) DEFAULT NULL,
  `itemAmount` int(11) DEFAULT NULL,
  `itemTotal` int(11) DEFAULT NULL,
  PRIMARY KEY (`dtlid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purchasedtl
-- ----------------------------
INSERT INTO `tb_purchasedtl` VALUES ('5', '7', '53', '商品1', '123.00', '1', '123');
INSERT INTO `tb_purchasedtl` VALUES ('6', '7', '54', '商品1', '123.00', '2', '246');

-- ----------------------------
-- Table structure for tb_rent
-- ----------------------------
DROP TABLE IF EXISTS `tb_rent`;
CREATE TABLE `tb_rent` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `customerName` varchar(50) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `stat` int(11) DEFAULT '0',
  `billStat` tinyint(1) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `rentDay` int(11) DEFAULT NULL,
  `rentMoney` decimal(12,2) DEFAULT NULL,
  `repoMoney` decimal(12,2) DEFAULT NULL,
  `customerPhone` varchar(20) DEFAULT NULL,
  `customerAddr` varchar(100) DEFAULT NULL,
  `customerCard` varchar(50) DEFAULT NULL,
  `supplierId` varchar(50) DEFAULT NULL,
  `expressBillNo` varchar(50) DEFAULT NULL,
  `returnBillNo` varchar(50) DEFAULT NULL,
  `beginDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_rent
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rentdtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_rentdtl`;
CREATE TABLE `tb_rentdtl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rentId` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `itemPrice` decimal(12,2) DEFAULT NULL,
  `itemAmount` int(11) DEFAULT NULL,
  `itemRepo` decimal(12,2) DEFAULT NULL,
  `itemRent` decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_rentdtl
-- ----------------------------
INSERT INTO `tb_rentdtl` VALUES ('80', '114', '28', '123.00', '123', '123.00', '15129.00');

-- ----------------------------
-- Table structure for tb_reversion
-- ----------------------------
DROP TABLE IF EXISTS `tb_reversion`;
CREATE TABLE `tb_reversion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `billStat` int(20) DEFAULT NULL,
  `rentBillId` int(20) DEFAULT NULL,
  `rentBillNo` varchar(50) DEFAULT NULL,
  `rentBillStat` int(11) DEFAULT NULL,
  `customerName` varchar(50) DEFAULT NULL,
  `customerPhone` varchar(20) DEFAULT NULL,
  `customerAddr` varchar(100) DEFAULT NULL,
  `customerCard` varchar(50) DEFAULT NULL,
  `warehouseId` int(20) DEFAULT NULL,
  `supplierId` int(20) DEFAULT NULL,
  `expressBillNo` varchar(50) DEFAULT NULL,
  `rentMoney` double(255,2) DEFAULT NULL,
  `repoMoney` double(255,0) DEFAULT NULL,
  `compensateMoney` double(255,0) DEFAULT NULL,
  `beginDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `reversionDate` date DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_reversion
-- ----------------------------

-- ----------------------------
-- Table structure for tb_reversiondtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_reversiondtl`;
CREATE TABLE `tb_reversiondtl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reversionId` bigint(20) DEFAULT NULL,
  `rentDtlId` int(11) DEFAULT NULL,
  `reversionAmount` bigint(20) DEFAULT NULL,
  `reversionStat` int(11) DEFAULT NULL,
  `itemRent` double(255,2) DEFAULT NULL,
  `itemRepo` double(255,2) DEFAULT NULL,
  `itemCompensate` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_reversiondtl
-- ----------------------------

-- ----------------------------
-- Table structure for tb_seq
-- ----------------------------
DROP TABLE IF EXISTS `tb_seq`;
CREATE TABLE `tb_seq` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `prefix` varchar(10) NOT NULL,
  `day` date NOT NULL,
  `seq` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_seq
-- ----------------------------
INSERT INTO `tb_seq` VALUES ('14', 'CZ', '2016-05-09', '1');
INSERT INTO `tb_seq` VALUES ('15', 'CZ', '2016-05-09', '2');
INSERT INTO `tb_seq` VALUES ('16', 'CZ', '2016-05-09', '3');
INSERT INTO `tb_seq` VALUES ('17', 'CZ', '2016-05-09', '4');
INSERT INTO `tb_seq` VALUES ('18', 'CZ', '2016-05-09', '5');
INSERT INTO `tb_seq` VALUES ('19', 'CZ', '2016-05-09', '6');
INSERT INTO `tb_seq` VALUES ('20', 'CZ', '2016-05-09', '7');
INSERT INTO `tb_seq` VALUES ('21', 'CZ', '2016-05-09', '8');
INSERT INTO `tb_seq` VALUES ('22', 'CZ', '2016-05-09', '9');
INSERT INTO `tb_seq` VALUES ('23', 'CZ', '2016-05-09', '10');
INSERT INTO `tb_seq` VALUES ('24', 'CZ', '2016-05-09', '11');
INSERT INTO `tb_seq` VALUES ('25', 'CZ', '2016-05-09', '12');
INSERT INTO `tb_seq` VALUES ('26', 'CZ', '2016-05-09', '13');
INSERT INTO `tb_seq` VALUES ('27', 'CZ', '2016-05-09', '14');
INSERT INTO `tb_seq` VALUES ('28', 'CZ', '2016-05-09', '15');
INSERT INTO `tb_seq` VALUES ('29', 'CZ', '2016-05-09', '16');
INSERT INTO `tb_seq` VALUES ('30', 'CZ', '2016-05-09', '17');
INSERT INTO `tb_seq` VALUES ('31', 'CZ', '2016-05-09', '18');
INSERT INTO `tb_seq` VALUES ('32', 'CZ', '2016-05-09', '19');
INSERT INTO `tb_seq` VALUES ('33', 'CZ', '2016-05-09', '20');
INSERT INTO `tb_seq` VALUES ('34', 'CZ', '2016-05-09', '21');
INSERT INTO `tb_seq` VALUES ('35', 'CZ', '2016-05-09', '22');
INSERT INTO `tb_seq` VALUES ('36', 'CZ', '2016-05-09', '23');
INSERT INTO `tb_seq` VALUES ('37', 'CZ', '2016-05-09', '24');
INSERT INTO `tb_seq` VALUES ('38', 'CZ', '2016-05-09', '25');
INSERT INTO `tb_seq` VALUES ('39', 'CZ', '2016-05-09', '26');
INSERT INTO `tb_seq` VALUES ('40', 'CZ', '2016-05-09', '27');
INSERT INTO `tb_seq` VALUES ('41', 'CZ', '2016-05-09', '28');
INSERT INTO `tb_seq` VALUES ('42', 'CZ', '2016-05-09', '29');
INSERT INTO `tb_seq` VALUES ('43', 'CZ', '2016-05-09', '30');
INSERT INTO `tb_seq` VALUES ('44', 'CZ', '2016-05-11', '1');
INSERT INTO `tb_seq` VALUES ('45', 'CZ', '2016-05-11', '2');
INSERT INTO `tb_seq` VALUES ('46', 'CZ', '2016-05-11', '3');
INSERT INTO `tb_seq` VALUES ('47', 'CZ', '2016-05-11', '4');
INSERT INTO `tb_seq` VALUES ('48', 'CZ', '2016-05-11', '5');
INSERT INTO `tb_seq` VALUES ('49', 'CZ', '2016-05-11', '6');
INSERT INTO `tb_seq` VALUES ('50', 'CZ', '2016-05-11', '7');
INSERT INTO `tb_seq` VALUES ('51', 'CZ', '2016-05-11', '8');
INSERT INTO `tb_seq` VALUES ('52', 'CZ', '2016-05-11', '9');
INSERT INTO `tb_seq` VALUES ('53', 'CZ', '2016-05-11', '10');
INSERT INTO `tb_seq` VALUES ('54', 'CZ', '2016-05-11', '11');
INSERT INTO `tb_seq` VALUES ('55', 'CZ', '2016-05-11', '12');
INSERT INTO `tb_seq` VALUES ('56', 'CZ', '2016-05-11', '13');
INSERT INTO `tb_seq` VALUES ('57', 'CZ', '2016-05-11', '14');
INSERT INTO `tb_seq` VALUES ('58', 'CZ', '2016-05-11', '15');
INSERT INTO `tb_seq` VALUES ('59', 'CZ', '2016-05-11', '16');
INSERT INTO `tb_seq` VALUES ('60', 'CZ', '2016-05-11', '17');
INSERT INTO `tb_seq` VALUES ('61', 'CZ', '2016-05-11', '18');
INSERT INTO `tb_seq` VALUES ('62', 'CZ', '2016-05-11', '19');
INSERT INTO `tb_seq` VALUES ('63', 'CZ', '2016-05-12', '1');
INSERT INTO `tb_seq` VALUES ('64', 'CZ', '2016-05-12', '2');
INSERT INTO `tb_seq` VALUES ('65', 'CZ', '2016-05-12', '3');
INSERT INTO `tb_seq` VALUES ('66', 'CZ', '2016-05-12', '4');
INSERT INTO `tb_seq` VALUES ('67', 'CZ', '2016-05-14', '1');
INSERT INTO `tb_seq` VALUES ('68', 'CZ', '2016-05-14', '2');
INSERT INTO `tb_seq` VALUES ('69', 'CZ', '2016-05-14', '3');
INSERT INTO `tb_seq` VALUES ('70', 'CZ', '2016-05-14', '4');
INSERT INTO `tb_seq` VALUES ('71', 'CZ', '2016-05-14', '5');
INSERT INTO `tb_seq` VALUES ('72', 'CZ', '2016-05-14', '6');
INSERT INTO `tb_seq` VALUES ('73', 'CZ', '2016-05-14', '7');
INSERT INTO `tb_seq` VALUES ('74', 'CZ', '2016-05-14', '8');
INSERT INTO `tb_seq` VALUES ('75', 'CZ', '2016-05-14', '9');
INSERT INTO `tb_seq` VALUES ('76', 'CZ', '2016-05-14', '10');
INSERT INTO `tb_seq` VALUES ('77', 'CZ', '2016-05-14', '11');
INSERT INTO `tb_seq` VALUES ('78', 'CZ', '2016-05-14', '12');
INSERT INTO `tb_seq` VALUES ('79', 'GH', '2016-05-14', '13');
INSERT INTO `tb_seq` VALUES ('80', 'GH', '2016-05-14', '14');
INSERT INTO `tb_seq` VALUES ('81', 'GH', '2016-05-14', '15');
INSERT INTO `tb_seq` VALUES ('82', 'GH', '2016-05-14', '16');
INSERT INTO `tb_seq` VALUES ('83', 'CZ', '2016-05-14', '17');
INSERT INTO `tb_seq` VALUES ('84', 'GH', '2016-05-14', '18');
INSERT INTO `tb_seq` VALUES ('85', 'GH', '2016-05-14', '19');
INSERT INTO `tb_seq` VALUES ('86', 'CZ', '2016-05-14', '20');
INSERT INTO `tb_seq` VALUES ('87', 'GH', '2016-05-14', '21');
INSERT INTO `tb_seq` VALUES ('88', 'CZ', '2016-05-14', '22');
INSERT INTO `tb_seq` VALUES ('89', 'CG', '2016-05-15', '1');
INSERT INTO `tb_seq` VALUES ('90', 'CG', '2016-05-15', '2');
INSERT INTO `tb_seq` VALUES ('91', 'CG', '2016-05-15', '3');
INSERT INTO `tb_seq` VALUES ('92', 'CG', '2016-05-15', '4');
INSERT INTO `tb_seq` VALUES ('93', 'CK', '2016-05-16', '1');
INSERT INTO `tb_seq` VALUES ('94', 'CK', '2016-05-16', '2');
INSERT INTO `tb_seq` VALUES ('95', 'CK', '2016-05-16', '3');
INSERT INTO `tb_seq` VALUES ('96', 'CK', '2016-05-16', '4');
INSERT INTO `tb_seq` VALUES ('97', 'CK', '2016-05-16', '5');
INSERT INTO `tb_seq` VALUES ('98', 'CK', '2016-05-16', '6');
INSERT INTO `tb_seq` VALUES ('99', 'CK', '2016-05-16', '7');
INSERT INTO `tb_seq` VALUES ('100', 'CK', '2016-05-16', '8');
INSERT INTO `tb_seq` VALUES ('101', 'CK', '2016-05-16', '9');
INSERT INTO `tb_seq` VALUES ('102', 'GH', '2016-05-16', '10');
INSERT INTO `tb_seq` VALUES ('103', 'RK', '2016-05-16', '11');
INSERT INTO `tb_seq` VALUES ('104', 'RK', '2016-05-16', '12');
INSERT INTO `tb_seq` VALUES ('105', 'RK', '2016-05-16', '13');
INSERT INTO `tb_seq` VALUES ('106', 'RK', '2016-05-16', '14');
INSERT INTO `tb_seq` VALUES ('107', 'RK', '2016-05-16', '15');

-- ----------------------------
-- Table structure for tb_size
-- ----------------------------
DROP TABLE IF EXISTS `tb_size`;
CREATE TABLE `tb_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_size
-- ----------------------------
INSERT INTO `tb_size` VALUES ('8', 'S001', 'S001', '使用', null, '2016-04-14 15:00:11', null, '2016-04-14 15:03:30', 'S001');
INSERT INTO `tb_size` VALUES ('9', 'S002', 'S002', '使用', null, '2016-04-14 15:43:58', null, '2016-05-08 13:07:03', 'S002');
INSERT INTO `tb_size` VALUES ('10', '123', '123', '停用', null, '2016-05-08 13:06:59', null, null, '123');
INSERT INTO `tb_size` VALUES ('11', '123', '123', '使用', null, '2016-05-15 22:46:36', null, null, '123');

-- ----------------------------
-- Table structure for tb_sizedtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_sizedtl`;
CREATE TABLE `tb_sizedtl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sizeId` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sizedtl
-- ----------------------------
INSERT INTO `tb_sizedtl` VALUES ('12', '8', 'size1', 'size1', '1', 'size1');
INSERT INTO `tb_sizedtl` VALUES ('13', '8', 'size2', 'size2', '1', 'size2');

-- ----------------------------
-- Table structure for tb_sku
-- ----------------------------
DROP TABLE IF EXISTS `tb_sku`;
CREATE TABLE `tb_sku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `itemId` bigint(20) DEFAULT NULL,
  `colorId` bigint(20) DEFAULT NULL,
  `sizeDtlId` bigint(20) DEFAULT NULL,
  `text` varchar(20) DEFAULT NULL,
  `hasImage` tinyint(1) DEFAULT NULL,
  `imgSuffix` varchar(64) DEFAULT NULL,
  `comment` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sku
-- ----------------------------
INSERT INTO `tb_sku` VALUES ('53', '16', '6', '12', '123', null, null, null);
INSERT INTO `tb_sku` VALUES ('54', '16', '6', '13', '123', null, null, null);
INSERT INTO `tb_sku` VALUES ('55', '16', '7', '12', '123', null, null, null);
INSERT INTO `tb_sku` VALUES ('56', '16', '7', '13', '123', null, null, null);
INSERT INTO `tb_sku` VALUES ('57', '16', '8', '12', '123', null, null, null);
INSERT INTO `tb_sku` VALUES ('58', '16', '8', '13', '123', null, null, null);

-- ----------------------------
-- Table structure for tb_stockadjust
-- ----------------------------
DROP TABLE IF EXISTS `tb_stockadjust`;
CREATE TABLE `tb_stockadjust` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `warehouseId` bigint(20) DEFAULT NULL,
  `stat` int(11) DEFAULT '0',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `warehouseName` varchar(50) DEFAULT NULL,
  `statName` varchar(50) DEFAULT NULL,
  `billDate` date DEFAULT NULL,
  `makerId` bigint(20) DEFAULT NULL,
  `makerName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stockadjust
-- ----------------------------

-- ----------------------------
-- Table structure for tb_stockadjustdtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_stockadjustdtl`;
CREATE TABLE `tb_stockadjustdtl` (
  `dtlid` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `skuAmount` int(11) DEFAULT NULL,
  `tzAmount` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`dtlid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stockadjustdtl
-- ----------------------------

-- ----------------------------
-- Table structure for tb_stockin
-- ----------------------------
DROP TABLE IF EXISTS `tb_stockin`;
CREATE TABLE `tb_stockin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `billStat` int(11) DEFAULT '0',
  `stockOutBillId` bigint(20) DEFAULT NULL,
  `stockOutWarehouseId` bigint(20) DEFAULT NULL,
  `stockInWarehouseId` bigint(20) DEFAULT NULL,
  `billDate` date DEFAULT NULL,
  `totalStockIn` decimal(20,2) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stockin
-- ----------------------------
INSERT INTO `tb_stockin` VALUES ('7', 'RK20160516000015', '0', '10', '1', '1', '2016-05-11', '1.00', null, null, null, null);

-- ----------------------------
-- Table structure for tb_stockindtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_stockindtl`;
CREATE TABLE `tb_stockindtl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stockInId` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `stockOutDtlId` bigint(20) DEFAULT NULL,
  `stockOutAmount` int(11) DEFAULT NULL,
  `stockInAmount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stockindtl
-- ----------------------------
INSERT INTO `tb_stockindtl` VALUES ('6', '7', '53', '8', '1', '1');

-- ----------------------------
-- Table structure for tb_stockout
-- ----------------------------
DROP TABLE IF EXISTS `tb_stockout`;
CREATE TABLE `tb_stockout` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `billNo` varchar(20) DEFAULT NULL,
  `billStat` int(11) DEFAULT '0',
  `StockOutWarehouseId` bigint(20) DEFAULT NULL,
  `StockInWarehouseId` bigint(20) DEFAULT NULL,
  `billDate` datetime DEFAULT NULL,
  `totalStockOut` bigint(20) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stockout
-- ----------------------------
INSERT INTO `tb_stockout` VALUES ('10', 'CK20160516000009', '1', '1', '1', '2016-05-16 00:00:00', '1', null, null, null, null);

-- ----------------------------
-- Table structure for tb_stockoutdtl
-- ----------------------------
DROP TABLE IF EXISTS `tb_stockoutdtl`;
CREATE TABLE `tb_stockoutdtl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stockOutId` bigint(20) DEFAULT NULL,
  `skuId` bigint(20) DEFAULT NULL,
  `stockOutAmount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_stockoutdtl
-- ----------------------------
INSERT INTO `tb_stockoutdtl` VALUES ('8', '10', '53', '1');

-- ----------------------------
-- Table structure for tb_supplier
-- ----------------------------
DROP TABLE IF EXISTS `tb_supplier`;
CREATE TABLE `tb_supplier` (
  `id` bigint(40) NOT NULL AUTO_INCREMENT,
  `supplierName` varchar(50) DEFAULT NULL,
  `supplierCode` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `stat` varchar(10) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_supplier
-- ----------------------------
INSERT INTO `tb_supplier` VALUES ('15', '顺丰', '1', '111', '物流', '使用', null, '2016-05-11 20:24:17', null, null);
INSERT INTO `tb_supplier` VALUES ('16', '韵达', '2', '111', '物流', '使用', null, '2016-05-11 20:24:29', null, null);
INSERT INTO `tb_supplier` VALUES ('17', '132', '123', '213', '物流', '停用', null, '2016-05-15 22:17:55', '2016-05-15 22:18:01', null);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(16) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `true_name` varchar(50) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `stat` varchar(10) DEFAULT NULL,
  `create_by` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `user_code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', 'admin', 'sky11', null, null, '1', null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('2', 'test', 'test', 'test', null, null, '1', null, null, null, null, null);
INSERT INTO `tb_user` VALUES ('3', 'xiaohong', '111', 'xiaohong', null, null, '0', null, null, null, null, null);

-- ----------------------------
-- Table structure for tb_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse`;
CREATE TABLE `tb_warehouse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `address` varchar(50) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `text` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_warehouse
-- ----------------------------
INSERT INTO `tb_warehouse` VALUES ('1', '浦东仓库', '使用', '手动复位', '2222', null, null, null, null, '001', '浦东仓库');
INSERT INTO `tb_warehouse` VALUES ('2', '浦西仓库', '停用', '徐家汇001号', '12344', null, null, null, null, '002', '浦西仓库');
INSERT INTO `tb_warehouse` VALUES ('3', '嘉定仓库', '停用', '嘉定城', '22222222', null, null, null, null, '003', '嘉定仓库');
INSERT INTO `tb_warehouse` VALUES ('4', '123', '使用', '123', '123', null, '2016-05-08 09:32:32', null, null, '123', '123');
INSERT INTO `tb_warehouse` VALUES ('5', '324', '使用', '123', '123', null, '2016-05-08 09:36:11', null, '2016-05-08 09:36:28', '123', '324');

-- ----------------------------
-- Table structure for tb_warehouse_location
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse_location`;
CREATE TABLE `tb_warehouse_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `warehouseId` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `stat` varchar(10) DEFAULT '使用',
  `code` varchar(20) DEFAULT NULL,
  `text` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_warehouse_location
-- ----------------------------
INSERT INTO `tb_warehouse_location` VALUES ('4', '1', '塘桥1号仓库11', null, '001', '塘桥1号仓库');
INSERT INTO `tb_warehouse_location` VALUES ('5', '1', '高科2号仓库22', null, '002', '高科2号仓库');
INSERT INTO `tb_warehouse_location` VALUES ('6', '1', 'sswww', null, '003', 'ss');
INSERT INTO `tb_warehouse_location` VALUES ('7', '5', '123', null, '123', '123');
INSERT INTO `tb_warehouse_location` VALUES ('8', '5', '123', null, '123', '123');
INSERT INTO `tb_warehouse_location` VALUES ('11', '2', '123', null, '123', '123');
