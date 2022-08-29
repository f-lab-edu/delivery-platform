package org.flab.deliveryplatform.server.web.resolver;

import javax.servlet.http.HttpServletRequest;
import org.flab.deliveryplatform.common.auth.AuthConst;
import org.flab.deliveryplatform.common.auth.User;
import org.flab.deliveryplatform.common.auth.UserInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserInfoArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(UserInfo.class);
        boolean isUserType = parameter.getParameterType().equals(User.class);
        return hasAnnotation && isUserType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest();
        return servletRequest.getAttribute(AuthConst.USER_INFO_KEY);
    }
}
