package com.example.app_chatting.controller;

import com.example.app_chatting.domain.ChatMessage;
import com.example.app_chatting.domain.RsData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private List<ChatMessage> messageList = new ArrayList<>();
    @PostMapping("/writeMessage")
    public RsData<ChatMessage> writeMessage(){
        ChatMessage message = new ChatMessage("hoon", "hi");
        return new RsData<>("S-1", "메세지가 작성되었습니다.", message);
    }
}
