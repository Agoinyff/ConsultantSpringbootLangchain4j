package com.yff.consultantspringbootlangchain4j.service;

import org.springframework.stereotype.Service;

/**
 * @author yff
 * @date 2026-01-31 14:03:25
 */
@Service
public interface ConsultantService {

    //用于里聊天的方法
    String chat(String message);

}
