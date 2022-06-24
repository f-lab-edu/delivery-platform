package org.flab.deliveryplatform.server;

import org.flab.deliveryplatform.member.interfaces.MemberContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MemberContextConfiguration.class})
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class);
    }
}
