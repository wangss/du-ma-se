
-- 因为mysql没有内置的sequence，故需要模拟一个

create table tb_sequence(
  name varchar(50) not null,
  current_value int(11) not null,
  _increment int(2) not null default 1,
  primary key(name)
);

-- tb_user.id sequence, 从100000000开始（共10位）
insert into tb_sequence values('seq_tb_user_id',100000001,1);
insert into tb_sequence values('seq_tb_user_head_id',400000001,1);
insert into tb_sequence values('seq_tb_label_id',300000001,1);
insert into tb_sequence values('seq_tb_label_media_id',600000001,1);

insert into tb_sequence values('seq_tb_user_report_id',700000001,1);


insert into tb_sequence values('seq_tb_label_comment_id',500000001,1);

insert into tb_sequence values('seq_tb_asking_id',300000001,1);
insert into tb_sequence values('seq_tb_asking_reply_id',500000001,1);
insert into tb_sequence values('seq_tb_asking_media_id',600000001,1);


DELIMITER //
create function _nextval(n varchar(50)) returns integer
  begin
    declare _cur int;
    set _cur=(select current_value from tb_sequence where name= n);
    update tb_sequence
    set current_value = _cur + _increment
    where name=n ;
    return _cur;
  end;
//

select _nextval('seq_tb_user_id');
select _nextval('seq_tb_user_head_id');
select _nextval('seq_tb_label_id');
select _nextval('seq_tb_label_media_id');
