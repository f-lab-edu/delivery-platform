package org.flab.deliveryplatform.server.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.geom.IllegalPathStateException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.common.auth.AuthorizationUseCase;
import org.flab.deliveryplatform.common.auth.UserType;
import org.flab.deliveryplatform.common.auth.exception.ExpiredAuthorizationException;
import org.flab.deliveryplatform.common.auth.exception.InvalidAuthorizationException;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.server.auth.AuthorizationServiceFactory;
import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(2)
@WebFilter
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final String[] excludedPathPatterns = {"/**/login", "/**/signUp"};

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String TOKEN_TYPE = "Bearer ";

    private static final long expiredTimeMillis = 1_800_000L;

    private final AuthorizationServiceFactory authorizationServiceFactory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        if (!isExcludedPath(request)) {
            try {
                UserType userType = parseUserType(request);

                String token = parseToken(request);

                AuthorizationUseCase authorizationUseCase =
                    authorizationServiceFactory.getAuthorizationUseCase(userType);

                AuthorizationData authorizationData = authorizationUseCase.getAuthorizationData(token);

                checkIsExpired(authorizationData);

                request.setAttribute(getFitUserIdName(userType), authorizationData.getUserId());
            } catch (ExpiredAuthorizationException tee) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                DeliveryPlatformErrorResponse<Object> errorResponse = DeliveryPlatformErrorResponse.error(null,
                    "EXPIRED_TOKEN", null);
                response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
                return;
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                DeliveryPlatformErrorResponse<Object> errorResponse = DeliveryPlatformErrorResponse.error(null,
                    "UNAUTHORIZED", null);
                response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private void checkIsExpired(AuthorizationData authorizationData) {
        if (System.currentTimeMillis() - Timestamp.valueOf(authorizationData.getIssueDate()).getTime()
            > expiredTimeMillis) {
            throw new ExpiredAuthorizationException();
        }
    }

    private boolean isExcludedPath(HttpServletRequest request) {
        return Arrays.stream(excludedPathPatterns)
            .anyMatch(pattern -> antPathMatcher.match(pattern, request.getRequestURI()));
    }

    private String parseToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader.startsWith(TOKEN_TYPE)) {
            return authorizationHeader.substring(TOKEN_TYPE.length());
        }
        throw new InvalidAuthorizationException();
    }

    private UserType parseUserType(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/members/")) {
            return UserType.MEMBER;
        } else if (requestURI.startsWith("/owners/")) {
            return UserType.OWNER;
        }
        throw new IllegalPathStateException();
    }

    private String getFitUserIdName(UserType userType) {
        switch (userType) {
            case MEMBER:
                return "memberId";
            case OWNER:
                return "ownerId";
            default:
                throw new IllegalArgumentException();
        }
    }

}
