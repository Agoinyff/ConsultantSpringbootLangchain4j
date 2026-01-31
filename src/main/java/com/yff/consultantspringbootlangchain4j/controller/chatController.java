package com.yff.consultantspringbootlangchain4j.controller;


import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yff
 * @date 2026-01-31 13:47:10
 */
@RestController
public class chatController {

    @Autowired
    private OpenAiChatModel model;


    @RequestMapping("/chat")
    public String chat(String message){

        String chat = model.chat(message);
        return chat;


    }



}
