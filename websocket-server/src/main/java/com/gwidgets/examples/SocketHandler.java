package com.gwidgets.examples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class SocketHandler extends TextWebSocketHandler {
	
	final List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("received a new message " + message.getPayload());
		//session.sendMessage(new TextMessage("received a new message " + message.getPayload()));
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// the messages will be broadcasted to all users.
		sessions.add(session);
	}
	
	public void brodcastMessage(String message){
		sessions.forEach((s) ->{
			try {
				if(s.isOpen())
				   s.sendMessage(new TextMessage(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

}
