create schema if not exists test;
set schema test;

drop table if exists test.test;
create table test.test(
id number(10),
name varchar(20)
);
commit;

insert into test.test(id, name) values(1, 'apple');
commit;
