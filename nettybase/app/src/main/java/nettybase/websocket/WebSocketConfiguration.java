package nettybase.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import nettybase.pojo.ChatMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

@Configuration
public class WebSocketConfiguration {

    private final CopyOnWriteArrayList<FluxSink<WebSocketMessage>>
            connections = new CopyOnWriteArrayList<>();

    @Bean
    public HandlerMapping webSocketMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setUrlMap(Collections.singletonMap("/ws/chat", chatHandler()));
        mapping.setOrder(-1); // before annotated controllers
        return mapping;
    }

    @Bean
    public WebSocketHandler chatHandler() {
        return session -> {
            Flux<WebSocketMessage> output = Flux.create(sink -> {
                connections.add(sink);
                sink.onDispose(() -> connections.remove(sink));
            });

            AtomicReference<FluxSink<WebSocketMessage>> currentSink = new AtomicReference<>();
            session.receive()
                    .map(WebSocketMessage::getPayloadAsText)
                    .map(json -> {
                        try {
                            return new ObjectMapper().readValue(json, ChatMessage.class);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .doOnNext(msg -> connections.forEach(sink -> {
                        currentSink.set(sink);
                        sink.next(session.textMessage(msg.getMessage()));
                    }))
                    .doOnError(ex -> {
                        if (currentSink.get() != null) {
                            currentSink.get().error(ex);
                        }  // Handle the error in another way if currentSink has not been set

                    })
                    .subscribe();
            return session.send(output);
        };
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
