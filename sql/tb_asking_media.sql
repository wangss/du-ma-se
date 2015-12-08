# Created by wangshuisheng on 2015/5/25.

CREATE TABLE tb_asking_media(
  id INT(11) NOT NULL COMMENT 'id',
  asking_id INT(11) NOT NULL COMMENT '喊话id',
  url VARCHAR(64) COMMENT 'url',
  media_type TINYINT(2) COMMENT '类型 1图片 2声音 3视频 4...',
  add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  edit_time TIMESTAMP COMMENT '修改时间',
  seq TINYINT(2) COMMENT '排序号',
  msg VARCHAR(32) COMMENT 'msg',
  status TINYINT(2) COMMENT '状态 1创建 2生效 3失效 4废除',

  PRIMARY KEY (id),
  INDEX ind_tb_asking_media_id (id),
#   INDEX ind_tb_asking_media_lid (asking_id),
  INDEX ind_tb_asking_media_lid_status (asking_id, status)

)
  COMMENT = '喊话媒体'
;
