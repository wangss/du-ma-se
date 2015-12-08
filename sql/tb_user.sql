
# Created by wangshuisheng on 2015/5/21.

CREATE TABLE tb_user(
  id INT(11) NOT NULL COMMENT 'id',
  tr_id VARCHAR(16) COMMENT '淘人id',
  phone VARCHAR(16) NOT NULL COMMENT '手机号',
  register_phone VARCHAR(16) NOT NULL COMMENT '注册手机号',
  area_code INT(4) COMMENT '国际区号',
  password VARCHAR(32) NOT NULL COMMENT '密码',

  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

  device_type TINYINT(2) COMMENT '注册时设备类型 1IOS;2Android;3',
  uuid VARCHAR(32) COMMENT '注册设备uuid',
  ip VARCHAR(16) COMMENT '注册设备的ip',
  token VARCHAR(32) COMMENT '注册用户验证token',
  device_token VARCHAR(32) COMMENT '注册推送消息token',
  net_work_type TINYINT(2) COMMENT '注册网络制式',

  longitude double(10,6)  COMMENT '注册时经度',
  latitude double(10,6)  COMMENT '注册时纬度',
  geohash VARCHAR(32) COMMENT 'geohash',
  area VARCHAR(64) COMMENT '注册地区',

  status TINYINT(2) COMMENT '账号状态 1正常使用;2账号注销; 3强制禁用',
  status_note VARCHAR(32) COMMENT '状态说明',
  easemob_status TINYINT COMMENT '环信账户 1已创建; 0未创建',
  account_update_time TIMESTAMP COMMENT '账户修改时间',
  account_update_msg VARCHAR(32) COMMENT '账户修改信息',
  user_type TINYINT(2) COMMENT '角色 1普通用户 2公司运营人员',


  PRIMARY KEY (id),
  INDEX ind_tb_user_id (id) ,
  INDEX ind_tb_user_tr_id (tr_id) ,
  INDEX ind_tb_user_phone (phone)
)
  COMMENT = '用户表'
;

#
# CREATE INDEX ind_tb_user_id ON tb_user(id);
# CREATE INDEX ind_tb_user_tr_id ON tb_user(tr_id);
# CREATE INDEX ind_tb_user_phone ON tb_user(phone);
