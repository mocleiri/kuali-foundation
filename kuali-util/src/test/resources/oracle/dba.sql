select sid, serial#, username, osuser, machine, program, logon_time from v$session where username is not null order by username desc, osuser asc, logon_time desc;


select substr(sys_context('USERENV','IP_ADDRESS'),1,15) as my_ip, substr(sys_context('USERENV','SERVER_HOST'),1,15) as server_host from dual;

select substr(sys_context('USERENV','TERMINAL','IP_ADDRESS','SERVER_HOST'),1,25) as terminal from dual;


select username, machine from v$session where username = 'OLEENV4' order by machine; 

select distinct machine from v$session where username = 'OLEENV4';
 
select distinct username, machine from v$session order by machine, username;

select v.username, v.machine, UTL_INADDR.get_host_address(v.machine) as ip from v$session v where username = 'OLEENV4' order by v.machine; 
