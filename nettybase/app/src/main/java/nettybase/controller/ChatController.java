package nettybase.controller;

import nettybase.pojo.ChatMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final List<ChatMessage> messages = new CopyOnWriteArrayList<>();

    @PostMapping
    public Mono<ChatMessage> postMessage(@RequestBody ChatMessage message) {
        messages.add(message);
        return Mono.just(message);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatMessage> getMessages() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(tick -> messages)
                .flatMapIterable(list -> list);
    }

}
