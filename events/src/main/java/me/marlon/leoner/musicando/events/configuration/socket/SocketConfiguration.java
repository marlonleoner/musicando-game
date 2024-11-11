package me.marlon.leoner.musicando.events.configuration.socket;

import lombok.RequiredArgsConstructor;
import me.marlon.leoner.musicando.events.handler.SocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class SocketConfiguration implements WebSocketConfigurer {

    private final SocketHandler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/musicando/*")
                .addInterceptors(new SocketInterceptor())
                .setAllowedOrigins("*");
    }
}
