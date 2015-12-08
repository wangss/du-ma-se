
# Created by wangshuisheng on 2015/6/15.

CREATE TABLE tb_user_refuse(
  uid INT(11) NOT NULL COMMENT 'uid',
  refuse_uid INT(11) NOT NULL  COMMENT '被拒绝对话人 Id',
  is_refused TINYINT(2) NOT NULL COMMENT '是否拒绝对话，值为1',
  add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  msg VARCHAR(32) COMMENT 'msg',

  PRIMARY KEY (uid,refuse_uid),
  INDEX ind_tb_user_refuse_uid (uid),
  INDEX ind_tb_user_refuse_uid_refuse_uid (uid, refuse_uid)
)
  COMMENT = '用户拒收对话的 用户';

