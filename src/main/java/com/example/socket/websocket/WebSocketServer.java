package com.example.socket.websocket;

import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Log
@Component
@ServerEndpoint(value = "/websocket")
public class WebSocketServer {
    private static Set<Session> sessions = new CopyOnWriteArraySet<>();

    private static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.warning(session.getId() + ", error: " + e.getMessage());
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        log.info("onOpen: " + sessions.size());
        sendMessage(session, "Hi!");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info(message);
    }
}
