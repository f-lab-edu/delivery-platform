#!/bin/bash
sleep 10

master_log_file=`mysql -uroot -p'root' -h 172.16.0.10 -S /var/lib/mysql/mysql.sock -e "show master status\G" | grep mysql-bin`
re="[a-z]*-bin.[0-9]*"

if [[ ${master_log_file} =~ $re ]];then
    master_log_file=${BASH_REMATCH[0]}
fi

master_log_pos=`mysql -uroot -p'root' -h 172.16.0.10 -S /var/lib/mysql/mysql.sock -e"show master status\G" | grep Position`
re="[0-9]+"

if [[ ${master_log_pos} =~ $re ]];then
    master_log_pos=${BASH_REMATCH[0]}
fi

query="change master to master_host='172.16.0.10', master_user='root', master_password='root', master_log_file='${master_log_file}', master_log_pos=${master_log_pos}"

mysql -uroot -p'root' -S /var/lib/mysql/mysql.sock -e"${query}"
mysql -uroot -p'root' -S /var/lib/mysql/mysql.sock -e"start slave"

/bin/bash
