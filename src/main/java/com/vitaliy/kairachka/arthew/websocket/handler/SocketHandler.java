package com.vitaliy.kairachka.arthew.websocket.handler;

import com.sun.istack.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.lang.NonNullApi;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Vitaliy Kayrachka
 */
public class SocketHandler extends TextWebSocketHandler implements WebSocketHandler {

  private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    sessions.add(session);
    super.afterConnectionEstablished(session);
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session);
    super.afterConnectionClosed(session, status);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    super.handleTextMessage(session, message);
    sessions.forEach(webSocketSession -> {
      try {
        webSocketSession.sendMessage(message);
      } catch (IOException e) {
//        LOGGER.error("Error occurred.", e); TODO
      }
    });
  }
}
