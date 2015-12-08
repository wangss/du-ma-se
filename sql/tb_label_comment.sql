# Created by wangshuisheng on 2015/6/25.

CREATE TABLE tb_label_comment(
  id INT(11) NOT NULL COMMENT 'id',
  uid INT(11) NOT NULL COMMENT '评论人uid',
  label_id INT(11) NOT NULL COMMENT '评论的标签labelId',

  comment_detail VARCHAR(300) NOT NULL COMMENT '评论详情',
  reply_to INT(11) COMMENT '回复谁 reply to',
  add_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  edit_time TIMESTAMP COMMENT '修改时间',

  INDEX ind_tb_label_comment_id (id),
  INDEX ind_tb_label_comment_label_id (label_id),
  INDEX ind_tb_label_comment_uid (uid),
  INDEX ind_tb_label_comment_uid_label_id (uid, label_id)
)
  COMMENT = '标签评论表';
