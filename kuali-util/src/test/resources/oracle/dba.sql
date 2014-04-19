select sid, serial#, username, osuser, machine, program, logon_time from v$session where username is not null order by username desc, osuser asc, logon_time desc;


select substr(sys_context('USERENV','IP_ADDRESS'),1,15) as my_ip, substr(sys_context('USERENV','SERVER_HOST'),1,15) as server_host from dual;

select substr(sys_context('USERENV','TERMINAL','IP_ADDRESS','SERVER_HOST'),1,25) as terminal from dual;


select machine from v$session where username is not null order by machine; 
