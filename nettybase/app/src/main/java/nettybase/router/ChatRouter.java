package nettybase.router;

import nettybase.handler.ChatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ChatRouter {

    @Bean
    public RouterFunction<ServerResponse> route(ChatHandler chatHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/chat")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), chatHandler::chat);
    }
}
