package com.project.Recommendation_Based.Telemedicine.Config;
import org.cloudinary.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebRTCSignalingHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JSONObject jsonMessage = new JSONObject(message.getPayload());

        String type = jsonMessage.getString("type");
        String roomId = jsonMessage.getString("roomId");

        switch (type) {
            case "joinRoom":
                sessions.put(roomId, session);
                broadcastToRoom(roomId, new TextMessage("{\"type\":\"newJoining\"}"), session);
                break;
            case "offer":
            case "answer":
            case "iceCandidate":
                broadcastToRoom(roomId, message, session);
                break;
        }
    }

    private void broadcastToRoom(String roomId, TextMessage message, WebSocketSession sender) {
        sessions.values().forEach(session -> {
            if (session != sender) {
                try {
                    session.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
