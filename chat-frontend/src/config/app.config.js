/**
 * 应用配置文件
 */

export const APP_CONFIG = {
    // 应用名称
    APP_NAME: 'AI 智能咨询助手',

    // 版本号
    VERSION: '1.0.0',

    // 默认语言
    DEFAULT_LOCALE: 'zh-CN',

    // 消息相关配置
    MESSAGE: {
        // 最大消息长度
        MAX_LENGTH: 2000,
        // 历史消息最大保留数量
        MAX_HISTORY: 100
    }
}

// 建议问题列表
export const SUGGESTION_QUESTIONS = [
    '你好，请介绍一下你自己',
    '今天天气怎么样？',
    '给我讲个笑话',
    '有什么建议给我？'
]
