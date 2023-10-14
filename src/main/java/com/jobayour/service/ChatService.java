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
        String a;       //굳이 없어도될듯 리턴만 받으면 됨. 추후 수정을 위해 일단은 이렇게 로직 짜놓은 것
        a = chatgptService.sendMessage(prompt+"질문마다 마지막에 .을 통해서 구분해주라");
        return a;

    }

}
