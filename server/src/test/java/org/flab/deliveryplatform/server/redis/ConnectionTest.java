package org.flab.deliveryplatform.server.redis;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

@SpringBootTest
public class ConnectionTest {

    @Autowired
    RedisClusterProperties redisProperties;

    // Test for redis connection
//    @Test
    void test() {
        List<String> nodes = redisProperties.getNodes();
        List<RedisURI> redisURIs = nodes.stream()
            .map(node -> node.split(":"))
            .map(split -> RedisURI.create(split[0], Integer.parseInt(split[1])))
            .collect(Collectors.toList());

        RedisClusterClient clusterClient = RedisClusterClient.create(redisURIs);
        StatefulRedisClusterConnection<String, String> connection = clusterClient.connect();

        RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
        String ping = syncCommands.ping();
        Assertions.assertThat(ping.toLowerCase()).isEqualTo("pong");
    }

    @Getter
    @Setter
    @TestConfiguration
    @ConfigurationProperties(prefix = "spring.redis.cluster")
    static class RedisClusterProperties {

        private List<String> nodes;
    }
}
