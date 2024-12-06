package com.java.features.java9.service.provider;

import com.java.features.java9.service.MessageService;

public class CustomMessageService implements MessageService {
    @Override
    public String getMessage() {
        return "Hello from the custom message service!";
    }
}
