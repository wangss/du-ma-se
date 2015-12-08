select * from tb_user_position

delete from tb_user_report

select * from tb_sequence

update tb_sequence set current_value = 100000001 where name = 'seq_tb_user_id'
update tb_sequence set current_value = 400000001 where name = 'seq_tb_user_head_id'
update tb_sequence set current_value = 700000001 where name = 'seq_tb_user_report_id'
update tb_sequence set current_value = 300000001 where name = 'seq_tb_label_id'
update tb_sequence set current_value = 600000001 where name = 'seq_tb_label_media_id'

select * from tb_user
select * from tb_user_info
select * from tb_label

update tb_label set status =1 where id=300000004



delete from tb_user;
delete from tb_user_info;
delete from tb_user_friend;
delete from tb_user_position;
delete from tb_user_refuse;
delete from tb_user_report;


delete from tb_label;
delete from tb_label_media;
delete from tb_label_zan;

update tb_sequence set current_value = 100000001 where name = 'seq_tb_user_id';
update tb_sequence set current_value = 400000001 where name = 'seq_tb_user_head_id';
update tb_sequence set current_value = 700000001 where name = 'seq_tb_user_report_id';
update tb_sequence set current_value = 300000001 where name = 'seq_tb_label_id';
update tb_sequence set current_value = 600000001 where name = 'seq_tb_label_media_id';




select * from tb_user_report