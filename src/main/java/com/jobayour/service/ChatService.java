package com.jobayour.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatgptService chatgptService;     //챗gpt 라이브러리에서 제공하는 클래스

    public String getChatResponse(String prompt) {
        // 챗gpt에 질문해서 답변 받기
        return chatgptService.sendMessage(prompt);
    }

}
