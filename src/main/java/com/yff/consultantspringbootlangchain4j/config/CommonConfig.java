package com.yff.consultantspringbootlangchain4j.config;


import com.yff.consultantspringbootlangchain4j.service.ConsultantService;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yff
 * @date 2026-01-31 14:04:31
 */
@Configuration
public class CommonConfig {

    @Autowired
    private OpenAiChatModel model;
//
//    @Bean   //注入容器
//    public ConsultantService consultantServiceConfig() {
//        return AiServices.builder(ConsultantService.class)
//                .chatModel(model)
//                .build(); //为其创建代理对象
//    }

    //构建会话记忆对象
    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory
                .builder()
                .maxMessages(20) //最多记住20条消息
                .build();
    }


    //构建ChatMemoryProvider对象
    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        ChatMemoryProvider chatMemoryProvider = new ChatMemoryProvider() {
            @Override
            public ChatMemory get(Object memoryId) {
               return MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(20)
                        .build();
            }
        };
        return chatMemoryProvider;
    }


}
