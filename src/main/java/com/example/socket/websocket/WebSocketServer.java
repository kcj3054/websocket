package com.example.socket.websocket;

import com.example.socket.CustomSpringConfigurator;
import com.example.socket.service.WebSocketService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Log
@Component
@ServerEndpoint(value = "/websocket", configurator = CustomSpringConfigurator.class)
public class WebSocketServer {
    private static Set<Session> sessions = new CopyOnWriteArraySet<>();

    private static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            log.warning(session.getId() + ", error: " + e.getMessage());
        }
    }

    @Autowired
    WebSocketService webSocketService;

    private static void broadcast(Session _session, String message) {
        for (Session session : sessions) {
            if (session == _session) continue;
            sendMessage(session, message);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        webSocketService.a();
        sessions.add(session);
        log.info("onOpen: " + sessions.size());
        sendMessage(session, "Hi!");
//        broadcast(session, "Halo! Others");
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info(message);
        broadcast(session, message);
    }

    // 통신 중 에러가 발생할 경우
    @OnError
    public void onError(Session session, Throwable throwable) {
        sessions.remove(session);
        log.warning("error: " + throwable.getMessage());
    }

    // server 또는 client 중 어느 하나가 통신을 끊는 경우
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        log.info("onClose: " + sessions.size());
    }
}
