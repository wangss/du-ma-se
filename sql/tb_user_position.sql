
# Created by wangshuisheng on 2015/6/15.

CREATE TABLE tb_user_position(
  uid INT(11) NOT NULL COMMENT 'uid',

  longitude double(10,6)  COMMENT '经度',
  latitude double(10,6)  COMMENT '纬度',
  geohash VARCHAR(32) COMMENT 'geohash',
  area VARCHAR(64) COMMENT '地区',
  active_time TIMESTAMP COMMENT '时间',

  PRIMARY KEY (uid),
  INDEX ind_tb_user_position_id (uid)
)
  COMMENT = '用户位置'
;

