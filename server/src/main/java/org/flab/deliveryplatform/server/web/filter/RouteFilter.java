package org.flab.deliveryplatform.server.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformErrorResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class RouteFilter extends OncePerRequestFilter {

    private final String[] excludedPathPatterns = {"/**/login", "/**/signUp"};

    private final ObjectMapper objectMapper;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final String[] users = {"members", "owners"};

    private final String[] domains = {"orders", "shops"};

    private final Pattern userPattern;

    private final Pattern domainPattern;


    public RouteFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.userPattern = generateUserPattern();
        this.domainPattern = generateDomainPattern();
    }

    private Pattern generateUserPattern() {
        String concatenatedUsers = String.join("|", users);
        String regex = "/(" + concatenatedUsers + ")";
        return Pattern.compile(regex);
    }

    private Pattern generateDomainPattern() {
        String concatenatedDomains = String.join("|", domains);
        String regex = "^.*/(" + concatenatedDomains + ").*$";
        return Pattern.compile(regex);
    }

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
        return Arrays.stream(excludedPathPatterns)
            .anyMatch(pattern -> antPathMatcher.match(pattern, request.getRequestURI()));
    }

    private String convertURI(String uri) {
        return hasDomainPath(uri) ? userPattern.matcher(uri).replaceFirst("") : uri;
    }

    private boolean hasDomainPath(String uri) {
        return domainPattern.matcher(uri).matches();
    }

}
