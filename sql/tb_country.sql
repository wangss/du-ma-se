
# Created by wangshuisheng on 2015/5/21.

CREATE TABLE tb_country_area_code(
  country_or_region VARCHAR(32) COMMENT '国家或地区',
  name VARCHAR(32)  COMMENT '国家或地区名称',
  for_short VARCHAR(3) COMMENT '国际域名缩写',
  area_code INT(4)  COMMENT '区号',
  moveut DOUBLE(4,2)  COMMENT '时差',
  INDEX ind_tb_country_area_code (area_code)
)
  COMMENT = '国家代号与区号'
;

#
# CREATE INDEX ind_tb_user_id ON tb_user(id);
# CREATE INDEX ind_tb_user_tr_id ON tb_user(tr_id);
# CREATE INDEX ind_tb_user_phone ON tb_user(phone);
