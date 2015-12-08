# Created by wangshuisheng on 2015/6/25.

CREATE TABLE tb_label_zan(
  label_id INT(11) NOT NULL COMMENT 'labelId',
  uid INT(11) NOT NULL COMMENT 'uid',
  have_zan TINYINT(2) NOT NULL COMMENT '是否已赞美，值为1',
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

  PRIMARY KEY (label_id, uid),
#   INDEX ind_tb_label_zan_id (label_id),
#   INDEX ind_tb_label_zan_uid (uid)
  INDEX ind_tb_label_zan_uid_label_id (uid, label_id)
)
  COMMENT = '用户标签赞表'
;
