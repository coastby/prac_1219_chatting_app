package com.example.app_chatting.controller;

import com.example.app_chatting.domain.ChatMessage;
import com.example.app_chatting.domain.RsData;
import com.example.app_chatting.util.SseEmitters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@Controller
@RequestMapping("/chat")
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final SseEmitters sseEmitters;  //sseEmitter 모아놓은 클래스
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
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest request){
        ChatMessage message = new ChatMessage(request.getAuthorName(), request.getContent());
        messageList.add(message);
        sseEmitters.noti("chat__messageAdded");     //메세지가 등록되면 Noti
        return new RsData<>("S-1", "메세지가 작성되었습니다.", new WriteMessageResponse(message.getId()));
    }
    @GetMapping("/messages")
    @ResponseBody
    public RsData<List<ChatMessage>> getMessages(@RequestParam(required = false) Long fromId){
        log.info("fromId : {}", fromId);
        long idx = -1;
        if (fromId != null){
//            for (long i = messageList.size()-1; i >= 0; i--){
//                if (messageList.get((int) i).getId() <= fromId){
//                    idx = i;
//                    break;
//                }
//            }

            idx = LongStream.range(0, messageList.size())
                    .map(i -> messageList.size()-1-i)
                    .filter(i -> messageList.get((int) i).getId() <= fromId)
                    .findFirst()
                    .orElse(-1);
        }
        return new RsData<>("S-1", "성공했습니다.", messageList.subList((int) (idx+1), messageList.size()));
    }
    @GetMapping("/room")
    public String showRoom(){
        return "chat/room";
    }
}
