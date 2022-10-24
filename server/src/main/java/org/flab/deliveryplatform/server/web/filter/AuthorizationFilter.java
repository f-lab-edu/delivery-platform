package org.flab.deliveryplatform.server.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.geom.IllegalPathStateException;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.AuthConst;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.common.auth.AuthorizationUseCase;
import org.flab.deliveryplatform.common.auth.Token;
import org.flab.deliveryplatform.common.auth.TokenType;
import org.flab.deliveryplatform.common.auth.User;
import org.flab.deliveryplatform.common.auth.UserType;
import org.flab.deliveryplatform.common.auth.exception.ExpiredAuthorizationException;
import org.flab.deliveryplatform.common.auth.exception.InvalidAuthorizationException;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.flab.deliveryplatform.server.auth.AuthorizationServiceFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final ObjectMapper objectMapper;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final String memberPathPrefix = "/members/";
    private final String ownerPathPrefix = "/owners/";
    private final String[] excludedPathPatterns = {"/**/login", "/**/signUp", "/actuator/**",
        "/admin/**" // temporal admin pattern for websocket send message test
    };
    private final AuthorizationServiceFactory authorizationServiceFactory;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        if (!isExcludedPath(request)) {
            try {
                UserType userType = parseUserType(request);

                Token token = parseToken(request);

                AuthorizationUseCase authorizationUseCase =
                    authorizationServiceFactory.getAuthorizationUseCase(userType);

                AuthorizationData authorizationData = authorizationUseCase.getAuthorizationData(token.getToken());

                checkIsExpired(authorizationData);

                request.setAttribute(AuthConst.USER_INFO_KEY, new User(userType, authorizationData.getUserId()));
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
        if (authorizationData.getExpiresIn() <= 0) {
            throw new ExpiredAuthorizationException();
        }
    }

    private boolean isExcludedPath(HttpServletRequest request) {
        return Arrays.stream(excludedPathPatterns)
            .anyMatch(pattern -> antPathMatcher.match(pattern, request.getRequestURI()));
    }

    private Token parseToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        String[] token = authorizationHeader.split(" ");
        try {
            return new Token(TokenType.valueOf(token[0].toUpperCase()), token[1]);
        } catch (Exception e) {
            throw new InvalidAuthorizationException();
        }
    }

    private UserType parseUserType(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith(memberPathPrefix) || requestURI.startsWith("/ws" + memberPathPrefix)) {
            return UserType.MEMBER;
        } else if (requestURI.startsWith(ownerPathPrefix) || requestURI.startsWith("/ws" + ownerPathPrefix)) {
            return UserType.OWNER;
        }
        throw new IllegalPathStateException();
    }
}
