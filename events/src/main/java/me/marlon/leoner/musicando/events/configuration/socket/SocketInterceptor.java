package me.marlon.leoner.musicando.events.configuration.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URI;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class SocketInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map<String, Object> attributes) throws Exception {
        URI uri = request.getURI();

        String path = uri.getPath();
        String gameCode = path.substring(path.lastIndexOf('/') + 1);
        attributes.put("gameCode", gameCode);

        String query = uri.getQuery();
        Stream.of(query.split("&")).forEach(item -> {
            String[] keyValue = item.split("=");
            attributes.put(keyValue[0], keyValue[1]);
        });

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Exception exception) {
        // empty method
    }
}
