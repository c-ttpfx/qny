SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码(加密)',
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '14e1b600b1fd579f47433b88e8d85291', 'admin', 10, '男', 'http://localhost:10002/video/images/路飞头像.png');
INSERT INTO `user` VALUES (111, '14e1b600b1fd579f47433b88e8d85291', '秃头披风侠', 18, '男', 'http://localhost:10002/video/images/初音未来.jpg');
INSERT INTO `user` VALUES (1719758987349610498, '14e1b600b1fd579f47433b88e8d85291', 'liuhuan', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1719762274740228097, 'd9b1d7db4cd6e70935368a1efb10e377', 'knight', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1719762843664011266, 'd9b1d7db4cd6e70935368a1efb10e377', 'Jack', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1721635672898420738, 'd9b1d7db4cd6e70935368a1efb10e377', 'nihao', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1721858865286483970, '14e1b600b1fd579f47433b88e8d85291', 'user', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1721862573026054145, '14e1b600b1fd579f47433b88e8d85291', 'user123', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1721865167433764866, 'd9b1d7db4cd6e70935368a1efb10e377', 'userqwe', NULL, NULL, NULL);
INSERT INTO `user` VALUES (1721879545541451778, '1f32aa4c9a1d2ea010adcf2348166a04', 'user123123', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for video_metadata
-- ----------------------------
DROP TABLE IF EXISTS `video_metadata`;
CREATE TABLE `video_metadata`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `uploader_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `upload_time` datetime NULL DEFAULT NULL,
  `duration` bigint NULL DEFAULT NULL,
  `format` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `resolution` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `file_size` bigint NULL DEFAULT NULL,
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `thumbnail_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1717836001797247044 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video_metadata
-- ----------------------------
INSERT INTO `video_metadata` VALUES (1717835279395491841, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/26/1698398810117--%5C游戏开发 (1).m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835646690693121, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/27/1698398821914--%5C游戏开发.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835677724348418, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/28/1698398908817--%5Cmooc.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835721911341058, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/28/1698398916211--%5C游戏开发 (1).m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835771882278914, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/28/1698398926743--%5C游戏开发 (2).m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835797761134594, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/28/1698398938660--%5C游戏开发.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835842531135489, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/29/1698398944822--%5C游戏开发.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835901452718081, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/29/1698398955492--%5C贪吃蛇.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835943035047938, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/29/1698398969572--%5C飞机大战.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835959040512001, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/29/1698398979463--%5CATM.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835980704092162, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/29/1698398983274--%5C游戏开发.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717835994952142849, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/29/1698398988444--%5Cl.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717836001797246977, '喜羊羊', NULL, '111', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/29/1698398991849--%5C游戏开发.m3u8', NULL, 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717836001797246984, '喜羊羊', '这是喜羊羊与灰太狼', '111', '2023-10-28 20:10:55', 9999, 'MP4', '1920*1080', 12345, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/10/27/17/28/1698398926743--%5C游戏开发 (2).m3u8', '书籍,车辆,科学', 'http://localhost:10002/video/images/亚斯娜小埋.jpg');
INSERT INTO `video_metadata` VALUES (1717836001797246985, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/43/1699343013334---  -  (1).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246986, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/43/1699343015699---  -  (10).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246987, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/43/1699343016789---  -  (11).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246988, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/43/1699343018568---  -  (12).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246989, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/43/1699343026208---  -  (13).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246990, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/43/1699343028179---  -  (14).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246991, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/43/1699343029302---  -  (15).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246992, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/43/1699343030813---  -  (16).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246993, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/43/1699343039689---  -  (17).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246994, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/44/1699343056003---  -  (18).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246995, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/44/1699343065404---  -  (19).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246996, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/44/1699343067106---  -  (2).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246997, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/44/1699343070552---  -  (20).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246998, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/44/1699343077038---  -  (21).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797246999, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/44/1699343080055---  -  (22).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247000, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/44/1699343089131---  -  (23).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247001, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/44/1699343094794---  -  (24).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247002, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/44/1699343099529---  -  (25).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247003, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343102648---  -  (26).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247004, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343111252---  -  (27).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247005, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343117181---  -  (28).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247006, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343120822---  -  (29).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247007, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343125173---  -  (3).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247008, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343131705---  -  (30).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247009, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343135236---  -  (31).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247010, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343143498---  -  (32).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247011, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343149509---  -  (33).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247012, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343150809---  -  (34).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247013, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343156533---  -  (35).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247014, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343157706---  -  (36).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247015, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/45/1699343158704---  -  (37).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247016, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343163969---  -  (38).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247017, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343166520---  -  (39).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247018, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343168558---  -  (4).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247019, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343170849---  -  (40).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247020, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343173787---  -  (41).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247021, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343178086---  -  (42).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247022, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343181506---  -  (43).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247023, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343190347---  -  (44).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247024, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343196323---  -  (45).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247025, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343199810---  -  (46).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247026, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343201549---  -  (47).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247027, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343202791---  -  (48).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247028, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343205350---  -  (49).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247029, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343206739---  -  (5).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247030, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343211575---  -  (50).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247031, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343212761---  -  (51).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247032, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343213791---  -  (52).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247033, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/46/1699343218992---  -  (53).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247034, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/47/1699343221553---  -  (54).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247035, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/47/1699343223171---  -  (6).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247036, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/47/1699343228441---  -  (7).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247037, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/48/1699343292262---  -  (8).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247038, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/48/1699343294175---  -  (9).m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247039, '喜羊羊', NULL, '1721635672898420738', NULL, NULL, NULL, NULL, NULL, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/15/48/1699343298210---  - .m3u8', '体育', NULL);
INSERT INTO `video_metadata` VALUES (1717836001797247040, '美羊羊', '测试数据', '1', '2023-11-07 18:08:47', 1200, 'video/mp4', NULL, 1079344, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/18/8/1699351721677--QQ录屏20231104221837.m3u8', '体育,书籍', '');
INSERT INTO `video_metadata` VALUES (1717836001797247041, '上传视频展示', '视频简单描述', '1', '2023-11-07 20:11:49', 1200, 'video/mp4', NULL, 1079344, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/20/11/1699359105393--QQ录屏20231104221837.m3u8', '体育', '');
INSERT INTO `video_metadata` VALUES (1717836001797247042, '标题展示', '这是一个体育视频', '1', '2023-11-07 20:22:17', 1200, 'video/mp4', NULL, 1079344, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/20/22/1699359726786--QQ录屏20231104221837.m3u8', '体育', '');
INSERT INTO `video_metadata` VALUES (1717836001797247043, '标题展示', '这是一个自行车比赛视频', '1', '2023-11-07 21:19:39', 1200, 'video/mp4', NULL, 1079344, 'http://s32r2vddr.hn-bkt.clouddn.com/2023/11/7/21/19/1699363170900--QQ录屏20231104221837.m3u8', '体育', '');

-- ----------------------------
-- Table structure for video_user_collect
-- ----------------------------
DROP TABLE IF EXISTS `video_user_collect`;
CREATE TABLE `video_user_collect`  (
  `video_id` bigint NULL DEFAULT NULL COMMENT '视频ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `collect_time` bigint NULL DEFAULT NULL COMMENT '收藏时间',
  UNIQUE INDEX `idx_video_user`(`video_id` ASC, `user_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video_user_collect
-- ----------------------------
INSERT INTO `video_user_collect` VALUES (1717835646690693121, 111, 1698560403905);
INSERT INTO `video_user_collect` VALUES (1717835279395491841, 111, 1698560493621);
INSERT INTO `video_user_collect` VALUES (1717835721911341058, 111, 1698560520278);
INSERT INTO `video_user_collect` VALUES (1717835959040512001, 111, 1698560562777);
INSERT INTO `video_user_collect` VALUES (1717835677724348418, 111, 1698560828948);
INSERT INTO `video_user_collect` VALUES (1717835901452718081, 111, 1698572752562);
INSERT INTO `video_user_collect` VALUES (1717835994952142849, 111, 1699193560846);
INSERT INTO `video_user_collect` VALUES (1717836001797247023, 1, 1699356507106);

-- ----------------------------
-- Table structure for video_user_comment
-- ----------------------------
DROP TABLE IF EXISTS `video_user_comment`;
CREATE TABLE `video_user_comment`  (
  `video_id` bigint NULL DEFAULT NULL COMMENT '视频ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `comment_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评论内容',
  `comment_time` bigint NULL DEFAULT NULL COMMENT '评论时间',
  `id` bigint NOT NULL COMMENT '评论id',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父评论id，也就是是否为追评',
  `comment_region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '父评论id，也就是回复了谁',
  `root_id` bigint NULL DEFAULT NULL COMMENT '顶级父id，最开始在哪个评论下进行评论和回复的',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_video_user`(`video_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video_user_comment
-- ----------------------------
INSERT INTO `video_user_comment` VALUES (1717835677724348418, 111, 'nihao ', 1698858827832, 1719764687496830977, NULL, '重庆', NULL);
INSERT INTO `video_user_comment` VALUES (1717835797761134594, 111, '123', 1698858841787, 1719764745994788865, NULL, '重庆', NULL);
INSERT INTO `video_user_comment` VALUES (1717835943035047938, 111, '你好', 1699087641364, 1720724400996839425, NULL, '重庆', NULL);
INSERT INTO `video_user_comment` VALUES (1717835901452718081, 111, '1', 1699087656100, 1720724462753771522, NULL, '重庆', NULL);
INSERT INTO `video_user_comment` VALUES (1717835279395491841, 1, '你好', 1699240187091, 1721364224212627458, NULL, '重庆', NULL);
INSERT INTO `video_user_comment` VALUES (1717835279395491841, 1, '1111', 1699240224837, 1721364382455328770, NULL, '重庆', NULL);
INSERT INTO `video_user_comment` VALUES (1717836001797247033, 1, '1', 1699347352020, 1721813706440138754, NULL, '重庆', NULL);

-- ----------------------------
-- Table structure for video_user_like
-- ----------------------------
DROP TABLE IF EXISTS `video_user_like`;
CREATE TABLE `video_user_like`  (
  `video_id` bigint NULL DEFAULT NULL COMMENT '视频ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `like_time` bigint NULL DEFAULT NULL COMMENT '点赞时间',
  UNIQUE INDEX `idx_video_user`(`video_id` ASC, `user_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video_user_like
-- ----------------------------
INSERT INTO `video_user_like` VALUES (1717836001797246988, 1, 1699352847436);
INSERT INTO `video_user_like` VALUES (1717836001797247021, 1, 1699352858067);
INSERT INTO `video_user_like` VALUES (1717836001797247002, 1, 1699359167834);
INSERT INTO `video_user_like` VALUES (1717836001797246990, 1, 1699359799185);

-- ----------------------------
-- Table structure for video_user_share
-- ----------------------------
DROP TABLE IF EXISTS `video_user_share`;
CREATE TABLE `video_user_share`  (
  `video_id` bigint NULL DEFAULT NULL COMMENT '视频ID',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户ID',
  `to_user_id` bigint NULL DEFAULT NULL COMMENT '被分享者ID',
  `has_read` tinyint NULL DEFAULT NULL COMMENT '是否已读，0代表未读，1代表已读',
  `share_tiem` bigint NULL DEFAULT NULL COMMENT '分享时间戳',
  UNIQUE INDEX `idx_video_user`(`video_id` ASC, `user_id` ASC) USING BTREE,
  INDEX `idx_user_id`(`user_id` ASC) USING BTREE,
  INDEX `idx_to_user_id`(`to_user_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info   */
/******************************************/
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) DEFAULT NULL,
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(20) DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) DEFAULT NULL,
  `c_use` varchar(64) DEFAULT NULL,
  `effect` varchar(64) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL,
  `c_schema` text,
  `encrypted_data_key` text NOT NULL COMMENT '密钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_aggr   */
/******************************************/
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) NOT NULL COMMENT 'datum_id',
  `content` longtext NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_beta   */
/******************************************/
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(20) DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text NOT NULL COMMENT '密钥',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_info_tag   */
/******************************************/
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL COMMENT 'content',
  `md5` varchar(32) DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COMMENT 'source user',
  `src_ip` varchar(20) DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = config_tags_relation   */
/******************************************/
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = group_capacity   */
/******************************************/
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = his_config_info   */
/******************************************/
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) NOT NULL,
  `group_id` varchar(128) NOT NULL,
  `app_name` varchar(128) DEFAULT NULL COMMENT 'app_name',
  `content` longtext NOT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text,
  `src_ip` varchar(20) DEFAULT NULL,
  `op_type` char(10) DEFAULT NULL,
  `tenant_id` varchar(128) DEFAULT '' COMMENT '租户字段',
  `encrypted_data_key` text NOT NULL COMMENT '密钥',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';


/******************************************/
/*   数据库全名 = nacos_config   */
/*   表名称 = tenant_capacity   */
/******************************************/
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';


CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) default '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) default '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

CREATE TABLE users (
	username varchar(50) NOT NULL PRIMARY KEY,
	password varchar(500) NOT NULL,
	enabled boolean NOT NULL
);

CREATE TABLE roles (
	username varchar(50) NOT NULL,
	role varchar(50) NOT NULL,
	constraint uk_username_role UNIQUE (username,role)
);

CREATE TABLE permissions (
    role varchar(50) NOT NULL,
    resource varchar(512) NOT NULL,
    action varchar(8) NOT NULL,
    constraint uk_role_permission UNIQUE (role,resource,action)
);

INSERT INTO users (username, password, enabled) VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', TRUE);

INSERT INTO roles (username, role) VALUES ('nacos', 'ROLE_ADMIN');


SET FOREIGN_KEY_CHECKS = 1;
