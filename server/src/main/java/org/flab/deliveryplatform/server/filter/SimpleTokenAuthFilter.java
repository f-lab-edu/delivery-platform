package org.flab.deliveryplatform.server.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.exception.InvalidTokenException;
import org.flab.deliveryplatform.member.application.service.provider.TokenProvider;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class SimpleTokenAuthFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer ";
    private static final String LOGIN_URI = "/members/login";
    private static final String SIGN_UP_URI = "/members";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        filterPath(request, response, filterChain);

        String accessToken = parseAccessToken(request);
        if (accessToken == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        Long memberId = null;
        try {
            memberId = tokenProvider.parseToken(accessToken);
        } catch (InvalidTokenException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value());
        }

        request.setAttribute("memberId", memberId);
        filterChain.doFilter(request, response);
    }

    private String parseAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(authorization) && authorization.startsWith(TOKEN_TYPE)) {
            return authorization.substring(TOKEN_TYPE.length());
        }

        return null;
    }

    private void filterPath(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals(LOGIN_URI)
            && HttpMethod.POST.matches(request.getMethod())) {
            filterChain.doFilter(request, response);
        }

        if (request.getRequestURI().equals(SIGN_UP_URI)
            && HttpMethod.POST.matches(request.getMethod())) {
            filterChain.doFilter(request, response);
        }
    }
}
