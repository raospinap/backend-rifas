package com.rifa.adapters.out.external.dto;

import java.util.List;

public class OpenAIRequest {

    private String model = "gpt-3.5-turbo";
    private List<Message> messages;

    public OpenAIRequest(String prompt) {
        this.messages = List.of(new Message("user", prompt));
    }

    public String getModel() {
        return model;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public static class Message {
        private String role;
        private String content;

        public Message() {}

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public String getContent() {
            return content;
        }
    }
}
