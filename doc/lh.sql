CREATE TABLE `user` (
                        `user_id` BIGINT comment '用户ID',
                        `password` varchar(32) comment '密码(加密)',
                        `name` varchar(16) comment '用户名',
                        `age` int comment '年龄',
                        `gender` varchar(2) comment '性别',
                        primary key(user_id)
);

insert into `user` values(1, '123456', 'admin', 10, '男');