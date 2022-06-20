package org.flab.deliveryplatform.member.interfaces;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({MemberContextConfiguration.class})
public class TestContextConfiguration {

}
