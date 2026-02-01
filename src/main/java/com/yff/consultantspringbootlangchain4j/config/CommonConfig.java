package com.yff.consultantspringbootlangchain4j.config;


import com.yff.consultantspringbootlangchain4j.service.ConsultantService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.ClassPathDocumentLoader;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author yff
 * @date 2026-01-31 14:04:31
 */
@Configuration
public class CommonConfig {

    @Autowired
    private OpenAiChatModel model;

    @Autowired
    private ChatMemoryStore chatMemoryStore;
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
                        .chatMemoryStore(chatMemoryStore)
                        .build();
            }
        };
        return chatMemoryProvider;
    }


    //构建向量数据库操作对象
    @Bean
    public EmbeddingStore embeddingStore() {

        //加载文档进内存
        List<Document> documents = ClassPathDocumentLoader.loadDocuments("content");

        //构建向量数据库操作对象
        InMemoryEmbeddingStore inMemoryEmbeddingStore = new InMemoryEmbeddingStore();

        //构建一个EmbeddingStoreIngestor对象，完成文本数据切割，向量化，存储
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(inMemoryEmbeddingStore)
                .build();
        ingestor.ingest(documents);//将指定文档提取到创建此EmbeddingStoreIngestor期间指定的EmbeddingStore中

        return inMemoryEmbeddingStore;
    }

    //构建向量数据库存储对象
    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore embeddingStore) {

       return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .minScore(0.5)//设置最小相似度
                .maxResults(3)//设置最多返回3条
                .build();

    }

}
