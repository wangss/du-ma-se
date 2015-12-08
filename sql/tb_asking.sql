# Created by wangshuisheng on 2015/5/25.

CREATE TABLE tb_asking(
  id INT(11) NOT NULL COMMENT 'id',
  uid INT(11) NOT NULL COMMENT '用户id',
  asking_detail VARCHAR(300) NOT NULL COMMENT '喊话详情',

  add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  edit_time TIMESTAMP COMMENT '修正时间',

  longitude DOUBLE(10,6) COMMENT '发布经度',
  latitude DOUBLE(10,6)  COMMENT '发布纬度',
  geohash VARCHAR(32) COMMENT 'geohash',
  area VARCHAR(64) COMMENT '发布地点',
  reply_count int COMMENT '回复的数量',

  PRIMARY KEY (id),
  INDEX ind_tb_asking_id (id),
  INDEX ind_tb_asking_uid (uid)
)
  COMMENT = '用户标签表'
;
