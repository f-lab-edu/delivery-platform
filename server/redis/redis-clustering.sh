#!/bin/bash 
### input: [(ip:port 1) (ip:port 2) ...]  
### supporting for cluster configuration such that master node has only 1 slave node 

IP_REGEX="^(([0-9]{1,3}\.){3})([0-9]{1,3}):([0-9]+)$"

function check_input_node_configuration {
  echo "input nodes configuration: $*"
  local node_counts=$#
  
  local mod=`expr $node_counts % 2`
  
  if [ $mod -ne 0 ]; then
    echo "total node counts must be even" && exit -1
  fi
  
  for node in $*
  do
    if [[ ! $node =~ $IP_REGEX ]]; then
      echo "node info $node dose not ip:port format" && exit -1
    fi
  done
}


function health_check {
  local response=""
  local ip=`echo $1 | cut -d : -f 1`
  local port=`echo $1 | cut -d : -f 2`
  
  local threshold=5
  local count=1

  echo "Node ip:$ip port:$port health check"
  while [[ "${count}" -le "${threshold}" ]]
  do
    echo "Health checking... ($count sec / $threshold sec)"
    response="$(redis-cli -h $ip -p $port ping)"
    if [ "${response}" = "PONG" ]; then
      echo "node $1 connected"
      break
    else
      count=`expr $count + 1`
      sleep 1
    fi
  done

  if [[ "${count}" -gt "${threshold}" ]]; then
    echo "cannot connect node (ip: $ip, port: $port)" && exit -1
  fi
}

function health_check_all {
  for node in $*
  do
    health_check $node
  done
}

check_input_node_configuration $*
health_check_all $*

redis-cli --cluster create $* --cluster-replicas 1 --cluster-yes
