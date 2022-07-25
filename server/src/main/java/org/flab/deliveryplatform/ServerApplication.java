package org.flab.deliveryplatform;

import org.flab.deliveryplatform.member.interfaces.MemberContextConfiguration;
import org.flab.deliveryplatform.owner.interfaces.OwnerContextConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({MemberContextConfiguration.class, OwnerContextConfiguration.class})
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class);
    }
}
