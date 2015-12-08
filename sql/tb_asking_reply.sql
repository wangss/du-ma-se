# Created by wangshuisheng on 2015/6/25.

CREATE TABLE tb_asking_reply(
  id INT(11) NOT NULL COMMENT 'id',
  uid INT(11) NOT NULL COMMENT '回复人uid',
  asking_id INT(11) NOT NULL COMMENT '回复的喊话askingId',

  reply_detail VARCHAR(300) NOT NULL COMMENT '回复详情',
  reply_to INT(11) COMMENT '回复谁 reply to',
  add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  edit_time TIMESTAMP COMMENT '修改时间',

  INDEX ind_tb_asking_reply_id (id),
  INDEX ind_tb_asking_reply_asking_id (asking_id),
  INDEX ind_tb_asking_reply_uid (uid),
  INDEX ind_tb_asking_reply_uid_asking_id (uid, asking_id)
)
  COMMENT = '喊话回复表'
;
