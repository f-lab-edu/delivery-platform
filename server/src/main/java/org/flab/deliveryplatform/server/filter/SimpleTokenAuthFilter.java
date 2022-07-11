package org.flab.deliveryplatform.server.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
import org.flab.deliveryplatform.member.domain.authorization.AuthorizationId;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Component
public class SimpleTokenAuthFilter extends OncePerRequestFilter {

    private final AuthorizationRepository authorizationRepository;
    private final ObjectMapper objectMapper;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_TYPE = "Bearer ";
    private static final String LOGIN_URI = "/members/login";
    private static final String SIGN_UP_URI = "/members";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        try {
            if (isExcludePath(request)) {
                filterChain.doFilter(request, response);
            }

            String accessToken = parseAccessToken(request);
            if (accessToken == null) {
                throw new IllegalArgumentException();
            }

            Authorization authorization = authorizationRepository.findById(new AuthorizationId(accessToken))
                .orElseThrow(IllegalArgumentException::new);

            request.setAttribute("memberId", authorization.getMemberId());
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            DeliveryPlatformErrorResponse<Object> errorResponse = DeliveryPlatformErrorResponse.error(null, null, null);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
        }
    }

    private String parseAccessToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(authorization) && authorization.startsWith(TOKEN_TYPE)) {
            return authorization.substring(TOKEN_TYPE.length());
        }

        return null;
    }

    private boolean isExcludePath(HttpServletRequest request) {
        if (request.getRequestURI().equals(LOGIN_URI) && HttpMethod.POST.matches(request.getMethod())) {
            return true;
        }

        if (request.getRequestURI().equals(SIGN_UP_URI) && HttpMethod.POST.matches(request.getMethod())) {
            return true;
        }

        return false;
    }
}
