package org.flab.deliveryplatform.server.websocket;

import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.UserPrinciple;
import org.flab.deliveryplatform.common.auth.UserType;
import org.flab.deliveryplatform.shop.application.port.GetShopUseCase;
import org.flab.deliveryplatform.shop.application.port.dto.ShopDetailData;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
@RequiredArgsConstructor
public class ShopPermissionInterceptor implements HandshakeInterceptor {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final GetShopUseCase getShopUseCase;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
        Map<String, Object> attributes) throws Exception {

        String path = request.getURI().getPath();
        if (!antPathMatcher.match("/ws/owners/shops", path)) {
            return true;
        }
        Long shopId = parseShopId(request.getURI().getQuery());
        Long ownerId = parseOwnerId(request);
        ShopDetailData shop = getShopUseCase.getShop(shopId);
        return shop.getOwnerId().equals(ownerId);
    }

    private Long parseShopId(String queryString) {
        String[] queries = queryString.split("&");
        for (String query : queries) {
            String[] split = query.split("=");
            if (split[0].equals("id")) {
                return Long.parseLong(split[1]);
            }
        }
        throw new RuntimeException();
    }

    private Long parseOwnerId(ServerHttpRequest request) {
        UserPrinciple principal = (UserPrinciple) Objects.requireNonNull(request.getPrincipal());
        if (principal.getUserType() != UserType.OWNER) {
            throw new RuntimeException();
        }
        return principal.getUserId();
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
        Exception exception) {
    }
}
