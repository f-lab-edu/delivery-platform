package org.flab.deliveryplatform.member.interfaces.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberRestController {

}
