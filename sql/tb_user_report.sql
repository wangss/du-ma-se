CREATE TABLE tb_user_report(
  id VARCHAR(32) NOT NULL COMMENT 'id',
  uid INT(11) NOT NULL COMMENT '举报人uid',
  defendant INT(11) NOT NULL  COMMENT '被举报人 Id',
  reason TINYINT(2) COMMENT '举报原因 1色情信息 2骚扰信息 3虚假广告 4欺诈 5其它',
  reason_msg VARCHAR(32) COMMENT '举报说明',
  report_time TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  edit_time TIMESTAMP  COMMENT '修改时间',
  status TINYINT(2) COMMENT '状态: 1新建 2正在处理 3已处理 4已驳回',
  operator INT(11) COMMENT '操作人',
  have_noticed TINYINT(2) COMMENT '通知举报对象 0未通知 1已通知',
  msg VARCHAR(32) COMMENT 'msg',

  PRIMARY KEY (id),
  INDEX ind_tb_user_report_uid (uid),
  INDEX ind_tb_user_report_uid_defendant (uid, defendant)
)
  COMMENT = '用户举报信息'
;
