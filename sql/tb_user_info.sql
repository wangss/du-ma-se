CREATE TABLE tb_user_info(
  uid INT(11) NOT NULL COMMENT '关联 tb_user.id',
  nickname VARCHAR(32) COMMENT '昵称',
  birthday DATE COMMENT '生日',
  gender TINYINT(2) COMMENT '性别 1男 2女 3保密',

  email VARCHAR(32) COMMENT '邮箱',
  wechat VARCHAR(32) COMMENT '微信',
  qq VARCHAR(16) COMMENT 'qq',

  astrology TINYINT(2) COMMENT '星座： 1白羊座; 2金牛座; 3双子座; 4巨蟹座; 5狮子座; 6处女座; 7天秤座; 8天蝎座; 9射手座; 10摩羯座; 11水瓶座; 12双鱼座;',
  relationship int(2) COMMENT '情感 1保密; 2单身; 3恋爱中;4已婚; 5同性',
  hometown VARCHAR(64) COMMENT '老乡',
  region VARCHAR(64) COMMENT '区域',
  school VARCHAR(64) COMMENT '学校',

  homepage VARCHAR(64) COMMENT '主页',
  avatar VARCHAR(64) COMMENT '头像url',

  job VARCHAR(32) COMMENT '职业',
  hobby VARCHAR(64) COMMENT '兴趣爱好',
  signature VARCHAR(64) COMMENT '个性签名',


  privacy_find TINYINT(2) COMMENT '1可找到我; 2不可找到我',
  privacy_stranger_talk TINYINT(2) COMMENT '1接收陌生人消息; 2不接收陌生人消息',
  info_update_time TIMESTAMP COMMENT '修改时间',
  info_update_msg VARCHAR(32) COMMENT 'msg',



  PRIMARY KEY (uid),
  INDEX ind_tb_user_info_uid (uid)
)
  COMMENT = '用户详情表'
;

