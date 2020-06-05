-- 创建数据库
create schema permanent;

-- oauth 表
-- auto-generated definition
create table oauth
(
    id            int auto_increment primary key,
    platform      varchar(20)  not null comment '授权服务类型：QQ,WECHAT,WEIBO等',
    open_id       varchar(100) null comment 'openId',
    token         varchar(100) null comment 'access_token',
    expires_in    varchar(100) null,
    refresh_token varchar(100) null
) comment 'oauth 授权信息';

