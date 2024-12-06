package com.java.features.java9.service;

public interface MessageService {
    String getMessage();
}

// Implementation class in the same package
class DefaultMessageService implements MessageService {
    @Override
    public String getMessage() {
        return "Hello from the message service!";
    }
}
