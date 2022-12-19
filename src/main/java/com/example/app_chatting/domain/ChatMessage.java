package com.example.app_chatting.domain;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class ChatMessage {
    private Long id;
    private LocalDateTime createdDate;
    private String authorName;
    private String content;

    public ChatMessage(String authorName, String content) {
        this(ChatMessageIdGenerator.genNextId(), LocalDateTime.now(), authorName, content);
    }
}

class ChatMessageIdGenerator{
    private static Long id = 0L;
    public static long genNextId(){
        return id++;
    }
}