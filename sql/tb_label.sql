# Created by wangshuisheng on 2015/5/25.

CREATE TABLE tb_label(
  id INT(11) NOT NULL COMMENT 'id',
  uid INT(11) NOT NULL COMMENT '用户id',
  label_name VARCHAR(16) COMMENT '标签名称',
  label_detail VARCHAR(300) NOT NULL COMMENT '标签详情',


  add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  edit_time TIMESTAMP COMMENT '修改时间',

  longitude DOUBLE(10,6) COMMENT '发布经度',
  latitude DOUBLE(10,6)  COMMENT '发布纬度',
  geohash VARCHAR(32) COMMENT 'geohash',
  area VARCHAR(64) COMMENT '注册地区',
  position_type TINYINT(2) COMMENT '坐标类型 1固定 2移动',
  deadline_type TINYINT(2) COMMENT '有效时间类型 1永久 2时间段',
  start_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
  end_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',

  zan int COMMENT '赞的数量',
  msg VARCHAR(32) COMMENT 'msg',
  status TINYINT(2) COMMENT '标签状态 1创建 2生效 3失效 4废除',

  PRIMARY KEY (id),
  INDEX ind_tb_label_id (id),
#   INDEX ind_tb_label_uid (uid),
  INDEX ind_tb_label_uid_status (uid,status)
#   INDEX ind_tb_label_name (label_name)
)
  COMMENT = '用户标签表'
;
