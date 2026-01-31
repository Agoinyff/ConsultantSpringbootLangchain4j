package com.yff.consultantspringbootlangchain4j.repository;


import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yff
 * @date 2026-01-31 16:41:38
 */
@Repository
public class RedisChatMemoryStore implements ChatMemoryStore {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String json = redisTemplate.opsForValue().get(memoryId.toString());
        if (json != null) {
            return ChatMessageDeserializer.messagesFromJson(json);
        }
        //redis中为空时查询数据库
        return List.of(); //这里暂时粗理一下
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> list) {

        //更新会话信息
        //1.把list转换为json字符串
        String json = ChatMessageSerializer.messagesToJson(list);
        //2.存储到redis中
        redisTemplate.opsForValue().set(memoryId.toString(),json, Duration.ofHours(1));
    }

    @Override
    public void deleteMessages(Object memoryId) {
        redisTemplate.delete(memoryId.toString());
    }
}
