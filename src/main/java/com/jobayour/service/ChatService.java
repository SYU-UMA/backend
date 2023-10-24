package com.jobayour.service;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ChatService {                      // 챗gpt에 string변수를 보내서 리터만 받는 서비스

    private final ChatgptService chatgptService;     //챗gpt 라이브러리에서 제공하는 클래스

    public String getChatResponse(String prompt) {
        // 챗gpt에 질문해서 답변 받기
        String a;       //굳이 없어도될듯 리턴만 받으면 됨. 추후 수정을 위해 일단은 이렇게 로직 짜놓은 것
        a = chatgptService.sendMessage(prompt+"예상질문의 끝에는 반드시 ?로 끝나게 해줘");
        return a;

    }

}
