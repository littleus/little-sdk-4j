create schema if not exists test;
set schema test;

drop table if exists test.setting_info;
create table test.setting_info(
paramKey varchar(10),
paramValue varchar(200)
);
commit;

insert into test.setting_info(paramKey, paramValue) values('first', 'apple');
commit;