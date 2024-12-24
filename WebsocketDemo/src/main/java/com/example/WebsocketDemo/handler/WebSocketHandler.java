package com.example.WebsocketDemo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger log= LoggerFactory.getLogger(WebSocketHandler.class);
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established: Session ID = {}", session.getId());
        session.sendMessage(new TextMessage(session.getId()));
    }
//
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        log.info("Message received from Session ID = {}: Payload = {}", session.getId(), message.getPayload());
//        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
//    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("Text Message received from Session ID = {}: Payload = {}", session.getId(), message.getPayload());
        session.sendMessage(new TextMessage("You sent: " + message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String reason = status.getReason() != null ? status.getReason() : "No specific reason provided";
        log.info("Connection closed: Session ID = {}, Close Status = {}, Reason = {}", session.getId(), status, reason);
        super.afterConnectionClosed(session, status);
    }
}
