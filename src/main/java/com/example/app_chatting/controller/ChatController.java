package com.example.app_chatting.controller;

import com.example.app_chatting.domain.ChatMessage;
import com.example.app_chatting.domain.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private List<ChatMessage> messageList = new ArrayList<>();

    //post response dto
    @AllArgsConstructor
    @Getter
    public static class WriteMessageResponse{
        private final Long id;
    }
    //post request dto
    @AllArgsConstructor
    @Getter
    public static class WriteMessageRequest{
        private final String authorName;
        private final String content;
    }

    @PostMapping("/messages")
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest request){
        ChatMessage message = new ChatMessage(request.getAuthorName(), request.getContent());
        messageList.add(message);
        return new RsData<>("S-1", "메세지가 작성되었습니다.", new WriteMessageResponse(message.getId()));
    }
    @GetMapping("/messages")
    public RsData<List<ChatMessage>> getMessages(){
        return new RsData<>("S-1", "성공했습니다.", messageList);
    }
}
