package com.yff.consultantspringbootlangchain4j.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author yff
 * @date 2026-01-31 14:03:25
 */
@Service
@AiService(
        wiringMode = AiServiceWiringMode.EXPLICIT,//手动装配
        chatModel = "openAiChatModel",//指定使用的chatModel
        streamingChatModel = "openAiStreamingChatModel",//指定使用的流式chatModel
//        chatMemory = "chatMemory",//指定使用的记忆组件
        chatMemoryProvider = "chatMemoryProvider", //指定使用的记忆组件提供者
        contentRetriever = "contentRetriever" //指定使用的内容检索器
)
//@AiService  这里可以不写参数，默认就是自动装配
public interface ConsultantService {

    //用于里聊天的方法
//    String chat(String message);

    //流式返回
    @SystemMessage(fromResource = "system.txt") //系统消息，设置AI的角色
    Flux<String> chat(@MemoryId String memoryId,@UserMessage String message);


}
