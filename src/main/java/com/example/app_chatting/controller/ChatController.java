package com.example.app_chatting.controller;

import com.example.app_chatting.domain.ChatMessage;
import com.example.app_chatting.domain.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private List<ChatMessage> messageList = new ArrayList<>();
    @AllArgsConstructor
    @Getter
    public static class WriteMessageResponse{
        private final Long id;
    }
    @PostMapping("/messages")
    public RsData<WriteMessageResponse> writeMessage(){
        ChatMessage message = new ChatMessage("hoon", "hi");
        messageList.add(message);
        return new RsData<>("S-1", "메세지가 작성되었습니다.", new WriteMessageResponse(message.getId()));
    }
    @GetMapping("/messages")
    public RsData<List<ChatMessage>> getMessages(){
        return new RsData<>("S-1", "성공했습니다.", messageList);
    }
}
