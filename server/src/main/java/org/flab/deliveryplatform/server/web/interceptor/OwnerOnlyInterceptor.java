package org.flab.deliveryplatform.server.web.interceptor;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.flab.deliveryplatform.common.auth.AuthConst;
import org.flab.deliveryplatform.common.auth.OwnerOnly;
import org.flab.deliveryplatform.common.auth.User;
import org.flab.deliveryplatform.common.auth.UserType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class OwnerOnlyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        HandlerMethod hm;
        try {
            hm = (HandlerMethod) handler;
        } catch (ClassCastException e) {
            return true;
        }
        
        Method method = hm.getMethod();
        if (method.getDeclaringClass().isAnnotationPresent(OwnerOnly.class) || method.isAnnotationPresent(
            OwnerOnly.class)) {
            User user = (User) request.getAttribute(AuthConst.USER_INFO_KEY);
            return user.getUserType() == UserType.OWNER;
        }
        return true;
    }
}
