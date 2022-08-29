package org.flab.deliveryplatform.order.interfaces.web.member;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members/orders")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberOrderRestController {

}
