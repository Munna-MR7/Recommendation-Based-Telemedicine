package com.project.Recommendation_Based.Telemedicine.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ZegoCloudService {

    private final String serverUrl = "wss://webliveroom1572906346-api.coolzcloud.com/ws";
    private final String appId = "1572906346";
    private final String serverSecret = "3e1e0b4aa66f275c7473641033ce5ca8";
    private final String appSign = "5b33ae46f2a9b7267d47fc2d77e2507200d421141b57a65c637990835bc4db76";

    private final WebClient webClient = WebClient.create(serverUrl);

    public Mono<String> createRoom(String roomId) {
        String body = "{ \"room_id\": \"" + roomId + "\", \"appId\": \"" + appId + "\" }";
        return webClient.post()
                .uri("/createRoom")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> joinRoom(String userId, String roomId) {
        String body = "{ \"user_id\": \"" + userId + "\", \"room_id\": \"" + roomId + "\" }";
        return webClient.post()
                .uri("/joinRoom")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> leaveRoom(String userId, String roomId) {
        String body = "{ \"user_id\": \"" + userId + "\", \"room_id\": \"" + roomId + "\" }";
        return webClient.post()
                .uri("/leaveRoom")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class);
    }
}
