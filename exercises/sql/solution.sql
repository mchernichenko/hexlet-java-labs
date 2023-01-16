/* H2 version */
select FIRST_NAME, CAST(BIRTHDAY AS DATE) as BIRTHDAY
from USERS
where birthday > TIMESTAMP '1999-10-23'
  and rownum < 4
order by first_name

/* Postgres version

select FIRST_NAME, CAST(BIRTHDAY AS DATE) as BIRTHDAY
from USERS
where birthday > TIMESTAMP '1999-10-23'
order by first_name
limit 4

*/
