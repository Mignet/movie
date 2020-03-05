/*
 initail database struct
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sea_content
-- ----------------------------
DROP TABLE IF EXISTS `sea_content`;
CREATE TABLE `sea_content`  (
  `v_id` mediumint(8) NOT NULL DEFAULT 0,
  `tid` smallint(8) UNSIGNED NOT NULL DEFAULT 0,
  `body` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`v_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '影片简介' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sea_data
-- ----------------------------
DROP TABLE IF EXISTS `sea_data`;
CREATE TABLE `sea_data`  (
  `v_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tid` smallint(8) UNSIGNED NOT NULL DEFAULT 0,
  `v_name` char(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_state` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `v_pic` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_spic` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_gpic` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_hit` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `v_money` smallint(6) NOT NULL DEFAULT 0,
  `v_rank` smallint(6) NOT NULL DEFAULT 0,
  `v_digg` smallint(6) NOT NULL DEFAULT 0,
  `v_tread` smallint(6) NOT NULL DEFAULT 0,
  `v_commend` smallint(6) NOT NULL DEFAULT 0,
  `v_wrong` smallint(8) UNSIGNED NOT NULL DEFAULT 0,
  `v_ismake` smallint(1) UNSIGNED NOT NULL DEFAULT 0,
  `v_actor` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_color` char(7) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_publishyear` int(10) DEFAULT 0,
  `v_publisharea` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_addtime` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `v_topic` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `v_note` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_tags` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_letter` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `v_isunion` smallint(6) NOT NULL DEFAULT 0,
  `v_recycled` smallint(6) NOT NULL DEFAULT 0,
  `v_director` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_enname` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_lang` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_score` int(10) DEFAULT 0,
  `v_scorenum` int(10) DEFAULT 0,
  `v_extratype` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `v_jq` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `v_nickname` char(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_reweek` char(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_douban` float DEFAULT 0,
  `v_mtime` float DEFAULT 0,
  `v_imdb` float DEFAULT 0,
  `v_tvs` char(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_company` char(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_dayhit` int(10) DEFAULT NULL,
  `v_weekhit` int(10) DEFAULT NULL,
  `v_monthhit` int(10) DEFAULT NULL,
  `v_daytime` int(10) DEFAULT NULL,
  `v_weektime` int(10) DEFAULT NULL,
  `v_monthtime` int(10) DEFAULT NULL,
  `v_len` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_total` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_ver` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_psd` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `v_longtxt` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`v_id`) USING BTREE,
  INDEX `idx_tid`(`tid`, `v_recycled`, `v_addtime`) USING BTREE,
  INDEX `idx_addtime`(`v_addtime`) USING BTREE,
  INDEX `idx_name`(`v_name`, `tid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 47832 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '影片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sea_news
-- ----------------------------
DROP TABLE IF EXISTS `sea_news`;
CREATE TABLE `sea_news`  (
  `n_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `tid` smallint(8) UNSIGNED NOT NULL DEFAULT 0,
  `n_title` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `n_pic` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `n_hit` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `n_money` smallint(6) NOT NULL DEFAULT 0,
  `n_rank` smallint(6) NOT NULL DEFAULT 0,
  `n_digg` smallint(6) NOT NULL DEFAULT 0,
  `n_tread` smallint(6) NOT NULL DEFAULT 0,
  `n_commend` smallint(6) NOT NULL DEFAULT 0,
  `n_author` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `n_color` char(7) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `n_addtime` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `n_note` smallint(6) NOT NULL DEFAULT 0,
  `n_letter` char(3) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `n_isunion` smallint(6) NOT NULL DEFAULT 0,
  `n_recycled` smallint(6) NOT NULL DEFAULT 0,
  `n_entitle` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `n_outline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `n_keyword` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `n_from` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `n_score` bigint(10) DEFAULT 0,
  `n_scorenum` int(10) DEFAULT 0,
  `n_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`n_id`) USING BTREE,
  INDEX `v_addtime`(`n_addtime`, `n_digg`, `n_tread`, `n_isunion`) USING BTREE,
  INDEX `tid`(`tid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6769 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻表-持久' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sea_playdata
-- ----------------------------
DROP TABLE IF EXISTS `sea_playdata`;
CREATE TABLE `sea_playdata`  (
  `v_id` mediumint(8) NOT NULL DEFAULT 0,
  `tid` smallint(8) UNSIGNED NOT NULL DEFAULT 0,
  `body` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `body1` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  PRIMARY KEY (`v_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '影片播放地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sea_type
-- ----------------------------
DROP TABLE IF EXISTS `sea_type`;
CREATE TABLE `sea_type`  (
  `tid` smallint(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `upid` tinyint(6) UNSIGNED NOT NULL DEFAULT 0,
  `tname` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `tenname` char(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `torder` int(11) NOT NULL DEFAULT 0,
  `templist` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `templist_1` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `templist_2` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `title` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `keyword` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `description` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `ishidden` smallint(6) NOT NULL DEFAULT 0,
  `unionid` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci,
  `tptype` smallint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`tid`) USING BTREE,
  INDEX `upid`(`upid`, `ishidden`) USING BTREE,
  INDEX `torder`(`torder`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 116 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '影片分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sea_type
-- ----------------------------
INSERT INTO `sea_type` VALUES (2, 0, '电视剧', 'dianshiju', 2, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_2', 0);
INSERT INTO `sea_type` VALUES (3, 0, '综艺', 'zongyi', 3, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_3,3_27,3_26,3_29,3_28', 0);
INSERT INTO `sea_type` VALUES (1, 0, '电影', 'dianying', 1, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_1', 0);
INSERT INTO `sea_type` VALUES (4, 0, '动漫', 'dongman', 4, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_4,3_23,3_24,3_25,3_32,3_31', 0);
INSERT INTO `sea_type` VALUES (5, 2, '国产剧', 'guochan', 5, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_12', 0);
INSERT INTO `sea_type` VALUES (6, 2, '香港剧', 'hongkong', 6, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_13', 0);
INSERT INTO `sea_type` VALUES (7, 2, '韩国剧', 'hanguo', 7, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_14', 0);
INSERT INTO `sea_type` VALUES (8, 2, '欧美剧', 'oumei', 8, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_15', 0);
INSERT INTO `sea_type` VALUES (9, 2, '台湾剧', 'taiwan', 9, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_16', 0);
INSERT INTO `sea_type` VALUES (10, 2, '日本剧', 'riben', 10, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_17', 0);
INSERT INTO `sea_type` VALUES (11, 2, '海外剧', 'haiwai', 11, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_18', 0);
INSERT INTO `sea_type` VALUES (12, 1, '动作片', 'dongzuo', 12, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_5', 0);
INSERT INTO `sea_type` VALUES (13, 1, '喜剧片', 'xiju', 13, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_6', 0);
INSERT INTO `sea_type` VALUES (14, 1, '爱情片', 'aiqing', 14, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_7', 0);
INSERT INTO `sea_type` VALUES (15, 1, '科幻片', 'kehuan', 15, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_8', 0);
INSERT INTO `sea_type` VALUES (16, 1, '恐怖片', 'kongbu', 16, 'channel.html', 'content.html', 'play.html', '', '', '', 1, '3_9', 0);
INSERT INTO `sea_type` VALUES (17, 1, '剧情片', 'juqing', 17, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_10', 0);
INSERT INTO `sea_type` VALUES (18, 1, '战争片', 'zhanzhen', 18, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_11', 0);
INSERT INTO `sea_type` VALUES (19, 1, '纪录片', 'jilu', 19, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '3_19', 0);
INSERT INTO `sea_type` VALUES (20, 1, '伦理片', 'lunli', 20, 'channel.html', 'content.html', 'play.html', '', '', '', 0, '', 0);
INSERT INTO `sea_type` VALUES (21, 0, '明天下', 'mingtianxia', 21, 'newspage.html', 'news.html', 'play.html', '', '', '', 0, '', 1);
INSERT INTO `sea_type` VALUES (22, 0, '赘婿', 'zhuixu', 22, 'newspage.html', 'news.html', 'play.html', '', '', '', 0, '', 1);
INSERT INTO `sea_type` VALUES (23, 0, '大道朝天', 'dadaochaotian', 23, 'newspage.html', 'news.html', 'play.html', '', '', '', 0, '', 1);
INSERT INTO `sea_type` VALUES (24, 0, '诡秘之主', 'guimizhizhu', 24, 'newspage.html', 'news.html', 'play.html', '', '', '', 0, '', 1);
INSERT INTO `sea_type` VALUES (25, 0, '红楼梦', 'hongloumeng', 25, 'newspage.html', 'news.html', 'play.html', '', '', '', 0, '', 1);
INSERT INTO `sea_type` VALUES (26, 0, '水浒传', 'shuihu', 26, 'newspage.html', 'news.html', 'play.html', '', '', '', 0, '', 1);
INSERT INTO `sea_type` VALUES (27, 0, '时尚', 'shishang', 27, 'newspage.html', 'news.html', 'play.html', '', '', '', 0, '', 1);

SET FOREIGN_KEY_CHECKS = 1;
