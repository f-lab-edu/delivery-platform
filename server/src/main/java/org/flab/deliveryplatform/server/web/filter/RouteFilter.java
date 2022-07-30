package org.flab.deliveryplatform.server.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.springframework.core.annotation.Order;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Order(1)
@WebFilter
@RequiredArgsConstructor
public class RouteFilter extends OncePerRequestFilter {

    private final String[] excludedPathPatterns = {"/**/login", "/**/signUp"};

    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        if (!isExcludedPath(request)) {
            try {
                String requestURI = request.getRequestURI();
                request.getRequestDispatcher(convertURI(requestURI)).forward(request, response);
                return;
            } catch (NotSupportedURIException ex) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                DeliveryPlatformErrorResponse<Object> errorResponse = DeliveryPlatformErrorResponse.error(null,
                    "NOT_FOUND", null);
                response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            }
        }
        filterChain.doFilter(request, response);
    }


    private boolean isExcludedPath(HttpServletRequest request) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return Arrays.stream(excludedPathPatterns)
            .anyMatch(pattern -> antPathMatcher.match(pattern, request.getRequestURI()));
    }

    private String convertURI(String uri) {
        if (uri.startsWith("/members/")) {
            return uri.replace("/members", "");
        } else if (uri.startsWith("/owners/")) {
            return uri.replace("/owners", "");
        }
        throw new NotSupportedURIException();
    }
}
