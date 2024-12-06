package com.java.features.java11.services;

import com.java.features.java11.models.Message;

public class ChatClient {
    private final String serverUrl;
    private boolean connected;

    public ChatClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void connect() {
        // Simulate connecting to a chat server
        connected = true;
    }

    public boolean isConnected() {
        return connected;
    }

    public void disconnect() {
        // Simulate disconnecting from a chat server
        connected = false;
    }

    public void sendMessage(String message) {
        // Simulate sending a message
    }

    public Message receiveMessage() {
        // Simulate receiving a message
        return new Message("user", "Hello, World!", System.currentTimeMillis());
    }
}
