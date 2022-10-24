package org.flab.deliveryplatform.server.web.filter;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UserPrincipleHttpServletRequest extends HttpServletRequestWrapper {

    private final Principal userPrinciple;

    public UserPrincipleHttpServletRequest(HttpServletRequest request, Principal userPrinciple) {
        super(request);
        this.userPrinciple = userPrinciple;
    }

    @Override
    public Principal getUserPrincipal() {
        return userPrinciple;
    }
}
