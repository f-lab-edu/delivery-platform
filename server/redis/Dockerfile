FROM amd64/redis:6.2-alpine

ADD redis-clustering.sh /usr/local/bin/docker-entrypoint.sh

ENTRYPOINT ["sh", "/usr/local/bin/docker-entrypoint.sh"]

