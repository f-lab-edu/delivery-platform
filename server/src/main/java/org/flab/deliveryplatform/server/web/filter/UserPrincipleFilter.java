package org.flab.deliveryplatform.server.web.filter;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.flab.deliveryplatform.common.auth.AuthConst;
import org.flab.deliveryplatform.common.auth.User;
import org.flab.deliveryplatform.common.auth.UserPrinciple;
import org.springframework.web.filter.OncePerRequestFilter;

public class UserPrincipleFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        request = new UserPrincipleHttpServletRequest(request, generateUserPrinciple(request));

        filterChain.doFilter(request, response);
    }

    private Principal generateUserPrinciple(HttpServletRequest request) {
        User user = (User) request.getAttribute(AuthConst.USER_INFO_KEY);
        if (user == null) {
            return null;
        }
        return new UserPrinciple(user.getUserType(), user.getUserId());
    }
}
