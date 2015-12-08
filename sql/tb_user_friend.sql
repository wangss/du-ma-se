
# Created by wangshuisheng on 2015/6/15.

CREATE TABLE tb_user_friend(
  uid INT(11) NOT NULL COMMENT 'uid',
  friend INT(11) NOT NULL  COMMENT 'friend Id',
  is_friend TINYINT(2) NOT NULL COMMENT '是否好友，值为1',
  add_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  msg VARCHAR(32) COMMENT 'msg',

  PRIMARY KEY (uid,friend),
  INDEX ind_tb_user_friend_uid (uid),
  INDEX ind_tb_user_friend_uid_friend_id (uid, friend)
)
  COMMENT = '用户friends'
;

